package by.kvach.lightBase.page.vacancy;

import by.kvach.lightBase.util.LogUtils;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.function.Supplier;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public abstract class VacancyAbstractModal<Context> {

    private Supplier<Context> callContext;

    private final LogUtils logger = LogUtils.getInstance();

    public VacancyAbstractModal(Supplier<Context> callContext) {
        this.callContext = callContext;
    }

    private SelenideElement

            //Buttons
            addInterviewerButton = $x("//button[contains(text(), 'Назначить')]"),
            calendarButton = $("div.calendar-btn"),
            confirmButton = $x("//div[contains (@class, 'Confirm')]"),

            //Fields
            nameVacancyField = $("#title"),
            projectField = $("input#project"),
            requiredTechnologiesField = $("textarea#requiredTechnologies"),
            desiredTechnologiesField = $("#desiredTechnologies"),
            englishLevelField = $("#englishLevel"),
            salaryField = $("#salary"),
            interviewerField = $("#interviewer"),
            additionallyField = $("#details"),

            //Selects
            positionSelect = $("#id"),
            levelSelect = $x("(//select[@class = 'form-control'])[1]"),
            hrSelect = $x("(//select[@class = 'form-control'])[2]"),

            dateTodayOnCalendar = $x("//td[contains (@class, 'today')]"),
            calendarBody = $("table.owl-calendar-day tbody");


    public Context fillInNameField(String vacancyName) {
        logger.logInfo("Fill in name field with Name - " + vacancyName);
        nameVacancyField.shouldBe(Condition.visible).setValue(vacancyName);
        return callContext.get();
    }

    public Context selectPosition(String position) {
        logger.logInfo("Select position with Name - " + position);
        positionSelect.click();
        positionSelect.$$x("../ul/li").filterBy(Condition.text("Далее")).first().click();
        positionSelect.$$x("../ul/li").filterBy(Condition.text(position)).first().click();
        return callContext.get();
    }

    public Context selectLevel(String level) {
        logger.logInfo("Select level with Name - " + level);
        levelSelect.shouldBe(Condition.visible).selectOption(level);
        return callContext.get();
    }

    public Context selectHR(String hr) {
        logger.logInfo("Select HR with Name - " + hr);
        hrSelect.shouldBe(Condition.visible).selectOption(hr);
        return callContext.get();
    }

    public Context fillInProjectField(String project) {
        logger.logInfo("Fill in project field with Name - " + project);
        projectField.shouldBe(Condition.visible).setValue(project);
        return callContext.get();
    }

    public Context fillInRequiredTechnologiesField(String technologies) {
        logger.logInfo("Fill in required technologies field with Name - " + technologies);
        requiredTechnologiesField.shouldBe(Condition.visible).setValue(technologies);
        return callContext.get();
    }

    public Context fillInDesiredTechnologiesField(String technologies) {
        logger.logInfo("Fill in desired technologies field with Name - " + technologies);
        desiredTechnologiesField.shouldBe(Condition.visible).setValue(technologies);
        return callContext.get();
    }

    public Context fillInEnglishLevelField(String englishLevel) {
        logger.logInfo("Fill in english level field with Name - " + englishLevel);
        englishLevelField.shouldBe(Condition.visible).setValue(englishLevel);
        return callContext.get();
    }

    public Context fillInSalaryField(String salary) {
        logger.logInfo("Fill in salary field with Name - " + salary);
        salaryField.shouldBe(Condition.visible).setValue(salary);
        return callContext.get();
    }

    public Context fillInInterviewerField(String interviewer) {
        logger.logInfo("Fill in interviewer field with Name - " + interviewer);
        interviewerField.shouldBe(Condition.visible).setValue(interviewer);
        return callContext.get();
    }

    public Context clickAddInterviewerButton(String interviewer) {
        logger.logInfo("Click Add Interviewer Button with Name - " + interviewer);
        addInterviewerButton.shouldBe(Condition.visible).setValue(interviewer);
        return callContext.get();
    }

    public Context fillInAdditionally(String text) {
        logger.logInfo("Fill additionally with Name - " + text);
        additionallyField.setValue(text);
        return callContext.get();
    }

    public Context fillInCalendarInputField(String data) {
        logger.logInfo("Select date in calendar - date is today");
        calendarButton.click();
        dateTodayOnCalendar.click();
        confirmButton.click();
        return callContext.get();
    }

    public Context makeHrNull() {
        logger.logInfo("Make HR field 'null' before deleting vacancy");
        hrSelect.shouldBe(Condition.visible).selectOption(0);
        return callContext.get();
    }

}
