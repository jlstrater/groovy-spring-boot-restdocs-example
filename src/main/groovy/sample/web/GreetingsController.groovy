package sample.web

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import sample.domain.Greeting

@CompileStatic
@RestController
@RequestMapping('/greetings')
@Slf4j
class GreetingsController {
    Integer counter = 6
    List<Greeting> greetings = [new Greeting(id: 1, message: 'Hello'), new Greeting(id: 2, message: 'Hi'),
                                new Greeting(id: 3, message: 'Hola'), new Greeting(id: 4, message: 'Olá'),
                                new Greeting(id: 5, message: 'Hej')]

    @RequestMapping(method = RequestMethod.GET, produces = 'application/json')
    ResponseEntity<?> list(@RequestParam(required = false) String message) {
        if (message) {
            return ResponseEntity.ok(greetings.find { it.message == message })
        }
        return ResponseEntity.ok(greetings)
    }

    @RequestMapping(path= '/{id}', method = RequestMethod.GET, produces = 'application/json')
    ResponseEntity<?> getById(@PathVariable String id) {
        Greeting greeting = greetings.find { it.id == id.toInteger() }
        if (!greeting) {
            return ResponseEntity.noContent().build()
        }
        return ResponseEntity.ok(greeting)
    }

    @SuppressWarnings('SpaceAroundOperator')
    @RequestMapping(method = RequestMethod.POST, produces = 'application/json', consumes = 'application/json')
    ResponseEntity<?> post(@RequestBody Greeting example) {
        Greeting existingGreeting = greetings.find { it.id == example.id } ?:
                greetings.find { it.message == example.message }
        if (!existingGreeting) {
            greetings << example
            if (!example.id) {
                example.id = counter
                counter++
            }
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest().path('/{id}')
                    .buildAndExpand(example.id).toUri()
            return ResponseEntity.created(location).build()
        }
        return ResponseEntity.noContent().build()
    }
}
