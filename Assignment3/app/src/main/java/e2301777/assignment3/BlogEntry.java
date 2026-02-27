package e2301777.assignment3;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
public class BlogEntry {
    private static int counter = 1;
    private String userName;
    private String message;
    private int entryNum;
    private String entryTime;
    private String entryCreationDate;

    public BlogEntry(String userName, String message) {
        this.userName = userName;
        this.message = message;
        this.entryNum = counter++;

        SimpleDateFormat timeDate = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault());
        SimpleDateFormat creationDate = new SimpleDateFormat("MM.dd.yyyy", Locale.getDefault());

        this.entryTime = timeDate.format(new Date());
        this.entryCreationDate = creationDate.format(new Date());
    }

    public boolean searchByText(String searchText) {
        String textToSearch = searchText.toLowerCase();
        return userName.toLowerCase().contains(textToSearch) ||
                message.toLowerCase().contains(textToSearch);
    }

    public boolean searchByDate(String searchDate) {
        return entryCreationDate.equals(searchDate);
    }

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(entryNum).append(". \n");
        stringBuilder.append("User: ").append(userName).append("\n");
        stringBuilder.append("Posted at: ").append(entryTime).append("\n");
        stringBuilder.append("Message: ").append(message);

        return stringBuilder.toString();
    }
}
