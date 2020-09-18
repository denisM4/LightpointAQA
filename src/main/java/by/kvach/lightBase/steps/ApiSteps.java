package by.kvach.lightBase.steps;

import by.kvach.lightBase.model.Candidate;
import by.kvach.lightBase.model.Vacancy;
import by.kvach.lightBase.service.apiangine.APIExecutor;
import by.kvach.lightBase.service.apiangine.EndPoint;
import by.kvach.lightBase.service.apiangine.JsonBody;
import by.kvach.lightBase.util.LogUtils;
import by.kvach.lightBase.util.Util;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;

public class ApiSteps extends APIExecutor {

    private final LogUtils logger = LogUtils.getInstance();

    public JsonPath postAddNewVacancyByAPI(Vacancy vacancy) {

        String jsonBody = JsonBody.getVacancyBody(vacancy);

        logger.logInfo(Util.writeToFile(vacancy.toString()), " Candidate created by API");

        return given()
                .with()
                .spec(createPostRequestSpecification())
                .body(jsonBody)
                .when()
                .post(EndPoint.END_POINT_VACANCY)
                .then()
                .spec(createPostResponseSpecification())
                .log().ifValidationFails()
                .extract().jsonPath();
    }


    public JsonPath postAddNewCandidateByAPI(Candidate candidate) {

        String jsonBody = JsonBody.getCandidateBody(candidate);

        logger.logInfo(Util.writeToFile(candidate.toString()), " Candidate created by API");

        return given()
                .with()
                .spec(createPostRequestSpecification())
                .body(jsonBody)
                .when()
                .post(EndPoint.END_POINT_CANDIDATE)
                .then()
                .spec(createPostResponseSpecification())
                .log().ifValidationFails()
                .extract().jsonPath();
    }

}
