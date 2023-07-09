package dataFactory;

import dataObjectsModel.RegistrationModel;
import utilities.JavaHelpers;

public class RegistrationData {


    /**
     * Declared variable and create objects
     */
    RegistrationModel registrationModel = new RegistrationModel();

    String timeStamp = new JavaHelpers().timeStamp();
    String firstName = "abc";
    String lastName = "xyz";
    String password = "Ab@1234567";
    String countryName = "Iceland";
    String phoneNum = "1234567890";

    /**
     *  Set registration data
     * @return RegistrationGonnaOrder object
     */
    public RegistrationModel getRegistrationModelData(){
        registrationModel.setEmailTextBox(firstName+timeStamp+"@gonnaorder.com");
        registrationModel.setPasswordTextBox(password);   //must be 8 character
        registrationModel.setConfirmPasswordTextBox(password);
        registrationModel.setFirstNameTextBox(firstName);
        registrationModel.setLastNameTextBox(lastName);
        registrationModel.setCountryNameDropdown(countryName);
        registrationModel.setPhoneNumberTextBox(phoneNum);
        return registrationModel;
    }

    /**
     * Set registration data with invalid Domain
     * @return RegistrationGonnaOrder object
     */
    public RegistrationModel getInvlaidDomainData(){
        registrationModel.setEmailTextBox(firstName + timeStamp+ "@gonnaorderss.com");
        registrationModel.setPasswordTextBox(password);   //must be 8 character
        registrationModel.setConfirmPasswordTextBox(password);
        registrationModel.setFirstNameTextBox(firstName);
        registrationModel.setLastNameTextBox(lastName);
        registrationModel.setCountryNameDropdown(countryName);
        registrationModel.setPhoneNumberTextBox(phoneNum);
        return registrationModel;
    }

    /**
     * Set registration data with already taken email
     * @return RegistrationGonnaOrder object
     */
    public RegistrationModel getAlreadyTakenEmailData(){
        registrationModel.setEmailTextBox(firstName+timeStamp+"@gonnaorder.com");
        registrationModel.setPasswordTextBox(password);   //must be 8 character
        registrationModel.setConfirmPasswordTextBox(password);
        registrationModel.setFirstNameTextBox(firstName);
        registrationModel.setLastNameTextBox(lastName);
        registrationModel.setCountryNameDropdown(countryName);
        registrationModel.setPhoneNumberTextBox(phoneNum);
        return registrationModel;
    }

    /**
     * Set registration data with only digit password
     * @return RegistrationGonnaOrder object
     */
    public RegistrationModel getDigitalPasswordData(){
        registrationModel.setEmailTextBox(firstName+timeStamp+"@gonnaorder.com");
        registrationModel.setPasswordTextBox("123456789");   //must be 8 character
        registrationModel.setConfirmPasswordTextBox("123456789");
        registrationModel.setFirstNameTextBox(firstName);
        registrationModel.setLastNameTextBox(lastName);
        registrationModel.setCountryNameDropdown(countryName);
        registrationModel.setPhoneNumberTextBox(phoneNum);
        return registrationModel;
    }

    /**
     * Set registration data with digit and lowercase password
     * @return RegistrationGonnaOrder object
     */
    public RegistrationModel getDigitalLowerCasePasswordData(){
        registrationModel.setEmailTextBox(firstName+timeStamp+"@gonnaorder.com");
        registrationModel.setPasswordTextBox("ab123456789");   //must be 8 character
        registrationModel.setConfirmPasswordTextBox("ab123456789");
        registrationModel.setFirstNameTextBox(firstName);
        registrationModel.setLastNameTextBox(lastName);
        registrationModel.setCountryNameDropdown(countryName);
        registrationModel.setPhoneNumberTextBox(phoneNum);
        return registrationModel;
    }

    /**
     * Set registration data with digit and uppercase password
     * @return RegistrationGonnaOrder object
     */
    public RegistrationModel getDigitalUpperCasePasswordData(){
        registrationModel.setEmailTextBox(firstName+timeStamp+"@gonnaorder.com");
        registrationModel.setPasswordTextBox("AB123456789");   //must be 8 character
        registrationModel.setConfirmPasswordTextBox("AB123456789");
        registrationModel.setFirstNameTextBox(firstName);
        registrationModel.setLastNameTextBox(lastName);
        registrationModel.setCountryNameDropdown(countryName);
        registrationModel.setPhoneNumberTextBox(phoneNum);
        return registrationModel;
    }

    /**
     * Set registration data with letters password
     * @return RegistrationGonnaOrder object
     */
    public RegistrationModel getUpperLowerCasePasswordData(){
        registrationModel.setEmailTextBox(firstName+timeStamp+"@gonnaorder.com");
        registrationModel.setPasswordTextBox("Abcdefgh");   //must be 8 character
        registrationModel.setConfirmPasswordTextBox("Abcdefgh");
        registrationModel.setFirstNameTextBox(firstName);
        registrationModel.setLastNameTextBox(lastName);
        registrationModel.setCountryNameDropdown(countryName);
        registrationModel.setPhoneNumberTextBox(phoneNum);
        return registrationModel;
    }
    /**
     *  Set registration data with letters phoneNum
     *  @return RegistrationGonnaOrder object
     */
    public RegistrationModel getLettersPhoneNumberData(){
        registrationModel.setEmailTextBox(firstName+timeStamp+"@gonnaorder.com");
        registrationModel.setPasswordTextBox(password);   //must be 8 character
        registrationModel.setConfirmPasswordTextBox(password);
        registrationModel.setFirstNameTextBox(firstName);
        registrationModel.setLastNameTextBox(lastName);
        registrationModel.setCountryNameDropdown(countryName);
        registrationModel.setPhoneNumberTextBox("abcdefghijk");
        return registrationModel;
    }

    /**
     *  Set registration data with Digital and letters phoneNum
     *  @return RegistrationGonnaOrder object
     */
    public RegistrationModel getDigitalLettersPhoneNumberData(){
        registrationModel.setEmailTextBox(firstName+timeStamp+"@gonnaorder.com");
        registrationModel.setPasswordTextBox(password);   //must be 8 character
        registrationModel.setConfirmPasswordTextBox(password);
        registrationModel.setFirstNameTextBox(firstName);
        registrationModel.setLastNameTextBox(lastName);
        registrationModel.setCountryNameDropdown(countryName);
        registrationModel.setPhoneNumberTextBox("abc567890");
        return registrationModel;
    }



}
