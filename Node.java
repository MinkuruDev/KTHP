package KTHP;

class Node {
    private int data;
    private Node leftNode, rightNode;

    public Node(int data){
        this.data = data;
        leftNode = rightNode = null;
    }

    public Node(int data, int leftValue, int rightValue){
        this.data = data;
        leftNode = new Node(leftValue);
        rightNode = new Node(rightValue);
    }

    public Node(int data, Node leftNode, Node rightNode){
        this.data = data;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }

    public void setData(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setLeftNode(Node leftNode) {
        this.leftNode = leftNode;
    }

    public void setRightNode(Node rightNode) {
        this.rightNode = rightNode;
    }

    public void setLeftNode(int data){
        leftNode = new Node(data);
    }

    public void setRightNode(int data){
        rightNode = new Node(data);
    }

    public Node getLeftNode() {
        return leftNode;
    }

    public Node getRightNode() {
        return rightNode;
    }

    public Node getRightMost(){
        if (rightNode == null)
            return this;
        return this.rightNode.getRightMost();
    }

}

