// add your imports here

class TSTNode<T extends Comparable<T>>{
    T element;     	            // The data in the node
    TSTNode<T>  left;   		// left child
    TSTNode<T>  mid;   		    // middle child
    TSTNode<T>  right;  		// right child
    
    TSTNode(T element){
        this.element= element;
        this.left =null;
        this.right=null;
        this.mid=null;
    }

    public TSTNode<T> findMax_1(TSTNode<T> node){
        if (node.element == null){
            return null;
        }
        else if(node.right== null){
            return node;
        }
        else {
            return findMax_1(node.right);
        }
    }
    public TSTNode<T> findMin_1(TSTNode<T> node){
        if (node.element == null){
            return null;
        }
        else if(node.left== null){
            return node;
        }
        else {
            return findMin_1(node.left);
        }
    }

    TSTNode<T> findMax(){
        return findMax_1(this);
    }

    TSTNode<T> findMin(){
        return findMin_1(this);
    }

    public int find_height(TSTNode<T> node){
        if (node == null){
            return 0;
        }
        else {
            int h=0;
            int h_left= Math.max(h, find_height(node.left));
            int h_mid =Math.max(h,find_height(node.mid));
            int h_right= Math.max(h,find_height(node.right));
            int height= Math.max(Math.max(h_left,h_mid),h_right);
            return 1+height;
        }
    }

    int height(){
        return find_height(this)-1;
    }

}