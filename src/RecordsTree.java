public class RecordsTree {
    static class Node {
        HistoricoDeCompra data;
        Node left;
        Node right;

        Node(HistoricoDeCompra data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
    private Node root;

    public RecordsTree(){
        this.root=null;
    }

    public boolean isEmpty(Node root){
        return root == null;
    }
    public Node addRecursive(Node current, HistoricoDeCompra pRecord){
        if (current == null){
            return new Node(pRecord);
        }

        if (pRecord.produtos.contagem() <= current.data.produtos.contagem()){
            current.left = addRecursive(current.left, pRecord);
        }
        else if(pRecord.produtos.contagem() > current.data.produtos.contagem()){
            current.right = addRecursive(current.right, pRecord);
        }

        return current;
    }



}
