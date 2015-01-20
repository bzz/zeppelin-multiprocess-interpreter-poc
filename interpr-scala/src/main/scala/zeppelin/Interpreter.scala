package zeppelin

import org.apache.thrift._
import org.apache.thrift.protocol._
import org.apache.thrift.server._
import org.apache.thrift.transport._
import scala.concurrent.Future
import org.apache.thrift.server.TServer.Args

class TwitterImpl extends Twitter[Future] {
  override def ping() {
    
  }

  override def postTweet(tweet: Tweet): Boolean {
    
  }

  override def searchTweeets(query: String): TweetSearchResult {
    
  }

}

object Interpreter extends App {

  println("Starting Zeppelin interpreter")
  try {
    val server = buildServer()    
    server.serve();     
  } catch { 
    case x: Exception => x.printStackTrace();
  }

  def buildServer() = {
    val transport = new TServerSocket(9090)
    val processor = new TwitterImpl()
  
    //val server = new TThreadPoolServer(new Args(transport).processor(processor))
    val server = new TSimpleServer(new Args(transport).processor(processor))
    server
  }
}
