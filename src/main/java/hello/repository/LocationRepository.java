package hello.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hello.model.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, UUID> {

}
