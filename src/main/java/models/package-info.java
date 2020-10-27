/**
 * Candidate.java The class describe model Candidate instance.
 *  public Candidate(String firstName, String lastName) The default constructor.
 *  public void addResume(Resume resume) The method add resume to collection.
 *  public void removeResume(Resume resume) The method remove resume from collection.
 *  public String getFirstName() The getter method.
 *  public String getLastName() The getter method.
 *  public boolean equals(Object o) The method override equals.
 *  public int hashCode() The method override hashcode.
 *  public String toString() The method override toString.
 *
 * Resume.java The class describe model Resume.
 *  public Resume(String title) The default constructor.
 *  public void addBio(String text) The method add bio record to collection.
 *  public void removeBio(String text) The method remove bio record from collection.
 *  public void addSkill(String text) The method add skill record to collection.
 *  public void removeSkill(String text) The method remove skill from collection.
 *  public void addPrevWork(String text) The method add record of previous work.
 *  public void removePrevWork(String text) The method remove record of previous work.
 *  public String getTitle() The getter for filed.
 *  public List<String> getBio() The method return list with all bios records.
 *  public List<String> getHardSoftSkills() The method return list with all skills records.
 *  public List<String> getPreviousWork() The method return list with all previous place work records.
 *  public boolean equals(Object o) The method override equals.
 *  public int hashCode() The method override hashcode.
 *  public String toString() The method override toString.
 *
 * HREmployee.java The class describe model HR employee.
 *  public HREmployee(String firstName, String lastName) The default constructor.
 *  public String getFirstName() The getter for filed.
 *  public String getLastName() The getter for filed.
 *  public Map<String, Vacancy> getAllVacancy() The method get collection with all vacancy.
 *  public void addVacancy(Vacancy vacancy) The method add vacancy to collection.
 *  public void removeVacancy(Vacancy vacancy) The method remove vacancy from collection.
 *  public boolean equals(Object o) The method override equals.
 *  public int hashCode() The method override hashcode.
 *  public String toString() The method override toString.
 *
 * Vacancy.java The class describe model Vacancy for Candidate.
 *  public Vacancy(String title) The default constructor.
 *  public void addRequirement(String newRequirement) The method add to collection new requirement.
 *  public void removeRequirement(String removeReq) The method remove from collection requirement.
 *  public List<String> getRequirement() The method return list with all requirements.
 *  public void addProvides(String text) The method add to collection what company provides.
 *  public void removeProvides(String text) The method remove from collection what company provides.
 *  public List<String> getCompanyProvides() The method get all records what company provides to candidate.
 *  public String getTitle() The getter for filed.
 *  public boolean equals(Object o) The method override equals.
 *  public int hashCode() The method override hashcode.
 *  public String toString() The method override toString.
 */
package models;