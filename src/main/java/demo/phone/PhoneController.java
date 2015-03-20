package demo.phone;

import demo.phone.domain.Phone;
import demo.phone.dto.PhoneDTO;
import demo.phone.exception.PhoneAlreadyExistsException;
import demo.phone.exception.PhoneNotFoundException;
import demo.phone.repository.PhoneRepository;
import demo.phone.service.AnonymeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

import static demo.phone.dto.PhoneDTO.newBuilder;
import static demo.phone.dto.PhoneDTO.toPhoneList;

@RestController
@RequestMapping("/phone")
public class PhoneController {
    @Autowired
    private PhoneRepository phoneRepository;
    @Autowired
    private AnonymeService anonymeService;

    @PostConstruct
    public void setUp(){
        // test phone 1
        String serial1 = "123456789";
        String num1 = "0798653214";
        String firstName1 = "John";
        String lastName1 = "Doe";
        boolean stolen1 = true;
        // test phone 2
        String serial2 = "987654321";
        String num2 = "0698653214";
        String firstName2 = "Juju";
        String lastName2 = "dePomme";
        boolean stolen2 = true;

        PhoneDTO.Builder phoneDTOBuilder = newBuilder(serial1, num1, stolen1)
            .withFirstName(firstName1)
            .withLastName(lastName1);
        phoneRepository.save(phoneDTOBuilder.build().toPhone());

        phoneDTOBuilder = newBuilder(serial2, num2, stolen2)
            .withFirstName(firstName2)
            .withLastName(lastName2);
        phoneRepository.save(phoneDTOBuilder.build().toPhone());
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<PhoneDTO> getPhones(){
        System.out.println("Get all");
        List<Phone> stolens = phoneRepository.findByStolen(true);

        return anonymeService.anonymisedList(toPhoneList(stolens));
    }

    @RequestMapping(value = "/{phoneId}",method = RequestMethod.GET)
    public PhoneDTO getPhone(@PathVariable long phoneId){
        System.out.println("Get one");
        System.out.println("ID : " + phoneId);

        Phone phoneById = phoneRepository.findById(phoneId);

        if (phoneById == null)
            throw new PhoneNotFoundException("Phone not found");

        return anonymeService.anonymised(new PhoneDTO(phoneById));
    }

    @RequestMapping(method = RequestMethod.POST)
    public PhoneDTO createPhone(@RequestBody PhoneDTO phoneDTOReceived){
        System.out.println("Post");
        System.out.println("received :" + phoneDTOReceived);
        Phone phone = phoneDTOReceived.toPhone();

        Phone searchedPhone = phoneRepository.findBySerialNumber(phone.getSerialNumber());

        if (searchedPhone != null){ // phone serial number already in DB
            throw new PhoneAlreadyExistsException("Phone serial number already exists");
        }

        phone = phoneRepository.save(phone);

        System.out.println("inserted :"+phone);
        return anonymeService.anonymised(new PhoneDTO(phone));
    }

    @RequestMapping(value = "/{phoneId}", method = RequestMethod.PUT)
    public PhoneDTO updatePhone(@PathVariable long phoneId, @RequestBody PhoneDTO phoneDTO){
        System.out.println("Put");
        //todo verification

        //todo retourner code erreur
        Phone s = phoneRepository.findById(phoneId);
        s.setStolen(phoneDTO.isStolen());
        Phone save = phoneRepository.save(s);

        return new PhoneDTO(save);
    }

}
