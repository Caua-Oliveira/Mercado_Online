public class HistoricoDeCompra implements Identificar {
    String id;
    Cliente.Vendedor vendedor;
    Cliente.Comprador comprador;
    ListaDuplamenteEncadeada<Produto> produtos;

    public HistoricoDeCompra(Cliente.Vendedor vendedor, Cliente.Comprador comprador, ListaDuplamenteEncadeada<Produto> produtos) {
        this.id = vendedor.getID() + "-" + comprador.getID();
        this.vendedor = vendedor;
        this.comprador = comprador;
        this.produtos = produtos;
    }

    @Override
    public String getID() {
        return id;
    }

    public String printarFormatado() {
        StringBuilder sb = new StringBuilder();
        sb.append("PurchaseRecord{\n")
                .append("seller = ").append(vendedor.getNomeLoja()).append(",\n")
                .append("consumer = ").append(comprador.getUsername()).append(",\n")
                .append("products = \n");

        ListaDuplamenteEncadeada.No<Produto> atual = produtos.primeiro;
        while (atual != null) {
            sb.append(atual.dado.getNome()).append(", ").append(atual.dado.getQuantidade()).append("\n");
            atual = atual.proximo;
        }

        sb.append('}');
        return sb.toString();
    }

    @Override
    public String toString() {
        return "PurchaseRecord{" +
                "id='" + id + '\'' +
                ", seller=" + vendedor +
                ", consumer=" + comprador +
                ", products=" + produtos +
                '}';
    }
}
