package omitsis.mobile.countdowntimer.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;


public class MainActivity extends Activity implements CountDownTimerObserver {

    TextView timerText;
    CountDownTimer countDown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.countDown  = new CountDownTimer(63000);
        this.countDown.register(this);
        this.timerText  = (TextView) this.findViewById(R.id.score_defaultTimer);

        this.countDown.start();
    }

    public void updateTime(long time) {

        final String text = String.valueOf(time);

        this.runOnUiThread(new Runnable(){
            @Override
            public void run() {
                timerText.setText(text);
            }
        });
    }

}
