public class Support_Bot {
    Dictonary dictonary = new Dictonary();
    private String Context;
    private String [] UsereingabeArray;
    private String Intent;
    private String Response;




    public String[] InsertStringtoArray(String Usereingabe){
        Usereingabe = Usereingabe.toLowerCase();
        UsereingabeArray = Usereingabe.split("[\\s \\p{Punct} ]+");

        for (int i =0; i< UsereingabeArray.length; i++){
        System.out.println(UsereingabeArray[i]);
        }

        return UsereingabeArray;
    }

    public String checkForIntent(){
        if(checkForProduct(UsereingabeArray)){
            Intent= "Laptop Problem";

        }
        if(checkForProblem(UsereingabeArray)){
            Intent = "Charging Problem";

        }
        System.out.println(Intent);
        return Intent;
    }

    public Boolean checkForProduct(String [] Usereingabe){
        for(int i =0 ; i < Usereingabe.length; i++){
            for(int I = 0; i < dictonary.Laptop.length; i++) {
                if (dictonary.Laptop[I].equals(Usereingabe[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    public Boolean checkForProblem(String [] Usereingabe){
        for(int i =0 ; i < Usereingabe.length; i++){
            for(int I = 0; i < dictonary.ProblemWithCharging.length; i++) {
                if (dictonary.ProblemWithCharging[I].equals(Usereingabe[i])) {
                    return true;
                }
            }
        }
        return false;

    }





}
