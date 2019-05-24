public class Support_Bot {
    Dictonary dictonary = new Dictonary();
    private String ContextString = "";
    private String [] UsereingabeArray;
    private String Intent;
    private String IntentString;
    private String Response;




    public String[] InsertStringtoArray(String Usereingabe){
        Usereingabe = Usereingabe.toLowerCase();
        UsereingabeArray = Usereingabe.split("[\\s \\p{Punct} ]+");

        return UsereingabeArray;
    }

    public String checkForIntent(){
        Intent  = "";
        checkForProduct(UsereingabeArray);
        checkForProblem(UsereingabeArray);

        if(Intent.equals("")) {
            Intent = dictonary.NoMatch;
        }

        System.out.println(Intent);

        return Intent;
    }

    public String getResponse(){
        Response = "";
        String IntentPlusContext = Intent + ContextString;
        if(IntentPlusContext.equals(dictonary.LaptopProblem + dictonary.ChargingProblem)){
            Response = "Solution for Laptop charging problem";
            System.out.println(Response);

        }

        ContextString += Intent;
        return Response;


    }

    public Boolean checkForProduct(String [] Usereingabe){
        for(int i =0 ; i < Usereingabe.length ; i++){
            for(int I = 0; I < dictonary.Laptop.length; I++) {
                if (dictonary.Laptop[I].equals(Usereingabe[i])) {
                    Intent = Intent + dictonary.LaptopProblem;
                    return true;
                }
            }
        }
        return false;
    }


    public Boolean checkForProblem(String [] Usereingabe){
        for(int i =0 ; i < Usereingabe.length ; i++){
            for(int I = 0; I < dictonary.ProblemWithCharging.length; I++) {
                if (dictonary.ProblemWithCharging[I].equals(Usereingabe[i])) {
                    Intent = Intent + dictonary.ChargingProblem;
                    return true;
                }
            }
        }
        return false;
    }





}
