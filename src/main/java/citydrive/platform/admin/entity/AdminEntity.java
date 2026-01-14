package citydrive.platform.admin.entity;

import citydrive.platform.roadissue.entity.RoadIssueEntity;
import citydrive.platform.user.enums.City;
import citydrive.platform.user.enums.Gender;
import citydrive.platform.user.enums.Language;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

@Entity
@Table(name = "admins")
public class AdminEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 4, max = 30)
    @Pattern(regexp = "^[A-Za-zА-Яа-яёЁ ]+$")
    private String fullName;
    private String email;
    @NotNull(message = "Password is required")
    private String password;

    public AdminEntity(){

    }

    public AdminEntity(
            String fullName,
            String password
    ){
        this.fullName= fullName;
        this.password = password;
    }

    //Getters
    public String getFullName() {
        return fullName;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public Long getId() {
        return id;
    }

    //Setters
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

}
