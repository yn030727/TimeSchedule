package eventbus;


//事件
//挑战详细介绍界面的Back返回按键
public class EventChallenge_CardActivity_Back {
    Boolean click_back;
    public EventChallenge_CardActivity_Back(Boolean click_back){
        this.click_back = click_back;
    }

    public Boolean getClick_back() {
        return click_back;
    }

    public void setClick_back(Boolean click_back) {
        this.click_back = click_back;
    }
}
