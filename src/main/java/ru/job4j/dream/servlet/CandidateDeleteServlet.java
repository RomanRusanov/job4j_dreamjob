package ru.job4j.dream.servlet;

import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 10.11.2020
 * email roman9628@gmail.com
 * The class describe servlet when user click delete candidate.
 */
public class CandidateDeleteServlet extends HttpServlet {
    /**
     * The preprocess.
     * @param req Request.
     * @param resp Response.
     * @throws javax.servlet.ServletException ServletException.
     * @throws java.io.IOException IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        PsqlStore store = (PsqlStore) PsqlStore.instOf();
        String pathPhotoToDelete = store.getPhotoPath(id);
        store.deleteCandidate(id);
        new File(pathPhotoToDelete).delete();
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }

}