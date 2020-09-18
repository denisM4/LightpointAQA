package by.kvach.lightBase.service.apiangine;

import by.kvach.lightBase.model.Candidate;
import by.kvach.lightBase.model.Vacancy;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonBody {

    public static String getCandidateBody(Candidate candidateForCreate) {

        JSONObject jsonObject = new JSONObject();
        JSONObject candidate = new JSONObject();
        JSONArray array = new JSONArray();
        JSONObject contactTypeJSONObject = new JSONObject();
        JSONObject contactProperties = new JSONObject();

        jsonObject.put("candidate", candidate);

        candidate
                .put("name", candidateForCreate.getName())
                .put("contacts", array);

        array
                .put(contactProperties);

        contactProperties
                .put("contactTypeId", 1)
                .put("value", candidateForCreate.getContacts().get(0).getValue())
                .put("isPrimary", true)
                .put("contactType", contactTypeJSONObject);

        candidate
                .put("isPrimary", false)
                .put("isRejected", false);

        jsonObject
                .put("candidate", candidate)
                .put("vacancyRequestId", "")
                .put("communicationStatusId", 1)
                .put("sourceTypeId", 1)
                .put("sourceContactTypeId", 1)
                .put("recommendedBy", "")
                .put("hrManagerAspNetUserId", "036bc3ec-7445-4462-8d94-976fed77b977")
                .put("levelId", "")
                .put("positionName", "")
                .put("positionId", 1)
                .put("salary", "")
                .put("experienceId", 1)
                .put("cvPath", "")
                .put("comment", "");

        return jsonObject.toString();

    }

    public static String getVacancyBody(Vacancy vacancy) {

        JSONObject vacancyJson = new JSONObject();

        vacancyJson
                .put("creatorName", "")
                .put("title", vacancy.getName())
                .put("levelName", vacancy.getLevel())
                .put("project", vacancy.getProject())
                .put("levelId", "")
                .put("requiredTechnologies", vacancy.getRequiredTechnologies())
                .put("desiredTechnologies", vacancy.getDesirableTechnologies())
                .put("englishLevel", vacancy.getEnglishLevel())
                .put("salary", vacancy.getSalary())
                .put("interviewer", vacancy.getInterviewer())
                .put("details", vacancy.getAdditionally())
                .put("closedDate", vacancy.getClosedDate() + "T00:00:00.000");

        return vacancyJson.toString();
    }

}
