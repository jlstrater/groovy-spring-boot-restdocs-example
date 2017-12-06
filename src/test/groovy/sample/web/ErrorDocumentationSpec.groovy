package sample.web

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.restdocs.payload.JsonFieldType

class ErrorDocumentationSpec extends BaseControllerSpec {

    void 'test and document error format'() {
        expect:
        this.webTestClient.delete().uri('/')
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.METHOD_NOT_ALLOWED)
                .expectBody()
                .jsonPath('$.timestamp').isNotEmpty()
                .jsonPath('$.path').isNotEmpty()
                .jsonPath('$.message').isEqualTo("Response status 405 with reason \"Request method 'DELETE' not supported\"")
                .jsonPath('$.status').isEqualTo(405)
                .jsonPath('$.error').isEqualTo('Method Not Allowed')
                .consumeWith(document('error-example', preprocessResponse(prettyPrint()),
                responseFields([fieldWithPath('timestamp').type(JsonFieldType.STRING)
                                        .description("The request's timestamp"),
                                fieldWithPath('path').type(JsonFieldType.STRING)
                                        .description('The path that generated the error'),
                                fieldWithPath('message').type(JsonFieldType.STRING)
                                        .description('The human readable error message'),
                                fieldWithPath('status').type(JsonFieldType.NUMBER)
                                        .description('The HTTP status describing the error'),
                                fieldWithPath('error').type(JsonFieldType.STRING)
                                        .description('The short message describing the error')
                ])))
    }
}
