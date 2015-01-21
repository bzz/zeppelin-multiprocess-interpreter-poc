package zeppelin;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

class ZeppelinServer {
  private static final String HOST = "localhost";
  private static final int PORT = 9090;
  private static final int SOCKET_TIMEOUT = 1000;


  static class RemoteInterpreter {
    private TSocket socket;
    private TTransport transport;
    private InterpreterServer.Client client;
    
    void start() {
      System.out.println("Starting remote Scala Interpreter");

      //TODO
      //fork interpreter process from ../interp-scala/bin/
      System.out.println("Done.");

      System.out.println("Establishing connection to interprete");
      socket = new TSocket(HOST, PORT);
      transport = new TFramedTransport(socket);
      socket.setTimeout(SOCKET_TIMEOUT);
      final TProtocol protocol = new TCompactProtocol(transport);

      client = new InterpreterServer.Client(protocol);
      try {
        transport.open();
      } catch (TTransportException e) {
        e.printStackTrace();
        System.out.println("Failed to connect to " + HOST + ":" + PORT);
        //TODO: re-try policy
      }
      System.out.println("Done");
    }
    
    void stop() {
      try {
        client.shutdown();
      } catch (TException e) {
        e.printStackTrace();
      }
      transport.close();
    }
    
    InterpreterResult interperte(String text) {
      //sent it a command
      InterpreterResult ir = null;
      try {
        ir = client.interprete(text);
      } catch (TException e) {
        e.printStackTrace();
      }
      System.out.println("In:\t" + text + " \nOut:\t" + ir.result);
      return ir;
    }
  }

  
  public static final void main(String[] argv) {
    System.out.println("Starting Zeppelin server");

    RemoteInterpreter rmi = new RemoteInterpreter();
    rmi.start();

    InterpreterResult r = rmi.interperte("val i = 1");

    rmi.stop();
  }

}
