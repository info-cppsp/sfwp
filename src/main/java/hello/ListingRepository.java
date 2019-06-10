package hello;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface ListingRepository extends CrudRepository<Listing, UUID> {

}
