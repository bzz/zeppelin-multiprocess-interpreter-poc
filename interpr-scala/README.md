#Example or remote interpreter (in Scala)


Thrift does not have native Scala support, but there is https://github.com/twitter/scrooge
The only thing is that it's not usefable AT ALL with plain Thrift (without Finagle): problems with Structs, does not have Processor, etc.

So Scala uses Java thrift generated code, for deitalis, check ```zeppelin-mock-java/README.md```


