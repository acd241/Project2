import java.util.*;
public class Main {
    public static void main(String args[]){
        
        Shiptest t = new Shiptest();
        Bot b = new Bot(t);
        Mouse m = new Mouse(t);
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
            //Pair nextMouseMove = m.PickRandomNeighbor(m.GetMousePos().getKey(), m.GetMousePos().getValue());
            //m.MoveMouse(nextMouseMove);
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
                    /* 
                    ArrayList<Pair> NextMove = b.PotentialNextMove(b.GetBotPos().getKey(), b.GetBotPos().getValue());
                    Pair NextStep = b.HighestProbNeighbor(NextMove, b.GetBotPos().getKey(), b.GetBotPos().getValue());
                    
                    b.MoveBot(NextStep);
                    */
                    
                    
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        int sense = j +1;
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        System.out.println();
                        t.PrintShip(t.grid);
                        Break = true;
                        break;
                    }
                    //Pair nextMouseMove = m.PickRandomNeighbor(m.GetMousePos().getKey(), m.GetMousePos().getValue());
                    //m.MoveMouse(nextMouseMove);
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
            //Pair nextMouseMove = m.PickRandomNeighbor(m.GetMousePos().getKey(), m.GetMousePos().getValue());
            //m.MoveMouse(nextMouseMove);
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
                    //Pair nextMouseMove = m.PickRandomNeighbor(m.GetMousePos().getKey(), m.GetMousePos().getValue());
                    //m.MoveMouse(nextMouseMove);
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
