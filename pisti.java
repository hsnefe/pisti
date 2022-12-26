package pisti;
import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
import java.util.Formatter;
import java.util.InputMismatchException;
import java.nio.file.Paths;
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
        }
        return deck;
    }
    public static cards[] cutter(cards[] deck,int a){
        cards[] part1 = new cards[a+1];
        cards[] part2 = new cards[51-a];
        int x = 0;
        for(int i =0;i<52;i++){
            if(i<=a) part1[i] = deck[i];
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
    public static player[] wraplayer(player[]thelist,player upcoming,int hierarchy){
        if(hierarchy<=1) return thelist;{ 
        if(thelist[hierarchy-1].getScore()>upcoming.getScore()) return thelist;
        else{ 
            if(hierarchy==10) thelist[hierarchy-1]=upcoming;
            else{
                player temp = thelist[hierarchy-1];
                thelist[hierarchy-1]= thelist[hierarchy];
                thelist[hierarchy]= temp;
            }
             return wraplayer(thelist,upcoming,hierarchy-1);
        }
    }
    }
    public static void main(String[] args){
        player theplayer = new player("0",0); 
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name:"); 
        theplayer.setName(sc.nextLine());
        Random rd = new Random();
        cards[] card = new cards[52];
        for(int i = 0;i<52;i++){
            card[i] = new cards("0","0");
        }
        for(int k = 0;k<52;k++){
                    card = suiter(k,card);
        }
        System.out.println("CARDS ARE READY, SHUFFLING...");
        card =shuffler(card);
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
        int playerpocket = 0;
        int aipocket=0;
        int boardsize = 3;
        cards[] table = new cards[52];
        table = dealer(card,table,2,0);
        for(cards i : phand){
            System.out.print(i.getNumber());
            System.out.println(i.getSuit());
            }
        int turn =3;
        int dealturn =1;
        for(;usedcards<52;usedcards=usedcards+12){
            for(int i =0;i!=8;i++){
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
                    while(phand[play-1]==null){ 
                            System.out.println("You can't play that card,play a different card");
                            play = sc.nextInt();
                        
                    }
                    cardplayer(table,turn,phand[play-1]);
                    phand[play-1] = null;
                    boardsize++;
                    if(table[turn]!=null){ 
                    if(table[turn].getNumberrank()==table[turn+1].getNumberrank()){ 
                        if(table[turn-1]==null){ 
                            table[turn+1].setPoint(10+table[turn].getPoint());
                            System.out.println("!!!!!!!!!!!!PİSTİİİİ!!!!!!!!!!!!");
                        }
                        playerpocket +=boardsize+1;
                        for(cards win:table){
                            if(win!= null){
                                ppoint += win.getPoint();
                            }
                        }
                            for(int a=turn+1;a>=0;a--){
                                table[a]=null;
                            }
                            
                        boardsize = 0;
                    }
                    if(table[turn]!=null){ 
                    if(table[turn+1].getNumberrank()==11){ 
                        
                        playerpocket +=boardsize+1;
                        for(cards wincards: table){
                            if(wincards!=null){
                                ppoint+= wincards.getPoint();
                                wincards =null;
                            }
                        }
                        boardsize = 0;
                    }
                }
                    
                }
                    turn++;
                }
                if(i%2==0){
                System.out.println("OPPONENT'S TURN:");
                if(table[turn]!=null){ 
                    for(cards smart: aihand){
                        if(smart!=null){ 
                        if(smart.getNumberrank()==table[turn].getNumberrank()){ 
                            cardplayer(table,turn,smart);
                            smart = null;
                            boardsize++;
                            if(table[turn-1]==null){ 
                                table[turn+1].setPoint(10+table[turn+1].getPoint());
                                System.out.println("!!!!!!!!!!!!PİSTİİİİ!!!!!!!!!!!!");
                            }
                            aipocket +=boardsize+1;
                            for(cards wincards: table){
                                if(wincards!=null){
                                    aipoint+= wincards.getPoint();
                                }
                            }
                            for(int a=turn+1;a>=0;a--){
                                table[a]=null;
                            }
                            boardsize = 0;
                            break;
                        }
                    }
                }
                }
                if(table[turn+1]==null){ 
                    int oyna = rd.nextInt(4);
                while(aihand[oyna]==null) oyna = rd.nextInt(4);
                cardplayer(table,turn,aihand[oyna]);
                aihand[oyna]=null;
                    boardsize++;    
                    if(table[turn]!=null){  
                    if(table[turn+1].getNumberrank()==11){ 
                        
                        aipocket +=boardsize+1;
                        for(cards wincards: table){
                            if(wincards!=null){
                                aipoint+= wincards.getPoint();
                                wincards =null;
                            }
                        }
                        boardsize = 0;
                    }
                }
                }
                System.out.println("OPPONENT PLAYED THE:"+ table[turn+1].getNumber()+table[turn+1].getSuit());
                turn++;
                }
            }
            System.out.println("BOTH OF THE PLAYER'S HANDS ARE EMPTY, DEALING NEW CARDS...");
            dealturn++;
            phand = dealer(card,phand,0,dealturn);
            aihand = dealer(card,aihand,1,dealturn);
        }
        for(int i =0;i!=8;i++){
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
                    if(phand[play-1]==null){
                        System.out.println("You can't play that card,play a different card");
                        try{ 
                        play = sc.nextInt();}
                        catch(InputMismatchException e){
                            System.err.println("Enter an integer, please...");
                        }
                    }
                    else{
                    }
                }
                cardplayer(table,turn,phand[play-1]);
                phand[play-1] = null;
                boardsize++;
                if(table[turn]!=null){ 
                    if(table[turn].getNumberrank()==table[turn+1].getNumberrank()){ 
                        playerpocket +=boardsize+1;
                        if(table[turn-1]==null){ 
                            table[turn+1].setPoint(10+table[turn+1].getPoint());
                    System.out.println("!!!!!!!!!!!!PİSTİİİİ!!!!!!!!!!!!");
                    boardsize =0;
                }
                for(cards win:table){
                    if(win!= null){
                        ppoint += win.getPoint();
                    }
                }
                    for(int a=turn+1;a>=0;a--){
                        table[a]=null;
                    }
                        boardsize = 0;
                        break;
                    }
                    if(table[turn]!=null){ 
                        if(table[turn+1].getNumberrank()==11){ 
                            
                            playerpocket +=boardsize+1;
                            for(cards wincards: table){
                                if(wincards!=null){
                                    ppoint+= wincards.getPoint();
                                    wincards =null;
                                }
                            }
                            for(int a=turn+1;a>=0;a--){
                                table[a]=null;
                            }
                            boardsize = 0;
                        }
                    }
                }
                turn++;
            }
            if(i%2==0){
                if(table[turn]!=null){ 
                    for(cards smart: aihand){
                        if(smart!=null){ 
                        if(smart.getNumberrank()==table[turn].getNumberrank()){ 
                            cardplayer(table,turn,smart);
                            smart = null;
                            boardsize++;
                            if(table[turn-1]==null){ 
                                table[turn+1].setPoint(10+table[turn+1].getPoint());
                                System.out.println("!!!!!!!!!!!!PİSTİİİİ!!!!!!!!!!!!");
                            }
                            aipocket +=boardsize+1;
                            for(int a=boardsize+1;a>=0;a--){
                                table[a]=null;
                            }
                            boardsize = 0;
                            turn++;
                        }
                    }
                }
                }
                    int oyna = rd.nextInt(4);
                while(aihand[oyna]==null) oyna = rd.nextInt(4);
                cardplayer(table,turn,aihand[oyna]);
                System.out.println("OPPONENT PLAYED THE:"+ table[turn+1].getNumber()+table[turn+1].getSuit());
                aihand[oyna]=null;
                    boardsize++;    
                    if(table[turn]!=null){
                    if(table[turn+1].getNumberrank()==11){ 
                        aipocket +=boardsize+1;
                        for(cards wincards: table){
                            if(wincards!=null){
                                aipoint+= wincards.getPoint();
                            }
                            for(int a =turn+1;a>=0;a--){
                                table[a]= null;
                            }
                        }
                        boardsize = 0;
                        turn++; 
                    }
                }
                }
            }
        
        System.out.println("//// END OF THE GAME\\\\\\");
        System.out.println("You have "+playerpocket+" cards");
        System.out.println("AI have "+aipocket+" cards");
        if(playerpocket>aipocket){
            System.out.println("You have more cards");
            ppoint+=3;
        }
        else{
            System.out.println("AI has more cards");
            aipoint +=3;
        }
        System.out.println("Your score: "+ppoint);
        System.out.println("AI's score: "+aipoint);
        if(ppoint>aipoint) System.out.println("YOU WİN!");
        else System.out.println("YOU LOSED!!!");
        theplayer.setScore(ppoint);
        Formatter f = null;
        player[] toplayers = new player[10];
        Scanner reader = null;
        try{
            reader = new Scanner(Paths.get("scores.txt"));
        while(reader.hasNextLine()){
            String[] user = reader.nextLine().split(":");
            for(int i =0;i<user.length;i++){
                if(i%2==0) toplayers[i/2].setName(user[i]);
                else toplayers[(i-1)/2].setScore(Integer.parseInt(user[i]));
            }
        }
        player[] theultimatelist = wraplayer(toplayers,theplayer,10);
        for(player t :theultimatelist){
            System.out.print(t.getName()+":");
            System.out.println(t.getScore());
        }
        }catch(Exception e){
            try{
     
                f = new Formatter("scores.txt");
                f.format("%s\n","Irem:95:Hasan:87:Bartu:56:Defne:38:Mert:37:Kirac:30:Erkan:29:Bora:21:Can:12:Yagız:0");
            }
            catch(IOException u){
                System.err.println("Something's wrong");
            }
            finally{
                if(f != null) f.close();
            }
        }
        finally{
        }
        reader = null;
        for(int i = 0;i<toplayers.length;i++){
            toplayers[i] = new player("0",0);
        }
       
        try{ 
        reader = new Scanner(Paths.get("scores.txt"));
        while(reader.hasNextLine()){
            String[] user = reader.nextLine().split(":");
            for(int i =0;i<user.length;i++){
                if(i%2==0) toplayers[i/2].setName(user[i]);
                else toplayers[(i-1)/2].setScore(Integer.parseInt(user[i]));
            }
        }
        player[] theultimatelist = wraplayer(toplayers,theplayer,10);
        try{
            for(player u : theultimatelist){ 
                f = new Formatter("scores.txt");
                f.format("%s,%s,%s,%s",":",u.getName(),":",u.getScore());
            }
        }catch(IOException e){
            System.err.println("Something's wrong in the final formatter");
        }finally{
            if(f!=null) f.close();
        }
        for(player t :theultimatelist){
            System.out.print(t.getName()+":");
            System.out.println(t.getScore());
        }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        
    }      
}