package pisti;
import java.util.Random;
public class pisti {

    
    public  static cards[] suiter(int a,int b,cards[] tar){
        if((b+1%13)<11&&(b%13)>0){
            tar[b].setNumber(String.valueOf(b+1%13));
        }
        switch((b+1)%13){
            case(1):
            tar[b].setNumber("ACE");
            break;
            case(11):
            tar[b].setNumber("JACK");
            break;
            case(12):
            tar[b].setNumber("QUEEN");
            break;
            case(0):
            tar[b].setNumber("KING");
            break;
        }
        switch((a+1)%4){
            case(1):
            tar[a].setSuit("♠");
            break;
            case(2):
            tar[a].setSuit("♣");
            break;
            case(3):
            tar[a].setSuit("♥");
            case(0):
            tar[a].setSuit("♦");

            
        }
        return tar;
    }
    public static cards[] shuffler(cards[] deck){
        Random rd = new Random();
        for(int i =0;i<51;i++){
            cards a = new cards("0","0");
            if(i<51){
                int b = rd.nextInt(51);
                a = deck[i];
                deck[i] = deck[b];
                deck[b] = a;
            }
            deck[51] = deck[1];
        }
        return deck;
    }
    public static void main(String[] args){
        cards[] card = new cards[52];
        for(int i = 0;i<52;i++){
            card[i] = new cards("0","0");
        }
            for(int k = 0;k<52;k++){
                    suiter(k,k,card);
            }
                for(cards i : card){
                        
                    System.out.println(i.getNumber());

                }
    }      
}
        
    
    

