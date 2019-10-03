/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import Entidad.Sistema;
import Entidad.Usuario;
import Frontera.FramePrincipal;
import static Frontera.FramePrincipal.sistema;

/**
 *
 * @author lupena
 */
public class CrearRegistro {

    private Sistema sistema = FramePrincipal.sistema;
    public CrearRegistro() {
    }
    
    public String verificarRegistro(String nombre, String pass, String valPass){
        if(!verificarLongitudNombre(nombre)){
            return("Longitud nombre incorrecta");
        }
         if(!verificarLongitudPassword(pass)){
            return("Longitud password incorrecta");
        }
        if(!valPass.equals(pass)){
            return("Las contraseñas no coinciden");
        }
        Usuario nuevo = new Usuario();
        nuevo.setNombre(nombre);
        nuevo.setPassword(pass);
        sistema.addUser(nuevo);
        printUsers();
        return ("Registro correcto");
        
    }
        
     public void printUsers(){
         for (Usuario usuario : sistema.getUsuarios()) {
             System.out.println(usuario.getNombre());
             System.out.println(usuario.getPassword());
             System.out.println("---------------");
        }
     }
    
     public Boolean verificarLongitudNombre(String nombre){
        return (nombre.length()>1 && nombre.length()<=6);
    }
    
    public Boolean verificarLongitudPassword(String password){
        return (password.length()>=3 && password.length()<6);
    }
    
}
