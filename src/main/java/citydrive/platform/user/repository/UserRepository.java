package citydrive.platform.user.repository;

import citydrive.platform.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByPhoneNumber(String phoneNumber);
}
