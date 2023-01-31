package eventbus;

public class EventEditSchedule {
    boolean click;

    public boolean getClick() {
        return click;
    }

    public void setClick(boolean click) {
        this.click = click;
    }

    public EventEditSchedule(boolean click){
        this.click = click;
    };
}
