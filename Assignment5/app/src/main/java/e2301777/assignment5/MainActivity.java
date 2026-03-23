package e2301777.assignment5;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText firstNameSubmit, lastNameSubmit, phoneSubmit;
    private AutoCompleteTextView firstNameAuto, lastNameAuto, phoneAuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Submit fields
        firstNameSubmit = findViewById(R.id.firstNameSubmit);
        lastNameSubmit = findViewById(R.id.lastNameSubmit);
        phoneSubmit = findViewById(R.id.phoneSubmit);
        Button submitButton = findViewById(R.id.submitButton);

//Search fields
        firstNameAuto = findViewById(R.id.firstNameAuto);
        lastNameAuto = findViewById(R.id.lastNameAuto);
        phoneAuto = findViewById(R.id.phoneAuto);
        Button resetButton = findViewById(R.id.resetButton);

        firstNameAuto.setThreshold(1);
        lastNameAuto.setThreshold(1);
        phoneAuto.setThreshold(1);

//Button listeners
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitPerson();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstNameAuto.setText("");
                lastNameAuto.setText("");
                phoneAuto.setText("");
            }
        });

//Autocomplete listeners
        firstNameAuto.setOnItemClickListener((adapterView, view, position, id) -> {
            String formatted = DatabaseHandler.formatResult(
                    firstNameAuto.getText().toString(),
                    DatabaseHandler.sortByFirstName,
                    MainActivity.this);
            firstNameAuto.setText(formatted);
        });

        lastNameAuto.setOnItemClickListener((adapterView, view, position, id) -> {
            String formatted = DatabaseHandler.formatResult(
                    lastNameAuto.getText().toString(),
                    DatabaseHandler.sortByLastName,
                    MainActivity.this);
            lastNameAuto.setText(formatted);
        });

        phoneAuto.setOnItemClickListener((adapterView, view, position, id) -> {
            String formatted = DatabaseHandler.formatResult(
                    phoneAuto.getText().toString(),
                    DatabaseHandler.sortByPhone,
                    MainActivity.this);
            phoneAuto.setText(formatted);
        });
    }


    private void submitPerson() {
        String firstName = firstNameSubmit.getText().toString().trim();
        String lastName = lastNameSubmit.getText().toString().trim();
        String phone = phoneSubmit.getText().toString().trim();

        if (validateInput(firstName, lastName, phone)) {
            Person newPerson = new Person(firstName, lastName, phone);
            DatabaseHandler.addPerson(newPerson);

            updateAdapters();

            firstNameSubmit.setText("");
            lastNameSubmit.setText("");
            phoneSubmit.setText("");

            Toast.makeText(this, getText(R.string.dataSubmitted), Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInput(String firstName, String lastName, String phone) {
        if (firstName.isEmpty() || lastName.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, getText(R.string.fillAllFields), Toast.LENGTH_SHORT).show();
            return false;
        }

        if (DatabaseHandler.checkDuplicatePhone(phone)) {
            phoneSubmit.requestFocus();
            Toast.makeText(this, getText(R.string.duplicatePhone), Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    private void updateAdapters() {
        ArrayAdapter<String> firstNameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, DatabaseHandler.firstNameList);
        firstNameAuto.setAdapter(firstNameAdapter);

        ArrayAdapter<String> lastNameAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, DatabaseHandler.lastNameList);
        lastNameAuto.setAdapter(lastNameAdapter);

        ArrayAdapter<String> phoneAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, DatabaseHandler.phoneList);
        phoneAuto.setAdapter(phoneAdapter);
    }


}