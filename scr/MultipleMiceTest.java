import java.util.ArrayList;

public class MultipleMiceTest {

    public static Pair Bot2TestStationaryMouse(double alpha){
        Shiptest t = new Shiptest( 1);
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
        t.PrintShip(t.grid);
        boolean hasBeeped = false;
        int count = 0;
        boolean Break = false;
        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
        Pair MousePredict = null;
        MousePredict = b.FindStartingCell();
        ArrayList<Integer> Path = null;
        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
        boolean alreadyFoundMouse = false;
        for(int i = 0; i<Path.size(); i++){
            count +=1;
            Pair next = b.GridLocationOfPath(Path.get(i));
            b.MoveBotStationary(next);
            //b.MoveBot(next);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                Break = true;
                alreadyFoundMouse =true;
                return new Pair(0,count);
            }
            //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
            //m1.MoveMouse1(nextMouseMove);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                Break = true;
                alreadyFoundMouse =true;
                return new Pair(0,count);
            }
            else{
                t.PrintShip(t.grid);
            }
        }
        if(!alreadyFoundMouse){
            int sense = 0;
            for(int i = 0; i<1000; i++){
                if(i%2 == 0) {
                    sense +=1;
                    hasBeeped = b.Sense(alpha, t.StartingMousePos);
                    b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                    //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                    t.DeInitilizeEdge(t.edgeTo);
                    t.DeInitilizevisit(t.visited);
                    Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                    MousePredict = null;
                    MousePredict = b.FindHighestProbCell();
                    Path = null;
                    Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                }
                else if(i%2 == 1){
                    Pair next = b.GridLocationOfPath(Path.get(0));
                    b.MoveBotStationary(next);
                    count +=1;
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        System.out.println();
                        t.PrintShip(t.grid);
                        Break = true;
                        alreadyFoundMouse = true;
                        return new Pair(sense, count);
                    }
                    else{
                        t.PrintShip(t.grid);
                    }
                }
            }
        }
        return new Pair(-1,-1);

    }

    public static Pair Bot2TestStationaryMouseAVG(double alpha){
        Shiptest t = new Shiptest( 1);
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
        //t.PrintShip(t.grid);
        boolean hasBeeped = false;
        int count = 0;
        boolean Break = false;
        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
        Pair MousePredict = null;
        MousePredict = b.FindStartingCell();
        ArrayList<Integer> Path = null;
        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
        boolean alreadyFoundMouse = false;
        for(int i = 0; i<Path.size(); i++){
            count +=1;
            Pair next = b.GridLocationOfPath(Path.get(i));
            b.MoveBotStationary(next);
            //b.MoveBot(next);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                //System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                Break = true;
                alreadyFoundMouse =true;
                return new Pair(0,count);
            }
            //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
            //m1.MoveMouse1(nextMouseMove);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                Break = true;
                alreadyFoundMouse =true;
                return new Pair(0,count);
            }
            else{
                //t.PrintShip(t.grid);
            }
        }
        if(!alreadyFoundMouse){
            int sense = 0;
            for(int i = 0; i<1000; i++){
                if(i%2 == 0) {
                    sense +=1;
                    hasBeeped = b.Sense(alpha, t.StartingMousePos);
                    b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                    //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                    t.DeInitilizeEdge(t.edgeTo);
                    t.DeInitilizevisit(t.visited);
                    Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                    MousePredict = null;
                    MousePredict = b.FindHighestProbCell();
                    Path = null;
                    Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                }
                else if(i%2 == 1){
                    Pair next = b.GridLocationOfPath(Path.get(0));
                    b.MoveBotStationary(next);
                    count +=1;
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        
                        //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        System.out.println();
                        t.PrintShip(t.grid);
                        Break = true;
                        alreadyFoundMouse = true;
                        return new Pair(sense, count);
                    }
                    else{
                        //t.PrintShip(t.grid);
                    }
                }
            }
        }
        return new Pair(-1,-1);

    }

    public static Pair Bot2TestMovingMouse(double alpha){
        Shiptest t = new Shiptest( 1);
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
        t.PrintShip(t.grid);
        boolean hasBeeped = false;
        int count = 0;
        boolean Break = false;
        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
        Pair MousePredict = null;
        MousePredict = b.FindStartingCell();
        ArrayList<Integer> Path = null;
        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
        boolean alreadyFoundMouse = false;
        for(int i = 0; i<Path.size(); i++){
            count +=1;
            Pair next = b.GridLocationOfPath(Path.get(i));
            //b.MoveBotStationary(next);
            b.MoveBot(next);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                Break = true;
                alreadyFoundMouse =true;
                return new Pair(0,count);
            }
            Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
            m1.MoveMouse1(nextMouseMove);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                Break = true;
                alreadyFoundMouse =true;
                return new Pair(0,count);
            }
            else{
                t.PrintShip(t.grid);
            }
        }
        if(!alreadyFoundMouse){
            int sense = 0;
            for(int i = 0; i<1000; i++){
                if(i%2 == 0) {
                    Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    m1.MoveMouse1(nextMouseMove);
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        t.PrintShip(t.grid);
                        Break = true;
                        alreadyFoundMouse =true;
                        return new Pair(sense,count);
                    }
                    sense +=1;
                    hasBeeped = b.Sense(alpha, t.StartingMousePos);
                    //b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                    b.UpdateProbabilitiesMoving(hasBeeped, alpha, 1);
                    t.DeInitilizeEdge(t.edgeTo);
                    t.DeInitilizevisit(t.visited);
                    Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                    MousePredict = null;
                    MousePredict = b.FindHighestProbCell();
                    Path = null;
                    Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                    
                    t.PrintShip(t.grid);
                    
                }
                else if(i%2 == 1){
                    Pair next = b.GridLocationOfPath(Path.get(0));
                    //b.MoveBotStationary(next);
                    b.MoveBot(next);
                    count +=1;
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        System.out.println();
                        t.PrintShip(t.grid);
                        Break = true;
                        alreadyFoundMouse = true;
                        return new Pair(sense, count);
                    }
                    Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    m1.MoveMouse1(nextMouseMove);
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        t.PrintShip(t.grid);
                        Break = true;
                        alreadyFoundMouse =true;
                        return new Pair(sense,count);
                    }
                    else{
                        t.PrintShip(t.grid);
                    }
                }
            }
        }
        return new Pair(-1,-1);

    }

    public static Pair Bot2TestMovingMouseAVG(double alpha){
        Shiptest t = new Shiptest( 1);
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
        //t.PrintShip(t.grid);
        boolean hasBeeped = false;
        int count = 0;
        boolean Break = false;
        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
        Pair MousePredict = null;
        MousePredict = b.FindStartingCell();
        ArrayList<Integer> Path = null;
        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
        boolean alreadyFoundMouse = false;
        for(int i = 0; i<Path.size(); i++){
            count +=1;
            Pair next = b.GridLocationOfPath(Path.get(i));
            //b.MoveBotStationary(next);
            b.MoveBot(next);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                //System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                Break = true;
                alreadyFoundMouse =true;
                return new Pair(0,count);
            }
            Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
            m1.MoveMouse1(nextMouseMove);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                //System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                Break = true;
                alreadyFoundMouse =true;
                return new Pair(0,count);
            }
            else{
                //t.PrintShip(t.grid);
            }
        }
        if(!alreadyFoundMouse){
            int sense = 0;
            for(int i = 0; i<1000; i++){
                if(i%2 == 0) {
                    Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    m1.MoveMouse1(nextMouseMove);
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        //t.PrintShip(t.grid);
                        Break = true;
                        alreadyFoundMouse =true;
                        return new Pair(sense,count);
                    }
                    sense +=1;
                    hasBeeped = b.Sense(alpha, t.StartingMousePos);
                    //b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                    b.UpdateProbabilitiesMoving(hasBeeped, alpha, 1);
                    t.DeInitilizeEdge(t.edgeTo);
                    t.DeInitilizevisit(t.visited);
                    Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                    MousePredict = null;
                    MousePredict = b.FindHighestProbCell();
                    Path = null;
                    Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                    
                    //t.PrintShip(t.grid);
                    
                }
                else if(i%2 == 1){
                    Pair next = b.GridLocationOfPath(Path.get(0));
                    //b.MoveBotStationary(next);
                    b.MoveBot(next);
                    count +=1;
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        
                        //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        System.out.println();
                        //t.PrintShip(t.grid);
                        Break = true;
                        alreadyFoundMouse = true;
                        return new Pair(sense, count);
                    }
                    Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    m1.MoveMouse1(nextMouseMove);
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        //t.PrintShip(t.grid);
                        Break = true;
                        alreadyFoundMouse =true;
                        return new Pair(sense,count);
                    }
                    else{
                        //t.PrintShip(t.grid);
                    }
                }
            }
        }
        return new Pair(-1,-1);

    }

    public static Pair Bot2TestStationaryMice(double alpha){
        Shiptest t = new Shiptest(2);
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
        Mouse m2 = new Mouse(t);
        t.InitializeMouseGrid1();
        t.InitializeMouseGrid2();
        t.PrintShip(t.grid);
        boolean hasBeeped = false;
        int count = 0;
        boolean Break = false;
        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
        Pair MousePredict = null;
        MousePredict = b.FindStartingCell();
        ArrayList<Integer> Path = null;
        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
        boolean alreadyFoundMouse = false;
        boolean FoundMouse1 = false;
        int MouseFound = 0;
        boolean FoundMouse2 = false;
        for(int i = 0; i<Path.size(); i++){
            count +=1;
            Pair next = b.GridLocationOfPath(Path.get(i));
            if(MouseFound > 0){
                b.MoveBotStationary(next);
            }
            else if(MouseFound == 0){
                b.UpdateMoveStationaryMice(next);
            }
            //b.MoveBot(next);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                System.out.println();
                if(MouseFound == 0){
                    if(b.GetBotPos().isSame(m1.GetMousePos())){
                        m1.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        b.ReInitializeProbabilities();
                        t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        FoundMouse1 =true;
                        MouseFound +=1;
                        continue;
                    }
                    else if(b.GetBotPos().isSame(m2.GetMousePos())){
                        m2.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        b.ReInitializeProbabilities();
                        t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        FoundMouse2 =true;
                        MouseFound +=1;
                        continue;
                    }
                }
                else if(MouseFound == 1){
                    if(FoundMouse1){
                        m2.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse1 =true;
                        MouseFound +=1;
                        return new Pair(0, count);
                    }
                    else if(FoundMouse2){
                        m1.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse1 =true;
                        MouseFound +=1;
                        return new Pair(0, count);
                    }
                    break;
                }
                
            }
            else{
                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                t.PrintShip(t.grid);
            }
        }
        if(MouseFound<=1){
            int sense = 0;
            for(int i = 0; i<10000; i++){
                if(i%2 == 0) {
                    sense+=1;
                    if(MouseFound == 0){
                        hasBeeped = b.Sense(alpha, m1.GetMousePos());
                        if(!hasBeeped){
                            hasBeeped = b.Sense(alpha, m2.GetMousePos());
                        }
                    }
                    else if(MouseFound == 1){
                        if(FoundMouse1){
                            hasBeeped = b.Sense(alpha, m2.GetMousePos());
                        }
                        else if(FoundMouse2){
                            hasBeeped = b.Sense(alpha, m1.GetMousePos());
                        }
                    }
                    if(MouseFound == 1){
                        b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                        //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                    }
                    else if(MouseFound == 0){
                        double [][] temp = null; 
                        temp = b.Replicate();
                        b.UpdateMouse1Grid(hasBeeped, alpha);
                        b.UpdateMouse2Grid(hasBeeped, alpha, temp);
                        b.Normalization(t.MouseGrid1);
                        b.Normalization(t.MouseGrid2);
                        //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 2);
                    }
                    t.DeInitilizeEdge(t.edgeTo);
                    t.DeInitilizevisit(t.visited);
                    Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                    MousePredict = null;
                    if(MouseFound == 0){
                        MousePredict = b.FindHighestProbCellMice();
                    }
                    else if (MouseFound == 1){
                        MousePredict = b.FindHighestProbCell();
                    }
                    Path = null;
                    Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                }
                else if(i%2 == 1){
                    Pair next = b.GridLocationOfPath(Path.get(0));
                    if(MouseFound > 0){
                        b.MoveBotStationary(next);
                    }
                    else if(MouseFound == 0){
                        b.UpdateMoveStationaryMice(next);
                    }
                    count +=1;
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        System.out.print("BOT FOUND THE MOUSE. Sense:" + sense + " Movement: " + count);
                        System.out.println();
                        if(MouseFound == 0){
                            if(b.GetBotPos().isSame(m1.GetMousePos())){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                b.ReInitializeProbabilities();
                                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                FoundMouse1 =true;
                                MouseFound +=1;
                            }
                            else if(b.GetBotPos().isSame(m2.GetMousePos())){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                b.ReInitializeProbabilities();
                                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                FoundMouse2 =true;
                                MouseFound +=1;
                            }
                        }
                        else if(MouseFound == 1){
                            if(FoundMouse1){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                Break =true;
                                return new Pair(sense, count);
                            }
                            else if(FoundMouse2){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                Break = true;
                                return new Pair(sense, count);
                            }
                            break;
                        }
                        
                    }
                    else{
                        t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.PrintShip(t.grid);
                    }
                }
            }
        }
        return new Pair(-1,-1);

    }


    public static Pair Bot2TestStationaryMiceAVG(double alpha){
        Shiptest t = new Shiptest(2);
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
        Mouse m2 = new Mouse(t);
        t.InitializeMouseGrid1();
        t.InitializeMouseGrid2();
        //t.PrintShip(t.grid);
        boolean hasBeeped = false;
        int count = 0;
        boolean Break = false;
        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
        Pair MousePredict = null;
        MousePredict = b.FindStartingCell();
        ArrayList<Integer> Path = null;
        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
        boolean alreadyFoundMouse = false;
        boolean FoundMouse1 = false;
        int MouseFound = 0;
        boolean FoundMouse2 = false;
        for(int i = 0; i<Path.size(); i++){
            count +=1;
            Pair next = b.GridLocationOfPath(Path.get(i));
            if(MouseFound > 0){
                b.MoveBotStationary(next);
            }
            else if(MouseFound == 0){
                b.UpdateMoveStationaryMice(next);
            }
            //b.MoveBot(next);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                //System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                System.out.println();
                if(MouseFound == 0){
                    if(b.GetBotPos().isSame(m1.GetMousePos())){
                        m1.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        b.ReInitializeProbabilities();
                        t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        FoundMouse1 =true;
                        MouseFound +=1;
                        continue;
                    }
                    else if(b.GetBotPos().isSame(m2.GetMousePos())){
                        m2.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        b.ReInitializeProbabilities();
                        t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        FoundMouse2 =true;
                        MouseFound +=1;
                        continue;
                    }
                }
                else if(MouseFound == 1){
                    if(FoundMouse1){
                        m2.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse1 =true;
                        MouseFound +=1;
                        return new Pair(0, count);
                    }
                    else if(FoundMouse2){
                        m1.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse1 =true;
                        MouseFound +=1;
                        return new Pair(0, count);
                    }
                    break;
                }
                
            }
            else{
                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                //t.PrintShip(t.grid);
            }
        }
        if(MouseFound<=1){
            int sense = 0;
            for(int i = 0; i<10000; i++){
                if(i%2 == 0) {
                    sense+=1;
                    if(MouseFound == 0){
                        hasBeeped = b.Sense(alpha, m1.GetMousePos());
                        if(!hasBeeped){
                            hasBeeped = b.Sense(alpha, m2.GetMousePos());
                        }
                    }
                    else if(MouseFound == 1){
                        if(FoundMouse1){
                            hasBeeped = b.Sense(alpha, m2.GetMousePos());
                        }
                        else if(FoundMouse2){
                            hasBeeped = b.Sense(alpha, m1.GetMousePos());
                        }
                    }
                    if(MouseFound == 1){
                        b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                        //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                    }
                    else if(MouseFound == 0){
                        double [][] temp = null; 
                        temp = b.Replicate();
                        b.UpdateMouse1Grid(hasBeeped, alpha);
                        b.UpdateMouse2Grid(hasBeeped, alpha, temp);
                        b.Normalization(t.MouseGrid1);
                        b.Normalization(t.MouseGrid2);
                        //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 2);
                    }
                    t.DeInitilizeEdge(t.edgeTo);
                    t.DeInitilizevisit(t.visited);
                    Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                    MousePredict = null;
                    if(MouseFound == 0){
                        MousePredict = b.FindHighestProbCellMice();
                    }
                    else if (MouseFound == 1){
                        MousePredict = b.FindHighestProbCell();
                    }
                    Path = null;
                    Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                }
                else if(i%2 == 1){
                    Pair next = b.GridLocationOfPath(Path.get(0));
                    if(MouseFound > 0){
                        b.MoveBotStationary(next);
                    }
                    else if(MouseFound == 0){
                        b.UpdateMoveStationaryMice(next);
                    }
                    count +=1;
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        //System.out.print("BOT FOUND THE MOUSE. Sense:" + sense + " Movement: " + count);
                        System.out.println();
                        if(MouseFound == 0){
                            if(b.GetBotPos().isSame(m1.GetMousePos())){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                b.ReInitializeProbabilities();
                                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                FoundMouse1 =true;
                                MouseFound +=1;
                            }
                            else if(b.GetBotPos().isSame(m2.GetMousePos())){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                b.ReInitializeProbabilities();
                                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                FoundMouse2 =true;
                                MouseFound +=1;
                            }
                        }
                        else if(MouseFound == 1){
                            if(FoundMouse1){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                Break =true;
                                return new Pair(sense, count);
                            }
                            else if(FoundMouse2){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                Break = true;
                                return new Pair(sense, count);
                            }
                            break;
                        }
                        
                    }
                    else{
                        t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        //t.PrintShip(t.grid);
                    }
                }
            }
        }
        return new Pair(-1,-1);

    }

    public static Pair Bot2TestMovingMice(double alpha){
        Shiptest t = new Shiptest(5,  2);
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
        Mouse m2 = new Mouse(t);
        t.InitializeMouseGrid1();
        t.InitializeMouseGrid2();

        t.PrintShip(t.grid);
        boolean hasBeeped = false;
        int count = 0;
        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
        Pair MousePredict = null;
        MousePredict = b.FindStartingCellTest();
        ArrayList<Integer> Path = null;
        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
        boolean FoundMouse1 = false;
        int MouseFound = 0;
        boolean FoundMouse2 = false;
        boolean Break = false;
        for(int i = 0; i<Path.size(); i++){
            count +=1;
            Pair next = b.GridLocationOfPath(Path.get(i));
            b.MoveBot(next);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                System.out.println();
                if(MouseFound == 0){
                    if(b.GetBotPos().isSame(m1.GetMousePos())){
                        m1.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        b.ReInitializeProbabilities();
                        t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        FoundMouse1 =true;
                        MouseFound +=1;
                        continue;
                    }
                    else if(b.GetBotPos().isSame(m2.GetMousePos())){
                        m2.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        b.ReInitializeProbabilities();
                        t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        FoundMouse2 =true;
                        MouseFound +=1;
                        continue;
                    }
                }
                else if(MouseFound == 1){
                    if(FoundMouse1){
                        m2.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse1 =true;
                        MouseFound +=1;
                        return new Pair(0, count);
                    }
                    else if(FoundMouse2){
                        m1.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse1 =true;
                        MouseFound +=1;
                        return new Pair(0, count);
                    }
                    break;
                }
                
            }
            
            if(!FoundMouse1){
                Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                m1.MoveMouse1(nextMouseMove);
            }
            if(!FoundMouse2){
                Pair nextMouseMove2 = m2.PickRandomNeighbor(m2.GetMousePos().getKey(), m2.GetMousePos().getValue());
                m2.MoveMouse2(nextMouseMove2);
                
            }
                 
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                System.out.println();
                if(MouseFound == 0){
                    if(b.GetBotPos().isSame(m1.GetMousePos())){
                        m1.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        b.ReInitializeProbabilities();
                        t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        FoundMouse1 =true;
                        MouseFound +=1;
                        continue;
                    }
                    else if(b.GetBotPos().isSame(m2.GetMousePos())){
                        m2.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        b.ReInitializeProbabilities();
                        t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        FoundMouse2 =true;
                        MouseFound +=1;
                        continue;
                    }
                }
                else if(MouseFound == 1){
                    if(FoundMouse1){
                        m2.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse1 =true;
                        MouseFound +=1;
                        return new Pair(0, count);
                    }
                    else if(FoundMouse2){
                        m1.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse1 =true;
                        MouseFound +=1;
                        return new Pair(0, count);
                    }
                }
            }
                
            else{
                //t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                //t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                t.PrintShip(t.grid);
            }
        }
        if(MouseFound<=1){
            int sense = 0;
            for(int i = 0; i<1000; i++){
                if(i%2 == 0) {
                    if(!FoundMouse1){
                        Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                        m1.MoveMouse1(nextMouseMove);
                    }
                    if(!FoundMouse2){
                        Pair nextMouseMove2 = m2.PickRandomNeighbor(m2.GetMousePos().getKey(), m2.GetMousePos().getValue());
                        m2.MoveMouse2(nextMouseMove2);
                        
                    }
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + "Movement: " + count);
                        System.out.println();
                        if(MouseFound == 0){
                            if(b.GetBotPos().isSame(m1.GetMousePos())){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                b.ReInitializeProbabilities();
                                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                FoundMouse1 =true;
                                MouseFound +=1;
                                continue;
                            }
                            else if(b.GetBotPos().isSame(m2.GetMousePos())){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                b.ReInitializeProbabilities();
                                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                FoundMouse2 =true;
                                MouseFound +=1;
                                continue;
                            }
                        }
                        else if(MouseFound == 1){
                            if(FoundMouse1){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                return new Pair(sense, count);
                            }
                            else if(FoundMouse2){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                return new Pair(sense, count);
                            }
                        }
                    }
                    sense+=1;
                    if(MouseFound == 0){
                        hasBeeped = b.Sense(alpha, m1.GetMousePos());
                        if(!hasBeeped){
                            hasBeeped = b.Sense(alpha, m2.GetMousePos());
                        }
                    }
                    else if(MouseFound == 1){
                        if(FoundMouse1){
                            hasBeeped = b.Sense(alpha, m2.GetMousePos());
                        }
                        else if(FoundMouse2){
                            hasBeeped = b.Sense(alpha, m1.GetMousePos());
                        }
                    }
                    if(MouseFound == 1){
                        //b.UpdateProbabilitiesStationary(hasBeeped, 0.5, 1, b.GetCellsTraversed());
                        b.UpdateProbabilitiesMoving(hasBeeped, alpha, 1);
                    }
                    else if(MouseFound == 0){
                        double [][] temp = null; 
                        temp = b.Replicate();
                        b.UpdateMouse1GridMoving(hasBeeped, alpha);
                        b.UpdateMouse2GridMoving(hasBeeped, alpha, temp);
                        b.Normalization(t.MouseGrid1);
                        b.Normalization(t.MouseGrid2);
                        //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 2);
                    }
                    t.DeInitilizeEdge(t.edgeTo);
                    t.DeInitilizevisit(t.visited);
                    Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                    MousePredict = null;
                    if(MouseFound == 0){
                        MousePredict = b.FindHighestProbCellMice();
                    }
                    else if (MouseFound == 1){
                        MousePredict = b.FindHighestProbCell();
                    }
                    Path = null;
                    Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                    t.PrintShip(t.grid);
                    
                }
                else if(i%2 == 1){
                    Pair next = b.GridLocationOfPath(Path.get(0));
                    //b.MoveBotStationary(next);
                    b.MoveBot(next);
                    count +=1;
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        System.out.print("BOT FOUND THE MOUSE. Sense:" + sense + " Movement: " + count);
                        System.out.println();
                        if(MouseFound == 0){
                            if(b.GetBotPos().isSame(m1.GetMousePos())){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                b.ReInitializeProbabilities();
                                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                FoundMouse1 =true;
                                MouseFound +=1;
                            }
                            else if(b.GetBotPos().isSame(m2.GetMousePos())){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                b.ReInitializeProbabilities();
                                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                FoundMouse2 =true;
                                MouseFound +=1;
                            }
                        }
                        else if(MouseFound == 1){
                            if(FoundMouse1){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                Break =true;
                                return new Pair(sense, count);
                            }
                            else if(FoundMouse2){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                Break = true;
                                return new Pair(sense, count);
                            }
                            break;
                        }
                        
                    }
                    if(!FoundMouse1){
                        Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                        m1.MoveMouse1(nextMouseMove);
                    }
                    if(!FoundMouse2){
                        Pair nextMouseMove2 = m2.PickRandomNeighbor(m2.GetMousePos().getKey(), m2.GetMousePos().getValue());
                        m2.MoveMouse2(nextMouseMove2);
                        
                    }
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense+ " Movement: " + count);
                        System.out.println();
                        if(MouseFound == 0){
                            if(b.GetBotPos().isSame(m1.GetMousePos())){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                b.ReInitializeProbabilities();
                                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                FoundMouse1 =true;
                                MouseFound +=1;
                                continue;
                            }
                            else if(b.GetBotPos().isSame(m2.GetMousePos())){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                b.ReInitializeProbabilities();
                                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                FoundMouse2 =true;
                                MouseFound +=1;
                                continue;
                            }
                        }
                        else if(MouseFound == 1){
                            if(FoundMouse1){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                return new Pair(sense, count);
                            }
                            else if(FoundMouse2){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                return new Pair(sense, count);
                            }
                        }
                    }
                    else{
                        t.PrintShip(t.grid);
                    }
                }
            }
        }
        return new Pair(-1,-1);

    }

    public static Pair Bot2TestMovingMiceAVG(double alpha){
        Shiptest t = new Shiptest(5,  2);
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
        Mouse m2 = new Mouse(t);
        t.InitializeMouseGrid1();
        t.InitializeMouseGrid2();

        //t.PrintShip(t.grid);
        boolean hasBeeped = false;
        int count = 0;
        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
        Pair MousePredict = null;
        MousePredict = b.FindStartingCellTest();
        ArrayList<Integer> Path = null;
        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
        boolean FoundMouse1 = false;
        int MouseFound = 0;
        boolean FoundMouse2 = false;
        boolean Break = false;
        for(int i = 0; i<Path.size(); i++){
            count +=1;
            Pair next = b.GridLocationOfPath(Path.get(i));
            b.MoveBot(next);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                //System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                //System.out.println();
                if(MouseFound == 0){
                    if(b.GetBotPos().isSame(m1.GetMousePos())){
                        m1.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        b.ReInitializeProbabilities();
                        t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        FoundMouse1 =true;
                        MouseFound +=1;
                        continue;
                    }
                    else if(b.GetBotPos().isSame(m2.GetMousePos())){
                        m2.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        b.ReInitializeProbabilities();
                        t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        FoundMouse2 =true;
                        MouseFound +=1;
                        continue;
                    }
                }
                else if(MouseFound == 1){
                    if(FoundMouse1){
                        m2.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse1 =true;
                        MouseFound +=1;
                        return new Pair(0, count);
                    }
                    else if(FoundMouse2){
                        m1.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse1 =true;
                        MouseFound +=1;
                        return new Pair(0, count);
                    }
                    break;
                }
                
            }
            
            if(!FoundMouse1){
                Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                m1.MoveMouse1(nextMouseMove);
            }
            if(!FoundMouse2){
                Pair nextMouseMove2 = m2.PickRandomNeighbor(m2.GetMousePos().getKey(), m2.GetMousePos().getValue());
                m2.MoveMouse2(nextMouseMove2);
                
            }
                 
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                //System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                //System.out.println();
                if(MouseFound == 0){
                    if(b.GetBotPos().isSame(m1.GetMousePos())){
                        m1.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        b.ReInitializeProbabilities();
                        t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        FoundMouse1 =true;
                        MouseFound +=1;
                        continue;
                    }
                    else if(b.GetBotPos().isSame(m2.GetMousePos())){
                        m2.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        b.ReInitializeProbabilities();
                        t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        FoundMouse2 =true;
                        MouseFound +=1;
                        continue;
                    }
                }
                else if(MouseFound == 1){
                    if(FoundMouse1){
                        m2.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse1 =true;
                        MouseFound +=1;
                        return new Pair(0, count);
                    }
                    else if(FoundMouse2){
                        m1.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        //t.StartingProbabilities();
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse1 =true;
                        MouseFound +=1;
                        return new Pair(0, count);
                    }
                }
            }
                
            else{
                //t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                //t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                //t.PrintShip(t.grid);
            }
        }
        if(MouseFound<=1){
            int sense = 0;
            for(int i = 0; i<1000; i++){
                if(i%2 == 0) {
                    if(!FoundMouse1){
                        Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                        m1.MoveMouse1(nextMouseMove);
                    }
                    if(!FoundMouse2){
                        Pair nextMouseMove2 = m2.PickRandomNeighbor(m2.GetMousePos().getKey(), m2.GetMousePos().getValue());
                        m2.MoveMouse2(nextMouseMove2);
                        
                    }
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + "Movement: " + count);
                        //System.out.println();
                        if(MouseFound == 0){
                            if(b.GetBotPos().isSame(m1.GetMousePos())){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                b.ReInitializeProbabilities();
                                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                FoundMouse1 =true;
                                MouseFound +=1;
                                continue;
                            }
                            else if(b.GetBotPos().isSame(m2.GetMousePos())){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                b.ReInitializeProbabilities();
                                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                FoundMouse2 =true;
                                MouseFound +=1;
                                continue;
                            }
                        }
                        else if(MouseFound == 1){
                            if(FoundMouse1){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                return new Pair(sense, count);
                            }
                            else if(FoundMouse2){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                return new Pair(sense, count);
                            }
                        }
                    }
                    sense+=1;
                    if(MouseFound == 0){
                        hasBeeped = b.Sense(alpha, m1.GetMousePos());
                        if(!hasBeeped){
                            hasBeeped = b.Sense(alpha, m2.GetMousePos());
                        }
                    }
                    else if(MouseFound == 1){
                        if(FoundMouse1){
                            hasBeeped = b.Sense(alpha, m2.GetMousePos());
                        }
                        else if(FoundMouse2){
                            hasBeeped = b.Sense(alpha, m1.GetMousePos());
                        }
                    }
                    if(MouseFound == 1){
                        //b.UpdateProbabilitiesStationary(hasBeeped, 0.5, 1, b.GetCellsTraversed());
                        b.UpdateProbabilitiesMoving(hasBeeped, alpha, 1);
                    }
                    else if(MouseFound == 0){
                        double [][] temp = null; 
                        temp = b.Replicate();
                        b.UpdateMouse1GridMoving(hasBeeped, alpha);
                        b.UpdateMouse2GridMoving(hasBeeped, alpha, temp);
                        b.Normalization(t.MouseGrid1);
                        b.Normalization(t.MouseGrid2);
                        //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 2);
                    }
                    t.DeInitilizeEdge(t.edgeTo);
                    t.DeInitilizevisit(t.visited);
                    Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                    MousePredict = null;
                    if(MouseFound == 0){
                        MousePredict = b.FindHighestProbCellMice();
                    }
                    else if (MouseFound == 1){
                        MousePredict = b.FindHighestProbCell();
                    }
                    Path = null;
                    Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                    //t.PrintShip(t.grid);
                    
                }
                else if(i%2 == 1){
                    Pair next = b.GridLocationOfPath(Path.get(0));
                    //b.MoveBotStationary(next);
                    b.MoveBot(next);
                    count +=1;
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        //System.out.print("BOT FOUND THE MOUSE. Sense:" + sense + " Movement: " + count);
                        //System.out.println();
                        if(MouseFound == 0){
                            if(b.GetBotPos().isSame(m1.GetMousePos())){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                b.ReInitializeProbabilities();
                                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                FoundMouse1 =true;
                                MouseFound +=1;
                            }
                            else if(b.GetBotPos().isSame(m2.GetMousePos())){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                b.ReInitializeProbabilities();
                                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                FoundMouse2 =true;
                                MouseFound +=1;
                            }
                        }
                        else if(MouseFound == 1){
                            if(FoundMouse1){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                Break =true;
                                return new Pair(sense, count);
                            }
                            else if(FoundMouse2){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                Break = true;
                                return new Pair(sense, count);
                            }
                            break;
                        }
                        
                    }
                    if(!FoundMouse1){
                        Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                        m1.MoveMouse1(nextMouseMove);
                    }
                    if(!FoundMouse2){
                        Pair nextMouseMove2 = m2.PickRandomNeighbor(m2.GetMousePos().getKey(), m2.GetMousePos().getValue());
                        m2.MoveMouse2(nextMouseMove2);
                        
                    }
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense+ " Movement: " + count);
                        //System.out.println();
                        if(MouseFound == 0){
                            if(b.GetBotPos().isSame(m1.GetMousePos())){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                b.ReInitializeProbabilities();
                                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                FoundMouse1 =true;
                                MouseFound +=1;
                                continue;
                            }
                            else if(b.GetBotPos().isSame(m2.GetMousePos())){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                b.ReInitializeProbabilities();
                                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                                FoundMouse2 =true;
                                MouseFound +=1;
                                continue;
                            }
                        }
                        else if(MouseFound == 1){
                            if(FoundMouse1){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                return new Pair(sense, count);
                            }
                            else if(FoundMouse2){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                //t.StartingProbabilities();
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                return new Pair(sense, count);
                            }
                        }
                    }
                    else{
                        //t.PrintShip(t.grid);
                    }
                }
            }
        }
        return new Pair(-1,-1);

    }




    public static void main(String args[]){
        //INDIVIDUAL TEST SENSE AND MOVEMENT VALUES
        
        //BOT2 STATIONARY MOUSE TEST VALUES
        /* 
        Pair SenseMovement = Bot2TestStationaryMouse(0.2);
        System.out.println();
        System.out.print("Total Sensing and Movement values. Sense: " + SenseMovement.getKey() + " Movement: " +SenseMovement.getValue());
        */

        //BOT2 MOVING MOUSE TEST VALUES
        //Pair SenseMovement2 = Bot2TestMovingMouse(0.2);
        //System.out.println();
        //System.out.print("Total Sensing and Movement values. Sense: " + SenseMovement2.getKey() + " Movement: " +SenseMovement2.getValue());
        
        //BOT2 MOVING MICE TEST VALUES
        /* 
        Pair SMMoving = Bot2TestMovingMice(0.2);
        System.out.println();
        System.out.print("Total Sensing and Movement values. Sense: " + SMMoving.getKey() + " Movement: " +SMMoving.getValue());
        */

        //BOT2 STATIONARY MICE TEST VALUES
        /* 
        Pair SenseMovement = Bot2TestStationaryMice(0.2);
        System.out.println();
        System.out.print("Total Sensing and Movement values. Sense: " + SenseMovement.getKey() + " Movement: " +SenseMovement.getValue());
        */

        //AVERAGE TEST CASES SENSE AND MOVEMENT VALUES 

        //BOT2 STATIONARY MOUSE AVG TEST VALUES

        /* 
        int sense1 = 0;
        int count1 = 0;
        int skip1 = 0;
        for(int i = 0; i< 50; i++){
            Pair SenseMovement1 = Bot2TestStationaryMouseAVG(0.2);
            if(SenseMovement1.getKey() > 1000 || SenseMovement1.getValue()> 1000){
                skip1 +=1;
                continue;
            }
            sense1 += SenseMovement1.getKey();
            count1 += SenseMovement1.getValue();
        }
        double AvgSense1 = (sense1 *1.0)/(50 - skip1);
        double AvgMovement1 = (count1 * 1.0)/ (50-skip1);
        System.out.print("Bot2 Stationary Mouse Average Sense: " + AvgSense1 + " Average Movement: " + AvgMovement1);
        */

        //BOT2 MOVING MOUSE AVG TEST VALUES

        /* 
        int sense2 = 0;
        int count2 = 0;
        int skip2 = 0;
        for(int i = 0; i< 50; i++){
            Pair SenseMovement2 = Bot2TestMovingMouseAVG(0.2);
            if(SenseMovement2.getKey() > 1000 || SenseMovement2.getValue()> 1000){
                skip2 +=1;
                continue;
            }
            sense2+= SenseMovement2.getKey();
            count2 += SenseMovement2.getValue();
        }
        double AvgSense2 = (sense2 *1.0)/(50 - skip2);
        double AvgMovement2 = (count2 * 1.0)/ (50-skip2);
        System.out.print("Bot2 Moving Mouse Average Sense: " + AvgSense2 + " Average Movement: " + AvgMovement2);
        */

        //BOT2 STATIONARY MICE AVG TEST VALUES

        /* 
        int sense3 = 0;
        int count3 = 0;
        int skip3 = 0;
        for(int i = 0; i< 50; i++){
            Pair SenseMovement3 = Bot2TestStationaryMiceAVG(0.2);
            if(SenseMovement3.getKey() > 1000 || SenseMovement3.getValue()> 1000){
                skip3 +=1;
                continue;
            }
            sense3 += SenseMovement3.getKey();
            count3 += SenseMovement3.getValue();
        }
        double AvgSense3 = (sense3 *1.0)/(50 - skip3);
        double AvgMovement3 = (count3 * 1.0)/ (50-skip3);
        System.out.print("Bot2 Stationary Mice Average Sense: " + AvgSense3 + " Average Movement: " + AvgMovement3);
        */

        //BOT2 MOVING MICE AVG TEST VALUES
        /* 
        int sense4 = 0;
        int count4 = 0;
        int skip4 = 0;
        for(int i = 0; i< 50; i++){
            Pair SenseMovement4 = Bot2TestMovingMiceAVG(0.2);
            if(SenseMovement4.getKey() > 1000 || SenseMovement4.getValue()> 1000){
                skip4 +=1;
                continue;
            }
            sense4 += SenseMovement4.getKey();
            count4 += SenseMovement4.getValue();
        }
        double AvgSense4 = (sense4 *1.0)/(50 - skip4);
        double AvgMovement4 = (count4 * 1.0)/ (50-skip4);
        System.out.print("Bot2 Moving Mice Average Sense: " + AvgSense4 + " Average Movement: " + AvgMovement4);
        */


        /* 
        Shiptest t = new Shiptest(5, 2);
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
        Mouse m2 = new Mouse(t);
        t.PrintShip(t.grid);
        boolean hasBeeped = false;
        int count = 0;
        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
        Pair MousePredict = null;
        MousePredict = b.FindStartingCellTest();
        ArrayList<Integer> Path = null;
        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
        boolean FoundMouse1 = false;
        int MouseFound = 0;
        boolean FoundMouse2 = false;
        for(int i = 0; i<Path.size(); i++){
            count +=1;
            Pair next = b.GridLocationOfPath(Path.get(i));
            b.MoveBotStationary(next);
            //b.MoveBot(next);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                if(MouseFound == 0){
                    if(b.GetBotPos().isSame(m1.GetMousePos())){
                        m1.SetMousePos(null);
                        FoundMouse1 =true;
                        MouseFound +=1;
                    }
                    else if(b.GetBotPos().isSame(m2.GetMousePos())){
                        m2.SetMousePos(null);
                        FoundMouse2 =true;
                        MouseFound +=1;
                    }
                }
                else if(MouseFound == 1){
                    if(FoundMouse1){
                        m2.SetMousePos(null);
                        FoundMouse1 =true;
                        MouseFound +=1;
                    }
                    else if(FoundMouse2){
                        m1.SetMousePos(null);
                        FoundMouse1 =true;
                        MouseFound +=1;
                    }
                    break;
                }
                
            }
            //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
            //m1.MoveMouse1(nextMouseMove);
            
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                if(MouseFound == 0){
                    if(b.GetBotPos().isSame(m1.GetMousePos())){
                        m1.SetMousePos(null);
                        FoundMouse1 =true;
                        MouseFound +=1;
                    }
                    else if(b.GetBotPos().isSame(m2.GetMousePos())){
                        m2.SetMousePos(null);
                        FoundMouse2 =true;
                        MouseFound +=1;
                    }
                }
                else if(MouseFound == 1){
                    if(b.GetBotPos().isSame(m1.GetMousePos())){
                        m1.SetMousePos(null);
                        FoundMouse1 =true;
                        MouseFound +=1;
                    }
                    else if(b.GetBotPos().isSame(m2.GetMousePos())){
                        m2.SetMousePos(null);
                        FoundMouse2 =true;
                        MouseFound +=1;
                    }
                    break;
                }
            }
                
            else{
                t.PrintShip(t.grid);
            }
        }
        if(MouseFound<=1){
            boolean Break = false;
            int sense = 0;
            while(!FoundMouse1 || !FoundMouse2){
                t.PrintShip(t.grid);
                sense+=1;
                hasBeeped = b.Sense(0.5, t.StartingMousePos);
                if(MouseFound == 1){
                    b.UpdateProbabilitiesStationary(hasBeeped, 0.5, 1, b.GetCellsTraversed());
                    //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                }
                else if(MouseFound == 0){
                    b.UpdateProbabilitiesStationary(hasBeeped, 0.5, 2, b.GetCellsTraversed());
                    //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                }
                t.DeInitilizeEdge(t.edgeTo);
                t.DeInitilizevisit(t.visited);
                Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                MousePredict = null;
                MousePredict = b.FindHighestProbCell();
                Path = null;
                Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                for(int i = 0; i<Path.size(); i++){
                    count +=1;
                    Pair next = b.GridLocationOfPath(Path.get(i));
                    b.MoveBotStationary(next);
                    //b.MoveBot(next);
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                        if(MouseFound == 0){
                            if(b.GetBotPos().isSame(m1.GetMousePos())){
                                m1.SetMousePos(null);
                                FoundMouse1 =true;
                                MouseFound +=1;
                            }
                            else if(b.GetBotPos().isSame(m2.GetMousePos())){
                                m2.SetMousePos(null);
                                FoundMouse2 =true;
                                MouseFound +=1;
                            }
                        }
                        else if(MouseFound == 1){
                            if(FoundMouse1){
                                m2.SetMousePos(null);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                Break =true;
                            }
                            else if(FoundMouse2){
                                m1.SetMousePos(null);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                Break = true;
                            }
                            break;
                        }
                        
                    }
                    //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    //m1.MoveMouse1(nextMouseMove);
                   /
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                        Break = true;
                        if(MouseFound == 0){
                            if(b.GetBotPos().isSame(m1.GetMousePos())){
                                m1.SetMousePos(null);
                                FoundMouse1 =true;
                                MouseFound +=1;
                            }
                            else if(b.GetBotPos().isSame(m2.GetMousePos())){
                                m2.SetMousePos(null);
                                FoundMouse2 =true;
                                MouseFound +=1;
                            }
                        }
                        else if(MouseFound == 1){
                            if(b.GetBotPos().isSame(m1.GetMousePos())){
                                m1.SetMousePos(null);
                                FoundMouse1 =true;
                                MouseFound +=1;
                            }
                            else if(b.GetBotPos().isSame(m2.GetMousePos())){
                                m2.SetMousePos(null);
                                FoundMouse2 =true;
                                MouseFound +=1;
                            }
                            break;
                        }
                    }
                        
                    else{
                        //t.PrintShip(t.grid);
                    }
                }
                if(Break){
                    break;
                }
            }
        }
        */
    }

}
