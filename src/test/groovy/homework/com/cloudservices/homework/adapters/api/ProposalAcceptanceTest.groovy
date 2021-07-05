package homework.com.cloudservices.homework.adapters.api

import com.cloudservices.homework.HomeworkApplication
import com.cloudservices.homework.adapters.api.ProposalEndpoint
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.boot.test.autoconfigure.filter.TypeExcludeFilters
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@AutoConfigureDataMongo
@SpringBootTest
@ContextConfiguration(classes = [HomeworkApplication.class])
class ProposalAcceptanceTest extends Specification {

    private static final def PROPOSAL_SEGMENT = "/proposal"
    private static final def STATE_SEGMENT = PROPOSAL_SEGMENT + "/state"
    private static final def CONTENT_SEGMENT = PROPOSAL_SEGMENT + "/content"

    @Autowired
    MockMvc mvc

    @Autowired
    ProposalEndpoint proposalApiService

    def "Full positive way"() {
        when: "create new proposal"
            ResultActions resultActions = mvc.perform(post(PROPOSAL_SEGMENT)
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
            resultActions.andExpect(jsonPath("\$.state").value("CREATED"))
            resultActions.andExpect(jsonPath("\$.id").exists())

        when: "bump to VERIFIED status"
            def id = proposalApiService.findByNameOrState(null, null, PageRequest.of(0, 1))
                    .getContent()
                    .stream()
                    .map(a -> a.getId())
                    .findFirst()
                    .get()

            resultActions = mvc.perform(put(STATE_SEGMENT)
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
            resultActions.andExpect(jsonPath("\$.state").value("VERIFIED"))

        when: "modify content"
            resultActions = mvc.perform(put(CONTENT_SEGMENT)
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
            resultActions.andExpect(jsonPath("\$.content").value("updated my-content"))

        when: "bump to ACCEPTED status"
            resultActions = mvc.perform(put(STATE_SEGMENT)
                    .contentType(APPLICATION_JSON)
                    .content("""
                        {
                        "id": "$id",
                        "state": "ACCEPTED"
                        }
                        """)
            )

        then: "return OK"
            resultActions.andExpect(status().isOk())
            resultActions.andExpect(jsonPath("\$.state").value("ACCEPTED"))

        when: "bump to PUBLISHED status"
            resultActions = mvc.perform(put(STATE_SEGMENT)
                    .contentType(APPLICATION_JSON)
                    .content("""
                        {
                        "id": "$id",
                        "state": "PUBLISHED"
                        }
                        """)
            )

        then: "return OK with uuid in response body"
            resultActions.andExpect(status().isOk())
            resultActions.andExpect(jsonPath("\$.state").value("PUBLISHED"))
            resultActions.andExpect(jsonPath("\$.uuid").isNumber())
    }

}
