package sample.web

import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import sample.domain.Example

@CompileStatic
@RestController
@RequestMapping('/hello')
@Slf4j
class ExampleController {

    @RequestMapping(method = RequestMethod.GET, produces = 'application/json')
    Example get() {
        new Example(name: 'World')
    }

    @RequestMapping(method = RequestMethod.POST, produces = 'application/json', consumes = 'application/json')
    Example post(@RequestBody Example example) {
        example
    }

    @RequestMapping(path='/error', method = RequestMethod.GET, produces = 'application/json',
            consumes = 'application/json')
    Example get(@RequestParam String name) {
        new Example(name: name)
    }

    @RequestMapping(path='/list', method = RequestMethod.GET, produces = 'application/json',
            consumes = 'application/json')
    List<Example> list() {
        [new Example(message: 'Hello'), new Example(message: 'Hi'), new Example(message: 'Hola'),
         new Example(message: 'Olá')]
    }
}
