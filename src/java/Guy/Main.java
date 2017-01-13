/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Guy;

import Agents.AgentBuy;
import Agents.AgentSell;
import jade.wrapper.StaleProxyException;

/**
 *
 * @author tassio
 */
public class Main {

    /**
     * @param arg the command line arguments
     */
    public static void main(String[] arg) throws StaleProxyException, InterruptedException {
        
        String[] args = {"java", "php", "c"};

        AgentSell s1 = new AgentSell();
        AgentSell s2 = new AgentSell();

        AgentBuy b1 = new AgentBuy();
        //padrão é php e java
        b1.setArguments(args);
        AgentBuy b2 = new AgentBuy();
        b2.setArguments(args);

        InitAgent.init(s1, "s1", "Seller");
        Thread.currentThread().sleep(5000);
        InitAgent.init(s2, "s2", "Seller2");
        Thread.currentThread().sleep(5000);
        InitAgent.init(b1, "b1", "Buyer1");
        Thread.currentThread().sleep(5000);
        InitAgent.init(b2, "b2", "Buyer2");

    }

}
