package com.example.demo.service;

import com.example.demo.dto.error.FieldErrorResponse;
import com.example.demo.exception.ValidationException;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PhoneNumberService {
    @Value("${phone.default-region}")
    private String defaultPhoneRegion;
    private final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();

    public String normalizePhoneNumber(String phone) {
        Phonenumber.PhoneNumber phoneNumber = parseAndValidatePhoneNumber(phone);
        String number = phoneNumberUtil.format(phoneNumber, PhoneNumberUtil.PhoneNumberFormat.E164);
        return number;
    }

    private ValidationException createValidationException() {
        FieldErrorResponse fieldErrorResponse = new FieldErrorResponse("phone", "Invalid phone number");
        return new ValidationException(
                List.of(fieldErrorResponse)
        );
    }

    private Phonenumber.PhoneNumber parseAndValidatePhoneNumber(String phone) {
        try {
            Phonenumber.PhoneNumber phoneNumber =
                    phoneNumberUtil.parse(phone, defaultPhoneRegion);

            if (!phoneNumberUtil.isValidNumber(phoneNumber)) {
              throw createValidationException();
            }

            return phoneNumber;
        } catch (NumberParseException e) {
            throw createValidationException();

        }
    }
}
