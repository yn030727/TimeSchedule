package eventbus;

import java.util.HashMap;
import java.util.HashSet;

//订阅事件
//在module_editschedule组件中按下创建按钮，发送EventBus，将数据传递给第一个界面，并返回到第一界面
//难点：在于判断是否重复添加了计划
public class EventSchedule {
    HashMap<String , Boolean> hashMap ;

    public EventSchedule(HashMap<String , Boolean> hashMap){
        this.hashMap = hashMap;
    }

    public HashMap<String, Boolean> getHashMap() {
        return hashMap;
    }

    public void setHashMap(HashMap<String, Boolean> hashMap) {
        this.hashMap = hashMap;
    }


}
