/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.problems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Objects;

/**
 *
 * @author Marc
 */
public final class GossipManager
{
    private final LinkedList<Person> people = new LinkedList<>();
    
    public final void addPerson(Person p)
    {
        people.add(Objects.requireNonNull(p));
    }
    
    public final Person createAndAddPerson(String... itinerary)
    {
        Person p = new Person();
        p.setItinerary(itinerary);
        addPerson(p);
        return p;
    }
    
    public final void loadPeopleFromFile(Path file)
    {
        try(BufferedReader br = Files.newBufferedReader(file))
        {
            br.lines().forEach(this::loadPersonFromText);
        }
        catch(IOException ex) { ex.printStackTrace(System.err); }
    }
    
    public final void loadPersonFromText(String line)
    {
        String[] stops = line.split("\\s");
        Person p = new Person();
        p.setItinerary(stops);
        addPerson(p);
    }
    
    public final void clear() { people.clear(); }
    
    public final void resetPeopleGossips()
    {
        people.stream().forEach(Person::clearGossips);
    }
    
    public final void updateGossips(final int minute)
    {
        final LinkedHashMap<String, LinkedList<Person>> stores = new LinkedHashMap<>();
        people.stream().forEach(p -> {
            final String store = p.getStoreAtMinute(minute);
            LinkedList<Person> list = stores.get(store);
            if(list == null)
            {
                list = new LinkedList<>();
                stores.put(store, list);
            }
            list.add(p);
        });
        stores.values().stream().forEach(Person::mergeGossips);
    }
    
    /**
     * 
     * @param maxMinutes
     * @return Returns the number of stops required for all customers to know all the gossip, or -1 if it is not possible.
     */
    public final int computeStopsNumber(int maxMinutes)
    {
        /**
         * Max Gossip count and people count.
         */
        final int maxGossips = people.size();
        resetPeopleGossips();
        for(int i = 0; i < maxMinutes; i++)
        {
            updateGossips(i);
            if(people.stream().filter(p -> p.getGossipCount() == maxGossips).count() == maxGossips)
                return i + 1;
        }
        return -1;
    }
    
    /**
     * Default maxMinutes is 480
     * 
     * @return Returns the number of stops required for all customers to know all the gossip, or -1 if it is not possible.
     */
    public final int computeStopsNumber() { return computeStopsNumber(480); }
    
    /**
     * Print the number of stops required for all customers to know all the gossip, or -1 if it is not possible.
     * @param output
     * @param maxMinutes
     */
    public final void printStopsNumber(OutputStream output, int maxMinutes)
    {
        PrintStream ps = new PrintStream(output);
        final int result = computeStopsNumber(maxMinutes);
        ps.append("Stops Number: ");
        if(result < 0)
            ps.println("never");
        else ps.println(result);
        ps.flush();
    }
    /**
     * Print the number of stops required for all customers to know all the gossip, or -1 if it is not possible.
     * Default maxMinutes is 480.
     * @param output
     */
    public final void printStopsNumber(OutputStream output) { printStopsNumber(output, 480); }
}
