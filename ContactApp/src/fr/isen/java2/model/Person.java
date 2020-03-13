package fr.isen.java2.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a Person.
 */
public class Person {

	private int idPerson;
    private final StringProperty lastName;
    private final StringProperty firstName;
    private final StringProperty nickName;
    private final StringProperty phoneNumber;
    private final StringProperty address;
    private final StringProperty emailAddress;
    private final StringProperty birthDate;

    /**
     * Default constructor.
     */
    public Person() {
        this(-1, null, null, null, null, null, null, null);
    }

    /**
     * Constructor with some initial data.
     *
     * @param lastName
     * @param firstName
     */
    public Person(int idPerson, String lastName, String firstName, String nickName, String phoneNumber, String address, String emailAddress, String birthDate) {
        this.idPerson = idPerson;
    	this.firstName = new SimpleStringProperty(lastName);
        this.lastName = new SimpleStringProperty(firstName);
        this.nickName = new SimpleStringProperty(nickName);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.address = new SimpleStringProperty(address);
        this.emailAddress = new SimpleStringProperty(emailAddress);
        this.birthDate = new SimpleStringProperty(birthDate);
    }

    public int getIdPerson() {
    	return idPerson;
    }
    
    public void setIdPerson(int idPerson) {
        this.idPerson = idPerson;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }
    
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getNickName() {
        return nickName.get();
    }

    public void setNickName(String nickName) {
        this.nickName.set(nickName);
    }

    public StringProperty nickNameProperty() {
        return nickName;
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return address;
    }

    public String getEmailAddress() {
        return emailAddress.get();
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress.set(emailAddress);
    }

    public StringProperty emailAddressProperty() {
        return emailAddress;
    }

    public String getBirthDate() {
        return birthDate.get();
    }

    public void setBirthDate(String birthDate) {
        this.birthDate.set(birthDate);
    }

    public StringProperty birthDateProperty() {
        return birthDate;
    }

}