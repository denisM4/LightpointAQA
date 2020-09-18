package by.kvach.lightBase;

import by.kvach.lightBase.config.ConfigTest;
import by.kvach.lightBase.steps.VacancySteps;
import com.codeborne.selenide.Configuration;

public class Main extends ConfigTest {

    public static void main(String[] args) {

        Configuration.baseUrl = "https://test.lightpoint.by";
        Configuration.startMaximized = true;

        new VacancySteps().deleteAllVacanciesWithNameLike("aqa.Denis");

    }

}
