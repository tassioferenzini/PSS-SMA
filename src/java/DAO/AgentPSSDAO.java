/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DB.PersistenceUtil;
import Model.AgentPSS;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            e.printStackTrace();
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
            session = PersistenceUtil.getSession();
            org.hibernate.Query query = session.createQuery("from Agent");
            List<AgentPSS> list = query.list();
            session.close();
            return list;
        } catch (Exception ex) {
            Logger.getLogger(AgentPSSDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            e.printStackTrace();
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
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
        }

    }
}
