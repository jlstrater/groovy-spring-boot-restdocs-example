package sample.web

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.MediaType
import org.springframework.restdocs.payload.JsonFieldType
import org.springframework.test.web.servlet.ResultActions
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import sample.domain.Example

class ExampleControllerSpec extends BaseControllerSpec {

    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new ExampleController())
                .apply(documentationConfiguration(this.restDocumentation))
                .build()
    }

    void 'test and document get on example endpoint'() {
        when:
        ResultActions result = this.mockMvc.perform(get('/hello')
                .contentType(MediaType.APPLICATION_JSON))

        then:
        result
            .andExpect(status().isOk())
            .andExpect(jsonPath('name').value('World'))
            .andExpect(jsonPath('message').value('Hello, World!'))
            .andDo(document('hello-get-example', preprocessResponse(prettyPrint()),
            responseFields(
                fieldWithPath('name').type(JsonFieldType.STRING)
                        .description('name'),
                fieldWithPath('message').type(JsonFieldType.STRING)
                        .description('message'))
        ))
    }

    void 'test and document post with example endpoint and custom name'() {
        when:
        ResultActions result = this.mockMvc.perform(post('/hello')
            .content(new ObjectMapper().writeValueAsString(new Example(name: 'mockmvc test')))
            .contentType(MediaType.APPLICATION_JSON))
        then:
        result
            .andExpect(status().isOk())
            .andExpect(jsonPath('name').value('mockmvc test'))
            .andExpect(jsonPath('message').value('Hello, mockmvc test!'))
            .andDo(document('hello-post-example', preprocessResponse(prettyPrint()),
            responseFields(
                fieldWithPath('name').type(JsonFieldType.STRING)
                        .description('name'),
                fieldWithPath('message').type(JsonFieldType.STRING)
                        .description('message'))
        ))
    }

    void 'test and document get of a list from example endpoint'() {
        when:
        ResultActions result = this.mockMvc.perform(get('/hello/list')
            .contentType(MediaType.APPLICATION_JSON))

        then:
        result
            .andExpect(status().isOk())
            .andDo(document('hello-list-example', preprocessResponse(prettyPrint()),
            responseFields(
                fieldWithPath('[].message').type(JsonFieldType.STRING)
                        .description('message'))
        ))
    }
}
