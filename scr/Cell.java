public class Cell {
    private Pair pos;
    private int state;
    private boolean bot;
    private boolean mouse;
    private double probOfMouse; 

    public Cell(Pair p1, int Value, boolean b, boolean m1){
        this.pos   = p1;
        this.state = Value;
        this.bot = b;
        this.mouse = m1;
        this.probOfMouse = 1/1600;
    }

    public Cell(Pair p, int value){
        this.pos = p;
        this.state = value;
        this.bot = false;
        this.mouse = false;
        this.probOfMouse = 1/1600;
    }

    public Cell(){
        this.pos = null;
        this.state = 0;
        this.bot = false;
        this.mouse = false;
        this.probOfMouse = 1/1600;
    }

    public Pair getPair(){
        return this.pos;
    }

    public void SetPair(Pair p1){
        this.pos = p1;
    }

    public int GetState(){
        return this.state;
    }

    public void SetState(int i){
        this.state = i;
    }

    public boolean hasBot(){
        return this.bot;
    }

    public void setBot(boolean b){
        this.bot = b;
    }

    public boolean hasMouse(){
        return this.mouse;
    }

    public void setMouse(boolean b){
        this.mouse = b;
    }

    public double getProbOfMouse(){
        return this.probOfMouse;
    }

    public void setProbOfMouse(double p){
        this.probOfMouse = p;
    }



}
