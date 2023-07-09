package datafactory.gonnaorder.admin;

import dataobjects.gonnaorder.admin.Address;
import dataobjects.gonnaorder.admin.Store;
import utilities.JavaHelpers;

public class StoreData {

    /**
     * Generate test data for store information
     *
     * @return Store object
     */
    public Store getStoreData() {
        JavaHelpers java = new JavaHelpers();
        Store info = new Store();

        String randomString = "Automation" + java.getTimeStamp("yyyyMMdd_HHmmss");
        info.setName(randomString);
        info.setDescription("Created via Automation");
        info.setAlias("automation" + java.getTimeStamp("yyyyMMddHHmmss"));
        info.setCountry("Netherlands");
        info.setPhoneNumber("3862159528");

        Address addressInfo = new Address();
        addressInfo.setAddressLine1("Nieuwe Spiegelstraat 72");
        addressInfo.setAddressLine2("1017 DH");
        addressInfo.setCity("Amsterdam");
        addressInfo.setRegion("Netherlands");
        addressInfo.setPostCode("32577");

        info.setAddress(addressInfo);

        return info;
    }

}
