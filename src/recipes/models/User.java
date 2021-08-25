package recipes.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank
    @Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
    private String email;

    @Column
    @NotBlank
    /* This is only applied to JSON requests
    Server will not transmit hashed passwords */
    @Size(min = 8)
    private String password;


    @Column(name = "updatedAt")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String date;

    public User(){}

    public User(Long id, String email, String password, String date) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void hashPassword(PasswordEncoder encoder){
        this.password = encoder.encode(password);
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void updateDate(){
        this.date = LocalDateTime.now().toString();
    }


}