package vic.kata.steps.functional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

import java.text.MessageFormat;

import static org.hamcrest.Matchers.hasXPath;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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

    public ResultActions followRedirect(ResultActions page, MockHttpSession session) throws Exception {
        String redirectTo = page.andExpect(redirectedUrlPattern("/*")).andReturn().getResponse().getRedirectedUrl();
        page = getMvc().perform(get(redirectTo).session(session));
        return page;
    }

    private AssertionError errorWithPageContent(ResultActions page, Throwable e) throws Exception {
        String content = page.andReturn().getResponse().getContentAsString();
        String msg = MessageFormat.format("{0} at page: [\n{1}\n]",
                e.getMessage(), content);
        return new AssertionError(msg, e);
    }

    public void assertAtPage(ResultActions page, String toMatch) throws Exception {
        try {
            page.andExpect(status().isOk())
                .andExpect(content().node(hasXPath(toMatch)));
        } catch (Throwable e) {
            throw errorWithPageContent(page, e);
        }
    }

    public void assertNotAtPage(ResultActions page, String toMatch) throws Exception {
        try {
            page.andExpect(status().isOk())
                .andExpect(content().node(not(hasXPath(toMatch))));
        } catch (Throwable e) {
            throw errorWithPageContent(page, e);
        }
    }

    public void assertAtPageOnly(ResultActions page, boolean isTrue, String xpath, GameFunctionalSteps gameFunctionalSteps) throws Exception {
        if (isTrue) {
            assertAtPage(page, xpath);
        } else {
            assertNotAtPage(page, xpath);
        }
    }
}