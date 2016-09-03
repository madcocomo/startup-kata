package vic.kata.steps.functional;

import junit.framework.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.validation.constraints.AssertFalse;

import static org.hamcrest.Matchers.hasXPath;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

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
        try {
            page.andExpect(status().isOk())
                .andExpect(content().node(hasXPath(toMatch)));
        } catch (AssertionError e) {
            page.andExpect(content().string(e.getMessage()));
        }
    }

    public void assertNotAtPage(ResultActions page, String toMatch) throws Exception {
        try {
            page.andExpect(status().isOk())
                .andExpect(content().node(not(hasXPath(toMatch))));
        } catch (AssertionError e) {
            page.andExpect(content().string(e.getMessage()));
        }
    }

    void assertAtPageOnly(ResultActions page, boolean isTrue, String xpath, GameFunctionalSteps gameFunctionalSteps) throws Exception {
        if (isTrue) {
            assertAtPage(page, xpath);
        } else {
            assertNotAtPage(page, xpath);
        }
    }
}