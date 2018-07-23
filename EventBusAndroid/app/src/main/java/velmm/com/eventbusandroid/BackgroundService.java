package velmm.com.eventbusandroid;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class BackgroundService extends Service {

    public static final int notify = 5000;
    private Handler mHandler = new Handler();
    private Timer mTimer = null;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        EventBus.getDefault().register(this);
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        if (mTimer != null)
            mTimer.cancel();
        else
            mTimer = new Timer();

        mTimer.scheduleAtFixedRate(new TimeDisplay(), 0, notify);
    }

    class TimeDisplay extends TimerTask {
        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    EventBus.getDefault().post(new Date());
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mTimer.cancel();
        EventBus.getDefault().unregister(this);
    }
}
