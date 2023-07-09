package gonnaorder.admin.store.settings;

import base.BaseTest;
import datafactory.gonnaorder.customer.CustomerOrderData;
import dataobjects.gonnaorder.customer.CustomerOrder;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import pageobjects.gonnaorder.admin.account.LoginPO;
import pageobjects.gonnaorder.admin.store.orders.CloseOrderPO;
import pageobjects.gonnaorder.admin.store.orders.OrdersPO;
import pageobjects.gonnaorder.admin.store.settings.OrderingPO;
import pageobjects.gonnaorder.admin.store.settings.SettingPO;
import pageobjects.gonnaorder.customer.LandingPO;
import pageobjects.gonnaorder.customer.ThankYouPO;
import pageobjects.gonnaorder.customer.YourOrderPO;
import pageobjects.gonnaorder.customer.common.FooterPO;
import utilities.Constants;


public class OrderingTests4 extends BaseTest {

    /*Test 1 (31) : Verify that delivery options at customer ui while 'Shopping Basket' toggle is enabled at admin ui.*/
    @Test
    public void verifyThankYouMessageDisplayAtCustomerUiWhileShoppingBasketToggleIsEnabledAtAdminUi() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrderingPO order = new OrderingPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);

        Reporter.log("Step 1 - Navigate to admin login page");
        selenium.navigateToPage(Constants.ADMIN_URL);

        Reporter.log("Step 2 - Entering valid sign in details and login to application");
        login.fillLoginDetailsAndPerformLogin(Constants.EMAIL, Constants.PASSWORD);

        Reporter.log("Step 3 - Navigate to Store Setting page");
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId8 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select delivery option, select Payment option");
        setting.clickOnOrderingButton();
        order.selectPaymentOptionalOption();
        order.selectDeselectOrderingToggle(false);
        Assert.assertTrue(order.IsShoppingBasketToggleEnabled(), "Shopping Basket Toggle is not shown enabled");

        Reporter.log("Step 5 - Navigate to Customer Ui and select offer to submit order");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl8);
        String offerName = Constants.StoreOffer8;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        Reporter.log("Step 6 - Verify that delivery options are not present at customer ui");
        Assert.assertFalse(yourOrder.isIAmSittingAtTableDeliveryOptionPresent(), "I am sitting at table delivery option is present");
        Assert.assertFalse(yourOrder.isIWillPickItUpDeliveryOptionPresent(), "I will pick it up myself delivery option is present");
        Assert.assertFalse(yourOrder.isIWantReceiveAtAddressDeliveryOptionPresent(), "I want to receive it at my address delivery option is present");

        Reporter.log("Step 7 - Verify that thank you message is displayed");
        String expectedMessage = "Thank you!" + "\n" + "Please share your order with the store when you are ready";
        Assert.assertEquals(thankYou.getThankYouMessageText(), expectedMessage, "Thank you message is incorrect");

    }

    /*Test 2 (32) : Verify that delivery options at customer ui while 'Shopping Basket Enable' toggle is Disable at admin ui.*/
    @Test
    public void verifyPlaceOrderSuccessfullyWhileShoppingBasketToggleIsDisabledAtAdminUi() throws InterruptedException {
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
        String settingsPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId8 + "/settings/store-edit";
        selenium.navigateToPage(settingsPageUrl);

        Reporter.log("Step 4 - Click on the 'Ordering' tab, select delivery option, select Payment option");
        setting.clickOnOrderingButton();
        order.selectPaymentOptionalOption();
        order.selectDeselectOrderingToggle(true);
        Assert.assertFalse(order.IsShoppingBasketToggleEnabled(), "Shopping Basket Toggle is shown enabled");
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);

        Reporter.log("Step 5 - Navigate to Customer Ui and select Offer to submit order");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl8);
        String offerName = Constants.StoreOffer8;
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
        String orderPageUrl = Constants.ADMIN_URL + "/manager/stores/" + Constants.StoreId8 + "/orders";
        selenium.navigateToPage(orderPageUrl);

        Reporter.log("Step 7 - Click on the Order Id which is present in first row");
        selenium.hardWait(10);
        Assert.assertEquals(orderId, orders.getOrderID(1), "Order ID isn't present");
    }

}


