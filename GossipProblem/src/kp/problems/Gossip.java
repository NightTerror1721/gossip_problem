/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.problems;

/**
 *
 * @author Marc
 */
public final class Gossip
{
    private static int GOSSIP_SEED = 0;
    
    private final int id;
    
    public Gossip()
    {
        this.id = GOSSIP_SEED++;
    }
    
    public final int getGossipId() { return id; }
    
    public final boolean equals(Gossip g) { return id == g.id; }
    
    @Override
    public final boolean equals(Object o)
    {
        return o instanceof Gossip &&
                id == ((Gossip) o).id;
    }

    @Override
    public final int hashCode()
    {
        int hash = 5;
        hash = 53 * hash + this.id;
        return hash;
    }
}
