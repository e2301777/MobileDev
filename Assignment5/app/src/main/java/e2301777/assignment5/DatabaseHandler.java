package e2301777.assignment5;

import android.content.Context;
import java.util.ArrayList;

public class DatabaseHandler {
    public static final String sortByFirstName = "fname";
    public static final String sortByLastName = "lname";
    public static final String sortByPhone = "phone";
    public static ArrayList<String> firstNameList = new ArrayList<>();
    public static ArrayList<String> lastNameList = new ArrayList<>();
    public static ArrayList<String> phoneList = new ArrayList<>();

    public static void addPerson(Person person) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(person.getFirstName()).append(";")
                .append(person.getLastName()).append(";")
                .append(person.getPhone());
        firstNameList.add(stringBuilder.toString());

        stringBuilder.setLength(0);
        stringBuilder.append(person.getLastName()).append(";")
                .append(person.getFirstName()).append(";")
                .append(person.getPhone());
        lastNameList.add(stringBuilder.toString());

        stringBuilder.setLength(0);
        stringBuilder.append(person.getPhone()).append(";")
                .append(person.getFirstName()).append(";")
                .append(person.getLastName());
        phoneList.add(stringBuilder.toString());
    }

    public static boolean checkDuplicatePhone(String phone) {
        for (String entry : phoneList) {
            if (entry.startsWith(phone)) {
                return true;
            }
        }
        return false;
    }

    public static String formatResult(String personEntry, String searchBy, Context context) {
        String[] splitPersonEntry = personEntry.split(";");

        StringBuilder stringBuilder = new StringBuilder();
        String firstNameLabel = context.getString(R.string.firstNameLabel);
        String lastNameLabel = context.getString(R.string.lastNameLabel);
        String phoneNumberLabel = context.getString(R.string.phoneLabel);

        switch (searchBy) {
            case sortByFirstName:
                stringBuilder.append(firstNameLabel).append(" ").append(splitPersonEntry[0]).append("\n")
                        .append(lastNameLabel).append(" ").append(splitPersonEntry[1]).append("\n")
                        .append(phoneNumberLabel).append(" ").append(splitPersonEntry[2]);
                break;
            case sortByLastName:
                stringBuilder.append(lastNameLabel).append(" ").append(splitPersonEntry[0]).append("\n")
                        .append(firstNameLabel).append(" ").append(splitPersonEntry[1]).append("\n")
                        .append(phoneNumberLabel).append(" ").append(splitPersonEntry[2]);
                break;
            case sortByPhone:
                stringBuilder.append(phoneNumberLabel).append(" ").append(splitPersonEntry[0]).append("\n")
                        .append(firstNameLabel).append(" ").append(splitPersonEntry[1]).append("\n")
                        .append(lastNameLabel).append(" ").append(splitPersonEntry[2]);
                break;
        }
        return stringBuilder.toString();
    }
}
