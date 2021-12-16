public class Users {
    //Private fields
    private String userName;
    private String passWord;

    //Getters|Setters|For private variables
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    //Constructor
    public Users(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }
}
