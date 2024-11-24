/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.controllers.transactions;

import core.controllers.utils.Response;

// Interface para ejecutar las transacciones
public interface InterfaceTransaction {
    Response execute(String idSource, String idDestination, String amount);
}
