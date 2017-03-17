/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p2p;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

import static p2p.Server.*;



/**
 *
 * @author jatin
 */
public class Mythread2   implements Runnable {
    private ServerSocket sers;
    private Mythread mth;
   
    public Thread[] ttt = new Thread[10];
    public String[] namett = new String[10];
    File f = new File("/home/jatin/Desktop/client.pdf");
   public Mythread2(ServerSocket s){
       sers = s;
       
   }

    /**
     *
     * @return
     */
   
    public void run() {
        while(true)
        try {
            
            Socket s = sers.accept();
            mth = new Mythread(s);
            DataInputStream din = new DataInputStream(s.getInputStream());
            
           ttt[count] = new Thread(mth);
          namett[count] = din.readUTF();
          System.out.println(namett[count]);
          p2p.Server.appendname(namett[count]);
          mth.setFile(f);
          
       
          count++;
            System.out.println("Hello world"); 
           
        } catch (IOException ex) {
            break;
        }
        
    }
    
    public Mythread getmainthread(){
        return mth;
    }
    
    public Thread[] getclientthread(){
        return ttt;
    }
    public String[] getclientname(){
        return namett;
    }
    
   

    
    
}
