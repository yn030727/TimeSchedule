package eventbus;

public class EventEditLogin {
    private boolean isLogin = false;
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
    public EventEditLogin(Boolean isLogin, String account, String user){
        this.account = account;
        this.user = user;
        this.isLogin = isLogin;
    }
}
