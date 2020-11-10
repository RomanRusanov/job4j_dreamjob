package ru.job4j.dream.servlet;

import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 10.11.2020
 * email roman9628@gmail.com
 * The class describe servlet make visible photo in candidate list.
 */
public class ViewPhotoServlet extends HttpServlet {
    /**
     * The preprocess.
     * @param req Request.
     * @param resp Response.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String photoId = req.getParameter("photoId");
        PsqlStore store = (PsqlStore) PsqlStore.instOf();
        String name = store.getPhotoPath(Integer.parseInt(photoId));
        name = name.replace("images\\phote_id\\", "");
        req.getRequestDispatcher("/download.do?name=" + name).forward(req, resp);
    }
}
