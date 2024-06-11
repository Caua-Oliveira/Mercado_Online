import java.time.LocalDateTime;

public class Compra implements Identificar {
    String id;
    Cliente.Vendedor vendedor;
    Cliente.Comprador comprador;
    ListaDuplamenteEncadeada<Produto> produtos;
    LocalDateTime data;
    int quantidade;

    public Compra(Cliente.Vendedor vendedor, Cliente.Comprador comprador, ListaDuplamenteEncadeada<Produto> produtos, LocalDateTime data, int quantidade) {
        this.data = data;
        this.id = data.toString();
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
        sb.append("Compra:{\n")
                .append("vendedor = ").append(vendedor.getNomeLoja()).append(",\n")
                .append("comprador = ").append(comprador.getUsername()).append(",\n")
                .append("produtos = \n");

        ListaDuplamenteEncadeada.No<Produto> atual = produtos.primeiro;
        while (atual != null) {
            sb.append(atual.dado.getNome()).append(", ").append(atual.dado.getEstoque()).append("\n");
            atual = atual.proximo;
        }

        sb.append('}');
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id='" + id + '\'' +
                ", vendedor=" + vendedor +
                ", comprador=" + comprador +
                ", produtos=" + produtos +
                '}';
    }
}
