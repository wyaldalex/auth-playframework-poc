package repositories

import models.{Comment, Post}

import javax.inject.Singleton

@Singleton
class DataRepository {

  private val posts = Seq(
    Post(1,"This is the first blog ever"),
    Post(2, "Another blog with awesome content")
  )

  private val comments = Seq(
    Comment(1, 1,"Good post", "Cersei Lan"),
    Comment(2, 1,"Bad post", "John S"),
    Comment(3, 2,"Such a so so post", "Tyrion Lan"),
  )

  def getPost(postId: Int): Option[Post] = posts.collectFirst{
    case p if p.id == postId => p
  }

  def getComments(postId: Int): Seq[Comment] = comments.collect {
    case c if c.postId == postId => c
  }

}
