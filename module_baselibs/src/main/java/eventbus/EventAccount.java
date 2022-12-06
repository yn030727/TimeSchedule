package eventbus;


//放在Baselibs组件下，让其他所有组件可以获得这个类的实例
public class EventAccount {
    String Account;
    String Password;

    public EventAccount(String Account , String Password){
        this.Account=Account;
        this.Password=Password;
    }

    public String getAccount() {
        return Account;
    }

    public String getPassword() {
        return Password;
    }

    public void setAccount(String account) {
        Account = account;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
