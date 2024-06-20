import java.util.*;
public class Main {
    public static void main(String args[]){
        Shiptest t = new Shiptest();
        Bot b = new Bot(t);
        Mouse m = new Mouse(t);
        t.PrintShip(t.grid);

        for(int i = 0; i< 1000; i++){
            boolean hasBeeped = b.Sense(0.4, t.StartingMousePos);
            if(hasBeeped){
                b.UpdateProbabilities(hasBeeped, 0.4);
            }
            else if(!hasBeeped){
                b.UpdateProbabilities(hasBeeped, 0.4);
            }
            ArrayList<Pair> NextMove = b.PotentialNextMove(b.GetBotPos().getKey(), b.GetBotPos().getValue());
            Pair NextStep = b.HighestProbNeighbor(NextMove, b.GetBotPos().getKey(), b.GetBotPos().getValue());
            b.MoveBot(NextStep);
            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                System.out.print("BOT FOUND THE MOUSE at " + i);
                break;
            }
            else{
                t.PrintShip(t.grid);
            }
        }
        
    }
    
}
