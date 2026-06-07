package de.ea234.aoc2018.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 1: Chronal Calibration ---
 * https://adventofcode.com/2018/day/1
 * 
 * https://www.reddit.com/r/adventofcode/comments/a20646/2018_day_1_solutions/
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * A Nr     0   Current frequency           0, change of     1; resulting frequency           1.
 * A Nr     1   Current frequency           1, change of    -2; resulting frequency          -1.
 * A Nr     2   Current frequency          -1, change of     3; resulting frequency           2.
 * A Nr     3   Current frequency           2, change of     1; resulting frequency           3.
 * B Nr     4   Current frequency           3, change of     1; resulting frequency           4.
 * B Nr     5   Current frequency           4, change of    -2; resulting frequency           2, which has already been seen in step 2
 * 
 * 
 * Result Part 1 3
 * Result Part 2 2
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * A Nr     0   Current frequency           0, change of     3; resulting frequency           3.
 * A Nr     1   Current frequency           3, change of     3; resulting frequency           6.
 * A Nr     2   Current frequency           6, change of     4; resulting frequency          10.
 * A Nr     3   Current frequency          10, change of    -2; resulting frequency           8.
 * A Nr     4   Current frequency           8, change of    -4; resulting frequency           4.
 * B Nr     5   Current frequency           4, change of     3; resulting frequency           7.
 * B Nr     6   Current frequency           7, change of     3; resulting frequency          10, which has already been seen in step 2
 * 
 * 
 * Result Part 1 4
 * Result Part 2 10
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * B Nr 142990   Current frequency       59898, change of -59243; resulting frequency         655, which has already been seen in step 342
 * 
 * 
 * Result Part 1 437
 * Result Part 2 655
 * 
 * </pre> 
 */
public class Day01_ChronalCalibration
{
  public static void main( String[] args )
  {
    calculatePart01( "+1, -2, +3, +1,",   true );

    calculatePart01( "+3, +3, +4, -2, -4", true );

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

    long cur_frequenzy  = 0;
    
    long result_part_01 = 0;

    long result_part_02 = Long.MAX_VALUE;

    /*
     * *******************************************************************************************************
     * Initializing the grid
     * *******************************************************************************************************
     */

    Properties prop_freqenzy_mem = new Properties();

    int count_nr = 0;

    for ( String input_str : pListInput )
    {
      long long_val = Long.valueOf( input_str ).longValue();

      if ( pKnzDebug )
      {
        wl( String.format( "A Nr %5d   Current frequency  %10d, change of %5d; resulting frequency  %10d.", count_nr, cur_frequenzy, long_val, ( cur_frequenzy + long_val ) ) );
      }

      cur_frequenzy += long_val;

      if ( ( result_part_02 == Long.MAX_VALUE ) && ( prop_freqenzy_mem.getProperty( "FREQ_" + cur_frequenzy ) != null ) )
      {
        result_part_02 = cur_frequenzy;
      }

      prop_freqenzy_mem.setProperty( "FREQ_" + cur_frequenzy, "" + count_nr );

      count_nr++;
    }

    result_part_01 = cur_frequenzy;

    while ( result_part_02 == Long.MAX_VALUE )
    {
      for ( String input_str : pListInput )
      {
        long long_val  = Long.valueOf( input_str ).longValue();

        cur_frequenzy += long_val;

        if ( ( result_part_02 == Long.MAX_VALUE ) && ( prop_freqenzy_mem.getProperty( "FREQ_" + cur_frequenzy ) != null ) )
        {
          wl( String.format( "B Nr %5d   Current frequency  %10d, change of %5d; resulting frequency  %10d, which has already been seen in step " + prop_freqenzy_mem.getProperty( "FREQ_" + cur_frequenzy ), count_nr, ( cur_frequenzy - long_val ), long_val, cur_frequenzy ) );

          result_part_02 = cur_frequenzy;

          break;
        }
        else
        {
          if ( pKnzDebug )
          {
            wl( String.format( "B Nr %5d   Current frequency  %10d, change of %5d; resulting frequency  %10d.", count_nr, ( cur_frequenzy - long_val ), long_val, cur_frequenzy ) );
          }
        }

        prop_freqenzy_mem.setProperty( "FREQ_" + cur_frequenzy, "" + count_nr );

        count_nr++;
      }
    }

    wl( "" );
    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
    wl( "" );
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2018__day01_input.txt";

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
}
