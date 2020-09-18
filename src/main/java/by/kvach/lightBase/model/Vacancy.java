package by.kvach.lightBase.model;

public class Vacancy {

    private String
            name,
            position,
            hr,
            project,
            salary,
            requiredTechnologies,
            desirableTechnologies,
            interviewer,
            additionally,
            closedDate,
            level,
            englishLevel;

    public Vacancy(String name, String position, String hr, String project, String salary, String requiredTechnologies,
                   String desirableTechnologies, String interviewer, String additionally, String closedDate,
                   String level, String englishLevel) {
        this.name = name;
        this.position = position;
        this.hr = hr;
        this.project = project;
        this.salary = salary;
        this.requiredTechnologies = requiredTechnologies;
        this.desirableTechnologies = desirableTechnologies;
        this.interviewer = interviewer;
        this.additionally = additionally;
        this.closedDate = closedDate;
        this.level = level;
        this.englishLevel = englishLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getRequiredTechnologies() {
        return requiredTechnologies;
    }

    public void setRequiredTechnologies(String requiredTechnologies) {
        this.requiredTechnologies = requiredTechnologies;
    }

    public String getDesirableTechnologies() {
        return desirableTechnologies;
    }

    public void setDesirableTechnologies(String desirableTechnologies) {
        this.desirableTechnologies = desirableTechnologies;
    }

    public String getInterviewer() {
        return interviewer;
    }

    public void setInterviewer(String interviewer) {
        this.interviewer = interviewer;
    }

    public String getAdditionally() {
        return additionally;
    }

    public void setAdditionally(String additionally) {
        this.additionally = additionally;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(String englishLevel) {
        this.englishLevel = englishLevel;
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", hr='" + hr + '\'' +
                ", project='" + project + '\'' +
                ", salary='" + salary + '\'' +
                ", requiredTechnologies='" + requiredTechnologies + '\'' +
                ", desirableTechnologies='" + desirableTechnologies + '\'' +
                ", interviewer='" + interviewer + '\'' +
                ", additionally='" + additionally + '\'' +
                ", closedDate='" + closedDate + '\'' +
                ", level='" + level + '\'' +
                ", englishLevel='" + englishLevel + '\'' +
                '}';
    }
}
