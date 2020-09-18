package by.kvach.lightBase.page;

import by.kvach.lightBase.page.candidate.CandidatesTablePage;
import by.kvach.lightBase.util.LogUtils;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final LogUtils logger = LogUtils.getInstance();

    private final SelenideElement
            //Fields
            userNameField = $("input#inputLogin"),
            userPasswordField = $x("//input[@id='inputPassword']"),

            //Buttons
            registerButton = $x("//a[contains(href, register)]"),
            singInButton = $x("//button[@class='btn btn-default btn-auth']");


    public CandidatesTablePage signInByManager() {
        logger.logInfo("Sign in as Manager");
        userNameField.setValue("");
        userPasswordField.setValue("");
        singInButton.click();
        return new CandidatesTablePage();
    }

    public CandidatesTablePage signInByAdmin() {
        logger.logInfo("Sign in as Admin");
        userNameField.setValue("");
        userPasswordField.setValue("");
        singInButton.click();
        return new CandidatesTablePage();
    }

    public CandidatesTablePage fillNameAndPassword(String login, String password) {
        userNameField.setValue(login);
        userPasswordField.setValue(password);
        singInButton.click();
        return new CandidatesTablePage();
    }

}

