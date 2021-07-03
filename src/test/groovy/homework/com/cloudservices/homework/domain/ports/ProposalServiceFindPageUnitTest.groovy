package homework.com.cloudservices.homework.domain.ports


import com.cloudservices.homework.domain.ports.ProposalRepository
import com.cloudservices.homework.domain.ports.ProposalService
import org.springframework.data.domain.PageRequest
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static com.cloudservices.homework.domain.model.proposal.ProposalState.CREATED

class ProposalServiceFindPageUnitTest extends Specification {

    ProposalRepository repository = Mock(ProposalRepository)
    @Subject
    ProposalService subject = new ProposalService(repository)

    @Unroll
    def "Should find proposals' page"() {
        def pageRequest = PageRequest.of(0, 10)
        when:
            subject.findByNameOrState(name, state, pageRequest)
        then:
            1 * repository.findByNameOrState(name, state, pageRequest)
        where:
            name   | state
            null   | null
            ""     | null
            "name" | null
            null   | CREATED
            "name" | CREATED
    }

}
