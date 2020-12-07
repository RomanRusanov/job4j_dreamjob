package ru.job4j.dream.servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class GreetingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        String data = req.getParameter("text");
        System.out.println(data);
        String json = "{name : " + data + "}";
        JsonObject jsonObject = new Gson().fromJson(json, JsonObject.class);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.print(jsonObject);
        writer.flush();
    }
}
