package by.kvach.lightBase.page.candidate;

import by.kvach.lightBase.page.vacancy.VacancyPage;
import by.kvach.lightBase.util.LogUtils;
import by.kvach.lightBase.util.Util;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class CandidatesTablePage {

    private final LogUtils logger = LogUtils.getInstance();

    private final SelenideElement
            //Buttons
            addButton = $x("//button[contains(., 'Добавить')]"),
            vacancyMenu = $x("//a[@href='/vacancies']"),
            candidatesMenu = $x("//a[@href='/candidates']"),

            //Fields
            searchField = $("#search"),

            firstCandidateName = $x("//tr[@aria-rowindex= '3']//a"),
            notFoundCandidateMessage = $x("//td[contains(., 'Кандидатов нет')]");


    public AddCandidatePage addButtonClick() {
        logger.logInfo("Click button with name 'Add'");
        addButton.click();
        return new AddCandidatePage();
    }

    public CandidatesTablePage findCandidateByName(String value) {
        logger.logInfo("Fill in search field by name - " + value + " and press Enter");
        searchField.setValue(value).pressEnter();
        Util.waitDynamicDisappears();
        return new CandidatesTablePage();
    }

    public CandidateDetailsPage clickFirstCandidate() {
        logger.logInfo("Click first found candidate in table");
        firstCandidateName.should(visible).click();
        return new CandidateDetailsPage();
    }

    public boolean candidateNotFound() {
        logger.logInfo("Candidate notification 'Candidate not found'");
        return notFoundCandidateMessage.isDisplayed();
    }

    public VacancyPage clickVacancyMenu() {
        logger.logInfo("Click vacancy menu from left side menu");
        vacancyMenu.shouldBe(Condition.enabled).click();
        return new VacancyPage();
    }

    public VacancyPage clickCandidatesMenu() {
        logger.logInfo("Click candidates menu from left side menu");
        candidatesMenu.shouldBe(Condition.enabled).click();
        return new VacancyPage();
    }

}

