public class PilhaDeProdutos {
    static class No {
        Produto produto;
        No proximo;

        No(Produto produto) {
            this.produto = produto;
            this.proximo = null;
        }
    }

    No topo;

    public PilhaDeProdutos() {
        this.topo = null;
    }

    public void empilhar(Produto produto){
        No novoNo = new No(produto);
        if (topo != null) {
            novoNo.proximo = topo;
        }
        topo = novoNo;
    }

    public Produto desempilhar(){
        if (topo != null) {
            Produto produto = topo.produto;
            topo = topo.proximo;
            return produto;
        }
        return null;
    }

    public boolean taVazia() {
        return topo == null;
    }

    public void printarProdutos() {
        No atual = topo;
        while (atual != null) {
            System.out.println(atual.produto);
            atual = atual.proximo;
        }
    }

}