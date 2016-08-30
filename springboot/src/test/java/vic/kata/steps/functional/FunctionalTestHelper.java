package vic.kata.steps.functional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import static org.hamcrest.Matchers.hasXPath;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Scope("singleton")
public class FunctionalTestHelper {
    @Autowired
    WebApplicationContext context;
    MockMvc mvc;

    @PostConstruct
    public void init() {
        mvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    public MockMvc getMvc() {
        return mvc;
    }

    public void assertAtPage(ResultActions page, String toMatch) throws Exception {
        page.andExpect(status().isOk())
            .andExpect(content().node(hasXPath(toMatch)));
    }

    public void assertNotAtPage(ResultActions page, String toMatch) throws Exception {
        page.andExpect(status().isOk())
            .andExpect(content().node(not(hasXPath(toMatch))));
    }

    void assertAtPageOnly(ResultActions page, boolean isTrue, String xpath, GameFunctionalSteps gameFunctionalSteps) throws Exception {
        if (isTrue) {
            assertAtPage(page, xpath);
        } else {
            assertNotAtPage(page, xpath);
        }
    }
}