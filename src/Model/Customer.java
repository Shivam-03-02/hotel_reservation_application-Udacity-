package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {
    String firstName;
    String lastName;
    String email;


    public Customer(String firstName,String lastName,String email) {
        this.firstName=firstName;
        this.lastName=lastName;

            validateEmail(email);
            this.email = email;


    }

    public void validateEmail(String email) {
        if(!Pattern.matches("[a-z0-9]+[@][a-z]+[.][a-z]+",email)){
            throw new IllegalArgumentException("Invalid Email");
        }
    }

    public String getCustomerEmail () {
        return email;
    }
    @Override
    public String toString() {
        return "First Name is:" + firstName + " Last Name:" + lastName;
    }
}
