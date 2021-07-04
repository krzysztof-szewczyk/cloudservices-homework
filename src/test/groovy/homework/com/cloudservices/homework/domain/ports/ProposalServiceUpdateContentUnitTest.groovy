package homework.com.cloudservices.homework.domain.ports

import com.cloudservices.homework.domain.model.proposal.Proposal
import com.cloudservices.homework.domain.model.proposal.ProposalState
import com.cloudservices.homework.domain.model.proposal.exceptions.ProposalContentCannotBeUpdatedException
import com.cloudservices.homework.domain.ports.ProposalRepository
import com.cloudservices.homework.domain.ports.ProposalService
import com.cloudservices.homework.domain.ports.UuidGenerator
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
    UuidGenerator generator = Mock(UuidGenerator)

    @Subject
    ProposalService subject = new ProposalService(repository, generator)

    @Unroll
    def "Should update proposal content"() {
        given:
            def oldProposal = createProposalWithFixedState(state)
            def updatedProposal = createProposalWithUpdatedContent(state)

        when:
            subject.updateContent(ID, UPDATED_CONTENT)

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
            subject.updateContent(ID, UPDATED_CONTENT)

        then:
            1 * repository.findById(ID) >> oldProposal
            thrown(ProposalContentCannotBeUpdatedException)

        where:
            state << [PUBLISHED, REJECTED, ACCEPTED, DELETED]
    }

    private def createProposalWithFixedState(ProposalState state) {
        Proposal.builder()
                .id(ID)
                .name(OLD_NAME)
                .content(OLD_CONTENT)
                .state(state)
                .build()
    }

    private def createProposalWithUpdatedContent(ProposalState state) {
        Proposal.builder()
                .id(ID)
                .name(OLD_NAME)
                .content(UPDATED_CONTENT)
                .state(state)
                .build()
    }

}
