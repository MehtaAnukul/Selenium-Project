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

public class PaymentTests2 extends BaseTest {


    /*Test 10 (43): Verify that user should be able to place order with Pickup Delivery option successfully and details shown correct at Admin UI while Payment status is Paid.*/
    @Test (groups = {"payment"})
    public void verifyPlaceOrderSuccessfullyWhileAPickupAndDeliveryAtAddressOptionsAndPaymentOptionalAtAdminUiAndSubmitOrderWithPaymentAndPickupDeliveryOptionAtCustomerUI() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);
        PaymentPagePO paymentPage = new PaymentPagePO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId2 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Pickup' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectDeliveryAtAddressAndPickupDeliveryOptions(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentOptionalOption();

        Reporter.log("Step 7 - Navigate to Customer Ui And place an order with payment");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl2);
        String offerName = Constants.StoreOffer2;
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
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeadingText(),"This is a test payment" + " of" +" ₹" + Constants.StoreOfferPrice2 + ".00 INR" + " using 3D Secure." ,"Credit Card Heading text does not match");
        paymentPage.clickOnCreditCardPaymentSuccessButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId2 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 9 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        selenium.hardWait(10);
        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "₹ " + Constants.StoreOfferPrice2 + ".00" + " - " + "Paid", "Amount and Payment Status Doesn't Match");


        Reporter.log("Step 10 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 11 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getPickUpDeliveryMode(), "shopping_basket Pickup " + data.getName(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getUserContactDetail(), data.getPhone(), "Contact detail isn't present");
        Assert.assertEquals(orderDetails.getUserEmailAddressDetail(), data.getEmail(), "email Address isn't present");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "₹ " + Constants.StoreOfferPrice2 + ".00" + " Paid", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer2, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice2 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "₹ " + Constants.StoreOfferPrice2 + ".00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

    /*Test 11 (45): Verify that user should be able to place order with Pickup Delivery option successfully and details shown correct at Admin UI while Payment is optional and status Paid.*/
    @Test (groups = {"payment"})
    public void verifyPlaceOrderSuccessfullyWithPickupDeliveryOptionAndPaymentOptionalAtAdminUiAndSubmitOrderWithPaymentOptionAtCustomerUI() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);
        PaymentPagePO paymentPage = new PaymentPagePO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId2 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Pickup' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentOptionalOption();

        Reporter.log("Step 7 - Navigate to Customer Ui And place an order with payment");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl2);
        String offerName = Constants.StoreOffer2;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();


        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);
        yourOrder.selectPayOnlineCheckBox();
        yourOrder.fillCustomerPaymentDetails(data);
        footer.clickOnProceedToPaymentButton();
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeaderText(),"3D Secure" + "\n" + "Test Payment Page", "Credit card Header text does not match");
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeadingText(),"This is a test payment" + " of" +" ₹" + Constants.StoreOfferPrice2 + ".00 INR" + " using 3D Secure." ,"Credit Card Heading text does not match");
        paymentPage.clickOnCreditCardPaymentSuccessButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId2 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 9 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        selenium.hardWait(10);

        Assert.assertEquals(orders.getData(rowIndex, 3), "person " + data.getName(), "Customer Name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "₹ " + Constants.StoreOfferPrice2 + ".00" + " - " + "Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 10 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 11 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getPickUpDeliveryMode(), "shopping_basket Pickup " + data.getName(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getUserContactDetail(), data.getPhone(), "Contact detail isn't present");
        Assert.assertEquals(orderDetails.getUserEmailAddressDetail(), data.getEmail(), "email Address isn't present");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "₹ " + Constants.StoreOfferPrice2 + ".00" + " Paid", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer2, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice2 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "₹ " + Constants.StoreOfferPrice2 + ".00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

    /*Test 12 (47): Verify that user should be able to place order with Serve At Table Delivery option successfully and details shown correct at Admin UI while Payment is optional and status Paid.*/
    @Test (groups = {"payment"})
    public void verifyPlaceOrderSuccessfullyWithServeAtTableDeliveryOptionAndPaymentOptionalAtAdminUiAndSubmitOrderWithPaymentOptionAtCustomerUI() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);
        PaymentPagePO paymentPage = new PaymentPagePO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId2 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Pickup' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableDeliveryOption(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentOptionalOption();

        Reporter.log("Step 7 - Navigate to Customer Ui And place an order with payment");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl2);
        String offerName = Constants.StoreOffer2;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        yourOrder.fillCustomerDetails(data, false);
        yourOrder.selectPayOnlineCheckBox();
        yourOrder.fillCustomerPaymentDetails(data);
        footer.clickOnProceedToPaymentButton();
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeaderText(),"3D Secure" + "\n" + "Test Payment Page", "Credit card Header text does not match");
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeadingText(),"This is a test payment" + " of" +" ₹" + Constants.StoreOfferPrice2 + ".00 INR" + " using 3D Secure." ,"Credit Card Heading text does not match");
        paymentPage.clickOnCreditCardPaymentSuccessButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId2 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 9 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 2), "room_service Serve room" + data.getTable() , "Customer's table number doesn't Match");
        Assert.assertEquals( orders.getData(rowIndex, 3),"person " + data.getName(),"Customer name doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "₹ " + Constants.StoreOfferPrice2 + ".00" + " - " + "Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 10 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 11 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getServeAtTableDeliveryMode(), "room_service Serve " + data.getName(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getTableNumber(), "room" + data.getTable(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "₹ " + Constants.StoreOfferPrice2 + ".00" + " Paid", "Payment Status doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer2, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice2 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "₹ " + Constants.StoreOfferPrice2 + ".00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

    /*Test 13 (49): Verify that user should be able to place order with Delivery At Address Delivery successfully details shown correct at Admin UI while Payment is optional and status Paid.*/
    @Test (groups = {"payment"})
    public void verifyPlaceOrderSuccessfullyWhileDeliveryAtAddressOptionsAndPaymentOptionalAtAdminUiAndSubmitOrderWithPaymentAtCustomerUI() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrderingPO order = new OrderingPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);
        PaymentPagePO paymentPage = new PaymentPagePO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId2 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Pickup' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectDeliveryAtAddressDeliveryOption(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentOptionalOption();

        Reporter.log("Step 7 - Navigate to Customer Ui And place an order with payment");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl2);
        String offerName = Constants.StoreOffer2;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, true);
        yourOrder.selectPayOnlineCheckBox();
        yourOrder.fillCustomerPaymentDetails(data);
        footer.clickOnProceedToPaymentButton();
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeaderText(),"3D Secure" + "\n" + "Test Payment Page", "Credit card Header text does not match");
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeadingText(),"This is a test payment" + " of" +" ₹" + Constants.StoreOfferPrice2 + ".00 INR" + " using 3D Secure." ,"Credit Card Heading text does not match");
        paymentPage.clickOnCreditCardPaymentSuccessButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId2 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 9 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        selenium.hardWait(10);
        Assert.assertEquals(orders.getData(rowIndex, 2), "directions_bike Deliver room" + data.getZipCode() + " - " + data.getStreetAddress().replace("72", "...") + " person " + data.getName(), "Address doesn't match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "₹ " + Constants.StoreOfferPrice2 + ".00" + " - " + "Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 10 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 11 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getLocation(), "room" + data.getStreetAddress() + " -" + data.getZipCode() + " - " + data.getTown(), "Delivery mode and Customer Address doesn't match");
        Assert.assertEquals(orderDetails.getUserContactDetail(), data.getPhone(), "Contact detail isn't present");
        Assert.assertEquals(orderDetails.getUserEmailAddressDetail(), data.getEmail(), "email Address isn't present");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "₹ " + Constants.StoreOfferPrice2 + ".00" + " Paid", "Payment Status doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer2, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice2 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "₹ " + Constants.StoreOfferPrice2 + ".00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

    /*Test 14 () : Verify that Order should not be submitted when payment gets failed and error should be displayed.*/
    @Test(groups = {"payment"})
    public void verifyOrderShouldNotSubmittedWhileCreditCardPaymentGetFailedFromTestPageAndErrorMessageDisplayed
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
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId9 + "/settings/store-edit";
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
        selenium.navigateToPage(Constants.StoreCustomerUiUrl9);
        String offerName = Constants.StoreOffer9;
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
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeadingText(),"This is a test payment" + " of" +" €" + Constants.StoreOfferPrice9 + ".00 EUR" + " using 3D Secure." ,"Credit Card Heading text does not match");
        paymentPage.clickOnCreditCardPaymentFailedButton();
        Assert.assertEquals(thankYou.getFailedPaymentErrorText(),"We are unable to authenticate your payment method. Please choose a different payment method and try again." ,"Failed Payment Credit Card error text does not match");
    }


}
