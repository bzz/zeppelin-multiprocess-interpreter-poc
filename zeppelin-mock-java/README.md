#Mock of Zeppelin (in Java)


## Generate Thrift Classes for RPC

    #make sure thrift is installed
    brew update && brew install thrift --with-java --with-python

    #generate classes
    thrift -r --gen java -out src/gen/java src/main/thrift/interpreter.thrift