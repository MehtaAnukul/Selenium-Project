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
import pageobjects.gonnaorder.admin.store.settings.SettingPO;
import pageobjects.gonnaorder.customer.LandingPO;
import pageobjects.gonnaorder.customer.ThankYouPO;
import pageobjects.gonnaorder.customer.YourOrderPO;
import pageobjects.gonnaorder.customer.common.FooterPO;
import utilities.Constants;


public class OrderingTests1 extends BaseTest {

    /*Test 1 : Verify that user should be able to place order successfully while pickup option is selected at admin UI.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWhilePickupDeliveryOption() throws InterruptedException {
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
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is disabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);

        Reporter.log("Step 5 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 6 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer;
        landing.selectOffer(offerName);

        Reporter.log("Step 7 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 8 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 9 - Fill the form i.e email");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 10 - Click on the 'Submit Your Order' button");
        footer.clickOnSubmitYourOrderButton();

        Reporter.log("Step 11 - Verify that order is submitted successfully and thank you page is displayed");
        String expectedMessage = "Thank you";
        Assert.assertEquals(thankYou.getPageHeaderText(), expectedMessage, "Thank you message is incorrect");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 12 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 13 - Verify that order ID is present at first row in open tab at admin UI ");
        Assert.assertEquals(orderId, orders.getOrderID(1), "Order ID isn't present");

    }

    /*Test 2 : Verify that user should be able to place order successfully while serve at a table option is selected at admin UI.*/
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

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableDeliveryOption(true);

        Reporter.log("Step 5 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 6 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer;
        landing.selectOffer(offerName);

        Reporter.log("Step 7 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 8 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 9 - On Your Order page, verify the offer is successfully added in cart");
        Assert.assertEquals(yourOrder.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice + ",00", "Price doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemName(1), Constants.StoreOffer, "Offer name doesn't match");
        Assert.assertEquals(yourOrder.getTotalPrice(), "€ " + Constants.StoreOfferPrice + ",00", "Total price doesn't match");

        Reporter.log("Step 10 - Fill the form i.e notes,name info");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 11 - Click on the 'Submit Your Order' button");
        footer.clickOnSubmitYourOrderButton();

        Reporter.log("Step 12 - Verify that order is submitted successfully and thank you page is displayed");
        String expectedMessage = "Thank you";
        Assert.assertEquals(thankYou.getPageHeaderText(), expectedMessage, "Thank you message is incorrect");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 13 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 14 - Verify that order ID is present at first row in open tab at admin UI ");
        Assert.assertEquals(orderId, orders.getOrderID(1), "Order ID isn't present");
    }

    /*Test 3 : Verify that user should be able to place order successfully while delivery at address option is selected at admin UI.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWhileDeliveryAtAddressDeliveryOption() throws InterruptedException {
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
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectDeliveryAtAddressDeliveryOption(true);

        Reporter.log("Step 5 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 6 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer;
        landing.selectOffer(offerName);

        Reporter.log("Step 7 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 8 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 9 - On Your Order page, verify the offer is successfully added in cart");
        Assert.assertEquals(yourOrder.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice + ",00", "Price doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemName(1), Constants.StoreOffer, "Offer name doesn't match");
        Assert.assertEquals(yourOrder.getTotalPrice(), "€ " + Constants.StoreOfferPrice + ",00", "Total price doesn't match");

        Reporter.log("Step 10 - Fill the form i.e notes,email info");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, true);

        Reporter.log("Step 11 - Click on the 'Submit Your Order' button");
        footer.clickOnSubmitYourOrderButton();

        Reporter.log("Step 12 - Verify that order is submitted successfully and thank you page is displayed");
        String expectedMessage = "Thank you";
        Assert.assertEquals(thankYou.getPageHeaderText(), expectedMessage, "Thank you message is incorrect");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 13 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 14 - Verify that order ID is present at first row in open tab at admin UI ");
        Assert.assertEquals(orders.getOrderID(1), orderId, "Order ID isn't present");
    }

    /*Test 4 : Verify order while Serve At Table And Pickup Delivery Options set at admin Ui and pick up delivery option is selected at customer UI.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWhileAServeAtTableAndPickupDeliveryOptionsAtAdminUiAndPickupDeliveryOptionAtCustomerUI() throws InterruptedException {
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
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Pickup' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableAndPickupDeliveryOptions(true);

        Reporter.log("Step 5 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 6 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer;
        landing.selectOffer(offerName);

        Reporter.log("Step 7 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 8 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 9 - On Your Order page, verify the offer is successfully added in cart");
        Assert.assertEquals(yourOrder.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice + ",00", "Price doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemName(1), Constants.StoreOffer, "Offer name doesn't match");
        Assert.assertEquals(yourOrder.getTotalPrice(), "€ " + Constants.StoreOfferPrice + ",00", "Total price doesn't match");

        Reporter.log("Step 10 - Select the radio button of 'I will pick it' delivery option");
        yourOrder.selectIWillPickItUpDeliveryOption();

        Reporter.log("Step 11 - Fill the form i.e email");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 12 - Click on the 'Submit Your Order' button");
        footer.clickOnSubmitYourOrderButton();

        Reporter.log("Step 13 - Verify that order is submitted successfully and thank you page is displayed");
        String expectedMessage = "Thank you";
        Assert.assertEquals(thankYou.getPageHeaderText(), expectedMessage, "Thank you message is incorrect");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 14 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 15 - Verify that order ID is present at first row in open tab at admin UI ");
        Assert.assertEquals(orders.getOrderID(1), orderId, "Order ID isn't present");
    }

    /*Test 5 : Verify order while Serve At Table And Pickup Delivery Options set at admin Ui and 'I am sitting at table' delivery option is selected at customer UI.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWhileAServeAtTableAndPickupDeliveryOptionsAtAdminUiAndIAmSittingAtTableDeliveryOptionAtCustomerUI() throws InterruptedException {
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
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Pickup' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableAndPickupDeliveryOptions(true);

        Reporter.log("Step 5 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 6 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer;
        landing.selectOffer(offerName);

        Reporter.log("Step 7 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 8 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 9 - On Your Order page, verify the offer is successfully added in cart");
        Assert.assertEquals(yourOrder.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice + ",00", "Price doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemName(1), Constants.StoreOffer, "Offer name doesn't match");
        Assert.assertEquals(yourOrder.getTotalPrice(), "€ " + Constants.StoreOfferPrice + ",00", "Total price doesn't match");

        Reporter.log("Step 10 - Select the radio button of 'I am sitting at table' delivery option");
        yourOrder.selectIAmSittingAtTableDeliveryOption();

        Reporter.log("Step 11 - Fill the form i.e table");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 12 - Click on the 'Submit Your Order' button");
        footer.clickOnSubmitYourOrderButton();

        Reporter.log("Step 13 - Verify that order is submitted successfully and thank you page is displayed");
        String expectedMessage = "Thank you";
        Assert.assertEquals(thankYou.getPageHeaderText(), expectedMessage, "Thank you message is incorrect");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 14 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 15 - Verify that order ID is present at first row in open tab at admin UI ");
        Assert.assertEquals(orders.getOrderID(1), orderId, "Order ID isn't present");
    }

    /*Test 6 : Verify order while Serve At Table And Delivery At Address Delivery Options set at admin Ui and I Want To Receive It At My Address delivery option is selected at customer UI.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWhileAServeAtTableAndDeliveryAtAddressDeliveryOptionsAtAdminUiAndIWantToReceiveAtAddressDeliveryOptionAtCustomerUI() throws InterruptedException {
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
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Delivery at address' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableAndDeliveryAtAddressDeliveryOptions(true);

        Reporter.log("Step 5 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 6 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer;
        landing.selectOffer(offerName);

        Reporter.log("Step 7 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 8 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 9 - On Your Order page, verify the offer is successfully added in cart");
        Assert.assertEquals(yourOrder.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice + ",00", "Price doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemName(1), Constants.StoreOffer, "Offer name doesn't match");
        Assert.assertEquals(yourOrder.getTotalPrice(), "€ " + Constants.StoreOfferPrice + ",00", "Total price doesn't match");

        Reporter.log("Step 10 - Select the radio button of 'I want to receive it at my address' delivery option");
        yourOrder.selectIWantReceiveAtAddressDeliveryOption();

        Reporter.log("Step 11 - Fill the form i.e email,address");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, true);

        Reporter.log("Step 12 - Click on the 'Submit Your Order' button");
        footer.clickOnSubmitYourOrderButton();

        Reporter.log("Step 13 - Verify that order is submitted successfully and thank you page is displayed");
        String expectedMessage = "Thank you";
        Assert.assertEquals(thankYou.getPageHeaderText(), expectedMessage, "Thank you message is incorrect");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 14 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 15 - Verify that order ID is present at first row in open tab at admin UI ");
        Assert.assertEquals(orders.getOrderID(1), orderId, "Order ID isn't present");
    }

    /*Test 7 : Verify order while Serve At Table And Delivery At Address Delivery Options set at admin Ui and Serve at table delivery option is selected at customer UI.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWhileAServeAtTableAndDeliveryAtAddressDeliveryOptionsAtAdminUiAndServeAtTableDeliveryOptionAtCustomerUI() throws InterruptedException {
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
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Delivery at address' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableAndDeliveryAtAddressDeliveryOptions(true);

        Reporter.log("Step 5 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 6 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer;
        landing.selectOffer(offerName);

        Reporter.log("Step 7 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 8 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 9 - On Your Order page, verify the offer is successfully added in cart");
        Assert.assertEquals(yourOrder.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice + ",00", "Price doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemName(1), Constants.StoreOffer, "Offer name doesn't match");
        Assert.assertEquals(yourOrder.getTotalPrice(), "€ " + Constants.StoreOfferPrice + ",00", "Total price doesn't match");

        Reporter.log("Step 10 - Select the radio button of 'I am sitting at a table' delivery option");
        yourOrder.selectIAmSittingAtTableDeliveryOption();

        Reporter.log("Step 11 - Fill the form i.e table");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        yourOrder.fillCustomerDetails(data, false);

        Reporter.log("Step 12 - Verify that total amount is displayed on the button and then Click on the 'Submit Your Order' button");
        footer.clickOnSubmitYourOrderButton();

        Reporter.log("Step 13 - Verify that order is submitted successfully and thank you page is displayed");
        String expectedMessage = "Thank you";
        Assert.assertEquals(thankYou.getPageHeaderText(), expectedMessage, "Thank you message is incorrect");
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 14 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 15 - Verify that order ID is present at first row in open tab at admin UI ");
        Assert.assertEquals(orders.getOrderID(1), orderId, "Order ID isn't present");
    }

    /*Test 8 : Verify that all selected Delivery Options at admin Ui are displayed at customer UI.*/
    @Test
    public void verifyDeliveryOptionIsDisplayAtCustomerUiWhileSelectCheckboxesOfDeliveryOptionAtAdminUi() throws InterruptedException {
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
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Delivery at address' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectAllDeliveryOptions(true);

        Reporter.log("Step 5 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 6 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer;
        landing.selectOffer(offerName);

        Reporter.log("Step 7 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 8 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 9 - On Your Order page, verify the offer is successfully added in cart");
        Assert.assertEquals(yourOrder.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice + ",00", "Price doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemName(1), Constants.StoreOffer, "Offer name doesn't match");
        Assert.assertEquals(yourOrder.getTotalPrice(), "€ " + Constants.StoreOfferPrice + ",00", "Total price doesn't match");

        Reporter.log("Step 10 - Verify that all delivery option is present at customer ui");
        Assert.assertTrue(yourOrder.isIAmSittingAtTableDeliveryOptionPresent(), "I am sitting at table delivery option is not present");
        Assert.assertTrue(yourOrder.isIWillPickItUpDeliveryOptionPresent(), "I will pick it up myself delivery option is not present");
        Assert.assertTrue(yourOrder.isIWantReceiveAtAddressDeliveryOptionPresent(), "I want to receive it at my address delivery option is not present");


    }

    /*Test 9 : Verify that all deselected Delivery Options at admin Ui are not displayed at customer UI.*/
    @Test
    public void verifyDeliveryOptionIsNotDisplayAtCustomerUiWhileUnselectCheckboxesOfDeliveryOptionAtAdminUi() throws InterruptedException {
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
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, UnSelect delivery options");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);

        Reporter.log("Step 5 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 6 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer;
        landing.selectOffer(offerName);

        Reporter.log("Step 7 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 8 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 9 - On Your Order page, verify the offer is successfully added in cart");
        Assert.assertEquals(yourOrder.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice + ",00", "Price doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemName(1), Constants.StoreOffer, "Offer name doesn't match");
        Assert.assertEquals(yourOrder.getTotalPrice(), "€ " + Constants.StoreOfferPrice + ",00", "Total price doesn't match");

        Reporter.log("Step 10 - Verify that any delivery option is not present at customer ui");
        Assert.assertFalse(yourOrder.isIAmSittingAtTableDeliveryOptionPresent(), "I am sitting at table delivery option is present");
        Assert.assertFalse(yourOrder.isIWillPickItUpDeliveryOptionPresent(), "I will pick it up myself delivery option is present");
        Assert.assertFalse(yourOrder.isIWantReceiveAtAddressDeliveryOptionPresent(), "I want to receive it at my address delivery option is present");


    }

    /*Test 10 : Verify that 'Pickup' and 'Serve at table' Delivery Options at admin Ui are displayed at customer UI.*/
    @Test
    public void verifyPickupAndServeAtTableDeliveryOptionIsDisplayAtCustomerUiWhileSelectCheckboxesOfDeliveryOptionAtAdminUi() throws InterruptedException {
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
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select the 'Serve at table' and 'Delivery at address' delivery option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableAndPickupDeliveryOptions(true);

        Reporter.log("Step 5 - Navigate to Customer Ui");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl);

        Reporter.log("Step 6 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer;
        landing.selectOffer(offerName);

        Reporter.log("Step 7 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 8 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 9 - On Your Order page, verify the offer is successfully added in cart");
        Assert.assertEquals(yourOrder.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemPrice(1), "€ " + Constants.StoreOfferPrice + ",00", "Price doesn't match");
        Assert.assertEquals(yourOrder.getOrderItemName(1), Constants.StoreOffer, "Offer name doesn't match");
        Assert.assertEquals(yourOrder.getTotalPrice(), "€ " + Constants.StoreOfferPrice + ",00", "Total price doesn't match");

        Reporter.log("Step 10 - Verify that only 'Pickup' and 'Serve at table' delivery option is present at customer ui");
        Assert.assertTrue(yourOrder.isIAmSittingAtTableDeliveryOptionPresent(), "I am sitting at table delivery option is not present");
        Assert.assertTrue(yourOrder.isIWillPickItUpDeliveryOptionPresent(), "I will pick it up myself delivery option is not present");
        Assert.assertFalse(yourOrder.isIWantReceiveAtAddressDeliveryOptionPresent(), "I want to receive it at my address delivery option is present");

    }
}




