import java.util.Scanner;

public class Cliente implements Identificar {
    String id;
    String username;
    String email;
    String senha;

    public Cliente(String username, String email, String senha) {
        this.username = username;
        this.email = email;
        this.senha = senha;
        this.id = username;
    }
/****
 * Methods
****/
    @Override
    public String getID() {
        return id;
    }
    public void setID(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String user) {
        this.username = user;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean ehVendedor() {
        return this instanceof Vendedor;
    }
    public boolean ehComprador() {
        return this instanceof Comprador;
    }

/****
 * Subclasses
 ****/
    public static class Vendedor extends Cliente {
        private String nomeLoja;
        private static ListaDuplamenteEncadeada<Vendedor> vendedores = new ListaDuplamenteEncadeada<>();
        private PilhaDeProdutos lojaEstoque = new PilhaDeProdutos();        
        public ArvoreDeCompra<Compra> historicoCompras = new ArvoreDeCompra<>();

        public Vendedor(String username, String email, String senha, String nomeLoja) {
            super(username, email, senha);
            this.nomeLoja = nomeLoja;
        }

        public static void listarLojas() {
            ListaDuplamenteEncadeada.No<Vendedor> atual = vendedores.primeiro;
            while (atual != null) {
                System.out.println(atual.dado.getNomeLoja());
                atual = atual.proximo;
            }
        }

        public static Vendedor escolherLoja(String nomeLoja) {
            ListaDuplamenteEncadeada.No<Vendedor> atual = vendedores.primeiro;
            while (atual != null) {
                if (atual.dado.getNomeLoja().equals(nomeLoja)) {
                    return atual.dado;
                }
                atual = atual.proximo;
            }
            return null;
        }

        public void solicitarAvaliacao(Avaliacao avaliacao) {
            avaliacoesPendentes.enfileirar(avaliacao);
        }

        public void getLojaEstoque() {
            lojaEstoque.printarProdutos();

        }
        public void getHistoricoCompras() {
            historicoCompras.printarHistorico();
        }


    public String getNomeLoja() {
            return nomeLoja;
        }
        public void setNomeLoja(String nomeLoja) {
            this.nomeLoja = nomeLoja;
        }

        @Override
        public String toString() {
            return "Client{" +
                    "username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    ", senha='" + senha + '\'' +
                    ", nome da loja='" + nomeLoja + '\'' +
                    ", tipo='" + this.getClass().getSimpleName() + '\'' +
                    '}';
        }
    }

    public static class Comprador extends Cliente {
        private Compra ultimaCompra;
        private String endereco;
        public Comprador(String username, String email, String password, String address) {
            super(username, email, password);
            this.endereco = address;
        }

        public PilhaDeProdutos carrinho = new PilhaDeProdutos();
        public ArvoreDeCompra<Compra> historicoCompras = new ArvoreDeCompra<>();

        public void setUltimaCompra(Compra ultimaCompra) {
            this.ultimaCompra = ultimaCompra;
        }
        public Compra getUltimaCompra() {
            return ultimaCompra;
        }

        public String getEndereco() {
            return endereco;
        }
        public void setEndereco(String endereco) {
            this.endereco = endereco;
        }

        public void solicitarAvaliacao() {
        if (ultimaCompra != null) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Por favor, avalie o produto:");
            System.out.print("Comentário: ");
            String comentario = scanner.nextLine();

            System.out.print("Nota (de 1 a 5): ");
            int nota = scanner.nextInt();
            scanner.nextLine();
            scanner.close();

            Avaliacao avaliacao = new Avaliacao(this, ultimaCompra.getProduto(), comentario, nota);
            ultimaCompra.getVendedor().solicitarAvaliacao(avaliacao);

            System.out.println("Avaliação realizada com sucesso!");
        } else {
            System.out.println("Você não fez nenhuma compra para avaliar.");
        }
        }
        
        @Override
        public String toString() {
            return "Client{" +
                    "username='" + username + '\'' +
                    ", email='" + email + '\'' +
                    ", senha='" + senha + '\'' +
                    ", endereco='" + endereco + '\'' +
                    ", tipo='" + this.getClass().getSimpleName() + '\'' +
                    '}';
        }
    }

}
