package de.ea234.aoc2018.day02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 2: Inventory Management System ---
 * https://adventofcode.com/2018/day/2
 * 
 * https://www.reddit.com/r/adventofcode/comments/a2aimr/2018_day_2_solutions/
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * abcdef  Two Times 0   Three Times 0 
 * bababc  Two Times 1   Three Times 1 
 * abbcde  Two Times 1   Three Times 0 
 * abcccd  Two Times 0   Three Times 1 
 * aabcdd  Two Times 1   Three Times 0 
 * abcdee  Two Times 1   Three Times 0 
 * ababab  Two Times 0   Three Times 1 
 * 
 * count_2_times_res 4
 * count_3_times_res 3
 * 
 * input_a abcdee
 * input_b abcdef
 * 
 * Result Part 1 12
 * Result Part 2 abcde
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * abcde  Two Times 0   Three Times 0 
 * fghij  Two Times 0   Three Times 0 
 * klmno  Two Times 0   Three Times 0 
 * pqrst  Two Times 0   Three Times 0 
 * fguij  Two Times 0   Three Times 0 
 * axcye  Two Times 0   Three Times 0 
 * wvxyz  Two Times 0   Three Times 0 
 * 
 * count_2_times_res 0
 * count_3_times_res 0
 * 
 * input_a fghij
 * input_b fguij
 * 
 * Result Part 1 0
 * Result Part 2 fgij
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * count_2_times_res 248
 * count_3_times_res 31
 * 
 * input_a lsrivmotzbdxpkxnaqmuwcgchj
 * input_b lsrivmotzbdxpkxnaqmuwcychj
 * 
 * Result Part 1 7688
 * Result Part 2 lsrivmotzbdxpkxnaqmuw
 * 
 * </pre> 
 */
public class Day02_InventoryManagementSystem
{
  public static void main( String[] args )
  {

    calculatePart01( "abcdef,bababc,abbcde,abcccd,aabcdd,abcdee,ababab", true );

    calculatePart01( "abcde,fghij,klmno,pqrst,fguij,axcye,wvxyz",        true );

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

    long result_part_01 = 0;

    String result_part_02 = "";

    int count_2_times_res = 0;

    int count_3_times_res = 0;

    for ( String input_str : pListInput )
    {
      /*
       * Create a vector for character add ups
       */
      int[] ascii_count = new int[ 30 ];

      /*
       * Iterate over the input string and add up the char-frequenzies
       */
      for ( int idx = 0; idx < input_str.length(); idx++ )
      {
        ascii_count[ ( (int) input_str.charAt( idx ) ) - 97 ]++;
      }

      int count_2_times_cur = 0;

      int count_3_times_cur = 0;

      for ( int idx = 0; idx < ascii_count.length; idx++ )
      {
        if ( ascii_count[ idx ] == 2 ) count_2_times_cur = 1;

        if ( ascii_count[ idx ] == 3 ) count_3_times_cur = 1;
      }

      count_2_times_res += count_2_times_cur;

      count_3_times_res += count_3_times_cur;

      if ( pKnzDebug )
      {
        wl( String.format( "%s  Two Times %d   Three Times %d ", input_str, count_2_times_cur, count_3_times_cur ) );
      }
    }

    result_part_01 = count_2_times_res * count_3_times_res;

    pListInput.sort( null );

    int error_count = 0;

    String input_a = null;
    String input_b = null;

    for ( int index_input = 1; index_input < pListInput.size(); index_input++ )
    {
      input_a = pListInput.get( index_input - 1 );

      input_b = pListInput.get( index_input );

      error_count = 0;

      for ( int idx = 0; idx < input_a.length(); idx++ )
      {
        if ( input_a.charAt( idx ) == input_b.charAt( idx ) )
        {
          // OK
        }
        else
        {
          /*
           * Increase the error count.
           */
          error_count++;

          /*
           * If the error count is 2, the check of the 2 strings is over.
           */
          if ( error_count == 2 )
          {
            break;
          }
        }
      }

      /*
       * If there is only 1 error count, the result strings are found
       */
      if ( error_count == 1 )
      {
        break;
      }
    }

    for ( int idx = 0; idx < input_a.length(); idx++ )
    {
      if ( input_a.charAt( idx ) == input_b.charAt( idx ) )
      {
        result_part_02 += "" + input_a.charAt( idx );
      }
    }

    wl( "" );
    wl( "count_2_times_res " + count_2_times_res );
    wl( "count_3_times_res " + count_3_times_res );
    wl( "" );
    wl( "input_a " + input_a );
    wl( "input_b " + input_b );
    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2018__day02_input.txt";

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
