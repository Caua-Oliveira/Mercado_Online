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

        System.out.print("Digite seu username: ");
        String username = scanner.nextLine();

        System.out.print("Digite seu e-mail: ");
        String email = scanner.nextLine();

        System.out.print("Digite sua senha: ");
        String senha = scanner.nextLine();

        System.out.print("Digite seu Endereço: ");
        String address = scanner.nextLine();


        if (escolha == 1) {
            cadastrarComprador(username, email, senha, address);
        } else if (escolha == 2) {
            System.out.print("Digite o nome da sua loja: ");
            String nomeLoja = scanner.nextLine();
            cadastrarVendedor(username, email, senha, nomeLoja);
        }else if (escolha == 3) {
            Cliente.Comprador comprador = fazerLoginComprador(username, senha);
            if (comprador != null) {
                System.out.println("Login como Comprador realizado com sucesso!");
                // Listar Lojas
                // Escolha de produto (talvez mostrar avaliação do mesmo também)
                // Processar compra (talvez simplificar/remover o saldo do cliente)
                // Logo após, avaliar a compra feita (this.ultimo)
                // Oferecer sempre a opção de voltar ao menu inicial
            } else {
                System.out.println("Username ou senha incorretos para Comprador.");
            }
        } else if (escolha == 4) {
            Cliente.Vendedor vendedor = fazerLoginVendedor(username, senha);
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
}


