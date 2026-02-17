package e2301777.assignment2;

import android.os.Bundle;
import android.os.Handler;
import androidx.activity.EdgeToEdge;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Handler handler = new Handler();
    Random rand = new Random();
    private TextView currentNumber;
    private int currentRand;
    private static final String KEY_RAND_NUM = "currentRandNumber";
    private static final String KEY_DATE_TIME = "dateTime";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        currentNumber = findViewById(R.id.randomNumber);
        TextView latestNumber = findViewById(R.id.latestNumber);

        if(savedInstanceState != null){
            int numBeforeRotate = savedInstanceState.getInt(KEY_RAND_NUM);
            String dateTime = savedInstanceState.getString(KEY_DATE_TIME);
            String randNumberText = getString(R.string.tw_latest) + numBeforeRotate;
            String timestamp = getString(R.string.dateTime) + dateTime;

            latestNumber.setText(randNumberText);
            Toast.makeText(this, timestamp, Toast.LENGTH_SHORT).show();

        }
        handler.post(updateNumber);
    }

    Runnable updateNumber = new Runnable() {
        @Override
        public void run() {
            currentRand = rand.nextInt(100);
            currentNumber.setText(getString(R.string.tw_rand) + currentRand);
            handler.postDelayed(this, 1000);
        }
    };

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss", Locale.getDefault());
        String currentDateTime = dateFormat.format(new Date());
        outState.putInt(KEY_RAND_NUM, currentRand);
        outState.putString(KEY_DATE_TIME, currentDateTime);
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateNumber);
    };
}