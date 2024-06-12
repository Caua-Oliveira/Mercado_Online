public class Produto implements Identificar {
    String id;
    String nome;
    String descricao;
    String categoria;
    double preco;
<<<<<<< Updated upstream
=======
    int estoque;
>>>>>>> Stashed changes

    public Produto(String nome, String descricao, String categoria, double preco) {
        this.nome = nome;
        this.descricao = descricao;
        this.categoria = categoria;
        this.preco = preco;
        this.id = nome;
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

<<<<<<< Updated upstream

=======
>>>>>>> Stashed changes
    @Override
    public String toString() {
        return "Product{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", preco=" + preco +
                '}';
    }


}
