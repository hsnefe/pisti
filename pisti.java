package pisti;
import java.util.Random;
import java.util.Scanner;
public class pisti {

    
    public  static cards[] suiter(int a,int b,cards[] tar){
        if(((b+1)%13)<11&&(b+1%13)>0){
            tar[b].setNumber(String.valueOf((b+1)%13));
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
        if(a+1<14) tar[a].setSuit("♣");
        if((a+1<27)&&(a+1>13)) tar[a].setSuit("♠");
        if((a+1<40)&&(a+1>26)) tar[a].setSuit("♦");
        if(a+1>39) tar[a].setSuit("♥");
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
    public static cards[] cutter(cards[] deck,int a){
        cards[] part1 = new cards[a+1];
        cards[] part2 = new cards[51-a];
        int x = 0;
        for(int i =0;i<52;i++){
            if(i<=a) part1[i] = new cards(deck[i].getSuit(),deck[i].getNumber());
            if(i>a){
                part2[x] = deck[i];
                x++;
            }
        }
        int y =0;
        for(int k = 0;k<52;k++){
            if(k<=50-a) deck[k] = part2[k];
            if(k>50-a){
                deck[k] = part1[y]; 
                y++;
            }
        }
        
        return deck;
    }
    public static void main(String[] args){
        cards[] card = new cards[52];
        for(int i = 0;i<52;i++){
            card[i] = new cards("0","0");
        }
        for(int k = 0;k<52;k++){
                    card = suiter(k,k,card);
        }
        card =shuffler(card);
        Scanner sc = new Scanner(System.in);
        int cutn=sc.nextInt();
        card = cutter(card,cutn);
        for(cards i : card){
                        
        System.out.println(i.getNumber());
        System.out.print(i.getSuit());
        }
    }      
}
        
    
    

