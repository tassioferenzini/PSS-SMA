package DAO;

import DB.PersistenceUtil;
import PROV.DM.ProvWasAttributedTo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Query;
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

    public void save(ProvWasAttributedTo wasAttributedTo) {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.save(wasAttributedTo);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(WasAttributedToDAO.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public ProvWasAttributedTo getWasAttributedTo(int id) {
        try {
            session = (Session) PersistenceUtil.getSession();
            ProvWasAttributedTo wasAttributedTo = (ProvWasAttributedTo) session.get(ProvWasAttributedTo.class, new Integer(id));
            session.close();
            return wasAttributedTo;
        } catch (Exception ex) {
            Logger.getLogger(WasAttributedToDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ProvWasAttributedTo> getAll() {
        try {
            session = PersistenceUtil.getSession();
            Query query = session.createQuery("from ProvWasAttributedTo");
            List<ProvWasAttributedTo> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(WasAttributedToDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ProvWasAttributedTo deleteWasAttributedTo(int id) {

        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            ProvWasAttributedTo wasAttributedTo = (ProvWasAttributedTo) session.get(ProvWasAttributedTo.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(wasAttributedTo);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(WasAttributedToDAO.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public void deleteWasAttributedTo(ProvWasAttributedTo wasAttributedTo) {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.delete(wasAttributedTo);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(WasAttributedToDAO.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public void deleteAllWasAttributedTo() {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from ProvAgent");
            query.executeUpdate();
            session.flush();
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(WasAttributedToDAO.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
    }

}
