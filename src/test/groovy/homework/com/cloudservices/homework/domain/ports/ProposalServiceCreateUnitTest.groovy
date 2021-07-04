package homework.com.cloudservices.homework.domain.ports

import com.cloudservices.homework.domain.model.proposal.NewProposal
import com.cloudservices.homework.domain.model.proposal.exceptions.ProposalCannotBeCreatedException
import com.cloudservices.homework.domain.ports.ProposalRepository
import com.cloudservices.homework.domain.ports.ProposalService
import com.cloudservices.homework.domain.ports.UuidGenerator
import spock.lang.Specification
import spock.lang.Subject

class ProposalServiceCreateUnitTest extends Specification {

    private static final def NAME = "name"

    private static final def CONTENT = "content"

    private def newProposal = new NewProposal(NAME, CONTENT)

    ProposalRepository repository = Mock(ProposalRepository)
    UuidGenerator generator = Mock(UuidGenerator)

    @Subject
    ProposalService subject = new ProposalService(repository, generator)

    def "Should create new proposal"() {
        when: "all fields are not null"
            subject.create(NAME, CONTENT)
        then: "proposal should be persisted"
            1 * repository.save(newProposal)
    }

    def "Should not create new proposal"() {
        when: "create new proposal"
            subject.create(name, content)
        then: "throw exception"
            thrown(ProposalCannotBeCreatedException)
        where: "any of fields is null"
            name | content
            null | CONTENT
            NAME | null
            null | null
    }

}
