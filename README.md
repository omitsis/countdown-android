# Countdown

![alt text](https://avatars2.githubusercontent.com/u/1447233?s=140 "Omitsis Consulting S.L.")


## About countdown

Countdown was created to provide a simple and fast way to create our own countdown in apps that need this feature. This countdown counts in seconds, but when the remaining time is less than one minute it counts in tenths.



As you will see, right now is a very simple class, with just some methods to use, but we will upload more methods as soon as somebody need it.



Please, if you want to help us to improve Countdown, send us your opinion to info@omitsis.com or just comment here ;-)



## How to build

To use Countdown you just need to import CountDownTimer.java in your Activity/Controller and implements CountDownTimerObserver.java


## Current methods

1. CountDownTimer() {

2. CountDownTimer(long defaultTime) {

3. void start() {

4. void stop() {



## Usage

```

public class MainActivity extends Activity implements CountDownTimerObserver {

    CountDownTimer countDown;

    protected void onCreate(Bundle savedInstanceState) {
		...
		...

        this.countDown  = new CountDownTimer(63000);
        this.countDown.register(this);
        this.countDown.start();

        ...
        ...
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

...

```

This content is released under the (http://opensource.org/licenses/MIT) MIT License.
