package com.targetindia.miniproject.model;


import com.targetindia.miniproject.utils.EmailValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {
    private Integer id;
    private String name;
    private String city;
    private String email;
    private String phone;

    public Customer(Integer id, String name, String city, String email, String phone) throws ModelException {
        this.id = id;
        this.name = name;
        this.city = city;
        this.phone = phone;

        if(EmailValidator.isValidEmail(email)) {
            this.email = email;
        } else {
            throw new ModelException("Invalid email entered: " + email);
        }
    }
}
