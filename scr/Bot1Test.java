import java.io.IOException;
import java.util.*;
public class Bot1Test {

    public static Pair Bot1TestMovingMouse(double alpha){
        Shiptest t = new Shiptest();
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
        t.StartingProbabilities();
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
                System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                Break = true;
                alreadyFoundMouse =true;
                return new Pair(0, count);
            }
            Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
            m1.MoveMouse1(nextMouseMove);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                Break = true;
                alreadyFoundMouse =true;
                return new Pair(0, count);
            }
            else{
                //t.PrintShip(t.grid);
            }
        }
        if(!alreadyFoundMouse){
            for(int j = 0; j<1000; j++){
                //t.PrintShip(t.grid);
                hasBeeped = b.Sense(alpha, m1.GetMousePos());
                //b.UpdateProbabilitiesStationary(hasBeeped, 0.5, 1, b.GetCellsTraversed());
                b.UpdateProbabilitiesMoving(hasBeeped, alpha, 1);
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
                    //b.MoveBotStationary(next);
                    b.MoveBot(next);
                    
                    
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        int sense = j +1;
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        System.out.println();
                        //t.PrintShip(t.grid);
                        Break = true;
                        return new Pair(sense, count);
                    }
                    Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    m1.MoveMouse1(nextMouseMove);
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        int sense = j +1;
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        System.out.println();
                        //t.PrintShip(t.grid);
                        Break = true;
                        return new Pair(sense, count);
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
        return new Pair(-1,-1);
    }

    public static Pair Bot1TestMovingMouseAVG(double alpha){
        Shiptest t = new Shiptest();
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
        t.StartingProbabilities();
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
                return new Pair(0, count);
            }
            Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
            m1.MoveMouse1(nextMouseMove);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                //System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                Break = true;
                alreadyFoundMouse =true;
                return new Pair(0, count);
            }
            else{
                //t.PrintShip(t.grid);
            }
        }
        if(!alreadyFoundMouse){
            for(int j = 0; j<1000; j++){
                //t.PrintShip(t.grid);
                hasBeeped = b.Sense(alpha, m1.GetMousePos());
                //b.UpdateProbabilitiesStationary(hasBeeped, 0.5, 1, b.GetCellsTraversed());
                b.UpdateProbabilitiesMoving(hasBeeped, alpha, 1);
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
                    //b.MoveBotStationary(next);
                    b.MoveBot(next);
                    
                    
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        int sense = j +1;
                        //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        //System.out.println();
                        //t.PrintShip(t.grid);
                        Break = true;
                        return new Pair(sense, count);
                    }
                    Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    m1.MoveMouse1(nextMouseMove);
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        int sense = j +1;
                        //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        //System.out.println();
                        //t.PrintShip(t.grid);
                        Break = true;
                        return new Pair(sense, count);
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
        return new Pair(-1,-1);
    }

    public static Pair Bot1TestStationaryMouse(double alpha){
        Shiptest t = new Shiptest();
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
        t.StartingProbabilities();
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
            for(int j = 0; j<1000; j++){
                //t.PrintShip(t.grid);
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

                for(int i = 0; i<Path.size(); i++){
                    count +=1;
                    Pair next = b.GridLocationOfPath(Path.get(i));
                    b.MoveBotStationary(next);
                    //b.MoveBot(next);
                    
                    
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        int sense = j +1;
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        System.out.println();
                        t.PrintShip(t.grid);
                        Break = true;
                        return new Pair(sense, count);
                    }
                    //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    //m1.MoveMouse1(nextMouseMove);
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        int sense = j +1;
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        System.out.println();
                        t.PrintShip(t.grid);
                        Break = true;
                        return new Pair(sense, count);
                    }
                    else{
                        t.PrintShip(t.grid);
                    }
                }
                if(Break){
                    break;
                }
            }
        }
        return new Pair(-1,-1);
    }

    public static Pair Bot1TestStationaryMouseAVG(double alpha){
        Shiptest t = new Shiptest();
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
        t.StartingProbabilities();
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
            for(int j = 0; j<1000; j++){
                //t.PrintShip(t.grid);
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

                for(int i = 0; i<Path.size(); i++){
                    count +=1;
                    Pair next = b.GridLocationOfPath(Path.get(i));
                    b.MoveBotStationary(next);
                    //b.MoveBot(next);
                    
                    
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        int sense = j +1;
                        //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        //System.out.println();
                        //t.PrintShip(t.grid);
                        Break = true;
                        return new Pair(sense, count);
                    }
                    //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    //m1.MoveMouse1(nextMouseMove);
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        int sense = j +1;
                        //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        //System.out.println();
                        //t.PrintShip(t.grid);
                        Break = true;
                        return new Pair(sense, count);
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
        return new Pair(-1,-1);
    }

    public static Pair Bot1TestStationaryMice(double alpha){
        Shiptest t = new Shiptest(2);
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
        MousePredict = b.FindStartingCell();
        ArrayList<Integer> Path = null;
        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
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
            /* 
            if(!FoundMouse1){
                Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                m1.MoveMouse1(nextMouseMove);
            }
            if(!FoundMouse2){
                Pair nextMouseMove2 = m2.PickRandomNeighbor(m2.GetMousePos().getKey(), m2.GetMousePos().getValue());
                m2.MoveMouse2(nextMouseMove2);
                
            }
                */ /* 
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
                */
            else{
                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                t.PrintShip(t.grid);
            }
        }
        int sense = 0;
        if(MouseFound<=1){
            boolean Break = false;
            while(!FoundMouse1 || !FoundMouse2){
                //t.PrintShip(t.grid);
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
                    //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    //m1.MoveMouse1(nextMouseMove);
                   /* 
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
                        */
                    else{
                        t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.PrintShip(t.grid);
                    }
                }
                if(Break){
                    break;
                }
            }
        }
        return new Pair(-1,-1);

    }

    public static Pair Bot1TestStationaryMiceAVG(double alpha){
        Shiptest t = new Shiptest(2);
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
        MousePredict = b.FindStartingCell();
        ArrayList<Integer> Path = null;
        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
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
            /* 
            if(!FoundMouse1){
                Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                m1.MoveMouse1(nextMouseMove);
            }
            if(!FoundMouse2){
                Pair nextMouseMove2 = m2.PickRandomNeighbor(m2.GetMousePos().getKey(), m2.GetMousePos().getValue());
                m2.MoveMouse2(nextMouseMove2);
                
            }
                */ /* 
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
                */
            else{
                t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                //t.PrintShip(t.grid);
            }
        }
        int sense = 0;
        if(MouseFound<=1){
            boolean Break = false;
            while(!FoundMouse1 || !FoundMouse2){
                //t.PrintShip(t.grid);
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
                    //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    //m1.MoveMouse1(nextMouseMove);
                   /* 
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
                        */
                    else{
                        t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        //t.PrintShip(t.grid);
                    }
                }
                if(Break){
                    break;
                }
            }
        }
        return new Pair(-1,-1);

    }


    public static Pair Bot1TestMovingMice(double alpha){
        Shiptest t = new Shiptest(2);
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
        MousePredict = b.FindStartingCell();
        ArrayList<Integer> Path = null;
        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
        boolean FoundMouse1 = false;
        int MouseFound = 0;
        boolean FoundMouse2 = false;
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
        int sense = 0;
        if(MouseFound<=1){
            boolean Break = false;
            while(!FoundMouse1 || !FoundMouse2){
                //t.PrintShip(t.grid);
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
                for(int i = 0; i<Path.size(); i++){
                    count +=1;
                    Pair next = b.GridLocationOfPath(Path.get(i));
                    b.MoveBot(next);
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
                        //t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        //t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.PrintShip(t.grid);
                    }
                }
                if(Break){
                    break;
                }
            }
        }
        return new Pair(-1,-1);
    }

    public static Pair Bot1TestMovingMiceAVG(double alpha){
        Shiptest t = new Shiptest(2);
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
        MousePredict = b.FindStartingCell();
        ArrayList<Integer> Path = null;
        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
        boolean FoundMouse1 = false;
        int MouseFound = 0;
        boolean FoundMouse2 = false;
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
        int sense = 0;
        if(MouseFound<=1){
            boolean Break = false;
            while(!FoundMouse1 || !FoundMouse2){
                //t.PrintShip(t.grid);
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
                for(int i = 0; i<Path.size(); i++){
                    count +=1;
                    Pair next = b.GridLocationOfPath(Path.get(i));
                    b.MoveBot(next);
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
                        //t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        //t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        //t.PrintShip(t.grid);
                    }
                }
                if(Break){
                    break;
                }
            }
        }
        return new Pair(-1,-1);
    }





    public static void main(String args[]){
        //INDIVIDUAL TEST SENSE AND MOVEMENT VALUES

        //BOT1 MOVING MOUSE TEST VALUES
         
        // Pair SMMoving = Bot1TestMovingMouse(0.5);
        // System.out.println();
        // System.out.print("Total Sensing and Movement values. Sense: " + SMMoving.getKey() + " Movement: " +SMMoving.getValue());
        

        //BOT1 STATIONARY MOUSE TEST VALUES
        /* 
        Pair SenseMovement = Bot1TestStationaryMouse(0.5);
        System.out.println();
        System.out.print("Stationary Bot 1: Total Sensing and Movement values. Sense: " + SenseMovement.getKey() + " Movement: " +SenseMovement.getValue());
        */
         
        //BOT1 MOVING MICE TEST VALUES
        /* 
        Pair SMiceMoving = Bot1TestMovingMice(0.4);
        System.out.println();
        System.out.print("Total Sensing and Movement values. Sense: " + SMiceMoving.getKey() + " Movement: " +SMiceMoving.getValue());
        */

        //BOT1 STATIONARY MICE TEST VALUES
        /* 
        Pair MiceSenseMovement = Bot1TestStationaryMice(0.5);
        System.out.println();
        System.out.print("Total Sensing and Movement values. Sense: " + MiceSenseMovement.getKey() + " Movement: " +MiceSenseMovement.getValue());
        */
        
        //AVERAGE TEST CASES SENSE AND MOVEMENT VALUES 

        //BOT1 STATIONARY MOUSE AVG TEST VALUES

         
        // for (double k = 0; k < 11.0; k += 1.0)
        // {
        //     int sense1 = 0;
        //     int count1 = 0;
        //     int skip1 = 0;
        //     int numberOfTrials = 50; // Sets number of tests that each alpha runs
        //     for(int i = 0; i< numberOfTrials; i++){
        //         Pair SenseMovement1 = Bot1TestStationaryMouseAVG(k/10);
        //         if(SenseMovement1.getKey() > 100000 || SenseMovement1.getValue()> 100000){
        //             skip1 +=1;
        //             continue;
        //         }
        //         sense1 += SenseMovement1.getKey();
        //         count1 += SenseMovement1.getValue();
        //     }
        //     double AvgSense1 = (sense1 *1.0)/(numberOfTrials - skip1);
        //     double AvgMovement1 = (count1 * 1.0)/ (numberOfTrials - skip1);
        //     System.out.println("Bot1 Stationary Mouse Average Sense: " + AvgSense1 + " Average Movement: " + AvgMovement1 + " Alpha Value: " + k/10);
        // }
        

        //BOT1 MOVING MOUSE AVG TEST VALUES

        // for (double k = 0; k < 11.0; k += 1.0)
        // {
        //     int sense2 = 0;
        //     int count2 = 0;
        //     int skip2 = 0;
        //     int numberOfTrials2 = 50; // Sets number of tests that each alpha runs
        //     for(int i = 0; i< numberOfTrials2; i++){
        //         Pair SenseMovement2 = Bot1TestMovingMouseAVG(k/10);
        //         if(SenseMovement2.getKey() > 1000 || SenseMovement2.getValue()> 1000){
        //             skip2 +=1;
        //             continue;
        //         }
        //         sense2+= SenseMovement2.getKey();
        //         count2 += SenseMovement2.getValue();
        //     }
        //     double AvgSense2 = (sense2 *1.0)/(numberOfTrials2 - skip2);
        //     double AvgMovement2 = (count2 * 1.0)/ (numberOfTrials2-skip2);
        //     System.out.print("Bot1 Moving Mouse Average Sense: " + AvgSense2 + " Average Movement: " + AvgMovement2 + " Alpha Value: " + k/10);
        // }

        //BOT1 STATIONARY MICE AVG TEST VALUES

        // for (double k = 0; k < 11.0; k += 1.0)
        // {
        //     int sense3 = 0;
        //     int count3 = 0;
        //     int skip3 = 0;
        //     int numberOfTrials3 = 50; // Sets number of tests that each alpha runs
        //     for(int i = 0; i< numberOfTrials3; i++){
        //         Pair SenseMovement3 = Bot1TestStationaryMiceAVG(k/10);
        //         if(SenseMovement3.getKey() > 1000000 || SenseMovement3.getValue()> 1000000){ // INCREASE SENSE MORE
        //             skip3 +=1;
        //             continue;
        //         }
        //         sense3 += SenseMovement3.getKey();
        //         count3 += SenseMovement3.getValue();

        //         System.out.println("Ran " + i + " Times");
        //     }
        //     double AvgSense3 = (sense3 *1.0)/(numberOfTrials3 - skip3);
        //     double AvgMovement3 = (count3 * 1.0)/ (numberOfTrials3-skip3);
        //     System.out.print("Bot1 Stationary Mice Average Sense: " + AvgSense3 + " Average Movement: " + AvgMovement3 + " Alpha Value: " + k/10);
        // }

        //BOT1 MOVING MICE AVG TEST VALUES
        // int sense4 = 0;
        // int count4 = 0;
        // int skip4 = 0;
        // int numberOfTrials4 = 50;
        // for(int i = 0; i< numberOfTrials4; i++){
        //     Pair SenseMovement4 = Bot1TestMovingMiceAVG(0.2);
        //     if(SenseMovement4.getKey() > 10000000 || SenseMovement4.getValue()> 10000000){
        //         skip4 +=1;
        //         continue;
        //     }
        //     sense4 += SenseMovement4.getKey();
        //     count4 += SenseMovement4.getValue();

        //     System.out.println("Ran " + i + " Times");
        // }
        // double AvgSense4 = (sense4 *1.0)/(numberOfTrials4 - skip4);
        // double AvgMovement4 = (count4 * 1.0)/ (numberOfTrials4-skip4);
        // System.out.print("Bot1 Moving Mice Average Sense: " + AvgSense4 + " Average Movement: " + AvgMovement4);

        
        
        
        /* 
        Shiptest t = new Shiptest();
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
                break;
            }
            //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
            //m1.MoveMouse1(nextMouseMove);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                Break = true;
                alreadyFoundMouse =true;
                break;
            }
            else{
                //t.PrintShip(t.grid);
            }
        }
        if(!alreadyFoundMouse){
            for(int j = 0; j<100; j++){
                t.PrintShip(t.grid);
                hasBeeped = b.Sense(0.5, t.StartingMousePos);
                b.UpdateProbabilitiesStationary(hasBeeped, 0.5, 1, b.GetCellsTraversed());
                //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
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
                        int sense = j +1;
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        System.out.println();
                        t.PrintShip(t.grid);
                        Break = true;
                        break;
                    }
                    //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    //m1.MoveMouse1(nextMouseMove);
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        int sense = j +1;
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        System.out.println();
                        t.PrintShip(t.grid);
                        Break = true;
                        break;
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
        
        /* 
        Shiptest t = new Shiptest( 0, 1);
        Bot b = new Bot(t);
        Mouse m = new Mouse(t);
        t.PrintShip(t.grid);
        boolean hasBeeped = false;
        int count = 0;
        boolean Break = false;
        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
        Pair MousePredict = null;
        MousePredict = b.FindStartingCellTest();
        ArrayList<Integer> Path = null;
        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
        boolean alreadyFoundMouse = false;
        for(int i = 0; i<Path.size(); i++){
            count +=1;
            Pair next = b.GridLocationOfPath(Path.get(i));
            b.MoveBotStationary(next);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                Break = true;
                alreadyFoundMouse =true;
                break;
            }
            //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
            //m1.MoveMouse(nextMouseMove);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                Break = true;
                System.out.print("BOT FOUND THE MOUSE.Sense: 0 Movement: " + count);
                alreadyFoundMouse = true;
                break;
            }
            else{
                t.PrintShip(t.grid);
            }
        }
        if(!alreadyFoundMouse){
            for(int j = 0; j<50; j++){
                t.PrintShip(t.grid);
                hasBeeped = b.Sense(0.5, t.StartingMousePos);
                b.UpdateProbabilitiesStationary(hasBeeped, 0.5, 1, b.GetCellsTraversed());
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
                    
                    //ArrayList<Pair> NextMove = b.PotentialNextMove(b.GetBotPos().getKey(), b.GetBotPos().getValue());
                    //Pair NextStep = b.HighestProbNeighbor(NextMove, b.GetBotPos().getKey(), b.GetBotPos().getValue());
                    //b.MoveBot(NextStep);
                    
                    
                    
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        int sense = j +1;
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        System.out.println();
                        t.PrintShip(t.grid);
                        Break = true;
                        break;
                    }
                    //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    //m1.MoveMouse(nextMouseMove);
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        Break = true;
                        int sense = j +1;
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        System.out.println();
                        t.PrintShip(t.grid);
                        break;
                    }
                    else{
                        t.PrintShip(t.grid);
                    }
                }
                if(Break){
                    break;
                }
            }
        }
            
    }
    
    */
    
}
