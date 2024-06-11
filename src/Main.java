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
        /*
        Cadastro padrão depois pergunta se quer ser vendedor ou comrpador
        se for vendedor, pergunta nome da loja
        se for comprador pergutna endereço
        */
    }

    private static void logar(Scanner scanner) {
        String email;
        String senha;
        /*
        pergunta o email e senha
        */
        Cliente cliente = autenticar(email, senha);
        //Se cliente for null, é pq a senha ou email ta errado
        
        //Se o cliente for vendedor, mostra o menuVendedor()
        //Se for comprador, mostra o menuComprador()
    }

    private static Cliente autenticar(String username, String senha) {
        //Loopa os nós da variavel |clientes|
        //enquanto nao for nulo, olha se o username e a senha do cliente = aos parametros que esse metodo recebeu
        //se for, retorna o dado, se não retorna nulo
        
    }


    /****
     ** Vendedor
     ****/
    private static void menuVendedor(Cliente.Vendedor vendedor, Scanner scanner) {
        
        while (true) {
            //Mostra opções de cadastrar produto, ver estoque, ver historico de venda e deslogar
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
        //cadastro do produto, pergunta nome descricao etc
        //cria variavel produto com os dados
        //adiciona ao estoque da loja
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
            //mostra opções pra Escolher loja, Realizar compra do carrinho, Ver historico de compras, Avaliar produtos comrpados, Deslogar
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
