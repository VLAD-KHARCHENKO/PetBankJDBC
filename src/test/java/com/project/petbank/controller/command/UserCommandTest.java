package com.project.petbank.controller.command;

import com.project.petbank.controller.data.PageResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static com.project.petbank.view.PageUrlConstants.USER_PAGE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class UserCommandTest {

    @InjectMocks
    private UserCommand instance;
    @Mock
    private HttpServletRequest request;

    @Test
    public void shouldReturnUserPage() {
        PageResponse result = instance.execute(request);

        assertThat(result.getUrl()).isEqualTo(USER_PAGE);
        assertThat(result.isRedirect()).isFalse();
    }
}
