package demo.phone.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Phone {
    @Id
    @GeneratedValue
    private long id;
    @Column(unique = true, nullable = false)
    private String serialNumber;
    @Column(unique = true, nullable = false)
    private String number;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(nullable = false)
    private boolean stolen;

    public Phone() {}

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", serialNumber='" + serialNumber + '\'' +
                ", number='" + number + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", stolen=" + stolen +
                '}';
    }

    public Phone(String serialNumber, String number, String firstName, String lastName, boolean stolen) {
        this.serialNumber = serialNumber;
        this.number = number;
        this.firstName = firstName;
        this.lastName = lastName;
        this.stolen = stolen;
    }

    public Phone(Builder builder) {
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

        public Phone build() {
            return new Phone(this);
        }
    }
}