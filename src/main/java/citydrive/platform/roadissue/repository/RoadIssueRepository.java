package citydrive.platform.roadissue.repository;

import citydrive.platform.roadissue.entity.RoadIssueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoadIssueRepository extends JpaRepository<RoadIssueEntity, Long> {

}
