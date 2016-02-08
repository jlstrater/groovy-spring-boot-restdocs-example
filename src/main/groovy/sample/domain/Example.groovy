package sample.domain

class Example {
    String name
    String message

    String setName(String name) {
        this.message = "Hello, $name"
        this.name = name
    }
}
