import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class ServidorBanco {

    public static void main(String[] args) {
        try {
            System.out.println("=== SERVIDOR BANCO RMI ===");
            System.out.println("Iniciando servidor...");

            try {
                LocateRegistry.createRegistry(8080);
            } catch (Exception e) {
                System.out.println("Registry já existe na porta 8080");
            }

            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o número da conta: ");
            String numeroConta = scanner.nextLine();

            System.out.print("Digite a senha da conta: ");
            String senhaConta = scanner.nextLine();

            System.out.print("Digite o saldo inicial: R$ ");
            double saldoInicial = scanner.nextDouble();

            ContaBancaria conta = new ContaBancaria(numeroConta, senhaConta, saldoInicial);

            String nomeServico = "rmi://localhost:8080/ContaBancaria";
            Naming.rebind(nomeServico, conta);

            System.out.println("\nServidor iniciado com sucesso!");
            System.out.println("\nPressione ENTER para encerrar o servidor");

            scanner.nextLine();
            System.in.read();
            System.out.println("Servidor encerrado.");
            System.exit(0);

        } catch (Exception e) {
            System.err.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}