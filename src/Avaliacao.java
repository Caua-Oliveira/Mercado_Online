public class Avaliacao implements Identificar {
    private Cliente.Comprador comprador;
    private Produto produto;
    private String comentario;
    private int nota;

    public Avaliacao(Cliente.Comprador comprador, Produto produto, String comentario, int nota) {
        this.comprador = comprador;
        this.produto = produto;
        this.comentario = comentario;
        this.nota = nota;
    }

    public Cliente.Comprador getComprador() {
        return comprador;
    }

    public Produto getProduto() {
        return produto;
    }

    public String getComentario() {
        return comentario;
    }

    public int getNota() {
        return nota;
    }

    @Override
    public String toString() {
        return "Avaliação{" +
                "comprador=" + comprador +
                ", produto=" + produto +
                ", comentario='" + comentario + '\'' +
                ", nota=" + nota +
                '}';
    }

    @Override
    public String getID() {
        return null;
    }
}
