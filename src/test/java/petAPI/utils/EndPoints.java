package petAPI.utils;

public class EndPoints {
    public static final String pet = Config.BASE_URL + "pet/";
    public static final String petUpdate = Config.BASE_URL + "pet/";
    public static final String petFindByStatus = Config.BASE_URL + "pet/findByStatus";
    public static final String petDelete = Config.BASE_URL + "pet/{petId}";
}