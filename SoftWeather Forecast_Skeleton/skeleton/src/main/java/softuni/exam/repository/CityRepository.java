package softuni.exam.repository;

// TODO:

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {


}
