package com.vdom.launcher;

import com.vdom.api.Card;
import com.vdom.api.GameType;
import com.vdom.core.CardSet;
import com.vdom.core.Cards;
import com.vdom.core.Expansion;
import java.util.*;
import java.io.*;

public class GameCreator
{
    public static String[] getGameArgs()
    {
        /*
         * Use File I/O to keep previously played card sets
         * The files will be placed in the topmost directory(androminion/vdom/filename)
         */

        // First determine the set of cards to be used
        List<String> args = new ArrayList<String>();
        GameType type = null;

        System.out.println("Dominion Game Launcher");
        System.out.println("========================");
        do  // First retrieve game type. Do not forget to add "Specified" for manual card selection.
        {
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
            } while (selection < (int) '1' || selection > (int) '4');

        /*
        System.out.println();
        System.out.println();
        */

            switch (selection - (int) '0')
            {
                case 1:
                    new Cards();    // to invoke a call to the static initializer.
                    /*
                    for(Card card : Expansion.Empires.getKingdomCards())
                        System.out.print(card);
                    */

                case 2:
                    // First make the user select an expansion
                    System.out.println("Preset Selection. Enter Expansion:");
                    Expansion expansion = (Expansion) selectOne(Expansion.values());

                    List<GameType> presets = new ArrayList<GameType>();
                    for (GameType t : GameType.values())
                    {
                        List<Expansion> exps = t.getExpansions();
                        if (exps.size() > 0 && exps.get(0) == expansion)
                            presets.add(t);
                    }

                    type = (GameType)selectOne(presets.toArray());
                    break;
                case 3:
                    try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                            new FileOutputStream("filename.txt"), "utf-8"))) {
                        writer.write("something");
                    }
                    catch (IOException ex)
                    {

                    }
                case 4:
            }
        }while(type == null);
        args.add("-type" + type.toString());

        // TODO fetch arguments here


        return args.toArray(new String[args.size()]);
    }

    private static List<Expansion> selectExpansions()
    {
        // TODO
        return null;
    }

    private static Object selectOne(Object[] list)
    {
        Scanner in = new Scanner(System.in);

        for(int i = 0; i < list.length; i++)
        {
            System.out.println("<" + (i + 1) +"> " + objectInfo(list[i]));
        }

        int selection = 0;
        do
        {
            try
            {
                selection = Integer.parseInt(in.nextLine());
            }
            catch(NumberFormatException ex) {}
        }while(selection <= 0 || selection > list.length);

        return list[selection - 1];
    }

    private static String objectInfo(Object object)
    {
        if(object instanceof GameType)
        {
            GameType type = (GameType)object;
            StringBuilder str = new StringBuilder();
            str.append(type.getName());
            str.append("(");
            boolean first = true;
            for (Expansion expansion : type.getExpansions())
            {
                if(first)
                   first = false;
                else
                    str.append(",");
                str.append(expansion.toString());
            }
            str.append("): ");

            first = true;
            List<Card> cards = CardSet.getCardSetMap().get(type).getCards();
            for(Card card : cards)
            {
                if(first)
                    first = false;
                else
                    str.append(",");
                str.append(card.getName());
            }

            return str.toString();
        }

        return object.toString();
    }
}
