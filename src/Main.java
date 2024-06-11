import java.util.Scanner;

public class Main {
    private static ListaDuplamenteEncadeada<Cliente.Vendedor> vendedores = new ListaDuplamenteEncadeada<>();
    private static ListaDuplamenteEncadeada<Cliente.Comprador> compradores = new ListaDuplamenteEncadeada<>();
    private static ListaDuplamenteEncadeada<Cliente> clientes = new ListaDuplamenteEncadeada<>();


    /****
     ** Cadastro/Login
     ****/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Bem-vindo ao sistema Mercado Livre!");
            System.out.println("1. Cadastrar Cliente");
            System.out.println("2. Logar");
            System.out.println("3. Sair");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    cadastrarCliente(scanner);
                    break;
                case 2:
                    logar(scanner);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Escolha inválida!");
            }
        }
    }

    private static void cadastrarCliente(Scanner scanner) {
        System.out.println("Cadastrar como: 1. Vendedor 2. Comprador");
        int tipo = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Email:");
        String email = scanner.nextLine();
        System.out.println("Senha:");
        String senha = scanner.nextLine();

        if (tipo == 1) {
            System.out.println("Nome da loja:");
            String nomeLoja = scanner.nextLine();
            Cliente.Vendedor vendedor = new Cliente.Vendedor(username, email, senha, nomeLoja);
            vendedores.adicionarFinal(vendedor);
            clientes.adicionarFinal(vendedor);
        } else if (tipo == 2) {
            System.out.println("Endereco:");
            String endereco = scanner.nextLine();
            Cliente.Comprador comprador = new Cliente.Comprador(username, email, senha, endereco);
            compradores.adicionarFinal(comprador);
            clientes.adicionarFinal(comprador);
        } else {
            System.out.println("Tipo inválido!");
        }
    }

    private static void logar(Scanner scanner) {
        System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Senha:");
        String senha = scanner.nextLine();

        Cliente cliente = autenticar(username, senha);
        if (cliente == null) {
            System.out.println("Usuário ou senha inválidos!");
            return;
        }

        if (cliente instanceof Cliente.Vendedor) {
            menuVendedor((Cliente.Vendedor) cliente, scanner);
        } else if (cliente instanceof Cliente.Comprador) {
            menuComprador((Cliente.Comprador) cliente, scanner);
        }
    }

    private static Cliente autenticar(String username, String senha) {
        ListaDuplamenteEncadeada.No<Cliente> atual = clientes.primeiro;
        while (atual != null) {
            if (atual.dado.getUsername().equals(username) && atual.dado.getSenha().equals(senha)) {
                return atual.dado;
            }
            atual = atual.proximo;
        }
        return null;
    }


    /****
     ** Vendedor
     ****/
    private static void menuVendedor(Cliente.Vendedor vendedor, Scanner scanner) {
        while (true) {
            System.out.println("Bem-vindo, " + vendedor.getUsername() + "!");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Ver Estoque");
            System.out.println("3. Ver Histórico de Vendas");
            System.out.println("4. Deslogar");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    cadastrarProduto(vendedor, scanner);
                    break;
                case 2:
                    verEstoque(vendedor);
                    break;
                case 3:
                    verHistoricoVendas(vendedor);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Escolha inválida!");
            }
        }
    }

    private static void cadastrarProduto(Cliente.Vendedor vendedor, Scanner scanner) {
        System.out.println("Nome do produto:");
        String nome = scanner.nextLine();
        System.out.println("Descrição:");
        String descricao = scanner.nextLine();
        System.out.println("Categoria:");
        String categoria = scanner.nextLine();
        System.out.println("Preço:");
        double preco = scanner.nextDouble();
        System.out.println("Estoque:");
        int estoque = scanner.nextInt();
        scanner.nextLine();

        Produto produto = new Produto(nome, descricao, categoria, preco, estoque);
        vendedor.lojaEstoque.adicionarFinal(produto);
    }

    private static void verEstoque(Cliente.Vendedor vendedor) {
        System.out.println("Estoque de produtos:");
        vendedor.getLojaEstoque().printarOrdem();
    }

    private static void verHistoricoVendas(Cliente.Vendedor vendedor) {
        System.out.println("Histórico de Vendas:");
        vendedor.getHistoricoVendas().printarHistorico();
    }


    /****
     ** Comprador
     ****/
    private static void menuComprador(Cliente.Comprador comprador, Scanner scanner) {
        while (true) {
            System.out.println("Bem-vindo, " + comprador.getUsername() + "!");
            System.out.println("1. Escolher Loja");
            System.out.println("2. Realizar Compra do Carrinho");
            System.out.println("3. Ver Histórico de Compras");
            System.out.println("4. Avaliar Produtos Comprados");
            System.out.println("5. Deslogar");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    escolherLoja(comprador, scanner);
                    break;
                case 2:
                    realizarCompra(comprador);
                    break;
                case 3:
                    verHistoricoCompras(comprador);
                    break;
                case 4:
                    avaliarProdutos(comprador, scanner);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Escolha inválida!");
            }
        }
    }

    private static void escolherLoja(Cliente.Comprador comprador, Scanner scanner) {
        System.out.println("Lojas disponíveis:");
        /*Ideia:
        -Mostra o nome de todas as lojas registradas
        -Entra em um loop em que o usuario poder digitar o nome da loja que quer ou "voltar"
        -Quando escolher a loja mostra o estoque dela
        -Entra em um loop para escolher quais produtos adicionar no carrinho ou voltar
        */
    }

    private static void realizarCompra(Cliente.Comprador comprador) {
        System.out.println("Realizar compra:");
        /*Ideia:
        -Cria uma variavel da classe |Compra|
        -Remove todos os produtos do carrinho
        -Adiciona a variavel |Compra| no historico de compras do cliente
        */
    }

    private static void verHistoricoCompras(Cliente.Comprador comprador) {
        System.out.println("Histórico de compras:");
        comprador.getHistoricoCompras().printarHistorico();
    }

    private static void avaliarProdutos(Cliente.Comprador comprador, Scanner scanner) {
        System.out.println("Avaliar produtos comprados:");
        /*Ideia:
        -Vai no historico de compras do cliente e mostra os produtos de cada compra
        -O usuario escolhe qual produto quer avaliar
        -Loop para criar avaliação
        -Adiciona a avaliação na fila de avaliações do produto
        */

    }
}
