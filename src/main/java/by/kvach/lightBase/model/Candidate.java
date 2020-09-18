package by.kvach.lightBase.model;

import java.util.ArrayList;
import java.util.List;

public class Candidate {

    private String name,
            vacancy,
            position,
            positionLevel,
            experience,
            hrName,
            typeInvite,
            valueInvite,
            comment,
            communicationLevel,
            englishLevel;

    private List<Contact> contacts = new ArrayList<>();

    private int salary;

    /**
     * Constructor for adding full candidate
     */
    public Candidate(String name, String vacancy, String position, String positionLevel, int salary, String experience, String hrName, String englishLevel, Contact contact, String typeInvite, String valueInvite, String comment, String communicationLevel) {
        this.name = name;
        this.vacancy = vacancy;
        this.position = position;
        this.positionLevel = positionLevel;
        this.salary = salary;
        this.experience = experience;
        this.hrName = hrName;
        this.englishLevel = englishLevel;
        this.contacts.add(contact);
        this.typeInvite = typeInvite;
        this.valueInvite = valueInvite;
        this.comment = comment;
        this.communicationLevel = communicationLevel;
    }

    /**
     * Constructor for quick adding candidate
     *
     * @param contacts Name, Contact
     */
    public Candidate(String name, List<Contact> contacts) {
        this.name = name;
        this.contacts = contacts;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(String englishLevel) {
        this.englishLevel = englishLevel;
    }

    public String getPositionLevel() {
        return positionLevel;
    }

    public void setPositionLevel(String positionLevel) {
        this.positionLevel = positionLevel;
    }

    public String getFirstName() {
        return name;
    }

    public void setFirstName(String firstName) {
        this.name = firstName;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Contact contacts) {
        this.contacts.add(contacts);
    }

    public String getTypeInvite() {
        return typeInvite;
    }

    public void setTypeInvite(String typeInvite) {
        this.typeInvite = typeInvite;
    }

    public String getValueInvite() {
        return valueInvite;
    }

    public void setValueInvite(String valueInvite) {
        this.valueInvite = valueInvite;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCommunicationLevel() {
        return communicationLevel;
    }

    public void setCommunicationLevel(String communicationLevel) {
        this.communicationLevel = communicationLevel;
    }

    @Override
    public String toString() {
        return "Candidate{" +
                "name='" + name + '\'' +
                ", vacancy='" + vacancy + '\'' +
                ", position='" + position + '\'' +
                ", positionLevel='" + positionLevel + '\'' +
                ", experience='" + experience + '\'' +
                ", hrName='" + hrName + '\'' +
                ", typeInvite='" + typeInvite + '\'' +
                ", valueInvite='" + valueInvite + '\'' +
                ", comment='" + comment + '\'' +
                ", communicationLevel='" + communicationLevel + '\'' +
                ", englishLevel='" + englishLevel + '\'' +
                ", contacts=" + contacts +
                ", salary=" + salary +
                '}';
    }
}
