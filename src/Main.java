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
            System.out.println("1 - Cadastrar como Cliente");
            System.out.println("2 - Cadastrar como Vendedor");
            System.out.println("3 - Login como Cliente");
            System.out.println("4 - Login como Vendedor");
            System.out.print("Escolha uma opção: ");
            escolha = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha após o número

            if (escolha < 1 || escolha > 4) {
                System.out.println("Opção inválida. Por favor, escolha entre as opções apresetadas.");
            }
        } while (escolha < 1 || escolha > 4);


        if (escolha == 1) {
            cadastrarComprador(cadastro(escolha)[0], cadastro(escolha)[1], cadastro(escolha)[2], cadastro(escolha)[3]);
        } else if (escolha == 2) {
            cadastrarVendedor(cadastro(escolha)[0], cadastro(escolha)[1], cadastro(escolha)[2], cadastro(escolha)[3]);
        }else if (escolha == 3) {
            Cliente.Comprador comprador = fazerLoginComprador(login()[0], login()[1]);
            if (comprador != null) {
                System.out.println("Login como Comprador realizado com sucesso!");
                // Listar Lojas
                Cliente.Vendedor.listarLojas();             
                // Escolha de loja
                System.out.print("Digite o nome da loja que deseja visitar: ");
                String nomeLoja = scanner.nextLine();
                Cliente.Vendedor lojaEscolhida = Cliente.Vendedor.escolherLoja(nomeLoja);

                if (lojaEscolhida != null) {
                    System.out.println("Você escolheu a loja: " + lojaEscolhida.getNomeLoja());
                    // Escolher produto
                    lojaEscolhida.getLojaEstoque();
                } else {
                    System.out.println("Loja não encontrada.");
                }
                scanner.close();

                // Processar compra
                // Logo após, avaliar a compra feita (this.ultimo)
            } else {
                System.out.println("Username ou senha incorretos para Comprador.");
            }
        } else if (escolha == 4) {
            Cliente.Vendedor vendedor = fazerLoginVendedor(login()[0], login()[1]);
            if (vendedor != null) {
                System.out.println("Login como Vendedor realizado com sucesso!");
                // Cadastrar produto novo ou existente (aumentar estoque)
                // Consultar faturamento (ou não, depende do tempo)
            } else {
                System.out.println("Username ou senha incorretos para Vendedor.");
            }
        }
        scanner.close(); 
    }

    public static String[] cadastro(int i) {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Digite seu username: ");
        String username = scanner.nextLine();
        System.out.print("Digite seu e-mail: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();
        System.out.print("Digite seu Endereço: ");
        String address = scanner.nextLine();

        if (i == 1) {
            System.out.print("Digite o nome da sua loja: ");
            String nomeLoja = scanner.nextLine();
            scanner.close();
            return new String[]{username, email, senha, nomeLoja};
        }

        scanner.close(); 

        return new String[]{username, email, senha, address};
    }
    
    public static String[] login() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.print("Digite seu username: ");
        String username = scanner.nextLine();   
        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        scanner.close(); 
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

    public Produto escolherProduto(ListaDuplamenteEncadeada<Produto> produtos) {
        Scanner scanner = new Scanner(System.in);
    
        // Mostra a lista de produtos disponíveis
        System.out.println("Produtos disponíveis:");
        produtos.printarOrdem();
    
        // Pede ao usuário para escolher um produto
        System.out.print("Escolha o ID do produto que deseja comprar: ");
        String idProdutoEscolhido = scanner.nextLine();
    
        // Obtém o produto escolhido da lista de produtos
        Produto produtoEscolhido = produtos.getDado(idProdutoEscolhido);
    
        if (produtoEscolhido == null) {
            System.out.println("Produto não encontrado. Por favor, escolha um ID válido.");
            scanner.close();
            return escolherProduto(produtos); // Recursivamente pede ao usuário para escolher um produto válido
        }
        
        scanner.close();
        return produtoEscolhido;
    }
    
    public void processarCompra(Cliente.Comprador comprador, Cliente.Vendedor vendedor, Produto produtoEscolhido) {
    Scanner scanner = new Scanner(System.in);

    System.out.print("Digite a quantidade que deseja comprar: ");
    int quantidade = scanner.nextInt();
    scanner.nextLine(); // Consumir a quebra de linha após o número

    if (quantidade <= 0) {
        System.out.println("Quantidade inválida. Por favor, digite um valor maior que zero.");
        scanner.close();
        return;
    }

    scanner.close();
    // Cria a compra
    Compra compra = new Compra(vendedor, comprador, produtoEscolhido, LocalDateTime.now(), quantidade);

    // Adiciona a compra ao histórico do comprador
    comprador.historicoCompras.inserir(compra);
    // Adiciona a compra ao histórico do vendedor
    vendedor.historicoCompras.inserir(compra);

    // Exibe os detalhes da compra
    System.out.println("Compra realizada com sucesso!");
    System.out.println(compra.printarFormatado());
}

}


