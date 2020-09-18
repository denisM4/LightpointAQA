package by.kvach.lightBase.page.candidate;

import by.kvach.lightBase.util.LogUtils;
import by.kvach.lightBase.util.Util;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class CandidateDetailsPage extends AddContactModal<CandidateDetailsPage> {

    public CandidateDetailsPage() {
        super(CandidateDetailsPage::new);
    }

    private final LogUtils logger = LogUtils.getInstance();

    DeleteModalPage deleteModalPage = new DeleteModalPage();

    private final ElementsCollection
            contactCheckBoxList = $$("label.custom-radio"),
            deleteContactButton = $$x("//button[contains(., 'x')]"),
            communicationDateList = $$x("//ul[@class = 'nav nav-tabs']/li");


    //Candidate
    private final SelenideElement

            //Candidate grid
            searchField = $("#search"),
            candidateNameField = $x("//div[contains(@class, 'flex-container')]/input"),
            candidatePriorityButton = $("span.fa-star"),
            sendCandidateButton = $(".col-sm-12 span.fa-envelope"),
            uploadFileButton = $("#uploadBtn"),
            deleteCandidateButton = $x("//div[@class = 'row']//button[contains(text(),'Удалить')]"),
            addContactButton = $("app-add-contact button.btn-primary"),
            addCommunicationButton = $x("//button[text() = 'Добавить']"),
            deleteCommunicationButton = $x("//button[text() = 'Удалить']"),

            //Information grid
            vacancySelect = $x("//div[contains(text(),'Вакансия')]/..//select"),
            positionSelect = $x("//div[@data-toggle='dropdown']"),
            levelSelect = $x("//div[text()='Уровень']/..//select"),
            salaryField = $x("//div[text()='Зарплата']/..//input"),
            experienceSelect = $x("//div[text()='Опыт']/..//select"),
            hrSelect = $x("//div[text()='HR']/..//select"),
            englishLevelSelect = $x("//div[text()='Уровень английского']/..//select");


    /**
     * Inner Class Delete Modal
     */
    private class DeleteModalPage {

        private final SelenideElement
                deleteButton = $x("//app-confirm-delete-candidate-modal//button[contains(text(),'Удалить')]");

        public CandidateDetailsPage confirmDelete() {
            logger.logInfo("Click 'Confirm Delete'");
            deleteButton.should(Condition.enabled).click();
            return new CandidateDetailsPage();
        }
    }

    /**
     * This method allows to change candidate Name
     */
    public CandidateDetailsPage renameCandidate(String newName) {
        logger.logInfo("Rename candidate. New name - " + newName);
        candidateNameField.setValue(newName).pressEnter();
        return new CandidateDetailsPage();
    }

    /**
     * This method allows to delete candidate
     */
    public CandidatesTablePage deleteCandidate() {
        logger.logInfo("Delete candidate");
        deleteCandidateButton.click();
        deleteModalPage.confirmDelete();
        return new CandidatesTablePage();
    }

    /**
     * This method allows to click Add contact Button
     */
    public CandidateDetailsPage clickAddNewContact() {
        logger.logInfo("Click 'Add new Contact'");
        addContactButton.click();
        return this;
    }

    /**
     * This method allows to click "priority" button
     */
    public CandidateDetailsPage clickPriorityButton() {
        logger.logInfo("Click 'Priority button'");
        candidatePriorityButton.click();
        return this;
    }

    /**
     * This method allows to select "priority" button
     */
    public CandidateDetailsPage selectPriority(int contactNumber) {
        logger.logInfo("Select priority by Number - " + contactNumber);
        System.out.println(contactCheckBoxList.size());
        contactCheckBoxList.get(contactNumber).click();
        return this;
    }

    /**
     * This method allows to click "delete contact" button
     */
    public CandidateDetailsPage deleteContact(int contactNumber) {
        logger.logInfo("Delete contact by Number - " + contactNumber);
        deleteContactButton.get(contactNumber).click();
        return this;
    }

    /**
     * This method allows to Add Communication Button
     */
    public CandidateDetailsPage clickAddCommunicationButton() {
        logger.logInfo("Click 'Add Communication Button'");
        addCommunicationButton.click();
        return this;
    }

    /**
     * This method allows to delete Candidate Name
     */
    public CandidateDetailsPage clickDeleteCommunicationButton() {
        logger.logInfo("Click delete Communication Button");
        deleteCommunicationButton.click();
        return this;
    }

    /**
     * This method allows to select vacancy
     */
    public CandidateDetailsPage selectVacancy(String vacancy) {
        logger.logInfo("Select vacancy with name - " + vacancy);
        vacancySelect.selectOption(vacancy);
        return this;
    }

    /**
     * This method allows to select position
     */
    public CandidateDetailsPage selectPosition(String position) {
        logger.logInfo("Select vacancy with name - " + position);
        positionSelect.click();
        positionSelect.$$x("../ul/li").filterBy(Condition.text("Далее")).first().click();
        Util.waitDynamicDisappears();
        positionSelect.$$x("../ul/li").filterBy(Condition.text(position)).first().shouldBe(Condition.visible).click();
        return this;
    }

    /**
     * This method allows to select level
     */
    public CandidateDetailsPage selectLevel(String level) {
        logger.logInfo("Select level with name - " + level);
        levelSelect.selectOption(level);
        return this;
    }

    /**
     * This method allows to fill in salary
     */
    public CandidateDetailsPage fillInSalaryField(String salary) {
        logger.logInfo("Fill in salary  - " + salary);
        salaryField.setValue(salary);
        return this;
    }

    /**
     * This method allows to select Experience
     */
    public CandidateDetailsPage selectExperience(String experience) {
        logger.logInfo("Select experience  - " + experience);
        experienceSelect.selectOption(experience);
        return this;
    }

    /**
     * This method allows to select HR
     */
    public CandidateDetailsPage selectHr(String hr) {
        logger.logInfo("Select HR with name  - " + hr);
        hrSelect.selectOption(hr);
        return this;
    }

    /**
     * This method allows to select in English level
     */
    public CandidateDetailsPage selectEnglishLevel(String englishLevel) {
        logger.logInfo("Select English level with name - " + englishLevel);
        englishLevelSelect.selectOption(englishLevel);
        return this;
    }


}
