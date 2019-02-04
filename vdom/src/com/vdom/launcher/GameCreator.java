package com.vdom.launcher;

import com.vdom.core.Expansion;
import java.util.*;
import java.io.*;

public class GameCreator
{
    public static String[] getGameArgs()
    {
        List<String> args = new ArrayList<String>();

        Scanner in = new Scanner(System.in);

        // First specify which kingdom cards to use
        System.out.println("Dominion Game Launcher");
        System.out.println("========================");
        System.out.println("<1> Set up a new game");
        System.out.println("<2> Play preset");
        System.out.println("<3> View previously played games");
        System.out.println("<4> Replay saved games");

        int selection = -1;
        do
        {
            try
            {
                selection = System.in.read();
            }
            catch (IOException ex)
            {

            }
        }while(selection < (int)'1' || selection > (int)'4');

        /*
        System.out.println();
        System.out.println();
        */

        switch (selection - (int)'0')
        {
            case 1:

            case 2:
                // First make the user select an expansion
                System.out.println("Preset Selection. Enter Expansion:");
                Expansion expansion = null;
                do
                {
                    try
                    {
                        String str = in.nextLine();
                        expansion = Expansion.valueOf(str);
                    }
                    catch (Exception ex)
                    {
                        if(ex instanceof IllegalArgumentException)
                        {
                            System.out.print("Name incorrect. Expansions:");
                            for(Expansion exp: Expansion.values())
                            {
                                System.out.print(" " + exp);
                            }
                            System.out.println();
                        }
                    }
                }while(expansion == null);



                break;
            case 3:
            case 4:
        }

        // TODO fetch arguments here

        return args.toArray(new String[args.size()]);
    }

    private static List<Expansion> selectExpansions()
    {
        // TODO
        return null;
    }
}
