package com.project.petbank.controller.command;

import com.project.petbank.controller.data.PageResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static com.project.petbank.view.PageUrlConstants.ADMIN_PAGE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class AdminCommandTest {
    @InjectMocks
    private AdminCommand instance;
    @Mock
    private HttpServletRequest request;

    @Test
    public void shouldReturnAdminPage() {
        PageResponse result = instance.execute(request);

        assertThat(result.getUrl()).isEqualTo(ADMIN_PAGE);
        assertThat(result.isRedirect()).isFalse();
    }
}
