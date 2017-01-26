package sample.web

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.test.web.servlet.ResultActions
import sample.domain.Greeting

import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders
import org.springframework.restdocs.payload.FieldDescriptor

class GreetingsControllerSpec extends BaseControllerSpec {

    void 'test and document get of a list of greetings'() {
        when:
        ResultActions result = this.mockMvc.perform(get('/greetings')
                .contentType(MediaType.TEXT_PLAIN))

        then:
        result
                .andExpect(status().isOk())
                .andDo(document('greetings-list-example', preprocessResponse(prettyPrint()),
                responseFields(greetingList)
        ))
    }

    void 'test and document getting the greeting matching the message'() {
        when:
        ResultActions result = this.mockMvc.perform(get('/greetings')
                .param('message', 'Hello')
                .contentType(MediaType.APPLICATION_JSON))

        then:
        result
            .andExpect(status().isOk())
            .andExpect(jsonPath('message').value('Hello'))
            .andDo(document('greetings-get-example', preprocessResponse(prettyPrint()),
            responseFields(greeting)))
    }

    void 'test and document getting a greeting by id'() {
        when:
        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.get('/greetings/{id}', 1))

        then:
        result
            .andExpect(status().isOk())
            .andExpect(jsonPath('id').value(1))
            .andDo(document('greetings-get-by-id-example',
                preprocessResponse(prettyPrint()),
                pathParameters(parameterWithName('id').description("The greeting's id")),
                responseFields(greeting)
        ))
    }

    void 'test and document creating a greeting with a custom name'() {
        when:
        ResultActions result = this.mockMvc.perform(post('/greetings')
            .content(new ObjectMapper().writeValueAsString(new Greeting(message: 'Hej JFokus!')))
            .contentType(MediaType.APPLICATION_JSON))
        then:
        result
            .andExpect(status().isCreated())
            .andDo(document('greetings-post-example', preprocessResponse(prettyPrint()),
                requestFields([fieldWithPath('id').type(JsonFieldType.NULL).optional()
                                       .description("The greeting's id"),
                               fieldWithPath('message').type(JsonFieldType.STRING)
                                       .description("The greeting's message")])
        ))
    }

    FieldDescriptor[] greeting = new FieldDescriptor().with {
        [fieldWithPath('id').type(JsonFieldType.NUMBER)
                 .description("The greeting's id"),
         fieldWithPath('message').type(JsonFieldType.STRING)
                 .description("The greeting's message")]
    }

    FieldDescriptor[] greetingList = new FieldDescriptor().with {
        [fieldWithPath('[].id').type(JsonFieldType.NUMBER).optional()
                 .description("The greeting's id"),
         fieldWithPath('[].message').type(JsonFieldType.STRING)
                 .description("The greeting's message")]
    }
}
