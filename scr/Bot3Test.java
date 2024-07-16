import java.util.ArrayList;

public class Bot3Test {


    public static Pair Bot3TestMovingMouse(double alpha){
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
        boolean bestProbability = false;
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
            int sense = 0;
            for(int j = 0; j<1000; j++){
                //t.PrintShip(t.grid);
                sense+=1;
                hasBeeped = b.Sense(alpha, t.StartingMousePos);

                if (t.highestProbabilityValue() > 0.7 || bestProbability)
                {
                    bestProbability = true;
                    
                    b.UpdateProbabilitiesMoving(hasBeeped, alpha, 1);
                    //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                    t.DeInitilizeEdge(t.edgeTo);
                    t.DeInitilizevisit(t.visited);
                    Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                    MousePredict = null;
                    MousePredict = b.FindHighestProbCell();
                    Path = null;
                    Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                
                    for(int i = 0; i<(int)((Path.size())) ; i++)
                    {
                        count +=1;
                        Pair next = b.GridLocationOfPath(Path.get(i));
                        //b.MoveBotStationary(next);
                        b.MoveBot(next);
                        
                        
                        if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                            System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                            System.out.println();
                            t.PrintShip(t.grid);
                            Break = true;
                            return new Pair(sense, count);
                        }
                        Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                        m1.MoveMouse1(nextMouseMove);
                        if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
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
                }

                //b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                b.UpdateProbabilitiesMoving(hasBeeped, alpha, 1);
                t.DeInitilizeEdge(t.edgeTo);
                t.DeInitilizevisit(t.visited);
                Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                MousePredict = null;
                MousePredict = b.FindHighestProbCell();
                Path = null;
                Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                //int Path = Path.size()/2;

                System.out.println("Path Size 3/4: " + (int)(Path.size()*(0.75)));

                for(int i = 0; i<(int)((Path.size()*1.0)*(0.75))+1 ; i++){
                    count +=1;
                    Pair next = b.GridLocationOfPath(Path.get(i));
                    //b.MoveBotStationary(next);
                    b.MoveBot(next);
                    
                    
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        System.out.println();
                        t.PrintShip(t.grid);
                        Break = true;
                        return new Pair(sense, count);
                    }
                    Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    m1.MoveMouse1(nextMouseMove);
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
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
                for(int i = 0; i<(int)((Path.size()*1.0) * (0.3)) + 1; i++){
                    if(i%2 == 0){
                        sense +=1;
                        if (t.highestProbabilityValue() > 0.7 || bestProbability)
                        {
                            bestProbability = true;
                            break;
                        }
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
                    }
                    else if(i%2 == 1){
                        Pair next = b.GridLocationOfPath(Path.get(0));
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
                        else{
                            t.PrintShip(t.grid);
                        }
                    }
                    

                    /* 
                    if(Break){
                        break;
                    }
                        */
                }
            }
        }
        return new Pair(-1,-1);
    }

    public static Pair Bot3TestMovingMouseAVG(double alpha){
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
        boolean bestProbability = false;
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
            int sense = 0;
            for(int j = 0; j<1000; j++){
                //t.PrintShip(t.grid);
                sense+=1;
                hasBeeped = b.Sense(alpha, t.StartingMousePos);

                if (t.highestProbabilityValue() > 0.7 || bestProbability)
                {
                    bestProbability = true;
                    
                    b.UpdateProbabilitiesMoving(hasBeeped, alpha, 1);
                    //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                    t.DeInitilizeEdge(t.edgeTo);
                    t.DeInitilizevisit(t.visited);
                    Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                    MousePredict = null;
                    MousePredict = b.FindHighestProbCell();
                    Path = null;
                    Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                
                    for(int i = 0; i<(int)((Path.size())) ; i++)
                    {
                        count +=1;
                        Pair next = b.GridLocationOfPath(Path.get(i));
                        //b.MoveBotStationary(next);
                        b.MoveBot(next);
                        
                        
                        if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                            //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                            //System.out.println();
                            //t.PrintShip(t.grid);
                            Break = true;
                            return new Pair(sense, count);
                        }
                        Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                        m1.MoveMouse1(nextMouseMove);
                        if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
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
                }

                //b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                b.UpdateProbabilitiesMoving(hasBeeped, alpha, 1);
                t.DeInitilizeEdge(t.edgeTo);
                t.DeInitilizevisit(t.visited);
                Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                MousePredict = null;
                MousePredict = b.FindHighestProbCell();
                Path = null;
                Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                //int Path = Path.size()/2;

                System.out.println("Path Size 3/4: " + (int)(Path.size()*(0.75)));

                for(int i = 0; i<(int)((Path.size()*1.0)*(0.75))+1 ; i++){
                    count +=1;
                    Pair next = b.GridLocationOfPath(Path.get(i));
                    //b.MoveBotStationary(next);
                    b.MoveBot(next);
                    
                    
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        //System.out.println();
                        //t.PrintShip(t.grid);
                        Break = true;
                        return new Pair(sense, count);
                    }
                    Pair nextMouseMove = m1.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    m1.MoveMouse1(nextMouseMove);
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
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
                for(int i = 0; i<(int)((Path.size()*1.0) * (0.3)) + 1; i++){
                    if(i%2 == 0){
                        sense +=1;
                        if (t.highestProbabilityValue() > 0.7 || bestProbability)
                        {
                            bestProbability = true;
                            break;
                        }
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
                    }
                    else if(i%2 == 1){
                        Pair next = b.GridLocationOfPath(Path.get(0));
                        b.MoveBot(next);
                        count +=1;
                        if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                            //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                            //System.out.println();
                            //t.PrintShip(t.grid);
                            Break = true;
                            alreadyFoundMouse = true;
                            return new Pair(sense, count);
                        }
                        else{
                            //t.PrintShip(t.grid);
                        }
                    }
                    

                    /* 
                    if(Break){
                        break;
                    }
                        */
                }
            }
        }
        return new Pair(-1,-1);
    }

    public static Pair Bot3TestStationaryMouse(double alpha){
        Shiptest t = new Shiptest();
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
        t.StartingProbabilities();
        t.PrintShip(t.grid);
        t.PrintProbabilityMap();
        t.WriteFileDouble(t.grid);
        t.WriteFileInt(true);
        t.WriteFileInt(false);
        t.WriteFilealpha(alpha);

        boolean hasBeeped = false;
        int count = 0;
        boolean Break = false;
        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
        Pair MousePredict = null;
        MousePredict = b.FindStartingCell();
        ArrayList<Integer> Path = null;
        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
        boolean alreadyFoundMouse = false;
        boolean bestProbability = false;
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
            for(int j = 0; j<1000; j++){
                //t.PrintShip(t.grid);
                sense+=1;
                hasBeeped = b.Sense(alpha, t.StartingMousePos);

                if (t.highestProbabilityValue() > 0.7 || bestProbability)
                {
                    bestProbability = true;
                    
                    b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                    //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                    t.DeInitilizeEdge(t.edgeTo);
                    t.DeInitilizevisit(t.visited);
                    Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                    MousePredict = null;
                    MousePredict = b.FindHighestProbCell();
                    Path = null;
                    Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                
                    for(int i = 0; i<(int)((Path.size())) ; i++)
                    {
                        count +=1;
                        Pair next = b.GridLocationOfPath(Path.get(i));
                        b.MoveBotStationary(next);
                        //b.MoveBot(next);
                        
                        
                        if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                            System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                            System.out.println();
                            t.PrintShip(t.grid);
                            Break = true;
                            return new Pair(sense, count);
                        }
                        //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                        //m1.MoveMouse1(nextMouseMove);
                        if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
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
                }

                b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                t.DeInitilizeEdge(t.edgeTo);
                t.DeInitilizevisit(t.visited);
                Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                MousePredict = null;
                MousePredict = b.FindHighestProbCell();
                Path = null;
                Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                //int Path = Path.size()/2;

                System.out.println("Path Size 3/4: " + (int)(Path.size()*(0.75)));

                for(int i = 0; i<(int)((Path.size()*1.0)*(0.75))+1 ; i++){
                    count +=1;
                    Pair next = b.GridLocationOfPath(Path.get(i));
                    b.MoveBotStationary(next);
                    //b.MoveBot(next);
                    
                    
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        System.out.println();
                        t.PrintShip(t.grid);
                        Break = true;
                        return new Pair(sense, count);
                    }
                    //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    //m1.MoveMouse1(nextMouseMove);
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
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
                for(int i = 0; i<(int)((Path.size()*1.0) * (0.3)) + 1; i++){
                    if(i%2 == 0){
                        sense +=1;
                        if (t.highestProbabilityValue() > 0.7 || bestProbability)
                        {
                            bestProbability = true;
                            break;
                        }
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
                    

                    /* 
                    if(Break){
                        break;
                    }
                        */
                }
            }
        }
        return new Pair(-1,-1);
    }
/* 
        public static Pair Bot3TestStationaryMouse2(double alpha){
        Shiptest t = new Shiptest();
        Bot b = new Bot(t);
        Mouse m1 = new Mouse(t);
        t.StartingProbabilities();
        t.PrintShip(t.grid);
        int sense = 0;

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
            for(int j = 0; j<100; j++)
            {
                if (j % 2 == 0)
                {
                    for (int i = 0; i < 10; i++)
                    {
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
                else
                {
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
                        sense = j +1;
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        System.out.println();
                        t.PrintShip(t.grid);
                        Break = true;
                        return new Pair(sense, count);
                    }
                    //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    //m1.MoveMouse1(nextMouseMove);
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        sense = j +1;
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
                }
            }
        }
        return new Pair(-1,-1);
    }
*/
public static Pair Bot3TestStationaryMouseAVG(double alpha){
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
    boolean bestProbability = false;
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
        int sense = 0;
        for(int j = 0; j<1000; j++){
            //t.PrintShip(t.grid);
            sense+=1;
            hasBeeped = b.Sense(alpha, t.StartingMousePos);

            if (t.highestProbabilityValue() > 0.7 || bestProbability)
            {
                bestProbability = true;
                
                b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                t.DeInitilizeEdge(t.edgeTo);
                t.DeInitilizevisit(t.visited);
                Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                MousePredict = null;
                MousePredict = b.FindHighestProbCell();
                Path = null;
                Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
            
                for(int i = 0; i<(int)((Path.size())) ; i++)
                {
                    count +=1;
                    Pair next = b.GridLocationOfPath(Path.get(i));
                    b.MoveBotStationary(next);
                    //b.MoveBot(next);
                    
                    
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                        //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                        //System.out.println();
                        //t.PrintShip(t.grid);
                        Break = true;
                        return new Pair(sense, count);
                    }
                    //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                    //m1.MoveMouse1(nextMouseMove);
                    if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
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
            }

            b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
            //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
            t.DeInitilizeEdge(t.edgeTo);
            t.DeInitilizevisit(t.visited);
            Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
            MousePredict = null;
            MousePredict = b.FindHighestProbCell();
            Path = null;
            Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
            //int Path = Path.size()/2;

            //System.out.println("Path Size 3/4: " + (int)(Path.size()*(0.75)));

            for(int i = 0; i<(int)((Path.size()*1.0)*(0.75))+1 ; i++){
                count +=1;
                Pair next = b.GridLocationOfPath(Path.get(i));
                b.MoveBotStationary(next);
                //b.MoveBot(next);
                
                
                if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                    //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                    //System.out.println();
                    //t.PrintShip(t.grid);
                    Break = true;
                    return new Pair(sense, count);
                }
                //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                //m1.MoveMouse1(nextMouseMove);
                if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
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
            for(int i = 0; i<(int)((Path.size()*1.0) * (0.3)) + 1; i++){
                if(i%2 == 0){
                    sense +=1;
                    if (t.highestProbabilityValue() > 0.7 || bestProbability)
                    {
                        bestProbability = true;
                        break;
                    }
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
                        //System.out.println();
                        //t.PrintShip(t.grid);
                        Break = true;
                        alreadyFoundMouse = true;
                        return new Pair(sense, count);
                    }
                    else{
                        //t.PrintShip(t.grid);
                    }
                }
                

                /* 
                if(Break){
                    break;
                }
                    */
            }
        }
    }
    return new Pair(-1,-1);
}

    public static Pair Bot3TestStationaryMice(double alpha){
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
        boolean bestProbability = false;
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
                if(MouseFound == 0){
                    if (t.highestProbabilityValueMice() > 0.7 || bestProbability)
                    {
                        bestProbability = true;
                        
                        double [][] temp = null; 
                        temp = b.Replicate();
                        b.UpdateMouse1Grid(hasBeeped, alpha);
                        b.UpdateMouse2Grid(hasBeeped, alpha, temp);
                        b.Normalization(t.MouseGrid1);
                        b.Normalization(t.MouseGrid2);
                        //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                        t.DeInitilizeEdge(t.edgeTo);
                        t.DeInitilizevisit(t.visited);
                        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                        MousePredict = null;
                        MousePredict = b.FindHighestProbCell();
                        Path = null;
                        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                    
                        for(int i = 0; i<(int)((Path.size())) ; i++)
                        {
                            count +=1;
                            Pair next = b.GridLocationOfPath(Path.get(i));
                            b.UpdateMoveStationaryMice(next);
                            //b.MoveBot(next);
                            
                            
                            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                                System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
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
                                    break;
                                }
                                
                            }
                            //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                            //m1.MoveMouse1(nextMouseMove);
                            /* 
                            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                                System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                                System.out.println();
                                t.PrintShip(t.grid);
                                Break = true;
                                return new Pair(sense, count);
                            }
                                */
                            else{
                                t.PrintShip(t.grid);
                            }
                        }
                    }
                }
                else if(MouseFound == 1){
                    if (t.highestProbabilityValue() > 0.7 || bestProbability)
                    {
                        bestProbability = true;
                        
                        b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                        //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                        t.DeInitilizeEdge(t.edgeTo);
                        t.DeInitilizevisit(t.visited);
                        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                        MousePredict = null;
                        MousePredict = b.FindHighestProbCell();
                        Path = null;
                        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                    
                        for(int i = 0; i<(int)((Path.size())) ; i++)
                        {
                            count +=1;
                            Pair next = b.GridLocationOfPath(Path.get(i));
                            b.MoveBotStationary(next);
                            //b.MoveBot(next);
                            
                            
                            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                                System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
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
                                    break;
                                }
                                
                            }
                            //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                            //m1.MoveMouse1(nextMouseMove);
                            /* 
                            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                                System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                                System.out.println();
                                t.PrintShip(t.grid);
                                Break = true;
                                return new Pair(sense, count);
                            }
                                */
                            else{
                                t.PrintShip(t.grid);
                            }
                        }
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
                for(int i = 0; i<(int)((Path.size()*1.0)*(0.75))+1 ; i++){
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
                for(int i = 0; i<(int)((Path.size()*1.0) * (0.3)) + 1; i++){
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
                        if(MouseFound == 0){
                            if (t.highestProbabilityValueMice() > 0.7 || bestProbability)
                            {
                                bestProbability = true;
                                break;
                            }
                        }
                        else if(MouseFound == 1){
                            if (t.highestProbabilityValue() > 0.7 || bestProbability)
                            {
                                bestProbability = true;
                                break;
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
                    

                    /* 
                    if(Break){
                        break;
                    }
                        */
                }
                if(Break){
                    break;
                }
            }
        }
        return new Pair(-1,-1);

    }

    public static Pair Bot3TestStationaryMiceAVG(double alpha){
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
        boolean bestProbability = false;
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
                if(MouseFound == 0){
                    if (t.highestProbabilityValueMice() > 0.7 || bestProbability)
                    {
                        bestProbability = true;
                        
                        double [][] temp = null; 
                        temp = b.Replicate();
                        b.UpdateMouse1Grid(hasBeeped, alpha);
                        b.UpdateMouse2Grid(hasBeeped, alpha, temp);
                        b.Normalization(t.MouseGrid1);
                        b.Normalization(t.MouseGrid2);
                        //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                        t.DeInitilizeEdge(t.edgeTo);
                        t.DeInitilizevisit(t.visited);
                        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                        MousePredict = null;
                        MousePredict = b.FindHighestProbCell();
                        Path = null;
                        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                    
                        for(int i = 0; i<(int)((Path.size())) ; i++)
                        {
                            count +=1;
                            Pair next = b.GridLocationOfPath(Path.get(i));
                            b.UpdateMoveStationaryMice(next);
                            //b.MoveBot(next);
                            
                            
                            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                                //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
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
                                    break;
                                }
                                
                            }
                            //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                            //m1.MoveMouse1(nextMouseMove);
                            /* 
                            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                                System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                                System.out.println();
                                t.PrintShip(t.grid);
                                Break = true;
                                return new Pair(sense, count);
                            }
                                */
                            else{
                                //t.PrintShip(t.grid);
                            }
                        }
                    }
                }
                else if(MouseFound == 1){
                    if (t.highestProbabilityValue() > 0.7 || bestProbability)
                    {
                        bestProbability = true;
                        
                        b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                        //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                        t.DeInitilizeEdge(t.edgeTo);
                        t.DeInitilizevisit(t.visited);
                        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                        MousePredict = null;
                        MousePredict = b.FindHighestProbCell();
                        Path = null;
                        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                    
                        for(int i = 0; i<(int)((Path.size())) ; i++)
                        {
                            count +=1;
                            Pair next = b.GridLocationOfPath(Path.get(i));
                            b.MoveBotStationary(next);
                            //b.MoveBot(next);
                            
                            
                            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                                //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
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
                                    break;
                                }
                                
                            }
                            //Pair nextMouseMove = m.PickRandomNeighbor(m1.GetMousePos().getKey(), m1.GetMousePos().getValue());
                            //m1.MoveMouse1(nextMouseMove);
                            /* 
                            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                                System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
                                System.out.println();
                                t.PrintShip(t.grid);
                                Break = true;
                                return new Pair(sense, count);
                            }
                                */
                            else{
                                //t.PrintShip(t.grid);
                            }
                        }
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
                for(int i = 0; i<(int)((Path.size()*1.0)*(0.75))+1 ; i++){
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
                for(int i = 0; i<(int)((Path.size()*1.0) * (0.3)) + 1; i++){
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
                        if(MouseFound == 0){
                            if (t.highestProbabilityValueMice() > 0.7 || bestProbability)
                            {
                                bestProbability = true;
                                break;
                            }
                        }
                        else if(MouseFound == 1){
                            if (t.highestProbabilityValue() > 0.7 || bestProbability)
                            {
                                bestProbability = true;
                                break;
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
                        else{
                            t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                            t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                            //t.PrintShip(t.grid);
                        }
                    }
                    

                    /* 
                    if(Break){
                        break;
                    }
                        */
                }
                if(Break){
                    break;
                }
            }
        }
        return new Pair(-1,-1);

    }


    public static Pair Bot3TestMovingMice(double alpha){
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
        boolean bestProbability = false;
        for(int i = 0; i<Path.size(); i++){
            count +=1;
            Pair next = b.GridLocationOfPath(Path.get(i));
            b.MoveBot(next);
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
                    break;
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
                if(MouseFound == 0){
                    if (t.highestProbabilityValueMice() > 0.7 || bestProbability)
                    {
                        bestProbability = true;
                        
                        double [][] temp = null; 
                        temp = b.Replicate();
                        b.UpdateMouse1Grid(hasBeeped, alpha);
                        b.UpdateMouse2Grid(hasBeeped, alpha, temp);
                        b.Normalization(t.MouseGrid1);
                        b.Normalization(t.MouseGrid2);
                        //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                        t.DeInitilizeEdge(t.edgeTo);
                        t.DeInitilizevisit(t.visited);
                        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                        MousePredict = null;
                        MousePredict = b.FindHighestProbCell();
                        Path = null;
                        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                    
                        for(int i = 0; i<(int)((Path.size())) ; i++)
                        {
                            count +=1;
                            Pair next = b.GridLocationOfPath(Path.get(i));
                            //b.UpdateMoveStationaryMice(next);
                            b.MoveBot(next);
                            
                            
                            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                                System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
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
                                    break;
                                }
                                
                            }
    
                            else{
                                t.PrintShip(t.grid);
                            }
                        }
                    }
                }
                else if(MouseFound == 1){
                    if (t.highestProbabilityValue() > 0.7 || bestProbability)
                    {
                        bestProbability = true;
                        
                        //b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                        b.UpdateProbabilitiesMoving(hasBeeped, alpha, 1);
                        t.DeInitilizeEdge(t.edgeTo);
                        t.DeInitilizevisit(t.visited);
                        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                        MousePredict = null;
                        MousePredict = b.FindHighestProbCell();
                        Path = null;
                        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                    
                        for(int i = 0; i<(int)((Path.size())) ; i++)
                        {
                            count +=1;
                            Pair next = b.GridLocationOfPath(Path.get(i));
                            //b.MoveBotStationary(next);
                            b.MoveBot(next);
                            
                            
                            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                                System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
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
                                    break;
                                }
                                
                            }
                            else{
                                t.PrintShip(t.grid);
                            }
                        }
                    }
                }
                if(MouseFound == 1){
                    //b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                    b.UpdateProbabilitiesMoving(hasBeeped, alpha, 1);
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
                for(int i = 0; i<(int)((Path.size()*1.0)*(0.75))+1 ; i++){
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
                        System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
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
                            break;
                        }
                        
                    }
                    else{
                        //t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        //t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        t.PrintShip(t.grid);
                    }
                }
                for(int i = 0; i<(int)((Path.size()*1.0) * (0.3)) + 1; i++){
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
                        if(MouseFound == 0){
                            if (t.highestProbabilityValueMice() > 0.7 || bestProbability)
                            {
                                bestProbability = true;
                                break;
                            }
                        }
                        else if(MouseFound == 1){
                            if (t.highestProbabilityValue() > 0.7 || bestProbability)
                            {
                                bestProbability = true;
                                break;
                            }
                        }
                        if(MouseFound == 1){
                            //b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                            b.UpdateProbabilitiesMoving(hasBeeped, alpha, 1);
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
                            System.out.print("BOT FOUND THE MOUSE. Sense: " +sense+ " Movement: " + count);
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
                                break;
                            }
                            
                        }
                        else{
                            //t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                            //t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                            t.PrintShip(t.grid);
                        }
                    }
                    

                    /* 
                    if(Break){
                        break;
                    }
                        */
                }
                if(Break){
                    break;
                }
            }
        }
        return new Pair(-1,-1);
    }

    public static Pair Bot3TestMovingMiceAVG(double alpha){
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
        boolean bestProbability = false;
        for(int i = 0; i<Path.size(); i++){
            count +=1;
            Pair next = b.GridLocationOfPath(Path.get(i));
            b.MoveBot(next);
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
                    break;
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
                if(MouseFound == 0){
                    if (t.highestProbabilityValueMice() > 0.7 || bestProbability)
                    {
                        bestProbability = true;
                        
                        double [][] temp = null; 
                        temp = b.Replicate();
                        b.UpdateMouse1Grid(hasBeeped, alpha);
                        b.UpdateMouse2Grid(hasBeeped, alpha, temp);
                        b.Normalization(t.MouseGrid1);
                        b.Normalization(t.MouseGrid2);
                        //b.UpdateProbabilitiesMoving(hasBeeped, 0.5, 1);
                        t.DeInitilizeEdge(t.edgeTo);
                        t.DeInitilizevisit(t.visited);
                        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                        MousePredict = null;
                        MousePredict = b.FindHighestProbCell();
                        Path = null;
                        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                    
                        for(int i = 0; i<(int)((Path.size())) ; i++)
                        {
                            count +=1;
                            Pair next = b.GridLocationOfPath(Path.get(i));
                            //b.UpdateMoveStationaryMice(next);
                            b.MoveBot(next);
                            
                            
                            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                                //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
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
                                    break;
                                }
                                
                            }
    
                            else{
                                //t.PrintShip(t.grid);
                            }
                        }
                    }
                }
                else if(MouseFound == 1){
                    if (t.highestProbabilityValue() > 0.7 || bestProbability)
                    {
                        bestProbability = true;
                        
                        //b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                        b.UpdateProbabilitiesMoving(hasBeeped, alpha, 1);
                        t.DeInitilizeEdge(t.edgeTo);
                        t.DeInitilizevisit(t.visited);
                        Bot.bfs(t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.AdjList, t.AdjListPar, t.visited, t.edgeTo);
                        MousePredict = null;
                        MousePredict = b.FindHighestProbCell();
                        Path = null;
                        Path = b.ComputePath(t.edgeTo, t.adjecencyGrid[b.GetBotPos().getKey()][b.GetBotPos().getValue()], t.adjecencyGrid[MousePredict.getKey()][MousePredict.getValue()]);
                    
                        for(int i = 0; i<(int)((Path.size())) ; i++)
                        {
                            count +=1;
                            Pair next = b.GridLocationOfPath(Path.get(i));
                            //b.MoveBotStationary(next);
                            b.MoveBot(next);
                            
                            
                            if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                                //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
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
                                    break;
                                }
                                
                            }
                            else{
                                //t.PrintShip(t.grid);
                            }
                        }
                    }
                }
                if(MouseFound == 1){
                    //b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                    b.UpdateProbabilitiesMoving(hasBeeped, alpha, 1);
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
                for(int i = 0; i<(int)((Path.size()*1.0)*(0.75))+1 ; i++){
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
                            break;
                        }
                        
                    }
                    else{
                        //t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        //t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                        //t.PrintShip(t.grid);
                    }
                }
                for(int i = 0; i<(int)((Path.size()*1.0) * (0.3)) + 1; i++){
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
                        if(MouseFound == 0){
                            if (t.highestProbabilityValueMice() > 0.7 || bestProbability)
                            {
                                bestProbability = true;
                                break;
                            }
                        }
                        else if(MouseFound == 1){
                            if (t.highestProbabilityValue() > 0.7 || bestProbability)
                            {
                                bestProbability = true;
                                break;
                            }
                        }
                        if(MouseFound == 1){
                            //b.UpdateProbabilitiesStationary(hasBeeped, alpha, 1, b.GetCellsTraversed());
                            b.UpdateProbabilitiesMoving(hasBeeped, alpha, 1);
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
                            //System.out.print("BOT FOUND THE MOUSE. Sense: " + sense + " Movement: " + count);
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
                                break;
                            }
                            
                        }
                        else{
                            //t.MouseGrid1[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                            //t.MouseGrid2[b.GetBotPos().getKey()][b.GetBotPos().getValue()] = 0.0;
                            //t.PrintShip(t.grid);
                        }
                    }
                    

                    /* 
                    if(Break){
                        break;
                    }
                        */
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

        //BOT3 MOVING MOUSE TEST VALUES
        /* 
        Pair SMMoving = Bot3TestMovingMouse(0.5);
        System.out.println();
        System.out.print("Bot3 Moving Mouse Average Sense: " + SMMoving.getKey() + " Movement: " +SMMoving.getValue());
        */

        //BOT3 STATIONARY MOUSE TEST VALUES
         
        Pair SenseMovement = Bot3TestStationaryMouse(0.5);
        System.out.println();
        System.out.print("Stationary Bot 3: Total Sensing and Movement values. Sense: " + SenseMovement.getKey() + " Movement: " +SenseMovement.getValue());
        
         
        //BOT3 MOVING MICE TEST VALUES
        /* 
        Pair SMiceMoving = Bot3TestMovingMice(0.5);
        System.out.println();
        System.out.print("Bot3 Moving Mice Average Sense: " + SMiceMoving.getKey() + " Movement: " +SMiceMoving.getValue());
        */

        //BOT3 STATIONARY MICE TEST VALUES
        /* 
        Pair MiceSenseMovement = Bot3TestStationaryMice(0.5);
        System.out.println();
        System.out.print("Bot3 Stationary Mice Sense: " + MiceSenseMovement.getKey() + " Movement: " +MiceSenseMovement.getValue());
        */
        
        //AVERAGE TEST CASES SENSE AND MOVEMENT VALUES 

        //BOT3 STATIONARY MOUSE AVG TEST VALUES

        /* 
        int sense1 = 0;
        int count1 = 0;
        int skip1 = 0;
        for(int i = 0; i< 50; i++){
            Pair SenseMovement1 = Bot3TestStationaryMouseAVG(0.5);
            if(SenseMovement1.getKey() > 1000 || SenseMovement1.getValue()> 1000){
                skip1 +=1;
                continue;
            }
            sense1 += SenseMovement1.getKey();
            count1 += SenseMovement1.getValue();
        }
        double AvgSense1 = (sense1 *1.0)/(50 - skip1);
        double AvgMovement1 = (count1 * 1.0)/ (50-skip1);
        System.out.print("Bot3 Stationary Mouse Average Sense: " + AvgSense1 + " Average Movement: " + AvgMovement1);
    */

        //BOT3 MOVING MOUSE AVG TEST VALUES

        /* 
        int sense2 = 0;
        int count2 = 0;
        int skip2 = 0;
        for(int i = 0; i< 50; i++){
            Pair SenseMovement2 = Bot3TestMovingMouseAVG(0.2);
            if(SenseMovement2.getKey() > 1000 || SenseMovement2.getValue()> 1000){
                skip2 +=1;
                continue;
            }
            sense2+= SenseMovement2.getKey();
            count2 += SenseMovement2.getValue();
        }
        double AvgSense2 = (sense2 *1.0)/(50 - skip2);
        double AvgMovement2 = (count2 * 1.0)/ (50-skip2);
        System.out.print("Bot1 Moving Mouse Average Sense: " + AvgSense2 + " Average Movement: " + AvgMovement2);
        */

        //BOT3 STATIONARY MICE AVG TEST VALUES

        /* 
        int sense3 = 0;
        int count3 = 0;
        int skip3 = 0;
        for(int i = 0; i< 50; i++){
            Pair SenseMovement3 = Bot3TestStationaryMiceAVG(0.2);
            if(SenseMovement3.getKey() > 10000 || SenseMovement3.getValue()> 10000){
                skip3 +=1;
                continue;
            }
            sense3 += SenseMovement3.getKey();
            count3 += SenseMovement3.getValue();
        }
        double AvgSense3 = (sense3 *1.0)/(50 - skip3);
        double AvgMovement3 = (count3 * 1.0)/ (50-skip3);
        System.out.print("Bot3 Stationary Mice Average Sense: " + AvgSense3 + " Average Movement: " + AvgMovement3);
        */

        //BOT3 MOVING MICE AVG TEST VALUES
        /* 
        int sense4 = 0;
        int count4 = 0;
        int skip4 = 0;
        for(int i = 0; i< 50; i++){
            Pair SenseMovement4 = Bot3TestMovingMiceAVG(0.5);
            if(SenseMovement4.getKey() > 100000 || SenseMovement4.getValue()> 10000){
                skip4 +=1;
                continue;
            }
            sense4 += SenseMovement4.getKey();
            count4 += SenseMovement4.getValue();
        }
        double AvgSense4 = (sense4 *1.0)/(50 - skip4);
        double AvgMovement4 = (count4 * 1.0)/ (50-skip4);
        System.out.print("Bot3 Moving Mice Average Sense: " + AvgSense4 + " Average Movement: " + AvgMovement4);
        */
    }
        

}
