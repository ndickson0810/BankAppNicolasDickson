/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import core.controllers.transactions.Deposit;
import core.controllers.transactions.InterfaceTransaction;
import core.controllers.transactions.Transfer;
import core.controllers.transactions.WithDraw;
import java.util.List;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.controllers.validators.ValidateTransaction;
import core.models.Transaction;
import core.models.TransactionType;
import core.models.storage.Storage;

public class TransactionController {

    // Crear una instancia de Storage de trasacciones y el validador
    private static Storage storage = Storage.getInstance();
    private static ValidateTransaction transactionValidator = new ValidateTransaction();

    public static Response makeTransaction(String type, String idDestination, String idSource, String amount) {
        // Validar que el monto sea un numero>=0
        String message = transactionValidator.validateAmount(amount);
        if (message != null) {
            return new Response(message, Status.BAD_REQUEST);
        }

        // Convertir el tipo de transaccion que llega a TransactionType
        TransactionType transactionType = TransactionType.valueOf(type.toUpperCase());
        InterfaceTransaction transaction = null;

        // Usar un switch para determinar qué tipo de transacción ejecutar
        switch (transactionType) {
            case DEPOSIT:
                transaction = new Deposit();
                break;
            case WITHDRAW:
                transaction = new WithDraw();
                break;
            case TRANSFER:
                transaction = new Transfer();
                break;
            default:
                return new Response("Invalid transaction type", Status.BAD_REQUEST);
        }

        // Se ejecuta la transaccion con ayuda de la interface
        return transaction.execute(idSource, idDestination, amount);
    }

    // Metodo para obtener todas las transacciones
    public static Response getTransactions() {
        try {
            // Se obtiene la lista de transacciones de la Storage
            List<Transaction> transactions = storage.getTransactionStorage().getTransactions();

            // Si no hay transacciones retorna un NOTFOUND
            if (transactions == null) {
                return new Response("Transactions not found", Status.NOT_FOUND);
            }
            return new Response("Transactions found", Status.OK, transactions);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
