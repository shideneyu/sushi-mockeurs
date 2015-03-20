package demo.phone.dto;

import javax.persistence.*;

/**
 * Created by Benoit on 20/03/2015.
 */
@Entity
public class PhoneDTO {
    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true, nullable = false)
    private String serialNumber;
    @Column(unique = true, nullable = false)
    private String number;
    @Column
    private String firstName; //(à remplacer par des ****  via l'api ! )
    @Column
    private String lastName; //(à remplacer par des **** via l'api ! )
    @Column(nullable = false)
    private boolean stolen;


    private PhoneDTO(Builder builder) {
        setId(builder.id);
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
        private long id;
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
}