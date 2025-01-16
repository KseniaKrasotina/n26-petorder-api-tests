package petAPI.tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import petAPI.models.Category;
import petAPI.models.OrderStatus;
import petAPI.models.Pet;
import petAPI.models.Tag;
import petAPI.utils.EndPoints;

import java.util.ArrayList;
import java.util.stream.Stream;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

@Epic("Pet API")
@Feature("Tests for pet creation")

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreatePetTests
{
    private static Stream<Arguments> getPetsForPositiveTests() {
        var dummyPet = createDummyPet();
public class CreatePetTests {

    @BeforeAll
    public void setUp() {
        RestAssured.filters(new AllureRestAssured());
    }

    @AfterEach
    public void clearFilters() {
        RestAssured.filters(new ArrayList<>());
    }

    private static Stream<Pet> getValidPets() {
        return Stream.of(
                new Pet(1,
                        "Rex",
                        new Category(1, "category name"),
                        new ArrayList<>() {{
                            add(new Tag(1, "white"));
                            add(new Tag(2, "friendly"));
                        }},
                        new ArrayList<>() {{
                            add("https://photo_dog1.com");
                            add("http://photo_dog2.com");
                        }},
                        OrderStatus.sold),
                new Pet(2,
                        "Mewo",
                        new Category(1, "category name"),
                        new ArrayList<>() {{
                            add(new Tag(1, "black"));
                            add(new Tag(2, "angry"));
                        }},
                        new ArrayList<>() {{
                            add("https://photo_cat1.com");
                            add("http://photo_cat2.com");
                        }},
                        OrderStatus.available),
                new Pet(3,
                        "Funny",
                        new Category(1, "category name"),
                        new ArrayList<>() {{
                            add(new Tag(1, "blue"));
                            add(new Tag(2, "hungry"));
                        }},
                        new ArrayList<>() {{
                            add("https://photo_horse1.com");
                            add("http://photo_horse2.com");
                        }},
                        OrderStatus.pending)
        );
    }
        return Stream.of(
                Arguments.of(dummyPet)
        );
    }

    @ParameterizedTest(name = "{index}. Positive tests. {0}")
    @MethodSource("getValidPets")
    public void postPet_validPet_success200(Pet pet) {
        Pet response = given()
            .contentType(ContentType.JSON)
            .body(pet)
        .when()
            .post(EndPoints.pet)
        .then()
            .statusCode(200)
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post(EndPoints.pet)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("pet-schema.json"))
                .extract().as(Pet.class);

        assertEquals(pet.toJson(), response.toJson());
    }
}
