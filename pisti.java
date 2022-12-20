package pisti;
import java.util.Random;
import java.util.Scanner;
public class pisti {

    
    public  static cards[] suiter(int a,cards[] tar){
        tar[a].setPoint(1);
        if(((a+1)%13)<11&&(a+1%13)>0){
            tar[a].setNumber(String.valueOf((a+1)%13));
            tar[a].setNumberrank((a+1)%13);
        }
        switch((a+1)%13){
            case(1):
            tar[a].setNumber("ACE");
            tar[a].setNumberrank(1);
            break;
            case(11):
            tar[a].setNumber("JACK");
            tar[a].setNumberrank(11);
            break;
            case(12):
            tar[a].setNumber("QUEEN");
            tar[a].setNumberrank(12);
            break;
            case(0):
            tar[a].setNumber("KING");
            tar[a].setNumberrank(13);
            break;
        }
        if(a+1<14){  
            tar[a].setSuitrank(1);
            tar[a].setSuit("♣");
        }
        if((a+1<27)&&(a+1>13)) {
            tar[a].setSuitrank(2);
            tar[a].setSuit("♠");
        }
        if((a+1<40)&&(a+1>26)) {
            tar[a].setSuitrank(3);
            tar[a].setSuit("♦");
        }
        if(a+1>39) {
            tar[a].setSuitrank(4);
            tar[a].setSuit("♥");
        }
        if((tar[a].getSuitrank()==1)&&(tar[a].getNumberrank()==2)) tar[a].setPoint(3);
        else if((tar[a].getSuitrank()==3)&&(tar[a].getNumberrank()==10)) tar[a].setPoint(4);
        else tar[a].setPoint(1);
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
            if(i<=a) part1[i] = deck[i]; // new cards(deck[i].getSuit(),deck[i].getNumber());
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
    public static cards[] dealer(cards[] deck,cards[] hand,int a,int c){
        int b =0;
        if(a%3 ==0){
            for(int i = c*8;(i<c*8+8)&&(b<4);i++){
                if(i%2==0){
                    hand[b] = deck[i];
                    b++;
                }
            }
        }
        else if(a%3 ==1){
            for(int i = c*8;(i<c*8+8)&&(b<4);i++){
                if(i%2==1){
                    hand[b] = deck[i];
                    b++;
                }
            }}
            else if(a%3==2){ 
            for(int j = 8;j<12;j++){
                hand[j-8] = deck[j];
            }
        }
        
        return hand;
    }
    public static cards[] cardplayer(cards[]table,int turn, cards throwed){
        table[turn+1] = throwed;
        return table;
    }
    public static void main(String[] args){
        cards[] card = new cards[52];
        for(int i = 0;i<52;i++){
            card[i] = new cards("0","0");
        }
        for(int k = 0;k<52;k++){
                    card = suiter(k,card);
        }
        System.out.println("CARDS ARE READY, SHUFFLING...");
        card =shuffler(card);
        Scanner sc = new Scanner(System.in);
        System.out.println("CARDS ARE SHUFFLED, CHOOSE A NUMBER TO CUT");
        int cutn=sc.nextInt();
        card = cutter(card,cutn-1);
        System.out.println("DEALING");
        cards[] phand = new cards[4];
        cards[] aihand = new cards[4];
        phand = dealer(card,phand,0,0);
        aihand = dealer(card,aihand,1,0);
        int usedcards = 11;
        int ppoint = 0;
        int aipoint = 0;
        cards[] table = new cards[52];
        table = dealer(card,table,2,0);
        for(cards i : phand){
            System.out.print(i.getNumber());
            System.out.println(i.getSuit());
            }
        int turn =3;
        int dealturn =1;
        for(;usedcards<52;usedcards=usedcards+12){
            for(int i =0;i!=9;i++){
                if(i%2==1){
                    System.out.println("YOUR TURN:");
                    System.out.println("Choose A Card To play");
                    int option = 1;
                    for(cards l : phand){
                        if(l==null) option++;
                        else{ 
                        System.out.print(option+")  ");
                        System.out.print(l.getNumber());
                        System.out.println(l.getSuit());
                        option++;}
                        }
                    int play = sc.nextInt();
                    for(int j=0;j<turn;j++){
                        if(phand[play-1]==null){
                            System.out.println("You can't play that card,play a different card");
                            play = sc.nextInt();
                        }
                        else{
                        }
                    }
                    //Daha önce kullanılmadığını doğrula; kullanılmadıysa devam et, kullanıldıysa yeni değer iste
                    //For>else>i=0,play=sc.nextInt 
                    
                    cardplayer(table,turn,phand[play-1]);
                    phand[play-1] = null;
                    if(table[turn]==table[turn+1]){ 
                        for(int nuller = i;nuller>0;nuller--){
                            ppoint += table[turn-nuller].getPoint();
                            table[turn-nuller]=null;
                        }
                    }
                    turn++;
                }
                if(i%2==0){
                    System.out.println("OPPONENT'S TURN:");
                    cardplayer(table,turn,aihand[0]);
                    System.out.println("OPPONENT PLAYED THE:"+ table[turn+1].getNumber()+table[turn+1].getSuit());
                    turn++;
                    
                }
            }
            System.out.println("BOTH OF THE PLAYER'S HANDS ARE EMPTY, DEALING NEW CARDS...");
            dealturn++;
            phand = dealer(card,phand,0,dealturn);
            aihand = dealer(card,aihand,1,dealturn);
            for(cards i : phand){
                System.out.print(i.getNumber());
                System.out.println(i.getSuit());
                }
        }
        /*for(cards i : phand){
        System.out.print(i.getNumber());
        System.out.println(i.getSuit());
        }*/
        for(int i =0;i!=9;i++){
            if(i%2==1){
                
                System.out.println("YOUR TURN:");
                System.out.println("Choose A Card To play");
                int option = 1;
                for(cards l : phand){
                    if(l==null) option++;
                    else{ 
                    System.out.print(option+")  ");
                    System.out.print(l.getNumber());
                    System.out.println(l.getSuit());
                    option++;}}
                int play = sc.nextInt();
                for(int j=0;j<turn;j++){
                    if((table[turn-j].getNumberrank()== phand[play-1].getNumberrank()) && ((table[turn-j].getSuitrank())== phand[play-1].getSuitrank())){
                        System.out.println("You can't play that card,play a different card");
                        play = sc.nextInt();
                    }
                    else{
                    }
                }
                
                cardplayer(table,turn,phand[play-1]);
                phand[play-1] = null;
                turn++;
            }
            if(i%2==0){
                System.out.println("OPONENT'S TURN:");
                cardplayer(table,turn,aihand[0]);
                System.out.println("OPPONENT PLAYED THE:"+ table[turn+1].getNumber()+table[turn+1].getSuit());
                turn++;
                
            }
        }
        System.out.println("//// END OF THE GAME\\\\\\");
    }      
}
        
    
    

