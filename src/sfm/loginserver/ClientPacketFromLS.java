/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sfm.loginserver;

import java.io.Serializable;

/**
 *
 * @author smit
 */
public class ClientPacketFromLS implements Serializable{
    public boolean serverIsOnline;
    public boolean authentication;
    public String session;
    public boolean userCanConnectByLimit;
}
