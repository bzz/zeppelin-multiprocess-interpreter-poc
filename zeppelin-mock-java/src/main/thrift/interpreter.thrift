namespace java zeppelin
namespace scala zeppelin
namespace py zeppelin


struct InterpreterResult {
    1: required string result
    //stdout
    //stderr
}

service InterpreterServer {
    InterpreterResult interprete(1:string text)
    void shutdown()
}