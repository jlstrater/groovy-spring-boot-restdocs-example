package sample.repository

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import sample.model.Greeting

@Repository
interface GreetingRepository extends ReactiveCrudRepository<Greeting, String> {

}
