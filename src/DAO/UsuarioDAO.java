/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Entidad.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Estudiante
 */
public class UsuarioDAO {
    private static EntityManagerFactory
            emf=Persistence.createEntityManagerFactory("LoginApp");
    
    public void crear(Usuario u){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try{
            em.persist(u);
            em.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
        }
    }
    
    public boolean eliminar(Usuario u){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            em.remove(u);
            em.getTransaction().commit();
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
            return ret;
        }
    }
    
    public Usuario leer(Usuario par){
        EntityManager em = emf.createEntityManager();
        Usuario usuario = null;
        Query q = em.createQuery("SELECT u FROM Usuario u "+ 
                "WHERE u.nombre LIKE :nombre" + 
                " AND u.password LIKE :password").setParameter("nombre", par.getNombre())
                .setParameter("password", par.getPassword());
        try {
            usuario = (Usuario) q.getSingleResult();
        } catch (NonUniqueResultException e) {
            usuario = (Usuario) q.getResultList().get(0);
        } catch (Exception e){
            e.printStackTrace();
        }finally{
            em.close();
            return usuario;
        }
    }
    
    public boolean actualizar(Usuario u, Usuario nuevo){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        boolean ret = false;
        try {
            u = leer(u);
            u.setNombre(nuevo.getNombre());
            u.setPassword(nuevo.getPassword());
            em.merge(u);
            em.getTransaction().commit();
            ret = true;
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }finally{
            em.close();
            return ret;
        }
        
    }
    
    
}
