package dataFactory;

import dataObjectsModel.RegistrationModel;
import utilities.JavaHelpers;

public class RegistrationData {

    /**
     * Declared variables and create objects
     */
     RegistrationModel registrationModel = new RegistrationModel();

     String timeStamp =new JavaHelpers().timeStamp();
     String name = "Cool";
     String password = "Ab1234567";

    /**
     * Set registration data
     * @return RegistrationHelloTeams object
     */
    public RegistrationModel getRegistrationModelData(){

        registrationModel.setNameTextBox(name);
        registrationModel.setEmailTextBox("Cool"+timeStamp+"@helloteams.io");
        registrationModel.setPasswordTextBox(password);
        registrationModel.setConfirmPasswordTextBox(password);
        return registrationModel;
    }

    /**
     * Set registration data with invalid Domain
     * @return RegistrationHelloTeams object
     */
    public RegistrationModel getInvalidDomainData(){

        registrationModel.setNameTextBox(name);
        registrationModel.setEmailTextBox("Cool" +timeStamp+ "@helloteams.io");
        registrationModel.setPasswordTextBox(password);
        registrationModel.setConfirmPasswordTextBox(password);
        return registrationModel;
    }

    /**
     * Set registration data with already taken email
     * @return RegistrationHelloTeams object
     */
    public RegistrationModel getAlreadyTakenEmailData(){
        registrationModel.setNameTextBox("piyush");
        registrationModel.setEmailTextBox("piyush+3@helloteams.io");
        registrationModel.setPasswordTextBox(password);
        registrationModel.setConfirmPasswordTextBox(password);
        return registrationModel;
    }

    /**
     * Set registration data with only digit password
     * @return RegistrationHelloTeams object
     */
    public RegistrationModel getDigitalPasswordData(){
        registrationModel.setNameTextBox(name);
        registrationModel.setEmailTextBox("Cool"+timeStamp+"@helloteams.io");
        registrationModel.setPasswordTextBox("123456789");
        registrationModel.setConfirmPasswordTextBox("123456789");
        return registrationModel;
    }

    /**
     * Set registration data with digit and lowercase password
     * @return RegistrationHelloTeams object
     */
    public RegistrationModel getDigitalLowerCasePasswordData(){
        registrationModel.setNameTextBox(name);
        registrationModel.setEmailTextBox("Cool"+timeStamp+"@helloteams.io");
        registrationModel.setPasswordTextBox("ab123456789");
        registrationModel.setConfirmPasswordTextBox("ab123456789");
        return registrationModel;
    }

    /**
     * Set registration data with digit and uppercase password
     * @return RegistrationHelloTeams object
     */
    public RegistrationModel getDigitalUpperCasePasswordData(){
        registrationModel.setNameTextBox(name);
        registrationModel.setEmailTextBox("Cool"+timeStamp+"@helloteams.io");
        registrationModel.setPasswordTextBox("AB123456789");
        registrationModel.setConfirmPasswordTextBox("AB123456789");
        return registrationModel;
    }

    /**
     * Set registration data with letters password
     * @return RegistrationHelloTeams object
     */
    public RegistrationModel getUpperLowerCasePasswordData(){
        registrationModel.setNameTextBox(name);
        registrationModel.setEmailTextBox("Cool"+timeStamp+"@helloteams.io");
        registrationModel.setPasswordTextBox("Abcdef");
        registrationModel.setConfirmPasswordTextBox("Abcdef");
        return registrationModel;
    }



}
