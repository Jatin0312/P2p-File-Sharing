/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;

/**
 *
 * @author jatin
 */
public class Mythread implements Runnable {
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
  private  File new_file=null;
  private Socket socket = null;
  
    Mythread(Socket s) {
        socket = s;
        
        System.out.println(s.getPort());
        
    }
    
    public void run(){
        try{
            
            DataOutputStream dout=new DataOutputStream(socket.getOutputStream()); 
        DataInputStream din = new DataInputStream(socket.getInputStream());
        long length = new_file.length();
        byte[] bytes = new byte[8192];
             InputStream in = new FileInputStream(new_file); 
             OutputStream out = socket.getOutputStream();
             int d;
             
             System.out.println("hey sending");
             while((d=in.read(bytes))>0){
                 out.write(bytes,0,d);
                 System.out.println("hey sending");
             }
             out.close();
             in.close();
             
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }
    
    public File get_file(){
        return new_file;
    }
    public void setFile(File f){
        new_file = f;
    }
    
}
