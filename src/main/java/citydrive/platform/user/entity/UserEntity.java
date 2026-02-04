package citydrive.platform.user.entity;
import citydrive.platform.roadissue.entity.RoadIssueEntity;
import citydrive.platform.user.enums.City;
import citydrive.platform.user.enums.Gender;
import citydrive.platform.user.enums.Language;
import citydrive.platform.user.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Entity
@Table(name = "users")
@Data
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

    @Enumerated(EnumType.STRING)
    private Role role;

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
            String email,
            String fullName,
            Gender gender,
            City city,
            LocalDate birthDate,
            String password
    ){
        this.email = email;
        this.fullName= fullName;
        this.gender = gender;
        this.city = city;
        this.birthDate = birthDate;
        this.password = password;
    }

}

