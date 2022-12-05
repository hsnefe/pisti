package pisti;

public class pisti {

    
    public  static cards[] suiter(int a,int b,cards[] tar){
        if(b<9 && b != 0){
            tar[b].number = String.valueOf(b+1);
        }
        switch(b){
            case(0):
            tar[b].number = "A";
            break;
            case(10):
            tar[b].number ="JACK";
            break;
            case(11):
            tar[b].number ="QUEEN";
            break;
            case(12):
            tar[b].number ="KING";
            break;
        }
        switch(a){
            case(0):
            tar[a].suit = "♠";
            break;
            case(1):
            tar[a].suit ="♣";
            break;
            case(2):
            tar[a].suit ="♥";
            case(3):
            tar[a].suit ="♦";

            
        }
        return tar;
    }
    public static void main(String[] args){
        cards[] card = new cards[51];
            for(int j = 0;j<3;j++){
                for(int k = 0;k<12;k++){
                    suiter(j,k,card);
                        
                        
                        }
                    }
                    }      
                }
        
    
    

