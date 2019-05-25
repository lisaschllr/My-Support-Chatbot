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

        System.out.println("-->"+ Intent);

        return Intent;
    }

    public String getResponse(){
        Response = "";
        String IntentPlusContext = ContextString + Intent;
        ContextString += Intent;
        if(IntentPlusContext.equals(dictonary.NoMatch)){
            Response = "Es tut mir leid, ich habe Sie nicht verstanden. Um welches Produkt handelt es sich?";
        }
        if (IntentPlusContext.contains(dictonary.NoMatch + dictonary.NoMatch)|IntentPlusContext.contains((dictonary.Solution2NotWorking))) {
            Response = "Es tut mit leid, ich kann Ihnen leider nicht weiter helfen. Ich leite Sie aber gerne an einen Mitarbeiter weiter";
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
            Response = "Dann wünsche ich Ihnen noch einen schönen Tag und viel Spaß mit unserem Produkt";

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
                        Intent = Intent + dictonary.SolutionWorking;
                        return true;
                    }else {
                        Intent = Intent + dictonary.SolutionNotWorking;
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
                    }else {
                        ContextString = dictonary.Solution2NotWorking;
                    }
                }
            }
        }
        return false;
    }





}
