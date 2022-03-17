package os.A2;
import java.io.IOException;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Router {
    public Semaphore semaphore;
    public int value; 
    private boolean [] activated;

    Router() {}
    public   Router (int numberOfConnections)
    {
        this.value = numberOfConnections;
        semaphore = new Semaphore(numberOfConnections);
        activated = new boolean[numberOfConnections];
    }
    public synchronized int occupyConnection(Device d) throws InterruptedException{
            for(int i=0;i<value;i++){
            if (!activated[i])
            {
                d.setID(i);
                sleep(200);
                try {
                    semaphore.WriteInFile("Connection " + (d.getID()+1)+": "+d.getN() + " Login");
                } catch (IOException ex) {
                    Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Connection " + (d.getID()+1)+": "+d.getN() + " Login");
                activated[i] = true;
                break;
            }}
              return d.getID(); 
    }
    
    public synchronized void releaseConnection(Device d) throws InterruptedException{  
        activated[d.getID()] = false;
        notify();
    }
}