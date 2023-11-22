package asm;

public final class Students {

    private String studentID;
    private String username;
    private String email;
    private String phoneNumber;
    private String address;
    private boolean sex;
    private String avatarPath;

    public Students(String studentID, String username, String email, String phoneNumber, String address, String sex, String avatarPath) {
        this.studentID = studentID;
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.avatarPath = avatarPath;
        this.sex = convertSex(sex);
    }

    public boolean convertSex(String sex) {
        return "Nam".equals(sex);
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }

    public boolean isSex() {
        return sex;
    }

    public void setGioiTinh(boolean sex) {
        this.sex = sex;
    }
}
