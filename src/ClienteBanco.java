import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClienteBanco {

    private static IContaBancaria conta;
    private static Scanner scanner;

    public static void main(String[] args) {
        try {
            System.out.println("=== CLIENTE BANCO RMI ===");

            scanner = new Scanner(System.in);

            String url = "rmi://localhost:8080/ContaBancaria";
            System.out.println("Conectando ao servidor...");
            conta = (IContaBancaria) Naming.lookup(url);
            System.out.println("Conectado com sucesso! Conta: " + conta.getNumeroConta());

            int tentativasRestantes = 3;
            boolean senhaValida = false;

            while (tentativasRestantes > 0) {
                System.out.print("Digite a senha da conta: ");
                String senhaConta = scanner.nextLine();

                senhaValida = conta.validarSenha(senhaConta);

                if (senhaValida) {
                    System.out.println("Senha correta. Acesso autorizado.");
                    break;
                } else {
                    tentativasRestantes--;
                    if (tentativasRestantes > 0) {
                        System.out.println("Senha incorreta. Tentativas restantes: " + tentativasRestantes);
                    } else {
                        System.out.println("Acesso negado. Número de tentativas excedido.");
                        return;
                    }
                }
            }

            System.out.println("\n" + conta.getInfoConta());

            boolean continuar = true;
            while (continuar) {
                continuar = exibirMenu();
            }

        } catch (Exception e) {
            System.err.println("Erro no cliente: " + e.getMessage());
        }
    }

    private static boolean exibirMenu() {
        try {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("Escolha uma opção: ");
            System.out.println("1. Consultar Saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Informações da Conta");
            System.out.println("5. Sair");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    getSaldo();
                    break;
                case 2:
                    depositar();
                    break;
                case 3:
                    sacar();
                    break;
                case 4:
                    getInfoConta();
                    break;
                case 5:
                    System.out.println("Cliente desconectado.");
                    return false;
                default:
                    System.out.println("Opção inválida!");
            }

        } catch (Exception e) {
            System.err.println("Erro: " + e.getMessage());
            scanner.nextLine();
        }

        return true;
    }

    private static void getSaldo() {
        try {
            String resposta = conta.getSaldo();
            System.out.println(resposta);
        } catch (RemoteException e) {
            System.err.println("Erro ao consultar saldo: " + e.getMessage());
        }
    }

    private static void depositar() {
        try {
            System.out.print("Digite o valor para depósito: R$ ");
            double valor = scanner.nextDouble();
            String resposta = conta.depositar(valor);
            System.out.println(resposta);
        } catch (RemoteException e) {
            System.err.println("Erro ao depositar: " + e.getMessage());
        }
    }

    private static void sacar() {
        try {
            System.out.print("Digite o valor para saque: R$ ");
            double valor = scanner.nextDouble();
            String resposta = conta.sacar(valor);
            System.out.println(resposta);
        } catch (RemoteException e) {
            System.err.println("Erro ao sacar: " + e.getMessage());
        }
    }

    private static void getInfoConta() {
        try {
            String info = conta.getInfoConta();
            System.out.println(info);
        } catch (RemoteException e) {
            System.err.println("Erro ao obter informações: " + e.getMessage());
        }
    }
}
