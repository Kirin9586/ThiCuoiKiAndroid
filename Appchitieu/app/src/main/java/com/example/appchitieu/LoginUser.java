package com.example.appchitieu;

public class LoginUser {
    public LoginUser() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getSodu() {
        return sodu;
    }

    public void setSodu(int sodu) {
        this.sodu = sodu;
    }

    private int ID;

        private String user;
        private String pass;
        private int sodu;
        public LoginUser(String muser,String mpass,int msodu,int mtong){
            this.user = muser;
            this.pass = mpass;
            this.sodu = msodu;
        }
}
