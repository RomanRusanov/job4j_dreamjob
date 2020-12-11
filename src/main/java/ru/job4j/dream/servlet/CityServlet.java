package ru.job4j.dream.servlet;

import com.google.gson.Gson;
import ru.job4j.dream.model.City;
import ru.job4j.dream.store.PsqlStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 10.12.2020
 * email roman9628@gmail.com
 * The class describe servlet for select element on candidate edit page.
 */
public class CityServlet extends HttpServlet {

    /**
     * The preprocess.
     * @param req Request.
     * @param resp Response.
     * @throws javax.servlet.ServletException ServletException.
     * @throws java.io.IOException IOException.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        HashMap<Integer, String> allCity;
        PsqlStore store = (PsqlStore) PsqlStore.instOf();
        allCity = (HashMap<Integer, String>) store.getAllCity();
        Iterator<Map.Entry<Integer, String>> iterator = allCity.entrySet().iterator();
        City city;
        ArrayList<City> list = new ArrayList<>();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            city = new City(entry.getKey(), entry.getValue());
            list.add(city);
        }
        Gson jsonObject = new Gson().newBuilder()
                                    .setPrettyPrinting()
                                    .create();
        String json = jsonObject.toJson(list);
        System.out.println(json);
        PrintWriter writer = new PrintWriter(resp.getOutputStream(), false, StandardCharsets.UTF_8);
        writer.print(json);
        writer.flush();
    }

}