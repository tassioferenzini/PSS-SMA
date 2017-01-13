/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB.PersistenceUtil;
import PROV.DM.WasAttributedTo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author tassio
 */
public class WasAttributedToDAO {

   public static WasAttributedToDAO wasAttributedToPSSDAO;

    public static WasAttributedToDAO getInstance() {
        if (wasAttributedToPSSDAO == null) {
            wasAttributedToPSSDAO = new WasAttributedToDAO();
        }
        return wasAttributedToPSSDAO;
    }
   private Session session;

    public void save(WasAttributedTo wasAttributedTo) {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.save(wasAttributedTo);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public WasAttributedTo getWasAttributedTo(int id) {
        try {
            session = (Session) PersistenceUtil.getSession();
            WasAttributedTo wasAttributedTo = (WasAttributedTo) session.get(WasAttributedTo.class, new Integer(id));
            session.close();
            return wasAttributedTo;
        } catch (Exception ex) {
            Logger.getLogger(WasAttributedTo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<WasAttributedTo> getAll() {
        try {
            session = PersistenceUtil.getSession();
            org.hibernate.Query query = session.createQuery("from WasAttributedTo");
            List<WasAttributedTo> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(WasAttributedToDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public WasAttributedTo deleteWasAttributedTo(int id) {

        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            WasAttributedTo wasAttributedTo = (WasAttributedTo) session.get(WasAttributedTo.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(wasAttributedTo);
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

    public void deleteWasAttributedTo(WasAttributedTo wasAttributedTo) {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.delete(wasAttributedTo);
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
