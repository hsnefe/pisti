package pisti;

public class cards {
    private String suit;
    private String number;
    public cards(String a,String b){
        suit = a;
        number = b;
    }
        public void setSuit(String a){suit = a;}       
        public void setNumber(String a){number = a;}
        public String getSuit() {return suit;}
        public String getNumber(){return number;}
}
