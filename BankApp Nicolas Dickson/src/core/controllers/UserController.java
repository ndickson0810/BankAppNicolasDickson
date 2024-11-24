/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import java.util.List;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.User;
import core.models.storage.Storage;
import core.controllers.validators.ValidateUser;

public class UserController {

    // Instanciamos las dependencias al inicio. Storage para usuarios y validador de
    // datos de entrada
    private static Storage storage = Storage.getInstance();
    private static ValidateUser userValidator = new ValidateUser();

    // Registrar usuario User
    public static Response registerUser(String id, String firstname, String lastname, String age) {
        try {
            // Validar los datos de entrada
            String message = userValidator.validateUser(id, firstname, lastname, age);
            if (message != null) {
                return new Response(message, Status.BAD_REQUEST);
            }

            int idInt = Integer.parseInt(id);
            int ageInt = Integer.parseInt(age);

            // Si existe un usuario con el id ingresado no se puede registrar
            if (!storage.getUserStorage().addUser(new User(idInt, firstname, lastname, ageInt))) {
                return new Response("User with that id already exists", Status.BAD_REQUEST);
            }
            // Sino Crear el usuario
            return new Response("User created successfully", Status.CREATED);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para obtener todos los usuarios ordenados por ID
    public static Response getUsers() {
        try {
            // Obtener los usuarios desde Storage
            List<User> users = storage.getUserStorage().getUsers();

            // Validar si hay en la lista retorna null estaria vacia
            if (users == null) {
                return new Response("Users not found", Status.NOT_FOUND);
            }
            // Sino Ordenar la lista de usuarios por id
            users.sort((obj1, obj2) -> (obj1.getId() - obj2.getId()));
            return new Response("Users found successfully", Status.OK, users);
        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    // Método para obtener un usuario por su ID
    public Response getUser(String id) {
        try {
            // Validar los datos de entrada
            String message = userValidator.validateUserID(id);
            if (message != null) {
                return new Response(message, Status.BAD_REQUEST);
            }

            int idInt = Integer.parseInt(id);

            // Buscar el usuario por ID en Storage
            User user = storage.getUserStorage().getUser(idInt);

            // Validar si el usuario no existe
            if (user == null) {
                return new Response("User not found", Status.NOT_FOUND);
            }

            // Sino retorna el usuario
            return new Response("User found", Status.OK, user);

        } catch (Exception ex) {
            return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
        }
    }

}
