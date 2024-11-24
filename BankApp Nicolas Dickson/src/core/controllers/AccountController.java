/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import java.util.List;
import java.util.Random;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.controllers.validators.ValidateAccount;
import core.models.Account;
import core.models.User;
import core.models.storage.Storage;

public class AccountController {

    // Instanciamos las dependencias de storage de acocunts y validador de datos de
    // entrada
    private static Storage storage = Storage.getInstance();
    private static ValidateAccount accountValidator = new ValidateAccount();

    // Crear una cuenta
    public static Response createAccount(String userId, String balanceInitial) {

        try {

            // Validar los datos de entrada
            String message = accountValidator.validateAccount(userId, balanceInitial);
            if (message != null) {
                return new Response(message, Status.BAD_REQUEST);
            }

            int userIdInt = Integer.parseInt(userId);
            double balanceInitialDouble = Double.parseDouble(balanceInitial);

            // Buscar si existe un usuario con el id ingresado
            User user = storage.getUserStorage().getUser(userIdInt);

            // Validar si el usuario no existe
            if (user == null) {
                return new Response("User not found", Status.NOT_FOUND);
            }

            // Crear la cuenta y agregarla al usuario
            // Primero crear el id aleatorio y validar que no exista ese id de la cuenta
            String accountId = createIdAccount();
            System.out.println(accountId);

            while (storage.getAccountStorage().getAccount(accountId) != null) {
                accountId = createIdAccount();
                System.out.println("Nuevo: " + accountId);
            }

            System.out.println("usuario: " + user.getId() + " baal: " + balanceInitialDouble);
            // Agregar la cuenta al usuario. Si no se pudo agregar, retornar error
            if (!storage.getAccountStorage().addAccount(new Account(accountId, user, balanceInitialDouble))) {
                System.out.println("Error");
                return new Response("Unexpected error", Status.BAD_REQUEST);
            }

            // Retornar la respuesta si todo sale bien
            return new Response("Account created successfully", Status.CREATED);

        } catch (Exception ex) {
            System.out.println(ex);

            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    // Metodo para crear el id de la cuenta de manera aleatoria
    private static String createIdAccount() {
        Random random = new Random();
        int first = random.nextInt(1000);
        int second = random.nextInt(1000000);
        int third = random.nextInt(100);

        String accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-"
                + String.format("%02d", third);
        return accountId;
    }

    // Método para obtener todos los usuarios ordenados por ID
    public static Response getAccounts() {
        try {
            // Obtener los usuarios desde Storage
            List<Account> accounts = storage.getAccountStorage().getAccounts();

            // Validar si retorna null estaria vacia
            if (accounts == null) {
                return new Response("Accounts not found", Status.NOT_FOUND);
            }

            // Ordenar la lista de cuentas por id(String)
            accounts.sort((obj1, obj2) -> (obj1.getId().compareTo(obj2.getId())));

            return new Response("Accounts found successfully", Status.OK, accounts);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para obtener un usuario por su ID
    public Response getAccount(String id) {
        try {
            // Buscar el usuario por ID en Storage
            Account account = storage.getAccountStorage().getAccount(id);

            // Validar si la cuenta no existe
            if (account == null) {
                return new Response("Account not found", Status.NOT_FOUND);
            }

            return new Response("Account found", Status.OK, account);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
