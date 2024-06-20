public class Main {
    public static void main(String args[]){
        Shiptest t = new Shiptest();
        Bot b = new Bot(t);
        Mouse m = new Mouse(t);
        t.PrintShip(t.grid);
    }
    
}
