package by.kvach.lightBase.page.vacancy;

import by.kvach.lightBase.util.LogUtils;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class AddVacancyModal extends VacancyAbstractModal<AddVacancyModal>{

    public AddVacancyModal() {
        super(AddVacancyModal::new);
    }

    private final LogUtils logger = LogUtils.getInstance();

    private final SelenideElement

            //Buttons
            addVacancyButton = $x("(//button[@class = 'btn btn-primary add-vacancy'])[1]"),
            cancelButton = $x("(//button[@class = 'btn btn-primary add-vacancy'])[2]"),
            confirmMessage = $x("//div[contains(text(), 'Вакансия успешно добавлена')]");


    /**
     * This method allows to click Add Communication Button
     */
    public VacancyPage clickaAddVacancyButton(){
        logger.logInfo("Click button with name 'Add'");
        addVacancyButton.shouldBe(Condition.enabled).click();
        confirmMessage.shouldBe(Condition.appears);
        return new VacancyPage();
    }
}

