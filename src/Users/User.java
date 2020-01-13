package Users;

public class User {
    private int userid;
    private int sexNumber;
    private int age;
    private int control;//权限 0-管理员
    private int state;//禁言状态 1-禁言
    private String userName;
    private String password;
    private String headPic;
    private String signature;
    private String address;
    private String phoneNumber;
    private String introduction;
    private String profession;
    private String coverPic;


    public User() {
        this.userid = 0;
        this.sexNumber = 0;
        this.age = 0;
        this.control = 1;
        this.state = 0;
        this.userName = "";
        this.password = "";
        this.headPic = "https://i.loli.net/2019/11/06/Mzv84LUWjqgCZyV.png";
        this.signature = "";
        this.address = "";
        this.phoneNumber = "";
        this.introduction = "%3Cp%3E%u8FD9%u4E2A%u4EBA%u5F88%u61D2%uFF0C%u6CA1%u6709%u4EFB%u4F55%u4E2A%u4EBA%u7B80%u4ECB%3C/p%3E";
        this.profession = "";
        this.coverPic = "https://i.loli.net/2019/11/12/rLFM6JfzlGKn15k.jpg";
    }

    public User(String phonenumber, String password) {
        this.userid = 0;
        this.sexNumber = 0;
        this.age = 0;
        this.control = 1;
        this.state = 0;
        this.userName = "";
        this.password = password;
        this.headPic = "https://i.loli.net/2019/11/06/Mzv84LUWjqgCZyV.png";
        this.signature = "";
        this.address = "";
        this.phoneNumber = phonenumber;
        this.introduction = "%3Cp%3E%u8FD9%u4E2A%u4EBA%u5F88%u61D2%uFF0C%u6CA1%u6709%u4EFB%u4F55%u4E2A%u4EBA%u7B80%u4ECB%3C/p%3E";
        this.profession = "";
        this.coverPic = "https://i.loli.net/2019/11/12/rLFM6JfzlGKn15k.jpg";
    }

    public User(int userid, int sexNumber, int age, String userName, String password, String headPic, String signature, String address, String phonenumber, String introduction, String profession,String coverPic) {
        this.userid = userid;
        this.sexNumber = sexNumber;
        this.age = age;
        this.control = 1;
        this.state = 0;
        this.userName = userName;
        this.password = password;
        this.headPic = headPic;
        this.signature = signature;
        this.address = address;
        this.phoneNumber = phonenumber;
        this.introduction = introduction;
        this.profession = profession;
        this.coverPic = coverPic;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getSexNumber() {
        return sexNumber;
    }

    public void setSexNumber(int sexNumber) {
        this.sexNumber = sexNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phonenumber) {
        this.phoneNumber = phonenumber;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public int getControl() {
        return control;
    }

    public void setControl(int control) {
        this.control = control;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
