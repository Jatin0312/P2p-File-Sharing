/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
  private static final int CHUNK_SIZE = 1024*16;
    Mythread(Socket s) {
        socket = s;
        
        System.out.println(s.getPort());
        
    }
    
    public void run(){
        try{
            
         DataOutputStream dout = new DataOutputStream(
                new BufferedOutputStream(socket.getOutputStream()));
        DataInputStream din = new DataInputStream(socket.getInputStream());
        long length = new_file.length();
        byte[] bytes = new byte[8192];
             InputStream in = new FileInputStream(new_file); 
            dout.writeLong(new_file.length());
             int d;
             writeFile(new_file, dout);
             System.out.println("hey sending");
             
             System.out.println("don");
             dout.close();
             din.close();
             
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
    
    
    private static void writeFile(File file, OutputStream outStream) throws FileNotFoundException, IOException {
        FileInputStream reader = null;
        try {
            reader = new FileInputStream(file);
            
            byte[] buffer = new byte[CHUNK_SIZE];
            int pos = 0;
            int bytesRead;
            while ((bytesRead = reader.read(buffer, 0, CHUNK_SIZE)) >= 0) {
                outStream.write(buffer, 0, bytesRead);
                outStream.flush();
                pos += bytesRead;
                System.out.println(pos + " bytes (" + bytesRead + " bytes read)");
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Error while reading file");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error while writing " + file.toString() + " to output stream");
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    
    
}
