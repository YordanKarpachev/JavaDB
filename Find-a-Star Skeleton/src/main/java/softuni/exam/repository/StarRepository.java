package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.Star;
import softuni.exam.models.entity.StartTypeEnum;

import java.util.List;
import java.util.Optional;

@Repository
public interface StarRepository extends JpaRepository<Star, Long>{

    Optional<Star> findByName(String name);

    Optional<Star> findById (Long id);

  //  @Query(
    //        value = "SELECT * FROM Star u WHERE StarType = 'RED_GIANT' and ",
     //       nativeQuery = true)
    List<Star> findAllByStarType(StartTypeEnum e);
}
