import java.io.IOException;
import java.util.*;
public class Main {

    public static Pair Bot1TestMovingMouse(){
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
                hasBeeped = b.Sense(0.5, m1.GetMousePos());
                //b.UpdateProbabilitiesStationary(hasBeeped, 0.5, 1, b.GetCellsTraversed());
                b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
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

    public static Pair Bot1TestStationaryMouse(){
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

    public static Pair Bot1TestStationaryMice(){
        Shiptest t = new Shiptest(5,2);
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
                    hasBeeped = b.Sense(0.5, m1.GetMousePos());
                    if(!hasBeeped){
                        hasBeeped = b.Sense(0.5, m2.GetMousePos());
                    }
                }
                else if(MouseFound == 1){
                    if(FoundMouse1){
                        hasBeeped = b.Sense(0.5, m2.GetMousePos());
                    }
                    else if(FoundMouse2){
                        hasBeeped = b.Sense(0.5, m1.GetMousePos());
                    }
                }
                if(MouseFound == 1){
                    b.UpdateProbabilitiesStationary(hasBeeped, 0.5, 1, b.GetCellsTraversed());
                    //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                }
                else if(MouseFound == 0){
                    double [][] temp = null; 
                    temp = b.Replicate();
                    b.UpdateMouse1Grid(hasBeeped, 0.5);
                    b.UpdateMouse2Grid(hasBeeped, 0.5, temp);
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

    
    public static Pair Bot1TestMovingMice(){
        Shiptest t = new Shiptest(2);
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
        Mouse m2 = new Mouse(t);
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
            //b.MoveBotStationary(next);
            b.MoveBot(next);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                System.out.print("BOT FOUND THE MOUSE. Sense: 0 Movement: " + count);
                System.out.println();
                if(MouseFound == 0){
                    if(b.GetBotPos().isSame(m1.GetMousePos())){
                        m1.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        t.StartingProbabilities();
                        //t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse1 =true;
                        MouseFound +=1;
                        //t.PrintShip(t.grid);
                        continue;
                    }
                    else if(b.GetBotPos().isSame(m2.GetMousePos())){
                        m2.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        t.StartingProbabilities();
                        //t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse2 =true;
                        MouseFound +=1;
                        //t.PrintShip(t.grid);
                        continue;
                    }
                }
                else if(MouseFound == 1){
                    if(FoundMouse1){
                        m2.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        t.StartingProbabilities();
                        //t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse1 =true;
                        MouseFound +=1;
                        //t.PrintShip(t.grid);
                        return new Pair(0, count);
                    }
                    else if(FoundMouse2){
                        m1.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        t.StartingProbabilities();
                        //t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse1 =true;
                        MouseFound +=1;
                        //t.PrintShip(t.grid);
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
                        t.StartingProbabilities();
                        //t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse1 =true;
                        MouseFound +=1;
                        //t.PrintShip(t.grid);
                        continue;
                    }
                    else if(b.GetBotPos().isSame(m2.GetMousePos())){
                        m2.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        t.StartingProbabilities();
                        //t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse2 =true;
                        MouseFound +=1;
                        //t.PrintShip(t.grid);
                        continue;
                    }
                }
                else if(MouseFound == 1){
                    if(FoundMouse2){
                        m1.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        t.StartingProbabilities();
                        //t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse1 =true;
                        MouseFound +=1;
                        //t.PrintShip(t.grid);
                        return new Pair(0, count);
                    }
                    else if(FoundMouse1){
                        m2.SetMousePos(null);
                        t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                        t.StartingProbabilities();
                        //t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                        FoundMouse2 =true;
                        MouseFound +=1;
                        //t.PrintShip(t.grid);
                        return new Pair(0, count);
                    }
                    break;
                }
            }
                
            else{
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
                    hasBeeped = b.Sense(0.5, m1.GetMousePos());
                    if(!hasBeeped){
                        hasBeeped = b.Sense(0.5, m2.GetMousePos());
                    }
                }
                else if(MouseFound == 1){
                    if(FoundMouse1){
                        hasBeeped = b.Sense(0.5, m2.GetMousePos());
                    }
                    else if(FoundMouse2){
                        hasBeeped = b.Sense(0.5, m1.GetMousePos());
                    }
                }
                if(MouseFound == 1){
                    //b.UpdateProbabilitiesStationary(hasBeeped, 0.5, 1, b.GetCellsTraversed());
                    b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                }
                else if(MouseFound == 0){
                    //b.UpdateProbabilitiesStationary(hasBeeped, 0.5, 2, b.GetCellsTraversed());
                    b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 2);
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
                    //b.MoveBotStationary(next);
                    b.MoveBot(next);
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        System.out.print("BOT FOUND THE MOUSE. Sense:" + sense + " Movement: " + count);
                        System.out.println();
                        if(MouseFound == 0){
                            if(b.GetBotPos().isSame(m1.GetMousePos())){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                t.StartingProbabilities();
                                //t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                //t.PrintShip(t.grid);
                                continue;
                            }
                            else if(b.GetBotPos().isSame(m2.GetMousePos())){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                t.StartingProbabilities();
                                //t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse2 =true;
                                MouseFound +=1;
                                //t.PrintShip(t.grid);
                                continue;
                            }
                        }
                        else if(MouseFound == 1){
                            if(FoundMouse1){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                t.StartingProbabilities();
                                //t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                Break =true;
                                //t.PrintShip(t.grid);
                                return new Pair(sense, count);
                            }
                            else if(FoundMouse2){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                t.StartingProbabilities();
                                //t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                Break = true;
                                //t.PrintShip(t.grid);
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
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        System.out.println();
                        //Break = true;
                        if(MouseFound == 0){
                            if(b.GetBotPos().isSame(m1.GetMousePos())){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                t.StartingProbabilities();
                                //t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                //t.PrintShip(t.grid);
                                continue;
                            }
                            else if(b.GetBotPos().isSame(m2.GetMousePos())){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                t.StartingProbabilities();
                                //t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse2 =true;
                                MouseFound +=1;
                                //t.PrintShip(t.grid);
                                continue;
                            }
                        }
                        else if(MouseFound == 1){
                            if(FoundMouse1){
                                m2.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                t.StartingProbabilities();
                                //t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse2 =true;
                                MouseFound +=1;
                                //t.PrintShip(t.grid);
                                return new Pair(sense, count);
                            }
                            else if(FoundMouse2){
                                m1.SetMousePos(null);
                                t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setMouse(false);
                                t.StartingProbabilities();
                                //t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].setProbOfMouse(0.00);
                                FoundMouse1 =true;
                                MouseFound +=1;
                                //t.PrintShip(t.grid);
                                return new Pair(sense, count);
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
        return new Pair(-1,-1);
    }






    public static void main(String args[]){
        /* 
        Pair SMMoving = Bot1TestMovingMouse();
        System.out.println();
        System.out.print("Total Sensing and Movement values. Sense: " + SMMoving.getKey() + " Movement: " +SMMoving.getValue());
        */
        /* 
        Pair SenseMovement = Bot1TestStationaryMouse();
        System.out.println();
        System.out.print("Stationary Bot 1: Total Sensing and Movement values. Sense: " + SenseMovement.getKey() + " Movement: " +SenseMovement.getValue());
        */
         /* 
        Pair SMiceMoving = Bot1TestMovingMice();
        System.out.println();
        System.out.print("Total Sensing and Movement values. Sense: " + SMiceMoving.getKey() + " Movement: " +SMiceMoving.getValue());
        */
        
        Pair MiceSenseMovement = Bot1TestStationaryMice();
        System.out.println();
        System.out.print("Total Sensing and Movement values. Sense: " + MiceSenseMovement.getKey() + " Movement: " +MiceSenseMovement.getValue());
        
        
        
        
        
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
