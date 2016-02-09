package sample.domain

import groovy.transform.CompileStatic

@CompileStatic
class Example {
    String name
    String message

    String setName(String name) {
        this.message = "Hello, $name!"
        this.name = name
    }
}
