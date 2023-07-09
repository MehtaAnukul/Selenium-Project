package dataobjects.gonnaorder.admin;

public class PayPalAndOtherPayment {
    private String PayPalMeLink;
    private String OtherPaymentLink;
    private String OtherPaymentProviderName;

    public String getPayPalMeLink() {
        return PayPalMeLink;
    }

    public void setPayPalMeLink(String payPalMeLink) {
        PayPalMeLink = payPalMeLink;
    }

    public String getOtherPaymentLink() {
        return OtherPaymentLink;
    }

    public void setOtherPaymentLink(String otherPaymentLink) {
        OtherPaymentLink = otherPaymentLink;
    }

    public String getOtherPaymentProviderName() {
        return OtherPaymentProviderName;
    }

    public void setOtherPaymentProviderName(String otherPaymentProviderName) {
        OtherPaymentProviderName = otherPaymentProviderName;
    }

}

