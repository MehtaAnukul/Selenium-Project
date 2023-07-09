package gonnaorder.admin.store.table;


import base.BaseTest;
import com.google.zxing.NotFoundException;
import datafactory.gonnaorder.customer.CustomerOrderData;
import dataobjects.gonnaorder.customer.CustomerOrder;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.gonnaorder.admin.account.LoginPO;
import pageobjects.gonnaorder.admin.store.ShareAndPreview.SharePagePO;
import pageobjects.gonnaorder.admin.store.orders.OrdersDetailsPO;
import pageobjects.gonnaorder.admin.store.orders.OrdersPO;
import pageobjects.gonnaorder.admin.store.settings.OrderingPO;
import pageobjects.gonnaorder.admin.store.settings.SettingPO;
import pageobjects.gonnaorder.admin.store.table.TablePO;
import pageobjects.gonnaorder.customer.LandingPO;
import pageobjects.gonnaorder.customer.ThankYouPO;
import pageobjects.gonnaorder.customer.YourOrderPO;
import pageobjects.gonnaorder.customer.common.FooterPO;
import pageobjects.gonnaorder.customer.common.PaymentPagePO;
import utilities.Constants;
import utilities.JavaHelpers;

import java.io.IOException;


public class TableTest extends BaseTest {


    /*Test 1 : Verify that table id is present at url of customer ui while scan the QR code at admin ui.*/
    @Test
    public void verifyTableIdIsPresentCustomerUiUrlWhileScanQRCodeAtAdminUi() throws InterruptedException, IOException, NotFoundException {
        LoginPO login = new LoginPO(driver);
        TablePO table = new TablePO(driver);
        SettingPO setting = new SettingPO(driver);
        OrderingPO order = new OrderingPO(driver);
        JavaHelpers java = new JavaHelpers();
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);

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
        order.selectDeselectServeAtTableDeliveryOption(true);

        Reporter.log("Step 5 - Navigate to Table page");
        String tablePageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId + "/locations";
        selenium.navigateToPage(tablePageUrl);

        Reporter.log("Step 6 - Click on the '1' table and then click on the link to download image");
        table.clickOnTableLink();

        java.deleteAllFilesFromFolder(java.getDownloadPath());
        table.clickOnImageDownloadLink();

        Reporter.log("Step 7 - Scan the QR code and verify the url of QR code and url of Customer UI");
        String imageName = Constants.StoreAliasName + "-" + Constants.StoreTable + "-qr.png";
        String url = JavaHelpers.readQRCode(java.getDownloadPath()+ imageName);
        String customerUrl = Constants.StoreCustomerUiUrl + "#l/" + Constants.StoreTable;
        Assert.assertEquals(url.split(":")[1], customerUrl.split(":")[1], "QR Code url doesn't match");

        Reporter.log("Step 8 - Navigate to customer ui");
        selenium.navigateToPage(customerUrl);

        Reporter.log("Step 9 - Verify that category is displayed on landing page");
        Assert.assertTrue(landing.isCategoryPresent(Constants.StoreCategory), "Category is not present ");

        Reporter.log("Step 10 - Click on the 'Automation Offer'");
        String offerName = Constants.StoreOffer;
        selenium.hardWait(3);
        landing.selectOffer(offerName);

        Reporter.log("Step 11 - Click on the 'Add to your order' button");
        footer.clickOnAddToYourOrderButton();

        Reporter.log("Step 12 - Click on the 'View Order' button");
        footer.clickOnViewOrderButton();

        Reporter.log("Step 13 - On Your Order page, verify that table number is present which was scan at admin UI");
        Assert.assertEquals(yourOrder.getTableNumber(), "Your table " + Constants.StoreTable, "Table number doesn't match");
    }

    /*Test 2 : Verify that table id is present at url of customer ui and Place order successfully with Payment while scan the QR code at admin ui .*/
    @Test(groups = {"payment"})
    public void verifyTableIdIsPresentCustomerUiUrlAndPlaceOrderSuccessfullyWithPaymentWhileScanQRCodeAtAdminUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        OrderingPO order = new OrderingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);
        PaymentPagePO paymentPage = new PaymentPagePO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is enabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableDeliveryOption(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentMandatoryOption();

        Reporter.log("Step 9 - Navigate to customer ui");
        String customerUrl = Constants.StoreCustomerUiUrl1 + "#l/" + Constants.StoreTable1;
        selenium.navigateToPage(customerUrl);

        Reporter.log("Step 10 - Select offer, Verify table number is present and Place Order with Payment from Customer UI'");
        String offerName = Constants.StoreOffer1;
        selenium.hardWait(3);
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        Assert.assertEquals(yourOrder.getTableNumber(), "Your table " + Constants.StoreTable1, "Table number doesn't match");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        yourOrder.fillCustomerPaymentDetails(data);
        footer.clickOnProceedToPaymentButton();
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeaderText(),"3D Secure" + "\n" + "Test Payment Page", "Credit card Header text does not match");
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeadingText(),"This is a test payment" + " of" +" ₹" + Constants.StoreOfferPrice1 + ".00 INR" + " using 3D Secure." ,"Credit Card Heading text does not match");
        paymentPage.clickOnCreditCardPaymentSuccessButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 11 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 12 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 2), "room_service Serve room" + data.getTable(), "Customer's table number doesn't Match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "₹ " + Constants.StoreOfferPrice3 + ".00" + " - " + "Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 13 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 14 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getServeAtTableDeliveryMode(), "room_service Serve", "Delivery mode doesn't match");
        Assert.assertEquals(orderDetails.getTableNumber(), "room" + data.getTable(), "Table Number doesn't match");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "₹ " + Constants.StoreOfferPrice1 + ".00" + " Paid", "Payment Status doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer1, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice1 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "₹ " + Constants.StoreOfferPrice1 + ".00", "Total price doesn't match");
        Assert.assertFalse(orderDetails.isSpecialNotePresent(), "Special Note Present");
    }


    /*Test 3 : Verify that table id is present at url of customer ui and Place order successfully without Payment when payment optional while scan the QR code at admin ui .*/
    @Test
    public void verifyTableIdIsPresentCustomerUiUrlAndPlaceOrderSuccessfullyWithoutPaymentWhenPaymentOptionalWhileScanQRCodeAtAdminUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        OrderingPO order = new OrderingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is enabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableDeliveryOption(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Optional' payment option");
        order.selectPaymentOptionalOption();

        Reporter.log("Step 6 - Navigate to customer ui");
        String customerUrl = Constants.StoreCustomerUiUrl1 + "#l/" + Constants.StoreTable1;
        selenium.navigateToPage(customerUrl);

        Reporter.log("Step 7 - Select offer, Verify table number is present and Place Order with Payment from Customer UI'");
        String offerName = Constants.StoreOffer1;
        selenium.hardWait(3);
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        Assert.assertEquals(yourOrder.getTableNumber(), "Your table " + Constants.StoreTable1, "Table number doesn't match");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        footer.clickOnSubmitYourOrderButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 9 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 3), "room_service Serve room" + data.getTable(), "Customer's table number doesn't Match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "₹ " + Constants.StoreOfferPrice1 + ".00" + " - " + "Paid", "Amount and Payment Status Doesn't Match");


        Reporter.log("Step 10 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 11 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getServeAtTableDeliveryMode(), "room_service Serve", "Delivery mode doesn't match");
        Assert.assertEquals(orderDetails.getTableNumber(), "room" + data.getTable(), "Table Number doesn't match");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "₹ " + Constants.StoreOfferPrice1 + ".00" + " Not Paid", "Payment Status doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer1, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice1 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "₹ " + Constants.StoreOfferPrice1 + ".00", "Total price doesn't match");
        Assert.assertFalse(orderDetails.isSpecialNotePresent(), "Special Note Present");
    }

    /*Test 4 : Verify that table id is present at url of customer ui and Place order successfully with Payment when payment optional while scan the QR code at admin ui .*/
    @Test(groups = {"payment"})
    public void verifyTableIdIsPresentCustomerUiUrlAndPlaceOrderSuccessfullyWithPaymentWhenPaymentOptionalWhileScanQRCodeAtAdminUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        OrderingPO order = new OrderingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);
        PaymentPagePO paymentPage = new PaymentPagePO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is enabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableDeliveryOption(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Optional' payment option");
        order.selectPaymentOptionalOption();

        Reporter.log("Step 6 - Navigate to customer ui");
        String customerUrl = Constants.StoreCustomerUiUrl1 + "#l/" + Constants.StoreTable1;
        selenium.navigateToPage(customerUrl);

        Reporter.log("Step 7 - Select offer, Verify table number is present and Place Order with Payment from Customer UI'");
        String offerName = Constants.StoreOffer1;
        selenium.hardWait(3);
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        Assert.assertEquals(yourOrder.getTableNumber(), "Your table " + Constants.StoreTable1, "Table number doesn't match");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
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
        Assert.assertEquals(orders.getData(rowIndex, 3), "room_service Serve room" + data.getTable(), "Customer's table number doesn't Match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "₹ " + Constants.StoreOfferPrice1 + ".00" + " - " + "Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 10 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 11 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getServeAtTableDeliveryMode(), "room_service Serve", "Delivery mode doesn't match");
        Assert.assertEquals(orderDetails.getTableNumber(), "room" + data.getTable(), "Table Number doesn't match");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "₹ " + Constants.StoreOfferPrice1 + ".00" + " Paid", "Payment Status doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer1, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice1 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "₹ " + Constants.StoreOfferPrice1 + ".00", "Total price doesn't match");
        Assert.assertFalse(orderDetails.isSpecialNotePresent(), "Special Note Present");
    }

    /*Test 5 : Verify that table id is present at url of customer ui and Place order successfully when Payment Disable while scan the QR code at admin ui .*/
    @Test
    public void verifyTableIdIsPresentCustomerUiUrlAndPlaceOrderSuccessfullyWhenPaymentDisableWhileScanQRCodeAtAdminUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        OrderingPO order = new OrderingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is enabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableDeliveryOption(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Disable' payment option");
        order.selectPaymentDisabledOption();

        Reporter.log("Step 6 - Navigate to customer ui");
        String customerUrl = Constants.StoreCustomerUiUrl1 + "#l/" + Constants.StoreTable1;
        selenium.navigateToPage(customerUrl);

        Reporter.log("Step 7 - Select offer, Verify table number is present and Place Order with Payment from Customer UI'");
        String offerName = Constants.StoreOffer1;
        selenium.hardWait(3);
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        Assert.assertEquals(yourOrder.getTableNumber(), "Your table " + Constants.StoreTable1, "Table number doesn't match");
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        footer.clickOnSubmitYourOrderButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 8 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 9 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 3), "room_service Serve room" + data.getTable(), "Customer's table number doesn't Match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "₹ " + Constants.StoreOfferPrice1 + ".00" + " - " + "Paid", "Amount and Payment Status Doesn't Match");


        Reporter.log("Step 10 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 11 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getServeAtTableDeliveryMode(), "room_service Serve", "Delivery mode doesn't match");
        Assert.assertEquals(orderDetails.getTableNumber(), "room" + data.getTable(), "Table Number doesn't match");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "₹ " + Constants.StoreOfferPrice1 + ".00" + " Not Paid", "Payment Status doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer1, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice1 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "₹ " + Constants.StoreOfferPrice1 + ".00", "Total price doesn't match");
        Assert.assertFalse(orderDetails.isSpecialNotePresent(), "Special Note Present");
    }

    /*Test 6 : Verify that table id is not present at url and user add table number manually from customer ui while scan the QR code at admin ui and Place order successfully with Payment.*/
    @Test(groups = {"payment"})
    public void verifyTableIdIsNotPresentCustomerUiUrlAndPlaceOrderSuccessfullyWithPaymentWhileScanQRCodeAtAdminUi() throws InterruptedException, IOException, NotFoundException {
        LoginPO login = new LoginPO(driver);
        SharePagePO sharePage = new SharePagePO(driver);
        SettingPO setting = new SettingPO(driver);
        OrderingPO order = new OrderingPO(driver);
        JavaHelpers java = new JavaHelpers();
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);
        PaymentPagePO paymentPage = new PaymentPagePO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is enabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableDeliveryOption(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentMandatoryOption();

        Reporter.log("Step 6 - Navigate to the Share & preview page and download QR code");
        String SharePageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/share";
        selenium.navigateToPage(SharePageUrl);

        java.deleteAllFilesFromFolder(java.getDownloadPath());
        sharePage.clickOnImageDownloadLinkOfSharePage();

        Reporter.log("Step 7 - Scan the QR code and verify the url of QR code and url of Customer UI");
        String imageName = Constants.StoreAliasName1 + "-qr.png";
        String url = JavaHelpers.readQRCode(java.getDownloadPath() + imageName);
        String customerUrl = Constants.StoreCustomerUiUrl1;
        Assert.assertEquals(url.split(":")[1] + "/", customerUrl.split(":")[1], "QR Code url doesn't match");

        Reporter.log("Step 8 - Navigate to customer ui");
        selenium.navigateToPage(customerUrl);

        Reporter.log("Step 9 - Verify that category is displayed on landing page");
        Assert.assertTrue(landing.isCategoryPresent(Constants.StoreCategory1), "Category is not present ");

        Reporter.log("Step 10 - Select offer,Add Table number,Payment Details and Submit Order'");
        String offerName = Constants.StoreOffer1;
        selenium.hardWait(3);
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

        Reporter.log("Step 11 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 12 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 2), "room_service Serve room" + data.getTable() + " person " + data.getName(), "Customer's table number doesn't Match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "₹ " + Constants.StoreOfferPrice1 + ".00" + " - " + "Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 13 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 14 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getServeAtTableDeliveryMode(), "room_service Serve " + data.getName(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getTableNumber(), "room" + data.getTable(), "Table number doesn't match");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "₹ " + Constants.StoreOfferPrice1 + ".00" + " Paid", "Payment Status doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer1, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice1 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "₹ " + Constants.StoreOfferPrice1 + ".00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

    /*Test 7 : Verify that table id is not present at url and user add table number manually from customer ui while scan the QR code at admin ui and Place order successfully without Payment.*/
    @Test
    public void verifyTableIdIsNotPresentCustomerUiUrlAndPlaceOrderSuccessfullyWithoutPaymentWhenPaymentOptionalWhileScanQRCodeAtAdminUi() throws InterruptedException, IOException, NotFoundException {
        LoginPO login = new LoginPO(driver);
        SharePagePO sharePage = new SharePagePO(driver);
        SettingPO setting = new SettingPO(driver);
        OrderingPO order = new OrderingPO(driver);
        JavaHelpers java = new JavaHelpers();
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is enabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableDeliveryOption(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentOptionalOption();

        Reporter.log("Step 6 - Navigate to the Share & preview page and download QR code");
        String SharePageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/share";
        selenium.navigateToPage(SharePageUrl);

        java.deleteAllFilesFromFolder(java.getDownloadPath());
        sharePage.clickOnImageDownloadLinkOfSharePage();

        Reporter.log("Step 7 - Scan the QR code and verify the url of QR code and url of Customer UI");
        String imageName = Constants.StoreAliasName1 + "-qr.png";
        String url = JavaHelpers.readQRCode(java.getDownloadPath() + imageName);
        String customerUrl = Constants.StoreCustomerUiUrl1;
        Assert.assertEquals(url.split(":")[1] + "/", customerUrl.split(":")[1], "QR Code url doesn't match");

        Reporter.log("Step 8 - Navigate to customer ui");
        selenium.navigateToPage(customerUrl);

        Reporter.log("Step 9 - Verify that category is displayed on landing page");
        Assert.assertTrue(landing.isCategoryPresent(Constants.StoreCategory1), "Category is not present ");

        Reporter.log("Step 10 - Select offer,Add Table number,Payment Details and Submit Order'");
        String offerName = Constants.StoreOffer1;
        selenium.hardWait(3);
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        yourOrder.fillCustomerDetails(data, false);
        footer.clickOnSubmitYourOrderButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 11 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 12 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 2), "room_service Serve room" + data.getTable() + " person " + data.getName(), "Customer's table number doesn't Match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "₹ " + Constants.StoreOfferPrice1 + ".00" + " - " + "Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 13 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 14 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getServeAtTableDeliveryMode(), "room_service Serve " + data.getName(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getTableNumber(), "room" + data.getTable(), "Table number doesn't match");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "₹ " + Constants.StoreOfferPrice1 + ".00" + " Not Paid", "Payment Status doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer1, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice1 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "₹ " + Constants.StoreOfferPrice1 + ".00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

    /*Test 8 : Verify that table id is not present at url and user add table number manually from customer ui while scan the QR code at admin ui and Place order successfully with Payment.*/
    @Test(groups = {"payment"})
    public void verifyTableIdIsNotPresentCustomerUiUrlAndPlaceOrderSuccessfullyWithPaymentWhenPaymentOptionalWhileScanQRCodeAtAdminUi() throws InterruptedException, IOException, NotFoundException {
        LoginPO login = new LoginPO(driver);
        SharePagePO sharePage = new SharePagePO(driver);
        SettingPO setting = new SettingPO(driver);
        OrderingPO order = new OrderingPO(driver);
        JavaHelpers java = new JavaHelpers();
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);
        PaymentPagePO paymentPage = new PaymentPagePO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is enabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableDeliveryOption(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentOptionalOption();

        Reporter.log("Step 6 - Navigate to the Share & preview page and download QR code");
        String SharePageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/share";
        selenium.navigateToPage(SharePageUrl);

        java.deleteAllFilesFromFolder(java.getDownloadPath());
        sharePage.clickOnImageDownloadLinkOfSharePage();

        Reporter.log("Step 7 - Scan the QR code and verify the url of QR code and url of Customer UI");
        String imageName = Constants.StoreAliasName1 + "-qr.png";
        String url = JavaHelpers.readQRCode(java.getDownloadPath() + imageName);
        String customerUrl = Constants.StoreCustomerUiUrl1;
        Assert.assertEquals(url.split(":")[1] + "/", customerUrl.split(":")[1], "QR Code url doesn't match");

        Reporter.log("Step 8 - Navigate to customer ui");
        selenium.navigateToPage(customerUrl);

        Reporter.log("Step 9 - Verify that category is displayed on landing page");
        Assert.assertTrue(landing.isCategoryPresent(Constants.StoreCategory1), "Category is not present ");

        Reporter.log("Step 10 - Select offer,Add Table number,Payment Details and Submit Order'");
        String offerName = Constants.StoreOffer1;
        selenium.hardWait(3);
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        yourOrder.fillCustomerDetails(data, false);
        yourOrder.selectPayOnlineCheckBox();
        yourOrder.fillCustomerPaymentDetails(data);
        footer.clickOnProceedToPaymentButton();
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeaderText(),"3D Secure" + "\n" + "Test Payment Page", "Credit card Header text does not match");
        Assert.assertEquals(paymentPage.getCreditCardPaymentHeadingText(),"This is a test payment" + " of" +" ₹" + Constants.StoreOfferPrice1 + ".00 INR" + " using 3D Secure." ,"Credit Card Heading text does not match");
        paymentPage.clickOnCreditCardPaymentSuccessButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 11 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 12 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 2), "room_service Serve room" + data.getTable() + " person " + data.getName(), "Customer's table number doesn't Match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "₹ " + Constants.StoreOfferPrice1 + ".00" + " - " + "Paid", "Amount and Payment Status Doesn't Match");

        Reporter.log("Step 13 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 14 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getServeAtTableDeliveryMode(), "room_service Serve " + data.getName(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getTableNumber(), "room" + data.getTable(), "Table number doesn't match");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "₹ " + Constants.StoreOfferPrice1 + ".00" + " Paid", "Payment Status doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer1, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice1 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "₹ " + Constants.StoreOfferPrice1 + ".00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

    /*Test 9 : Verify that table id is not present at url of customer ui and user add table number manually from customer ui and Place order successfully when Payment Disable while scan the QR code at admin ui .*/
    @Test
    public void verifyTableIdIsNotPresentCustomerUiUrlAndPlaceOrderSuccessfullyWhenPaymentDisableWhileScanQRCodeAtAdminUi() throws InterruptedException, IOException, NotFoundException {
        LoginPO login = new LoginPO(driver);
        SharePagePO sharePage = new SharePagePO(driver);
        SettingPO setting = new SettingPO(driver);
        OrderingPO order = new OrderingPO(driver);
        JavaHelpers java = new JavaHelpers();
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrdersPO orders = new OrdersPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrdersDetailsPO orderDetails = new OrdersDetailsPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, making sure Ordering is enabled");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectServeAtTableDeliveryOption(true);

        Reporter.log("Step 5 - Select the radio button of 'Payment Mandatory' payment option");
        order.selectPaymentDisabledOption();

        Reporter.log("Step 6 - Navigate to the Share & preview page and download QR code");
        String SharePageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/share";
        selenium.navigateToPage(SharePageUrl);

        java.deleteAllFilesFromFolder(java.getDownloadPath());
        sharePage.clickOnImageDownloadLinkOfSharePage();

        Reporter.log("Step 7 - Scan the QR code and verify the url of QR code and url of Customer UI");
        String imageName = Constants.StoreAliasName1 + "-qr.png";
        String url = JavaHelpers.readQRCode(java.getDownloadPath() + imageName);
        String customerUrl = Constants.StoreCustomerUiUrl1;
        Assert.assertEquals(url.split(":")[1] + "/", customerUrl.split(":")[1], "QR Code url doesn't match");

        Reporter.log("Step 8 - Navigate to customer ui");
        selenium.navigateToPage(customerUrl);

        Reporter.log("Step 9 - Verify that category is displayed on landing page");
        Assert.assertTrue(landing.isCategoryPresent(Constants.StoreCategory1), "Category is not present ");

        Reporter.log("Step 10 - Select offer,Add Table number,Payment Details and Submit Order'");
        String offerName = Constants.StoreOffer1;
        selenium.hardWait(3);
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();
        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        yourOrder.fillCustomerDetails(data, false);
        footer.clickOnSubmitYourOrderButton();
        String orderId = thankYou.getOrderID();

        Reporter.log("Step 11 - Navigate to the Orders page at admin UI");
        String ordersPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId1 + "/orders";
        selenium.navigateToPage(ordersPageUrl);

        Reporter.log("Step 12 - Verify that order ID is present at first row in open tab at admin UI ");
        int rowIndex = orders.getRowIndexForOrderID(orderId);
        Assert.assertEquals(orders.getData(rowIndex, 2), "room_service Serve room" + data.getTable() + " person " + data.getName(), "Customer's table number doesn't Match");
        Assert.assertEquals(orders.getData(rowIndex, 4), "₹ " + Constants.StoreOfferPrice1 + ".00" + " - " + "Paid", "Amount and Payment Status Doesn't Match");


        Reporter.log("Step 13 - Click on order to open order detail page");
        orders.clickOnOrderID(rowIndex);

        Reporter.log("Step 14 - Verify that all detail are present & correct in Order Detail page");
        Assert.assertEquals("Order " + orderId + " Help on Orders", orderDetails.getOrderIDFromHeader(), "Order id doesn't match");
        Assert.assertEquals(orderDetails.getServeAtTableDeliveryMode(), "room_service Serve " + data.getName(), "Delivery mode and Customer Name doesn't match");
        Assert.assertEquals(orderDetails.getTableNumber(), "room" + data.getTable(), "Table number doesn't match");
        Assert.assertEquals(orderDetails.getPaymentDetail(), "₹ " + Constants.StoreOfferPrice1 + ".00" + " Not Paid", "Payment Status doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemQuantity(1), "1 x", "Quantity doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemName(1), Constants.StoreOffer1, "Offer Name doesn't Match");
        Assert.assertEquals(orderDetails.getOrderItemPrice(1), "₹ " + Constants.StoreOfferPrice1 + ".00", "Price doesn't match");
        Assert.assertEquals(orderDetails.getOrderItemTotalPrice(2), "₹ " + Constants.StoreOfferPrice1 + ".00", "Total price doesn't match");
        Assert.assertEquals(orderDetails.getOrderNote(), data.getSpecialNote(), "Note doesn't match");
    }

}


