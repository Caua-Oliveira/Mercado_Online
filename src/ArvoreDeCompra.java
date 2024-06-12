public class ArvoreDeCompra<T extends Identificar> {
    private static class No<T> {
        Compra compra;
        No<T> left, right;

        private No(Compra compra) {
            this.compra = compra;
            this.left = this.right = null;
        }
    }

    private No<T> raiz;

    public ArvoreDeCompra() {
        this.raiz = null;
    }

    public void inserir(Compra compra) {
        this.raiz = inserirRecursivo(this.raiz, compra);
    }

    private No<T> inserirRecursivo(No<T> raiz, Compra compra) {
        if (raiz == null) {
            return new No<>(compra);
        }

        if (compra.getID().compareTo(raiz.compra.getID()) < 0) {
            raiz.left = inserirRecursivo(raiz.left, compra);
        } else if (compra.getID().compareTo(raiz.compra.getID()) > 0) {
            raiz.right = inserirRecursivo(raiz.right, compra);
        }

        return raiz;
    }

    public void printarHistorico() {
        printarHistoricoRecursivo(raiz);
    }

    private void printarHistoricoRecursivo(No<T> raiz) {
        if (raiz != null) {
            printarHistoricoRecursivo(raiz.left);
            System.out.println(raiz.compra.printarFormatado());
            System.out.println(raiz.compra.toString());
            printarHistoricoRecursivo(raiz.right);
        }
    }
}
