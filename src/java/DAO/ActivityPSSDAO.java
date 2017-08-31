package DAO;

import DB.PersistenceUtil;
import Model.ActivityPSS;
import java.io.Serializable;
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
public class ActivityPSSDAO implements Serializable {

    public static ActivityPSSDAO activityPSSDAO;

    public static ActivityPSSDAO getInstance() {
        if (activityPSSDAO == null) {
            activityPSSDAO = new ActivityPSSDAO();
        }
        return activityPSSDAO;
    }

    private Session session;

    public void save(ActivityPSS activityPSS) {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.save(activityPSS);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(ActivityPSS.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public ActivityPSS getActivityPSS(int id) {
        try {
            session = (Session) PersistenceUtil.getSession();
            ActivityPSS activityPSS = (ActivityPSS) session.get(ActivityPSS.class, new Integer(id));
            session.close();
            return activityPSS;
        } catch (Exception ex) {
            Logger.getLogger(ActivityPSS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ActivityPSS> getAll() {
        try {
            session = PersistenceUtil.getSession();
            Query query = session.createQuery("from ProvActivity");
            List<ActivityPSS> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(ActivityPSSDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public ActivityPSS deleteActivityPSS(int id) {

        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            ActivityPSS activityPSS = (ActivityPSS) session.get(ActivityPSS.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(activityPSS);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(ActivityPSS.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public void deleteActivityPSS(ActivityPSS activityPSS) {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.delete(activityPSS);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(ActivityPSS.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public void deleteAllActivityPSS() {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from ProvActivity");
            query.executeUpdate();
            session.flush();
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(ActivityPSS.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
    }

}
