package os.A2;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Device extends Thread{
    private String name;
    private String Type;   
    private Router r=new Router();
    private int specID;
    
    public Device(String name, String Type, Router rr){
        this.name=name;
        this.Type=Type;
        r=rr;
        specID=1;
    }
    
    public String getN(){
        return name;
    }
    
    public String getType(){
        return Type;
    }
    public void setID(int id){
        specID=id;
    }
    public int getID(){
        return specID;
    }

    @Override
    public void run(){
        try {
            r.semaphore.Wait(this);
            specID=r.occupyConnection(this);
            specID++;
            r.semaphore.WriteInFile("Connection " + (specID)+": "+this.name+ " performs online activity \n"+"Connection " + (specID)+": "+this.name + " Logged out");
            System.out.println("Connection " + (specID)+": "+this.name+ " performs online activity");
            System.out.println("Connection " + (specID)+": "+this.name + " Logged out");
                        r.releaseConnection(this);

            r.semaphore.signal();
        } catch (InterruptedException ex) {
            Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }
}  