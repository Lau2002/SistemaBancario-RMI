import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IContaBancaria extends Remote {

    String getSaldo() throws RemoteException;
    String depositar(double valor) throws RemoteException;
    String sacar(double valor) throws RemoteException;
    String getNumeroConta() throws RemoteException;
    String getInfoConta() throws RemoteException;
    boolean validarSenha(String senha) throws RemoteException;
}
