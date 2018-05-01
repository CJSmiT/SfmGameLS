/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfm.loginserver;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author smit
 */
public abstract class Connection {
    private final Socket socket;
    private final OutputStream sout;
    final ObjectOutputStream out;
    private final InputStream sin;
    final ObjectInputStream in;

    public Connection(Socket clientSocket) {
        try {
            this.socket = clientSocket;
            sout = this.socket.getOutputStream();
            out = new ObjectOutputStream(sout);
            sin = this.socket.getInputStream();
            in = new ObjectInputStream(sin);
            
            System.out.println("Conntection succes init!");
        } catch (IOException ex) {
            System.err.println("Connect FAIL!");
            throw new RuntimeException(ex);
        }
    }

    public void close(){
        try {
            in.close();
            out.close();
            socket.close();
            System.out.println("Connection closed!");
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


}
