package sample

import groovy.transform.CompileStatic
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@CompileStatic
@SpringBootApplication
class ExampleGreetingsApplication {

    static void main(String[] args) {
        SpringApplication.run ExampleGreetingsApplication, args
    }

}
