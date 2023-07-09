package gonnaorder.admin.store.settings;

import base.BaseTest;
import datafactory.gonnaorder.admin.PayPalAndOtherPaymentData;
import datafactory.gonnaorder.customer.CustomerOrderData;
import dataobjects.gonnaorder.admin.PayPalAndOtherPayment;
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


public class PaymentProviderTests1 extends BaseTest {


    /*Test 11 : Verify enable Credit Card and Bancontact and iDEAL Payment Option at admin Ui and Displayed Credit Card and Bancontact and iDEAL Option at customer UI.*/
    @Test (groups = {"payment"})
    public void verifyEnableCreditCardAndBancontactAndiDEALPaymentOptionAtAdminUiAndDisplayedCreditCardAndBancontactAndiDEAlOptionAtCustomerUI() throws InterruptedException {
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

        Reporter.log("Step 5 - Enable the All payment provider");
        paymentProviders.enableDisableAllPaymentProviderOptions(true);

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

        Reporter.log("Step 12 - Verify that pay online section is present and all payment methods are displayed at CustomerUI");
        Assert.assertTrue(yourOrder.isPayOnlineTextPresent(),"Pay online section is not present");
        Assert.assertEquals(yourOrder.getiDEALPaymentText(),"iDEAL","iDeal Payment method text does not match");
        yourOrder.selectIDEALPaymentOption();
        Assert.assertTrue(yourOrder.isiDealBankDropdownPresent(),"iDEAL Bank Dropdown is not present");
        Assert.assertEquals(yourOrder.getCreditCardPaymentText(),"Credit card","Credit Card payment method text does not match");
        yourOrder.selectCreditCardPaymentOption();
        Assert.assertEquals(yourOrder.getBanContactPaymentText(),"Bancontact","BanContact payment method text does not match");
        yourOrder.selectBanContactPaymentOption();
        Assert.assertTrue(footer.verifyProceedToPaymentButtonIsEnabled(),"Proceed to Payment button is not Enabled");
    }



    /*Test 12 : Verify that enable Paypal payment method from AdminUi and PayPal payment option displayed at customer UI.*/
    @Test(groups = {"payment"})
    public void verifyEnablePayPalPaymentOptionAtAdminUiAndDisplayedPaypalOptionAtThankYouPageOfCustomerUI() throws InterruptedException {
        LoginPO login = new LoginPO(driver);
        SettingPO setting = new SettingPO(driver);
        PaymentProvidersPO paymentProviders = new PaymentProvidersPO(driver);
        LandingPO landing = new LandingPO(driver);
        FooterPO footer = new FooterPO(driver);
        YourOrderPO yourOrder = new YourOrderPO(driver);
        OrderingPO order = new OrderingPO(driver);
        ThankYouPO thankYou = new ThankYouPO(driver);
        OrdersPO orders = new OrdersPO(driver);
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

        Reporter.log("Step 5 - Enter PayPal.Me Link and Make sure PayPal.Me toggle gets enabled");
        PayPalAndOtherPayment data1 = new PayPalAndOtherPaymentData().getPayPalAndOtherPaymentData();
        paymentProviders.fillPayPalMELink(data1);
        Assert.assertTrue(paymentProviders.verifyPayPalMEToggleIsEnabled(),"PayPal is not Enabled");

        Reporter.log("Step 6 - Click on the 'Ordering' tab, select the 'Pickup' delivery option & 'Payment Optional' option");
        setting.clickOnOrderingButton();
        order.selectDeselectOrderingToggle(true);
        order.selectDeselectAllDeliveryOptions(false);
        order.selectDeselectPickupDeliveryOption(true);
        order.selectPaymentOptionalOption();

        Reporter.log("Step 7 - Navigate to Customer Ui, select offer and Submit Order");
        selenium.navigateToPage(Constants.StoreCustomerUiUrl9);
        String offerName = Constants.StoreOffer9;
        landing.selectOffer(offerName);
        footer.clickOnAddToYourOrderButton();
        footer.clickOnViewOrderButton();

        CustomerOrder data = new CustomerOrderData().getCustomerDetailsData();
        data.setTable("");
        yourOrder.fillCustomerDetails(data, false);
        footer.clickOnSubmitYourOrderButton();
        selenium.hardWait(5);

        Reporter.log("Step 8 - Verify that PayPal.Me Link displayed at Thank You Page and verify the URL and Payment Details after navigate to Payment page Link ");
        Assert.assertTrue(yourOrder.isPayOnlineTextPresent(), "Pay online section is not present");
        thankYou.ClickOnPayPalPaymentButton();
        selenium.switchToWindow(2);
        String PayPalURL = driver.getCurrentUrl();
        Assert.assertEquals(PayPalURL, Constants.PAYPALURL + data1.getPayPalMeLink() + "/" + Constants.StoreOfferPrice9 + "EUR", "PAYPAL URL doesn't match" );
        Assert.assertEquals(paymentPage.getPayPalPaymentHeaderText(), "GonnaOrder B.V.", "PAYPAL Header doesn't match" );
        Assert.assertEquals(paymentPage.getPayPalStoreLinkText(), "paypal.me/gonnaorder", "PAYPAL Store link text doesn't match" );
        Assert.assertEquals(paymentPage.getPayPalCurrencySymbol(), "€", "PAYPAL Currency doesn't match" );
        Assert.assertEquals(paymentPage.getPayPalTotalAmount(), Constants.StoreOfferPrice9 + ".00", "PAYPAL amount doesn't match" );
    }
}



