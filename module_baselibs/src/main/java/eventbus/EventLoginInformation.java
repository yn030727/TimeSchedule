package eventbus;

import org.greenrobot.eventbus.EventBus;

public class EventLoginInformation
{
    private boolean isLogin = true;
    private String account;
    private String user;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public boolean getLogin() {
        return isLogin;
    }

    public void setLogin(boolean login) {
        isLogin = login;
    }
    public EventLoginInformation(Boolean isLogin, String account, String user){
        this.account = account;
        this.user = user;
        this.isLogin = isLogin;
    }
}
