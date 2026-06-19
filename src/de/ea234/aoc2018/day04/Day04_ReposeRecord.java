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
 * 
 * max_sleep_time_guard_id 99
 * max_sleep_time_minute   45
 * max_sleep_time_value    3
 * 
 *     0    1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25   26   27   28   29   30   31   32   33   34   35   36   37   38   39   40   41   42   43   44   45   46   47   48   49   50   51   52   53   54   55   56   57   58   59
 *     0    0    0    0    0    1    1    1    1    1    1    1    1    1    1    1    1    1    1    1    1    1    1    1    2    1    1    1    1    0    1    1    1    1    1    1    1    1    1    1    2    2    2    2    2    3    2    2    2    2    1    1    1    1    1    0    0    0    0    0
 *    10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   10   99   99   99   99   99   99   99   99   99   99   10   10   10   10   10   10   10   10   10   10
 * 
 * Result Part 1 240
 * Result Part 2 4455
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * 1518-03-19 Guard 2953    Sleep from 37 to 41 =  4 | total sleep time    4
 * 1518-03-20 Guard 457     Sleep from 14 to 26 = 12 | total sleep time   12
 * 1518-03-21 Guard 691     Sleep from 31 to 59 = 28 | total sleep time   28
 * 1518-03-22 Guard 503     Sleep from 17 to 36 = 19 | total sleep time   19
 * 1518-03-23 Guard 223     Sleep from 23 to 55 = 32 | total sleep time   32
 * ...
 * 1518-03-28 Guard 2693    Sleep from 18 to 42 = 24 | total sleep time   24
 * 1518-03-28 Guard 2693    Sleep from 56 to 59 =  3 | total sleep time   27
 * 1518-11-22 Guard 2953    Sleep from 51 to 53 =  2 | total sleep time  465
 * 1518-11-23 Guard 457     Sleep from 24 to 49 = 25 | total sleep time  354
 * 
 * max_sleep_time_guard_id 1069
 * max_sleep_time_minute   30
 * max_sleep_time_value    17
 * 
 *     0    1    2    3    4    5    6    7    8    9   10   11   12   13   14   15   16   17   18   19   20   21   22   23   24   25   26   27   28   29   30   31   32   33   34   35   36   37   38   39   40   41   42   43   44   45   46   47   48   49   50   51   52   53   54   55   56   57   58   59
 *     1    1    2    2    3    3    3    4    4    5    5    6    7    8    8    9   10   11   11   10   10   11   11   11   12   12   13   13   14   15   17   16   14   14   14   14   15   15   15   16   15   13   14   12   13   11   11   12   12   12   12    9   10    9    9    6    6    5    3    0
 *  1069  419  419  419  659  659  419  419  419  419  419  419  419  419  419  419  419  419  419  419  419  419  419  419  419  419  419  419 1069 1069 1069 1069 2953 2953 2953 2953 2953 2953 2953 2953 2953 2953 1699 2953 1699 1699 1699  223 1069  223  223 2953  223  223  223  223 1699 1699 2953 2953
 * 
 * Result Part 1 115167
 * Result Part 2 32070
 * 
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

    int result_part_01       = 0;

    int result_part_02       = 0;

    int minutes_falls_asleep = 0;

    int minutes_wakes_up     = 0;

    HashMap< String, Guard > hash_map = new HashMap< String, Day04_ReposeRecord.Guard >();

    Guard cur_guard = null;

    /*
     * Sorting the input list
     */
    pListInput.sort( null );

    /*
     * Getting the sleep informations from the input list
     */
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

    /*
     * Create a sorted list with the longest sleep time
     */

    List< Guard > guard_list = hash_map.values().stream().sorted( ( a, b ) -> Integer.compare( b.getTotalSleepTime(), a.getTotalSleepTime() ) ).toList();

    /*
     * Getting the result for part 1 from the first Guard
     */
    result_part_01 = guard_list.get( 0 ).getResultPart01();

    /*
     * Part 2
     */

    int[] max_minutes_array_sleeptime = new int[ 60 ];
    int[] max_minutes_array_guard_id  = new int[ 60 ];

    for ( int cur_minute = 0; cur_minute < 60; cur_minute++ )
    {
      int max_val = -1;

      for ( Guard c_guard : guard_list )
      {
        if ( c_guard.getSleepStatusMinute( cur_minute ) > max_val )
        {
          max_val = c_guard.getSleepStatusMinute( cur_minute );

          max_minutes_array_sleeptime[ cur_minute ] = c_guard.getSleepStatusMinute( cur_minute );

          max_minutes_array_guard_id[ cur_minute ] = c_guard.getID();
        }
      }
    }

    String dbg_minute     = "";
    String dbg_sleep_time = "";
    String dbg_guard_id   = "";

    int max_sleep_time_minute   = -1;
    int max_sleep_time_value    = -1;
    int max_sleep_time_guard_id = -1;

    for ( int cur_minute = 0; cur_minute < 60; cur_minute++ )
    {
      dbg_minute     += String.format( " %4d", cur_minute );
      dbg_sleep_time += String.format( " %4d", max_minutes_array_sleeptime[ cur_minute ] );
      dbg_guard_id   += String.format( " %4d", max_minutes_array_guard_id[ cur_minute ] );

      if ( max_minutes_array_sleeptime[ cur_minute ] > max_sleep_time_value )
      {
        max_sleep_time_guard_id = max_minutes_array_guard_id[ cur_minute ];

        max_sleep_time_value    = max_minutes_array_sleeptime[ cur_minute ];

        max_sleep_time_minute   = cur_minute;
      }
    }

    wl( "" );
    wl( "max_sleep_time_guard_id " + max_sleep_time_guard_id );
    wl( "max_sleep_time_minute   " + max_sleep_time_minute   );
    wl( "max_sleep_time_value    " + max_sleep_time_value    );
    wl( "" );
    wl( dbg_minute );
    wl( dbg_sleep_time );
    wl( dbg_guard_id );

    result_part_02 = max_sleep_time_guard_id * max_sleep_time_minute;

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

    public int getSleepStatusMinute( int pMinute )
    {
      return minutes[ pMinute ];
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

    public int getResultPart01()
    {
      return id * getMinute();
    }

    public String toString()
    {
      return String.format( "%-5d - Total Sleep Time %4d - Max-Minute = %2d,  Result %d ", id, sleep_time_total, getMinute(), getResultPart01() );
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
