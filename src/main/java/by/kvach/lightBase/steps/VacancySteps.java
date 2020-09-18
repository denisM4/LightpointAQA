package by.kvach.lightBase.steps;

import by.kvach.lightBase.model.Vacancy;
import by.kvach.lightBase.page.LoginPage;
import by.kvach.lightBase.page.candidate.CandidatesTablePage;
import by.kvach.lightBase.page.vacancy.VacancyPage;
import by.kvach.lightBase.service.dbangine.DBConnect;
import by.kvach.lightBase.service.dbangine.Query;
import by.kvach.lightBase.util.LogUtils;
import by.kvach.lightBase.util.Util;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Screenshots;
import com.codeborne.selenide.SelenideElement;

import static by.kvach.lightBase.util.Util.waitDynamicDisappears;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.assertEquals;

public class VacancySteps {

    private Vacancy vacancyForAdd;
    private Vacancy vacancyForUpdate;
    private Vacancy vacancyForDelete;
    public static SelenideElement elementForFind;


    private final LogUtils logger = LogUtils.getInstance();

    public void addVacancy() {

        vacancyForAdd = Util.createRandomVacancy();

        new CandidatesTablePage()
                .clickVacancyMenu()
                .clickAllVacancyCheckbox()
                .clickClosedVacancyCheckbox()
                .clickInWorkVacancyCheckbox()
                .clickAddVacancy()
                .fillInNameField(vacancyForAdd.getName())
                .selectPosition(vacancyForAdd.getPosition())
                .selectLevel(vacancyForAdd.getLevel())
                .selectHR(vacancyForAdd.getHr())
                .fillInProjectField(vacancyForAdd.getProject())
                .fillInRequiredTechnologiesField(vacancyForAdd.getRequiredTechnologies())
                .fillInDesiredTechnologiesField(vacancyForAdd.getDesirableTechnologies())
                .fillInEnglishLevelField(vacancyForAdd.getEnglishLevel())
                .fillInSalaryField(vacancyForAdd.getSalary())
                .fillInInterviewerField(vacancyForAdd.getInterviewer())
                .fillInCalendarInputField(vacancyForAdd.getClosedDate())
                .fillInAdditionally(vacancyForAdd.getAdditionally())
                .clickaAddVacancyButton();

        waitDynamicDisappears();
        logger.logInfo(Screenshots.takeScreenShotAsFile(), "Creating new vacancy with name - " + vacancyForAdd);

    }

    public void updateVacancy() {

        vacancyForAdd = Util.createRandomVacancy();
        ApiSteps apiSteps = new ApiSteps();
        apiSteps.postAddNewVacancyByAPI(vacancyForAdd);

        vacancyForUpdate = Util.createRandomVacancy();

        new CandidatesTablePage()
                .clickVacancyMenu()
                .clickAllVacancyCheckbox()
                .findVacancyByName(vacancyForAdd.getName())
                .clickNameAfterFinding()
                .fillInNameField(vacancyForUpdate.getName())
                .selectPosition(vacancyForUpdate.getPosition())
                .selectLevel(vacancyForUpdate.getLevel())
                .selectHR(vacancyForUpdate.getHr())
                .fillInProjectField(vacancyForUpdate.getProject())
                .fillInRequiredTechnologiesField(vacancyForUpdate.getRequiredTechnologies())
                .fillInDesiredTechnologiesField(vacancyForUpdate.getDesirableTechnologies())
                .fillInEnglishLevelField(vacancyForUpdate.getEnglishLevel())
                .fillInSalaryField(vacancyForUpdate.getSalary())
                .fillInInterviewerField(vacancyForUpdate.getInterviewer())
                .fillInCalendarInputField(vacancyForUpdate.getClosedDate())
                .fillInAdditionally(vacancyForUpdate.getAdditionally())
                .clickSaveBtn();

        waitDynamicDisappears();
        logger.logInfo(Screenshots.takeScreenShotAsFile(), "Updating vacancy. New name - " + vacancyForUpdate);

    }

    public void deleteVacancy() {

        vacancyForDelete = Util.createRandomVacancy();
        ApiSteps apiSteps = new ApiSteps();
        apiSteps.postAddNewVacancyByAPI(vacancyForDelete);

        new CandidatesTablePage()
                .clickVacancyMenu()
                .clickAllVacancyCheckbox()
                .findVacancyByName(vacancyForDelete.getName())
                .clickNameAfterFinding()
                .makeHrNull()
                .clickSaveBtn()
                .deleteVacancyByName(vacancyForDelete.getName());

        waitDynamicDisappears();
        logger.logInfo(Screenshots.takeScreenShotAsFile(), "Delete vacancy with name - " + vacancyForDelete);

    }

    public void assertVacancyUrl() {

        new CandidatesTablePage()
                .clickVacancyMenu();

        String currentUrl = url();

        assertEquals(currentUrl, "", "Vacancy URL is not correct");
        waitDynamicDisappears();
        logger.logInfo(Screenshots.takeScreenShotAsFile(), "Assert url Vacancy. URL - " + currentUrl);
    }


    public void assertVacancyHasBeenCreated() {
        var connect = new DBConnect();
        var result = connect.getDBDData(Query.vacancyInfo(vacancyForAdd.getName()));

        assertEquals(result.get("title"), vacancyForAdd.getName(), "Candidate is not created");
        assertEquals(result.get("englishLevel"), vacancyForAdd.getEnglishLevel(), "English Level is not created");
        assertEquals(result.get("project"), vacancyForAdd.getProject(), "Project is not created");
        assertEquals(result.get("interviewer"), vacancyForAdd.getInterviewer(), "Interviewer is not created");
        assertEquals(result.get("requiredTechnologies"), vacancyForAdd.getRequiredTechnologies(), "Required Technologies is not created");
        assertEquals(result.get("desiredTechnologies"), vacancyForAdd.getDesirableTechnologies(), "Desired Technologies is not created");

        logger.logInfo("Assert vacancy. Expected name  - " + vacancyForAdd.getName() + " actual name - " + result.get("title"));

    }

    public void assertVacancyHasBeenUpdated() {
        var connect = new DBConnect();
        var result = connect.getDBDData(Query.vacancyInfo(vacancyForUpdate.getName()));

        assertEquals(result.get("title"), vacancyForUpdate.getName(), "Candidate is not updated");
        assertEquals(result.get("englishLevel"), vacancyForUpdate.getEnglishLevel(), "English Level is not updated");
        assertEquals(result.get("project"), vacancyForUpdate.getProject(), "Project is not updated");
        assertEquals(result.get("interviewer"), vacancyForUpdate.getInterviewer(), "Interviewer is not updated");
        assertEquals(result.get("requiredTechnologies"), vacancyForUpdate.getRequiredTechnologies(), "Required Technologies is not updated");
        assertEquals(result.get("desiredTechnologies"), vacancyForUpdate.getDesirableTechnologies(), "Desired Technologies is not updated");

        logger.logInfo("Assert vacancy. Expected name  - " + vacancyForUpdate.getName() + " actual name - " + result.get("title"));

    }

    public void assertVacancyHasBeenDeleted() {
        var connect = new DBConnect();
        var result = connect.getDBDData(Query.candidateInfo(vacancyForDelete.getName()));
        assertEquals(result.size(), 0);
        waitDynamicDisappears();
        logger.logInfo("Assert vacancy for delete. Vacancy with name  - " + vacancyForDelete.getName() + " deleted. DB request - " + result.size());

    }


    /**
     * This method can to find row with necessary Vacancy name
     *
     * @param name - Vacancy name for find
     */
    public VacancyPage findVacancyByName(String name) {

        VacancyPage vacancyPage = new VacancyPage();

        elementForFind = vacancyPage.getVacancyTable().findAll("a").find(Condition.text(name));
        waitDynamicDisappears();

        if (!elementForFind.is(Condition.visible)) {
            vacancyPage.getLastPageButton().click();
            waitDynamicDisappears();

            while (!elementForFind.is(Condition.visible)) {

                vacancyPage.getPreviousPageButton().click();
                waitDynamicDisappears();
            }
        }
        elementForFind.scrollIntoView(false);
        logger.logInfo(Screenshots.takeScreenShotAsFile(), "Vacancy is found");
        return new VacancyPage();
    }

    /**
     * This method can to delete row with necessary Vacancy name
     *
     * @param name - Vacancy name for find
     */
    public VacancyPage deleteVacancyByName(String name) {

        waitDynamicDisappears();
        findVacancyByName(name);

        elementForFind.parent().parent().findAll("td").last().lastChild().click();

        waitDynamicDisappears();
        new VacancyPage().getDeleteVacancyNotification().click();
        return new VacancyPage();
    }


    /**
     * This method can to find and delete all vacancies with name LIKE 'vacancyName'
     *
     * @param vacancyName - Vacancy name for delete
     */
    public static void deleteAllVacanciesWithNameLike(String vacancyName) {

        open("/login");

        new LoginPage()
                .signInByManager();

        for (int i = 0; i < 80; i++) {

            new CandidatesTablePage()
                    .clickVacancyMenu()
                    .findVacancyByName(vacancyName)
                    .clickNameAfterFinding()
                    .makeHrNull()
                    .clickSaveBtn()
                    .deleteVacancyByName(vacancyName);
        }
    }

}
