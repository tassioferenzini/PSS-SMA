/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB.PersistenceUtil;
import PROV.DM.WasAssociatedWith;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tassio
 */
public class WasAssociatedWithDAO {

   public static WasAssociatedWithDAO wasAssociatedWithPSSDAO;

    public static WasAssociatedWithDAO getInstance() {
        if (wasAssociatedWithPSSDAO == null) {
            wasAssociatedWithPSSDAO = new WasAssociatedWithDAO();
        }
        return wasAssociatedWithPSSDAO;
    }
    
  private Session session;

    public void save(WasAssociatedWith wasAssociatedWith) {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.save(wasAssociatedWith);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public WasAssociatedWith getWasAssociatedWith(int id) {
        try {
            session = (Session) PersistenceUtil.getSession();
            WasAssociatedWith wasAssociatedWith = (WasAssociatedWith) session.get(WasAssociatedWith.class, new Integer(id));
            session.close();
            return wasAssociatedWith;
        } catch (Exception ex) {
            Logger.getLogger(WasAssociatedWith.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<WasAssociatedWith> getAll() {
        try {
            session = PersistenceUtil.getSession();
            org.hibernate.Query query = session.createQuery("from WasAssociatedWith");
            List<WasAssociatedWith> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(WasAssociatedWithDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public WasAssociatedWith deleteWasAssociatedWith(int id) {

        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            WasAssociatedWith wasAssociatedWith = (WasAssociatedWith) session.get(WasAssociatedWith.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(wasAssociatedWith);
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

    public void deleteWasAssociatedWith(WasAssociatedWith wasAssociatedWith) {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.delete(wasAssociatedWith);
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
