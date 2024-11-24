/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package core.models.storage;

import core.models.User;
import java.util.ArrayList;

//Interfaz que se usará para crear uusario, ver un usuario y listar usuarios de Storage
public interface InterfaceUserStorage {
    boolean addUser(User user);
    User getUser(int id);
    ArrayList<User> getUsers();
    
}
