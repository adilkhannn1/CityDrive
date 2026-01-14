package citydrive.platform.news.repository;

import citydrive.platform.news.entity.NewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<NewsEntity, Long> {

}
