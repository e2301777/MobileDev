package e2301777.assignment1;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    String tag="Lifecycle Event: ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(tag, tag + "onCreate()");
    }
    protected void onStart() {
        super.onStart();
        Log.d(tag, tag + "onStart()");
    }
    protected void onRestart() {
        super.onRestart();
        Log.d(tag, tag + "onReStart()");
    }
    protected void onResume() {
        super.onResume();
        Log.d(tag, tag + "onResume()");
    }
    protected void onPause() {
        super.onPause();
        Log.d(tag, tag + "onPause()");
    }
    protected void onStop() {
        super.onStop();
        Log.d(tag, tag + "onStop()");
    }
    protected void onDestroy() {
        super.onDestroy();
        Log.d(tag, tag + "onDestroy()");
    }
}