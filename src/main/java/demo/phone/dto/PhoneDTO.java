package demo.phone.dto;

import com.google.common.base.Function;
import demo.phone.domain.Phone;

import java.util.List;

import static com.google.common.collect.FluentIterable.from;

public class PhoneDTO {

    private long id;
    private String serialNumber;
    private String number;
    private String firstName;
    private String lastName;
    private boolean stolen;

    @Override
    public String toString() {
        return "PhoneDTO{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", number='" + number + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", stolen=" + stolen +
                '}';
    }

    public PhoneDTO() {}

    private PhoneDTO(Builder builder) {
        setSerialNumber(builder.serialNumber);
        setNumber(builder.number);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setStolen(builder.stolen);
    }

    public static Builder newBuilder(String serialNumber, String number, boolean stolen) {
        return new Builder(serialNumber, number, stolen);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isStolen() {
        return stolen;
    }

    public void setStolen(boolean stolen) {
        this.stolen = stolen;
    }


    public static final class Builder {
        private final String serialNumber;
        private final String number;
        private String firstName;
        private String lastName;
        private final boolean stolen;

        public Builder(String serialNumber, String number, boolean stolen) {
            this.serialNumber = serialNumber;
            this.number = number;
            this.stolen = stolen;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public PhoneDTO build() {
            return new PhoneDTO(this);
        }

    }

    public Phone toPhone(){
        return new Phone(serialNumber,number, firstName,lastName,stolen);

    }

    public PhoneDTO(Phone phone){
        id = phone.getId();
        serialNumber = phone.getSerialNumber();
        number = phone.getNumber();
        firstName = phone.getFirstName();
        lastName = phone.getLastName();
        stolen = phone.isStolen();

    }

    public static List<PhoneDTO> toPhoneList(List<Phone> phoneList){
        return from(phoneList).transform(new Function<Phone, PhoneDTO>() {
            @Override
            public PhoneDTO apply(Phone phone) {
                return new PhoneDTO(phone);
            }

        }).toList();
    }
}