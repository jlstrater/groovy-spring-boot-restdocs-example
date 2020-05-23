package sample

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

@CompileStatic
@Component
class DataInitializer {

    @Autowired
    MongoTemplate template

    @PostConstruct
    void onStartup() {
        template.db.drop()
    }

}
