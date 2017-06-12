/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Epulapp
 */
public class TimeFormatter {
    
    public enum TimeChoice {
        MINUTE(60000)
        , SECONDE(1000)
        , MILLISECONDE(1); 

        public int tick;
        TimeChoice(int tick)
        {
            this.tick = tick;
        }
    }
    
    /**
    * Permet de récupérer le temps écoulé depuis le start au format MM:SS (ex : 00:06 | 01:42 | 12:21)
     * @param timeChoice
     * @param value
    * @return le temps écoulé au format string
    */
    public static String getMinuteSecondeFormat(TimeChoice timeChoice, int value)
    {
        return getMinute(timeChoice, value) + ":" + getSeconde(timeChoice, value);	
    }
    
    /**
    * Permet de récupérer le nombre de minute écoulée depuis le start (ex : 01 | 10 | 42)
     * @param timeChoice
     * @param value
    * @return le nombre de minute au format string
    */
    public static String getMinute(TimeChoice timeChoice, int value)
    {
        long time = getSecondeByTimeSet(timeChoice, value);
        long minute = time / 60;

        String min = "";

        if (minute < 10)
                min += "0";

        return min + minute;

    }

    /**
    * Permet de récupérer le nombre de seconde écoulée depuis le start (ex : 01 | 10 | 42)
     * @param timeChoice
     * @param value
    * @return le nombre de seconde au format string
    */
    public static String getSeconde(TimeChoice timeChoice, int value)
    {
        long time = getSecondeByTimeSet(timeChoice, value);
        long minute = time / 60;
        long seconde = time - (minute * 60);

        String sec = "";

        if (seconde < 10)
                sec += "0";

        return sec + seconde;
    }
    
    private static long getSecondeByTimeSet(TimeChoice timeChoice, int value)
    {
        long seconde = -1;
        switch (timeChoice)
        {
            case MILLISECONDE:
                seconde = value / 1000;
                break;
            case MINUTE:
                seconde = value * 60;
                break;
            case SECONDE:
                seconde = value;
                break;
            default:
                seconde = value;
                break;
        }
        
        return seconde;
    }
}
