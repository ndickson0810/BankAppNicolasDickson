/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.storage;

import core.models.Transaction;
import java.util.ArrayList;

//Interfaz que se usará para crear transacciones,  y listarlas de Storage
public interface InterfaceTransactionStorage {
    boolean addTransaction(Transaction transaction);
    ArrayList<Transaction> getTransactions();
    
}
