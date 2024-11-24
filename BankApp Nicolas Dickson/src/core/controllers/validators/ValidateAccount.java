/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.validators;

public class ValidateAccount {

    // Validaciones necesarias para crear un account
    public String validateAccount(String userId, String balanceInitial) {
        // Validar que el id tenga 9 digitos o menos
        if (userId.length() > 9) {
            return "Id must be less than 9 digits";
        }
        // Validar que id sea numero, y mayor o igual a cero
        try {
            int userIdInt = Integer.parseInt(userId);
            if (userIdInt < 0) {
                return "Id must be positive";
            }
        } catch (NumberFormatException ex) {
            return "Id must be numeric";
        }

        // Validar que el saldo sea un numero >= 0
        try {
            double balanceInitialDouble = Double.parseDouble(balanceInitial);
            if (balanceInitialDouble < 0) {
                return "Initial Balance must be positive";
            }
        } catch (NumberFormatException ex) {
            return "Initial Balance must be numeric";
        }

        return null; // Si todas las validaciones pasan, devolver null
    }

}
