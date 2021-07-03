package homework.com.cloudservices.homework.domain.ports

import com.cloudservices.homework.domain.model.proposal.NewProposal
import com.cloudservices.homework.domain.model.proposal.exceptions.ProposalCannotBeCreatedException
import com.cloudservices.homework.domain.ports.ProposalRepository
import com.cloudservices.homework.domain.ports.ProposalService
import spock.lang.Specification
import spock.lang.Subject

class ProposalServiceCreateUnitTest extends Specification {

    private static final def NAME = "name"

    private static final def CONTENT = "content"

    private def newProposal = new NewProposal(NAME, CONTENT)

    ProposalRepository repository = Mock(ProposalRepository)
    @Subject
    ProposalService service = new ProposalService(repository)

    def "Should create new proposal"() {
        when: "all fields are not null"
            service.create(NAME, CONTENT)
        then: "proposal should be persisted"
            1 * repository.save(newProposal)
    }

    def "Should not create new proposal"() {
        when: "create new proposal"
            service.create(name, content)
        then: "throw exception"
            thrown(ProposalCannotBeCreatedException)
        where: "any of fields is null"
            name | content
            null | CONTENT
            NAME | null
            null | null
    }

}
