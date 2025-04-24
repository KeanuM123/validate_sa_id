package validate_sa_id;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ValidateSaId {
    // check if Id number is 13 digits long
    public static boolean isIdNumberValid(String idNumber) {
        if (idNumber == null || idNumber.length() != 13 || !idNumber.matches("\\d{13}")) {
            return false;
        }

        // Date validation (YYMMDD)
        String birthDate = idNumber.substring(0, 6);
        if (!isValidDate(birthDate)) {
            return false;
        }

        // Gender digits (SSSS)
        int genderCode = Integer.parseInt(idNumber.substring(6, 10));
        if (genderCode < 0 || genderCode > 9999) {
            return false;
        }

        // Citizenship (C)
        char citizenship = idNumber.charAt(10);
        if (citizenship != '0' && citizenship != '1') {
            return false;
        }

        // Luhn check (Z)
        return isValidLuhn(idNumber);
    }

    private static boolean isValidDate(String yymmdd) {
        // Add 19xx or 20xx based on year
        String fullYear = yymmdd.startsWith("0") || Integer.parseInt(yymmdd.substring(0, 2)) >= 20
            ? "19" + yymmdd
            : "20" + yymmdd;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        try {
            LocalDate.parse(fullYear, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static boolean isValidLuhn(String id) {
        int sum = 0;
        boolean alternate = false;
        for (int i = id.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(id.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) n -= 9;
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
