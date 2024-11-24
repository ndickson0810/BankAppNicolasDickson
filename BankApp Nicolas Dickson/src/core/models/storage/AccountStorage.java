/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.Account;
import java.util.ArrayList;

// Clase que se usara para crear cuenta, ver una cuenta y listar cuentas
public class AccountStorage implements InterfaceAccountStorage {

    private ArrayList<Account> accounts;

    public AccountStorage() {
        this.accounts = new ArrayList<>();
    }

    // Agregar una cuenta, pero primero se verifica si el id ya fue ingresado
    public boolean addAccount(Account account) {
        for (Account u : this.accounts) {
            if (u.getId() == account.getId()) {
                // System.out.println("Ya está");
                return false;
            }
        }

        this.accounts.add(account);
        // System.out.println(account);
        return true;
    }

    // Obtener una cuenta mediante su id
    public Account getAccount(String id) {
        for (Account account : this.accounts) {
            if (account.getId().equals(id)) {
                return account;
            }
        }
        System.out.println("No hay");
        return null;
    }

    // Obtener todas las cuentas
    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }
}
