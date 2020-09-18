package by.kvach.lightBase.page.vacancy;

import by.kvach.lightBase.steps.VacancySteps;
import by.kvach.lightBase.util.LogUtils;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class VacancyPage extends VacancyAbstractModal<VacancyPage> {

    public VacancyPage() {
        super(VacancyPage::new);
    }

    private final LogUtils logger = LogUtils.getInstance();

    VacancySteps vacancySteps = new VacancySteps();

    private final SelenideElement

            //Buttons
            addVacancyButton = $x("//button"),
            nextPageButton = $x("//kendo-pager-next-buttons/a[@title = 'Go to the next page']"),
            lastPageButton = $x("//a[@title = 'Go to the last page']"),
            previousPageButton = $x("//a[@title = 'Go to the previous page']"),

            //Checkboxes
            allVacancyCheckbox = $x("//li[contains(., 'вакансии')]/input"),
            vacancyInWorkCheckbox = $x("//li[contains(., 'работе')]/input"),
            closedVacancyCheckbox = $x("//li[contains(., 'Закрыта')]/input"),

            //Table
            vacancyTable = $x("//tbody"),

            //Notifications
            deleteVacancyNotification = $x("//div[text()='Вакансия успешно удалена']"),
            notPossibleDeleteVacancyNotification = $x("//div[@class = 'sn-title']");

    public AddVacancyModal clickAddVacancy() {
        logger.logInfo("Click 'Add vacancy' button");
        addVacancyButton.shouldBe(Condition.enabled).click();
        return new AddVacancyModal();
    }

    public VacancyPage clickAllVacancyCheckbox() {
        logger.logInfo("Click 'All vacancies' checkbox");
        allVacancyCheckbox.shouldNotBe(Condition.checked).click();
        return this;
    }

    public VacancyPage clickInWorkVacancyCheckbox() {
        logger.logInfo("Click 'In work' checkbox");
        vacancyInWorkCheckbox.shouldBe(Condition.enabled).click();
        return this;
    }

    public VacancyPage clickClosedVacancyCheckbox() {
        logger.logInfo("Click 'Closed' checkbox");
        closedVacancyCheckbox.shouldBe(Condition.enabled).click();
        return this;
    }

    public EditVacancyModal clickNameAfterFinding() {
        logger.logInfo("Click name vacancy after finding");
        VacancySteps.elementForFind.scrollIntoView(false).click();
        return new EditVacancyModal();
    }

    public EditVacancyModal clickNextPageButton() {
        logger.logInfo("Click 'Next page' button");
        nextPageButton.click();
        return new EditVacancyModal();
    }

    public EditVacancyModal clickPreviousPageButton() {
        logger.logInfo("Click 'Previous page' button");
        previousPageButton.click();
        return new EditVacancyModal();
    }

    public EditVacancyModal clickLastPageButton() {
        logger.logInfo("Click 'Last page' button");
        nextPageButton.click();
        return new EditVacancyModal();
    }

    public VacancyPage findVacancyByName(String name) {
        logger.logInfo("Find vacancy with Name - " + name);
        vacancySteps.findVacancyByName(name);
        return new VacancyPage();
    }

    public VacancyPage deleteVacancyByName(String name) {
        vacancySteps.deleteVacancyByName(name);
        logger.logInfo("Vacancy with Name - " + name + " - was deleted");
        return new VacancyPage();
    }

    public SelenideElement getNextPageButton() {
        nextPageButton.scrollIntoView(false);
        return nextPageButton;
    }

    public SelenideElement getLastPageButton() {
        return lastPageButton;
    }

    public SelenideElement getDeleteVacancyNotification() {
        return deleteVacancyNotification;
    }

    public SelenideElement getVacancyTable() {
        return vacancyTable;
    }

    public SelenideElement getPreviousPageButton() {
        return previousPageButton;
    }
}
