package sample.web

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import sample.model.Greeting
import sample.repository.GreetingRepository

import javax.validation.Valid

@CompileStatic
@RestController
@RequestMapping('/greetings')
class GreetingsController {

    @Autowired
    GreetingRepository greetingsRepository

    @PostMapping()
    Mono<Greeting> createGreeting(@Valid @RequestBody Greeting greeting) {
        return greetingsRepository.save(greeting)
    }

    @GetMapping()
    Flux<Greeting> listAllGreetings() {
        return greetingsRepository.findAll()
    }

    @GetMapping('/{id}')
    Mono<Greeting> getGreetingById(@PathVariable(value = 'id') String greetingId) {
        greetingsRepository.findById(greetingId)
    }

}
