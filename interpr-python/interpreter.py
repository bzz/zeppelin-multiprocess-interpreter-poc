#!/usr/bin/python
#Pure python interpreter for Zeppelin

#Intended to be run as a separate process and communicate with
# Z though Thrift RPC

import sys
import socket
sys.path.append('./gen-py')

from zeppelin import InterpreterServer
from zeppelin.ttypes import *

from thrift import Thrift
from thrift.transport import TSocket
from thrift.transport import TTransport
from thrift.protocol import TCompactProtocol



class InterpreterServerHandler:
  def __init__(self):
    self.log = {}

  def sutdown(self):
    print "Shuting Interpreter down"

  def interprete(self, text):
    print "Interpreting: ", text
    return InterpreterResult("Done by " + socket.gethostbyname(socket.gethostname()))


def main():
  try:
    handler = InterpreterServerHandler()
    processor = InterpreterServer.Processor(handler)
    transport = TSocket.TServerSocket(9090)
    tfactory = TTransport.TFramedTransportFactory()
    pfactory = TCompactProtocol.TCompactProtocolFactory()
     
    server = TServer.TSimpleServer(processor, transport, tfactory, pfactory)
     
    print "Starting Python Interpreter server..."
    server.serve()
    print "Done."
  
  except Thrift.TException, tx:
    print '%s' % (tx.message)

if __name__ == '__main__':
  main()
