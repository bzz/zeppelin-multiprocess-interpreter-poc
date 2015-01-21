# PoC for multiprocess zeppelin interpreter

**Idea**: Decouple ZeppelinServer JVM process with the Interpreter instances by running each interpreter in it's own process and communicate with Server though RPC.

**Status**: _alpha_

Current PoC consists of:
 - zeppelin-mock-jave the Thrift client, spawning Interperter processes
 - interp-scala the Thrift server, using plain scala interpreter
 - interp-python the the the Thrift server, plain python interpreter
 - interp-spark TBD
 - interp-pyspark TBD

In current version Thrift client in Zeppelin _pulls_ Thrift servers with Interpreter instances

Quickstart:

    #build
    ./gradlew build

    #manually start interpreter
    java -jar ./interpr-scala/build/libs/interpr-scala-0.1.jar

    #start Zeppelin (mock)
    java -jar zeppelin-mock-java/build/libs/zeppelin-mock-java.jar


TODO:
  - [ ] make zeppelin-mock-jave actually spawn the inerperter processes
  - [ ] implement AsyncHandlers on servers, move from SimpleServer?
  - [ ] add actual interpreter calls in Python\Scala PoCs
  - [ ] streamin stdout\stderr back to clients
 
