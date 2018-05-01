/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfm.loginserver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author smit
 */
public class LoginServer {
    
    public static LoginInterface loginService;

    public static void main(String[] args) {
        System.out.println("Try start login server...");
        
        File gameFolder = identifyArgs(args);
        LoginServerSettings settings = initByFile(gameFolder);
        
        loginService = new LoginInterfaceImpl(settings);
        
        try {
            ServerSocket serverSocet = new ServerSocket(settings.loginServerPlayerPort);
            System.out.println("Login server waiting for a client...");

            Socket clientSocket = null;
            while (loginService.isRunning()) {
                clientSocket = serverSocet.accept();
                System.out.println("Client connected! " + clientSocket.getInetAddress());

                ClientConnection connection = new ClientConnection(clientSocket);
                processRequest(connection);

            }
        } catch (IOException ex) {
            Logger.getLogger(LoginServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private static void processRequest(ClientConnection connection) {
        ClientPacketForLS packetForLs = connection.getPacketForLS();
        
        ClientPacketFromLS packet = new ClientPacketFromLS();
        
        if (loginService.gameServerIsOnline()){
            packet.serverIsOnline = true;
            
            if (loginService.userCanConnectByUserLimit()){
                packet.userCanConnectByLimit = true;
                
                String login = packetForLs.login;
                String password = packetForLs.password;
                if (loginService.loginAndPassCorrect(login, password)){
                    packet.authentication = true;
                    
                    packet.session = loginService.getSessionForUser(login, password);

                } else {
                    packet.authentication = false;
                }
            } else {
                packet.userCanConnectByLimit = false;
            }
            
        } else {
            packet.serverIsOnline = false;
            connection.sendPacketForClient(packet);
        }
        
        connection.close();
    }
    

    private static File identifyArgs(String[] args) {
        if (args.length>0){
            File folder = new File(args[0]);
            if (folder.exists() && folder.isDirectory()){
                return folder;
            } else {
                throw new RuntimeException("The path not isset! " + folder.getAbsolutePath());
            }
        } else {
            throw new RuntimeException("Game folder path NOT SET!");
        }
    }

    private static LoginServerSettings initByFile(File gameFolder) {
        System.out.println("Try read conf file...");
        
        String propertiesFilename = "config.properties";
        String configPath = gameFolder.getAbsolutePath() + "/" + propertiesFilename;
        
        int usersOnlineLimit;
        boolean autoRegistration;
        String gameServerIp;
        int gameServerPlayerPort;
        int gameServerInfoPort;
        String loginServerDbIp;
        int loginServerDbPort;
        String loginServerDbUserName;
        String loginServerDbUserPass;
        String loginServerDbName;
        int loginServerPlayerPort;
        
        FileInputStream fis;
        Properties property = new Properties();
         try {
            fis = new FileInputStream(configPath);
            property.load(fis);
            
            usersOnlineLimit = Integer.valueOf(property.getProperty("usersOnlineLimit"));
            autoRegistration = Boolean.valueOf(property.getProperty("autoRegistration"));
            gameServerIp = property.getProperty("gameServerIp");
            gameServerPlayerPort = Integer.valueOf(property.getProperty("gameServerPlayerPort"));
            gameServerInfoPort = Integer.valueOf(property.getProperty("gameServerInfoPort"));
            loginServerDbIp = property.getProperty("loginServerDbIp");
            loginServerDbPort = Integer.valueOf(property.getProperty("loginServerDbPort"));
            loginServerDbUserName = property.getProperty("loginServerDbUserName");
            loginServerDbUserPass = property.getProperty("loginServerDbUserPass");
            loginServerDbName = property.getProperty("loginServerDbName");
            loginServerPlayerPort = Integer.valueOf(property.getProperty("loginServerPlayerPort"));

        } catch (IOException e) {
            throw new RuntimeException("Not found conf file by path: " + configPath);
        }
        
        LoginServerSettings settings = new LoginServerSettings(
                usersOnlineLimit,
                autoRegistration,
                gameServerIp,
                gameServerPlayerPort,
                gameServerInfoPort,
                loginServerDbIp,
                loginServerDbPort,
                loginServerDbUserName,
                loginServerDbUserPass,
                loginServerDbName,
                loginServerPlayerPort
                );
        
        System.out.println("Settings successfully initialized: " + settings);
        
        return settings;
    }

    
    
}
