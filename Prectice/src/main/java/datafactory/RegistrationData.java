package datafactory;

import dataobjects.Registration;
import utilities.JavaHelpers;

public class RegistrationData {
    /**
     * Get registration data
     * @return Registration object
     */

    public Registration getRegistrationData()
    {
        Registration data = new Registration();
        String timeStamp = new JavaHelpers().timeStamp();

        data.setFirstName("FirstName" + timeStamp);
        data.setLastName("LastName" + timeStamp);
        data.setEmail("email" + timeStamp + "@test.com");
        data.setPassword("12345");
        data.setConfirmPassword("12345");
        return data;
    }

}
