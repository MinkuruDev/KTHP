package KTHP;
import java.util.Arrays;

public class BinarySearchTree {
    protected Node root;
    protected int size;

    public BinarySearchTree(){
        root = null;
        size = 0;
    }

    public BinarySearchTree(int[] arr){
        buildTree(arr);
    }

    protected void buildTree(int[] arr){
        root = null;
        System.gc();
        size = 0;
        Arrays.sort(arr);
        buildFromSortedArray(arr, 0, arr.length - 1);
    }

    protected void buildFromSortedArray(int[] arr, int l, int r){
        if(l > r) return;
        int mid = (l + r) / 2;
        insert(arr[mid]);
        buildFromSortedArray(arr, l, mid - 1);
        buildFromSortedArray(arr, mid + 1, r);
    }

    public Node getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    public int getHeight(){
        return getHeight(root);
    }

    protected int getHeight(Node current){
        if(current == null) return 0;
        return Math.max(getHeight(current.getLeftNode()), getHeight(current.getRightNode())) + 1;
    }

    /**
     * Insert a value into BST
     * @param data - value need to insert
     * @return - {@code true} if insert success
     */
    public boolean insert(int data){
        Node focusNode = root;
        if(focusNode == null){
            root = new Node(data);
            size++;
            return true;
        }

        while(true){
            if(focusNode.getData() == data)
                return false;
            
            if(data < focusNode.getData()){
                if(focusNode.getLeftNode() == null){
                    focusNode.setLeftNode(data);
                    size++;
                    return true;
                }
                focusNode = focusNode.getLeftNode();
            }else{
                if(focusNode.getRightNode() == null){
                    focusNode.setRightNode(data);
                    size++;
                    return true;
                }
                focusNode = focusNode.getRightNode();
            }
        }
    }

    /**
     * Check if a value is current in BST
     * @param data - value need to check
     * @return - true if value in BST
     */
    public boolean contains(int data){
        if(root == null) return false;

        Node focusNode = root;

        while(focusNode != null){
            if(focusNode.getData() == data)
                return true;
            
            if(data < focusNode.getData())
                focusNode = focusNode.getLeftNode();
            else
                focusNode = focusNode.getRightNode();
        }

        return false;
    }

    /**
     * Delete a value in BST
     * @param value - value to delete
     * @return - true if deleted
     */
    public boolean delete(int value){
        int oldSize = size;
        root = delete(root, value);
        return oldSize > size || oldSize == 0;
    }

    protected Node delete(Node focusNode, int value){
        if(focusNode == null) return null;
    
        if(value < focusNode.getData()){
            focusNode.setLeftNode(delete(focusNode.getLeftNode(), value));
        }else if(focusNode.getData() < value){
            focusNode.setRightNode(delete(focusNode.getRightNode(), value));
        }else{ // got the right node
            if(focusNode.getLeftNode() == null || focusNode.getRightNode() == null){
                // have only one child or leaf
                if(focusNode.getLeftNode() != null)
                    focusNode = focusNode.getLeftNode();
                else 
                    focusNode = focusNode.getRightNode();
                size--;
            }else{
                int theChoosen = focusNode.getLeftNode().getRightMost().getData();
                delete(focusNode, theChoosen);
                focusNode.setData(theChoosen);
            }
        }
        return focusNode;
    }

    /**
     * Get all value in tree in order
     * @return
     */
    public int[] inOrderTraversal(){
        if(root == null) return null;
        int[] res = new int[size];
        inOrderTraversal(root, 0, res);
        return res;
    }

    protected int inOrderTraversal(Node current, int i, int[] arr){
        if(current == null) return i;

        i = inOrderTraversal(current.getLeftNode(), i, arr);
        arr[i] = current.getData();
        i++;
        return inOrderTraversal(current.getRightNode(), i, arr);
    }

    protected void preOrderTraversal(Node current){
        if(current == null) return;
        System.out.print(current.getData() + " ");
        preOrderTraversal(current.getLeftNode());
        preOrderTraversal(current.getRightNode());
    }

    public void balancing(){
        buildTree(inOrderTraversal());
    }
}
