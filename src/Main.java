import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    private static ListaDuplamenteEncadeada<Cliente.Comprador> compradores = new ListaDuplamenteEncadeada<>();
    private static ListaDuplamenteEncadeada<Cliente.Vendedor> vendedores = new ListaDuplamenteEncadeada<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Bem-vindo ao Mercado Online!");
    
        int escolha;
        do {
            System.out.println("1 - Entrar como Comprador");
            System.out.println("2 - Entrar como Vendedor");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após o número
    
            if (escolha == 1) {
                String[] loginDados = login(scanner);
                Cliente.Comprador comprador = fazerLoginComprador(loginDados[0], loginDados[1]);
    
                if (comprador != null) {
                    System.out.println("Login como Comprador realizado com sucesso!");
                    // Escolha de Loja
                    Cliente.Vendedor loja = Cliente.Vendedor.escolherLoja();             
                    // Escolha de Produto
                    PilhaDeProdutos produtos = loja.getLojaEstoque();
                    Produto produtoEscolhido = escolherProduto(produtos);
                    // Processar compra e avaliar
                    processarCompra(loja, comprador, produtoEscolhido);
                } else {
                    System.out.println("Usuário não encontrado. Deseja se cadastrar? (S/N)");
                    String resposta = scanner.nextLine().trim().toUpperCase();
                    if (resposta.equals("S")) {
                        String[] dados = cadastro(escolha, scanner);
                        cadastrarComprador(dados[0], dados[1], dados[2], dados[3]);
                        System.out.println("Cadastro realizado com sucesso. Faça o login novamente.");
                    }
                }
            } else if (escolha == 2) {
                String[] loginDados = login(scanner);
                Cliente.Vendedor vendedor = fazerLoginVendedor(loginDados[0], loginDados[1]);
    
                if (vendedor != null) {
                    System.out.println("Login como Vendedor realizado com sucesso!");
                    // Implemente as ações do vendedor aqui
                } else {
                    System.out.println("Usuário não encontrado. Deseja se cadastrar? (S/N)");
                    String resposta = scanner.nextLine().trim().toUpperCase();
                    if (resposta.equals("S")) {
                        String[] dados = cadastro(escolha, scanner);
                        cadastrarVendedor(dados[0], dados[1], dados[2], dados[3]);
                        System.out.println("Cadastro realizado com sucesso. Faça o login novamente.");
                    }
                }
            } else if (escolha != 0) {
                System.out.println("Opção inválida. Por favor, escolha entre as opções apresentadas.");
            }
    
        } while (escolha != 0);
    
        scanner.close();    
    }

    public static String[] cadastro(int i, Scanner scanner) {
        System.out.print("Digite seu username: ");
        String username = scanner.nextLine();
        System.out.print("Digite seu e-mail: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        if (i == 1) {
            System.out.print("Digite seu Endereço: ");
            String address = scanner.nextLine();
            return new String[]{username, email, senha, address};
        } else {
            System.out.print("Digite o nome da sua loja: ");
            String nomeLoja = scanner.nextLine();
            return new String[]{username, email, senha, nomeLoja};
        }
    }    

    public static String[] login(Scanner scanner) {
        System.out.print("Digite seu username: ");
        String username = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();
        return new String[]{username, senha};
    }
    

    public static void cadastrarComprador(String username, String email, String senha, String address) {
        Cliente.Comprador comprador = new Cliente.Comprador(username, email, senha, address);
        compradores.adicionarFinal(comprador);
    }

    public static void cadastrarVendedor(String username, String email, String senha, String nomeLoja) {
        Cliente.Vendedor vendedor = new Cliente.Vendedor(username, email, senha, nomeLoja);
        vendedores.adicionarFinal(vendedor);
    }

    public static Cliente.Comprador fazerLoginComprador(String username, String senha) {
        ListaDuplamenteEncadeada.No<Cliente.Comprador> atual = compradores.primeiro;
        while (atual != null) {
            if (atual.dado.getUsername().equals(username) && atual.dado.getSenha().equals(senha)) {
                return atual.dado;
            }
            atual = atual.proximo;
        }
        return null; // usuario não encontrado
    }

    public static Cliente.Vendedor fazerLoginVendedor(String username, String senha) {
        ListaDuplamenteEncadeada.No<Cliente.Vendedor> atual = vendedores.primeiro;
        while (atual != null) {
            if (atual.dado.getUsername().equals(username) && atual.dado.getSenha().equals(senha)) {
                return atual.dado;
            }
            atual = atual.proximo;
        }
        return null; // usuario não encontrado
    }

    public static Produto escolherProduto(PilhaDeProdutos produtos) {
        Scanner scanner = new Scanner(System.in);
    
        // Mostra a lista de produtos disponíveis
        System.out.println("Produtos disponíveis:");
        produtos.printarProdutos();
    
        // Pede ao usuário para escolher um produto
        System.out.print("Escolha o nome do produto que deseja comprar: ");
        String nomeProdutoEscolhido = scanner.nextLine();
    
        // Obtém o produto escolhido da lista de produtos
        Produto produtoEscolhido = produtos.getProduto(nomeProdutoEscolhido);
    
        if (produtoEscolhido == null) {
            System.out.println("Produto não encontrado. Por favor, escolha um nome válido.");
            scanner.close();
            return escolherProduto(produtos); // Recursivamente pede ao usuário para escolher um produto válido
        }
        
        scanner.close();
        return produtoEscolhido;
    } 

    public static void processarCompra(Cliente.Vendedor vendedor, Cliente.Comprador comprador, Produto produto) {
        Scanner scanner = new Scanner(System.in);
        
        // Pergunta a quantidade desejada
        System.out.print("Digite a quantidade desejada: ");
        int quantidade = scanner.nextInt();
        scanner.close();
    
        // Cria a compra
        Compra compra = new Compra(vendedor, comprador, produto, LocalDateTime.now(), quantidade);
    
        // Adiciona a compra ao histórico do vendedor
        vendedor.historicoCompras.inserir(compra);
        comprador.historicoCompras.inserir(compra);
    
        System.out.println("Compra realizada com sucesso!");
    
        // Solicita a avaliação
        solicitarAvaliacao(compra);
    }

    public static void solicitarAvaliacao(Compra compra) {
        Scanner scanner = new Scanner(System.in);
    
        // Pergunta ao comprador se deseja avaliar o produto
        System.out.print("Você gostaria de avaliar o produto? (s/n): ");
        String resposta = scanner.nextLine();
    
        if (resposta.equalsIgnoreCase("s")) {
            System.out.print("Digite uma nota (0 a 5): ");
            int nota = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha
    
            System.out.print("Digite um comentário: ");
            String comentario = scanner.nextLine();
            scanner.close();
        
            // Adiciona a avaliação à fila de avaliações pendentes do vendedor
            compra.solicitarAvaliacao(comentario, nota);
    
            System.out.println("Obrigado pela sua avaliação!");
        } else {
            System.out.println("Obrigado pela sua compra!");
        }
    }
    
}


