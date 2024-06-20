import java.util.*;

public class Bot {
    private Pair pos;
    private Shiptest s;
    Random random = new Random();

    
    
    public Bot(Shiptest t){
        this.s = t;
        StartingBotPosition();
    }

    public void StartingBotPosition(){
        int x = random.nextInt(s.OpenCells.size());
        Pair temp = s.OpenCells.get(x);
        if(s.isOpen(temp.getKey(),temp.getValue()) || s.isDeadEnd(temp.getKey(),temp.getValue())){
            this.pos = temp;
            s.BotPosition = temp;
            s.grid[temp.getKey()][temp.getValue()].SetState(3);
            s.grid[temp.getKey()][temp.getValue()].setBot(true);
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

    public ArrayList<Pair> PotentialNextMove(int row, int col){
        ArrayList<Pair> a = new ArrayList<Pair>();
        if(s.InBounds(row-1, col)){
            if(s.isOpen(row -1, col)|| s.isDeadEnd(row-1, col)){
                a.add(new Pair(row-1, col));
            }
        }
        if(s.InBounds(row, col+1) ){
            if(s.isOpen(row , col+1)|| s.isDeadEnd(row, col+1)){
                a.add(new Pair(row, col+1));
            }
        }
        if(s.InBounds(row+1, col) == true){
            if(s.isOpen(row +1, col)|| s.isDeadEnd(row+1, col)){
                a.add(new Pair(row+1, col));
            }
        }
        if(s.InBounds(row, col-1) == true){
            if(s.isOpen(row , col-1)|| s.isDeadEnd(row, col-1)){
                a.add(new Pair(row, col-1));
            }
        }
        return a;
        
    }

    public Pair HighestProbNeighbor(ArrayList<Pair> a, int row, int col){
        double max = s.grid[row][col].getProbOfMouse();
        int idx =-1;
        for(int i = 0; i< a.size(); i++){
            if(max < s.grid[a.get(i).getKey()][a.get(i).getValue()].getProbOfMouse()){
                max = s.grid[a.get(i).getKey()][a.get(i).getValue()].getProbOfMouse();
                idx = i;
            }
        }
        if(idx == -1){
            int x = random.nextInt(a.size());
            Pair p = a.get(x);
            return p;
        }
        return a.get(idx);

    }
    

    public void MoveBot(Pair p){
        s.grid[pos.getKey()][pos.getValue()].SetState(1);
        s.grid[pos.getKey()][pos.getValue()].setBot(false);
        this.pos = p;
        s.BotPosition = p;
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
