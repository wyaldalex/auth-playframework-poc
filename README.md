# PlayFrameWork + Auth0 Test

## Setup Auth0 Account
https://auth0.com/signup

## 1) Setup environment variables

* **Unix**  
export AUTH0_DOMAIN=<your-tenant.auth0.com>  
export AUTH0_AUDIENCE=<your-domanin.com>  

* **Powershell**  
$env:AUTH0_DOMAIN = ‘your-tenant.auth0.com'  
$env:AUTH0_AUDIENCE = ‘your-domanin.com'  
   
## 2) Run the app

```console
sbt run <your-port-of-choice>
```

## Updated version based on tutorial:
https://auth0.com/blog/build-and-secure-a-scala-play-framework-api/