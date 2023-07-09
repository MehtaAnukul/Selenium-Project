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
import pageobjects.gonnaorder.admin.store.orders.RejectOrderPO;
import pageobjects.gonnaorder.admin.store.settings.OrderingPO;
import pageobjects.gonnaorder.admin.store.settings.SettingPO;
import pageobjects.gonnaorder.customer.LandingPO;
import pageobjects.gonnaorder.customer.ThankYouPO;
import pageobjects.gonnaorder.customer.YourOrderPO;
import pageobjects.gonnaorder.customer.common.FooterPO;
import utilities.Constants;
import utilities.JavaHelpers;
import utilities.emailhelpers.EmailHelpers;
import utilities.emailhelpers.mailinator.EmailDetailsPO;
import utilities.emailhelpers.mailinator.EmailListingGridPO;

import java.awt.*;
import java.text.ParseException;


public class OrderingTests3 extends BaseTest {

    /*Test 21 (48): Verify that user should be able to place order with Delivery At Address Delivery successfully details shown correct at Admin UI while Payment is optional and status Not Paid.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWhileDeliveryAtAddressOptionsAndPaymentOptionalAtAdminUiAndSubmitOrderWithoutPaymentAtCustomerUI() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId4 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Pickup' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectDeliveryAtAddressDeliveryOption(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentOptionalOption();

        Reporter.log("Step 7 - Navigate to Customer Ui And place an order with payment");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl4);
        String offerName = Constants.StoreOffer4;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, true);
        footer.clickOnSubmitYourOrderButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId4 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 9 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        selenium.hardWait(10);
        Assert.assertEquals(orders.getData(rowIndex, 2), "directions_bike Deliver room" + data.getZipCode() + " - " + data.getStreetAddress().replace("72", "...") ,"Address doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 3),  "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "€ " + Constants.StoreOfferPrice4 + ",00" + " - " + "Not Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 10 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 11 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getLocation(), "room " + data.getStreetAddress() + ", " + data.getZipCode() + " - " + data.getTown(), "Delivery mode and Customer Address doesn't match");
        Assert.assertEquals(orderDetails.getUserContactDetail(), data.getPhone(), "Contact detail isn't present");
        Assert.assertEquals(orderDetails.getUserEmailAddressDetail(), data.getEmail(), "email Address isn't present");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "€ " + Constants.StoreOfferPrice4 + ",00" + " Not Paid", "Payment Status doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer4, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice4 + ",00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "€ " + Constants.StoreOfferPrice4 + ",00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

    /*Test 22 (50): Verify that user should be able to place order successfully without enter optional data while pickup option is selected at admin UI.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWithoutOptionalDataWhilePickupDeliveryOption() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId4 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is disabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);

        Reporter.log("Step 5 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl4);

        Reporter.log("Step 6 - Select Offer item and Submit Order without enter optional Data");
        String offerName = Constants.StoreOffer4;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        footer.clickOnSubmitYourOrderButton();

        Reporter.log("Step 7 - Verify that order is submitted successfully and thank you page is displayed");
        String expectedMessage = "Thank you";
        Assert.assertEquals(thankYou.getPageHeaderText(), expectedMessage, "Thank you message is incorrect");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId4 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 9 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        selenium.hardWait(10);
        Assert.assertEquals(orders.getData(rowIndex, 2), "shopping_basket Pickup", "Delivery mode doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "€ " + Constants.StoreOfferPrice4 + ",00" + " - " + "Not Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 8 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 9 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals(orderDetails.getPickUpDeliveryMode(), "shopping_basket Pickup", "Delivery mode doesn't match");
        Assert.assertFalse(orderDetails.isContactDetailPresent(), "Contact detail is present");
        Assert.assertFalse(orderDetails.isEmailDetailPresent(), "email Address is present");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "€ " + Constants.StoreOfferPrice4 + ",00" + " Not Paid", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer4, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice4 + ",00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "€ " + Constants.StoreOfferPrice4 + ",00", "Total price doesn't match");
        Assert.assertFalse(orderDetails.isSpecialNotePresent(), "Special Note Present");
    }

    /*Test 23 (51): Verify that user should be able to place order successfully without enter optional data while serve at a table option is selected at admin UI.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWhileServeAtTableDeliveryOption() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId4 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableDeliveryOption(true);

        Reporter.log("Step 5 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl4);

        Reporter.log("Step 6 - Select Offer item and Submit Order without enter optional Data");
        String offerName = Constants.StoreOffer4;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        yourOrder.AddTableNumberWithoutOptionalData(data);
        footer.clickOnSubmitYourOrderButton();

        Reporter.log("Step 7 - Verify that order is submitted successfully and thank you page is displayed");
        String expectedMessage = "Thank you";
        Assert.assertEquals(thankYou.getPageHeaderText(), expectedMessage, "Thank you message is incorrect");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId4 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 9 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 2), "room_service Serve room" + data.getTable(), "Customer's table number doesn't Match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "€ " + Constants.StoreOfferPrice4 + ",00" + " - " + "Not Paid", "Amount and Payment Status Doesn't Match");


        Reporter.log("Step 10 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 11 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getServeAtTableDeliveryMode(), "room_service Serve", "Delivery mode doesn't match");
        Assert.assertEquals(orderDetails.getTableNumber(), "room" + data.getTable(), "Table Number doesn't match");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "€ " + Constants.StoreOfferPrice3 + ",00" + " Not Paid", "Payment Status doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer3, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice3 + ",00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "€ " + Constants.StoreOfferPrice3 + ",00", "Total price doesn't match");
        Assert.assertFalse(orderDetails.isSpecialNotePresent(), "Special Note Present");
    }

    /*Test 24 (86) : Verify that order is marked as rejected at customer ui while reject the order without reason at admin ui.*/
    @Test
    public void verifyOrderIsMarkedAsRejectedAtCustomerUiWhileRejectOrderWithoutReasonAtAdminUi() throws InterruptedException, AWTException, ParseException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        RejectOrderPO rejectOrder = new RejectOrderPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is enabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);

        Reporter.log("Step 5 - Navigate to Customer Ui And place an order");
        selenium.openNewTab();
        selenium.switchToWindow(2);
        selenium.navigateToPage(Constants.StoreCustomerUiUrl3);
        String offerName = Constants.StoreOffer3;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);
        footer.clickOnSubmitYourOrderButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 6 - Navigate to the Orders page at admin UI");
        selenium.switchToWindow(1);
        String orderPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/orders";
        selenium.navigateToPage(orderPageUrl);

        Reporter.log("Step 7 - Click on the Order Id which is present in first row");
        selenium.hardWait(10);
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 8 - Click on the reject button and then reject the order without reason");
        rejectOrder.clickOnRejectButton();
        rejectOrder.selectRejectWithoutReasonRadioButton();
        rejectOrder.clickOnRejectOrderButton();

        Reporter.log("Step 9 - Click on rejected tab and verify that rejected order is display in first row");
        orders.clickOnRejectTab();
        Assert.assertEquals(orders.getOrderID(1), orderId, "Rejected order id isn't present");
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "€ " + Constants.StoreOfferPrice3 + ",00" + " - " + "Not Paid", "Amount and Payment Status Doesn't Match");


        Reporter.log("Step 10 - Navigate to customer ui and verify that order is marked as rejected");
        selenium.switchToWindow(2);
        Assert.assertEquals(thankYou.getRejectedStateColor(), "rgba(255, 9, 9, 1)", "Order isn't marked as rejected");
    }

    /*Test 25 (87) : Verify that order is marked as rejected and rejected reason is display at customer ui while reject the order with reason at admin ui.*/
    @Test
    public void verifyOrderIsMarkedAsRejectedAndRejectedReasonDisplayAtCustomerUiWhileRejectOrderWithReasonAtAdminUi() throws InterruptedException, AWTException, ParseException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        OrdersDetailsPO ordersDetails = new OrdersDetailsPO(driver);
        RejectOrderPO rejectOrder = new RejectOrderPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is enabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);

        Reporter.log("Step 5 - Navigate to Customer Ui And place an order");
        selenium.openNewTab();
        selenium.switchToWindow(2);
        selenium.navigateToPage(Constants.StoreCustomerUiUrl3);
        String offerName = Constants.StoreOffer3;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);
        footer.clickOnSubmitYourOrderButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 6 - Navigate to the Orders page at admin UI");
        selenium.switchToWindow(1);
        String orderPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/orders";
        selenium.navigateToPage(orderPageUrl);

        Reporter.log("Step 7 - Click on the Order Id which is present in first row");
        selenium.hardWait(10);
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 8 - Click on the reject button and then reject the order with reason");
        rejectOrder.clickOnRejectButton();
        rejectOrder.selectRejectWithReasonRadioButton();
        String reason = "This item is not available";
        rejectOrder.fillRejectedReason(reason);
        rejectOrder.clickOnRejectOrderButton();

        Reporter.log("Step 9 - Click on rejected tab and verify that rejected order is display in first row");
        orders.clickOnRejectTab();
        Assert.assertEquals(orders.getOrderID(1), orderId, "Rejected order id isn't present");
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "€ " + Constants.StoreOfferPrice3 + ",00" + " - " + "Not Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 10 - Click on rejected order and verify that 'Rejected Reason' is display");
        orders.clickOnOrderID(rowIndex);
        Assert.assertEquals(ordersDetails.getOrderRejectedReasonMessage(), reason, "Order rejected reason Doesn't Match ");

        Reporter.log("Step 11 - Navigate to customer ui and verify that order is marked as rejected and rejected reason is display");
        selenium.switchToWindow(2);
        Assert.assertEquals(thankYou.getRejectedStateColor(), "rgba(255, 9, 9, 1)", "Order isn't marked as rejected");
        Assert.assertTrue(thankYou.isOrderRejectedReasonMessagePresent(), "Order rejected reason isn't present");

    }

    /*Test (52): Verify that user should be able to place order successfully and get order confirm email.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWithPickupDeliveryOptionAndVerifyEmailReceived() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        EmailDetailsPO emailDetails = new EmailDetailsPO(driver);
        EmailHelpers emailhelpers = new EmailHelpers(driver);
        EmailListingGridPO emailListingGrid = new EmailListingGridPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId4 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is disabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);

        Reporter.log("Step 5 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl4);

        Reporter.log("Step 6 - Select Offer item and Submit Order without enter optional Data");
        String offerName = Constants.StoreOffer4;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();
        footer.clickOnSubmitYourOrderButton();

        Reporter.log("Step 7 - Verify that order is submitted successfully and thank you page is displayed");
        String expectedMessage = "Thank you";
        Assert.assertEquals(thankYou.getPageHeaderText(), expectedMessage, "Thank you message is incorrect");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8- Verify that success message is displayed.");
        Assert.assertEquals(thankYou.getGetEmailHeaderText(), "Fill in your email address to receive a copy of your order", "Header Text didn't match");

        Reporter.log("Step 9- Fill email info and send order confirmation email.");
        CustomerOrderData data1 = new CustomerOrderData();
        CustomerOrder emailId = data1.getEmailData();
        String email = emailId.getEmailAddress();
        thankYou.fillEmailAndClickOnSendButton(emailId);
        Assert.assertEquals(thankYou.getGetEmailSendStatus(), "Email sent", "Email Status didn't match");

        Reporter.log("Step 10- Navigate to Email box for : " + email + " and verifying that Order Confirmation email is received");
        String emailFromInfo = Constants.StoreName4;
        String emailSubjectConfirmationOfOrder = "Je bestelling " + orderId + " bij " + Constants.StoreName4;
        Assert.assertTrue(emailhelpers.isEmailReceived(Constants.MAILINATORNAME, email.split("@")[0], emailFromInfo, emailSubjectConfirmationOfOrder, 10), "Email is not received");

        Reporter.log("Step 11- Opening Email and verifying OrderId & clicking downloading link , again comparing orderId.");
        emailListingGrid.openEmail(emailFromInfo, emailSubjectConfirmationOfOrder);
        //Assert.assertEquals(emailDetails.getOrderIdFromMail(), "Bestelling nummer: " + orderId, "Order id doesn't match in Mail");
        emailDetails.clickOnButtonOrLink("Klik hier om de status van je bestelling te volgen");
        selenium.switchToWindow(2);
        selenium.hardWait(5);
        Assert.assertEquals(thankYou.getOrderID(), orderId, "Order id doesn't match at Downloaded Thank you page");
    }

    /*Test (52): Verify that user should be able to place order successfully and get order confirm email.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWithServeAtTableOptionAndVerifyEmailReceived() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        EmailDetailsPO emailDetails = new EmailDetailsPO(driver);
        EmailHelpers emailhelpers = new EmailHelpers(driver);
        EmailListingGridPO emailListingGrid = new EmailListingGridPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId4 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is disabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableDeliveryOption(true);

        Reporter.log("Step 5 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl4);

        Reporter.log("Step 6 - Select Offer item and Submit Order without enter optional Data");
        String offerName = Constants.StoreOffer4;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        yourOrder.AddTableNumberWithoutOptionalData(data);
        footer.clickOnSubmitYourOrderButton();

        Reporter.log("Step 7 - Verify that order is submitted successfully and thank you page is displayed");
        String expectedMessage = "Thank you";
        Assert.assertEquals(thankYou.getPageHeaderText(), expectedMessage, "Thank you message is incorrect");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8- Verify that success message is displayed");
        Assert.assertEquals(thankYou.getGetEmailHeaderText(), "Fill in your email address to receive a copy of your order", "Header Text didn't match");

        Reporter.log("Step 9- Fill email info and send order confirmation email.");
        CustomerOrderData data1 = new CustomerOrderData();
        CustomerOrder emailId = data1.getEmailData();
        String email = emailId.getEmailAddress();
        thankYou.fillEmailAndClickOnSendButton(emailId);
        Assert.assertEquals(thankYou.getGetEmailSendStatus(), "Email sent", "Email Status didn't match");

        Reporter.log("Step 10- Navigate to Email box for : " + email + " and verifying that Order Confirmation email is received");
        String emailFromInfo = Constants.StoreName4;
        String emailSubjectConfirmationOfOrder = "Je bestelling " + orderId + " bij " + Constants.StoreName4;
        Assert.assertTrue(emailhelpers.isEmailReceived(Constants.MAILINATORNAME, email.split("@")[0], emailFromInfo, emailSubjectConfirmationOfOrder, 10), "Email is not received");

        Reporter.log("Step 11- Opening Email and verifying OrderId & clicking downloading link , again comparing orderId.");
        emailListingGrid.openEmail(emailFromInfo, emailSubjectConfirmationOfOrder);
        //Assert.assertEquals(emailDetails.getOrderIdFromMail(), "Bestelling nummer: " + orderId, "Order id doesn't match in Mail");
        emailDetails.clickOnButtonOrLink("Klik hier om de status van je bestelling te volgen");
        selenium.switchToWindow(2);
        selenium.hardWait(5);
        Assert.assertEquals(thankYou.getOrderID(), orderId, "Order id doesn't match at Downloaded Thank you page");
    }

    /*Test (88) : Verify that order pdf is downloaded successfully.*/
    @Test
    public void verifyOrderPdfDownloadedSuccessfully() throws InterruptedException, AWTException, ParseException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        OrdersDetailsPO ordersDetails = new OrdersDetailsPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        JavaHelpers java = new JavaHelpers();
        JavaHelpers helper = new JavaHelpers();

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is enabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);

        Reporter.log("Step 5 - Navigate to Customer Ui And place an order");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        selenium.navigateToPage(Constants.StoreCustomerUiUrl3);
        String offerName = Constants.StoreOffer3;
        selenium.hardWait(3);
        landing.selectOffer(offerName);
        data.setOrderPlacedTime(helper.getTimeStamp("HH:mm a - dd MMM.")); // tj
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);
        footer.clickOnSubmitYourOrderButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 6 - Navigate to the Orders page at admin UI");
        String orderPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/orders";
        selenium.navigateToPage(orderPageUrl);

        Reporter.log("Step 7 - Click on the Order Id which is present in first row");
        selenium.hardWait(10);
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "€ " + Constants.StoreOfferPrice3 + ",00" + " - " + "Not Paid", "Amount and Payment Status Doesn't Match");

        String expected_from = data.getOrderPlacedTime().replace("p.m.", "").replace("a.m", "");
        String actual_to = helper.updateTime("HH:mm a - dd MMM.", data.getOrderPlacedTime(), "HH:mm a - dd MMM.", 0, 0, 4, 0);
        Assert.assertTrue(new JavaHelpers().isTimeBetween(expected_from, actual_to, orders.getData(rowIndex, 5).replace("p.m.", "PM").replace("a.m.", "AM"), "HH:mm a - dd MMM."), "Date and time doesn't match");        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 8 - Click on the 'Download' button");
        selenium.hardWait(3);
        ordersDetails.clickOnDownloadButton();

        Reporter.log("Step 9 - Verify that Pdf is downloaded");
        String downloadedOrderPDF = Constants.StoreName3 + "-order-" + orderId + ".pdf";
        Assert.assertTrue(java.isFilePresent(java.getDownloadPath(), downloadedOrderPDF), "Failed to download order pdf");

    }

    /*Test (53) : Verify that order Submitted with Estimate time from Customer Ui and View With Detail at Admin UI */
    @Test
    public void verifyOrderSubmittedWithEstimateTimeFromCustomerUiAndViewWithDetailAtAdminUI() throws InterruptedException, AWTException, ParseException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        JavaHelpers helper = new JavaHelpers();

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is enabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);

        Reporter.log("Step 5 - Navigate to Customer Ui And place an order with manually select Specific Time of an Order");
        selenium.openNewTab();
        selenium.switchToWindow(2);
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);
        String offerName = Constants.StoreOffer;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        yourOrder.selectIWantToSpecifyWhenToReceiveItRadioButton();
        String CurrentTime = helper.getTimeStamp("mm");
        int diff = (15 - Integer.parseInt(CurrentTime) % 15) + 15;
        String TimeUpdate = helper.updateTime("HH:mm a", helper.getTimeStamp("HH:mm a"), "HH:mm a", 0, 1, diff, 0).toLowerCase();
        yourOrder.SelectEstimateTime();
        footer.clickOnSubmitYourOrderButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 6 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 7 - Verify that order ID, Delivery Mode, Payment Status, Amount are correct ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        selenium.hardWait(10);
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "€ " + Constants.StoreOfferPrice + ",00" + " - " + "Not Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 8 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 9 - Verify that all detail are present & correct in Order Detail page");
        String TimeFormation = TimeUpdate.substring(0, 7) + "." + TimeUpdate.substring(7) + ".";
        Assert.assertEquals(orderDetails.getOrderWishDetail(), "Order Wish " + helper.getTimeStamp("E dd MMM.").toLowerCase().replace("oct", "okt") + ", " + TimeFormation.toLowerCase(), "Expected Details doesn't match");
        Assert.assertEquals(orderDetails.getPickUpDeliveryMode(), "shopping_basket Pickup " + data.getName(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getUserContactDetail(), data.getPhone(), "Contact detail isn't present");
        Assert.assertEquals(orderDetails.getUserEmailAddressDetail(), data.getEmail(), "email Address isn't present");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "€ " + Constants.StoreOfferPrice + ",00" + " Not Paid", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice + ",00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "€ " + Constants.StoreOfferPrice + ",00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

    /*Test (89) : Verify that order pdf is printed successfully.*/
    @Test
    public void verifyOrderPdfPrintedSuccessfully() throws InterruptedException, AWTException {
        LoginPO login = new LoginPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        OrdersDetailsPO ordersDetails = new OrdersDetailsPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Order page");
        String orderPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/orders";
        selenium.navigateToPage(orderPageUrl);

        Reporter.log("Step 4 - Click on the Order Id which is present in first row");
        selenium.hardWait(10);
        String orderID = orders.getOrderID(1);
        orders.clickOnOrderID(1);

        Reporter.log("Step 5 - Click on the 'Print' button");
        selenium.hardWait(3);
        ordersDetails.clickOnPrintButton();

        Reporter.log("Step 6 - Verify the correct order id is displayed on the print page");
        selenium.switchToWindow(2);
        String act = driver.getTitle();
        Assert.assertEquals(act, "Order " + orderID, "Order id didn't match");

    }

    /*Test (53.1) : Verify that order Submitted with Estimate time details from Customer Ui and View With Days of week Detail at Admin UI */
    @Test
    public void verifyOrderSubmittedWithEstimateTimeFromCustomerUiAndViewWithDetailWithDaysOfWeekAtAdminUI() throws InterruptedException, AWTException, ParseException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        JavaHelpers helper = new JavaHelpers();

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId10 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is enabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);

        Reporter.log("Step 5 - Navigate to Customer Ui And place an order with manually select Specific Time of an Order");
        selenium.openNewTab();
        selenium.switchToWindow(2);
        selenium.navigateToPage(Constants.StoreCustomerUiUrl10);
        String offerName = Constants.StoreOffer10;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        yourOrder.selectIWantToSpecifyWhenToReceiveItRadioButton();
        String CurrentTime = helper.getTimeStamp("mm");
        int diff = (15 - Integer.parseInt(CurrentTime) % 15) + 15;
        String TimeUpdate = helper.updateTime("HH:mm a", helper.getTimeStamp("HH:mm a"), "HH:mm a", 0, 1, diff, 0).toLowerCase();
        yourOrder.SelectEstimateTime();
        footer.clickOnSubmitYourOrderButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 6 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId10 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 7 - Verify that order ID, Delivery Mode, Payment Status, Amount are correct ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        selenium.hardWait(10);
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "£" + Constants.StoreOfferPrice10 + ".00" + " - " + "Not Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 8 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 9 - Verify that all detail are present & correct in Order Detail page");
        String TimeFormation = TimeUpdate.substring(0, 7) + "." + TimeUpdate.substring(7) + ".";
        Assert.assertEquals(orderDetails.getOrderWishDetail(), "Order Wish " + helper.getTimeStamp("E dd MMM.").toLowerCase().replace("oct", "okt") + ", " + TimeFormation.toLowerCase(), "Expected Details doesn't match");
        Assert.assertEquals(orderDetails.getPickUpDeliveryMode(), "shopping_basket Pickup " + data.getName(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getUserContactDetail(), data.getPhone(), "Contact detail isn't present");
        Assert.assertEquals(orderDetails.getUserEmailAddressDetail(), data.getEmail(), "email Address isn't present");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "£" + Constants.StoreOfferPrice10 + ".00" + " Not Paid", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer10, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "£" + Constants.StoreOfferPrice10 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "£" + Constants.StoreOfferPrice10 + ".00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

}


