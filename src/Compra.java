import java.time.LocalDateTime;

public class Compra implements Identificar {
    String id;
    Cliente.Vendedor vendedor;
    Cliente.Comprador comprador;
    PilhaDeProdutos produtos;
    LocalDateTime data;
    Fila<Avaliacao> avaliacao;

    public Compra(Cliente.Vendedor vendedor, Cliente.Comprador comprador, PilhaDeProdutos produtos, LocalDateTime data) {
        this.data = data;
        this.id = data.toString();
        this.vendedor = vendedor;
        this.comprador = comprador;
        this.produtos = produtos;
        this.avaliacao = new Fila<>();
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

        PilhaDeProdutos.No atual = produtos.topo;
        while (atual != null) {
            sb.append(atual.produto.getID()).append(", ").append(atual.produto.getEstoque()).append("\n");
            atual = atual.proximo;
        }
        sb.append("avaliacao =  ").append(avaliacao.toString()).append("\n");
        sb.append('}');
        return sb.toString();
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id='" + id + '\'' +
                ",\n vendedor=" + vendedor +
                ",\n comprador=" + comprador +
                ",\n produtos=" + produtos +
                ",\n avaliacao=" + avaliacao +
                '}';
    }
}
