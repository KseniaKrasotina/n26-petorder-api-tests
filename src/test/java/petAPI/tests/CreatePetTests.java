package petAPI.tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestInstance;
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

@DisplayName("Tests for pet creation")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CreatePetTests
{
    private static Stream<Arguments> getPetsForPositiveTests() {
        var dummyPet = createDummyPet();

        return Stream.of(
                Arguments.of(dummyPet)
        );
    }

    private static Pet createDummyPet()
    {
        var tags1 = new ArrayList<Tag>(){{
            add(new Tag(1, "tag1"));
            add(new Tag(2, "tag2"));
        }};
        var photoUrls1 = new ArrayList<String>() {{
            add("https://photo1.com");
            add("http://photo2.com");
        }};
        return new Pet( 1,  "Pet name", new Category(1, "category name"), tags1, photoUrls1, OrderStatus.available);
    }

    @ParameterizedTest(name = "{index}. Positive tests. {0}")
    @MethodSource("getPetsForPositiveTests")
    public void postPet_validPet_success(Pet pet)
    {
        Pet response = given()
            .contentType(ContentType.JSON)
            .body(pet)
        .when()
            .post(EndPoints.pet)
        .then()
            .statusCode(200)
                .extract().as(Pet.class);

//        assertEquals(pet.id, response.id);
//        assertEquals(pet.name, response.name);
//        assertEquals(pet.category.id, response.category.id);
//        assertEquals(pet.tags.size(), response.tags.size());
        assertEquals(pet.toJson(), response.toJson());
    }
}
