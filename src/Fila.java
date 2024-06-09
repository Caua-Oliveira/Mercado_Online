public class Fila<T extends Identificar> {
    private ListaDuplamenteEncadeada<T> fila;

    public Fila() {
        this.fila = new ListaDuplamenteEncadeada<>();
    }

    public void enfileirar(T dado) {
        fila.adicionarFinal(dado);
    }

    public T desenfileirar() {
        if (estaVazia()) {
            throw new IllegalStateException("A fila está vazia");
        }
        T dado = fila.primeiro.dado;
        fila.remover(dado);
        return dado;
    }

    public T primeiro() {
        if (estaVazia()) {
            throw new IllegalStateException("A fila está vazia");
        }
        return fila.primeiro.dado;
    }

    public boolean estaVazia() {
        return fila.taVazia();
    }

    public int tamanho() {
        return fila.contagem();
    }

    @Override
    public String toString() {
        return fila.toString();
    }
}
