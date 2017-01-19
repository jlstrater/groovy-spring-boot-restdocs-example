package sample

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@CompileStatic
@Configuration
@EnableAutoConfiguration
@Slf4j
@ComponentScan(['sample.web'])
class SampleApplication extends WebMvcConfigurerAdapter {

	static void main(String[] args) throws Exception {
		SpringApplication.run(SampleApplication, args)
	}

	@Override
	void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController('/').setViewName('forward:/html5/index.html')
	}

}
