package datafactory.gonnaorder.admin;

import dataobjects.gonnaorder.admin.PayPalAndOtherPayment;
import utilities.JavaHelpers;

public class PayPalAndOtherPaymentData {

    /**
     * Generate test data for register user information
     *
     * @return Store object
     */
    public PayPalAndOtherPayment getPayPalAndOtherPaymentData() {
        JavaHelpers java = new JavaHelpers();
        PayPalAndOtherPayment info = new PayPalAndOtherPayment();
        info.setPayPalMeLink("GonnaOrder");
        return info;
    }

}




