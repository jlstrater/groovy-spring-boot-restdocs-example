package sample.web

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document

import groovy.json.JsonSlurper
import org.springframework.http.MediaType
import org.springframework.restdocs.payload.FieldDescriptor
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.web.reactive.function.BodyInserters
import spock.lang.Shared
import spock.lang.Stepwise

@Stepwise
class GreetingsControllerSpec extends BaseControllerSpec {

    @Shared
    String greetingId

    void 'test and document creating a greeting with a custom name'() {
        when:
        def result = this.webTestClient.post().uri('/')
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject('{"message": "Hello!"}'))
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath('$.id').isNotEmpty()
                .jsonPath('$.message').isEqualTo('Hello!')
                .consumeWith(document('greetings-post-example',
                    requestFields(
                        fieldWithPath('message').type(JsonFieldType.STRING).description("The greeting's message"))))
        JsonSlurper slurper = new JsonSlurper()
        greetingId = slurper.parseText(new String(result.returnResult().body)).id

        then:
        assert greetingId
    }

    void 'test and document get of a list of greetings'() {
        expect:
        this.webTestClient.get().uri('/').accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document('greetings-list-example',
                   responseFields(greetingList)))
    }

    void 'test and document getting a greeting by id'() {
        expect:
        this.webTestClient.get().uri('/{id}', greetingId).accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document('greetings-get-by-id-example',
                pathParameters(parameterWithName('id').description("The greeting's id")),
                responseFields(greeting)
        ))
    }

    FieldDescriptor[] greeting = new FieldDescriptor().with {
        [fieldWithPath('id').type(JsonFieldType.STRING)
                 .description("The greeting's id"),
         fieldWithPath('message').type(JsonFieldType.STRING)
                 .description("The greeting's message")]
    }

    FieldDescriptor[] greetingList = new FieldDescriptor().with {
        [fieldWithPath('[].id').type(JsonFieldType.STRING).optional()
                 .description("The greeting's id"),
         fieldWithPath('[].message').type(JsonFieldType.STRING)
                 .description("The greeting's message")]
    }
}
