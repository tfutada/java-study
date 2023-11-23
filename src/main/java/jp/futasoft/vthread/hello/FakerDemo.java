package jp.futasoft.vthread.hello;

import com.github.javafaker.Faker;

public class FakerDemo {
    public static void main(String[] args) {
        // Create a new Faker instance
        var faker = new Faker();
        // Generate some fake data
        String name = faker.name().fullName(); // Ex: "John Smith"
        String email = faker.internet().emailAddress(); // Ex: "john.smith@example.com"
        String phoneNumber = faker.phoneNumber().phoneNumber(); // Ex: "(555) 555-5555"
        String address = faker.address().fullAddress(); // Ex: "1234 Main St, Smalltown, CO 12345"

        // Print the fake data
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Address: " + address);
    }
}

