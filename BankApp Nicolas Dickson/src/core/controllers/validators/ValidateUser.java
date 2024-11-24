/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.validators;

public class ValidateUser {

    // Validaciones necesarias para crear un usuaario
    public String validateUser(String id, String firstname, String lastname, String age) {
        // Validar que el id tenga 9 digitos o menos
        if (id.length() > 9) {
            return "Id must be less than 9 digits";
        }
        // Validar que el id sea un número y positivo
        try {
            int idInt = Integer.parseInt(id);
            if (idInt < 0) {
                return "Id must be positive";
            }
        } catch (NumberFormatException ex) {
            return "Id must be numeric";
        }
        // Validar que la edad sea un número, y mayor o igual a 18
        try {
            int ageInt = Integer.parseInt(age);
            if (ageInt < 18) {
                return "Age must be at least 18";
            }
        } catch (NumberFormatException ex) {
            return "Age must be numeric";
        }

        // Validar que nombre y apellido no estén vacíos
        if (firstname.equals("")) {
            return "Firstname must be not empty";
        }
        if (lastname.equals("")) {
            return "Lastname must be not empty";
        }

        return null; // Si todas las validaciones pasan, devolver null
    }

    // Validaciones necesarias para obtener un usuario
    public String validateUserID(String id) {
        int idInt;
        try {
            idInt = Integer.parseInt(id);
            if (idInt < 0) {
                return "Id must be positive";
            }
        } catch (NumberFormatException ex) {
            return "Id must be numeric";
        }
        return null;
    }
}
