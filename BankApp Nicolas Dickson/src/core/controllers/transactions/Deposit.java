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

// Clase para realizar un deposito
public class Deposit implements InterfaceTransaction {

    public Response execute(String idSource, String idDestination, String amount) {
        try {
            Storage storage = Storage.getInstance();

            // Obtener la cuenta de destino para el deposito
            Account account = storage.getAccountStorage().getAccount(idDestination);
            System.out.println(account);

            // Validar si la cuenta no exista
            if (account == null) {
                return new Response("Account Destination not found", Status.NOT_FOUND);
            }

            double amountDouble = Double.parseDouble(amount);

            // Realizar el deposito
            account.deposit(amountDouble);
            // Agregar la transaccion. Se guarda en storage
            storage.getTransactionStorage()
                    .addTransaction(new Transaction(TransactionType.DEPOSIT, null, account, amountDouble));

            return new Response("Deposit executed successfully", Status.OK);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }

    }
}
