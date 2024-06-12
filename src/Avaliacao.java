public class Avaliacao implements Identificar {
    private Cliente.Comprador comprador;
    private Compra compra;
    private String comentario;
    private int nota;

    public Avaliacao(Cliente.Comprador comprador, Compra compra, String comentario, int nota) {
        this.comprador = comprador;
        this.compra = compra;
        this.comentario = comentario;
        this.nota = nota;
    }

    public Cliente.Comprador getComprador() {
        return comprador;
    }

    public Compra getCompra() {
        return compra;
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
                ", compra=" + compra +
                ", comentario='" + comentario + '\'' +
                ", nota=" + nota +
                '}';
    }

    @Override
    public String getID() {
        return null;
    }
}
