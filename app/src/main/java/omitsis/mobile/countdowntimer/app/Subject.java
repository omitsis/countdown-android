package omitsis.mobile.countdowntimer.app;

public interface Subject {
    public void register(CountDownTimerObserver obj);
    public void unregister(CountDownTimerObserver obj);
    public void notifyObservers();
}
