package homework.com.cloudservices.homework.adapters.api

import com.cloudservices.homework.HomeworkApplication
import com.cloudservices.homework.adapters.api.ProposalEndpoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@SpringBootTest
@ContextConfiguration(classes = [HomeworkApplication.class])
class ProposalAcceptanceTest extends Specification {

    @Autowired
    MockMvc mvc

    @Autowired
    ProposalEndpoint proposalApiService

    def "positive"() {
        when: "create new proposal"
            ResultActions resultActions = mvc.perform(post("/proposal")
                    .contentType(APPLICATION_JSON)
                    .content("""
                        {
                        "name":"my-name",
                        "content":"my-content"
                        }
                        """)
            )

        then: "created status should be returned"
            resultActions.andExpect(status().isCreated())

        when: "bump to VERIFIED status"
            def id = proposalApiService.findByNameOrState(null, null, PageRequest.of(0, 1))
                    .getContent()
                    .stream()
                    .map(a -> a.getId())
                    .findFirst()
                    .get()

            resultActions = mvc.perform(put("/proposal/state")
                    .contentType(APPLICATION_JSON)
                    .content("""
                        {
                        "id": "$id",
                        "state": "VERIFIED"
                        }
                        """)
            )

        then: "return OK"
            resultActions.andExpect(status().isOk())

        when: "modify content"
            resultActions = mvc.perform(put("/proposal/content")
                    .contentType(APPLICATION_JSON)
                    .content("""
                        {
                        "id": "$id",
                        "content": "updated my-content"
                        }
                        """)
            )

        then: "return OK"
            resultActions.andExpect(status().isOk())
    }

}
