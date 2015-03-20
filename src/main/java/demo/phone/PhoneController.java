package demo.phone;

import com.google.common.collect.FluentIterable;
import demo.phone.dto.PhoneDTO;
import demo.phone.repository.PhoneRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Benoit on 20/03/2015.
 */
@RestController
@RequestMapping("/phone")
public class PhoneController {
    private PhoneRepository phoneRepository;

    @RequestMapping(method = RequestMethod.GET)
    public List<PhoneDTO> getPhones(){
        return phoneRepository.findByStolen(true);
    }

    @RequestMapping(value = "/{phoneId}",method = RequestMethod.GET)
    public PhoneDTO getPhone(@PathVariable long phoneId){
        
        //todo verification non null
        
        PhoneDTO phoneDTO = phoneRepository.findById(phoneId);
        
        //todo retourner code erreur

        return phoneDTO;
    }

    @RequestMapping(method = RequestMethod.POST)
    public PhoneDTO createPhone(@RequestBody PhoneDTO phoneDTOReceived){
        //todo verification

        //todo retourner code erreur
        return phoneRepository.save(phoneDTOReceived);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public PhoneDTO updatePhone(@PathVariable long phoneId, @RequestBody boolean stolen){
        //todo verification

        //todo retourner code erreur
        return phoneRepository.save(phoneRepository.updateById(phoneId, stolen));
    }
}
