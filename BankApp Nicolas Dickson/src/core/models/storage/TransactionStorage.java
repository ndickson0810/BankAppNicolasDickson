/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Transaction;
import java.util.ArrayList;

// Clase que se usara para crear transacciones y listarlas
public class TransactionStorage implements InterfaceTransactionStorage {

    private ArrayList<Transaction> transactions;

    public TransactionStorage() {
        this.transactions = new ArrayList<>();
    }

    // Agregar una transacción
    public boolean addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
        return true;
    }

    // Obtener todas las transacciones
    public ArrayList<Transaction> getTransactions() {
        if (this.transactions.isEmpty()) {
            return null;
        }
        return this.transactions;
    }

}
