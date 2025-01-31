package petAPI.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import petAPI.models.Pet;
import petAPI.utils.EndPoints;
import petAPI.utils.TestDataProvider;
import java.util.ArrayList;
import static io.restassured.RestAssured.given;

@Epic("Pet API")
@Feature("DELETE. Delete pet")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class DeletePetTests {
    @BeforeAll
    public void setUp() {
        RestAssured.filters(new AllureRestAssured());
    }

    @AfterEach
    public void clearFilters() {
        RestAssured.filters(new ArrayList<>());
    }

    @Test
    @DisplayName("1. Positive. Delete existing Pet returns 200")
    public void delete_existing_returns200()
    {
        Pet referencePet = TestDataProvider.createReferencePet();

        given()
                .when()
                .delete(EndPoints.PET+referencePet.id)
                .then()
                .statusCode(200);
    }

    @Test
    @DisplayName("1. Negative. Delete not existing  Pet returns 400")
    public void delete_notExisting_returns404()
    {
        int notExistingId = 101;

        given()
                .when()
                .delete(EndPoints.PET+notExistingId)
                .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("1. Negative. Delete the Pet with invalid id returns 400")
    public void delete_withInvalidId_returns400()
    {
        String invalidId = "a";
        System.out.println(EndPoints.PET+invalidId);

        given()
                .when()
                .delete(EndPoints.PET+invalidId)
                .then()
                .statusCode(400);
    }
}
