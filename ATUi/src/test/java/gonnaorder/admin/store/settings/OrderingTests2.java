package gonnaorder.admin.store.settings;

import base.BaseTest;
import datafactory.gonnaorder.customer.CustomerOrderData;
import dataobjects.gonnaorder.customer.CustomerOrder;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.gonnaorder.admin.account.LoginPO;
import pageobjects.gonnaorder.admin.store.orders.CloseOrderPO;
import pageobjects.gonnaorder.admin.store.orders.OrdersDetailsPO;
import pageobjects.gonnaorder.admin.store.orders.OrdersPO;
import pageobjects.gonnaorder.admin.store.settings.OrderingPO;
import pageobjects.gonnaorder.admin.store.settings.SettingPO;
import pageobjects.gonnaorder.customer.LandingPO;
import pageobjects.gonnaorder.customer.ThankYouPO;
import pageobjects.gonnaorder.customer.YourOrderPO;
import pageobjects.gonnaorder.customer.common.FooterPO;
import utilities.Constants;
import utilities.JavaHelpers;

import java.awt.*;
import java.text.ParseException;


public class OrderingTests2 extends BaseTest {

    /*Test 11 : Verify that delivery options at customer ui while 'Ordering' toggle is enabled at admin ui.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWhileOrderingToggleIsEnabledAtAdminUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrderingPO order = new OrderingPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Pickup' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableAndPickupDeliveryOptions(true);

        Reporter.log("Step 5 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl3);

        Reporter.log("Step 6 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer3;
        landing.selectOffer(offerName);

        Reporter.log("Step 7 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 8 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 9 - On Your Order page, verify the offer is successfully added in cart");
        Assert.assertEquals(yourOrder.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice3 + ",00", "Price doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemName(1), Constants.StoreOffer3, "Offer name doesn't match");
        Assert.assertEquals(yourOrder.getTotalPrice(), "€ " + Constants.StoreOfferPrice3 + ",00", "Total price doesn't match");

        Reporter.log("Step 10 - Verify that 'Pickup' and 'Serve at table' delivery options are present");
        Assert.assertTrue(yourOrder.isIAmSittingAtTableDeliveryOptionPresent(), "I am sitting at table delivery option is not present");
        Assert.assertTrue(yourOrder.isIWillPickItUpDeliveryOptionPresent(), "I will pick it up myself delivery option is not present");
        Assert.assertFalse(yourOrder.isIWantReceiveAtAddressDeliveryOptionPresent(), "I want to receive it at my address delivery option is present");


    }

    /*Test 12 : Verify that thank you message is displayed at customer ui while 'Ordering' toggle is disabled at admin ui.*/
    @Test
    public void verifyThankYouMessageDisplayAtCustomerUiWhileOrderingToggleIsDisabledAtAdminUi() throws InterruptedException {
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
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab");
        setting.clickOnOrderingButton();

        Reporter.log("Step 5 - Click on the 'Ordering' toggle as disabled ordering");
        order.selectDeselectOrderingToggle(false);

        Reporter.log("Step 6 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl3);

        Reporter.log("Step 7 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer3;
        landing.selectOffer(offerName);

        Reporter.log("Step 8 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 9 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 10 - Verify that delivery options are not present at customer ui");
        Assert.assertFalse(yourOrder.isIAmSittingAtTableDeliveryOptionPresent(), "I am sitting at table delivery option is present");
        Assert.assertFalse(yourOrder.isIWillPickItUpDeliveryOptionPresent(), "I will pick it up myself delivery option is present");
        Assert.assertFalse(yourOrder.isIWantReceiveAtAddressDeliveryOptionPresent(), "I want to receive it at my address delivery option is present");

        Reporter.log("Step 11 - Verify that thank you message is displayed");
        String expectedMessage = "Thank you!" + "\n" + "Please share your order with the store when you are ready";
        Assert.assertEquals(thankYou.getThankYouMessageText(), expectedMessage, "Thank you message is incorrect");

    }

    /*Test 13 : Verify that order details are correct when delivery mode - Pick up and and Order placed without payment.*/
    @Test
    public void verifyOrderDetailsWhenPickupDeliveryOptionAndOrderPlacedAsNotPaid() throws InterruptedException, ParseException {
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
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is disabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);

        Reporter.log("Step 5 - Navigate to Customer Ui And place an order without payment");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        selenium.navigateToPage(Constants.StoreCustomerUiUrl3);
        String offerName = Constants.StoreOffer3;
        landing.selectOffer(offerName);
        data.setOrderPlacedTime(helper.getTimeStamp("HH:mm a - dd MMM.")); // tj
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        footer.clickOnSubmitYourOrderButton();

        String orderId = thankYou.getOrderID();

        Reporter.log("Step 6 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 7 - Verify that order ID, Delivery Mode, Payment Status, Amount are correct ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        selenium.hardWait(10);
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "€ " + Constants.StoreOfferPrice3 + ",00" + " - " + "Not Paid", "Amount and Payment Status Doesn't Match");

        String expected_from = data.getOrderPlacedTime().replace("p.m.", "").replace("a.m", "");
        String actual_to = helper.updateTime("HH:mm a - dd MMM.", data.getOrderPlacedTime(), "HH:mm a - dd MMM.", 0, 0, 4, 0);
        Assert.assertTrue(new JavaHelpers().isTimeBetween(expected_from, actual_to, orders.getData(rowIndex, 5).replace("p.m.", "PM").replace("a.m.", "AM"), "HH:mm a - dd MMM."), "Date and time doesn't match");

        Reporter.log("Step 8 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 9 - Verify that all detail are present & correct in Order Detail page");
        String actualOrderSentDetails = orderDetails.getOrderSentDetail();
        String expectedOrderSentDetails = "Order Sent";
        Assert.assertTrue(actualOrderSentDetails.startsWith(expectedOrderSentDetails), "Order Sent time doesn't match. Expected: + " + expectedOrderSentDetails + " , Actual : " + actualOrderSentDetails);
        Assert.assertEquals(orderDetails.getPickUpDeliveryMode(), "shopping_basket Pickup " + data.getName(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getUserContactDetail(), data.getPhone(), "Contact detail isn't present");
        Assert.assertEquals(orderDetails.getUserEmailAddressDetail(), data.getEmail(), "email Address isn't present");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "€ " + Constants.StoreOfferPrice3 + ",00" + " Not Paid", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer3, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice3 + ",00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "€ " + Constants.StoreOfferPrice3 + ",00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

    /*Test 14 : Verify that order is marked as accepted at customer ui while accept order at admin ui.*/
    @Test
    public void verifyOrderIsMarkedAsAcceptedAtCustomerUiWhileAcceptOrderAtAdminUi() throws InterruptedException, AWTException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        CloseOrderPO closeOrder = new CloseOrderPO(driver);
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

        Reporter.log("Step 8 - Click on the close button and then accept the order");
        closeOrder.clickOnCloseButton();
        closeOrder.selectAcceptOrderRadioButton();
        closeOrder.clickOnCloseOrderButton();

        Reporter.log("Step 9 - Click on close tab and verify that closed order is display in first row");
        orders.clickOnCloseTab();
        Assert.assertEquals(orders.getOrderID(1), orderId, "Closed order id isn't present");
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "€ " + Constants.StoreOfferPrice3 + ",00" + " - " + "Not Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 10 - Navigate to customer ui and verify that order is marked as accepted");
        selenium.switchToWindow(2);
        Assert.assertEquals(thankYou.getAcceptedStateColor(), "rgba(9, 139, 0, 1)", "Order isn't marked as accepted");
    }

    /*Test 15 : Verify that user should be able to place order successfully and details shown at Admin Ui while Delivery Option is Delivery at Address.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWhileDeliveryAtAddressDeliveryOptionAndViewAtAdminSideWithDetails() throws InterruptedException, ParseException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        JavaHelpers helper = new JavaHelpers();
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectDeliveryAtAddressDeliveryOption(true);

        Reporter.log("Step 5 - Navigate to Customer Ui");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        selenium.navigateToPage(Constants.StoreCustomerUiUrl3);
        String offerName = Constants.StoreOffer3;
        landing.selectOffer(offerName);
        data.setOrderPlacedTime(helper.getTimeStamp("HH:mm a - dd MMM.")); // tj
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        data.setTable("");
        yourOrder.fillCustomerDetails(data, true);

        footer.clickOnSubmitYourOrderButton();
        String expectedMessage = "Thank you";
        Assert.assertEquals(thankYou.getPageHeaderText(), expectedMessage, "Thank you message is incorrect");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 6 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 7 - Verify that order ID, Delivery Mode, Payment Status, amount are present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        selenium.hardWait(10);
        Assert.assertEquals(orders.getData(rowIndex, 2), "directions_bike Deliver room" + data.getZipCode() + " - " + data.getStreetAddress().replace("72", "..."), "Address doesnt match");
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "€ " + Constants.StoreOfferPrice3 + ",00" + " - " + "Not Paid", "Amount and Payment Status Doesn't Match");
        String expected_from = data.getOrderPlacedTime().replace("p.m.", "").replace("a.m", "");
        String actual_to = helper.updateTime("HH:mm a - dd MMM.", data.getOrderPlacedTime(), "HH:mm a - dd MMM.", 0, 0, 4, 0);
        Assert.assertTrue(new JavaHelpers().isTimeBetween(expected_from, actual_to, orders.getData(rowIndex, 5).replace("p.m.", "PM").replace("a.m.", "AM"), "HH:mm a - dd MMM."), "Date and time doesn't match");

        Reporter.log("Step 8 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 9 - Verify that all detail are present & correct in Order Detail page");
        String actualOrderSentDetails = orderDetails.getOrderSentDetail();
        String expectedOrderSentDetails = "Order Sent";
        Assert.assertTrue(actualOrderSentDetails.startsWith(expectedOrderSentDetails), "Order Sent time doesn't match. Expected: + " + expectedOrderSentDetails + " , Actual : " + actualOrderSentDetails);
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getUserContactDetail(), data.getPhone(), "Contact detail isn't present");
        Assert.assertEquals(orderDetails.getUserEmailAddressDetail(), data.getEmail(), "email Address isn't present");
        Assert.assertEquals(orderDetails.getLocation(), "room " + data.getStreetAddress() + ", " + data.getZipCode() + " - " + data.getTown(), "Delivery mode and Customer Address doesn't match");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "€ " + Constants.StoreOfferPrice3 + ",00" + " Not Paid", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getDeliveryAtAddressMode(), "directions_bike Deliver " + data.getName(), "Delivery mode doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer3, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice3 + ",00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "€ " + Constants.StoreOfferPrice3 + ",00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

    /*Test 16 : Verify that order is marked as accepted and ready at customer ui while accept order as ready at admin ui.*/
    @Test
    public void verifyOrderIsMarkedAsAcceptedAndReadyAtCustomerUiWhileAcceptOrderAsReadyAtAdminUi() throws InterruptedException, AWTException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);
        CloseOrderPO closeOrder = new CloseOrderPO(driver);
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

        Reporter.log("Step 8 - Click on the close button and then accept the order as a ready");
        closeOrder.clickOnCloseButton();
        closeOrder.selectAcceptOrderAndMarkAsReadyRadioButton();
        closeOrder.clickOnCloseOrderButton();

        Reporter.log("Step 9 - Click on close tab and verify that closed order is display in first row");
        orders.clickOnCloseTab();
        Assert.assertEquals(orders.getOrderID(1), orderId, "Closed order id isn't present");
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "€ " + Constants.StoreOfferPrice3 + ",00" + " - " + "Not Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 10 - Click on closed order and verify that 'Order is ready' message display");
        orders.clickOnOrderID(rowIndex);
        Assert.assertTrue(orderDetails.isOrderReadyMessagePresent(), "Order is ready!");

        Reporter.log("Step 11 - Navigate to customer ui and verify that order is marked as accepted and 'Order is ready' message display");
        selenium.switchToWindow(2);
        Assert.assertEquals(thankYou.getAcceptedStateColor(), "rgba(9, 139, 0, 1)", "Order isn't marked as accepted");
        Assert.assertTrue(thankYou.isOrderReadyMessagePresent(), "Your order is ready");
    }

    /*Test 17 : Verify that order is marked as accepted and expected message display at customer ui while accept order and include estimated time at admin ui.*/
    @Test
    public void verifyOrderIsMarkedAsAcceptedAndExpectedAtCustomerUiWhileAcceptOrderAndIncludeEstimatedTimeAtAdminUi() throws InterruptedException, AWTException, ParseException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);
        CloseOrderPO closeOrder = new CloseOrderPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
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
        selenium.openNewTab();
        selenium.switchToWindow(2);
        selenium.navigateToPage(Constants.StoreCustomerUiUrl3);
        String offerName = Constants.StoreOffer3;
        selenium.hardWait(3);
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

        Reporter.log("Step 8 - Click on the close button and then accept the order as a expected time");
        closeOrder.clickOnCloseButton();
        closeOrder.selectAcceptOrderAndEstimatedTimeRadioButton();
        int addMinute = 3;
        closeOrder.fillExpectedTime(addMinute);
        closeOrder.clickOnCloseOrderButton();

        Reporter.log("Step 9 - Click on close tab and verify that closed order is display in first row");
        orders.clickOnCloseTab();
        Assert.assertEquals(orders.getOrderID(1), orderId, "Closed order id isn't present");
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "€ " + Constants.StoreOfferPrice3 + ",00" + " - " + "Not Paid", "Amount and Payment Status Doesn't Match");


        Reporter.log("Step 10 - Click on closed order and verify that 'Order expected' message display");
        orders.clickOnOrderID(rowIndex);
        String TimeUpdate = helper.updateTime(("HH:mm a"), helper.getTimeStamp("HH:mm a"), ("HH:mm a"), 0, 0, addMinute, 0).toLowerCase();
        String TimeFormation = TimeUpdate.substring(0, 7) + "." + TimeUpdate.substring(7) + ".";
        String actualOrderSentDetails = orderDetails.getOrderExpectedMessagePresent();
        String ExpectedOrderMessage = "Order Expected " + helper.getTimeStamp("dd MMM").toLowerCase().replace("oct", "okt") + "., " + TimeFormation;
        Assert.assertEquals(orderDetails.getOrderExpectedMessagePresent(), ExpectedOrderMessage, "Order Expected time doesn't match");
        Assert.assertTrue(actualOrderSentDetails.startsWith(ExpectedOrderMessage), "Order Expected time doesn't match. Expected: + " + ExpectedOrderMessage + " , Actual : " + actualOrderSentDetails);

        Reporter.log("Step 11 - Navigate to customer ui and verify that order is marked as accepted and 'Order Expected' message display");
        selenium.switchToWindow(2);
        Assert.assertEquals(thankYou.getAcceptedStateColor(), "rgba(9, 139, 0, 1)", "Order isn't marked as accepted");
        String TimeUpdate1 = helper.updateTime("HH:mm", helper.getTimeStamp("HH:mm"), "HH:mm", 0, 0, addMinute, 0);
        String expectedMessage = "Your order is expected" + "\n" + TimeUpdate1;
        Assert.assertEquals(thankYou.getOrderExpectedMessagePresent(), expectedMessage, "Expected message doesn't match");
    }

    /*Test 18 (42): Verify that user should be able to place order with Pickup Delivery option from both options successfully and details shown correct at Admin UI while Payment is optional and status Not Paid.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWhileAPickupAndDeliveryAtAddressOptionsAndPaymentOptionalAtAdminUiAndSubmitOrderWithoutPaymentAndPickupDeliveryOptionAtCustomerUI() throws InterruptedException {
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
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Pickup' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectDeliveryAtAddressAndPickupDeliveryOptions(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentOptionalOption();

        Reporter.log("Step 7 - Navigate to Customer Ui And place an order with payment");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl3);
        String offerName = Constants.StoreOffer3;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();
        yourOrder.selectIWillPickItUpDeliveryOption();

        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);
        footer.clickOnSubmitYourOrderButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 9 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        selenium.hardWait(10);
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "€ " + Constants.StoreOfferPrice3 + ",00" + " - " + "Not Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 10 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 11 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getPickUpDeliveryMode(), "shopping_basket Pickup " + data.getName(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getUserContactDetail(), data.getPhone(), "Contact detail isn't present");
        Assert.assertEquals(orderDetails.getUserEmailAddressDetail(), data.getEmail(), "email Address isn't present");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "€ " + Constants.StoreOfferPrice3 + ",00" + " Not Paid", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer3, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice3 + ",00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "€ " + Constants.StoreOfferPrice3 + ",00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

    /*Test 19 (44): Verify that user should be able to place order with Pickup Delivery option successfully and details shown correct at Admin UI while Payment is optional and status Not Paid.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWithPickupDeliveryOptionAndPaymentOptionalAtAdminUiAndSubmitOrderWithoutPaymentOptionAtCustomerUI() throws InterruptedException {
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
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Pickup' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentOptionalOption();

        Reporter.log("Step 7 - Navigate to Customer Ui And place an order with payment");
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

        Reporter.log("Step 8 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 9 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        selenium.hardWait(10);
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "€ " + Constants.StoreOfferPrice3 + ",00" + " - " + "Not Paid", "Amount and Payment Status Doesn't Match");


        Reporter.log("Step 10 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 11 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getPickUpDeliveryMode(), "shopping_basket Pickup " + data.getName(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getUserContactDetail(), data.getPhone(), "Contact detail isn't present");
        Assert.assertEquals(orderDetails.getUserEmailAddressDetail(), data.getEmail(), "email Address isn't present");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "€ " + Constants.StoreOfferPrice3 + ",00" + " Not Paid", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer3, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice3 + ",00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "€ " + Constants.StoreOfferPrice3 + ",00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

    /*Test 20 (46): Verify that user should be able to place order with Serve At Table Delivery option successfully and details shown correct at Admin UI while Payment is optional and status Not Paid.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWithServeAtTableDeliveryOptionAndPaymentOptionalAtAdminUiAndSubmitOrderWithoutPaymentOptionAtCustomerUI() throws InterruptedException {
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
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Pickup' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableDeliveryOption(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentOptionalOption();

        Reporter.log("Step 7 - Navigate to Customer Ui And place an order with payment");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl3);
        String offerName = Constants.StoreOffer3;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        yourOrder.fillCustomerDetails(data, false);
        footer.clickOnSubmitYourOrderButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId3 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 9 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 2), "room_service Serve room" + data.getTable() , "Customer's table number doesn't Match");
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer's Name number doesn't Match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "€ " + Constants.StoreOfferPrice3 + ",00" + " - " + "Not Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 10 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 11 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getServeAtTableDeliveryMode(), "room_service Serve " + data.getName(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getTableNumber(), "room" + data.getTable(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "€ " + Constants.StoreOfferPrice3 + ",00" + " Not Paid", "Payment Status doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer3, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice3 + ",00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "€ " + Constants.StoreOfferPrice3 + ",00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

}




