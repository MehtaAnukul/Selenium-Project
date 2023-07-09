package dataobjects.gonnaorder.admin;

public class DeliveryAtAddress {

    private String deliveryAmountDecimal;
    private String deliveryAmountFloat;
    private boolean addressDeliveryAutocomplete;

    public String getDeliveryAmountDecimal() {
        return deliveryAmountDecimal;
    }

    public void setDeliveryAmountPriceDecimal(String deliveryAmountDecimal) {
        this.deliveryAmountDecimal = deliveryAmountDecimal;
    }
    public String getDeliveryAmountFloat() {
        return deliveryAmountFloat;
    }

    public void setDeliveryAmountFloat(String deliveryAmountFloat) {
        this.deliveryAmountFloat = deliveryAmountFloat;
    }

    public boolean isAddressDeliveryAutocomplete() {
        return addressDeliveryAutocomplete;
    }

    public void setAddressDeliveryAutocomplete(boolean addressDeliveryAutocomplete) {
        this.addressDeliveryAutocomplete = addressDeliveryAutocomplete;
    }

}

