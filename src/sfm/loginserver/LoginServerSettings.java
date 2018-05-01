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
public class LoginServerSettings {
    public final int usersOnlineLimit;
    public final boolean autoRegistration;
    public final String gameServerIp;
    public final int gameServerPlayerPort;
    public final int gameServerInfoPort;
    public final String loginServerDbIp;
    public final int loginServerDbPort;
    public final String loginServerDbUserName;
    public final String loginServerDbUserPass;
    public final String loginServerDbName;
    public final int loginServerPlayerPort;

    public LoginServerSettings(int usersOnlineLimit, 
            boolean autoRegistration, 
            String gameServerIp, 
            int gameServerPlayerPort, 
            int gameServerInfoPort, 
            String loginServerDbIp, 
            int loginServerDbPort, 
            String loginServerDbUserName, 
            String loginServerDbUserPass, 
            String loginServerDbName,
            int loginServerPlayerPort) {
        this.usersOnlineLimit = usersOnlineLimit;
        this.autoRegistration = autoRegistration;
        this.gameServerIp = gameServerIp;
        this.gameServerPlayerPort = gameServerPlayerPort;
        this.gameServerInfoPort = gameServerInfoPort;
        this.loginServerDbIp = loginServerDbIp;
        this.loginServerDbPort = loginServerDbPort;
        this.loginServerDbUserName = loginServerDbUserName;
        this.loginServerDbUserPass = loginServerDbUserPass;
        this.loginServerDbName = loginServerDbName;
        this.loginServerPlayerPort = loginServerPlayerPort;
    }

    @Override
    public String toString() {
        return "LoginServerSettings{" + "usersOnlineLimit=" + usersOnlineLimit + ", autoRegistration=" + autoRegistration + ", gameServerIp=" + gameServerIp + ", gameServerPlayerPort=" + gameServerPlayerPort + ", gameServerInfoPort=" + gameServerInfoPort + ", loginServerDbIp=" + loginServerDbIp + ", loginServerDbPort=" + loginServerDbPort + ", loginServerDbUserName=" + loginServerDbUserName + ", loginServerDbUserPass=" + loginServerDbUserPass + ", loginServerDbName=" + loginServerDbName + ", loginServerPlayerPort=" + loginServerPlayerPort + '}';
    }

    
    
}
