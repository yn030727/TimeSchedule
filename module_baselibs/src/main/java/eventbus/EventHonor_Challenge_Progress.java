package eventbus;


//事件
//Honor界面后去数据库中挑战的信息
public class EventHonor_Challenge_Progress {
    int challenges[];
    int progress;
    public EventHonor_Challenge_Progress(int challenges[], int progress){
        this.challenges = challenges;
        this.progress = progress;
    }

    public int getProgress() {
        return progress;
    }

    public int[] getChallenges() {
        return challenges;
    }

    public void setChallenges(int[] challenges) {
        this.challenges = challenges;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}
