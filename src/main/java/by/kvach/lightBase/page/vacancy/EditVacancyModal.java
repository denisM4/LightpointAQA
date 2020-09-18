package by.kvach.lightBase.page.vacancy;

import by.kvach.lightBase.util.LogUtils;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class EditVacancyModal extends VacancyAbstractModal<EditVacancyModal> {

    public EditVacancyModal() {
        super(EditVacancyModal::new);
    }

    private final LogUtils logger = LogUtils.getInstance();

    private final SelenideElement

            //Buttons
            saveButton = $x("//button[@class = 'btn btn-primary add-vacancy'][1]"),

            //Checkboxes
            inWorkCheckbox = $("#isInWork"),
            closedCheckbox = $("#isClosed");


    public EditVacancyModal clickInWorkCheckbox() {
        logger.logInfo("Click Checkbox - 'In work'");
        inWorkCheckbox.click();
        return this;
    }

    public EditVacancyModal clickClosedCheckbox() {
        logger.logInfo("Click Checkbox - 'Closed'");
        closedCheckbox.click();
        return this;
    }

    public VacancyPage clickSaveBtn() {
        logger.logInfo("Click 'Save vacancy' button");
        saveButton.click();
        return new VacancyPage();
    }



}





