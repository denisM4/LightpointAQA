import by.kvach.lightBase.config.ConfigTest;
import by.kvach.lightBase.steps.CandidateSteps;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCandidate extends ConfigTest {

    @BeforeTest
    public void start() {
        configLightBase();
    }

    @Test(priority = 1, description = "Add New Candidate", groups = "Smoke")
    public void addCandidate() {
        CandidateSteps candidateSteps = new CandidateSteps();
        candidateSteps.addNewCandidate();
        candidateSteps.assertCandidateHasBeenCreated();
    }

    @Test(priority = 2,description = "Update created Candidate", groups = "Smoke")
    public void updateCandidate() {
        CandidateSteps candidateSteps = new CandidateSteps();
        candidateSteps.updateCandidate();
        candidateSteps.assertCandidateHasBeenUpDated();
    }

    @Test(priority = 3, description = "Delete created Candidate", groups = "Smoke")
    public void deleteCandidateByName() {
        CandidateSteps candidateSteps = new CandidateSteps();
        candidateSteps.deleteCandidateByName();
        candidateSteps.assertCandidateHasBeenDeleted();
    }

    @Test(priority = 4, description = "Delete created Candidate with opening URL")
    public void deleteCandidateById() {
        CandidateSteps candidateSteps = new CandidateSteps();
        candidateSteps.deleteCandidateByID();
        candidateSteps.assertCandidateHasBeenDeleted();
    }

    @Test(priority = 5, description = "Validation adding new Candidate", groups = "Smoke")
    public void validationAddCandidate() {
        CandidateSteps candidateSteps = new CandidateSteps();
        candidateSteps.validationAddCandidate();
    }

    @Test(priority = 6, description = "Validation Negative adding new Contact", groups = "validationCandidate")
    public void validationDisableAddContact() {
        CandidateSteps candidateSteps = new CandidateSteps();
        candidateSteps.validationDisableAddContactButton();
    }

    @Test(priority = 7, description = "Validation Positive adding new Contact", groups = "validationCandidate")
    public void validationEnableAddContact() {
        CandidateSteps candidateSteps = new CandidateSteps();
        candidateSteps.validationEnableAddContactButton();
    }

    @Test(priority = 8, description = "Validation Candidate URL", groups = "validationCandidate")
    public void assertCandidateUrl() {
        CandidateSteps candidateSteps = new CandidateSteps();
        candidateSteps.assertCandidateUrl();
    }

    @Test(priority = 9, description = "Assert contact types", groups = "validationCandidate")
    public void assertContactTypes() {
        CandidateSteps candidateSteps = new CandidateSteps();
        candidateSteps.validateTypeContact();
    }


}

