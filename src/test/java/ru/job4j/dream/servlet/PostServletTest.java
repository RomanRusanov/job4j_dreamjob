package ru.job4j.dream.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.dream.store.MemStore;
import ru.job4j.dream.store.PsqlStore;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The class check behavior servlet with out worked container.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(PsqlStore.class)
public class PostServletTest {

    /**
     * The test check servlet with mock objects.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Test
    public void whenAddPostThenStoreIt() throws ServletException, IOException {
        MemStore validate = MemStore.instOf();
        PowerMockito.mockStatic(PsqlStore.class);
        Mockito.when(PsqlStore.instOf()).thenReturn(validate);
        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        when(req.getParameter("id")).thenReturn("1");
        when(req.getParameter("name")).thenReturn("Petr Arsentev");
        new PostServlet().doPost(req, resp);
        assertThat(validate.getAllPosts().iterator().next().getId(), is(1));
        assertThat(validate.getAllPosts().iterator().next().getName(), is("Petr Arsentev"));
    }
}
