package homework.com.cloudservices.homework.domain.ports


import com.cloudservices.homework.domain.ports.ProposalRepository
import com.cloudservices.homework.domain.ports.ProposalService
import com.cloudservices.homework.domain.ports.UuidGenerator
import org.springframework.data.domain.PageRequest
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static com.cloudservices.homework.domain.model.proposal.ProposalState.CREATED

class ProposalServiceFindPageUnitTest extends Specification {

    ProposalRepository repository = Mock(ProposalRepository)
    UuidGenerator generator = Mock(UuidGenerator)

    @Subject
    ProposalService subject = new ProposalService(repository, generator)

    @Unroll
    def "Should find proposals' page"() {
        def pageRequest = PageRequest.of(0, 10)
        when:
            subject.findByNameOrState(name, state, pageRequest)
        then:
            1 * repository.findByNameAndState(name, state, pageRequest)
        where:
            name   | state
            null   | null
            ""     | null
            "name" | null
            null   | CREATED
            "name" | CREATED
    }

}
