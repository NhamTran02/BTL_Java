package validator;

import modal.Person;
import modal.Student;

import javax.xml.validation.Validator;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Const {
    public static final int MAX_NAME_LENGTH=100;
    public static final int MAX_YEAR=2024;
    public static final int MIN_YEAR=1900;
    public static final int MAX_ADDRESS_LENGTH=300;
    public static final double MAX_HEIGHT=300.0;
    public static final double MIN_HEIGHT=50.0;
    public static final double MAX_WEIGHT=1000.0;
    public static final double MIN_WEIGHT =5.0;

    public static final int STUDENT_ID_MAX_LENGTH=10;
    public static final int SCHOOL_NAME_MAX_LENGTH=200;
    public static final int MIN_SCHOOL_YEAR=1900;
    public static final int MAX_SCHOOL_YEAR=2024;
    public static final double MAX_GPA=10.0;
    public static final double MIN_GPA=0.0;

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");


    public static boolean validateName(String name) {
        return !name.isEmpty() && name.length() <= MAX_NAME_LENGTH;
    }


    public static boolean validateBirth(String dateStr) {
        try {
            Date date = DATE_FORMAT.parse(dateStr);
            String[] parts = dateStr.split("-");
            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int day = Integer.parseInt(parts[2]);
            if (year >= MIN_YEAR && year <= MAX_YEAR) {
                if (month >= 1 && month <= 12) {
                    if (day >= 1 && day <= 31) {
                        return true;
                    }
                }
            }
            return false;
        } catch (ParseException | NumberFormatException e) {
            return false;
        }
    }

    public static boolean validateAddress(String address) {
        return !address.isEmpty() && address.length() <= MAX_ADDRESS_LENGTH;
    }
    public static boolean validateHeight(double height) {
        return height >= MIN_HEIGHT && height <= MAX_HEIGHT;
    }
    public static boolean validateWeight(double weight) {
        return weight >= MIN_WEIGHT && weight <= MAX_WEIGHT;
    }
    public static boolean validateStudentId(String studentId) {
        return studentId.length() == STUDENT_ID_MAX_LENGTH;
    }

    public static boolean validateSchoolName(String schoolName) {
        return !schoolName.isEmpty() && schoolName.length() <= SCHOOL_NAME_MAX_LENGTH;
    }
    public static boolean validateSchoolYear(int schoolYear) {
        return schoolYear >= MIN_SCHOOL_YEAR && schoolYear <= MAX_SCHOOL_YEAR;
    }
    public static boolean validateGPA(double gpa) {
        return gpa >= MIN_GPA && gpa <= MAX_GPA;
    }

}
