package gonnaorder.admin.store.settings;

import base.BaseTest;
import datafactory.gonnaorder.customer.CustomerOrderData;
import dataobjects.gonnaorder.customer.CustomerOrder;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.gonnaorder.admin.account.LoginPO;
import pageobjects.gonnaorder.admin.store.orders.OrdersDetailsPO;
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

public class PaymentTests1 extends BaseTest {

    /*Test 1 : Verify that user should be able to place order successfully while payment is disabled at admin UI.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWhileDisabledPaymentOption() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Pickup' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableAndPickupDeliveryOptions(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Disabled' payment option");
        order.selectPaymentDisabledOption();

        Reporter.log("Step 6 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl1);

        Reporter.log("Step 7 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer1;
        landing.selectOffer(offerName);

        Reporter.log("Step 8 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 9 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 10 - On Your Order page, verify the offer is successfully added in cart");
        Assert.assertEquals(yourOrder.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice1 + ".00", "Price doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemName(1), Constants.StoreOffer1, "Offer name doesn't match");
        Assert.assertEquals(yourOrder.getTotalPrice(), "₹ " + Constants.StoreOfferPrice1 + ".00", "Total price doesn't match");

        Reporter.log("Step 11 - Select the radio button of 'Pickup' delivery option");
        yourOrder.selectIWillPickItUpDeliveryOption();

        Reporter.log("Step 12 - Fill the form i.e email");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 13 - Verify that total amount is displayed on the button and then Click on the 'Submit Your Order' button");
        Assert.assertEquals(footer.getTotalPriceOnSubmitYourOrderButton(), "( ₹ " + Constants.StoreOfferPrice1 + ".00 )");
        footer.clickOnSubmitYourOrderButton();

        Reporter.log("Step 14 - Verify that order is submitted successfully and thank you page is displayed");
        String expectedMessage = "Thank you";
        Assert.assertEquals(thankYou.getPageHeaderText(), expectedMessage, "Thank you message is incorrect");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 15 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 16 - Verify that order ID is present at first row in open tab at admin UI ");
        Assert.assertEquals(orders.getOrderID(1), orderId, "Order ID isn't present");

    }

    /*Test 2 : Verify that user should be able to place order successfully while payment is Optional at admin UI and unselect the payment option at customer UI.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWhileOptionalPaymentOptionAtAdminUiAndUnSelectPaymentOptionAtCustomerUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Enable the 'Credit Card' payment provider");
        setting.clickOnPaymentProvidersButton();
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableCreditCardOption(true);

        Reporter.log("Step 5 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Pickup' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableAndPickupDeliveryOptions(true);

        Reporter.log("Step 6 - Select the radio button of 'Payment Optional' payment option");
        order.selectPaymentOptionalOption();

        Reporter.log("Step 7 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl1);

        Reporter.log("Step 8 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer1;
        landing.selectOffer(offerName);

        Reporter.log("Step 9 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 10 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 11 - On Your Order page, verify the offer is successfully added in cart");
        Assert.assertEquals(yourOrder.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice1 + ".00", "Price doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemName(1), Constants.StoreOffer1, "Offer name doesn't match");
        Assert.assertEquals(yourOrder.getTotalPrice(), "₹ " + Constants.StoreOfferPrice1 + ".00", "Total price doesn't match");

        Reporter.log("Step 12 - Select the radio button of 'Pickup' delivery option");
        yourOrder.selectIWillPickItUpDeliveryOption();

        Reporter.log("Step 13 - Fill the form i.e email");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 14 - Verify that total amount is displayed on the button and then Click on the 'Submit Your Order' button");
        Assert.assertEquals(footer.getTotalPriceOnSubmitYourOrderButton(), "( ₹ " + Constants.StoreOfferPrice1 + ".00 )");
        footer.clickOnSubmitYourOrderButton();

        Reporter.log("Step 15 - Verify that order is submitted successfully and thank you page is displayed");
        String expectedMessage = "Thank you";
        Assert.assertEquals(thankYou.getPageHeaderText(), expectedMessage, "Thank you message is incorrect");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 16 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 17 - Verify that order ID is present at first row in open tab at admin UI ");
        Assert.assertEquals(orders.getOrderID(1), orderId, "Order ID isn't present");

    }

    /*Test 3 : Verify that user should be able to place order successfully while payment is Optional at admin UI and select the payment option at customer UI.*/
    @Test(groups = {"payment"})
    public void verifyPlaceOrderSuccessfullyWhileOptionalPaymentOptionAtAdminUiAndSelectPaymentOptionAtCustomerUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        PaymentPagePO paymentPage = new PaymentPagePO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Enable the 'Credit Card' payment provider");
        setting.clickOnPaymentProvidersButton();
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableCreditCardOption(true);

        Reporter.log("Step 5 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Delivery at address' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableAndDeliveryAtAddressDeliveryOptions(true);

        Reporter.log("Step 6 - Select the radio button of 'Payment Optional' payment option");
        order.selectPaymentOptionalOption();

        Reporter.log("Step 7 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl1);

        Reporter.log("Step 8 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer1;
        landing.selectOffer(offerName);

        Reporter.log("Step 9 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 10 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 11 - On Your Order page, verify the offer is successfully added in cart");
        Assert.assertEquals(yourOrder.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice1 + ".00", "Price doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemName(1), Constants.StoreOffer1, "Offer name doesn't match");
        Assert.assertEquals(yourOrder.getTotalPrice(), "₹ " + Constants.StoreOfferPrice1 + ".00", "Total price doesn't match");

        Reporter.log("Step 12 - Select the radio button of 'I want receive at address' delivery option");
        yourOrder.selectIWantReceiveAtAddressDeliveryOption();

        Reporter.log("Step 13 - Fill the form i.e email,address");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, true);

        Reporter.log("Step 14 - Select the checkbox of payment");
        yourOrder.selectPayOnlineCheckBox();

        Reporter.log("Step 15 - Fill the form of credit card details i.e card number");
        yourOrder.fillCustomerPaymentDetails(data);

        Reporter.log("Step 16 - Click on the 'Proceed to payment' button");
        footer.clickOnProceedToPaymentButton();
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeaderText(),"3D Secure" + "\n" + "Test Payment Page", "Credit card Header text does not match");
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeadingText(),"This is a test payment" + " of" +" ₹" + Constants.StoreOfferPrice1 + ".00 INR" + " using 3D Secure." ,"Credit Card Heading text does not match");
        paymentPage.clickOnCreditCardPaymentSuccessButton();

        Reporter.log("Step 17 - Verify that order is submitted successfully and thank you page is displayed");
        String expectedMessage = "Thank you";
        Assert.assertEquals(thankYou.getPageHeaderText(), expectedMessage, "Thank you message is incorrect");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 18 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 19 - Verify that order ID is present at first row in open tab at admin UI ");
        Assert.assertEquals(orders.getOrderID(1), orderId, "Order ID isn't present");
    }

    /*Test 4 : Verify that user should be able to place order successfully while payment is Mandatory at admin UI.*/
    @Test(groups = {"payment"})
    public void verifyPlaceOrderSuccessfullyWhileMandatoryPaymentOptionAtAdminUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        PaymentPagePO paymentPage = new PaymentPagePO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Enable the 'Credit Card' payment provider");
        setting.clickOnPaymentProvidersButton();
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableCreditCardOption(true);

        Reporter.log("Step 5 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Delivery at address' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableAndDeliveryAtAddressDeliveryOptions(true);

        Reporter.log("Step 6 - Select the radio button of 'Payment Disabled' payment option");
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

        Reporter.log("Step 11 - On Your Order page, verify the offer is successfully added in cart");
        Assert.assertEquals(yourOrder.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice1 + ".00", "Price doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemName(1), Constants.StoreOffer1, "Offer name doesn't match");
        Assert.assertEquals(yourOrder.getTotalPrice(), "₹ " + Constants.StoreOfferPrice1 + ".00", "Total price doesn't match");

        Reporter.log("Step 12 - Select the radio button of 'I want receive at address' delivery option");
        yourOrder.selectIWantReceiveAtAddressDeliveryOption();

        Reporter.log("Step 13 - Fill the form i.e email,address");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, true);

        Reporter.log("Step 14 - Fill the form of credit card details i.e card number");
        yourOrder.fillCustomerPaymentDetails(data);

        Reporter.log("Step 15 - Click on the 'Proceed to payment' button");
        footer.clickOnProceedToPaymentButton();
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeaderText(),"3D Secure" + "\n" + "Test Payment Page", "Credit card Header text does not match");
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeadingText(),"This is a test payment" + " of" +" ₹" + Constants.StoreOfferPrice1 + ".00 INR" + " using 3D Secure." ,"Credit Card Heading text does not match");
        paymentPage.clickOnCreditCardPaymentSuccessButton();

        Reporter.log("Step 16 - Verify that order is submitted successfully and thank you page is displayed");
        String expectedMessage = "Thank you";
        Assert.assertEquals(thankYou.getPageHeaderText(), expectedMessage, "Thank you message is incorrect");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 17 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 18 - Verify that order ID is present at first row in open tab at admin UI ");
        Assert.assertEquals(orders.getOrderID(1), orderId, "Order ID isn't present");
    }

    /*Test 5 : Verify that user should be able to place order successfully and details shown correct at Admin UI while Payment status is Paid.*/
    @Test(groups = {"payment"})
    public void verifyPlaceOrderSuccessfullyWithPaymentAndServeAtTableOptionFromCustomerUIAndVerifyDetailsAtAdminUIWhilePaymentStatusPaid() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        PaymentPagePO paymentPage = new PaymentPagePO(driver);


        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Enable the 'Credit Card' payment provider");
        setting.clickOnPaymentProvidersButton();
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableCreditCardOption(true);

        Reporter.log("Step 5 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Delivery at address' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableDeliveryOption(true);

        Reporter.log("Step 6 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentMandatoryOption();

        Reporter.log("Step 7 - Navigate to Customer Ui And place an order with payment");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl1);
        String offerName = Constants.StoreOffer1;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        yourOrder.fillCustomerDetails(data, false);
        yourOrder.fillCustomerPaymentDetails(data);
        footer.clickOnProceedToPaymentButton();
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeaderText(),"3D Secure" + "\n" + "Test Payment Page", "Credit card Header text does not match");
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeadingText(),"This is a test payment" + " of" +" ₹" + Constants.StoreOfferPrice1 + ".00 INR" + " using 3D Secure." ,"Credit Card Heading text does not match");
        paymentPage.clickOnCreditCardPaymentSuccessButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 9 - Verify that order ID, Delivery Mode, Payment Status, Amount are correct");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 2), "room_service Serve room" + data.getTable(), "Customer's table number doesn't Match");
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer's Name doesn't Match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "₹ " + Constants.StoreOfferPrice3 + ".00" + " - " + "Paid", "Amount and Payment Status Doesn't Match");


        Reporter.log("Step 10 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 11 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getServeAtTableDeliveryMode(), "room_service Serve " + data.getName(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getTableNumber(), "room" + data.getTable(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "₹ " + Constants.StoreOfferPrice1 + ".00" + " Paid", "Payment Status doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer1, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice1 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "₹ " + Constants.StoreOfferPrice1 + ".00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

    /*Test 6 (35) : Verify that user should be able to place order with Pickup Delivery option successfully and details shown correct at Admin UI while Payment status is Mandatory.*/
    @Test(groups = {"payment"})
    public void verifyPlaceOrderSuccessfullyWithPaymentAndPickupDeliveryOptionFromCustomerUIAndVerifyDetailsAtAdminUIWhilePaymentStatusPaid
    () throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        PaymentPagePO paymentPage = new PaymentPagePO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Enable the 'Credit Card' payment provider");
        setting.clickOnPaymentProvidersButton();
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableCreditCardOption(true);

        Reporter.log("Step 5 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Delivery at address' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableAndPickupDeliveryOptions(true);

        Reporter.log("Step 6 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentMandatoryOption();

        Reporter.log("Step 7 - Navigate to Customer Ui And place an order with payment");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl1);
        String offerName = Constants.StoreOffer1;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        yourOrder.selectIWillPickItUpDeliveryOption();
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);
        yourOrder.fillCustomerPaymentDetails(data);
        footer.clickOnProceedToPaymentButton();
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeaderText(),"3D Secure" + "\n" + "Test Payment Page", "Credit card Header text does not match");
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeadingText(),"This is a test payment" + " of" +" ₹" + Constants.StoreOfferPrice1 + ".00 INR" + " using 3D Secure." ,"Credit Card Heading text does not match");
        paymentPage.clickOnCreditCardPaymentSuccessButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 9 - Verify that order ID, Delivery Mode, Payment Status, Amount are correct");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "₹ " + Constants.StoreOfferPrice3 + ".00" + " - " + "Paid", "Amount and Payment Status Doesn't Match");


        Reporter.log("Step 10 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 11 - Verify that all detail are present & correct in Order Detail page");
        selenium.refreshPage();
        selenium.hardWait(3);
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getPickUpDeliveryMode(), "shopping_basket Pickup " + data.getName(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getUserContactDetail(), data.getPhone(), "Contact detail isn't present");
        Assert.assertEquals(orderDetails.getUserEmailAddressDetail(), data.getEmail(), "email Address isn't present");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "₹ " + Constants.StoreOfferPrice1 + ".00" + " Paid", "Payment Status doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer1, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice1 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "₹ " + Constants.StoreOfferPrice1 + ".00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

    /*Test 7 (37): Verify that user should be able to place order with Delivery At Address Delivery successfully and details shown correct at Admin UI while Payment status is Mandatory.*/
    @Test(groups = {"payment"})
    public void verifyPlaceOrderSuccessfullyWithPaymentAndDeliveryAtAddressOptionFromCustomerUIAndVerifyDetailsAtAdminUIWhilePaymentStatusPaid
    () throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        PaymentPagePO paymentPage = new PaymentPagePO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Enable the 'Credit Card' payment provider");
        setting.clickOnPaymentProvidersButton();
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableCreditCardOption(true);

        Reporter.log("Step 5 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Delivery at address' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectDeliveryAtAddressAndPickupDeliveryOptions(true);

        Reporter.log("Step 6 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentMandatoryOption();

        Reporter.log("Step 7 - Navigate to Customer Ui And place an order with payment");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl1);
        String offerName = Constants.StoreOffer1;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        yourOrder.selectIWantReceiveAtAddressDeliveryOption();
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, true);
        yourOrder.fillCustomerPaymentDetails(data);
        footer.clickOnProceedToPaymentButton();
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeaderText(),"3D Secure" + "\n" + "Test Payment Page", "Credit card Header text does not match");
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeadingText(),"This is a test payment" + " of" +" ₹" + Constants.StoreOfferPrice1 + ".00 INR" + " using 3D Secure." ,"Credit Card Heading text does not match");
        paymentPage.clickOnCreditCardPaymentSuccessButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 9 - Verify that order ID, Delivery Mode, Payment Status, Amount are correct");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 2), "directions_bike Deliver room" + data.getZipCode() + " - " + data.getStreetAddress().replace("72", "...") ,"Address doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName() ,"Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "₹ " + Constants.StoreOfferPrice3 + ".00" + " - " + "Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 10 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 11 - Verify that all detail are present & correct in Order Detail page");
        selenium.hardWait(3);
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getLocation(), "room " + data.getStreetAddress() + ", " + data.getZipCode() + " - " + data.getTown(), "Delivery mode and Customer Address doesn't match");
        Assert.assertEquals(orderDetails.getUserContactDetail(), data.getPhone(), "Contact detail isn't present");
        Assert.assertEquals(orderDetails.getUserEmailAddressDetail(), data.getEmail(), "email Address isn't present");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "₹ " + Constants.StoreOfferPrice1 + ".00" + " Paid", "Payment Status doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer1, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice1 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "₹ " + Constants.StoreOfferPrice1 + ".00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

    /*Test 8 (39): Verify that user should be able to place order with Pickup Delivery successfully and details shown correct at Admin UI while Payment status is Paid.*/
    @Test(groups = {"payment"})
    public void verifyPlaceOrderSuccessfullyWhileAServeAtTableAndPickupDeliveryOptionsAndPaymentOptionalAtAdminUiAndSubmitOrderWithPaymentAndPickupDeliveryOptionAtCustomerUI
    () throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        PaymentPagePO paymentPage = new PaymentPagePO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Enable the 'Credit Card' payment provider");
        setting.clickOnPaymentProvidersButton();
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableCreditCardOption(true);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Pickup' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableAndPickupDeliveryOptions(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentOptionalOption();

        Reporter.log("Step 7 - Navigate to Customer Ui And place an order with payment");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl1);
        String offerName = Constants.StoreOffer1;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();
        yourOrder.selectIWillPickItUpDeliveryOption();

        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);
        yourOrder.selectPayOnlineCheckBox();
        yourOrder.fillCustomerPaymentDetails(data);
        footer.clickOnProceedToPaymentButton();
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeaderText(),"3D Secure" + "\n" + "Test Payment Page", "Credit card Header text does not match");
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeadingText(),"This is a test payment" + " of" +" ₹" + Constants.StoreOfferPrice1 + ".00 INR" + " using 3D Secure." ,"Credit Card Heading text does not match");
        paymentPage.clickOnCreditCardPaymentSuccessButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 9 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        selenium.hardWait(10);
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "₹ " + Constants.StoreOfferPrice3 + ".00" + " - " + "Paid", "Amount and Payment Status Doesn't Match");


        Reporter.log("Step 10 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 11 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getPickUpDeliveryMode(), "shopping_basket Pickup " + data.getName(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getUserContactDetail(), data.getPhone(), "Contact detail isn't present");
        Assert.assertEquals(orderDetails.getUserEmailAddressDetail(), data.getEmail(), "email Address isn't present");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "₹ " + Constants.StoreOfferPrice1 + ".00" + " Paid", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer1, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice1 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "₹ " + Constants.StoreOfferPrice1 + ".00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

    /*Test 9 (40): Verify that user should be able to place order with Delivery At Address Delivery successfully and details shown correct at Admin UI while Payment status is Paid.*/
    @Test(groups = {"payment"})
    public void verifyPlaceOrderSuccessfullyWhileAServeAtTableAndDeliveryAtAddressOptionsAndPaymentOptionalAtAdminUiAndSubmitOrderWithPaymentAndPickupDeliveryOptionAtCustomerUI
    () throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        PaymentPagePO paymentPage = new PaymentPagePO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Enable the 'Credit Card' payment provider");
        setting.clickOnPaymentProvidersButton();
        paymentProviders.enableDisableAllPaymentProviderOptions(false);
        paymentProviders.enableDisableCreditCardOption(true);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Pickup' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableAndDeliveryAtAddressDeliveryOptions(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentOptionalOption();

        Reporter.log("Step 7 - Navigate to Customer Ui And place an order with payment");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl1);
        String offerName = Constants.StoreOffer1;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();
        yourOrder.selectIWantReceiveAtAddressDeliveryOption();

        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, true);
        yourOrder.selectPayOnlineCheckBox();
        yourOrder.fillCustomerPaymentDetails(data);
        footer.clickOnProceedToPaymentButton();
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeaderText(),"3D Secure" + "\n" + "Test Payment Page", "Credit card Header text does not match");
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeadingText(),"This is a test payment" + " of" +" ₹" + Constants.StoreOfferPrice1 + ".00 INR" + " using 3D Secure." ,"Credit Card Heading text does not match");
        paymentPage.clickOnCreditCardPaymentSuccessButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 9 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        selenium.hardWait(10);
        Assert.assertEquals(orders.getData(rowIndex, 2), "directions_bike Deliver room" + data.getZipCode() + " - " + data.getStreetAddress().replace("72", "...") ,"Address doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName() ,"Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "₹ " + Constants.StoreOfferPrice3 + ".00" + " - " + "Paid", "Amount and Payment Status Doesn't Match");


        Reporter.log("Step 10 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 11 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getLocation(), "room " + data.getStreetAddress() + ", " + data.getZipCode() + " - " + data.getTown(), "Delivery mode and Customer Address doesn't match");
        Assert.assertEquals(orderDetails.getUserContactDetail(), data.getPhone(), "Contact detail isn't present");
        Assert.assertEquals(orderDetails.getUserEmailAddressDetail(), data.getEmail(), "email Address isn't present");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "₹ " + Constants.StoreOfferPrice1 + ".00" + " Paid", "Payment Status doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer1, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice1 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "₹ " + Constants.StoreOfferPrice1 + ".00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

}

