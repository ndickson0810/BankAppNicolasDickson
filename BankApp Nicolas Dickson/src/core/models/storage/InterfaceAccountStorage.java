/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.storage;

import core.models.Account;
import java.util.ArrayList;

//Interfaz que se usará para crear cuenta, ver una cuenta y listar cuentas de Storage
public interface InterfaceAccountStorage {
    boolean addAccount(Account account);
    Account getAccount(String id);
    ArrayList<Account> getAccounts();
}
