public class ListaDuplamenteEncadeada<T extends Identificar> {
    static class No<T> {
        T dado;
        No<T> proximo;
        No<T> anterior;

        No(T dado) {
            this.dado = dado;
            this.proximo = null;
            this.anterior = null;
        }
    }

    No<T> primeiro;
    No<T> ultimo;

    public ListaDuplamenteEncadeada() {
        this.primeiro = null;
        this.ultimo = null;
    }

    public void adicionarInicio(T dado) {
        No<T> novoNo = new No<>(dado);
        if (taVazia()) {
            primeiro = ultimo = novoNo;
        } else {
            novoNo.proximo = primeiro;
            primeiro.anterior = novoNo;
            primeiro = novoNo;
        }
    }

    public void adicionarFinal(T dado) {
        No<T> novoNo = new No<>(dado);
        if (taVazia()) {
            primeiro = ultimo = novoNo;
        } else {
            novoNo.anterior = ultimo;
            ultimo.proximo = novoNo;
            ultimo = novoNo;
        }
    }

    public boolean remover(T dado) {
        No<T> atual = primeiro;
        while (atual != null) {
            if (atual.dado.equals(dado)) {
                if (atual.anterior != null) {
                    atual.anterior.proximo = atual.proximo;
                } else {
                    primeiro = atual.proximo;
                }
                if (atual.proximo != null) {
                    atual.proximo.anterior = atual.anterior;
                } else {
                    ultimo = atual.anterior;
                }
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public boolean contem(T dado) {
        No<T> atual = primeiro;
        while (atual != null) {
            if (atual.dado.equals(dado)) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public T getDado(String id) {
        No<T> atual = primeiro;
        while (atual != null) {
            if (atual.dado.getID().equals(id)) {
                return atual.dado;
            }
            atual = atual.proximo;
        }
        return null;
    }
    public void printarContrario() {
        No<T> atual = ultimo;
        while (atual != null) {
            System.out.println(atual.dado);
            atual = atual.anterior;
        }
        System.out.println();
    }

    public void printarOrdem() {
        No<T> atual = primeiro;
        while (atual != null) {
            System.out.println(atual.dado);
            atual = atual.proximo;
        }
        System.out.println();
    }

    public void printarPorTipo(String tipo) {
        No<T> atual = primeiro;
        while (atual != null) {
            if (atual.dado.getClass().getSimpleName().equals(tipo)) {
                System.out.println(atual.dado);
            }
            atual = atual.proximo;
        }
        System.out.println();
    }

    public int contagem() {
        No<T> atual = primeiro;
        int quantia = 0;
        while (atual != null) {
            quantia++;
            atual = atual.proximo;
        }
        return quantia;
    }

    public boolean taVazia() {
        return primeiro == null;
    }

}