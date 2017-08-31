package DAO;

import DB.PersistenceUtil;
import PROV.DM.ProvWasAssociatedWith;
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
public class WasAssociatedWithDAO {

    public static WasAssociatedWithDAO wasAssociatedWithPSSDAO;

    public static WasAssociatedWithDAO getInstance() {
        if (wasAssociatedWithPSSDAO == null) {
            wasAssociatedWithPSSDAO = new WasAssociatedWithDAO();
        }
        return wasAssociatedWithPSSDAO;
    }

    private Session session;

    public void save(ProvWasAssociatedWith wasAssociatedWith) {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.save(wasAssociatedWith);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(WasAssociatedWithDAO.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public ProvWasAssociatedWith getWasAssociatedWith(int id) {
        try {
            session = (Session) PersistenceUtil.getSession();
            ProvWasAssociatedWith wasAssociatedWith = (ProvWasAssociatedWith) session.get(ProvWasAssociatedWith.class, new Integer(id));
            session.close();
            return wasAssociatedWith;
        } catch (Exception ex) {
            Logger.getLogger(ProvWasAssociatedWith.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ProvWasAssociatedWith> getAll() {
        try {
            session = PersistenceUtil.getSession();
            Query query = session.createQuery("from ProvWasAssociatedWith");
            List<ProvWasAssociatedWith> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(WasAssociatedWithDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ProvWasAssociatedWith deleteWasAssociatedWith(int id) {

        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            ProvWasAssociatedWith wasAssociatedWith = (ProvWasAssociatedWith) session.get(ProvWasAssociatedWith.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(wasAssociatedWith);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(WasAssociatedWithDAO.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public void deleteWasAssociatedWith(ProvWasAssociatedWith wasAssociatedWith) {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.delete(wasAssociatedWith);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(WasAssociatedWithDAO.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public void deleteAllWasAssociatedWith() {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from ProvAgent");
            query.executeUpdate();
            session.flush();
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(WasAssociatedWithDAO.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
    }

}
