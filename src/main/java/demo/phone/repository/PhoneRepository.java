package demo.phone.repository;

        import demo.phone.dto.PhoneDTO;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.data.repository.CrudRepository;
        import org.springframework.data.repository.query.Param;

        import java.util.List;

@org.springframework.stereotype.Repository
public interface PhoneRepository extends CrudRepository<PhoneDTO, Long>{

    public PhoneDTO findById(Long id);
    public PhoneDTO findBySerialNumber(String serialNumber);
    public List<PhoneDTO> findByStolen(boolean stolen);

    @Query(value = "update PhoneDTO p set p.stolen = :stolen where p.serialNumber = :serialNumber")
    public PhoneDTO updateById(@Param("serialNumber") long serialNumber, @Param("stolen") boolean stolen);
}