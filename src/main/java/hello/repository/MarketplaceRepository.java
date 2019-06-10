package hello.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import hello.model.Marketplace;

@Repository
public interface MarketplaceRepository extends CrudRepository<Marketplace, Integer> {

}
