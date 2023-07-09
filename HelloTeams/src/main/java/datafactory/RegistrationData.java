package datafactory;


import dataobjects.Registration;
import utilities.JavaHelpers;

public class RegistrationData
{
    /**
     * Declared variables and create objects
     */
    Registration data = new Registration();
    String timeStamp = new JavaHelpers().timeStamp();
    String password = "Ab123456";
    String name = "Smith";

    /**
     * Set registration data
     * @return RegistrationHelloTeams object
     */
    public Registration getRegistrationData()
    {
        data.setNameTextBox(name);
        data.setEmail("smith+"+timeStamp+"@helloteams.io");
        data.setPassword(password);                    //must be 8 characters
        data.setConfirmPassword(password);
        return data;
    }

    /**
     * Set registration data with invalid Domain
     * @return RegistrationHelloTeams object
     */
    public Registration getInvalidDomainData()
    {
        data.setNameTextBox(name);
        data.setEmail("smith+" + timeStamp+ "@helloteam.io");
        data.setPassword(password);
        data.setConfirmPassword(password);
        return data;
    }

    /**
     * Set registration data with already taken email
     * @return RegistrationHelloTeams object
     */
    public Registration getAlreadyTakenEmailData()
    {
        data.setNameTextBox("piyush");
        data.setEmail("piyush+3@helloteams.io");
        data.setPassword(password);
        data.setConfirmPassword(password);
        return data;
    }

    /**
     * Set registration data with only digit password
     * @return RegistrationHelloTeams object
     */
    public Registration getDigitPasswordData()
    {
        data.setNameTextBox(name);
        data.setEmail("smith+"+timeStamp+"@helloteams.io");
        data.setPassword("12345678");
        data.setConfirmPassword("12345678");
        return data;
    }

    /**
     * Set registration data with digit and lowercase password
     * @return RegistrationHelloTeams object
     */
    public Registration getDigitLowerCaseData()
    {
        data.setNameTextBox(name);
        data.setEmail("smith+"+timeStamp+"@helloteams.io");
        data.setPassword("a12345678");
        data.setConfirmPassword("a12345678");
        return data;
    }

    /**
     * Set registration data with digit and uppercase password
     * @return RegistrationHelloTeams object
     */
    public Registration getDigitUpperCaseData()
    {
        data.setNameTextBox(name);
        data.setEmail("smith+"+timeStamp+"@helloteams.io");
        data.setPassword("A12345678");
        data.setConfirmPassword("A12345678");
        return data;
    }

    /**
     * Set registration data with letters password
     * @return RegistrationHelloTeams object
     */
    public Registration getUpperLowerCaseData()
    {
        data.setNameTextBox(name);
        data.setEmail("smith+"+timeStamp+"@helloteams.io");
        data.setPassword("Abcdefgh");
        data.setConfirmPassword("Abcdefgh");
        return data;
    }
}
