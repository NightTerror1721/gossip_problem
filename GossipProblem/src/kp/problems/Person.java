/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.problems;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 *
 * @author Marc
 */
public class Person
{
    private final Gossip selfGossip = new Gossip();
    private final HashSet<Gossip> gossips = new HashSet<>();
    private String[] itinerary;
    
    public final void setItinerary(String... itinerary)
    {
        this.itinerary = Objects.requireNonNull(itinerary);
    }
    
    public final String getStoreAtMinute(int minute) { return itinerary[minute % itinerary.length]; }
    
    public final void clearGossips()
    {
        gossips.clear();
        gossips.add(selfGossip);
    }
    
    public final Gossip getSelfGossip() { return selfGossip; }
    
    public final boolean hasGossip(Gossip gossip) { return gossips.contains(gossip); }
    public final void addGossip(Gossip gossip) { gossips.add(gossip); }
    public final int getGossipCount() { return gossips.size(); }
    
    
    public static final void mergeGossips(Collection<Person> people)
    {
        final Set<Gossip> merged = people.stream()
                .map(p -> p.gossips)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());
        
        people.stream()
                .map(p -> p.gossips)
                .forEach(gs -> gs.addAll(merged));
    }
    public static final void mergeGossips(Person... people) { mergeGossips(Arrays.asList(people)); }
}
