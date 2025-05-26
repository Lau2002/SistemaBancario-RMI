import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.DecimalFormat;

public class ContaBancaria extends UnicastRemoteObject implements IContaBancaria{

    private double saldo;
    private String numeroConta;
    private String senhaConta;
    private int transacoes;
    private DecimalFormat df;


    public ContaBancaria(String numeroConta, String senhaConta, double saldoInicial) throws RemoteException {
        super();
        this.numeroConta = numeroConta;
        this.senhaConta = senhaConta;
        this.saldo = saldoInicial;
        this.transacoes = 0;
        this.df = new DecimalFormat("#,##0.00");
        System.out.println("Conta " + numeroConta + " criada com saldo inicial: R$ " + df.format(saldoInicial));
    }


    public synchronized String depositar(double valor) throws RemoteException {
        if (valor <= 0) {
            return "Valor para depósito deve ser maior que zero";
        }

        double saldoAnterior = saldo;
        saldo += valor;
        transacoes++;

        return "DEPÓSITO - Conta: " + numeroConta +
                " | Valor: R$ " + df.format(valor) +
                " | Saldo anterior: R$ " + df.format(saldoAnterior) +
                " | Novo saldo: R$ " + df.format(saldo);
    }

    public synchronized String sacar(double valor) throws RemoteException {
        if (valor <= 0) {
            return "Valor para saque deve ser maior que zero";
        }

        if (valor > saldo) {
            return "Saldo insuficiente. Saldo atual: R$ " + df.format(saldo);
        }

        double saldoAnterior = saldo;
        saldo -= valor;
        transacoes++;

        return "SAQUE - Conta: " + numeroConta +
                " | Valor: R$ " + df.format(valor) +
                " | Saldo anterior: R$ " + df.format(saldoAnterior) +
                " | Novo saldo: R$ " + df.format(saldo);
    }

    public synchronized String getNumeroConta() throws RemoteException {
        return numeroConta;
    }

    public synchronized String getSaldo() throws RemoteException {
        return "CONSULTA SALDO - Conta: " + numeroConta +
                " | Saldo: R$ " + df.format(saldo);
    }

    public synchronized String getInfoConta() throws RemoteException {
        return "Conta: " + numeroConta +
                " | Saldo: R$ " + df.format(saldo) +
                " | Total de transações: " + transacoes;
    }

    public synchronized boolean validarSenha(String senhaConta) throws RemoteException {
        return this.senhaConta.equals(senhaConta);
    }
}