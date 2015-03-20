package demo.phone.repository;

import demo.phone.domain.Phone;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface PhoneRepository extends CrudRepository<Phone, Long>{

    public Phone findById(Long id);
    public Phone findBySerialNumber(String serialNumber);
    public List<Phone> findByStolen(boolean stolen);

//    @Query(value = "update Phone p set stolen = :stolen where id = :id")
//    public Phone updateById(@Param("id") long id, @Param("stolen") boolean stolen);
}