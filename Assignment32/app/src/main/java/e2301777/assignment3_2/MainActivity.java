package e2301777.assignment3_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private TextView calculatorTextView;
    private double firstValue = 0.0;
    private String operator = "";
    private boolean newOperation = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculatorTextView = findViewById(R.id.calcDisplay);
    }

    public void onNumberClick(View v) {
        Button button = (Button) v;

        if (newOperation) {
            calculatorTextView.setText("");
            newOperation = false;
        }

        calculatorTextView.append(button.getText().toString());
    }

    public void onOperatorClick(View v) {
        String value = calculatorTextView.getText().toString();

        if (!value.isEmpty()) {
            firstValue = Double.parseDouble(value);
            operator = ((Button) v).getText().toString();
            newOperation = true;
        }
    }

    public void onEqualClick(View v) {
        String value = calculatorTextView.getText().toString();

        if (value.isEmpty()) return;

        double secondValue = Double.parseDouble(value);
        double result = 0;

        switch (operator) {
            case "+":
                result = firstValue + secondValue;
                break;

            case "−":
                result = firstValue - secondValue;
                break;

            case "x":
                result = firstValue * secondValue;
                break;

            case "/":
                if (secondValue == 0) {
                    calculatorTextView.setText(R.string.divide_with_0);
                    newOperation = true;
                    return;
                }
                result = firstValue / secondValue;
                break;

            case "%":
                result = (firstValue / 100) * secondValue;
                break;
        }

        calculatorTextView.setText(String.format(Locale.getDefault(),"%.2f", result));
        firstValue = result;
        operator = "";
        newOperation = true;
    }

    public void onBackSpaceClick(View v) {
        String text = calculatorTextView.getText().toString();

        if (!text.isEmpty()) {
            calculatorTextView.setText(text.substring(0, text.length() - 1));
        }
    }

    public void onClearClick(View v) {
        calculatorTextView.setText("");
        firstValue = 0;
        operator = "";
    }
}
