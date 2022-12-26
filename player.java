package pisti;
public class player {
    private String name;
    private int score;
    public player(String a,int b){
        name = a;
        score = b;
    }
    public void setName(String a){name = a;}
    public String getName(){return name;}
    public void setScore(int a){score = a;}
    public int getScore(){return score;}
}
