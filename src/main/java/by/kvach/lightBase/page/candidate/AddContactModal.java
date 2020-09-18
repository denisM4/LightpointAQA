package by.kvach.lightBase.page.candidate;

import by.kvach.lightBase.util.LogUtils;
import by.kvach.lightBase.util.Util;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.util.function.Supplier;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public abstract class AddContactModal<Context> {

    private Supplier<Context> callContext;

    public AddContactModal(Supplier<Context> callContext) {
        this.callContext = callContext;
    }

    private final LogUtils logger = LogUtils.getInstance();

    private final SelenideElement container = $x("//app-add-contact-modal/div");

    private final SelenideElement
            //Fields
            typeContactList = $("app-add-contact-modal select"),
            valueContactInput = container.$(" input#input-data"),
            mainContactCheckBox = $x("//input[@type = 'checkbox']"),

            //Buttons
            addContactButton = $x("//app-add-contact-modal/div//button[contains(@class, 'primary')]"),
            cancelButton = $x("//button[contains(@class, 'danger')]"),
            closeButton = $x("//button[@class = 'close']");



    public AddContactModal<Context> selectContactTypeByValue(String value) {
        logger.logInfo("Select contact type by Value - " + value);
        typeContactList.click();
        typeContactList.selectOption(value);
        return this;
    }

    public AddContactModal<Context> selectContactTypeByNumber(int value) {
        logger.logInfo("Select contact type by Number - " + value);
        container.shouldBe(Condition.visible);
        typeContactList.selectOption(value);
        return this;
    }

    public AddContactModal<Context> fillContactValue(String value) {
        logger.logInfo("Select contact type by Number - " + value);
        valueContactInput.setValue(value);
        return this;
    }

    public AddContactModal<Context> selectMainCheckbox(boolean selectOption) {
        logger.logInfo("Select main checkbox - " + selectOption);
        mainContactCheckBox.setSelected(selectOption);
        return this;
    }

    public Context addContact() {
        logger.logInfo("Click button 'Add Contact' ");
        addContactButton.click();
        Util.waitDynamicDisappears();
        return callContext.get();
    }

    public Context clickCancelButton() {
        logger.logInfo("Click button 'Cancel' ");
        cancelButton.click();
        return callContext.get();
    }

    public Context closeModalWindow() {
        logger.logInfo("Click X - close modal window");
        closeButton.click();
        return callContext.get();
    }

    public SelenideElement getAddContactButton() {
        Util.waitDynamicDisappears();
        return addContactButton;
    }
}
