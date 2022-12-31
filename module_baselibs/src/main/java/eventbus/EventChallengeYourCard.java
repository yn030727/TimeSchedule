package eventbus;

//事件
//检测实时挑战中选择的是哪个
public class EventChallengeYourCard {
    int your_card_number;

    public EventChallengeYourCard(int your_card_number){
        this.your_card_number = your_card_number;
    }

    public EventChallengeYourCard(){}

    public int getYour_card_number() {
        return your_card_number;
    }

    public void setYour_card_number(int your_card_number) {
        this.your_card_number = your_card_number;
    }
}
