package models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.Map;
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
     * The instance with logger.
     */
    private static final Logger LOG = LoggerFactory.getLogger(HREmployee.class.getName());
    /**
     * The filed with first name.
     */
    private final String firstName;
    /**
     * The filed with last name.
     */
    private final String lastName;
    /**
     * The filed contain collection with all vacancy this HR employee.
     */
    private final Map<String, Vacancy> allVacancy = new HashMap<>();

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
     * The method get collection with all vacancy.
     * @return HashMap.
     */
    public Map<String, Vacancy> getAllVacancy() {
        return new HashMap<>(this.allVacancy);
    }

    /**
     * The method add vacancy to collection.
     * @param vacancy Vacancy.
     */
    public void addVacancy(Vacancy vacancy) {
        this.allVacancy.put(vacancy.getTitle(), vacancy);
    }

    /**
     * The method remove vacancy from collection.
     * @param vacancy Vacancy.
     */
    public void removeVacancy(Vacancy vacancy)  {
        this.allVacancy.remove(vacancy.getTitle());
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
                && lastName.equals(that.lastName)
                && allVacancy.equals(that.allVacancy);
    }

    /**
     * The method override hashcode.
     * @return int.
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, allVacancy);
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
                + ", allVacancy=" + allVacancy
                + '}';
    }
}