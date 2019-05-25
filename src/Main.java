public class Main {
    public static void main (String[]args){

        //implement interface here;
        //String Usereigabe aus interface übergeben

        Support_Bot supportbot = new Support_Bot();
        supportbot.GreetCustomer();
        supportbot.InsertStringtoArray("hi, my lapto");
        supportbot.checkForIntent();
        supportbot.getResponse();
        supportbot.InsertStringtoArray("hi, my laptop");
        supportbot.checkForIntent();
        supportbot.getResponse();
        supportbot.InsertStringtoArray("my laptop is not charging anymore");
        supportbot.checkForIntent();
        supportbot.getResponse();
        supportbot.InsertStringtoArray("no");
        supportbot.checkForIntent();
        supportbot.getResponse();
        supportbot.InsertStringtoArray("yes");
        supportbot.checkForIntent();
        supportbot.getResponse();




    }
}
