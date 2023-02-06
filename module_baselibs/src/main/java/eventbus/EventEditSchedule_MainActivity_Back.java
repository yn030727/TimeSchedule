package eventbus;


//事件
//编辑计划界面的back返回按键
public class EventEditSchedule_MainActivity_Back {
    boolean click_back;
    public EventEditSchedule_MainActivity_Back(boolean click_back){
        this.click_back = click_back;
    }

    public void setClick_back(boolean click_back) {
        this.click_back = click_back;
    }

    public boolean getClick_back() {
        return click_back;
    }
}
