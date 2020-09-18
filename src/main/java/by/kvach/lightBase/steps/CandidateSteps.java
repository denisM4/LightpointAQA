package by.kvach.lightBase.steps;

import by.kvach.lightBase.model.Candidate;
import by.kvach.lightBase.model.Contact;
import by.kvach.lightBase.model.ContactType;
import by.kvach.lightBase.page.LoginPage;
import by.kvach.lightBase.page.candidate.AddCandidatePage;
import by.kvach.lightBase.page.candidate.CandidateDetailsPage;
import by.kvach.lightBase.page.candidate.CandidatesTablePage;
import by.kvach.lightBase.service.dbangine.DBConnect;
import by.kvach.lightBase.service.dbangine.Query;
import by.kvach.lightBase.util.LogUtils;
import by.kvach.lightBase.util.Util;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Screenshots;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.url;
import static org.testng.Assert.*;

public class CandidateSteps {

    private Candidate candidateForAdd;
    private Candidate candidateForUpdate;
    private Candidate candidateForDelete;
    private String id;

    private final LogUtils logger = LogUtils.getInstance();

    public void addNewCandidate() {

        candidateForAdd = Util.createRandomCandidate(ContactType.LINKEDIN);

        new CandidatesTablePage()
                .addButtonClick()
                .fillInNameField(candidateForAdd.getName())
                .selectVacancyByName(candidateForAdd.getVacancy())
                .selectPositionByName(candidateForAdd.getPosition())
                .selectLevel(candidateForAdd.getPositionLevel())
                .setSalary(candidateForAdd.getSalary())
                .selectExperienceFieldByValue(candidateForAdd.getExperience())
                .selectHrByValue(candidateForAdd.getHrName())
                .selectEnglishByValue(candidateForAdd.getEnglishLevel())
                .clickAddNewContact()
                .selectContactTypeByValue(candidateForAdd.getContacts().get(0).getType())
                .fillContactValue(candidateForAdd.getContacts().get(0).getValue())
                .selectMainCheckbox(candidateForAdd.getContacts().get(0).isPrimary())
                .addContact()
                .upLoadCvFile()
                .selectTypeInviteByValue(candidateForAdd.getTypeInvite())
                .selectValueInviteByValue(candidateForAdd.getValueInvite())
                .selectCommunicationLevelByValue(candidateForAdd.getCommunicationLevel())
                .fillInCommentField(candidateForAdd.getComment())
                .createCandidateButton();

        Util.waitDynamicDisappears();
        logger.logInfo(Screenshots.takeScreenShotAsFile(), "Create new - " + candidateForAdd.toString());
    }

    public void updateCandidate() {

        candidateForAdd = Util.createRandomCandidate(ContactType.PHONE);

        ApiSteps apiSteps = new ApiSteps();
        apiSteps.postAddNewCandidateByAPI(candidateForAdd);
        logger.logInfo("Create candidate and add to DB by API with name - " + candidateForAdd.getName());

        candidateForUpdate = Util.createRandomCandidate(ContactType.TELEGRAM);
        logger.logInfo("Create candidate for update with name - " + candidateForUpdate.getName());

        new CandidatesTablePage()
                .findCandidateByName(candidateForAdd.getName())
                .clickFirstCandidate()
                .renameCandidate(candidateForUpdate.getName())
                .deleteContact(0)
                .clickAddNewContact()
                .selectContactTypeByValue(String.valueOf(candidateForUpdate.getContacts().get(0).getType()))
                .fillContactValue(candidateForUpdate.getContacts().get(0).getValue())
                .selectMainCheckbox(candidateForUpdate.getContacts().get(0).isPrimary())
                .addContact()
                .selectVacancy(candidateForUpdate.getVacancy())
                .selectPosition(candidateForUpdate.getPosition())
                .selectLevel(candidateForUpdate.getPositionLevel())
                .fillInSalaryField(String.valueOf(candidateForUpdate.getSalary()))
                .selectExperience(candidateForUpdate.getExperience())
                .selectHr(candidateForUpdate.getHrName())
                .selectEnglishLevel(candidateForUpdate.getEnglishLevel());

        Util.waitDynamicDisappears();
        logger.logInfo(Screenshots.takeScreenShotAsFile(), "Candidate " + candidateForAdd.getName() + " updated to " + candidateForUpdate.getName());

    }

    public void deleteCandidateByName() {

        candidateForDelete = Util.createRandomCandidate(ContactType.PHONE);
        ApiSteps apiSteps = new ApiSteps();
        apiSteps.postAddNewCandidateByAPI(candidateForDelete);
        logger.logInfo("Create candidate for delete by API with name - " + candidateForDelete.getName());

        new CandidatesTablePage()
                .findCandidateByName(candidateForDelete.getName())
                .clickFirstCandidate()
                .deleteCandidate();

        Util.waitDynamicDisappears();
        logger.logInfo(Screenshots.takeScreenShotAsFile(), "Candidate " + candidateForDelete.getName() + " deleted");
    }


    public void deleteCandidateByID() {

        candidateForDelete = Util.createRandomCandidate(ContactType.PHONE);
        ApiSteps apiSteps = new ApiSteps();
        apiSteps.postAddNewCandidateByAPI(candidateForDelete);

        var connect = new DBConnect();
        var result = connect.getDBDData(Query.candidateInfoFofAPI(candidateForDelete.getName()));

        id = result.get("idCandidate");

        open("/candidate/" + id + "/details");

        new CandidateDetailsPage()
                .deleteCandidate();

        Util.waitDynamicDisappears();
        logger.logInfo(Screenshots.takeScreenShotAsFile(), "Candidate " + candidateForDelete.getName() + " deleted");
    }

    public void assertCandidateHasBeenCreated() {
        var connect = new DBConnect();
        var result = connect.getDBDData(Query.candidateInfo(candidateForAdd.getName()));

        assertEquals(result.get("candidateName"), candidateForAdd.getName(), "Candidate is not added");
        assertEquals(result.get("vacancy"), candidateForAdd.getVacancy(), "Vacancy is not added");
        assertEquals(result.get("positionName"), candidateForAdd.getPosition(), "Position Name is not added");
        assertEquals(result.get("englishLevel"), candidateForAdd.getEnglishLevel(), "English Level is not added");
        assertEquals(result.get("experience"), candidateForAdd.getExperience(), "Experience is not added");
        assertEquals(result.get("hr"), candidateForAdd.getHrName(), "Hr is not added");
        assertEquals(result.get("contactName"), candidateForAdd.getContacts().get(0).getType(), "Contact Name is not added");
        assertEquals(result.get("contactValue"), candidateForAdd.getContacts().get(0).getValue(), "Contact Value is not added");

        logger.logInfo("Assert candidate. Expected name  - " + candidateForAdd.getName() + " actual name - " + result.get("candidateName"));

    }

    public void assertCandidateHasBeenUpDated() {
        var connect = new DBConnect();
        var result = connect.getDBDData(Query.candidateInfo(candidateForUpdate.getName()));

        assertEquals(result.get("candidateName"), candidateForUpdate.getName(), "Candidate is not updated");
        assertEquals(result.get("vacancy"), candidateForUpdate.getVacancy(), "Vacancy is not updated");
        assertEquals(result.get("positionName"), candidateForUpdate.getPosition(), "Position Name is not updated");
        assertEquals(result.get("englishLevel"), candidateForUpdate.getEnglishLevel(), "English Level is not updated");
        assertEquals(result.get("experience"), candidateForUpdate.getExperience(), "Experience is not updated");
        assertEquals(result.get("hr"), candidateForUpdate.getHrName(), "Hr is not updated");
        assertEquals(result.get("contactName"), candidateForUpdate.getContacts().get(0).getType(), "Contact is not updated");
        assertEquals(result.get("contactValue"), candidateForUpdate.getContacts().get(0).getValue(), "Contact is not updated");

        logger.logInfo("Assert candidate. Expected name  - " + candidateForUpdate.getName() + " actual name - " + result.get("candidateName"));

    }

    public void assertCandidateHasBeenDeleted() {
        var connect = new DBConnect();
        var result = connect.getDBDData(Query.candidateInfo(candidateForDelete.getName()));
        assertEquals(result.size(), 0);
        logger.logInfo("Assert candidateForDelete. Candidate with name  - " + candidateForDelete.getName() + " deleted. DB request - " + result.size());

    }


    //////////////////// Validation Candidate Steps //////////////////////////


    public void validationAddCandidate() {

        logger.logInfo("Checking 'Add candidate button'. Without Name and Contact the button should be Disable");
        AddCandidatePage addCandidatePage = new AddCandidatePage();
        Contact contact = Util.createRandomContact(ContactType.LINKEDIN);

        new CandidatesTablePage()
                .addButtonClick();

        assertFalse(addCandidatePage.getCreateButton().isEnabled());
        logger.logInfo("Name and Contact is empty. Button is disable");

        addCandidatePage
                .fillInNameField(Util.createRandomWord(8));

        assertFalse(addCandidatePage.getCreateButton().isEnabled());
        logger.logInfo("Name is not empty. Contact is empty. Button is disable");

        addCandidatePage
                .clickAddNewContact()
                .fillContactValue(contact.getValue())
                .selectContactTypeByValue(contact.getType())
                .selectMainCheckbox(contact.isPrimary())
                .addContact();

        assertTrue(addCandidatePage.getCreateButton().isEnabled());
        logger.logInfo("Name and Contact is not empty. Button is enable");

        addCandidatePage
                .createCandidateButton();

        assertTrue(addCandidatePage.getNotification().is(Condition.appears));
        logger.logInfo("New candidate is added to DB. Notification is appear");

    }

    public void validationDisableAddContactButton() {

        AddCandidatePage addCandidatePage = new AddCandidatePage();
        Contact contact = Util.createRandomContact(ContactType.EMAIL);

        new CandidatesTablePage()
                .addButtonClick()
                .clickAddNewContact();

        assertFalse(addCandidatePage.getAddContactButton().isEnabled());
        logger.logInfo("Assert enable 'Add contact' button. Name and Contact is not empty. Button is disable");

        addCandidatePage
                .fillContactValue(contact.getValue())
                .selectContactTypeByValue(contact.getType())
                .selectMainCheckbox(contact.isPrimary());

        assertTrue(addCandidatePage.getAddContactButton().isEnabled());
        logger.logInfo("Assert enable 'Add contact' button.Name and Contact is not empty. Button is enable");

    }

    public void validationEnableAddContactButton() {

        AddCandidatePage addCandidatePage = new AddCandidatePage();
        Contact contact = Util.createRandomContact(ContactType.TELEGRAM);

        new CandidatesTablePage()
                .addButtonClick()
                .clickAddNewContact()
                .fillContactValue(contact.getValue())
                .selectContactTypeByValue(contact.getType())
                .selectMainCheckbox(contact.isPrimary());

        assertTrue(addCandidatePage.getAddContactButton().isEnabled());
        logger.logInfo("Assert enable 'Add contact' button.Name and Contact is not empty. Button is enable");

    }

    public void validateTypeContact() {

        AddCandidatePage addCandidatePage = new AddCandidatePage();

        Contact email = Util.createRandomContact(ContactType.EMAIL);
        Contact linkedin = Util.createRandomContact(ContactType.LINKEDIN);
        Contact phone = Util.createRandomContact(ContactType.PHONE);
        Contact skype = Util.createRandomContact(ContactType.SKYPE);

        new CandidatesTablePage()
                .addButtonClick()
                .clickAddNewContact();

        assertFalse(addCandidatePage.getAddContactButton().isEnabled());
        logger.logInfo("Assert enable 'Add contact' button. Name and Contact is not empty. Button is disable");

        //EMAIL
        addCandidatePage
                .fillContactValue("1111111111")
                .selectContactTypeByValue(email.getType())
                .selectMainCheckbox(email.isPrimary());

        assertFalse(addCandidatePage.getAddContactButton().isEnabled());
        logger.logInfo("Assert enable 'Add contact' button. Email value wrong - " + "1111111111" + " Button is disable");

        addCandidatePage
                .fillContactValue(email.getValue())
                .selectContactTypeByValue(email.getType())
                .selectMainCheckbox(email.isPrimary());

        assertTrue(addCandidatePage.getAddContactButton().isEnabled());
        logger.logInfo("Assert enable 'Add contact' button. Email value is - " + email.getValue() + ". Button is enable");

        //LINKEDIN
        addCandidatePage
                .fillContactValue("1111111111")
                .selectContactTypeByValue(linkedin.getType())
                .selectMainCheckbox(linkedin.isPrimary());

        assertFalse(addCandidatePage.getAddContactButton().isEnabled());
        logger.logInfo("Assert enable 'Add contact' button. LINKEDIN value wrong - " + "1111111111" + " Button is disable");

        addCandidatePage
                .fillContactValue(linkedin.getValue())
                .selectContactTypeByValue(linkedin.getType())
                .selectMainCheckbox(linkedin.isPrimary());

        assertTrue(addCandidatePage.getAddContactButton().isEnabled());
        logger.logInfo("Assert enable 'Add contact' button. LINKEDIN value is - " + email.getValue() + ". Button is enable");

        //PHONE
        addCandidatePage
                .fillContactValue("qqqqqqqqq")
                .selectContactTypeByValue(phone.getType())
                .selectMainCheckbox(phone.isPrimary());

        assertTrue(addCandidatePage.getAddContactButton().isEnabled());
        logger.logInfo("Assert enable 'Add contact' button. PHONE value wrong - " + "1111111111" + " Button is disable");

        addCandidatePage
                .fillContactValue(phone.getValue())
                .selectContactTypeByValue(phone.getType())
                .selectMainCheckbox(phone.isPrimary());

        assertTrue(addCandidatePage.getAddContactButton().isEnabled());
        logger.logInfo("Assert enable 'Add contact' button. PHONE value is - " + email.getValue() + ". Button is enable");

        //SKYPE
        addCandidatePage
                .fillContactValue("12312qweq!qweq/.,/*-+")
                .selectContactTypeByValue(skype.getType())
                .selectMainCheckbox(skype.isPrimary());

        assertTrue(addCandidatePage.getAddContactButton().isEnabled());
        logger.logInfo("Assert enable 'Add contact' button. SKYPE value wrong - " + "1111111111" + " Button is disable");

        addCandidatePage
                .fillContactValue(skype.getValue())
                .selectContactTypeByValue(skype.getType())
                .selectMainCheckbox(skype.isPrimary());

        assertTrue(addCandidatePage.getAddContactButton().isEnabled());
        logger.logInfo("Assert enable 'Add contact' button. SKYPE value is - " + email.getValue() + ". Button is enable");


    }

    public void assertCandidateUrl() {

        new CandidatesTablePage()
                .clickCandidatesMenu();

        String currentUrl = url();

        assertEquals(currentUrl, "", "Candidates URL is not correct");
        logger.logInfo("Assert url Candidates. URL - " + currentUrl);

    }

    /**
     * This method can to find and delete all candidates with name LIKE 'candidateName'
     *
     * @param candidateName - Candidate name for delete
     */
    public static void deleteAllCandidateWithNameLike(String candidateName) {

        open("/");

        new LoginPage()
                .signInByManager();

        for (int i = 0; i < 120; i++) {

            new CandidatesTablePage()
                    .findCandidateByName(candidateName)
                    .clickFirstCandidate()
                    .deleteCandidate();
            Util.waitDynamicDisappears();
        }
    }
}

