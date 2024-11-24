/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.transactions;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.TransactionType;
import core.models.Transaction;
import core.models.storage.Storage;

// Clase para realizar una transferencia
public class Transfer implements InterfaceTransaction {
    public Response execute(String idSource, String idDestination, String amount) {

        try {
            Storage storage = Storage.getInstance();
            // Obtener la cuenta de origen
            Account accountSource = storage.getAccountStorage().getAccount(idSource);

            // Validar si la cuenta origen no existe
            if (accountSource == null) {
                return new Response("Account Source not found", Status.NOT_FOUND);
            }

            // Obtener la cuenta de destino
            Account accountDestination = storage.getAccountStorage().getAccount(idDestination);

            // Validar si la cuenta de destino no existe
            if (accountDestination == null) {
                return new Response("Account Destination not found", Status.NOT_FOUND);
            }

            double amountDouble = Double.parseDouble(amount);

            // Validar que el monto no supere su saldo
            if (accountSource.withdraw(amountDouble)) {
                // Se realiza la transferencia
                accountDestination.deposit(amountDouble);
                storage.getTransactionStorage().addTransaction(
                        new Transaction(TransactionType.TRANSFER, accountSource, accountDestination, amountDouble));
                return new Response("Transfer executed successfully", Status.OK);
            }

            // Retornar error si la transferencia no se pudo realizar
            return new Response("Transfer not executed, insufficient balance", Status.BAD_REQUEST);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);

        }

    }

}
