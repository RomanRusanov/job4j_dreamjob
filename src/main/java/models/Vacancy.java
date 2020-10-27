package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 27.10.2020
 * email roman9628@gmail.com
 * The class describe model Vacancy for Candidate.
 */
public class Vacancy {
    /**
     * The field contain title.
     */
    private final String title;
    /**
     * The field contain collection with all requirement.
     */
    private final List<String> requirement;
    /**
     * The filed contain collection with all list what company provide for candidate.
     */
    private final List<String> companyProvides;

    /**
     * The default constructor.
     * @param title Title string.
     */
    public Vacancy(String title) {
        this.title = title;
        this.requirement = new ArrayList<>();
        this.companyProvides = new ArrayList<>();
    }

    /**
     * The method add to collection new requirement.
     * @param newRequirement String.
     */
    public void addRequirement(String newRequirement) {
        this.requirement.add(newRequirement);
    }

    /**
     * The method remove from collection requirement.
     * @param removeReq String.
     */
    public void removeRequirement(String removeReq) {
        this.requirement.remove(removeReq);
    }

    /**
     * The method return list with all requirements.
     * @return List.
     */
    public List<String> getRequirement() {
        return new ArrayList<>(this.requirement);
    }

    /**
     * The method add to collection what company provides.
     * @param text String.
     */
    public void addProvides(String text) {
        this.companyProvides.add(text);
    }

    /**
     * The method remove from collection what company provides.
     * @param text String.
     */
    public void removeProvides(String text) {
        this.companyProvides.remove(text);
    }

    /**
     * The method get all records what company provides to candidate.
     * @return List.
     */
    public List<String> getCompanyProvides() {
        return new ArrayList<>(this.companyProvides);
    }

    /**
     * The getter for filed.
     * @return String title.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * The method override equals.
     * @param o Object to compare.
     * @return Boolean.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return title.equals(vacancy.title)
                && requirement.equals(vacancy.requirement)
                && companyProvides.equals(vacancy.companyProvides);
    }

    /**
     * The method override hashcode.
     * @return int.
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, requirement, companyProvides);
    }

    /**
     * The method override toString.
     * @return String.
     */
    @Override
    public String toString() {
        return "Vacancy{"
                + "title='" + title + '\''
                + ", requirement=" + requirement
                + ", companyProvides=" + companyProvides
                + '}';
    }
}