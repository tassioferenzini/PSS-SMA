package DAO;

import DB.PersistenceUtil;
import Model.AgentPSS;
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
public class AgentPSSDAO {

    public static AgentPSSDAO agentPSSDAO;

    public static AgentPSSDAO getInstance() {
        if (agentPSSDAO == null) {
            agentPSSDAO = new AgentPSSDAO();
        }
        return agentPSSDAO;
    }
    private Session session;

    public void save(AgentPSS agentPSS) {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.save(agentPSS);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(AgentPSS.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public AgentPSS getAgentPSS(int id) {
        try {
            session = (Session) PersistenceUtil.getSession();
            AgentPSS agentPSS = (AgentPSS) session.get(AgentPSS.class, new Integer(id));
            session.close();
            return agentPSS;
        } catch (Exception ex) {
            Logger.getLogger(AgentPSS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<AgentPSS> getAll() {
        try {
            session = (Session) PersistenceUtil.getSession();
            Query query = session.createQuery("from ProvAgent");
            List<AgentPSS> list = query.list();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(AgentPSSDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            session.close();
        }
        return null;
    }

    public AgentPSS deleteAgentPSS(int id) {

        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            AgentPSS agentPSS = (AgentPSS) session.get(AgentPSS.class, new Integer(id));
            tx = session.beginTransaction();
            session.delete(agentPSS);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(AgentPSS.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
        return null;
    }

    public void deleteAgentPSS(AgentPSS agentPSS) {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            session.delete(agentPSS);
            session.flush();
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(AgentPSS.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
    }

    public void deleteAllAgentPSS() {
        Transaction tx = null;
        try {
            session = (Session) PersistenceUtil.getSession();
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from ProvAgent");
            query.executeUpdate();
            session.flush();
            tx.commit();
        } catch (Exception e) {
            Logger.getLogger(AgentPSS.class.getName()).log(Level.SEVERE, null, e);
            tx.rollback();
        } finally {
            session.close();
        }
    }
}
