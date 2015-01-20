package zeppelin;

import java.util.List;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

class Zeppelin {
  private static final String HOST = "localhost";
  private static final int PORT = 9090;
  private static final int SOCKET_TIMEOUT = 1000;

  public static final void main(String[] argv) {
    System.out.println("Starting Zeppelin server");

    //start ThriftClient
    final TSocket socket = new TSocket(HOST, PORT);
		socket.setTimeout(SOCKET_TIMEOUT);
		final TTransport transport = new TFramedTransport(socket);
		final TProtocol protocol = new TCompactProtocol(transport);
		final Twitter.Client client = new Twitter.Client(protocol);
		
		//The transport must be opened before you can begin using
		transport.open();

    //TODO
    //fork interpreter process from ../interp-scala/bin/


    //sent it a command
		List<String> classInv = client.getCourseInventory();
		System.out.println("Received " + classInv.size() + " class(es).");
		
		transport.close();
  }

}
