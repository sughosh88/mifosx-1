package org.mifosplatform.integrationtests.common.charges;

import java.util.HashMap;

import org.mifosplatform.integrationtests.common.CommonConstants;
import org.mifosplatform.integrationtests.common.Utils;

import com.google.gson.Gson;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

@SuppressWarnings({ "unused", "rawtypes" })
public class ChargesHelper {

    private static final String CHARGES_URL = "/mifosng-provider/api/v1/charges";
    private static final String CREATE_CHARGES_URL = CHARGES_URL + "?" + Utils.TENANT_IDENTIFIER;

    private static final Integer CHARGE_APPLIES_TO_LOAN = 1;
    private static final Integer CHARGE_APPLIES_TO_SAVINGS = 2;

    private static final Integer CHARGE_DISBURSEMENT_FEE = 1;
    private static final Integer CHARGE_SPECIFIED_DUE_DATE = 2;
    private static final Integer CHARGE_SAVINGS_ACTIVATION_FEE = 3;
    private static final Integer CHARGE_WITHDRAWAL_FEE = 5;
    private static final Integer CHARGE_ANNUAL_FEE = 6;
    private static final Integer CHARGE_MONTHLY_FEE = 7;
    private static final Integer CHARGE_INSTALLMENT_FEE = 8;
    private static final Integer CHARGE_OVERDUE_INSTALLMENT_FEE = 9;
    private static final Integer CHARGE_OVERDRAFT_FEE = 10;

    private static final Integer CHARGE_CALCULATION_TYPE_FLAT = 1;
    private static final Integer CHARGE_CALCULATION_TYPE_PERCENTAGE_AMOUNT = 2;
    private static final Integer CHARGE_CALCULATION_TYPE_PERCENTAGE_AMOUNT_AND_INTEREST = 3;
    private static final Integer CHARGE_CALCULATION_TYPE_PERCENTAGE_INTEREST = 4;

    private static final Integer CHARGE_PAYMENT_MODE_REGULAR = 0;
    private static final Integer CHARGE_PAYMENT_MODE_ACCOUNT_TRANSFER = 1;

    private static final Integer CHARGE_FEE_FREQUENCY_DAYS = 0;
    private static final Integer CHARGE_FEE_FREQUENCY_WEEKS = 1;
    private static final Integer CHARGE_FEE_FREQUENCY_MONTHS = 2;
    private static final Integer CHARGE_FEE_FREQUENCY_YEARS = 3;

    private final static boolean active = true;
    private final static boolean penalty = false;
    private final static boolean addfeefrequency = true;
    private final static String amount = "100";
    private final static String currencyCode = "USD";
    public final static String feeOnMonthDay = "04 March";
    private final static String monthDayFormat = "dd MMM";

    public static String getSavingsSpecifiedDueDateJSON() {
        final HashMap<String, Object> map = populateDefaultsForSavings();
        map.put("chargeTimeType", CHARGE_SPECIFIED_DUE_DATE);
        map.put("feeInterval", 2);
        String chargesCreateJson = new Gson().toJson(map);
        System.out.println(chargesCreateJson);
        return chargesCreateJson;
    }

    public static String getSavingsActivationFeeJSON() {
        final HashMap<String, Object> map = populateDefaultsForSavings();
        map.put("chargeTimeType", CHARGE_SAVINGS_ACTIVATION_FEE);
        String chargesCreateJson = new Gson().toJson(map);
        System.out.println(chargesCreateJson);
        return chargesCreateJson;
    }

    public static String getSavingsWithdrawalFeeJSON() {
        final HashMap<String, Object> map = populateDefaultsForSavings();
        map.put("chargeTimeType", CHARGE_WITHDRAWAL_FEE);
        String chargesCreateJson = new Gson().toJson(map);
        System.out.println(chargesCreateJson);
        return chargesCreateJson;
    }

    public static String getSavingsAnnualFeeJSON() {
        final HashMap<String, Object> map = populateDefaultsForSavings();
        map.put("feeOnMonthDay", ChargesHelper.feeOnMonthDay);
        map.put("chargeTimeType", CHARGE_ANNUAL_FEE);
        String chargesCreateJson = new Gson().toJson(map);
        System.out.println(chargesCreateJson);
        return chargesCreateJson;
    }

    public static String getSavingsMonthlyFeeJSON() {
        final HashMap<String, Object> map = populateDefaultsForSavings();
        map.put("feeOnMonthDay", ChargesHelper.feeOnMonthDay);
        map.put("chargeTimeType", CHARGE_MONTHLY_FEE);
        map.put("feeInterval", 2);
        String chargesCreateJson = new Gson().toJson(map);
        System.out.println(chargesCreateJson);
        return chargesCreateJson;
    }

    public static String getSavingsOverdraftFeeJSON() {
        final HashMap<String, Object> map = populateDefaultsForSavings();
        map.put("chargeTimeType", CHARGE_OVERDRAFT_FEE);
        String chargesCreateJson = new Gson().toJson(map);
        System.out.println(chargesCreateJson);
        return chargesCreateJson;
    }

    public static HashMap<String, Object> populateDefaultsForSavings() {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("active", ChargesHelper.active);
        map.put("amount", ChargesHelper.amount);
        map.put("chargeAppliesTo", ChargesHelper.CHARGE_APPLIES_TO_SAVINGS);
        map.put("chargeCalculationType", ChargesHelper.CHARGE_CALCULATION_TYPE_FLAT);
        map.put("currencyCode", ChargesHelper.currencyCode);
        map.put("locale", CommonConstants.locale);
        map.put("monthDayFormat", ChargesHelper.monthDayFormat);
        map.put("name", Utils.randomNameGenerator("Charge_Savings_", 6));
        return map;
    }

    public static String getLoanDisbursementJSON() {
        final HashMap<String, Object> map = populateDefaultsForLoan();
        map.put("chargeTimeType", CHARGE_DISBURSEMENT_FEE);
        String chargesCreateJson = new Gson().toJson(map);
        System.out.println(chargesCreateJson);
        return chargesCreateJson;
    }

    public static String getLoanSpecifiedDueDateJSON() {
        final HashMap<String, Object> map = populateDefaultsForLoan();
        map.put("chargeTimeType", CHARGE_SPECIFIED_DUE_DATE);
        String chargesCreateJson = new Gson().toJson(map);
        System.out.println(chargesCreateJson);
        return chargesCreateJson;
    }

    public static String getLoanInstallmentFeeJSON() {
        final HashMap<String, Object> map = populateDefaultsForLoan();
        map.put("chargeTimeType", CHARGE_INSTALLMENT_FEE);
        String chargesCreateJson = new Gson().toJson(map);
        System.out.println(chargesCreateJson);
        return chargesCreateJson;
    }

    public static String getLoanOverdueFeeJSON() {
        final HashMap<String, Object> map = populateDefaultsForLoan();
        map.put("penalty", true);
        map.put("chargeTimeType", CHARGE_OVERDUE_INSTALLMENT_FEE);
        String chargesCreateJson = new Gson().toJson(map);
        System.out.println(chargesCreateJson);
        return chargesCreateJson;
    }

    public static HashMap<String, Object> populateDefaultsForLoan() {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("active", ChargesHelper.active);
        map.put("penalty", ChargesHelper.penalty);
        map.put("amount", ChargesHelper.amount);
        map.put("chargeAppliesTo", ChargesHelper.CHARGE_APPLIES_TO_LOAN);
        map.put("chargeCalculationType", ChargesHelper.CHARGE_CALCULATION_TYPE_FLAT);
        map.put("chargePaymentMode", ChargesHelper.CHARGE_PAYMENT_MODE_REGULAR);
        map.put("currencyCode", ChargesHelper.currencyCode);
        map.put("locale", CommonConstants.locale);
        map.put("monthDayFormat", ChargesHelper.monthDayFormat);
        map.put("name", Utils.randomNameGenerator("Charge_Loans_", 6));
        if (ChargesHelper.addfeefrequency) {
            map.put("feeFrequency", ChargesHelper.CHARGE_FEE_FREQUENCY_MONTHS);
            map.put("feeInterval", 2);
        }
        return map;
    }

    public static Integer createCharges(final RequestSpecification requestSpec, final ResponseSpecification responseSpec,
            final String request) {
        return Utils.performServerPost(requestSpec, responseSpec, CREATE_CHARGES_URL, request, "resourceId");
    }

    public static HashMap updateCharges(final RequestSpecification requestSpec, final ResponseSpecification responseSpec,
            final Integer chargeId, final String request) {
        return Utils.performServerPut(requestSpec, responseSpec, CHARGES_URL + "/" + chargeId + "?" + Utils.TENANT_IDENTIFIER, request,
                CommonConstants.RESPONSE_CHANGES);
    }

    public static Integer deleteCharge(final ResponseSpecification responseSpec, final RequestSpecification requestSpec,
            final Integer chargeId) {
        return Utils.performServerDelete(requestSpec, responseSpec, CHARGES_URL + "/" + chargeId + "?" + Utils.TENANT_IDENTIFIER,
                CommonConstants.RESPONSE_RESOURCE_ID);
    }

    public static String getModifyChargeJSON() {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("locale", CommonConstants.locale);
        map.put("amount", "200");
        String json = new Gson().toJson(map);
        System.out.println(json);
        return json;
    }

    public static String getModifyWithdrawalFeeChargeJSON() {
        final HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("locale", CommonConstants.locale);
        map.put("chargeCalculationType", CHARGE_CALCULATION_TYPE_PERCENTAGE_AMOUNT);
        String json = new Gson().toJson(map);
        System.out.println(json);
        return json;
    }

}