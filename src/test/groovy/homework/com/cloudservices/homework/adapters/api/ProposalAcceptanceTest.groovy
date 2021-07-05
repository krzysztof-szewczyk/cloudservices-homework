package homework.com.cloudservices.homework.adapters.api

import com.cloudservices.homework.HomeworkApplication
import com.jayway.jsonpath.JsonPath
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultActions
import spock.lang.Specification

import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
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

        when: "find by status and name"
            resultActions = mvc.perform(get(PROPOSAL_SEGMENT)
                    .param("name", "y-nam")
                    .param("state", "CREATED")
            )


        then: "return requested proposal page and get proposal id"
            resultActions.andExpect(status().isOk())
            def id = JsonPath.parse(resultActions.andReturn()
                    .getResponse()
                    .getContentAsString()
            ).read("\$.content[0].id")

        when: "bump to VERIFIED status"
            resultActions = mvc.perform(put(STATE_SEGMENT)
                    .contentType(APPLICATION_JSON)
                    .content("""
                        {
                        "id": "$id",
                        "state": "VERIFIED"
                        }
                        """)
            )

        then: "return OK with new state of proposal"
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

        then: "return OK with new content of proposal"
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

        then: "return OK with new state of proposal"
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
