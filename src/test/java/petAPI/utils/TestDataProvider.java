package petAPI.utils;

import petAPI.models.Category;
import petAPI.models.OrderStatus;
import petAPI.models.Pet;
import petAPI.models.Tag;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDataProvider {
    public static Pet createReferencePet() {
        return new Pet(
                9,
                "I am ReferencePet",
                new Category(1, "Reference category name"),
                new ArrayList<>() {{
                    add(new Tag(1, "Reference white"));
                    add(new Tag(2, "Reference friendly"));
                }},
                new ArrayList<>() {{
                    add("https://photo_dog1.com");
                    add("http://photo_dog2.com");
                }},
                OrderStatus.sold);
    }
}
