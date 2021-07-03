package homework.com.cloudservices.homework.domain.ports

import com.cloudservices.homework.domain.model.proposal.Proposal
import com.cloudservices.homework.domain.model.proposal.ProposalState
import com.cloudservices.homework.domain.model.proposal.exceptions.ProposalContentCannotBeUpdatedException
import com.cloudservices.homework.domain.ports.ProposalRepository
import com.cloudservices.homework.domain.ports.ProposalService
import org.bson.types.ObjectId
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static com.cloudservices.homework.domain.model.proposal.ProposalState.*

class ProposalServiceUpdateContentUnitTest extends Specification {

    private final def ID = ObjectId.get().toString()
    private final def OLD_NAME = "name"
    private final def OLD_CONTENT = "content"
    private final def UPDATED_CONTENT = "updated content"

    ProposalRepository repository = Mock(ProposalRepository)
    @Subject
    ProposalService service = new ProposalService(repository)

    @Unroll
    def "Should update proposal content"() {
        given:
            def oldProposal = createProposalWithFixedState(state)
            def updatedProposal = createProposalWithUpdatedContent(state)

        when:
            service.updateContent(ID, UPDATED_CONTENT)

        then:
            1 * repository.findById(ID) >> oldProposal
            1 * repository.update(updatedProposal)

        where:
            state << [CREATED, VERIFIED]
    }

    @Unroll
    def "Should NOT update proposal content"() {
        given:
            def oldProposal = createProposalWithFixedState(state)

        when:
            service.updateContent(ID, UPDATED_CONTENT)

        then:
            1 * repository.findById(ID) >> oldProposal
            thrown(ProposalContentCannotBeUpdatedException)

        where:
            state << [PUBLISHED, REJECTED, ACCEPTED, DELETED]
    }

    private def createProposalWithFixedState(ProposalState state) {
        new Proposal(ID, OLD_NAME, OLD_CONTENT, state, null)
    }

    private def createProposalWithUpdatedContent(ProposalState state) {
        new Proposal(ID, OLD_NAME, UPDATED_CONTENT, state, null)
    }

}
