package village;

public class Village {
    private int dayCounter;
    private boolean isSaved;

    public Village() {
        this.dayCounter = 30;
        this.isSaved = false;
    }

    public int getDayCounter() {
        return dayCounter;
    }

    public void setDayCounter(int dayCounter) {
        this.dayCounter = dayCounter;
    }

    public boolean isSaved() {
        return isSaved;
    }

    public void setSaved(boolean saved) {
        isSaved = saved;
    }
}