package gonnaorder.admin.store.settings;

import base.BaseTest;
import datafactory.gonnaorder.customer.CustomerOrderData;
import dataobjects.gonnaorder.customer.CustomerOrder;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.gonnaorder.admin.account.LoginPO;
import pageobjects.gonnaorder.admin.store.orders.OrdersPO;
import pageobjects.gonnaorder.admin.store.settings.OrderingPO;
import pageobjects.gonnaorder.admin.store.settings.PaymentProvidersPO;
import pageobjects.gonnaorder.admin.store.settings.SettingPO;
import pageobjects.gonnaorder.customer.LandingPO;
import pageobjects.gonnaorder.customer.ThankYouPO;
import pageobjects.gonnaorder.customer.YourOrderPO;
import pageobjects.gonnaorder.customer.common.FooterPO;
import pageobjects.gonnaorder.customer.common.PaymentPagePO;
import utilities.Constants;

import java.awt.*;


public class PaymentProviderTests extends BaseTest {


    /*Test 1 : Verify Enable Credit Card Payment Option at admin Ui and Displayed Credit Card Option at customer UI.*/
    @Test(groups = {"payment"})
    public void verifyEnableCreditCardPaymentOptionAtAdminUiAndDisplayedCreditCardOptionAtCustomerUI() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        PaymentPagePO paymentPage = new PaymentPagePO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId9 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Payment Providers' tab");
        setting.clickOnPaymentProvidersButton();

        Reporter.log("Step 5 - Enable the 'Credit Card' payment provider");
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableCreditCardOption(true);

        Reporter.log("Step 6 - Click on the 'Ordering' tab, select the 'Pickup' delivery option & 'Payment Mandatory' payment option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);
        order.selectPaymentMandatoryOption();

        Reporter.log("Step 7 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl9);

        Reporter.log("Step 8 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer9;
        landing.selectOffer(offerName);

        Reporter.log("Step 9 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 10 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 11 - Fill the form i.e email,name");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 12 - Verify that pay online section is present");
        Assert.assertTrue(yourOrder.isPayOnlineTextPresent(),"Pay online section is not present");

        Reporter.log("Step 13 - Fill the form of credit card details i.e card number");
        yourOrder.fillCustomerPaymentDetails(data);

        Reporter.log("Step 14 - Click on the 'Proceed to payment' button");
        footer.clickOnProceedToPaymentButton();
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeaderText(),"3D Secure" + "\n" + "Test Payment Page", "Credit card Header text does not match");
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeadingText(),"This is a test payment" + " of" +" €" + Constants.StoreOfferPrice9 + ".00 EUR" + " using 3D Secure." ,"Credit Card Heading text does not match");
        paymentPage.clickOnCreditCardPaymentSuccessButton();

        Reporter.log("Step 15 - Verify that order is submitted successfully and thank you page is displayed");
        String expectedMessage = "Thank you";
        Assert.assertEquals(thankYou.getPageHeaderText(), expectedMessage, "Thank you message is incorrect");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 16 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId9 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 17 - Verify that order ID is present at first row in open tab at admin UI ");
        Assert.assertEquals(orders.getOrderID(1), orderId, "Order ID isn't present");
    }

    /*Test 2 : Verify Disable Credit Card Payment Option at admin Ui and Not Displayed Credit Card Option at customer UI.*/
    @Test(groups = {"payment"})
    public void verifyDisableCreditCardPaymentOptionAtAdminUiAndNotDisplayedCreditCardOptionAtCustomerUI() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrderingPO order = new OrderingPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Payment Providers' tab");
        setting.clickOnPaymentProvidersButton();

        Reporter.log("Step 5 - Enable the 'Credit Card' payment provider");
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableCreditCardOption(false);

        Reporter.log("Step 6 - Click on the 'Ordering' tab, select the 'Pickup' delivery option & 'Payment Mandatory' payment option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);
        order.selectPaymentMandatoryOption();

        Reporter.log("Step 7 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl1);

        Reporter.log("Step 8 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer1;
        landing.selectOffer(offerName);

        Reporter.log("Step 9 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 10 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 11 - Fill the form i.e email,name");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 12 - Verify that pay online section is not present");
        Assert.assertFalse(yourOrder.isPayOnlineTextPresent(),"Pay online section is present");

    }

    /*Test 3 : Verify Disable iDEAL Payment Option at admin Ui and Not Displayed iDEAL Option at customer UI.*/
    @Test
    public void verifyDisableIDEALPaymentOptionAtAdminUiAndNotDisplayedIDEALOptionAtCustomerUI() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrderingPO order = new OrderingPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId7 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Payment Providers' tab");
        setting.clickOnPaymentProvidersButton();

        Reporter.log("Step 5 - Enable the 'Credit Card' payment provider");
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableIDEALOption(false);

        Reporter.log("Step 6 - Click on the 'Ordering' tab, select the 'Pickup' delivery option & 'Payment Mandatory' payment option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);
        order.selectPaymentMandatoryOption();

        Reporter.log("Step 7 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl7);

        Reporter.log("Step 8 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer7;
        landing.selectOffer(offerName);

        Reporter.log("Step 9 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 10 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 11 - Fill the form i.e email,name");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 12 - Verify that pay online section is not present");
        Assert.assertFalse(yourOrder.isPayOnlineTextPresent(),"Pay online section is present");

    }

    /*Test : Verify Enable iDEAL Payment Option at admin Ui and Displayed iDEAL Option at customer UI.*/
    @Test(groups = {"payment"})
    public void verifyEnableIDEALPaymentOptionAtAdminUiAndDisplayedIDEALOptionAtCustomerUI() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrderingPO order = new OrderingPO(driver);
        PaymentPagePO paymentPage= new PaymentPagePO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrdersPO orders = new OrdersPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId9 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Payment Providers' tab");
        setting.clickOnPaymentProvidersButton();

        Reporter.log("Step 5 - Enable the 'Credit Card' payment provider");
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableIDEALOption(true);

        Reporter.log("Step 6 - Click on the 'Ordering' tab, select the 'Pickup' delivery option & 'Payment Mandatory' payment option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);
        order.selectPaymentMandatoryOption();

        Reporter.log("Step 7 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl9);

        Reporter.log("Step 8 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer9;
        landing.selectOffer(offerName);

        Reporter.log("Step 9 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 10 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 11 - Fill the form i.e email,name");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 12 - Verify that pay online section is present with iDEAl Option and user can select Bank from the List");
        Assert.assertTrue(yourOrder.isPayOnlineTextPresent(),"Pay online section is present");
        Assert.assertEquals(yourOrder.getiDEALPaymentText(),"iDEAL","iDeal Payment method text does not match");
        yourOrder.selectIDEALPaymentOption();
        yourOrder.selectBankNameFromDropDown(data.getBankName());
        Assert.assertTrue(yourOrder.isiDealBankDropdownPresent(),"iDEAL Bank Dropdown is not present");
        footer.clickOnProceedToPaymentButton();

        Reporter.log("Step 13 - Verify that iDEAL Payment test page gets open and user do payment successfully");
        Assert.assertEquals(paymentPage.StripePaymentHeaderText(),"iDEAL test payment page","iDEAL Test Payment text doesn't match");
        paymentPage.clickOnStripePaymentSuccessButton();

        Reporter.log("Step 14 - Verify order submitted with successful Payment and Thank You Page displayed");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 15 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId9 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 16 - Verify that order ID, Delivery Mode, Payment Status, Amount are correct");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), Constants.StoreOfferPrice9 + ",00" + " €" + " - " + "Paid", "Amount and Payment Status Doesn't Match");


    }

    /*Test 4 : Verify Disable Bancontact Payment Option at admin Ui and Not Displayed Bancontact Option at customer UI.*/
    @Test
    public void verifyDisableBancontactPaymentOptionAtAdminUiAndNotDisplayedBancontactOptionAtCustomerUI() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrderingPO order = new OrderingPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId7 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Payment Providers' tab");
        setting.clickOnPaymentProvidersButton();

        Reporter.log("Step 5 - Enable the 'Credit Card' payment provider");
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableBancontactOption(false);

        Reporter.log("Step 6 - Click on the 'Ordering' tab, select the 'Pickup' delivery option & 'Payment Mandatory' payment option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);
        order.selectPaymentMandatoryOption();

        Reporter.log("Step 7 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl7);

        Reporter.log("Step 8 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer7;
        landing.selectOffer(offerName);

        Reporter.log("Step 9 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 10 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 11 - Fill the form i.e email,name");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 12 - Verify that pay online section is not present");
        Assert.assertFalse(yourOrder.isPayOnlineTextPresent(),"Pay online section is present");

    }


    /*Test  : Verify Enable Bancontact Payment Option at admin Ui and Displayed Bancontact Option at customer UI.*/
    @Test(groups = {"payment"})
    public void verifyEnableBancontactPaymentOptionAtAdminUiAndDisplayedBancontactOptionAtCustomerUI() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrderingPO order = new OrderingPO(driver);
        PaymentPagePO paymentPage= new PaymentPagePO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrdersPO orders = new OrdersPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId9 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Payment Providers' tab");
        setting.clickOnPaymentProvidersButton();

        Reporter.log("Step 5 - Enable the 'Credit Card' payment provider");
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableBancontactOption(true);

        Reporter.log("Step 6 - Click on the 'Ordering' tab, select the 'Pickup' delivery option & 'Payment Mandatory' payment option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);
        order.selectPaymentMandatoryOption();

        Reporter.log("Step 7 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl9);

        Reporter.log("Step 8 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer9;
        landing.selectOffer(offerName);

        Reporter.log("Step 9 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 10 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 11 - Fill the form i.e email,name");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 12 - Verify that pay online section is present, Select Bancontact option as payment");
        Assert.assertTrue(yourOrder.isPayOnlineTextPresent(),"Pay online section is not present");
        Assert.assertEquals(yourOrder.getBanContactPaymentText(),"Bancontact","BanContact payment method text does not match");
        yourOrder.selectBanContactPaymentOption();
        footer.clickOnProceedToPaymentButton();

        Reporter.log("Step 13 - Verify that BanContact Payment test page gets open and user do payment successfully");
        Assert.assertEquals(paymentPage.StripePaymentHeaderText(),"Bancontact test payment page","BanContact Test Payment text doesn't match");
        paymentPage.clickOnStripePaymentSuccessButton();

        Reporter.log("Step 14 - Verify order submitted with successful Payment and Thank You Page displayed");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 15 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId9 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 16 - Verify that order ID, Delivery Mode, Payment Status, Amount are correct");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), Constants.StoreOfferPrice9 + ",00" + " €" + " - " + "Paid", "Amount and Payment Status Doesn't Match");

    }

    /*Test 5 : Verify enable Credit Card and iDEAl Payment Option at admin Ui and Displayed Credit Card and iDEAl Option at customer UI.*/
    @Test (groups = {"payment"})
    public void verifyEnableCreditCardAndiDEAlPaymentOptionAtAdminUiAndDisplayedCreditCardAndiDEAlOptionAtCustomerUI() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrderingPO order = new OrderingPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId9 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Payment Providers' tab");
        setting.clickOnPaymentProvidersButton();

        Reporter.log("Step 5 - Enable the 'Credit Card' payment provider");
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableCreditCardAndiDealOption(true);

        Reporter.log("Step 6 - Click on the 'Ordering' tab, select the 'Pickup' delivery option & 'Payment Mandatory' payment option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);
        order.selectPaymentMandatoryOption();

        Reporter.log("Step 7 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl9);

        Reporter.log("Step 8 - Click on the ' Offer'");
        String offerName = Constants.StoreOffer9;
        landing.selectOffer(offerName);

        Reporter.log("Step 9 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 10 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 11 - Fill the form i.e email,name");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 12 - Verify that pay online section is not present");
        Assert.assertTrue(yourOrder.isPayOnlineTextPresent(),"Pay online section is not present");
        Assert.assertEquals(yourOrder.getiDEALPaymentText(),"iDEAL","iDeal Payment method text does not match");
        yourOrder.selectIDEALPaymentOption();
        Assert.assertTrue(yourOrder.isiDealBankDropdownPresent(),"iDEAL Bank Dropdown is not present");
        Assert.assertEquals(yourOrder.getCreditCardPaymentText(),"Credit card","Credit Card payment method text does not match");

    }

    /*Test 6 (115): Verify enable Credit Card and iDEAl Payment Option at admin Ui and Displayed Credit Card and iDEAl Option at customer UI and select iDEAl ,do payment and verify that order submitted successfully.*/
    @Test(groups = {"payment"})
    public void verifyEnableCreditCardAndiDEAlPaymentOptionAtAdminUiAndDisplayedCreditCardAndiDEAlOptionAndSelectIDEALBankFromListAtCustomerUIAndSubmitOrderWithPaymentSuccess() throws InterruptedException, AWTException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrderingPO order = new OrderingPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        PaymentPagePO paymentPage= new PaymentPagePO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId9 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Payment Providers' tab");
        setting.clickOnPaymentProvidersButton();

        Reporter.log("Step 5 - Enable the 'Credit Card' payment provider");
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableCreditCardAndiDealOption(true);

        Reporter.log("Step 6 - Click on the 'Ordering' tab, select the 'Pickup' delivery option & 'Payment Mandatory' payment option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);
        order.selectPaymentMandatoryOption();

        Reporter.log("Step 7 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl9);

        Reporter.log("Step 8 - Click on the ' Offer'");
        String offerName = Constants.StoreOffer9;
        landing.selectOffer(offerName);

        Reporter.log("Step 9 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 10 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 11 - Fill the form i.e email,name");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 12 - Verify that pay online section is present with iDEAL and Credit Card options and user can submit order with ideal");
        Assert.assertTrue(yourOrder.isPayOnlineTextPresent(),"Pay online section is not present");
        Assert.assertEquals(yourOrder.getiDEALPaymentText(),"iDEAL","iDeal Payment method text does not match");
        yourOrder.selectIDEALPaymentOption();
        yourOrder.selectBankNameFromDropDown(data.getBankName());
        Assert.assertTrue(yourOrder.isiDealBankDropdownPresent(),"iDEAL Bank Dropdown is not present");
        Assert.assertEquals(yourOrder.getCreditCardPaymentText(),"Credit card","Credit Card payment method text does not match");
        footer.clickOnProceedToPaymentButton();

        Reporter.log("Step 13 - Verify that iDEAL Payment test page gets open and user do payment successfully");
        Assert.assertEquals(paymentPage.StripePaymentHeaderText(),"iDEAL test payment page","iDEAL Test Payment text doesn't match");
        paymentPage.clickOnStripePaymentSuccessButton();

        Reporter.log("Step 14 - Verify order submitted with successful Payment and Thank You Page displayed");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 15 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId9 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 16 - Verify that order ID, Delivery Mode, Payment Status, Amount are correct");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), Constants.StoreOfferPrice9 + ",00" + " €" + " - " + "Paid", "Amount and Payment Status Doesn't Match");


    }

    /*Test 7 (116): Verify enable Credit Card and iDEAl Payment Option at admin Ui and Displayed Credit Card and iDEAl Option at customer UI and select iDEAl ,do payment as failed and verify that user redirect to 'Your Order' page.*/
    @Test(groups = {"payment"})
    public void verifyEnableCreditCardAndiDEAlPaymentOptionAtAdminUiAndDisplayedCreditCardAndiDEAlOptionAndSelectIDEALBankFromListAtCustomerUIAndSubmitOrderWithPaymentFailed() throws InterruptedException, AWTException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrderingPO order = new OrderingPO(driver);
        PaymentPagePO paymentPage= new PaymentPagePO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId9 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Payment Providers' tab");
        setting.clickOnPaymentProvidersButton();

        Reporter.log("Step 5 - Enable the 'Credit Card' payment provider");
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableCreditCardAndiDealOption(true);

        Reporter.log("Step 6 - Click on the 'Ordering' tab, select the 'Pickup' delivery option & 'Payment Mandatory' payment option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);
        order.selectPaymentMandatoryOption();

        Reporter.log("Step 7 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl9);

        Reporter.log("Step 8 - Click on the ' Offer'");
        String offerName = Constants.StoreOffer9;
        landing.selectOffer(offerName);

        Reporter.log("Step 9 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 10 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 11 - Fill the form i.e email,name");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 12 - Verify that pay online section is present with iDEAL and Credit Card options and user submitted order with failed Payment");
        Assert.assertTrue(yourOrder.isPayOnlineTextPresent(),"Pay online section is not present");
        Assert.assertEquals(yourOrder.getiDEALPaymentText(),"iDEAL","iDeal Payment method text does not match");
        yourOrder.selectIDEALPaymentOption();
        yourOrder.selectBankNameFromDropDown(data.getBankName());
        Assert.assertTrue(yourOrder.isiDealBankDropdownPresent(),"iDEAL Bank Dropdown is not present");
        Assert.assertEquals(yourOrder.getCreditCardPaymentText(),"Credit card","Credit Card payment method text does not match");
        footer.clickOnProceedToPaymentButton();

        Reporter.log("Step 13 - Verify that iDEAL Payment test page gets open and user payment gets Failed ");
        Assert.assertEquals(paymentPage.StripePaymentHeaderText(),"iDEAL test payment page","iDEAL Test Payment text doesn't match");
        paymentPage.clickOnStripePaymentFailedButton();

        Reporter.log("Step 14 - Verify that user navigate back to 'Your order' page after failed payment");
        Assert.assertEquals(yourOrder.getYourOrderPageHeaderText(),"Your order", "Page Header text doesn't match");
        selenium.hardWait(3);
        Assert.assertTrue(yourOrder.isPayOnlineTextPresent(),"Pay online section is not present");
        Assert.assertEquals(yourOrder.getiDEALPaymentText(),"iDEAL","iDeal Payment method text does not match");
        Assert.assertEquals(yourOrder.getCreditCardPaymentText(),"Credit card","Credit Card payment method text does not match");

    }

    /*Test 8 (117) : Verify enable iDEAl Payment and Ban Contact Option at admin Ui and Displayed iDEAl and BanContact Option at customer UI and select BanContact ,do payment and verify that order submitted successfully.*/
    @Test(groups = {"payment"})
    public void verifyEnableiDEAlPaymentAndBanContactOptionAtAdminUiAndDisplayediDEAlAndBanContactOptionAtCustomerUISelectBanContactOptionAndSubmitOrderWithPaymentSuccess() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrderingPO order = new OrderingPO(driver);
        PaymentPagePO paymentPage= new PaymentPagePO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrdersPO orders = new OrdersPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId9 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Payment Providers' tab");
        setting.clickOnPaymentProvidersButton();

        Reporter.log("Step 5 - Enable the 'Credit Card' payment provider");
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableiDealAndBanContactOption(true);

        Reporter.log("Step 6 - Click on the 'Ordering' tab, select the 'Pickup' delivery option & 'Payment Mandatory' payment option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);
        order.selectPaymentMandatoryOption();

        Reporter.log("Step 7 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl9);

        Reporter.log("Step 8 - Click on the ' Offer'");
        String offerName = Constants.StoreOffer9;
        landing.selectOffer(offerName);

        Reporter.log("Step 9 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 10 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 11 - Fill the form i.e email,name");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 12 - Verify that pay online section is not present");
        Assert.assertTrue(yourOrder.isPayOnlineTextPresent(),"Pay online section is not present");
        Assert.assertEquals(yourOrder.getiDEALPaymentText(),"iDEAL","iDeal Payment method text does not match");
        yourOrder.selectIDEALPaymentOption();
        Assert.assertTrue(yourOrder.isiDealBankDropdownPresent(),"iDEAL Bank Dropdown is not present");
        Assert.assertEquals(yourOrder.getBanContactPaymentText(),"Bancontact","BanContact payment method text does not match");
        yourOrder.selectBanContactPaymentOption();
        footer.clickOnProceedToPaymentButton();

        Reporter.log("Step 13 - Verify that BanContact Payment test page gets open and user do payment successfully");
        Assert.assertEquals(paymentPage.StripePaymentHeaderText(),"Bancontact test payment page","BanContact Test Payment text doesn't match");
        paymentPage.clickOnStripePaymentSuccessButton();

        Reporter.log("Step 14 - Verify order submitted with successful Payment and Thank You Page displayed");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 15 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId9 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 16 - Verify that order ID, Delivery Mode, Payment Status, Amount are correct");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), Constants.StoreOfferPrice9 + ",00" + " €" + " - " + "Paid", "Amount and Payment Status Doesn't Match");

    }

    /*Test 9 (118) : Verify enable iDEAl Payment and Ban Contact Option at admin Ui and Displayed iDEAl and BanContact Option at customer UI and select BanContact ,do payment as failed and verify that user redirect to 'Your Order' page.*/
    @Test(groups = {"payment"})
    public void verifyEnableiDEAlPaymentAndBanContactOptionAtAdminUiAndDisplayediDEAlAndBanContactOptionAtCustomerUISelectBanContactOptionAndSubmitOrderWithPaymentFailed() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrderingPO order = new OrderingPO(driver);
        PaymentPagePO paymentPage = new PaymentPagePO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrdersPO orders = new OrdersPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId9 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Payment Providers' tab");
        setting.clickOnPaymentProvidersButton();

        Reporter.log("Step 5 - Enable the 'Credit Card' payment provider");
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableiDealAndBanContactOption(true);

        Reporter.log("Step 6 - Click on the 'Ordering' tab, select the 'Pickup' delivery option & 'Payment Mandatory' payment option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);
        order.selectPaymentMandatoryOption();

        Reporter.log("Step 7 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl9);

        Reporter.log("Step 8 - Click on the ' Offer'");
        String offerName = Constants.StoreOffer9;
        landing.selectOffer(offerName);

        Reporter.log("Step 9 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 10 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 11 - Fill the form i.e email,name");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 12 - Verify that pay online section is not present");
        Assert.assertTrue(yourOrder.isPayOnlineTextPresent(), "Pay online section is not present");
        Assert.assertEquals(yourOrder.getiDEALPaymentText(), "iDEAL", "iDeal Payment method text does not match");
        yourOrder.selectIDEALPaymentOption();
        Assert.assertTrue(yourOrder.isiDealBankDropdownPresent(), "iDEAL Bank Dropdown is not present");
        Assert.assertEquals(yourOrder.getBanContactPaymentText(), "Bancontact", "Credit Card payment method text does not match");
        yourOrder.selectBanContactPaymentOption();
        footer.clickOnProceedToPaymentButton();

        Reporter.log("Step 13 - Verify that BanContact Payment test page gets open and user do payment successfully");
        Assert.assertEquals(paymentPage.StripePaymentHeaderText(), "Bancontact test payment page", "BanContact Test Payment text doesn't match");
        paymentPage.clickOnStripePaymentFailedButton();

        Reporter.log("Step 14 - Verify that user navigate back to 'Your order' page after failed payment");
        Assert.assertEquals(yourOrder.getYourOrderPageHeaderText(), "Your order", "Page Header text doesn't match");
        selenium.hardWait(3);
        Assert.assertTrue(yourOrder.isPayOnlineTextPresent(), "Pay online section is not present");
        Assert.assertEquals(yourOrder.getiDEALPaymentText(), "iDEAL", "iDeal Payment method text does not match");
        Assert.assertEquals(yourOrder.getBanContactPaymentText(),"Bancontact","BanContact payment method text does not match");
    }

    /*Test 10 : Verify enable Credit Card and Bancontact Payment Option at admin Ui and Displayed Credit Card and Bancontact Option at customer UI.*/
    @Test (groups = {"payment"})
    public void verifyEnableCreditCardAndBancontactPaymentOptionAtAdminUiAndDisplayedCreditCardAndBancontactOptionAtCustomerUI() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrderingPO order = new OrderingPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrdersPO orders = new OrdersPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId9 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Payment Providers' tab");
        setting.clickOnPaymentProvidersButton();

        Reporter.log("Step 5 - Enable the 'Credit Card' and 'Bancontact' payment provider");
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableCreditCardAndBancontactOption(true);

        Reporter.log("Step 6 - Click on the 'Ordering' tab, select the 'Pickup' delivery option & 'Payment Mandatory' payment option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);
        order.selectPaymentMandatoryOption();

        Reporter.log("Step 7 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl9);

        Reporter.log("Step 8 - Click on the ' Offer'");
        String offerName = Constants.StoreOffer9;
        landing.selectOffer(offerName);

        Reporter.log("Step 9 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 10 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 11 - Fill the form i.e email,name");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 12 - Verify that pay online section is present and Creditcard and Bancontact option are available");
        Assert.assertTrue(yourOrder.isPayOnlineTextPresent(),"Pay online section is not present");
        Assert.assertEquals(yourOrder.getBanContactPaymentText(),"Bancontact","BanContact payment method text does not match");
        Assert.assertEquals(yourOrder.getCreditCardPaymentText(),"Credit card","Credit Card payment method text does not match");
        yourOrder.selectBanContactPaymentOption();
        Assert.assertTrue(footer.verifyProceedToPaymentButtonIsEnabled(),"Proceed to Payment button is not Enabled");
    }
}



