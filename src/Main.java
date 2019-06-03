import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main (String[]args){

        //implement interface here;
        //String Usereigabe aus interface übergeben

        Support_Bot supportbot = new Support_Bot();
        supportbot.GreetCustomer();
        try {
            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(in);
            String a;
            while((a = br.readLine()) != null) {
                if(supportbot.sayGoodbye(a.toLowerCase())){break;}
                supportbot.InsertStringtoArray(a);
            supportbot.checkForIntent();
            supportbot.getResponse();
            }
        }catch(Exception e){
        }





    }
}
