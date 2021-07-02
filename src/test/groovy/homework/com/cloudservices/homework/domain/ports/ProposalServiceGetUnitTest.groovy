package homework.com.cloudservices.homework.domain.ports

import com.cloudservices.homework.domain.ports.ProposalRepository
import com.cloudservices.homework.domain.ports.ProposalService
import spock.lang.Specification
import spock.lang.Subject

class ProposalServiceGetUnitTest extends Specification {


    ProposalRepository repository = Mock(ProposalRepository)
    @Subject
    ProposalService subject = new ProposalService(repository)

    def "Should get proposal page"() {

    }

}
