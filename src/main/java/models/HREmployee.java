package models;

import java.util.Objects;

/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 27.10.2020
 * email roman9628@gmail.com
 * The class describe model HR employee.
 */
public class HREmployee {
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
     * @param firstName First name Sting.
     * @param lastName Last name String.
     */
    public HREmployee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * The getter for filed.
     * @return String.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * The getter for filed.
     * @return String.
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
        HREmployee that = (HREmployee) o;
        return firstName.equals(that.firstName)
                && lastName.equals(that.lastName);
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
        return "HREmployee{"
                + "firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + '}';
    }
}