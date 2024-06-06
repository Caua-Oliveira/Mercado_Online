public class Cliente implements Identificar {
    String id;
    String username;
    String email;
    String senha;
    double saldo = 0;

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

    public double getSaldo() {
        return saldo;
    }
    public void setSaldo(float saldo) {
        this.saldo = saldo;
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
        public Vendedor(String username, String email, String senha, String nomeLoja) {
            super(username, email, senha);
            this.nomeLoja = nomeLoja;
        }

        public ListaDuplamenteEncadeada<Produto> lojaEstoque = new ListaDuplamenteEncadeada<>();

        //TODO: HISTORICO DE COMPRA: TROCAR LISTA POR ARVORE (VENDEDOR)
        public ListaDuplamenteEncadeada<HistoricoDeCompra> historicoCompras = new ListaDuplamenteEncadeada<>();

        public void getLojaEstoque() {
            lojaEstoque.printarOrdem();

        }
        public void getHistoricoCompras() {
            historicoCompras.printarOrdem();
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
        private String endereco;
        public Comprador(String username, String email, String password, String adress) {
            super(username, email, password);
            this.endereco = adress;
        }

        public PilhaDeProdutos carrinho = new PilhaDeProdutos();

        //TODO: HISTORICO DE COMPRA: TROCAR LISTA POR ARVORE (COMPRADOR)
        public ListaDuplamenteEncadeada<HistoricoDeCompra> historicoCompras = new ListaDuplamenteEncadeada<>();

        public String getEndereco() {
            return endereco;
        }
        public void setEndereco(String endereco) {
            this.endereco = endereco;
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
