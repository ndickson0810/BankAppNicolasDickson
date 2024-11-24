/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

public class Storage {

    // Instancia Singleton
    private static Storage instance;

    // Instancias para los storages de cada clase
    private InterfaceUserStorage userStorage;
    private InterfaceAccountStorage accountStorage;
    private InterfaceTransactionStorage transactionStorage;

    // Constructor
    private Storage() {
        this.userStorage = new UserStorage();
        this.accountStorage = new AccountStorage();
        this.transactionStorage = new TransactionStorage();

    }

    // Método para obtener la instancia Singleton
    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    // Metodos para delegar en cada tipo de storage
    // Para los usuarios
    public InterfaceUserStorage getUserStorage() {
        return this.userStorage;
    }

    // Para las cuentas
    public InterfaceAccountStorage getAccountStorage() {
        return this.accountStorage;
    }

    // Para las transacciones
    public InterfaceTransactionStorage getTransactionStorage() {
        return this.transactionStorage;
    }
}
