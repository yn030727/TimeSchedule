package eventbus;

//事件
//检测实时挑战中选择的是哪个
public class EventChallengeYourCard {
    String your_card_name_event;
    public EventChallengeYourCard(String your_card_name_event){
        this.your_card_name_event = your_card_name_event;
    }

    public String getYour_card_name_event() {
        return your_card_name_event;
    }

    public void setYour_card_name_event(String your_card_name_event) {
        this.your_card_name_event = your_card_name_event;
    }
}
