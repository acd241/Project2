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

    public double highestProbabilityValue()
    {
        double highest = (double) Integer.MIN_VALUE;

        //if (MouseGrid1 != null)
        //{
            for (int i = 0; i < grid.length; i++)
            {
                for (int j = 0; j < grid[0].length; j++)
                {
                    if (highest < grid[i][j].getProbOfMouse())
                    {
                        highest = grid[i][j].getProbOfMouse();
                    }
                }
            }
        //}

        if (MouseGrid2 != null)
        {
            for (int i = 0; i < MouseGrid2.length; i++)
            {
                for (int j = 0; j < MouseGrid2[0].length; j++)
                {
                    if (highest < MouseGrid2[i][j])
                    {
                        highest = MouseGrid2[i][j];
                    }
                }
            }
        }

        return highest;
    }

    public double highestProbabilityValueMice()
    {
        double highest = (double) Integer.MIN_VALUE;

        for (int i = 0; i < MouseGrid1.length; i++)
        {
            for (int j = 0; j < MouseGrid1[0].length; j++)
            {
                if (highest < MouseGrid1[i][j])
                {
                    highest = MouseGrid1[i][j];
                }
            }
        }


        for (int i = 0; i < MouseGrid2.length; i++)
        {
            for (int j = 0; j < MouseGrid2[0].length; j++)
            {
                if (highest < MouseGrid2[i][j])
                {
                    highest = MouseGrid2[i][j];
                }
            }
        }
        

        return highest;
    }

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
        MouseGrid1 = new double [40][40];
        MouseGrid2 = new double [40][40];
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
        MouseGrid1 = new double [5][5];
        MouseGrid2 = new double [5][5];
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

    public Shiptest(boolean b){
        grid = new Cell[40][40];
        adjecencyGrid = new int [40][40];
        int x = random.nextInt(5);
        int y = random.nextInt(5);
        PopulateShip(grid);
        grid[x][y].SetState(Open);
        ExploreShip();
        IdentifyDeadEnds();
        AddExtraOpenCells();
        OpenCells = LabelAllOpenCells();
        IdentifyDeadEnds();
        int [][] g = SetGrid1("array1.csv");
        SwapGrids(g);
        OpenCells = LabelAllOpenCells();
        AdjustOpenCells();
        AdjGrid();
        AdjParList();
        ArrayList<LL> temp = AdjList1();
        AdjList = AdjList2(temp);
        StartingProbabilities();
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

    public void AdjustOpenCells(){
        totalOpenCells = 0;
        for(int i = 0; i<grid.length; i++){
            for (int j = 0; j<grid[i].length; j++){
                if(isOpen(i,j) || isBot(i,j)|| isDeadEnd(i, j) || isMouse(i, j)){
                    totalOpenCells+=1;
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

    public void InitializeMouseGrid1(){
        for(int i = 0; i< MouseGrid1.length; i++){
            for(int j = 0; j<MouseGrid1[i].length; j++){
                if(isOpen(i,j)|| isDeadEnd(i, j) || isMouse(i, j)){
                    MouseGrid1[i][j] = 1.0/(totalOpenCells-1);
                }
                else{
                    MouseGrid1[i][j]=0.0;
                }
            }
        }
    }
    public void InitializeMouseGrid2(){
        for(int i = 0; i< MouseGrid2.length; i++){
            for(int j = 0; j<MouseGrid2[i].length; j++){
                if(isOpen(i,j)|| isDeadEnd(i, j) || isMouse(i, j)){
                    MouseGrid2[i][j] = 1.0/(totalOpenCells-1);
                }
                else{
                    MouseGrid2[i][j]=0.0;
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

    public void PrintProbabilityMap(){
        for(int i = 0; i<grid.length; i++){
            for(int j = 0; j<grid[i].length; j++){
                System.out.print(grid[i][j].getProbOfMouse() + " ");
            }
            System.out.println();
        }
    }

    public void SwapGrids(int [][] g){
        if(g.length != grid.length){
            return;
        }
        else{
            for(int i = 0; i<g.length; i++){
                for(int j = 0; j<g[i].length; j++){
                    grid[i][j].SetState(g[i][j]);
                }
            }
        }
    }

    public int [][] SetGrid1(String filename){ //throws FileNotFoundException, IOException{
        int [][] g = new int [40][40];
        try{
            BufferedReader b = new BufferedReader(new FileReader(filename));
            String l = null;
            int row = 0;
            while((l =b.readLine()) != null){
                String newString = l.replaceAll(",", "");
                for(int z = 0; z<newString.length(); z++){
                    String s1 = newString.substring(z, z+1);
                    g[row][z] = Integer.parseInt(s1);
                }
                row++; 
            }
            b.close();
            return g;
        }catch(FileNotFoundException e){
            System.out.print(e);
        }
        catch (IOException e1) {
            System.out.print(e1);
        }
        return g;
    }

    public void WriteFileInt(boolean IsLayout){
        if(IsLayout){
            try (FileWriter csvWriter = new FileWriter("array1.csv")) {
                for (int i = 0; i < 40; i++) {
                    for (int j = 0; j < 40; j++) {
                        csvWriter.append(String.valueOf(grid[i][j].GetState()));
                        if (j < 39) {
                            csvWriter.append(",");
                        }
                    }
                    csvWriter.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            try (FileWriter csvWriter = new FileWriter("array2.csv")) {
                for (int i = 0; i < 40; i++) {
                    for (int j = 0; j < 40; j++) {
                        if(isBot(i,j)){
                            csvWriter.append(String.valueOf(1));
                            if (j < 39) {
                                csvWriter.append(",");
                            }
                        }
                        else{
                            csvWriter.append(String.valueOf(0));
                            if (j < 39) {
                                csvWriter.append(",");
                            }
                        }
                    }
                    csvWriter.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void WriteFileDouble(Cell [][] g){
        try (FileWriter csvWriter = new FileWriter("array3.csv")) {
            for (int i = 0; i < 40; i++) {
                for (int j = 0; j < 40; j++) {
                    csvWriter.append(String.valueOf(g[i][j].getProbOfMouse()));
                    if (j < 39) {
                        csvWriter.append(",");
                    }
                }
                csvWriter.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void WriteFilealpha(double alpha){
        try (FileWriter csvWriter = new FileWriter("array4.csv")) {
            for (int i = 0; i < 40; i++) {
                for (int j = 0; j < 40; j++) {
                    csvWriter.append(String.valueOf(alpha));
                    if (j < 39) {
                        csvWriter.append(",");
                    }
                }
                csvWriter.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
                else if(isBot(i,j)){

                }
            }
        }
        return a;
    }
   

}



/*
 * class Custom2DDataset(Dataset):
    def __init__(self, array1, array2, array3, array4, transform=None):
        self.array1 = array1
        self.array2 = array2
        self.array3 = array3
        self.array4 = array4
        self.transform = transform

    def __len__(self):
        return len(self.array1)

    def __getitem__(self, idx):
        sample = {
            'array1': torch.tensor(self.array1[idx], dtype=torch.float32),
            'array2': torch.tensor(self.array2[idx], dtype=torch.float32),
            'array3': torch.tensor(self.array3[idx], dtype=torch.float32),
            'array4': torch.tensor(self.array4[idx], dtype=torch.float32)
        }
        if self.transform:
            sample = self.transform(sample)
        return sample

# Example usage
import numpy as np

array1 = np.loadtxt('array1.csv', delimiter=',', dtype=int)
array2 = np.loadtxt('array2.csv', delimiter=',', dtype=int)
array3 = np.loadtxt('array3.csv', delimiter=',', dtype=float)
array4 = np.loadtxt('array4.csv', delimiter=',', dtype=float)

print(array1)
print(array1.shape)
print(array2)
print(array2.shape)
print(array3)
print(array3.shape)
print(array4)
print(array4.shape)


dataset = Custom2DDataset(array1, array2, array3, array4)
dataloader = DataLoader(dataset, batch_size=2, shuffle=True)

for batch in dataloader:
    print(batch)


class Custom2DDataset(Dataset):
    def __init__(self, arrays, labels, transform=None):
        self.arrays = arrays
        self.labels = labels
        self.transform = transform

    def __len__(self):
        return len(self.labels)

    def __getitem__(self, idx):
        sample = torch.tensor(self.arrays[idx], dtype=torch.float32)
        label = torch.tensor(self.labels[idx], dtype=torch.long)
        if self.transform:
            sample = self.transform(sample)
        return sample, label

# Example usage
import numpy as np


array1 = np.loadtxt('array1.csv', delimiter=',', dtype=int)
array2 = np.loadtxt('array2.csv', delimiter=',', dtype=int)
array3 = np.loadtxt('array3.csv', delimiter=',', dtype=float)
array4 = np.loadtxt('array4.csv', delimiter=',', dtype=float)

stacked_input = np.stack([array1, array2, array3, array4], axis=0)


stacked_inputs = np.stack([stacked_input] * 1000, axis=0)
output_actions = np.random.randint(0, 5, size=(1000,))


print(array1)
print(array1.shape)
print(array2)
print(array2.shape)
print(array3)
print(array3.shape)
print(array4)
print(array4.shape)


dataset = Custom2DDataset(stacked_inputs, output_actions)


class SimpleCNN(nn.Module):
    def __init__(self):
        super(SimpleCNN, self).__init__()

        # Define the CNN layers
        #self.conv1 = nn.Conv2d(4, 8, kernel_size=3, padding=1)  # 4 input channels
        #self.conv2 = nn.Conv2d(8, 16, kernel_size=3, padding=1)

        # Define the fully connected layers
        self.fc1 = nn.Linear(4 * 40 * 40, 512)
        self.fc2 = nn.Linear(512, 256)
        self.fc3 = nn.Linear(256, 128)
        self.fc4 = nn.Linear(128, 64)  # Adjust the input size based on the output size after conv layers
        self.fc5 = nn.Linear(64, 5)  # 5 output classes

    def forward(self, x):
        #x = F.relu(self.conv1(x))
        #x = F.max_pool2d(x, 2)
        #x = F.relu(self.conv2(x))
        #x = F.max_pool2d(x, 2)

        x = x.view(x.size(0), -1)  # Flatten the tensor
        x = F.relu(self.fc1(x))
        x = F.relu(self.fc2(x))
        x = F.relu(self.fc3(x))
        x = F.relu(self.fc4(x))
        x = self.fc5(x)
        return x

# Create the model
dataloader = DataLoader(dataset, batch_size=32, shuffle=True)
model = SimpleCNN()
criterion = nn.CrossEntropyLoss()
optimizer = optim.Adam(model.parameters(), lr=0.1)

 */