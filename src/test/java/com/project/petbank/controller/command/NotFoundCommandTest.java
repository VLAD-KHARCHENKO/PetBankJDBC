package com.project.petbank.controller.command;

import com.project.petbank.controller.data.PageResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.http.HttpServletRequest;

import static com.project.petbank.view.PageUrlConstants.NOT_FOUND_PAGE;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class NotFoundCommandTest {
    @InjectMocks
    private NotFoundCommand instance;
    @Mock
    private HttpServletRequest request;

    @Test
    public void shouldReturnNotFoundPage() {
        PageResponse result = instance.execute(request);

        assertThat(result.getUrl()).isEqualTo(NOT_FOUND_PAGE);
        assertThat(result.isRedirect()).isFalse();
    }
}
