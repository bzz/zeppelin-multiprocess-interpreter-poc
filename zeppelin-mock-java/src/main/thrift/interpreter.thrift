namespace java zeppelin
namespace scala zeppelin
namespace python zeppelin


struct InterpreterResult {
    1: required string result
}

service InterpreterServer {
    InterpreterResult interprete(1:string text)
}