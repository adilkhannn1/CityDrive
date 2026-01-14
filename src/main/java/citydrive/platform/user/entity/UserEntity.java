package citydrive.platform.user.entity;
import citydrive.platform.roadissue.entity.RoadIssueEntity;
import citydrive.platform.user.enums.City;
import citydrive.platform.user.enums.Gender;
import citydrive.platform.user.enums.Language;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 4, max = 30)
    @Pattern(regexp = "^[A-Za-zА-Яа-яёЁ ]+$")
    private String fullName;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Gender gender;


    private String email;


    private Language language;

//    @NotBlank(message = "Phone number cannot be empty")
//    @Pattern(regexp = "^\\+[1-9]\\d{7,14}$")
//    private String phoneNumber;


    private String avatarUrl;

    @NotNull(message = "City cannot be Null")
    @Enumerated(EnumType.STRING)
    private City city;

    @NotNull(message = "Birth date is required")
    @Past(message = "Birthdate must be in the past")
    private LocalDate birthDate;

    @NotNull(message = "Password is required")
    private String password;

    @OneToMany(mappedBy = "creator")
    private List<RoadIssueEntity> roadIssues;

    public UserEntity(){

    }

    public UserEntity(
            String fullName,
            Gender gender,
            String phoneNumber,
            City city,
            LocalDate birthDate,
            String password
    ){
        this.fullName= fullName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.birthDate = birthDate;
        this.password = password;
    }

    //Getters
    public String getFullName() {
        return fullName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public City getCity() {
        return city;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public Language getLanguage() {
        return language;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    //Setters
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}

