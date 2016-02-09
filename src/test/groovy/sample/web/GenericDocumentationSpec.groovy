package sample.web

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

import javax.servlet.RequestDispatcher

import org.springframework.http.MediaType
import org.springframework.test.web.servlet.ResultActions

class GenericDocumentationSpec extends BaseControllerSpec {

    void 'test and document error format'() {
        when:
        ResultActions result = this.mockMvc.perform(put('/error')
            .contentType(MediaType.APPLICATION_JSON)
            .requestAttr(RequestDispatcher.ERROR_STATUS_CODE, 405)
            .requestAttr(RequestDispatcher.ERROR_REQUEST_URI, '/hello')
            .requestAttr(RequestDispatcher.ERROR_MESSAGE, "Required String parameter 'name' is not present"))

        then:
        result
            .andExpect(status().isMethodNotAllowed())
            .andDo(document('error-example', preprocessResponse(prettyPrint()),
            responseFields(
                fieldWithPath('error').description('The HTTP error that occurred, e.g. `Bad Request`'),
                fieldWithPath('message').description('A description of the cause of the error'),
                fieldWithPath('path').description('The path to which the request was made'),
                fieldWithPath('status').description('The HTTP status code, e.g. `400`'),
                fieldWithPath('timestamp').description('The time, in milliseconds, at which the error occurred'))
        ))
    }
}
