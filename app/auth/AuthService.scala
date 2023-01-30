package auth

import com.auth0.jwk.UrlJwkProvider
import pdi.jwt.{JwtAlgorithm, JwtBase64, JwtClaim, JwtJson}
import play.api.Configuration

import java.time.Clock
import javax.inject.Inject
import scala.util.{Failure, Success, Try}


class AuthService @Inject()(config: Configuration) {

  private val jwtRegex = """(.+?)\.(.+?)\.(.+?)""".r

  private def domain = config.get[String]("auth0.domain")

  private def audience = config.get[String]("auth0.audience")

  private def issuer = s"https://$domain/"

  def validateJwt(token: String): Try[JwtClaim] = for {
    jwk <- getJwk(token) // Get the secret key for this token
    claims <- JwtJson.decode(token, jwk.getPublicKey, Seq(JwtAlgorithm.RS256)) // Decode the token using the secret key
    _ <- validateClaims(claims) // validate the data stored inside the token
  } yield claims

  private val splitToken = (jwt: String) => jwt match {
    case jwtRegex(header, body, sig) => Success((header, body, sig))
    case _ => Failure(new Exception("Token does not match the correct pattern"))
  }

  private val decodeElements = (data: Try[(String, String, String)]) => data map {
    case (header, body, sig) =>
      (JwtBase64.decodeString(header), JwtBase64.decodeString(body), sig)
  }

  private val getJwk = (token: String) =>
    (splitToken andThen decodeElements) (token) flatMap {
      case (header, _, _) =>
        val jwtHeader = JwtJson.parseHeader(header) // extract the header
        val jwkProvider = new UrlJwkProvider(s"https://$domain")

        // Use jwkProvider to load the JWKS data and return the JWK
        jwtHeader.keyId.map { k =>
          Try(jwkProvider.get(k))
        } getOrElse Failure(new Exception("Unable to retrieve kid"))
    }

  implicit val clock: Clock = Clock.systemUTC
  private val validateClaims = (claims: JwtClaim) =>

    if (claims.isValid(issuer, audience)) {
      Success(claims)
    } else {
      Failure(new Exception("The JWT did not pass validation"))
    }
}
