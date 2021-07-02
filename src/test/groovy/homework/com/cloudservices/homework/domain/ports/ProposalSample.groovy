package homework.com.cloudservices.homework.domain.ports

import com.cloudservices.homework.domain.model.proposal.Proposal
import com.cloudservices.homework.domain.model.proposal.ProposalState
import groovy.transform.CompileStatic
import org.bson.types.ObjectId

import static com.cloudservices.homework.domain.model.proposal.ProposalState.CREATED

@CompileStatic
trait ProposalSample {

    Proposal proposal = createProposal("name", "content", CREATED)

    static private Proposal createProposal(String name, String content, ProposalState state) {
        def id = ObjectId.get().toString()
        def proposal = new Proposal(id, name, content, state)
        proposal
    }
}