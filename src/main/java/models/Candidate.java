package models;

import java.util.Objects;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 27.10.2020
 * email roman9628@gmail.com
 * The class describe model Candidate instance.
 */
public class Candidate {
    /**
     * The filed with first name.
     */
    private final String firstName;
    /**
     * The filed with last name.
     */
    private final String lastName;

    /**
     * The default constructor.
     * @param firstName First name.
     * @param lastName Last name.
     */
    public Candidate(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * The getter method.
     * @return String
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * The getter method.
     * @return String
     */
    public String getLastName() {
        return lastName;
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
        Candidate candidate = (Candidate) o;
        return firstName.equals(candidate.firstName)
                && lastName.equals(candidate.lastName);
    }

    /**
     * The method override hashcode.
     * @return int.
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    /**
     * The method override toString.
     * @return String.
     */
    @Override
    public String toString() {
        return "Сandidate{"
                + "firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + '}';
    }
}