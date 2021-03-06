package zeppelin

import org.apache.thrift._
import org.apache.thrift.protocol._
import org.apache.thrift.server._
import org.apache.thrift.transport._
import org.apache.thrift.server.TServer.Args

class InterpreterServerImpl extends InterpreterServer.Iface {
  override def shutdown() = {
    println("Sutting down Scala interpreter")
    //TODO suicide, exit 0
    println("Done.")  
  }

  override def interprete(text: String): InterpreterResult = {
    println("Interpreting: " + text)
    new InterpreterResult("Ok")
  }
}

object Interpreter extends App {
  val port = 9090
  val host = "localhost"

  println("Starting Zeppelin interpreter")
  try {
    val server = buildServer()    
    server.serve();     
  } catch {
    case x: Exception => x.printStackTrace();
  }

  def buildServer() = {
    println("Listnenig port " + port)
    val transport = new TServerSocket(port)
    val processor = new InterpreterServer.Processor(new InterpreterServerImpl())

    //val server = new TThreadPoolServer(new Args(transport).processor(processor))
    val server = new TSimpleServer(new Args(transport)
      .processor(processor)
      .protocolFactory(new TCompactProtocol.Factory())
      .transportFactory(new TFramedTransport.Factory())
    )
    server
  }
}
