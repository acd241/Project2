import java.util.ArrayList;

public class MultipleMiceTest {

    public static Pair Bot2TestStationaryMouse(double alpha){
        Shiptest t = new Shiptest(5, 1);
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
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

    public static Pair Bot2TestMovingMouse(double alpha){
        Shiptest t = new Shiptest(5, 1);
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
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
    




    public static void main(String args[]){

        //Pair SenseMovement = Bot2TestStationaryMouse(0.2);
        //System.out.println();
        //System.out.print("Total Sensing and Movement values. Sense: " + SenseMovement.getKey() + " Movement: " +SenseMovement.getValue());
        
        Pair SenseMovement2 = Bot2TestMovingMouse(0.2);
        System.out.println();
        System.out.print("Total Sensing and Movement values. Sense: " + SenseMovement2.getKey() + " Movement: " +SenseMovement2.getValue());
        
        /* 
        Pair SMMoving = Bot1TestMovingMice();
        System.out.println();
        System.out.print("Total Sensing and Movement values. Sense: " + SMMoving.getKey() + " Movement: " +SMMoving.getValue());
        */
        /* 
        Pair SenseMovement = Bot1TestStationaryMice();
        System.out.println();
        System.out.print("Total Sensing and Movement values. Sense: " + SenseMovement.getKey() + " Movement: " +SenseMovement.getValue());
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
