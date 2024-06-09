public class Produto implements Identificar {
    String id;
    String nome;
    String descricao;
    String categoria;
    double preco;
    int estoque;
    int totalAvaliacoes;
    double mediaAvaliacoes;

    public Produto(String nome, String descricao, String categoria, double preco, int estoque) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
        this.estoque = estoque;
        this.id = nome;
        this.mediaAvaliacoes = 0.0;
        this.totalAvaliacoes = 0;
    }

    @Override
    public String getID() {
        return id;
    }
    public void setID(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoque() {
        return estoque;
    }
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getMediaAvaliacoes() {
        return mediaAvaliacoes;
    }
    public int getTotalAvaliacoes() {
        return totalAvaliacoes;
    }
    public void avaliar(Avaliacao avaliacao) {
        double mediaAtualizada = ((mediaAvaliacoes * totalAvaliacoes) + avaliacao.getNota()) / (totalAvaliacoes + 1);
        this.mediaAvaliacoes = mediaAtualizada;
        this.totalAvaliacoes++;
    }

    @Override
    public String toString() {
        return "Product{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", preco=" + preco +
                ", estoque=" + estoque +
                '}';
    }


}
