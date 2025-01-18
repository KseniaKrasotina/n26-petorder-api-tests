package petAPI.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import petAPI.models.Pet;
import petAPI.utils.EndPoints;
import petAPI.utils.TestDataProvider;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Pet API")
@Feature("GET. Get pet by ID")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class GetPetbyIDTests {

    @BeforeAll
    public void setUp() {
        RestAssured.filters(new AllureRestAssured());
    }

    @Test
    @DisplayName("1. Positive. Get Existing Pet: Returns 200 and Verifies Response Structure")
    public void get_existing_returns200()
    {
        Pet referencePet = TestDataProvider.createReferencePet();
        postPet(referencePet);

        Pet response = given()
                .when()
                .get(EndPoints.PET+referencePet.id)
                .then()
                .statusCode(200)
                .extract().as(Pet.class);
        assertEquals(referencePet.toJson(), response.toJson());
    }

    @Test
    @DisplayName("2. Negative. Get not existing Pet: Returns 404")
    public void get_notExisting_returns404()
    {
        int notExistingId = 101;

        given()
                .when()
                .get(EndPoints.PET + notExistingId)
                .then()
                .statusCode(404);
    }

    private void postPet(Pet pet) {
        Pet response = given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post(EndPoints.PET)
                .then()
                .statusCode(200)
                .extract().as(Pet.class);
    }
}
