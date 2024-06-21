
                    break;
                }
                Pair nextMouseMove = m.PickRandomNeighbor(m.GetMousePos().getKey(), m.GetMousePos().getValue());
                m.MoveMouse(nextMouseMove);
                if(t.grid[b.GetBotPos().getKey()][b.GetBotPos().getValue()].hasMouse()){
                    System.out.print("BOT FOUND THE MOUSE at " + j + count);
                    break;
                }
                else{
                    //t.PrintShip(t.grid);