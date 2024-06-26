import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;



public class Shiptest {

    private static final int Open = 1;
    private static final int Mouse = 5;
    private static final int Blocked = 0;
    private static final int Bot = 3;
    private static final int DeadEnd = 2;


    public Cell[][] grid;
    public int [][] adjecencyGrid;
    public ArrayList<Integer> AdjListPar = new ArrayList<Integer>();
    public ArrayList<LL> AdjList = new ArrayList<LL>();
    public Pair BotPosition;
    public Pair StartingMousePos;
    public Pair SecondMousePos;
    public int [] edgeTo;
    public boolean [] visited;
    public int totalOpenCells = 1;
    public boolean IsBot = false;
    public ArrayList<Pair> OpenCells;
    public double [][] MouseGrid1;
    public double [][] MouseGrid2;
    //BufferedWriter b = new BufferedWriter();

    Random random = new Random();

    public Shiptest(){
        grid = new Cell[40][40];
        adjecencyGrid = new int [40][40];
        int x = random.nextInt(40);
        int y = random.nextInt(40);
        PopulateShip(grid);
        grid[x][y].SetState(Open);
        ExploreShip();
        IdentifyDeadEnds();
        AddExtraOpenCells();
        OpenCells = LabelAllOpenCells();
        IdentifyDeadEnds();
        AdjGrid();
        AdjParList();
        ArrayList<LL> temp = AdjList1();
        AdjList = AdjList2(temp);
        StartingProbabilities();
        visited = new boolean [AdjList.size()];
        edgeTo = new int [AdjList.size()];

        int n = random.nextInt(OpenCells.size());
        int n2 = random.nextInt(OpenCells.size());
        /* 
        while(true){
            if (n!=n2){
                break;
            }
            else{
                n = random.nextInt(a.size());
                n2 = random.nextInt(a.size());
            }
        }
        
        BotPosition = a.get(n);
        StartingMousePos = a.get(n2);
        grid[BotPosition.getKey()][BotPosition.getValue()].SetState(Bot);
        grid[StartingMousePos.getKey()][StartingMousePos.getValue()].SetState(Mouse);
        */
    }

    public Shiptest(int Mice){
        grid = new Cell[40][40];
        adjecencyGrid = new int [40][40];
        int x = random.nextInt(40);
        int y = random.nextInt(40);
        PopulateShip(grid);
        grid[x][y].SetState(Open);
        ExploreShip();
        IdentifyDeadEnds();
        AddExtraOpenCells();
        OpenCells = LabelAllOpenCells();
        IdentifyDeadEnds();
        AdjGrid();
        AdjParList();
        ArrayList<LL> temp = AdjList1();
        AdjList = AdjList2(temp);
        StartingProbabilities(Mice);
        visited = new boolean [AdjList.size()];
        edgeTo = new int [AdjList.size()];

        int n = random.nextInt(OpenCells.size());
        int n2 = random.nextInt(OpenCells.size());
        /* 
        while(true){
            if (n!=n2){
                break;
            }
            else{
                n = random.nextInt(a.size());
                n2 = random.nextInt(a.size());
            }
        }
        
        BotPosition = a.get(n);
        StartingMousePos = a.get(n2);
        grid[BotPosition.getKey()][BotPosition.getValue()].SetState(Bot);
        grid[StartingMousePos.getKey()][StartingMousePos.getValue()].SetState(Mouse);
        */
    }

    public Shiptest(int size, int mice){
        grid = new Cell[5][5];
        adjecencyGrid = new int [5][5];
        int x = random.nextInt(5);
        int y = random.nextInt(5);
        PopulateShip(grid);
        grid[x][y].SetState(Open);
        ExploreShip();
        IdentifyDeadEnds();
        AddExtraOpenCells();
        OpenCells = LabelAllOpenCells();
        IdentifyDeadEnds();
        AdjGrid();
        AdjParList();
        ArrayList<LL> temp = AdjList1();
        AdjList = AdjList2(temp);
        StartingProbabilities(mice);
        visited = new boolean [AdjList.size()];
        edgeTo = new int [AdjList.size()];
    }
    

    public void StartingProbabilities(){
        for(int i = 0; i<grid.length; i++){
            for (int j = 0; j<grid[i].length; j++){
                if(isOpen(i,j)|| isDeadEnd(i, j) || isMouse(i, j)){
                    double d = 1.0/(totalOpenCells-1);
                    grid[i][j].setProbOfMouse(d);
                }
                else{
                    grid[i][j].setProbOfMouse(0.000);
                }
            }
        }
    }

    public void StartingProbabilities(int numOfMice){
        for(int i = 0; i<grid.length; i++){
            for (int j = 0; j<grid[i].length; j++){
                if(isOpen(i,j)|| isDeadEnd(i, j) || isMouse(i, j)){
                    double d = (1.0*numOfMice)/totalOpenCells;
                    grid[i][j].setProbOfMouse(d);
                }
                else{
                    grid[i][j].setProbOfMouse(0.000);
                }
            }
        }
    }

    public void PopulateShip(Cell [][] g){
        for(int i = 0; i<g.length; i++){
            for (int j = 0; j<g[i].length; j++){
                g[i][j] = new Cell(new Pair(i,j), 0);
            }
        }
    }

    public void InitializeMouseGrid(double [][] a){
        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j<grid[j].length; j++){
                if(isOpen(i,j)|| isDeadEnd(i, j) || isMouse(i, j)){
                    a[i][j] = 1/totalOpenCells;
                }
                else{
                    a[i][j]=0.0;
                }
            }
        }
    }

    public Pair GetBotPosition(){
        return BotPosition;
    }

    public void SetBotPosition(Pair p){
        BotPosition = p;
    }

    public int [] GetEdgeTo(){
        return this.edgeTo;
    }

    public void DeInitilizeEdge(int [] edge){
        int l = edge.length;
        for(int i = 0; i<edge.length; i++){
            edge[i] = 0;
        }
        edge = null;
        edge = new int[l];
    }

    public void DeInitilizevisit(boolean [] visit){
        int l = visit.length;
        for(int i = 0; i<visit.length; i++){
            visit[i] = false;
        }
        visit = null;
        visit = new boolean [l];
    }
    public void PrintPath(ArrayList<Integer> a){
        for(int i = 0; i<a.size(); i++){
            System.out.print(a.get(i) + " ");
        }
        System.out.println();
    }



    public void PrintShip(Cell [][] g) /*(throws IOException*/{
        for(int i = 0; i<g.length; i++){
            for(int j = 0; j<g[i].length; j++){
                System.out.print(g[i][j].GetState()+" ");
            }
            System.out.println();
        }
        System.out.println();
        /*BufferedWriter b = null;

        try
        {

            for (int i = 0; i < g.length; i++)
            {
                for (int j = 0; j < g[i].length; j++)
                {
                    b.write(g[i][j].GetState() + " ");
                }
            }
            b.newLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (b != null)
            {
                b.close();
            }
        }
            */
    }

    public void AdjGrid(){
        int x = 0;
        for(int i = 0; i <grid.length; i++){
            for(int j = 0; j<grid[i].length; j++){
                if(isOpen(i,j) == true || isDeadEnd(i,j) == true || isBot(i,j) == true || isMouse(i,j) == true){
                    adjecencyGrid[i][j] = x;
                    x+=1;
                }
                else{
                    adjecencyGrid[i][j] = -2;
                }
            }
        }
    }

    public void AdjParList(){
        for(int i = 0; i <adjecencyGrid.length; i++){
            for(int j = 0; j<adjecencyGrid[i].length; j++){
                if(isOpen(i,j) == true || isDeadEnd(i,j) == true || isBot(i,j) == true || isMouse(i,j) == true){
                    AdjListPar.add(adjecencyGrid[i][j]);
                }
                else{
                    
                }
            }
        }
    }

    public ArrayList<LL> AdjList1(){
        ArrayList<LL> a = new ArrayList<LL>();
        for(int i = 0; i <adjecencyGrid.length; i++){
            for(int j = 0; j<adjecencyGrid[i].length; j++){
                if(isOpen(i,j) == true || isDeadEnd(i,j) == true ||  isBot(i,j) == true || isMouse(i,j) == true){
                    LL l = new LL();
                    l.addToBack(adjecencyGrid[i][j]);
                    a.add(l);
                }
            }
        }

        return a;
    }

    public ArrayList<LL> AdjList2(ArrayList<LL> a){
        for(int i = 0; i <adjecencyGrid.length; i++){
            for(int j = 0; j<adjecencyGrid[i].length; j++){
                if(isOpen(i,j) == true || isDeadEnd(i,j) == true  || isBot(i,j) == true || isMouse(i,j) == true){
                    LL as = OpenNeighbors(i,j);
                    int idx = AdjListPar.indexOf(adjecencyGrid[i][j]);
                    for(IntNode ptr = as.front; ptr != null; ptr = ptr.getNextIntNode()){
                        a.get(idx).addToBack(ptr.getInt());
                    }

                }
            }
        }
        return a;
    }

    public LL OpenNeighbors(int row, int col){
        LL a = new LL();
        if(InBounds(row-1, col) == true){
            if(isOpen(row-1, col) == true || isBot(row-1, col) == true || isMouse(row-1, col) == true || isDeadEnd(row-1, col) == true ){
                int x = adjecencyGrid[row-1][col];
                a.addToBack(x);
            }
        }
        if(InBounds(row, col+1) == true){
            if(isOpen(row, col+1)|| isBot(row, col+1) == true || isMouse(row, col+1) == true || isDeadEnd(row, col+1) == true){
                int x = adjecencyGrid[row][col+1];
                a.addToBack(x);
            }
        }
        if(InBounds(row+1, col) == true){
            if(isOpen(row+1, col) || isBot(row+1, col) == true || isMouse(row+1, col) == true || isDeadEnd(row+1, col) == true ){
                int x = adjecencyGrid[row+1][col];
                a.addToBack(x);
            }
        }
        if(InBounds(row, col-1) == true){
            if(isOpen(row, col-1) || isBot(row, col-1) == true || isMouse(row, col-1) == true || isDeadEnd(row, col-1) == true ){
                int x = adjecencyGrid[row][col-1];
                a.addToBack(x);
            }
        }
        return a;
    }


    public boolean isOpen(int row, int col){
        if(grid[row][col].GetState() == Open){
            return true;
        }
        else{
            return false;
        }

    }


    public boolean isDeadEnd(int row, int col){
        if(grid[row][col].GetState() == DeadEnd){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isClosed(int row, int col){
        if(grid[row][col].GetState() == Blocked){
            return true;
        }
        else{
            return false;
        }

    }


    public boolean isMouse(int row, int col){
        if(grid[row][col].GetState() == Mouse){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isBot(int row, int col){
        if(grid[row][col].GetState() == Bot){
            return true;
        }
        else{
            return false;
        }
    }


    public boolean isNextTo(Pair p){
        if(InBounds(p.getKey()+1, p.getValue()) == true){
            if(grid[p.getKey()+1][p.getValue()].hasMouse()){
                return true;
            }
        }
        if(InBounds(p.getKey(), p.getValue()+1) == true){
            if(grid[p.getKey()][p.getValue()+1].hasMouse()){
                return true;
            }
        }
        if(InBounds(p.getKey()-1, p.getValue()) == true){
            if(grid[p.getKey()-1][p.getValue()].hasMouse()){
                return true;
            }
        }
        if(InBounds(p.getKey(), p.getValue()-1) == true){
            if(grid[p.getKey()][p.getValue() -1].hasMouse()){
                return true;
            }
        }
        return false;
    }


    public boolean InBounds(int row, int col){
        if(0<=row && row <grid.length){
            if(0<=col && col<grid.length){
                return true;
            }
            return false;
        }
        return false;
    }

    public boolean ExplorableNeighbor(int row, int col){
        if(0<=row && row <grid.length){
            if(0<=col && col<grid.length){
                if(isClosed(row,col) == true){
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    public boolean PotentialNextStep(int row, int col){
        if(0<=row && row <grid.length){
            if(0<=col && col<grid.length){
                if(isOpen(row,col) == true|| isDeadEnd(row, col)|| isBot(row, col)){
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }
    
    public int numOfClosedNeighbors(int row, int col){
        int count = 0;
        if(InBounds(row+1, col) == true){
            if(isClosed(row+1, col) == true){
                count+=1;
            }
        }
        if(InBounds(row, col+1) == true){
            if(isClosed(row, col+1) == true){
                count+=1;
            }
        }
        if(InBounds(row-1, col) == true){
            if(isClosed(row-1, col) == true){
                count+=1;
            }
        }
        if(InBounds(row, col-1) == true){
            if(isClosed(row, col-1) == true){
                count+=1;
            }
        }
        return count;

    }
    public int numOfOpenNeighbors(int row, int col){
        int count = 0;
        if(InBounds(row+1, col) == true){
            if(isOpen(row+1, col) == true){
                count+=1;
            }
        }
        if(InBounds(row, col+1) == true){
            if(isOpen(row, col+1) == true){
                count+=1;
            }
        }
        if(InBounds(row-1, col) == true){
            if(isOpen(row-1, col) == true){
                count+=1;
            }
        }
        if(InBounds(row, col-1) == true){
            if(isOpen(row, col-1) == true){
                count+=1;
            }
        }
        return count;

    }

    public boolean HasExplorableNeighbors(){
        int count = 0;
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[i].length; j++){
                if(ExplorableNeighbor(i, j) == true && numOfOpenNeighbors(i,j) == 1){
                    count +=1;
                }
            }
        }
        if(count>0){
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<Pair> CellsThatAreExplorable(){
        ArrayList<Pair> a = new ArrayList<Pair>();
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[i].length; j++){
                if(ExplorableNeighbor(i, j) == true && numOfOpenNeighbors(i,j) == 1){
                    Pair p = new Pair(i,j);
                    a.add(p);
                }
            }
        }
        return a;
    }

    public void ExploreShip(){
        while(HasExplorableNeighbors()){
            ArrayList<Pair> a = CellsThatAreExplorable();
            if(a.size() == 0){
                break;
            }
            int n = random.nextInt(a.size());
            Pair b = a.get(n);
            int i = b.getKey();
            int j = b.getValue();
            grid[i][j].SetState(Open);
            totalOpenCells+=1;
        }
    }

    public int IdentifyDeadEnds(){
        int x = 0;
        ArrayList<Pair> a = new ArrayList<Pair>();
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[i].length; j++){
                if(isOpen(i,j) == true && numOfOpenNeighbors(i,j) == 1){
                    Pair p = new Pair(i, j);
                    a.add(p);
                }
            }
        }
        for(int b = 0; b<a.size(); b++){
            Pair d = a.get(b);
            int z = d.getKey();
            int y = d.getValue();
            grid[z][y].SetState(DeadEnd);
            x+=1;
        }
        return x;
    }

    public Pair PickRandomNeighbor(int row, int col){
        ArrayList<Pair> a = new ArrayList<Pair>();
        if(ExplorableNeighbor(row-1, col) == true){
            Pair p0 = new Pair(row-1, col);
            a.add(p0);
        }
        if(ExplorableNeighbor(row, col+1) == true){
            Pair p1 = new Pair(row, col+1);
            a.add(p1);
        }
        if(ExplorableNeighbor(row+1, col) == true){
            Pair p2 = new Pair(row+1, col);
            a.add(p2);
        }
        if(ExplorableNeighbor(row, col-1) == true){
            Pair p3 = new Pair(row, col-1);
            a.add(p3);
        }
        if(a.size() != 0){
            int n = random.nextInt(a.size());
            Pair p = a.get(n);
            return p;
        }
        Pair p = new Pair();
        return p;
    }

    public void AddExtraOpenCells(){
        ArrayList<Pair> a = new ArrayList<Pair>();
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[i].length; j++){
                if(grid[i][j].GetState() == DeadEnd){
                    if(random.nextBoolean()){
                        Pair p = new Pair(i, j);
                        a.add(p);
                    }
                    else{
                        continue;
                    }
                }
            }
        }
        for(int b = 0; b<a.size(); b++){
            Pair p = a.get(b);
            int row = p.getKey();
            int col = p.getValue();
            Pair neighborPair = PickRandomNeighbor(row, col);
            row = neighborPair.getKey();
            col = neighborPair.getValue();
            if(row == -2 && col == -2){
                continue;
            }
            else{
                grid[row][col].SetState(Open);
                totalOpenCells+=1;
            }
        }
    }

    public ArrayList<Pair> LabelAllOpenCells(){
        ArrayList<Pair> a = new ArrayList<Pair>();
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[i].length; j++){
                if(isOpen(i,j) == true || isDeadEnd(i,j) == true){
                    Pair p = new Pair(i,j);
                    a.add(p);
                    grid[i][j].SetState(Open);
                }
            }
        }
        return a;
    }
   

}
