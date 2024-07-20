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

    public Mouse(Shiptest t, boolean b){
        this.s = t;
        this.pos = new Pair(23,12);
        s.StartingMousePos = this.pos;
        s.grid[this.pos.getKey()][this.pos.getValue()].SetState(5);
        s.grid[this.pos.getKey()][this.pos.getValue()].setMouse(true);
    }
    public Pair StartingMousePosition(){
        while(true){
            int x = random.nextInt(s.OpenCells.size());
            Pair temp = s.OpenCells.get(x);
            if(!s.isBot(temp.getKey(),temp.getValue()) && !s.isMouse(temp.getKey(), temp.getValue())){
                this.pos = temp;
                if(s.StartingMousePos != null){
                    s.SecondMousePos = temp;
                }
                else{
                    s.StartingMousePos = temp;
                }
                s.grid[temp.getKey()][temp.getValue()].SetState(5);
                s.grid[temp.getKey()][temp.getValue()].setMouse(true);
                return temp;
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

    

    public void MoveMouse1(Pair p){
        s.grid[pos.getKey()][pos.getValue()].SetState(1);
        s.grid[pos.getKey()][pos.getValue()].setMouse(false);
        this.pos = p;
        s.StartingMousePos= p;
        s.grid[p.getKey()][p.getValue()].setMouse(true);
        s.grid[pos.getKey()][pos.getValue()].SetState(5);
    }

    public void MoveMouse2(Pair p){
        s.grid[pos.getKey()][pos.getValue()].SetState(1);
        s.grid[pos.getKey()][pos.getValue()].setMouse(false);
        this.pos = p;
        s.SecondMousePos= p;
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
