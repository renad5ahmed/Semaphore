package os.A2;
import java.io.FileOutputStream;
import java.io.IOException;

public class Semaphore {
    protected int value = 0 ;
    protected Semaphore() {
        value = 0 ;
    }
    protected Semaphore(int initial) {//max number of users can be held
        value = initial ;
    }
    public synchronized void Wait(Device d) throws IOException{
        value-- ;
        if (value < 0){
            try {
                WriteInFile("("+d.getN()+")"+"("+d.getType()+")" + " arrived and waiting");
                System.out.println("("+d.getN()+")"+"("+d.getType()+")" + " arrived and waiting");
                wait();
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        else{
            WriteInFile("("+d.getN()+")"+"("+d.getType()+")" +  " arrived ");
            System.out.println("("+d.getN()+")"+"("+d.getType()+")" +  " arrived ");
        }
    }
    public synchronized void signal() {
        value++ ; 
        if (value <= 0) 
            notify() ;
    }
    public void WriteInFile(String s) throws IOException{
        FileOutputStream f= new FileOutputStream("C:\\output.txt",true);  
        s=s+'\n';
        byte b[]=s.getBytes(); 
        f.write(b);   
        f.close();
    }
}