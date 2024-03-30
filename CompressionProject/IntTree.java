package CompressionProject;

public class IntTree {
    private IntTree left;
    private IntTree right;
    private boolean _data;
    public IntTree(boolean data){   
        _data = data;
    }

    public boolean getData(){
        return _data;
    }

    public void addLeft(IntTree other){
        left = other;
    }

    public void addRight(IntTree other){
        right = other;
    }

    public IntTree getLeft(){
        return left;
    }
    
    public IntTree getRight(){
        return right;
    }
}
