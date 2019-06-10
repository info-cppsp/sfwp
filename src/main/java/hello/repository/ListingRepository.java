package hello.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hello.model.Listing;

@Repository
public interface ListingRepository extends CrudRepository<Listing, UUID> {

}
