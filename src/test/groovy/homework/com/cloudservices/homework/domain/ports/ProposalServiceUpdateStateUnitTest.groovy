package homework.com.cloudservices.homework.domain.ports

import com.cloudservices.homework.domain.model.proposal.Proposal
import com.cloudservices.homework.domain.model.proposal.ProposalState
import com.cloudservices.homework.domain.model.proposal.exceptions.ProposalStateCannotBeUpdatedException
import com.cloudservices.homework.domain.ports.ProposalRepository
import com.cloudservices.homework.domain.ports.ProposalService
import com.cloudservices.homework.domain.ports.UuidGenerator
import org.bson.types.ObjectId
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Unroll

import static com.cloudservices.homework.domain.model.proposal.ProposalState.ACCEPTED
import static com.cloudservices.homework.domain.model.proposal.ProposalState.CREATED
import static com.cloudservices.homework.domain.model.proposal.ProposalState.DELETED
import static com.cloudservices.homework.domain.model.proposal.ProposalState.PUBLISHED
import static com.cloudservices.homework.domain.model.proposal.ProposalState.REJECTED
import static com.cloudservices.homework.domain.model.proposal.ProposalState.VERIFIED

class ProposalServiceUpdateStateUnitTest extends Specification {

    private final def ID = ObjectId.get().toString()
    private final def OLD_NAME = "name"
    private final def OLD_CONTENT = "content"
    private static final def UUID = 267477262965985540

    ProposalRepository repository = Mock(ProposalRepository)
    UuidGenerator generator = Mock(UuidGenerator)

    @Subject
    ProposalService subject = new ProposalService(repository, generator)

    @Unroll
    def "Should update proposal state from CREATED to #updatedState"() {
        given:
            def oldProposal = createProposalWithFixedState(CREATED)
            def updatedProposal = createProposalWithFixedState(updatedState, reason)

        when:
            subject.updateState(ID, updatedState, reason)

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
            subject.updateState(ID, updatedState, reason)

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
            def updatedProposal = createProposalWithFixedState(updatedState, reason)

        when:
            subject.updateState(ID, updatedState, reason)

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
            subject.updateState(ID, updatedState, reason)

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
            def updatedProposal = createProposalWithFixedState(updatedState, reason, uuid)

        when:
            subject.updateState(ID, updatedState, reason)

        then:
            1 * repository.findById(ID) >> oldProposal
            generatorCalls * generator.generateUuid() >> UUID
            1 * repository.update(updatedProposal)

        where:
            updatedState | reason   | uuid | generatorCalls
            PUBLISHED    | null     | UUID | 1
            REJECTED     | "reason" | null | 0
    }

    @Unroll
    def "Should NOT update proposal state from ACCEPTED to #updatedState"() {
        given:
            def oldProposal = createProposalWithFixedState(ACCEPTED)

        when:
            subject.updateState(ID, updatedState, reason)

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
            subject.updateState(ID, updatedState, reason)

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
            subject.updateState(ID, updatedState, reason)

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
            subject.updateState(ID, updatedState, reason)

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
            subject.updateState(ID, updatedState, reason)

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
        createProposalWithFixedState(state, null)
    }

    private def createProposalWithFixedState(ProposalState state, String reason) {
        createProposalWithFixedState(state, reason, null)
    }

    private def createProposalWithFixedState(ProposalState state, String reason, Long uuid) {
        Proposal.builder()
                .id(ID)
                .name(OLD_NAME)
                .content(OLD_CONTENT)
                .state(state)
                .reason(reason)
                .uuid(uuid)
                .build()
    }

}
