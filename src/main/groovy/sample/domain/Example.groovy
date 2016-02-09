package sample.domain

import com.fasterxml.jackson.annotation.JsonInclude
import groovy.transform.CompileStatic

@CompileStatic
class Example {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String name
    String message

    String setName(String name) {
        this.message = "Hello, $name!"
        this.name = name
    }
}
