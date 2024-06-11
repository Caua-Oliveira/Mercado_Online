import java.time.LocalDateTime;

public class Compra implements Identificar {
    String id;
    Cliente.Vendedor vendedor;
    Cliente.Comprador comprador;
    Produto produto;
    LocalDateTime data;
    int quantidade;
    Fila<Avaliacao> avaliacoesPendentes;

    public Compra(Cliente.Vendedor vendedor, Cliente.Comprador comprador, Produto produto, LocalDateTime data, int quantidade) {
        this.vendedor = vendedor;
        this.comprador = comprador;
        this.produto = produto;
        this.data = data;
        this.quantidade = quantidade;
        this.id = data.toString(); // Ajuste o ID conforme necessário
        this.avaliacoesPendentes = new Fila<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente.Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Cliente.Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Cliente.Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Cliente.Comprador comprador) {
        this.comprador = comprador;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void solicitarAvaliacao(String comentario, int nota) {
        Avaliacao avaliacao = new Avaliacao(this.comprador, this.produto, comentario, nota);
        this.avaliacoesPendentes.enfileirar(avaliacao);
    }

    public String printarFormatado() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        sb.append("Comprador: ").append(comprador.getUsername()).append("\n");
        sb.append("Vendedor: ").append(vendedor.getUsername()).append("\n");
        sb.append("Produtos: ").append(produto.toString()).append("\n");
        sb.append("Data: ").append(data.toString()).append("\n");
        sb.append("Quantidade: ").append(quantidade).append("\n");
        return sb.toString();
    }

    public void processarAvaliacoesPendentes() {
        while (!this.avaliacoesPendentes.estaVazia()) {
            Avaliacao avaliacao = this.avaliacoesPendentes.desenfileirar();
            System.out.println("Avaliação processada: " + avaliacao);
        }
    }

    @Override
    public String getID() {
        return id;
    }

}
