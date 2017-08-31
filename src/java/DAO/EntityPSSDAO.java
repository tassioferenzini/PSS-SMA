package DAO;

import DB.PersistenceUtil;
import Model.EntityPSS;
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
            Logger.getLogger(EntityPSS.class.getName()).log(Level.SEVERE, null, e);
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
            Query query = session.createQuery("from ProvEntity");
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
            Logger.getLogger(EntityPSS.class.getName()).log(Level.SEVERE, null, e);
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
            Logger.getLogger(EntityPSS.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
    }
    
        public void deleteAllEntityPSS() {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from ProvAgent");
            query.executeUpdate();
            session.flush();
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(EntityPSS.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
    }
        
}
