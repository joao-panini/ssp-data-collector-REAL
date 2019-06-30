package repository;

import entity.WomanViolenceIndicatorsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WomanViolenceIndicatorsRepository extends CrudRepository<WomanViolenceIndicatorsEntity,Long> {



}
