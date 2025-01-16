package petAPI.tests;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
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
                        null,
                        new Category(1, "null pet category"),
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
                        OrderStatus.pending),
                new Pet(2,
                        "",
                        new Category(1, "noname pet category"),
                        new ArrayList<>() {{
                            add(new Tag(1, "black"));
                            add(new Tag(2, "angry"));
                        }},
                        new ArrayList<>() {{
                            add("https://photo_cat1.com");
                            add("http://photo_cat2.com");
                        }},
                        OrderStatus.available)
        );
    }

    private static Stream<Pet> getInvalidPets() {
        return Stream.of(
                new Pet(0,
                        "0 ID name",
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
                new Pet(null,
                        "Null ID name",
                        new Category(1, "category name"),
                        new ArrayList<>() {{
                            add(new Tag(1, "blue"));
                            add(new Tag(2, "hungry"));
                        }},
                        new ArrayList<>() {{
                            add("1");
                            add("http://photo_horse2.com");
                        }},
                        OrderStatus.pending),
                new Pet(-1,
                        "Negative ID name",
                        new Category(1, "negative id category"),
                        new ArrayList<>() {{
                            add(new Tag(1, "blue"));
                            add(new Tag(2, "hungry"));
                        }},
                        new ArrayList<>() {{
                            add("1");
                            add("http://photo_horse2.com");
                        }},
                        OrderStatus.pending)
        );
    }

    @ParameterizedTest(name = "{index}. Positive tests. {0}")
    @MethodSource("getValidPets")
    public void postPet_validPet_success200(Pet pet) {
        Pet response = given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post(EndPoints.PET)
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("pet-schema.json"))
                .extract().as(Pet.class);

        assertEquals(pet.toJson(), response.toJson());
    }

    @ParameterizedTest
    @ValueSource(strings = {"testData/valid-pet-all-fields.json"})
    public void postPet_validPet_valid200(String fileName) throws IOException {
        var requestBody = getJsonFromFile(fileName);

            Response response = given()
                    .contentType(ContentType.JSON)
                    .body(requestBody)
                    .when()
                    .post(EndPoints.PET)
                    .then()
                    .statusCode(200)
                    .contentType(ContentType.JSON)
                    .extract().response();

            String responseBody = response.getBody().asString();

            String minifiedRequestBody = minifyJson(requestBody);
            String minifiedResponseBody = minifyJson(responseBody);

            assertEquals(minifiedRequestBody, minifiedResponseBody);
    }

    @ParameterizedTest(name = "{index}. Negative tests. {0}")
    @MethodSource("getInvalidPets")
    public void postPet_invalidPet_400(Pet pet) {
        Pet response = given()
                .contentType(ContentType.JSON)
                .body(pet)
                .when()
                .post(EndPoints.PET)
                .then()
                .statusCode(400)
                .body(matchesJsonSchemaInClasspath("pet-schema.json"))
                .extract().as(Pet.class);

        assertEquals(pet.toJson(), response.toJson());
    }

    private String getJsonFromFile(String fileName) throws IOException {
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IOException("Resource file not found: " + fileName);
            }
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private String minifyJson(String json) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        return jsonNode.toString();
    }
}
