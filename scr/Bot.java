import java.util.*;

public class Bot {
    private Pair pos;
    private Shiptest s;

    
    
    public Bot(Shiptest t){
        this.s = t;
        StartingBotPosition();
    }

    public void StartingBotPosition(){
        Random random = new Random();
        while(true){
            int i = random.nextInt(s.grid.length);
            int j = random.nextInt(s.grid.length);
            if(s.isOpen(i,j) || s.isDeadEnd(i,j)){
                Pair p = new Pair(i,j);
                this.pos = p;
                s.grid[i][j].setBot(true);
            }
        }
    }

    public boolean Sense(double alpha, Pair mousePos){
        if(s.isNextTo(pos)){
            return true;
        }
        int d = Math.abs(pos.getKey() - mousePos.getKey()) + Math.abs(pos.getValue() - mousePos.getValue());
        double x = ((-alpha)*(d-1));
        if(Math.random() < Math.exp(x)){
            return true;
        }
        else{
            return false;
        }
    }

    public void UpdateProbabilities(boolean beep, double alpha){
        for(int i = 0; i<s.grid.length; i++){
            for(int j = 0; j<s.grid.length; j++){
                int d = Math.abs(pos.getKey() - i) + Math.abs(pos.getValue() - j);
                double x = ((-alpha)*(d-1));
                if(beep){
                    double prob = Math.exp(x);
                    s.grid[i][j].setProbOfMouse(prob);
                }
                else if(!beep){
                    double prob = 1- Math.exp(x);
                    s.grid[i][j].setProbOfMouse(prob);
                }
            }
        }
    }

    

    public void MoveBot(Pair p){
        s.grid[pos.getKey()][pos.getValue()].SetState(1);
        s.grid[pos.getKey()][pos.getValue()].setBot(false);
        this.pos = p;
        s.grid[p.getKey()][p.getValue()].setBot(true);
        s.grid[p.getKey()][p.getValue()].SetState(5);
    }

    public Shiptest GetShip(){
        return this.s;
    }

    public void setShip(Shiptest t){
        this.s = t;
    }

    public Pair GetBotPos(){
        return this.pos;
    }

    public void SetBotPos(Pair p){
        this.pos = p;
    }



}
