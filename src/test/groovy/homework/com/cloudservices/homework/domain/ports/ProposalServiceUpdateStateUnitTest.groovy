package homework.com.cloudservices.homework.domain.ports


import com.cloudservices.homework.domain.model.proposal.Proposal
import com.cloudservices.homework.domain.model.proposal.ProposalState
import com.cloudservices.homework.domain.model.proposal.exceptions.ProposalStateCannotBeUpdatedException
import com.cloudservices.homework.domain.ports.ProposalRepository
import com.cloudservices.homework.domain.ports.ProposalService
import org.bson.types.ObjectId
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static com.cloudservices.homework.domain.model.proposal.ProposalState.*

class ProposalServiceUpdateStateUnitTest extends Specification {

    private final def ID = ObjectId.get().toString()
    private final def OLD_NAME = "name"
    private final def OLD_CONTENT = "content"

    ProposalRepository repository = Mock(ProposalRepository)
    @Subject
    ProposalService service = new ProposalService(repository)

    @Unroll
    def "Should update proposal state from CREATED to #updatedState"() {
        given:
            def oldProposal = createProposalWithFixedState(CREATED)
            def updatedProposal = createProposalWithFixedState(updatedState)

        when:
            service.updateState(ID, updatedState, reason)

        then:
            1 * repository.findById(ID) >> oldProposal
            1 * repository.update(updatedProposal)

        where:
            updatedState | reason
            VERIFIED     | null
            DELETED      | "reason"
    }

    @Unroll
    def "Should NOT update proposal state from CREATED to #updatedState"() {
        given:
            def oldProposal = createProposalWithFixedState(CREATED)

        when:
            service.updateState(ID, updatedState, reason)

        then:
            1 * repository.findById(ID) >> oldProposal
            thrown(ProposalStateCannotBeUpdatedException)

        where:
            updatedState | reason
            CREATED      | null
            ACCEPTED     | null
            REJECTED     | "reason"
            PUBLISHED    | null
    }

    @Unroll
    def "Should update proposal state from VERIFIED to #updatedState"() {
        given:
            def oldProposal = createProposalWithFixedState(VERIFIED)
            def updatedProposal = createProposalWithFixedState(updatedState)

        when:
            service.updateState(ID, updatedState, reason)

        then:
            1 * repository.findById(ID) >> oldProposal
            1 * repository.update(updatedProposal)

        where:
            updatedState | reason
            ACCEPTED     | null
            REJECTED     | "reason"
    }

    @Unroll
    def "Should NOT update proposal state from VERIFIED to #updatedState"() {
        given:
            def oldProposal = createProposalWithFixedState(VERIFIED)

        when:
            service.updateState(ID, updatedState, reason)

        then:
            1 * repository.findById(ID) >> oldProposal
            thrown(ProposalStateCannotBeUpdatedException)

        where:
            updatedState | reason
            DELETED      | "reason"
            PUBLISHED    | null
            CREATED      | null
            VERIFIED     | null
    }

    @Unroll
    def "Should update proposal state from ACCEPTED to #updatedState"() {
        given:
            def oldProposal = createProposalWithFixedState(ACCEPTED)
            def updatedProposal = createProposalWithFixedState(updatedState)

        when:
            service.updateState(ID, updatedState, reason)

        then:
            1 * repository.findById(ID) >> oldProposal
            1 * repository.update(updatedProposal)

        where:
            updatedState | reason
            PUBLISHED    | null
            REJECTED     | "reason"
    }

    @Unroll
    def "Should NOT update proposal state from ACCEPTED to #updatedState"() {
        given:
            def oldProposal = createProposalWithFixedState(ACCEPTED)

        when:
            service.updateState(ID, updatedState, reason)

        then:
            1 * repository.findById(ID) >> oldProposal
            thrown(ProposalStateCannotBeUpdatedException)

        where:
            updatedState | reason
            ACCEPTED     | null
            VERIFIED     | null
            DELETED      | "reason"
            CREATED      | null
    }

    @Unroll
    def "Should NOT update proposal state from DELETED to #updatedState"() {
        given:
            def oldProposal = createProposalWithFixedState(DELETED)

        when:
            service.updateState(ID, updatedState, reason)

        then:
            1 * repository.findById(ID) >> oldProposal
            thrown(ProposalStateCannotBeUpdatedException)

        where:
            updatedState | reason
            ACCEPTED     | null
            VERIFIED     | null
            DELETED      | "reason"
            CREATED      | null
            REJECTED     | "reason"
            PUBLISHED    | null
    }

    @Unroll
    def "Should NOT update proposal state from REJECTED to #updatedState"() {
        given:
            def oldProposal = createProposalWithFixedState(REJECTED)

        when:
            service.updateState(ID, updatedState, reason)

        then:
            1 * repository.findById(ID) >> oldProposal
            thrown(ProposalStateCannotBeUpdatedException)

        where:
            updatedState | reason
            ACCEPTED     | null
            VERIFIED     | null
            DELETED      | "reason"
            CREATED      | null
            REJECTED     | "reason"
            PUBLISHED    | null
    }

    @Unroll
    def "Should NOT update proposal state from PUBLISHED to #updatedState"() {
        given:
            def oldProposal = createProposalWithFixedState(PUBLISHED)

        when:
            service.updateState(ID, updatedState, reason)

        then:
            1 * repository.findById(ID) >> oldProposal
            thrown(ProposalStateCannotBeUpdatedException)

        where:
            updatedState | reason
            ACCEPTED     | null
            VERIFIED     | null
            DELETED      | "reason"
            CREATED      | null
            REJECTED     | "reason"
            PUBLISHED    | null
    }

    def "Should NOT update proposal state when no reason"() {
        when:
            service.updateState(ID, updatedState, reason)

        then:
            0 * _
            thrown(ProposalStateCannotBeUpdatedException)

        where:
            updatedState | reason
            DELETED      | null
            DELETED      | ""
            DELETED      | " "
            REJECTED     | null
            REJECTED     | ""
            REJECTED     | " "
    }

    private def createProposalWithFixedState(ProposalState state) {
        new Proposal(ID, OLD_NAME, OLD_CONTENT, state, null)
    }

}
