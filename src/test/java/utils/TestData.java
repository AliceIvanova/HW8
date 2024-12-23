package utils;

import com.github.javafaker.Faker;

import java.text.SimpleDateFormat;

public class TestData {

 public String dateOfDeparture() {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
   Faker faker = new Faker();
  String dob = sdf.format(faker.date());
   System.out.println(dob);
   return dob;
  }
}