import java.util.*;
public class Main {
    public static void main(String args[]){
        Shiptest t = new Shiptest();
        Bot b = new Bot(t);
        Mouse m = new Mouse(t);
        //t.PrintShip(t.grid);
        boolean hasBeeped = false;
        int count = 0;
        boolean Break = false;
        for(int j = 0; j<25; j++){
            t.PrintShip(t.grid);
            hasBeeped = b.Sense(0.5, t.StartingMousePos);
            b.UpdateProbabilities(hasBeeped, 0.5);
            t.DeInitilizeEdge(t.edgeTo);
            t.DeInitilizevisit(t.visited);
            Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
            Pair MousePredict = null;
            MousePredict = b.FindHighestProbCell();
            ArrayList<Integer> Path = null;
            Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);

            for(int i = 0; i<Path.size(); i++){
                count +=1;
                Pair next = b.GridLocationOfPath(Path.get(i));
                b.MoveBot(next);
                /* 
                ArrayList<Pair> NextMove = b.PotentialNextMove(b.GetBotPos().getKey(), b.GetBotPos().getValue());
                Pair NextStep = b.HighestProbNeighbor(NextMove, b.GetBotPos().getKey(), b.GetBotPos().getValue());
                b.MoveBot(NextStep);
                */
                if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                    System.out.print("BOT FOUND THE MOUSE at " + j + count);
                    Break = true;
                    break;
                }
                Pair nextMouseMove = m.PickRandomNeighbor(m.GetMousePos().getKey(), m.GetMousePos().getValue());
                m.MoveMouse(nextMouseMove);
                if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                    Break = true;
                    System.out.print("BOT FOUND THE MOUSE at " + j + count);
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
