/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfm.loginserver;

import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author smit
 */
public class ClientConnection extends Connection{
    
    public ClientConnection(Socket clientSocket) {
        super(clientSocket);
    }
    
    public void sendPacketForClient(ClientPacketFromLS packet){
        try {
            out.writeObject(packet);
            out.flush();
        } catch (IOException ex) {
            Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ClientPacketForLS getPacketForLS() {
        try {
            ClientPacketForLS packetForLS = (ClientPacketForLS) in.readObject();
            return packetForLS;
        } catch (IOException ex) {
            Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        throw new RuntimeException();
    }
    
}
