package e2301777.assignment3;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private BlogEntryHandler entryHandler;
    private TextView displayAllPosts;
    private RadioGroup filters;
    private RadioButton searchFilter;
    private EditText userNameField, messageField, textSearch;
    private DatePicker datePicker;
    private Button searchButton, submitButton, refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        entryHandler = new BlogEntryHandler();
        displayAllPosts = findViewById(R.id.allPosts);
        userNameField = findViewById(R.id.userName);
        messageField = findViewById(R.id.commentField);
        filters = findViewById(R.id.searchRadioGroup);
        searchFilter = findViewById(R.id.textSearchRadio);
        searchFilter.setChecked(true);
        textSearch = findViewById(R.id.textSearchField);
        datePicker = findViewById(R.id.calendarSearch);
        submitButton = findViewById(R.id.submitButton);
        searchButton = findViewById(R.id.searchButton);
        refreshButton = findViewById(R.id.refreshButton);

        showAllEntries(entryHandler.getAllEntries());

        TextWatcher colorReset = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (userNameField.hasFocus()) {
                    userNameField.setBackgroundColor(Color.TRANSPARENT);
                }
                else if (messageField.hasFocus()) {
                    messageField.setBackgroundColor(Color.TRANSPARENT);
                }
            }
            @Override
            public void afterTextChanged(Editable s) {}
        };
        userNameField.addTextChangedListener(colorReset);
        messageField.addTextChangedListener(colorReset);



        filters.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.textSearchRadio) {
                textSearch.setVisibility(View.VISIBLE);
                datePicker.setVisibility(View.GONE);
            } else if (checkedId == R.id.dateSearchRadio) {
                textSearch.setVisibility(View.GONE);
                datePicker.setVisibility(View.VISIBLE);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = userNameField.getText().toString().trim();
                String message = messageField.getText().toString().trim();

                if (userName.isEmpty() || message.isEmpty()) {
                    if (userName.isEmpty()){
                        userNameField.setBackgroundColor(Color.parseColor("#f25c4b"));
                    }
                    if (message.isEmpty()){
                        messageField.setBackgroundColor(Color.parseColor("#f25c4b"));
                    }
                }
                else{
                    entryHandler.addBlogEntry(new BlogEntry(userName, message));
                    showAllEntries(entryHandler.getAllEntries());

                    userNameField.setText("");
                    messageField.setText("");
                }
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchFilter.isChecked()) {
                    showAllEntries(entryHandler.filterByText(textSearch.getText().toString()));
                    textSearch.setText("");
                } else {
                    int day = datePicker.getDayOfMonth();
                    int month = datePicker.getMonth() + 1;
                    int year = datePicker.getYear();

                    String inputDate = String.format(Locale.getDefault(), "%02d.%02d.%d", month, day, year);
                    showAllEntries(entryHandler.filterByDate(inputDate));
                }
            }
        });

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textSearch.setText("");
                searchFilter.setChecked(true);
                showAllEntries(entryHandler.getAllEntries());
            }
        });

    }

    private void showAllEntries(List<BlogEntry> blogEntries) {
        StringBuilder stringBuilder = new StringBuilder();
        for (BlogEntry entry : blogEntries){
            stringBuilder.append(entry.toString()).append("\n\n");
        }
        displayAllPosts.setText(stringBuilder.toString());
    }

}