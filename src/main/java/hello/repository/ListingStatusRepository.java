package hello.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hello.model.ListingStatus;

@Repository
public interface ListingStatusRepository extends CrudRepository<ListingStatus, Integer> {

}
