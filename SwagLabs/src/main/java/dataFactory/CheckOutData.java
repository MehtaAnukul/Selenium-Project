package dataFactory;

import dataObjectsModel.CheckOutModel;
import utilities.JavaHelpers;

public class CheckOutData {

    /**
     * Declared variable and create objects
     */

    CheckOutModel checkOutModel = new CheckOutModel();

    // String timeStamp = new JavaHelpers().timeStamp();
    String firstName = "abc";
    String lastName = "xyz";
    String zipCode = "1234";

    /**
     * Set CheckOut Data
     */
    public CheckOutModel getCheckOutModel() {
        checkOutModel.setFirstNameTextBox(firstName);
        checkOutModel.setLastNameTextBox(lastName);
        checkOutModel.setZipCodeTextBox(zipCode);
        return checkOutModel;
    }

    /**
     * Set CheckOut details with only spacial & junk character
     */
    public CheckOutModel getSpacialAndJunkCharacterData() {
        checkOutModel.setFirstNameTextBox("@##$#(*$**%&%&%&^%^%^%");
        checkOutModel.setLastNameTextBox("&*#&$&&^%^%^");
        checkOutModel.setZipCodeTextBox("#&*$&$&%%");
        return checkOutModel;
    }

    /**
     * Set CheckOut All field blank
     */
    public CheckOutModel getAllFieldBlank() {
        checkOutModel.setFirstNameTextBox("");
        checkOutModel.setLastNameTextBox("");
        checkOutModel.setZipCodeTextBox("");
        return checkOutModel;
    }

    /**
     * Set CheckOut details with only firstName
     */
    public CheckOutModel getValidFirstName() {
        checkOutModel.setFirstNameTextBox(firstName);
        checkOutModel.setLastNameTextBox("");
        checkOutModel.setZipCodeTextBox("");
        return checkOutModel;
    }

    /**
     * Set CheckOut Details with valid firstName and lastName
     * And further field blank
     */
    public CheckOutModel getValidFirstAndLastName() {
        checkOutModel.setFirstNameTextBox(firstName);
        checkOutModel.setLastNameTextBox(lastName);
        checkOutModel.setZipCodeTextBox("");
        return checkOutModel;
    }

    /**
     * Set CheckOut Details with valid firstName and lastName
     * And further field blank
     */
    public CheckOutModel getValidFirstAndZipCode() {
        checkOutModel.setFirstNameTextBox(firstName);
        checkOutModel.setLastNameTextBox("");
        checkOutModel.setZipCodeTextBox(zipCode);
        return checkOutModel;
    }

    /**
     * Set CheckOut Details with valid LastName and ZipCode
     * And further field blank
     */
    public CheckOutModel getValidLastNameAndZipCode() {
        checkOutModel.setFirstNameTextBox("");
        checkOutModel.setLastNameTextBox(lastName);
        checkOutModel.setZipCodeTextBox(zipCode);
        return checkOutModel;
    }

    /**
     * Set CheckOut Details with Invalid Firstname and LastName
     * Enter further field Valid
     */
    public CheckOutModel getInvalidFirstNameAndLastName() {
        checkOutModel.setFirstNameTextBox("@##*$58142121214");
        checkOutModel.setLastNameTextBox("#&*@$&@&^%^%475215353145111211");
        checkOutModel.setZipCodeTextBox(zipCode);
        return checkOutModel;
    }

    /**
     * Set CheckOut Details with Invalid Firstname and ZipCode
     * Enter further field Valid
     */
    public CheckOutModel getInvalidFirstNameAndZipCode() {
        checkOutModel.setFirstNameTextBox("@##*$58142121214");
        checkOutModel.setLastNameTextBox(lastName);
        checkOutModel.setZipCodeTextBox("#@&*$&#dfhdfghfghg");
        return checkOutModel;
    }

    /**
     * Set CheckOut Details with Invalid Firstname and ZipCode
     * Enter further field Valid
     */
    public CheckOutModel getInvalidLastNameAndZipCode() {
        checkOutModel.setFirstNameTextBox(firstName);
        checkOutModel.setLastNameTextBox("&^%%455212111");
        checkOutModel.setZipCodeTextBox("ddsfjdfn&%^%$$$$%%$%");
        return checkOutModel;
    }
}

