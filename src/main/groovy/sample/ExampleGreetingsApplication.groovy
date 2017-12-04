package sample

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@CompileStatic
@Slf4j
@SpringBootApplication
class ExampleGreetingsApplication extends WebMvcConfigurerAdapter {

    static void main(String[] args) throws Exception {
        SpringApplication.run(ExampleGreetingsApplication, args)
    }

    @Override
    void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController('/').viewName = 'forward:/html5/index.html'
    }

}
