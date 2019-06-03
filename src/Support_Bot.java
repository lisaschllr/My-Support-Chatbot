public class Support_Bot {
    Dictonary dictonary = new Dictonary();
    private String ContextString = "";
    private String [] InsertArray;
    private String Intent;
    private String Response;


    public String GreetCustomer(){
        String Greeting = dictonary.Greeting;
        System.out.println(Greeting);
        return  Greeting;
    }
    public String[] InsertStringtoArray(String UserInsert){
        //System.out.println("Customer: " + UserInsert);
        UserInsert = UserInsert.toLowerCase();
        InsertArray = UserInsert.split("[\\s \\p{Punct} ]+");

        return InsertArray;
    }

    public String checkForIntent(){
        Intent  = "";
        checkForProduct(InsertArray);
        checkForProblem(InsertArray);
        checkForSolutionWorking(InsertArray);
        checkForSolution2Working(InsertArray);

        if(Intent.equals("")) {
            Intent = dictonary.NoMatch;
        }

       //System.out.println("-->"+ Intent);

        return Intent;
    }

    public String getResponse(){
        Response = "";
        String IntentPlusContext = ContextString + Intent;
        ContextString += Intent;
        if(IntentPlusContext.contains(dictonary.NoMatch)){
            Response = "I am sorry, I don't understand you. Could you please repeat the productname oder the problem?";
        }
        if (IntentPlusContext.contains(dictonary.NoMatch + dictonary.NoMatch)|IntentPlusContext.contains((dictonary.Solution2NotWorking))) {
            Response = "I am so sorry, I am not able to help you with your problem. However, I will gladly forward you to a member of staff";
        }
        if(IntentPlusContext.equals(dictonary.LaptopProblem)){
            Response = "Whats the Problem with your laptop?";
        }
        if (IntentPlusContext.equals(dictonary.ChargingProblem)) {
            Response = "Which of our Products is not charging?";
        }
        if(IntentPlusContext.equals(dictonary.LaptopProblem + dictonary.ChargingProblem) | IntentPlusContext.equals( dictonary.ChargingProblem + dictonary.LaptopProblem)){
            Response = "[Solution for Laptop charging problem]. Was it working?";
            ContextString += dictonary.Solution;
        }

        if(IntentPlusContext.contains(dictonary.SolutionWorking)|IntentPlusContext.contains(dictonary.Solution2Working)){
            Response = "Then I wish you a nice day and a lot of fun with our product. Goodbye!";

        }

        if(IntentPlusContext.contains(dictonary.SolutionNotWorking)){
            if(IntentPlusContext.contains(dictonary.LaptopProblem)){
                if(IntentPlusContext.contains(dictonary.ChargingProblem)){
                    Response = "[Alternative Solution]. Was it working?";
                    ContextString += dictonary.Solution2;
                }
            }
        }


        System.out.println(Response);
        return Response;


    }

    public Boolean checkForProduct(String [] UserInsert){
        for(int i =0 ; i < UserInsert.length ; i++){
            for(int I = 0; I < dictonary.Laptop.length; I++) {
                if (dictonary.Laptop[I].equals(UserInsert[i])) {
                    if(!ContextString.contains(dictonary.LaptopProblem)) {
                        Intent = Intent + dictonary.LaptopProblem;
                        resetNoMatch();
                        return true;
                    }

                }
            }
        }
        return false;
    }


    public Boolean checkForProblem(String [] UserInsert){
        for(int i =0 ; i < UserInsert.length ; i++){
            for(int I = 0; I < dictonary.ProblemWithCharging.length; I++) {
                if (dictonary.ProblemWithCharging[I].equals(UserInsert[i])) {
                    if(!ContextString.contains(dictonary.ChargingProblem)) {
                        Intent = Intent + dictonary.ChargingProblem;
                        resetNoMatch();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Boolean checkForSolutionWorking (String []UserInsert){
        String IntentPlusContext = ContextString + Intent;
        if(IntentPlusContext.contains(dictonary.Solution)) {
            for (int i = 0; i < UserInsert.length; i++) {
                for (int I = 0; I < dictonary.goodKeyword.length; I++) {
                    if (dictonary.goodKeyword[I].equals(UserInsert[i])) {
                        Intent = dictonary.SolutionWorking;
                        return true;
                    }
                }
                for (int I = 0; I < dictonary.badKeyword.length; I++) {
                    if (dictonary.badKeyword[I].equals(UserInsert[i])){
                        Intent = Intent + dictonary.SolutionNotWorking;
                        resetNoMatch();
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public Boolean checkForSolution2Working (String []UserInsert){
        String IntentPlusContext = ContextString + Intent;
        if(IntentPlusContext.contains(dictonary.Solution2)) {
            for (int i = 0; i < UserInsert.length; i++) {
                for (int I = 0; I < dictonary.goodKeyword.length; I++) {
                    if (dictonary.goodKeyword[I].equals(UserInsert[i])) {
                        ContextString = dictonary.Solution2Working;
                        return true;
                    }
                }
                for (int I = 0; I < dictonary.badKeyword.length; I++) {
                    if (dictonary.badKeyword[I].equals(UserInsert[i])) {
                        ContextString = dictonary.Solution2NotWorking;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void resetNoMatch(){
        if(ContextString.contains(dictonary.NoMatch)){
           ContextString = ContextString.replaceAll(dictonary.NoMatch, "");
        }
    }

    public boolean sayGoodbye(String UserInsert){
                for (int I = 0; I < dictonary.End.length; I++) {
                    if (UserInsert.contains(dictonary.End[I])) {
                        return true;
                    }
                }

        return false;

    }




}
