public class Main {
    public static void main (String[]args){

        //implement interface here;
        //String Usereigabe aus interface übergeben
        Support_Bot supportbot = new Support_Bot();
        supportbot.GreetCustomer();
        supportbot.InsertStringtoArray("not charge");
        supportbot.checkForIntent();
        supportbot.getResponse();
        supportbot.InsertStringtoArray("laptop");
        supportbot.checkForIntent();
        supportbot.getResponse();
        supportbot.InsertStringtoArray("no");
        supportbot.checkForIntent();
        supportbot.getResponse();
        supportbot.InsertStringtoArray("no");
        supportbot.checkForIntent();
        supportbot.getResponse();



    }
}
