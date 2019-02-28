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
public final class Main
{
    public static void main(String[] args)
    {
        GossipManager gm = new GossipManager();
        
        /* EXAMPLE 1 */
        gm.createAndAddPerson("Store3", "Store1", "Store2", "Store3");
        gm.createAndAddPerson("Store3", "Store2", "Store3", "Store1");
        gm.createAndAddPerson("Store4", "Store2", "Store3", "Store4", "Store5");
        gm.printStopsNumber(System.out);
        
        /* EXAMPLE 2 */
        gm.clear();
        gm.createAndAddPerson("Store2", "Store1", "Store2");
        gm.createAndAddPerson("Store5", "Store2", "Store8");
        gm.printStopsNumber(System.out);
    }
}
