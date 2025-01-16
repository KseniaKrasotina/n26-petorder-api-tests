package petAPI.models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Pet {
    public Integer id;
    public String name;
    public Category category;
    public ArrayList<Tag> tags;
    public ArrayList<String> photoUrls;
    public OrderStatus status;

    public Pet(int i, String s, Category categoryName, ArrayList<Tag> tags) {}

    public Pet(Integer id, String name, Category category, ArrayList<Tag> tags, ArrayList<String> photoUrls, OrderStatus status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.tags = tags;
        this.photoUrls = photoUrls;
        this.status = status;
    }

    public Pet() {
    }

    public Pet(String name) {
        this.name = name;
    }

    public String toJson() {
        Gson gson = new GsonBuilder().create();
        return gson.toJson(this);
    }

    public String toString() {
        return String.format("id: %d, name: %s", id, name);
    }
}
