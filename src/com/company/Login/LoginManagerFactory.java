package com.company.Login;


public class LoginManagerFactory {
    private static LoginManager loginManager = null;

    private LoginManagerFactory(){

    }

    public static LoginManager getLoginManager(){
        if(loginManager == null){
            loginManager = new LoginManager();
        }
        return loginManager;
    }
}
