public class Support_Bot {
    Dictonary dictonary = new Dictonary();
    private String ContextString = "";
    private String [] UsereingabeArray;
    private String Intent;
    private String IntentString;
    private String Response;



    public String GreetCustomer(){
        String Greeting = dictonary.Greeting;
        System.out.println(Greeting);
        return  Greeting;
    }
    public String[] InsertStringtoArray(String Usereingabe){
        System.out.println("Customer: " + Usereingabe);
        Usereingabe = Usereingabe.toLowerCase();
        UsereingabeArray = Usereingabe.split("[\\s \\p{Punct} ]+");

        return UsereingabeArray;
    }

    public String checkForIntent(){
        Intent  = "";
        checkForProduct(UsereingabeArray);
        checkForProblem(UsereingabeArray);
        checkForSolutionWorking(UsereingabeArray);
        checkForSolution2Working(UsereingabeArray);

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
        if(IntentPlusContext.equals(dictonary.NoMatch)){
            Response = "I am sorry, I don't understand you. Which of our products are you talking about?";
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
            Response = "Then I wish you a nice day and a lot of fun with our product.";

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

    public Boolean checkForProduct(String [] Usereingabe){
        for(int i =0 ; i < Usereingabe.length ; i++){
            for(int I = 0; I < dictonary.Laptop.length; I++) {
                if (dictonary.Laptop[I].equals(Usereingabe[i])) {
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


    public Boolean checkForProblem(String [] Usereingabe){
        for(int i =0 ; i < Usereingabe.length ; i++){
            for(int I = 0; I < dictonary.ProblemWithCharging.length; I++) {
                if (dictonary.ProblemWithCharging[I].equals(Usereingabe[i])) {
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

    public Boolean checkForSolutionWorking (String []Usereingabe){
        String IntentPlusContext = ContextString + Intent;
        if(IntentPlusContext.contains(dictonary.Solution)) {
            for (int i = 0; i < Usereingabe.length; i++) {
                for (int I = 0; I < dictonary.goodKeyword.length; I++) {
                    if (dictonary.goodKeyword[I].equals(Usereingabe[i])) {
                        Intent = dictonary.SolutionWorking;
                        return true;
                    }
                }
                for (int I = 0; I < dictonary.badKeyword.length; I++) {
                    if (dictonary.badKeyword[I].equals(Usereingabe[i])){
                        Intent = Intent + dictonary.SolutionNotWorking;
                        resetNoMatch();
                        return false;
                    }
                }
            }
        }
        return false;
    }

    public Boolean checkForSolution2Working (String []Usereingabe){
        String IntentPlusContext = ContextString + Intent;
        if(IntentPlusContext.contains(dictonary.Solution2)) {
            for (int i = 0; i < Usereingabe.length; i++) {
                for (int I = 0; I < dictonary.goodKeyword.length; I++) {
                    if (dictonary.goodKeyword[I].equals(Usereingabe[i])) {
                        ContextString = dictonary.Solution2Working;
                        return true;
                    }
                }
                for (int I = 0; I < dictonary.badKeyword.length; I++) {
                    if (dictonary.badKeyword[I].equals(Usereingabe[i])) {
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




}
