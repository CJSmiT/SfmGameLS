/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfm.loginserver;

/**
 *
 * @author smit
 */
public interface LoginInterface { // SFM (stardrive factorio minecraft)
    
    boolean gameServerIsOnline();
    
    boolean userCanConnectByUserLimit();
    
    int usersOnline();
    
    boolean loginAndPassCorrect(String login, String pass);
    
    String authorization(String login, String pass);

    public String getSessionForUser(String login, String password);

    public boolean isRunning();
    
}
