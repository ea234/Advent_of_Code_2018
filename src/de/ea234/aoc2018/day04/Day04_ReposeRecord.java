package de.ea234.aoc2018.day04;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 4: Repose Record ---
 * https://adventofcode.com/2018/day/4
 * 
 * https://www.reddit.com/r/adventofcode/comments/a2xef8/2018_day_4_solutions/
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * 1518-11-01 Guard 10      Sleep from  5 to 25 = 20 | total sleep time   20
 * 1518-11-01 Guard 10      Sleep from 30 to 55 = 25 | total sleep time   45
 * 1518-11-02 Guard 99      Sleep from 40 to 50 = 10 | total sleep time   10
 * 1518-11-03 Guard 10      Sleep from 24 to 29 =  5 | total sleep time   50
 * 1518-11-04 Guard 99      Sleep from 36 to 46 = 10 | total sleep time   20
 * 1518-11-05 Guard 99      Sleep from 45 to 55 = 10 | total sleep time   30
 * 10    - Total Sleep Time   50 - Max-Minute = 24,  Result 240 
 * 99    - Total Sleep Time   30 - Max-Minute = 45,  Result 4455 
 * 
 * Result Part 1 240
 * Result Part 2 0
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * 1518-03-19 Guard 2953    Sleep from 37 to 41 =  4 | total sleep time    4
 * 1518-11-22 Guard 2953    Sleep from 51 to 53 =  2 | total sleep time  465
 * 1518-11-23 Guard 457     Sleep from 24 to 49 = 25 | total sleep time  354
 *
 * 2953  - Total Sleep Time  465 - Max-Minute = 39,  Result 115167 
 * 419   - Total Sleep Time  457 - Max-Minute = 36,  Result 15084 
 * 1699  - Total Sleep Time  453 - Max-Minute = 42,  Result 71358 
 * 1069  - Total Sleep Time  448 - Max-Minute = 30,  Result 32070 
 * 659   - Total Sleep Time  387 - Max-Minute = 30,  Result 19770 
 * 223   - Total Sleep Time  366 - Max-Minute = 43,  Result 9589 
 * 457   - Total Sleep Time  354 - Max-Minute = 25,  Result 11425 
 * 1933  - Total Sleep Time  337 - Max-Minute = 25,  Result 48325 
 * 2039  - Total Sleep Time  306 - Max-Minute = 37,  Result 75443 
 * 691   - Total Sleep Time  297 - Max-Minute = 31,  Result 21421 
 * 179   - Total Sleep Time  274 - Max-Minute = 33,  Result 5907 
 * 1153  - Total Sleep Time  245 - Max-Minute = 47,  Result 54191 
 * 2693  - Total Sleep Time  230 - Max-Minute = 33,  Result 88869 
 * 3547  - Total Sleep Time  226 - Max-Minute = 20,  Result 70940 
 * 647   - Total Sleep Time  200 - Max-Minute = 32,  Result 20704 
 * 401   - Total Sleep Time  161 - Max-Minute = 34,  Result 13634 
 * 503   - Total Sleep Time  151 - Max-Minute = 33,  Result 16599 
 * 409   - Total Sleep Time  149 - Max-Minute = 41,  Result 16769 
 * 163   - Total Sleep Time  147 - Max-Minute = 15,  Result 2445 
 * 2753  - Total Sleep Time  120 - Max-Minute = 41,  Result 112873 
 * 683   - Total Sleep Time    0 - Max-Minute =  0,  Result 0 
 * 3347  - Total Sleep Time    0 - Max-Minute =  0,  Result 0 
 * 1637  - Total Sleep Time    0 - Max-Minute =  0,  Result 0 
 * 
 * Result Part 1 115167
 * Result Part 2 0
 * </pre> 
 */
public class Day04_ReposeRecord
{
  public static void main( String[] args )
  {
    String test_input = "";

    test_input += "[1518-11-01 00:00] Guard #10 begins shift";
    test_input += ",[1518-11-01 00:05] falls asleep";
    test_input += ",[1518-11-01 00:25] wakes up";
    test_input += ",[1518-11-01 00:30] falls asleep";
    test_input += ",[1518-11-01 00:55] wakes up";
    test_input += ",[1518-11-03 00:05] Guard #10 begins shift";
    test_input += ",[1518-11-03 00:24] falls asleep";
    test_input += ",[1518-11-05 00:03] Guard #99 begins shift";
    test_input += ",[1518-11-05 00:45] falls asleep";
    test_input += ",[1518-11-03 00:29] wakes up";
    test_input += ",[1518-11-04 00:02] Guard #99 begins shift";
    test_input += ",[1518-11-02 00:40] falls asleep";
    test_input += ",[1518-11-04 00:46] wakes up";
    test_input += ",[1518-11-01 23:58] Guard #99 begins shift";
    test_input += ",[1518-11-04 00:36] falls asleep";
    test_input += ",[1518-11-02 00:50] wakes up";
    test_input += ",[1518-11-05 00:55] wakes up";

    calculatePart01( test_input, true );

    calculate01( getListProd(), false );

    System.exit( 0 );
  }

  private static void calculatePart01( String pString, boolean pKnzDebug )
  {
    List< String > converted_string_list = Arrays.stream( pString.split( "," ) ).map( String::trim ).collect( Collectors.toList() );

    calculate01( converted_string_list, pKnzDebug );
  }

  private static void calculate01( List< String > pListInput, boolean pKnzDebug )
  {
    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );

    int result_part_01 = 0;

    int result_part_02 = 0;

    int minutes_falls_asleep = 0;

    int minutes_wakes_up = 0;

    HashMap< String, Guard > hash_map = new HashMap< String, Day04_ReposeRecord.Guard >();

    Guard cur_guard = null;

    pListInput.sort( null );

    for ( String input_str : pListInput )
    {
      if ( input_str.contains( "begins shift" ) )
      {
        String guard_id = getString( input_str, "#", "begins shift" );

        cur_guard = hash_map.get( guard_id );

        if ( cur_guard == null )
        {
          cur_guard = new Guard( guard_id );

          hash_map.put( guard_id, cur_guard );
        }
      }
      else if ( input_str.contains( "falls" ) )
      {
        minutes_falls_asleep = Integer.parseInt( input_str.substring( 15, 17 ) );
      }
      else if ( input_str.contains( "wakes" ) )
      {
        minutes_wakes_up = Integer.parseInt( input_str.substring( 15, 17 ) );

        cur_guard.addSleppTime( input_str.substring( 1, 11 ), minutes_falls_asleep, minutes_wakes_up );
      }
    }

    List< Guard > guard_list = hash_map.values().stream().sorted( ( a, b ) -> Integer.compare( b.getTotalSleepTime(), a.getTotalSleepTime() ) ).toList();

    for ( Guard c_guard : guard_list )
    {
      wl( c_guard.toString() );
    }

    result_part_01 = guard_list.get( 0 ).getResult1();

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
  }

  private static class Guard
  {
    int   id               = 0;

    int   sleep_time_total = 0;

    int[] minutes          = new int[ 60 ];

    public Guard( String pID )
    {
      id = Integer.parseInt( pID.trim() );
    }

    public void addSleppTime( String pDate, int pMinuteFallsAsleep, int pMinuteWakesUp )
    {
      long sleep_time = pMinuteWakesUp - pMinuteFallsAsleep;

      sleep_time_total += sleep_time;

      for ( int cur_minute = pMinuteFallsAsleep; cur_minute < pMinuteWakesUp; cur_minute++ )
      {
        minutes[ cur_minute ]++;
      }

      wl( String.format( "%s Guard %-5d   Sleep from %2d to %2d = %2d | total sleep time %4d", pDate, id, pMinuteFallsAsleep, pMinuteWakesUp, sleep_time, sleep_time_total ) );
    }

    public int getTotalSleepTime()
    {
      return sleep_time_total;
    }

    public int getID()
    {
      return id;
    }

    public int getMinute()
    {
      int max_minute = 0;

      int max_val = -1;

      for ( int cur_minute = 0; cur_minute < 60; cur_minute++ )
      {
        if ( minutes[ cur_minute ] > max_val )
        {
          max_minute = cur_minute;
          
          max_val = minutes[ cur_minute ];
        }
      }

      return max_minute;
    }

    public int getResult1()
    {
      return id * getMinute();
    }

    public String toString()
    {
      return String.format( "%-5d - Total Sleep Time %4d - Max-Minute = %2d,  Result %d ", id, sleep_time_total, getMinute(), getResult1() );
    }
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2018__day04_input.txt";

    try
    {
      string_array = Files.readAllLines( Path.of( datei_input ) );
    }
    catch ( IOException e )
    {
      e.printStackTrace();
    }

    return string_array;
  }

  private static void wl( String pString ) // wl = short for "write log"
  {
    System.out.println( pString );
  }

  /**
   * <pre>
   * Sucht in pString die Start- und die Endzeichenfolge und gibt den 
   * "eingeschlossenen" Teilstring zurueck. 
   * 
   * Es wird ein String zurueckgeben, wenn die Start- und Endzeichenfolge 
   * gefunden wurden und hintereinander stehen. Es erfolgt keine automatische
   * Umkehrung, wenn der Start hinter dem Ende gefunden wird.  
   * In allen anderen Faellen wird null zurueckgegeben. 
   * 
   * FkString.Mid( "1234567890", "5", "6" )                                = ""
   * FkString.Mid( "1234567890", "6", "5" )                                = null
   * FkString.Mid( "1234567890", "4", "7" )                                = "56"
   * FkString.Mid( "1234567890", "3", "8" )                                = "4567"
   * 
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", "Zwei",  "Fuenf"  )  = " Drei Vier "
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", "Fuenf", "Zwei"   )  = null
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", "Zwei",  "Neun"   )  = null
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", "Null",  "Fuenf"  )  = null
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", "Null",  "Neun"   )  = null
   * FkString.Mid( null,                              "Zwei",  "Fuenf"  )  = null
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", null,    "Fuenf"  )  = null
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", "Zwei",  null     )  = null
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", null,    null     )  = null
   * FkString.Mid( "Eins Zwei Drei Vier Fuenf Sechs", "Sechs", "Fuenf"  )  = null
   * </pre>
   * 
   * @param pString der String mit dem herauszutrennenden Teilstring
   * @param pAbString Suchzeichenfolge fuer den Start
   * @param pBisString Suchzeichenfolge fuer das Ende
   * @return den Teilstring, wenn Start und Ende gefunden wurden, sonst null
   */
  private static String getString( String pString, String pAbString, String pBisString )
  {
    /*
     * Pruefung: Sind die Parameter ungleich null?
     */
    if ( ( pString != null ) && ( pAbString != null ) && ( pBisString != null ) )
    {
      /*
       * Position der Startzeichenfolge suchen.
       */
      int ab_position = pString.indexOf( pAbString );

      /*
       * Pruefung: Startzeichenfolge gefunden?
       * Wurde die Startzeichenfolge nicht gefunden, bekommt der Aufrufer "null" zurueck.
       */
      if ( ab_position >= 0 )
      {
        /* 
         * Wurde die Startzeichenfolge gefunden, muss die Ab-Position um die 
         * Laenge der Startzeichenfolge erhoeht werden. Die Startzeichenfolge 
         * ist nicht Bestandteil der Rueckgabe.  
         */
        ab_position += pAbString.length();

        /*
         * Die Bis-Zeichenfolge wird ab der Ab-Positon gesucht.
         */
        int bis_position = pString.indexOf( pBisString, ab_position );

        /*
         * Pruefung: Bis-Zeichenfolge gefunden?
         * Wurde zwar die Startzeichenfolge gefunden, aber die Bis-Zeichenfolge 
         * nicht im Suchstring enthalten ist, bekommt der Aufrufer ebenfalls null zurueck.
         */
        if ( bis_position >= 0 )
        {
          /*
           * Rueckgabe des eingeschlossenen Strings
           */
          return pString.substring( ab_position, bis_position );
        }
      }
    }

    /*
     * Als Standardrueckgabe wird eine null-Referenz zurueckgegeben. 
     */
    return null;
  }

}
