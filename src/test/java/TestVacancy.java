import by.kvach.lightBase.config.ConfigTest;
import by.kvach.lightBase.steps.VacancySteps;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestVacancy extends ConfigTest {

    @BeforeTest
    public void start() {
        configLightBase();
    }


    @Test(priority = 1, description = "Add New Vacancy" )
    public void addVacancy(){
        VacancySteps vacancySteps = new VacancySteps();
        vacancySteps.addVacancy();
        vacancySteps.assertVacancyHasBeenCreated();
    }

    @Test(priority = 2, description = "Update New Vacancy")
    public void updateVacancy(){
        VacancySteps vacancySteps = new VacancySteps();
        vacancySteps.updateVacancy();
        vacancySteps.assertVacancyHasBeenUpdated();
    }

    @Test(priority = 3, description = "Delete New Vacancy")
    public void deleteVacancy(){
        VacancySteps vacancySteps = new VacancySteps();
        vacancySteps.deleteVacancy();
        vacancySteps.assertVacancyHasBeenDeleted();
    }

    @Test(priority = 4, description = "Assert Vacancy URL", groups = "validationVacancy")
    public void assertURLVacancy(){
        VacancySteps vacancySteps = new VacancySteps();
        vacancySteps.assertVacancyUrl();
    }

}

