package by.kvach.lightBase.page.candidate;

import by.kvach.lightBase.util.LogUtils;
import by.kvach.lightBase.util.Util;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.testng.Assert;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class AddCandidatePage extends AddContactModal<AddCandidatePage> {

    private final LogUtils logger = LogUtils.getInstance();

    public AddCandidatePage() {
        super(AddCandidatePage::new);
    }

    private final SelenideElement

            //Fields
            nameField = $("#name"),
            salaryField = $("#salary"),
            experienceField = $x("//*[@for='yearsOfExperience']/..//select"),
            commentField = $("textarea"),
            notification = $(".sn-title"),

            //Selects
            vacanciesDdSelect = $x("//*[@for='vacancyRequests']/..//select"),
            positionDd = $x("//*[@for='positions']/..//div[@data-toggle='dropdown']"),
            levelDdSelect = $x("//*[@for='levels']/..//select"),
            communicationLevelDd = $x("//div[contains(@class,'flex-container')]//select"),
            englishLevelSelect = $x("//*[@for='englishLevels']/..//select"),
            hrSelect = $x("//*[@for='hrs']/..//select"),

            //Buttons
            addContactButton = $x("//button[@class = 'btn btn-primary']"),
            addSvButton = $("#uploadBtn"),
            inviteSourceTypeButton = $x("//select[contains(.,'Инвайт')]"),
            inviteSourceValueButton = $x("//select[contains(.,'Телефон')]"),
            createButton = $x("//button[@class = 'btn btn-primary btn-create']");


    /**
     * This method allows to Set Candidate Name
     */
    public AddCandidatePage fillInNameField(String name) {
        nameField.setValue(name);
        Assert.assertNotNull(nameField.getValue());
        logger.logInfo("Fill in name candidate - " + name);
        return this;
    }

    /**
     * This method selects default vacancy
     */
    public AddCandidatePage selectDefaultVacancy() {
        logger.logInfo("Fill in default vacancy - Need QA");
        vacanciesDdSelect.click();
        vacanciesDdSelect.selectOption(1);
        Assert.assertNotNull(vacanciesDdSelect.getValue());
        return this;
    }

    /**
     * This method selects vacancy By Candidate name
     */
    public AddCandidatePage selectVacancyByName(String name) {
        logger.logInfo("Select in vacancy - " + name);
        vacanciesDdSelect.selectOption(name);
        Assert.assertNotNull(vacanciesDdSelect.getValue());
        return this;
    }

    /**
     * This method selects default position
     */
    public AddCandidatePage selectDefaultPosition() {
        positionDd.click();
        positionDd.$$x("../ul/li").filterBy(Condition.text("Далее")).first().click();
        positionDd.$$x("../ul/li").last().shouldBe(Condition.visible).click();
        return this;
    }

    /**
     * This method selects position By Position Name
     */
    public AddCandidatePage selectPositionByName(String name) {
        logger.logInfo("Select Position with Name - " + name);
        positionDd.click();
        positionDd.$$x("../ul/li").filterBy(Condition.text("Далее")).first().click();
        positionDd.$$x("../ul/li").filterBy(Condition.text(name)).first().click();
        return this;
    }

    /**
     * This method selects default Level
     */
    public AddCandidatePage selectDefaultLevel() {
        logger.logInfo("Select Default Level - " + levelDdSelect.getValue());
        levelDdSelect.click();
        levelDdSelect.selectOption(0);
        Assert.assertNotNull(levelDdSelect.getText());
        return this;
    }

    /**
     * This method selects Level by name
     */
    public AddCandidatePage selectLevel(String level) {
        logger.logInfo("Select Level - " + levelDdSelect.getValue());
        levelDdSelect.click();
        levelDdSelect.selectOption(level);
        Assert.assertNotNull(levelDdSelect.getText());
        return this;
    }

    /**
     * This method allows to fill fill in salary
     */
    public AddCandidatePage setSalary(int number) {
        logger.logInfo("Fill In Salary - " + salaryField.getValue());
        salaryField.click();
        salaryField.setValue(String.valueOf(number));
        Assert.assertNotNull(salaryField.getText());
        return this;
    }

    /**
     * This method selects experience field by number
     */
    public AddCandidatePage selectExperienceFieldByNumber(int number) {
        experienceField.selectOption(number);
        return this;
    }

    /**
     * This method selects experience field by value
     */
    public AddCandidatePage selectExperienceFieldByValue(String value) {
        logger.logInfo("Fill In Experience with Name - " + value);
        experienceField.selectOption(value);
        Assert.assertNotNull(experienceField.getText());
        return this;
    }

    /**
     * This method selects HR name by value
     */
    public AddCandidatePage selectHrByValue(String value) {
        logger.logInfo("Select HR with Name - " + value);
        hrSelect.selectOption(value);
        Assert.assertNotNull(hrSelect.getText());
        return this;
    }

    /**
     * This method selects HR name by number
     */
    public AddCandidatePage selectHrByNumber(int value) {
        logger.logInfo("Select HR with default Name ");
        hrSelect.selectOption(value);
        return this;
    }

    /**
     * This method selects English level by value
     */
    public AddCandidatePage selectEnglishByValue(String value) {
        logger.logInfo("Select English Level with Value - " + value);
        englishLevelSelect.selectOption(value);
        return this;
    }

    /**
     * This method selects English level by number
     */
    public AddCandidatePage selectEnglishByNumber(int value) {
        logger.logInfo("Select English Level with Number - " + value);
        englishLevelSelect.selectOption(value);
        return this;
    }

    /**
     * This method allows to click 'Add new contact' button
     */
    public AddCandidatePage clickAddNewContact() {
        logger.logInfo("Click button 'Add new Contact'");
        addContactButton.click();
        return this;
    }

    /**
     * This method allows to upload file
     */
    public AddCandidatePage upLoadCvFile() {
        logger.logInfo("File has uploaded");
        addSvButton.uploadFile(new File("src/main/resources/testFile.txt"));
        return this;
    }

    /**
     * This method selects type invite by number
     */
    public AddCandidatePage selectTypeInviteByNumber(int number) {
        logger.logInfo("Select type invite by number - " + number);
        inviteSourceTypeButton.selectOption(number);
        return this;
    }

    /**
     * This method selects type invite by value
     */
    public AddCandidatePage selectTypeInviteByValue(String value) {
        logger.logInfo("Select select type invite by Value - " + value);
        inviteSourceTypeButton.selectOption(value);
        return this;
    }

    /**
     * This method selects value invite by number
     */
    public AddCandidatePage selectValueInviteByNumber(int number) {
        logger.logInfo("Select value invite by Number - " + number);
        inviteSourceValueButton.selectOption(number);
        return this;
    }

    /**
     * This method selects invite value by value
     */
    public AddCandidatePage selectValueInviteByValue(String value) {
        logger.logInfo("Select value invite by Value - " + value);
        inviteSourceValueButton.selectOption(value);
        return this;
    }

    /**
     * This method allows to fill in comment field by value
     */
    public AddCandidatePage fillInCommentField(String value) {
        logger.logInfo("Fill in comment field with Value - " + value);
        commentField.setValue(value);
        return this;
    }

    /**
     * This method allows to fill in comment field by number
     */
    public AddCandidatePage selectCommunicationLevelByNumber(int number) {
        logger.logInfo("Select communication level with Number - " + number);
        communicationLevelDd.selectOption(number);
        return this;
    }

    /**
     * This method selects communication level by value
     */
    public AddCandidatePage selectCommunicationLevelByValue(String value) {
        logger.logInfo("Select communication level with Value - " + value);
        communicationLevelDd.selectOption(value);
        return this;
    }

    /**
     * This method allows to click 'Create candidate button' and check create candidate notification
     */
    public AddCandidatePage createCandidateButton() {
        logger.logInfo("Click 'Add Candidate' / notification has appeared");
        createButton.shouldBe(Condition.enabled).click();
        notification.shouldBe(Condition.appear);
        return this;
    }

    /**
     * Getter for 'Create candidate button'
     */
    public SelenideElement getCreateButton() {
        Util.waitDynamicDisappears();
        return createButton;
    }

    /**
     * Getter for notification Creating candidate
     */
    public SelenideElement getNotification() {
        Util.waitDynamicDisappears();
        return notification;
    }

}
