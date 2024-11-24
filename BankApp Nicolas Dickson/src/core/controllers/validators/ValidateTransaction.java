/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.validators;

public class ValidateTransaction {

    // Validar datos para hacer una transaccion
    public String validateAmount(String amount) {
        // Validar que el monto sea un numero, y sea > 0
        try {
            double amountDouble = Double.parseDouble(amount);
            if (amountDouble <= 0) {
                return "Amount must be superior to zero";
            }
        } catch (NumberFormatException ex) {
            return "Amount must be numeric";
        }
        return null;
    }
}
