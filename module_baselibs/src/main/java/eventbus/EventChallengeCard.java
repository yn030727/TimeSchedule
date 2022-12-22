package eventbus;


//事件
//检测挑战界面是否点击了挑战小卡片的按钮
//检测点击的是第几个按钮
public class EventChallengeCard {
    Boolean click_card;
    int card_number;

    public EventChallengeCard(boolean b, int position) {
        this.click_card = b;
        this.card_number = position;
    }

    public EventChallengeCard(EventChallengeCard card) {
    }

    public Boolean getClick_card() {
        return click_card;
    }

    public int getCard_number() {
        return card_number;
    }

    public void setCard_number(int card_number) {
        this.card_number = card_number;
    }

    public void setClick_card(Boolean click_card) {
        this.click_card = click_card;
    }
}
