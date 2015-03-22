package net.zburak.poc.domain;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by buraq
 */
@Document(collection = "Users")
public class User extends BaseEntity{

    public User(String username, String name, String surname, String phone) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
    }

    public User() {
    }

    @NotNull
    @NotBlank
    @Size(min = 3, max = 30)
    @Indexed(unique = true)
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 30)
    private String name;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
    private String surname;

    @Pattern(regexp="\\([0-9]{3}\\)\\-[0-9]{7}")
    @NotBlank
    @Indexed(unique = true)
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
