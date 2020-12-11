package ru.job4j.dream.model;

import java.util.Objects;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 11.12.2020
 * email roman9628@gmail.com
 * The class describe model.
 */
public class City {
    /**
     * The field unique id.
     */
    private final int cityId;
    /**
     * The name of city.
     */
    private final String cityName;

    /**
     * The default constructor.
     * @param cityId id.
     * @param cityName name.
     */
    public City(int cityId, String cityName) {
        this.cityId = cityId;
        this.cityName = cityName;
    }

    /**
     * The getter for field.
     * @return id.
     */
    public int getCityId() {
        return cityId;
    }

    /**
     * The getter for field.
     * @return name.
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * The method override equals.
     * @param o Object to compare.
     * @return if fields equals return true, otherwise false.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return cityId == city.cityId && cityName.equals(city.cityName);
    }

    /**
     * The method override hashcode.
     * @return generate hash using fields.
     */
    @Override
    public int hashCode() {
        return Objects.hash(cityId, cityName);
    }
}