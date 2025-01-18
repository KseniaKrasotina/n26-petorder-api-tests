//package petAPI.tests;
//
//import io.qameta.allure.Epic;
//import io.qameta.allure.Feature;
//import io.qameta.allure.restassured.AllureRestAssured;
//import io.restassured.RestAssured;
//import io.restassured.http.ContentType;
//import org.junit.jupiter.api.*;
//import petAPI.models.Pet;
//import petAPI.utils.EndPoints;
//import petAPI.utils.TestDataProvider;
//import java.util.ArrayList;
//import static io.restassured.RestAssured.given;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//@Epic("Pet API")
//@Feature("PUT. Update existing Pet")
//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//
//public class UpdatePetTests {
//    @BeforeAll
//    public void setUp() {
//        RestAssured.filters(new AllureRestAssured());
//    }
//    @AfterEach
//    public void clearFilters() {
//        RestAssured.filters(new ArrayList<>());
//    }
//
//    @Test
//    @DisplayName("1. Positive. Update existing Pet Name returns 201")
//    public void put_update_petName_200()
//    {
//        Pet referencePet = TestDataProvider.createReferencePet();
//        postPet(referencePet);
//
//        referencePet.name = "New Pet Name";
//
//        Pet response = given()
//                .contentType(ContentType.JSON)
//                .body(referencePet)
//                .when()
//                .put(EndPoints.PET)
//                .then()
//                .statusCode(200)
//                .extract().as(Pet.class);
//
//        assertEquals(referencePet.toJson(), response.toJson());
//    }
//
//    @Test
//    @DisplayName("1. Negative. Update not existing Pet returns 404")
//
//    public void put_update_notExistingPet_404()
//    {
//        Pet referencePet = TestDataProvider.createReferencePet();
//        postPet(referencePet);
//
//        referencePet.id = 909;
//
//        given()
//                .contentType(ContentType.JSON)
//                .body(referencePet)
//                .when()
//                .put(EndPoints.PET)
//                .then()
//                .statusCode(404);
//    }
//
//    private void postPet(Pet pet) {
//        Pet response = given()
//                .contentType(ContentType.JSON)
//                .body(pet)
//                .when()
//                .post(EndPoints.PET)
//                .then()
//                .statusCode(200)
//                .extract().as(Pet.class);
//    }
//}
