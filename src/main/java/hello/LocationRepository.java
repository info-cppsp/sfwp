package hello;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, UUID>{

}
