import java.util.Scanner;
import java.time.LocalDateTime;

public class Main {
    private static ListaDuplamenteEncadeada<Cliente.Vendedor> vendedores = new ListaDuplamenteEncadeada<>();
    private static ListaDuplamenteEncadeada<Cliente.Comprador> compradores = new ListaDuplamenteEncadeada<>();
    private static ListaDuplamenteEncadeada<Cliente> clientes = new ListaDuplamenteEncadeada<>();
    private static Cliente.Vendedor lojaEscolhida;

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
                    System.out.println("Escolha inválida! ");
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
            System.out.println("4. Deslogar");
            int escolha = scanner.nextInt();
            scanner.nextLine();

            switch (escolha) {
                case 1:
                    escolherLoja(comprador, scanner);
                    break;
                case 2:
                    realizarCompra(comprador, scanner);
                    break;
                case 3:
                    verHistoricoCompras(comprador);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Escolha inválida!");
            }
        }
    }

    private static void escolherLoja(Cliente.Comprador comprador, Scanner scanner) {
        if (!comprador.carrinho.taVazia()){
            System.out.println("Seu carrinho ja está cheio");
            return;
        }
        boolean sair = false;
        while (true) {
            if (sair){
                sair = false;
                break;
            }
            System.out.println("Lojas disponíveis:");
            ListaDuplamenteEncadeada.No<Cliente.Vendedor> atual = vendedores.primeiro;
            while (atual != null) {
                System.out.println(atual.dado.getNomeLoja());
                atual = atual.proximo;
            }

            System.out.println("Digite o nome da loja que você quer visualizar ou 'voltar' para retornar ao menu anterior:");
            String nomeLoja = scanner.nextLine();

            if (nomeLoja.equalsIgnoreCase("voltar")) {
                break;
            }

            Cliente.Vendedor vendedorEscolhido = null;
            atual = vendedores.primeiro;
            while (atual != null) {
                if (atual.dado.getNomeLoja().equalsIgnoreCase(nomeLoja)) {
                    vendedorEscolhido = atual.dado;
                    break;
                }
                atual = atual.proximo;
            }

            if (vendedorEscolhido == null) {
                System.out.println("Loja não encontrada! Tente novamente.");
                continue;
            }
            lojaEscolhida = vendedorEscolhido;
            while (true) {
                System.out.println("Estoque de " + vendedorEscolhido.getNomeLoja() + ":");
                vendedorEscolhido.getLojaEstoque().printarOrdem();

                System.out.println("Digite o nome do produto que deseja adicionar ao carrinho ou 'voltar':");
                String nomeProduto = scanner.nextLine();

                if (nomeProduto.equalsIgnoreCase("voltar")) {
                    sair = true;
                    break;
                }

                Produto produtoEscolhido = null;
                ListaDuplamenteEncadeada.No<Produto> produtoAtual = vendedorEscolhido.getLojaEstoque().primeiro;
                while (produtoAtual != null) {
                    if (produtoAtual.dado.getNome().equalsIgnoreCase(nomeProduto)) {
                        produtoEscolhido = produtoAtual.dado;
                        break;
                    }
                    produtoAtual = produtoAtual.proximo;
                }

                if (produtoEscolhido == null) {
                    System.out.println("Produto não encontrado! Tente novamente.");
                    continue;
                }

                comprador.carrinho.empilhar(produtoEscolhido);
                System.out.println("Produto adicionado ao carrinho.");
            }
        }
    }

    private static void realizarCompra(Cliente.Comprador comprador, Scanner scanner) {
        if (comprador.carrinho.taVazia()) {
            System.out.println("Seu carrinho está vazio!");
            return;
        }

        LocalDateTime agora = LocalDateTime.now();
        Compra novaCompra = new Compra(lojaEscolhida, comprador, comprador.carrinho, agora);

        comprador.getHistoricoCompras().inserir(novaCompra);
        lojaEscolhida.getHistoricoVendas().inserir(novaCompra);

        while(comprador.carrinho.topo!=null){
            comprador.carrinho.desempilhar();
        }
        System.out.println("Compra realizada com sucesso!");
        avaliarProdutos(comprador, novaCompra, scanner);
    }


    private static void verHistoricoCompras(Cliente.Comprador comprador) {
        System.out.println("Histórico de compras:");
        comprador.getHistoricoCompras().printarHistorico();
    }

    private static void avaliarProdutos(Cliente.Comprador comprador, Compra compra, Scanner scanner) {
        System.out.println("Avaliar produtos comprados:");
        System.out.print("Você gostaria de avaliar o produto? (s/n): ");
        String resposta = scanner.nextLine();

        if (resposta.equalsIgnoreCase("s")) {
            System.out.print("Digite uma nota (0 a 5): ");
            int nota = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Digite um comentário: ");
            String comentario = scanner.nextLine();

            compra.avaliacao.enfileirar(new Avaliacao(comprador, compra, comentario, nota));

            System.out.println("Obrigado pela sua avaliação!");
        } else {
            System.out.println("Obrigado pela sua compra!");
        }
    }

}

