package demo.phone.service;

import com.google.common.base.Function;
import demo.phone.dto.PhoneDTO;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.google.common.collect.FluentIterable.from;

@Service
public class AnonymeService {

    public  PhoneDTO anonymised(PhoneDTO phoneDTO){
        String anom =  "**********";
        phoneDTO.setFirstName(anom);
        phoneDTO.setLastName(anom);
        return phoneDTO;
    }


    public  List<PhoneDTO> anonymisedList(List<PhoneDTO> phoneList){
        return from(phoneList).transform(new Function<PhoneDTO, PhoneDTO>() {
            @Override
            public PhoneDTO apply(PhoneDTO phoneDTO) {
                return anonymised(phoneDTO);
            }

        }).toList();
    }
}
