package e2301777.assignment4;

import android.graphics.Color;
import android.icu.text.RelativeDateTimeFormatter;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    LayoutParams viewLayoutParams = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Here we define parameters for views
        viewLayoutParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        viewLayoutParams.leftMargin = 40;
        viewLayoutParams.rightMargin = 40;
        viewLayoutParams.topMargin = 50;
        viewLayoutParams.bottomMargin = 10;
// Here we create the layout
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
// Here we define a text view
        TextView yourNumberLabel = new TextView(this);
        yourNumberLabel.setText(R.string.yourNumberTxt);
        yourNumberLabel.setLayoutParams(viewLayoutParams);
        yourNumberLabel.setTextSize(30);
        linearLayout.addView(yourNumberLabel);
// Here we define the edit text
        EditText yourNumber= new EditText(this);
        yourNumber.setLayoutParams(viewLayoutParams);
        yourNumber.setInputType(InputType.TYPE_CLASS_NUMBER);
        yourNumber.setTextSize(30);


        linearLayout.addView(yourNumber);
// Here we define a text view
        TextView randomNumberLabel = new TextView(this);
        randomNumberLabel.setText(R.string.randomNumberTxt);
        randomNumberLabel.setLayoutParams(viewLayoutParams);
        randomNumberLabel.setTextSize(30);
        linearLayout.addView(randomNumberLabel);

        yourNumber.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyCode==KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
                    if(!yourNumber.getText().toString().isEmpty()) {
                        Random random = new Random();
                        int generatedRandom = random.nextInt(10);
                        randomNumberLabel.setText(getString(R.string.randomNumberTxt) + generatedRandom);

                        if (generatedRandom == Integer.parseInt(yourNumber.getText().toString())) {
                            Toast.makeText(MainActivity.this, R.string.sameNumber, Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(MainActivity.this, R.string.enterNumber, Toast.LENGTH_SHORT).show();
                    }

                    return true;
                }
                return false;
            }
        });

        setContentView(linearLayout);
    }
}