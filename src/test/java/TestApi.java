import by.kvach.lightBase.config.ConfigTest;
import by.kvach.lightBase.model.Candidate;
import by.kvach.lightBase.model.ContactType;
import by.kvach.lightBase.model.Vacancy;
import by.kvach.lightBase.steps.ApiSteps;
import by.kvach.lightBase.util.Util;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestApi extends ConfigTest {

    @BeforeTest
    public void start() {
        configLightBase();
    }

    @Test
    public void postAddNewVacancy() {
        ApiSteps apiSteps = new ApiSteps();
        Vacancy vacancy = Util.createRandomVacancy();

        Assert.assertEquals(apiSteps.postAddNewVacancyByAPI(vacancy).get("title"), vacancy.getName());
    }

    @Test
    public void postAddNewCandidate() {
        ApiSteps apiSteps = new ApiSteps();
        Candidate candidate = Util.createRandomCandidate(ContactType.PHONE);

        Assert.assertEquals(apiSteps.postAddNewCandidateByAPI(candidate).get("candidate.name"), candidate.getName());
    }

}
