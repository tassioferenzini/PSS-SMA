/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB.PersistenceUtil;
import Model.EntityPSS;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tassio
 */
public class EntityPSSDAO {

    public static EntityPSSDAO entityPSSDAO;

    public static EntityPSSDAO getInstance() {
        if (entityPSSDAO == null) {
            entityPSSDAO = new EntityPSSDAO();
        }
        return entityPSSDAO;
    }

    private Session session;

    public void save(EntityPSS entityPSS) {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.save(entityPSS);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public EntityPSS getEntityPSS(int id) {
        try {
            session = PersistenceUtil.getSession();
            EntityPSS entityPSS = (EntityPSS) session.get(EntityPSS.class, new Integer(id));
            session.close();
            return entityPSS;
        } catch (Exception ex) {
            Logger.getLogger(EntityPSS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<EntityPSS> getAll() {
        try {
            session = (Session) PersistenceUtil.getSession();
            org.hibernate.Query query = session.createQuery("from Entity");
            List<EntityPSS> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(EntityPSSDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public EntityPSS deleteEntityPSS(int id) {

        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            EntityPSS entityPSS = (EntityPSS) session.get(EntityPSS.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(entityPSS);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public void deleteEntityPSS(EntityPSS entityPSS) {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.delete(entityPSS);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }

    }
}
