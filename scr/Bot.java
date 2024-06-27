import java.util.*;

public class Bot {
    private Pair pos;
    private Shiptest s;
    private HashSet<String> CellsTraversed = new HashSet<String>();
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
            String coord = temp.getKey() + "," + temp.getValue();
            CellsTraversed.add(coord);
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

    public boolean Sense(double alpha, Pair mousePos, Pair SecondPos){
        if(s.isNextTo(pos)){
            return true;
        }
        int d = Math.abs(pos.getKey() - mousePos.getKey()) + Math.abs(pos.getValue() - mousePos.getValue());
        double x = ((-alpha)*(d-1));
        int d1 = Math.abs(pos.getKey() - SecondPos.getKey()) + Math.abs(pos.getValue() - SecondPos.getValue());
        double x1 = ((-alpha)*(d-1));
        double prob = 0.0;
        if(Math.exp(x) > Math.exp(x1)){
            prob = Math.exp(x);
        }
        else{
            prob = Math.exp(x1);
        }
        if(Math.random() < prob){
            return true;
        }
        else{
            return false;
        }
    }

    public double ProbOfBeep(boolean beep, double alpha, Pair mousePos){
        int d = Math.abs(pos.getKey() - mousePos.getKey()) + Math.abs(pos.getValue() - mousePos.getValue());
        double x = ((-alpha)*(d-1));
        if(beep){
            return Math.exp(x);
        }
        else{
            return 1-Math.exp(x);
        }

    }


    public void UpdateProbabilitiesStationary(boolean beep, double alpha, int numOfMice, HashSet<String> CellsChecked){
        double ProbTotal = 0.00;
        for(int i = 0; i<s.grid.length; i++){
            for(int j = 0; j<s.grid.length; j++){
                if(s.isClosed(i,j)){
                    s.grid[i][j].setProbOfMouse(0.0);
                    continue;
                }
                if(beep){
                    String coord = i + "," + j;
                    if(CellsChecked.contains(coord)){
                        s.grid[i][j].setProbOfMouse(0.0);
                        continue;
                    }
                    int d = Math.abs(pos.getKey() - i) + Math.abs(pos.getValue() - j);
                    if(d == 0){
                        s.grid[i][j].setProbOfMouse(0.0);
                        continue;
                    }
                    double x = ((-alpha)*(d-1));
                    double probGivenMouseInCell = Math.exp(x);
                    double MouseInCell = (numOfMice*1.0)/(s.totalOpenCells - CellsChecked.size());
                    double NewMouseProbOfCell = (probGivenMouseInCell * MouseInCell);
                    ProbTotal+=NewMouseProbOfCell;
                    s.grid[i][j].setProbOfMouse(NewMouseProbOfCell);
                    
                }
                else{
                    String coord = i + "," + j;
                    if(CellsChecked.contains(coord)){
                        s.grid[i][j].setProbOfMouse(0.0);
                        continue;
                    }
                    int d = Math.abs(pos.getKey() - i) + Math.abs(pos.getValue() - j);
                    if(d == 0){
                        s.grid[i][j].setProbOfMouse(0.0);
                        continue;
                    }
                    double x = ((-alpha)*(d-1));
                    double probGivenMouseInCell = 1-Math.exp(x);
                    double MouseInCell = (numOfMice*1.0)/(s.totalOpenCells - CellsChecked.size());
                    double NewMouseProbOfCell = (probGivenMouseInCell * MouseInCell);
                    ProbTotal +=NewMouseProbOfCell;
                    s.grid[i][j].setProbOfMouse(NewMouseProbOfCell);
                }
            }
        }
        for(int i = 0; i<s.grid.length; i++){
            for(int j = 0; j<s.grid.length; j++){
                double OldProb = s.grid[i][j].getProbOfMouse();
                double FinalProb = OldProb/ProbTotal;
                s.grid[i][j].setProbOfMouse(FinalProb);
            }
        }
    }



    public void UpdateProbabilitiesMoving(boolean beep, double alpha, int numOfMice){
        double ProbTotal = 0.00;
        for(int i = 0; i<s.grid.length; i++){
            for(int j = 0; j<s.grid.length; j++){
                if(s.isClosed(i,j)){
                    s.grid[i][j].setProbOfMouse(0.0);
                    continue;
                }
                if(beep){
                    int d = Math.abs(pos.getKey() - i) + Math.abs(pos.getValue() - j);
                    if(d == 0){
                        s.grid[i][j].setProbOfMouse(0.0);
                        continue;
                    }
                    double x = ((-alpha)*(d-1));
                    double probGivenMouseInCell = Math.exp(x);
                    double MouseInCell = s.grid[i][j].getProbOfMouse();
                    double NewProb = (probGivenMouseInCell * MouseInCell);
                    ProbTotal += NewProb;
                    /* 
                    double TotalProbOfBeep = (probGivenMouseInCell * MouseInCell)+ (probGivenMouseInCell*(1-MouseInCell));
                    double NewMouseProbOfCell = (probGivenMouseInCell * MouseInCell)/TotalProbOfBeep;
                    */
                    s.grid[i][j].setProbOfMouse(NewProb);
                }
                else{
                    int d = Math.abs(pos.getKey() - i) + Math.abs(pos.getValue() - j);
                    if(d == 0){
                        s.grid[i][j].setProbOfMouse(0.00);
                        continue;
                    }
                    double x = ((-alpha)*(d-1));
                    double probGivenMouseInCell = 1-Math.exp(x);
                    double MouseInCell = s.grid[i][j].getProbOfMouse();
                    double NewProb = (probGivenMouseInCell * MouseInCell);
                    ProbTotal+=NewProb;
                    /* 
                    double TotalProbOfNoBeep = (probGivenMouseInCell * MouseInCell) + (1.0 * (1-MouseInCell));
                    double NewMouseProbOfCell = (probGivenMouseInCell * MouseInCell)/TotalProbOfNoBeep;
                    */
                    s.grid[i][j].setProbOfMouse(NewProb);
                }
            }
        }
        for(int i = 0; i<s.grid.length; i++){
            for(int j = 0; j<s.grid.length; j++){
                double OldProb = s.grid[i][j].getProbOfMouse();
                double FinalProb = OldProb/ProbTotal;
                s.grid[i][j].setProbOfMouse(FinalProb);
            }
        }
    }

    public Pair FindStartingCell(){
        Pair p = new Pair(-1,-1);
        for(int i = 19; i <=21; i++){
            for(int j = 17; j<23; j++){
                if(s.isOpen(i,j) == true || s.isDeadEnd(i,j) == true){
                    p = new Pair(i,j);
                    return p;
                }
            }
        }
        return p;
    }

    public Pair FindStartingCellTest(){
        Pair p = new Pair(-1,-1);
        for(int i = 2; i <3; i++){
            for(int j = 1; j<4; j++){
                if(s.isOpen(i,j) == true || s.isDeadEnd(i,j) == true){
                    p = new Pair(i,j);
                    return p;
                }
            }
        }
        return p;
    }

    public Pair FindHighestProbCell(){
        double max = 0.0;
        Pair p = new Pair(-1,-1);
        HashSet<String> ClosestTie = new HashSet<String>();
        for(int i = 0; i <s.grid.length; i++){
            for(int j = 0; j<s.grid[i].length; j++){
                if(s.isOpen(i,j) == true || s.isDeadEnd(i,j) == true || s.isBot(i,j) || s.isMouse(i,j) == true){
                    if(s.grid[i][j].getProbOfMouse() > max){
                        max = s.grid[i][j].getProbOfMouse();
                        p = new Pair(i, j);
                    }
                }
            }
        }
        //return p;
        int least = 1000;
        for(int i = 0; i <s.grid.length; i++){
            for(int j = 0; j<s.grid[i].length; j++){
                if(s.grid[i][j].getProbOfMouse() == max){
                    int d = Math.abs(pos.getKey() - i) + Math.abs(pos.getValue() - j);
                    if(d<least){
                        least = d;
                        p = new Pair(i,j);
                    }
                }
            }
        }
        
        return p;
        
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
        String coord = pos.getKey() + "," + pos.getValue();
        CellsTraversed.add(coord);
        this.pos = p;
        s.BotPosition = p;
        s.grid[p.getKey()][p.getValue()].setBot(true);
        s.grid[p.getKey()][p.getValue()].SetState(3);
    }

    public void MoveBotStationary(Pair p){
        s.grid[pos.getKey()][pos.getValue()].SetState(1);
        s.grid[pos.getKey()][pos.getValue()].setBot(false);
        s.grid[pos.getKey()][pos.getValue()].setProbOfMouse(0.00);
        String coord = p.getKey() + "," + p.getValue();
        CellsTraversed.add(coord);
        this.pos = p;
        s.BotPosition = p;
        s.grid[p.getKey()][p.getValue()].setBot(true);
        s.grid[p.getKey()][p.getValue()].SetState(3);
    }

    public ArrayList<Integer> ComputePath(int[] edge, int Start, int Finish){
        ArrayList<Integer> a = new ArrayList<Integer>();
        Stack<Integer> s = new Stack<Integer>();
        boolean reachedButton = false;
        int x = Finish;
        while(reachedButton != true){
            if(Start == x){
                reachedButton = true;
            }
            else{
                s.push(x);
                x = edge[x];
            }
        }
        while(!s.isEmpty()){
            a.add(s.pop());
        }
        return a;
    }

    public Pair GridLocationOfPath(int n){
        Pair p = new Pair();
        for(int i = 0; i <s.adjecencyGrid.length; i++){
            for(int j = 0; j<s.adjecencyGrid[i].length; j++){
                if(s.adjecencyGrid[i][j] == n){
                    p.setKey(i);
                    p.setValue(j);
                }
            }
        }
        return p;
    }

    public static void bfs(int startValue, ArrayList<LL> adj, ArrayList<Integer> adjPar, boolean [] vis, int [] edgeTo){
        Queue q = new Queue();
        q.enqueue(startValue);
        vis[startValue] = true;
        while(!q.isEmpty()){
            int v = q.dequeue();
            int index = adjPar.indexOf(v);
            LL ll = adj.get(index);
            for(IntNode ptr = ll.front; ptr != null; ptr = ptr.getNextIntNode()){
                int i = adjPar.indexOf(ptr.getInt());
                if(!vis[i]){
                    q.enqueue(ptr.getInt());
                    vis[i] = true;
                    edgeTo[i] = v;
                }
            }

        }
            
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

    public HashSet<String> GetCellsTraversed(){
        return this.CellsTraversed;
    }

    public void SetCellsTraversed(HashSet<String> p){
        this.CellsTraversed = p;
    }

}
