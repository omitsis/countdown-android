package omitsis.mobile.countdowntimer.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CountDownTimer implements Subject {

    static final int DEFAULT_TIME_MILLISECONDS = 600000;
    static final int MILLISECONDS_TO_TENTHS    = 60000;
    static final int SECOND_IN_MILLISECONDS    = 1000;
    static final int TENTH_IN_MILLISECONDS     = 100;

    public static enum Interval {
        SECONDS, TENTHS
    };

    public Interval interval;

    public static long getIntervalValue(Interval interval) {
        switch (interval) {
            case TENTHS:
                return TENTH_IN_MILLISECONDS;
            case SECONDS:
                return SECOND_IN_MILLISECONDS;
            default:
                return SECOND_IN_MILLISECONDS;
        }
    }

    public Timer countDownTimer;

    public long time;

    private List<CountDownTimerObserver> observers;

    public CountDownTimer() {
        this.time           = DEFAULT_TIME_MILLISECONDS;
        this.interval       = Interval.SECONDS;

        this.observers      = new ArrayList<CountDownTimerObserver>();
        this.countDownTimer = new Timer();
    }

    public CountDownTimer(long defaultTime) {
        this.time     = defaultTime;
        this.interval = Interval.SECONDS;

        if (defaultTime <= MILLISECONDS_TO_TENTHS) {
            this.interval = Interval.TENTHS;
        }

        this.observers      = new ArrayList<CountDownTimerObserver>();
        this.countDownTimer = new Timer();
    }

    public void start() {
        this.countDownTimer = new Timer();
        countDownTimer.scheduleAtFixedRate(countDownTask(), getIntervalValue(this.interval), getIntervalValue(this.interval));
    }

    public void stop() {
        countDownTimer.cancel();
        countDownTimer = null;
    }

    public TimerTask countDownTask() {
        return new TimerTask() {
            @Override
            public void run() {
                if (time <= MILLISECONDS_TO_TENTHS) {
                    if (Interval.SECONDS == interval) {
                        interval = Interval.TENTHS;
                        countDownTimer.cancel();
                        start();
                    }
                    decrementTimeInTenths();
                } else {
                    decrementTimeInSeconds();
                }

                notifyObservers();
            }
        };
    }

    public void decrementTimeInTenths() {
        if (this.time > 0) {
            time -= TENTH_IN_MILLISECONDS;
        } else {
            this.stop();
        }
    }

    public void decrementTimeInSeconds() {
        if (this.time > 0) {
            time -= SECOND_IN_MILLISECONDS;
        }
    }

    @Override
    public void register(CountDownTimerObserver obj) {
        if (obj == null) {
            throw new NullPointerException("Observer is null");
        }

        if(!observers.contains(obj)) {
            observers.add(obj);
        }
    }

    @Override
    public void unregister(CountDownTimerObserver obj) {
        observers.remove(obj);
    }

    @Override
    public void notifyObservers() {
        List<CountDownTimerObserver> observersLocal = new ArrayList<CountDownTimerObserver>(this.observers);

        for (CountDownTimerObserver obj : observersLocal) {
            obj.updateTime(time);
        }
    }
}
