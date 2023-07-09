package datafactory.gonnaorder.admin;

import dataobjects.gonnaorder.admin.Offer;
import enums.gonnaorder.DiscountType;
import enums.gonnaorder.OptionGroupType;
import utilities.Constants;
import utilities.JavaHelpers;

public class OfferData {

    /**
     * Generate test data for Offer information
     *
     * @return Offer object
     */
    public Offer getOfferData()
    {
        Offer info = new Offer();
        JavaHelpers java = new JavaHelpers();
        String randomString = "Automation" + java.getTimeStamp("yyyyMMdd_HHmmss");
        info.setName(randomString);
        info.setShortDescription("Created via Automation");
        info.setLongDescription("Created Long desc via Automation");
        info.setCategory(Constants.StoreCategory);
        info.setPriceDecimal("5");
        info.setPriceFloat("55");
        info.setPriceDescription("Automation");
        info.setDiscountType(DiscountType.None);
        info.setMonitoryDecimalDiscount("2");
        info.setMonitoryFloatDiscount("55");
        info.setPercentileDiscountValue("10%");
        info.setSellable(true);
        info.setOptionGroupName("Automation Option Group" + randomString);
        info.setOptionGroupType(OptionGroupType.ExactlyOne);
        return info;
    }

    /**
     * Generate edit test data for Offer information
     *
     * @return Offer object
     */
    public Offer getOfferEditData()
    {
        Offer info = new Offer();
        JavaHelpers java = new JavaHelpers();
        String randomString = "AutomationEdit" + java.getTimeStamp("yyyyMMdd_HHmmss");
        info.setName(randomString);
        info.setShortDescription("Edited via Automation");
        info.setLongDescription("Edited Long desc via Automation");
        info.setCategory(Constants.StoreCategory);
        info.setPriceDecimal("5");
        info.setPriceFloat("00");
        info.setPriceDescription("Edit Automation");
        info.setDiscountType(DiscountType.None);
        info.setMonitoryDecimalDiscount("2");
        info.setMonitoryFloatDiscount("00");
        info.setPercentileDiscountValue("20%");
        info.setSellable(true);
        info.setOptionGroupName("Edited Option Group" + randomString);
        info.setOptionGroupType(OptionGroupType.ExactlyOne);
        return info;
    }

    public Offer getOfferPriceVariantData()
    {
        Offer info = new Offer();
        JavaHelpers java = new JavaHelpers();
        String randomString = "PriceVariant" + java.getTimeStamp("yyyyMMdd_HHmmss");
        info.setPriceDescription(randomString);
        info.setPriceDecimal("200");
        info.setPriceFloat("00");
        info.setPercentileDiscountValue("5%");
        info.setMonitoryDecimalDiscount("100");
        info.setMonitoryFloatDiscount("00");
        info.setDiscountType(DiscountType.None);
        info.setSellable(true);
        return info;
    }

    public Offer getAddOptionData()
    {
        Offer info = new Offer();
        JavaHelpers java = new JavaHelpers();
        String randomString = "Option" + java.getTimeStamp("yyyyMMdd_HHmmss");
        info.setName(randomString);
        info.setShortDescription("Add option via Automation");
        info.setPriceDecimal("5");
        info.setPriceFloat("00");
        info.setSellable(true);
        return info;
    }

}