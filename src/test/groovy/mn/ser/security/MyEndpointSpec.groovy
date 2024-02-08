package mn.ser.security

import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import org.hibernate.stat.Statistics
import spock.lang.Specification

@MicronautTest(application = Application, transactional = false)
class MyEndpointSpec extends Specification {

    @Inject
    @Client('/')
    HttpClient client

    void "reset the H8 stats"() {

        when: 'contact the endpoint to reset the stats'
        def req = HttpRequest.POST("/h8stats", [:])
        def res = client.toBlocking().exchange(req, Statistics)

        then: 'the stats have been obtained'
        def body = res.body()
        body != null
    }
}
