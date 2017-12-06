package sample.model

import groovy.transform.CompileStatic
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.annotation.Id

import javax.validation.constraints.NotBlank

@CompileStatic
@Document(collection='greetings')
class Greeting {

    @Id
    String id

    @NotBlank
    String message
}
