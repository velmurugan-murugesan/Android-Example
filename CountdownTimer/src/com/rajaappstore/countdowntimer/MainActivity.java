package com.rajaappstore.countdowntimer;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {
private CountDownTimer countDownTimer;
private boolean timerHasStarted = false;
private Button startB;
public TextView text;
private final long startTime = 30 * 1000;
private final long interval = 1 * 1000;
@Override
public void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
startB = (Button) this.findViewById(R.id.button);
startB.setOnClickListener(this);
text = (TextView) this.findViewById(R.id.timer);
countDownTimer = new MyCountDownTimer(startTime, interval);
text.setText(text.getText() + String.valueOf(startTime/1000));
}
public class MyCountDownTimer extends CountDownTimer {
public MyCountDownTimer(long startTime, long interval) {
super(startTime, interval);
}
@Override
public void onFinish() {
text.setText("Time's up!");
}
@Override
public void onTick(long millisUntilFinished) {
text.setText("" + millisUntilFinished/1000);
}
}
@Override
public void onClick(View v) {
	// TODO Auto-generated method stub
	if (!timerHasStarted) {
		countDownTimer.start();
		timerHasStarted = true;
		startB.setText("STOP");
		} else {
		countDownTimer.cancel();
		timerHasStarted = false;
		startB.setText("RESTART");
		}
}
}
