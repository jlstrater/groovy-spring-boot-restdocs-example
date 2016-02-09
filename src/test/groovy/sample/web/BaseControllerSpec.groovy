package sample.web

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import groovy.transform.CompileStatic
import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.restdocs.RestDocumentation
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification
import sample.SampleApplication

@CompileStatic
@ContextConfiguration(classes = [SampleApplication])
@WebAppConfiguration
class BaseControllerSpec extends Specification {

    @Rule
    RestDocumentation restDocumentation = new RestDocumentation('src/docs/generated-snippets')

    protected MockMvc mockMvc

    @Autowired
    private WebApplicationContext context

    void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context)
                .apply(documentationConfiguration(this.restDocumentation))
                .build()
    }
}
