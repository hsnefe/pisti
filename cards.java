package pisti;

public class cards {
    private String suit;
    private String number;
    private int suitrank;
    private int numberrank;
    public cards(String a,String b){
        suit = a;
        number = b;
    }
        public void setSuit(String a){suit = a;}       
        public void setNumber(String a){number = a;}
        public String getSuit() {return suit;}
        public String getNumber(){return number;}
        public void setSuitrank(int a){suitrank = a;}
        public int getSuitrank(){return suitrank;}
        public void setNumberrank(int a){numberrank=a;}
        public int getNumberrank(){return numberrank;}
}
