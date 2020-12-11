package ru.job4j.dream.servlet;

import ru.job4j.dream.model.Candidate;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 03.11.2020
 * email roman9628@gmail.com
 * The class describe Candidate Edit Servlet.
 */
public class CandidateEditServlet extends HttpServlet{
    /**
     * The preprocess.
     * @param req Request.
     * @param resp Response.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Candidate candidate = new Candidate(0, "", 0);
        if (req.getParameter("id") != null) {
            candidate = PsqlStore.instOf().findCandidateById(
                    Integer.parseInt(req.getParameter("id")));
            req.setAttribute("candidateById", candidate);
        }
        PsqlStore store = (PsqlStore) PsqlStore.instOf();
        int cityId = candidate.getCityId();
        req.setAttribute("cityName", store.getCityNameById(cityId));
        req.setAttribute("user", req.getSession().getAttribute("user"));
        req.getRequestDispatcher("/candidate/edit.jsp").forward(req, resp);
    }
    /**
     * The postprocess.
     * @param req Request.
     * @param resp Response.
     * @throws ServletException ServletException.
     * @throws IOException IOException.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        PsqlStore.instOf().saveCandidate(
                new Candidate(
                        Integer.valueOf(req.getParameter("id")),
                        req.getParameter("name"),
                        Integer.parseInt(req.getParameter("city"))));
        resp.sendRedirect(req.getContextPath() + "/candidates.do");
    }
}