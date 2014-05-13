package omitsis.mobile.countdowntimer.app.tests;

import junit.framework.TestCase;
import omitsis.mobile.countdowntimer.app.CountDownTimer;

import java.util.TimerTask;

public class CountDownTimerTest extends TestCase {

    public CountDownTimerTest() {
        this("CountDownTimerTest");
    }

    public CountDownTimerTest(String name) {
        super(name);
    }

    public void testCountDownTimerInitializeTimer() {
        CountDownTimer countDown = new CountDownTimer();
        assertNotNull(countDown.countDownTimer);
    }

    public void testThatCountDownTimerDecreasesOneSecondWhenTimeAreGreaterThan60Seconds() {
        CountDownTimer countDown = new CountDownTimer(61000);

        TimerTask timerTask = countDown.countDownTask();
        timerTask.run();

        long expected = 60000;
        assertEquals(expected, countDown.time);
    }

    public void testThatCountDownTimerDecreaseOneTenthWhenTimerAreLowerOrEqualThan60Seconds() {
        CountDownTimer countDown = new CountDownTimer(60000);

        TimerTask timerTask = countDown.countDownTask();
        timerTask.run();

        long expected = 59900;
        assertEquals(expected, countDown.time);
    }

    public void testThatCountDownTimerDecreaseOneSecondWhenTimerAreGreaterThan60SecondsAndChangeToTenthWhenTimerAreLowerOrEqualThan60Seconds() throws Exception {
        CountDownTimer countDown = new CountDownTimer(61000);

        TimerTask timerTask = countDown.countDownTask();
        timerTask.run();
        timerTask.run();

        long expected = 59900;
        assertEquals(expected, countDown.time);
    }

    public void testThatCountDownTimerStopWhenTimerAre0() throws Exception {
        CountDownTimer countDown = new CountDownTimer(0);

        TimerTask timerTask = countDown.countDownTask();
        timerTask.run();

        assertNull(countDown.countDownTimer);
    }

}