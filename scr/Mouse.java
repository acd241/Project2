import java.util.ArrayList;
import java.util.Random;

public class Mouse {
    private Pair pos;
    Shiptest s;
    Random random = new Random();

    public Mouse(Shiptest t){
        this.s = t;
        StartingMousePosition();
    }

    public void StartingMousePosition(){
        while(true){
            int x = random.nextInt(s.OpenCells.size());
            Pair temp = s.OpenCells.get(x);
            if(!s.isBot(temp.getKey(),temp.getValue())){
                this.pos = temp;
                s.StartingMousePos = temp;
                s.grid[temp.getKey()][temp.getValue()].SetState(5);
                s.grid[temp.getKey()][temp.getValue()].setMouse(true);
                break;
            }
        }
    }

    public Pair PickRandomNeighbor(int row, int col){
        ArrayList<Pair> a = new ArrayList<Pair>();
        a.add(new Pair(row, col));
        if(s.PotentialNextStep(row-1, col) == true){
            Pair p0 = new Pair(row-1, col);
            a.add(p0);
        }
        if(s.PotentialNextStep(row, col+1) == true){
            Pair p1 = new Pair(row, col+1);
            a.add(p1);
        }
        if(s.PotentialNextStep(row+1, col) == true){
            Pair p2 = new Pair(row+1, col);
            a.add(p2);
        }
        if(s.PotentialNextStep(row, col-1) == true){
            Pair p3 = new Pair(row, col-1);
            a.add(p3);
        }
        
        int n = random.nextInt(a.size());
        Pair p = a.get(n);
        return p;
        
    }

    public void UpdateProbabilities(boolean beep, double alpha){
        for(int i = 0; i<s.grid.length; i++){
            for(int j = 0; j<s.grid.length; j++){
                if(s.isClosed(i,j)){
                    s.grid[i][j].setProbOfMouse(0.0);
                    continue;
                }
                int d = Math.abs(pos.getKey() - i) + Math.abs(pos.getValue() - j);
                if(d == 1){
                    s.grid[i][j].setProbOfMouse(1.0);
                }
                else{
                    double x = (-alpha*(d-1));
                    double prob = Math.exp(x);
                    s.grid[i][j].setProbOfMouse(prob);
                    /* 
                    else if(!beep){
                        double prob = 1- Math.exp(x);
                        s.grid[i][j].setProbOfMouse(prob);
                    }
                        */
                }
            }
        }
    }

    public void MoveMouse(Pair p){
        s.grid[pos.getKey()][pos.getValue()].SetState(1);
        s.grid[pos.getKey()][pos.getValue()].setMouse(false);
        this.pos = p;
        s.StartingMousePos= p;
        s.grid[p.getKey()][p.getValue()].setMouse(true);
        s.grid[pos.getKey()][pos.getValue()].SetState(5);
    }

    public Shiptest GetShip(){
        return this.s;
    }

    public void setShip(Shiptest t){
        this.s = t;
    }

    public Pair GetMousePos(){
        return this.pos;
    }

    public void SetMousePos(Pair p){
        this.pos = p;
    }
}
