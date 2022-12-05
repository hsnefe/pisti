package pisti;

public class pisti {

    
    public  static cards[] suiter(int a,int b,cards[] tar){
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
            for(int j = 0;j<4;j++){
                for(int k = 0;k<12;k++){
                    suiter(j,k,card);
                        
                        
                        }
                    }
                    }      
                }
        
    
    

