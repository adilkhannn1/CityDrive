package citydrive.platform.user.mapper;

import citydrive.platform.user.dto.request.UserProfileRequest;
import citydrive.platform.user.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserProfileUpdateMapper {
    public UserProfileRequest toDto(UserEntity userEntity){
        return new UserProfileRequest(
                userEntity.getFullName(),
                userEntity.getCity(),
                userEntity.getBirthDate(),
                userEntity.getGender(),
                userEntity.getAvatarUrl(),
                userEntity.getLanguage()
        );
    }

}

