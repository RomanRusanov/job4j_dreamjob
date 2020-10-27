package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * @author Roman Rusanov
 * @version 0.1
 * @since 27.10.2020
 * email roman9628@gmail.com
 * The class describe model Resume.
 */
public class Resume {
    /**
     * The field contain title.
     */
    private final String title;
    /**
     * The collection contain all bio records.
     */
    private final List<String> bio;
    /**
     * The collection contain all skills records of Candidate.
     */
    private final List<String> hardSoftSkills;
    /**
     * The collection contain all records previous work place of Candidate.
     */
    private final List<String> previousWork;

    /**
     * The default constructor.
     * @param title String.
     */
    public Resume(String title) {
        this.title = title;
        this.bio = new ArrayList<>();
        this.hardSoftSkills = new ArrayList<>();
        this.previousWork = new ArrayList<>();
    }

    /**
     * The method add bio record to collection.
     * @param text String.
     */
    public void addBio(String text) {
        this.bio.add(text);
    }

    /**
     * The method remove bio record from collection.
     * @param text String.
     */
    public void removeBio(String text) {
        this.bio.remove(text);
    }

    /**
     * The method add skill record to collection.
     * @param text String.
     */
    public void addSkill(String text) {
        this.bio.add(text);
    }

    /**
     * The method remove skill from collection.
     * @param text String.
     */
    public void removeSkill(String text) {
        this.hardSoftSkills.remove(text);
    }

    /**
     * The method add record of previous work.
     * @param text String.
     */
    public void addPrevWork(String text) {
        this.previousWork.add(text);
    }

    /**
     * The method remove record of previous work.
     * @param text String.
     */
    public void removePrevWork(String text) {
        this.previousWork.remove(text);
    }

    /**
     * The getter for filed.
     * @return String title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * The method return list with all bios records.
     * @return List.
     */
    public List<String> getBio() {
        return new ArrayList<>(this.bio);
    }

    /**
     * The method return list with all skills records.
     * @return List.
     */
    public List<String> getHardSoftSkills() {
        return new ArrayList<>(this.hardSoftSkills);
    }

    /**
     * The method return list with all previous place work records.
     * @return List.
     */
    public List<String> getPreviousWork() {
        return new ArrayList<>(this.previousWork);
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
        Resume resume = (Resume) o;
        return title.equals(resume.title)
                && bio.equals(resume.bio)
                && hardSoftSkills.equals(resume.hardSoftSkills)
                && previousWork.equals(resume.previousWork);
    }

    /**
     * The method override hashcode.
     * @return int.
     */
    @Override
    public int hashCode() {
        return Objects.hash(title, bio, hardSoftSkills, previousWork);
    }

    /**
     * The method override toString.
     * @return String.
     */
    @Override
    public String toString() {
        return "Resume{"
                + "title='" + title + '\''
                + ", bio=" + bio
                + ", hardSoftSkills=" + hardSoftSkills
                + ", previousWork=" + previousWork
                + '}';
    }
}