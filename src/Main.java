public class Main {
    public static void main (String[]args){

        //implement interface here;
        //String Usereigabe aus interface übergeben
        Support_Bot supportbot = new Support_Bot();
        supportbot.InsertStringtoArray("laptop");
        supportbot.checkForIntent();
        supportbot.getResponse();
        supportbot.InsertStringtoArray("charge");
        supportbot.checkForIntent();
        supportbot.getResponse();



    }
}
