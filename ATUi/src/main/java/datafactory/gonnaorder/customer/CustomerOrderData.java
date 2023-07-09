package datafactory.gonnaorder.customer;

import dataobjects.gonnaorder.customer.CustomerOrder;
import utilities.Constants;
import utilities.JavaHelpers;

public class CustomerOrderData {
    /**
     * Generate test data for Customer Order information
     *
     * @return Customer object
     */
    public CustomerOrder getCustomerDetailsData() {
        JavaHelpers java = new JavaHelpers();
        String randomString = "Automation" + java.getTimeStamp("yyyyMMdd_HHmmss");
        CustomerOrder info = new CustomerOrder();
        info.setSpecialNote("Automation Special Note " + randomString);
        info.setTable(Constants.StoreTable);
        info.setStreetAddress("Nieuwe Spiegelstraat72");
        info.setTown("Amsterdam");
        info.setZipCode("32577");
        info.setEmail(randomString + "@yopmail.com");
        info.setPhone("123456789");
        info.setName("QA Automation");
        info.setCardNumber("4000 0035 6000 0008");
        info.setExpDate("02/22");
        info.setCvc("222");
        info.setBankName("ASN Bank");
        return info;
    }

    /**
     * Generate test EmailAddress for Submitted Order Copy
     *
     * @return Store object
     */
    public CustomerOrder getEmailData() {
        CustomerOrder info = new CustomerOrder();
        JavaHelpers java = new JavaHelpers();
        info.setEmailAddress("auto" + java.getTimeStamp("yyMMddHHmmss") + "@mailinator.com");
        return info;
    }
}