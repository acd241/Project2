public class Pair {
    private int key;
    private int value;

    public Pair(int Key, int Value){
        this.key   = Key;
        this.value = Value;
    }

    public Pair(){
        this.key = -2;
        this.value = -2;
    }

    public int getKey()   { 
        return key; 
    }
    public int getValue() { 
        return value; 
    }

    public void setKey(int i){
        this.key = i;
    }

    public void setValue(int v){
        this.value = v;
    }

    public boolean isSame(Pair p1){
        if(p1.getKey() == this.getKey() && p1.getValue() == this.getValue()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isSame(int i, int j){
        if(i == this.getKey() && j == this.getValue()){
            return true; 
        }
        else{
            return false;
        }
    }
}
