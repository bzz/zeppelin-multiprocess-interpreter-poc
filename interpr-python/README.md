#Python Interpreter example


## Quickstart

    #see zeppelin-mock-java/README for details on how to instal thrift 0.9

    #generate py Thrift code
    thrift -r --gen py ../zeppelin-mock-java/src/main/thrift/interpreter.thrift
    interpreter.py