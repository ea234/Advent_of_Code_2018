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
 * ------------------------------------------------------------------------------------------
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
 * Result Part 1 12
 * Result Part 2 0
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * count_2_times_res 248
 * count_3_times_res 31
 * 
 * Result Part 1 7688
 * Result Part 2 0
 * 
 * </pre> 
 */
public class Day02_InventoryManagementSystem
{
  public static void main( String[] args )
  {
    String test_input = "abcdef,bababc,abbcde,abcccd,aabcdd,abcdee,ababab";

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

    long result_part_01 = 0;

    long result_part_02 = 0;

    int count_2_times_res = 0;

    int count_3_times_res = 0;

    for ( String input_str : pListInput )
    {
      int[] ascii_count = new int[ 30 ];

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

    wl( "" );
    wl( "count_2_times_res " + count_2_times_res );
    wl( "count_3_times_res " + count_3_times_res );
    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
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
