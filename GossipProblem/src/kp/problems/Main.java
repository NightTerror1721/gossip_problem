/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kp.problems;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author Marc
 */
public final class Main
{
    public static void main(String[] args)
    {
        if(args.length < 1)
            executeHardcodedExamples();
        else switch(args[0])
        {
            case "-input": computeFromInput(); break;
            case "-files": computeFromFiles(args); break;
            case "-example":
            default: executeHardcodedExamples(); break;
        }
    }
    
    private static void computeFromInput()
    {
        Scanner s = new Scanner(System.in);
        
        int count = 0;
        for(;;)
        {
            System.out.println("Write people count:");
            try { count = s.nextInt(); break; }
            catch(NoSuchElementException ex)
            {
                System.err.println("Expected valid integer");
            }
        }
        
        if(count < 1)
            return;
        s.nextLine();
        
        GossipManager gm = new GossipManager();
        
        System.out.println();
        for(int i = 0; i < count; i++)
        {
            System.out.println("Write the itinerary of person " + (i + 1) + " separated by spaces:");
            try
            {
                String line = s.nextLine();
                gm.loadPersonFromText(line);
            }
            catch(NoSuchElementException ex)
            {
                System.err.println("Expected valid line of person itinerary");
                i--;
            }
        }
        
        gm.printStopsNumber(System.out);
    }
    
    private static void computeFromFiles(String[] args)
    {
        //Skip first parameter ["-files", ...]
        Arrays.stream(args).skip(1).forEach(Main::computeFromFile);
    }
    private static void computeFromFile(String sfile)
    {
        GossipManager gm = new GossipManager();
        Path path = Paths.get(sfile);
        
        gm.loadPeopleFromFile(path);
        gm.printStopsNumber(System.out);
    }
    
    private static void executeHardcodedExamples()
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
