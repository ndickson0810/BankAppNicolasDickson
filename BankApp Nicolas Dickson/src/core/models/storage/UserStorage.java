/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.models.storage;

import core.models.User;
import java.util.ArrayList;

// Clase que se usara para crear usuario, ver un usuario y listar usuarios implementa su interfaz 
public class UserStorage implements InterfaceUserStorage {

    private ArrayList<User> users;

    public UserStorage() {
        this.users = new ArrayList<>();
    }

    // Agregar un usuario, pero primero se verifica si el id ya fue ingresado

    public boolean addUser(User user) {
        for (User u : this.users) {
            if (u.getId() == user.getId()) {
                return false;
            }
        }
        this.users.add(user);
        return true;
    }

    // Buscar si existe un usuario con el id ingresado
    public User getUser(int id) {
        for (User user : this.users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    // Obtener todos los usuarios
    public ArrayList<User> getUsers() {
        // Validar si hay en la lista los retorna, sino, retorna null
        if (this.users.isEmpty()) {
            return null;
        }
        return this.users;
    }
}
