package vmo.demowebshop.utils;

import com.github.javafaker.Faker;

public class DataFakerUtils {
    private Faker faker;

    public static DataFakerUtils getData() {
        return new DataFakerUtils();
    }
    private DataFakerUtils() {
        faker = new Faker();
    }
    public String getFirstName() {
        return faker.name().firstName();
    }
    public String getLastName() {
        return faker.name().lastName();
    }
    public String getEmailAddress() {
        return faker.internet().emailAddress();
    }
    public String getCompanyName() {
        return faker.company().name();
    }
    public String getCityName() {
        return faker.address().cityName();
    }
    public String getFullAddress() {
        return faker.address().fullAddress();
    }

    public String getZipCode() {
        return faker.address().zipCode();
    }

    public String getPhoneNumber() {
        return faker.phoneNumber().phoneNumber();
    }
    public String getCountry(){
        return faker.country().name();
    }
}
