package com.owjmedia.faaz.general.utils;

import android.telephony.PhoneNumberUtils;

/**
 * Created by salman on 11/10/17.
 */

public class Validator {

    public static boolean isPhoneNumberValid(String phone) {
        return isStringValid(phone) && PhoneNumberUtils.isGlobalPhoneNumber(phone);
    }

    public static boolean isStringValid(String str) {
        return str != null && !str.isEmpty();
    }
}
