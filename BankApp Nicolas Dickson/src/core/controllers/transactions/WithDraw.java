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

// Clase para realizar un retiro
public class WithDraw implements InterfaceTransaction {

    // Metodo para realizar el retiro
    public Response execute(String idSource, String idDestination, String amount) {
        try {
            Storage storage = Storage.getInstance();

            // Obtener la cuenta de origen de storage
            Account account = storage.getAccountStorage().getAccount(idSource);

            // Validar si la cuenta no existe
            if (account == null) {
                return new Response("Account Source not found", Status.NOT_FOUND);
            }

            double amountDouble = Double.parseDouble(amount);

            // Validar que el monto no supere su saldo
            if (account.withdraw(amountDouble)) {
                // Agregar la transaccion
                storage.getTransactionStorage()
                        .addTransaction(new Transaction(TransactionType.WITHDRAW, account, null, amountDouble));
                return new Response("Withdraw executed successfully", Status.OK);
            }
            // Retornar error si el retiro no se pudo realizar
            return new Response("Withdraw not executed, insufficient balance", Status.BAD_REQUEST);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }

}
