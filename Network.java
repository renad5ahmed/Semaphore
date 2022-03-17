package os.A2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Network {          
public static void main(String[] args) throws IOException{     
    Scanner in=new Scanner (System.in);
    int numberOfConnections,numberOfUsers;
    String name="",type="";
    System.out.println("What is the number of WI-FI Connections?");
    numberOfConnections=in.nextInt();

    Router router=new Router(numberOfConnections);
    System.out.println("What is the number of devices Clients want to connect?");
    numberOfUsers=in.nextInt();
    ArrayList<Device> ddd=new ArrayList<Device>();
    System.out.println("Semaphore with  "+numberOfConnections+"  permit has been created");
    for(int i=0;i<numberOfUsers;i++){
        name=in.next(); type=in.next();
        Device d=new Device(name,type,router);
        ddd.add(d);
    }
        for (int i = 0; i < ddd.size(); i++) {
            ddd.get(i).start();
        }
}
}