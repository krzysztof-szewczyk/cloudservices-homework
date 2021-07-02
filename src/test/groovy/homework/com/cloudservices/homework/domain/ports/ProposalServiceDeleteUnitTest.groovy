package homework.com.cloudservices.homework.domain.ports

import com.cloudservices.homework.domain.model.proposal.Proposal
import com.cloudservices.homework.domain.model.proposal.ProposalState
import com.cloudservices.homework.domain.ports.ProposalRepository
import com.cloudservices.homework.domain.ports.ProposalService
import org.bson.types.ObjectId
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static com.cloudservices.homework.domain.model.proposal.ProposalState.*

class ProposalServiceDeleteUnitTest extends Specification {

    private final def ID = ObjectId.get().toString()

    ProposalRepository repository = Mock(ProposalRepository)
    @Subject
    ProposalService subject = new ProposalService(repository)

    @Unroll
    def "Should delete proposal"() {
        given:
            def proposal = createProposalWithFixedState(state)
        when:
            subject.delete(ID)

        then:
            1 * repository.findById(ID) >> proposal
            1 * repository.delete(ID)

        where:
            state << [CREATED]
    }

    @Unroll
    def "Should not delete proposal"() {
        given:
            def proposal = createProposalWithFixedState(state)

        when:
            subject.delete(ID)

        then:
            1 * repository.findById(ID) >> proposal
            thrown()

        where:
            state << [ACCEPTED, DELETED, VERIFIED, REJECTED, PUBLISHED]
    }

    private def createProposalWithFixedState(ProposalState state) {
        new Proposal(ID, "name", "content", state)
    }

}
