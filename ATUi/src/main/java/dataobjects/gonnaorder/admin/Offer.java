package dataobjects.gonnaorder.admin;

import enums.gonnaorder.DiscountType;
import enums.gonnaorder.OptionGroupType;

public class Offer {

    private String name;
    private String shortDescription;
    private String longDescription;
    private String category;
    private String priceDecimal;
    private String priceFloat;
    private String priceDescription;
    private DiscountType discountType;
    private OptionGroupType optionGroupType;
    private String monitoryDecimalDiscount;
    private String monitoryFloatDiscount;
    private String percentileDiscountValue;
    private String optionGroupName;
    private boolean sellable;

    public String getOptionGroupName() {
        return optionGroupName;
    }

    public void setOptionGroupName(String optionGroupName) {
        this.optionGroupName = optionGroupName;
    }

    public OptionGroupType getOptionGroupType() {
        return optionGroupType;
    }

    public void setOptionGroupType(OptionGroupType optionGroupType) {
        this.optionGroupType = optionGroupType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPriceDecimal() {
        return priceDecimal;
    }

    public void setPriceDecimal(String priceDecimal) {
        this.priceDecimal = priceDecimal;
    }

    public String getPriceFloat() {
        return priceFloat;
    }

    public void setPriceFloat(String priceFloat) {
        this.priceFloat = priceFloat;
    }

    public String getPriceDescription() {
        return priceDescription;
    }

    public void setPriceDescription(String priceDescription) {
        this.priceDescription = priceDescription;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    public String getMonitoryDecimalDiscount() {
        return monitoryDecimalDiscount;
    }

    public void setMonitoryDecimalDiscount(String monitoryDecimalDiscount) {
        this.monitoryDecimalDiscount = monitoryDecimalDiscount;
    }

    public String getMonitoryFloatDiscount() {
        return monitoryFloatDiscount;
    }

    public void setMonitoryFloatDiscount(String monitoryFloatDiscount) {
        this.monitoryFloatDiscount = monitoryFloatDiscount;
    }

    public String getPercentileDiscountValue() {
        return percentileDiscountValue;
    }

    public void setPercentileDiscountValue(String percentileDiscountValue) {
        this.percentileDiscountValue = percentileDiscountValue;
    }

    public boolean isSellable() {
        return sellable;
    }

    public void setSellable(boolean sellable) {
        this.sellable = sellable;
    }


}
