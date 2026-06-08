package de.ea234.aoc2018.day08;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 8: Memory Maneuver ---
 * https://adventofcode.com/2018/day/8
 * 
 * https://www.reddit.com/r/adventofcode/comments/a47ubw/2018_day_8_solutions/
 * 
 * </pre> 
 */
public class Day08_MemoryManeuver
{
  public static void main( String[] args )
  {
    //                  AAA   BBBBBBBBBBBB   CCC   DDDDDD   CC  AAAAAAAAA
    //calculatePart01( "2 3   0 3 10 11 12   1 1   0 1 99    2    1  1  2", true );
    //calculatePart01( "2 3   0 3 20 20 20   1 1   0 1 20   20   20 20 20", true );

    calculatePart01( "1 1    2 2   0 3 30 30 30   0 4 40 40 40 40   0 5 50 50 50 50 50    20 20   10", true );

    System.exit( 0 );
  }

  private static void calculatePart01( String pString, boolean pKnzDebug )
  {
    List< Integer > converted_integer_list = Arrays.stream( pString.split( "\\s+" ) ).map( Integer::parseInt ).collect( Collectors.toList() );

    if ( pKnzDebug )
    {
      int max_index_dbg = Math.min( converted_integer_list.size(), 30 );

      for ( int metadata_index_cur = 0; metadata_index_cur < max_index_dbg; metadata_index_cur++ )
      {
        wl( String.format( "List Index %5d - %10d ", metadata_index_cur, converted_integer_list.get( metadata_index_cur ) ) );
      }
    }

    long sum_metadata = calcSum( converted_integer_list, 0, converted_integer_list.size() - 1, pKnzDebug );

    wl( "converted_integer_list.size() " + converted_integer_list.size() );
    wl( "sum meta " + sum_metadata );
  }

  private static long calcSum( List< Integer > pListInput, int pStartIndex, int pEndIndex, boolean pKnzDebug )
  {
    if ( pStartIndex == -1 ) return 0l;

    if ( pEndIndex == -1 ) return 0l;

    if ( pEndIndex <= pStartIndex ) return 0l;

    /*
     * ********************************************************************************* 
     * Handling the current node
     * 
     * 1. Read the quantity of child nodes
     * 
     * 2. Read the quantity of metadata entries
     * 
     * 3. Calculate the size of the data
     * 
     * 4. Set the start index for the metadata to the given index from "pEndIndex"
     * 
     * 5. If the node has no child nodes, the given index of "pEndIndex" is not valid.
     * 
     *    No Child nodes, no embedded child nodes, the metadata starts behind the header.
     *    
     *    In this case, the metadata ends at start plus the size of the data.
     *    
     * 6. Calculate the end-index for the metadata
     *   
     */

    int quantity_of_child_nodes = pListInput.get( pStartIndex ).intValue();

    int quantity_of_metadata_entries = pListInput.get( pStartIndex + 1 ).intValue();

    int size_of_data = ( 2 + quantity_of_child_nodes );

    int metadata_index_start = pEndIndex;

    if ( quantity_of_child_nodes == 0 )
    {
      metadata_index_start = pStartIndex + size_of_data;
    }

    int metadata_index_end = metadata_index_start - quantity_of_metadata_entries;

    /*
     * ********************************************************************************* 
     * Calculate the sum of metadata for the current node
     * 
     */

    if ( pKnzDebug )
    {
      wl( "" );
      wl( String.format( "CalcSum  From %5d - %5d ", metadata_index_end + 1, metadata_index_start ) );
    }

    long result_sum = 0;

    for ( int metadata_index_cur = metadata_index_start; metadata_index_cur > metadata_index_end; metadata_index_cur-- )
    {
      if ( pKnzDebug )
      {
        wl( String.format( "CalcSum  %5d - %10d ", metadata_index_cur, pListInput.get( metadata_index_cur ) ) );
      }

      result_sum += pListInput.get( metadata_index_cur ).intValue();
    }

    /*
     * ********************************************************************************* 
     * Doing some recursive stuff
     */
    if ( ( pStartIndex + size_of_data ) < metadata_index_end )
    {
      if ( quantity_of_child_nodes == 0 )
      {
        result_sum += calcSum( pListInput, metadata_index_start, pEndIndex, pKnzDebug );
      }
      else
      {
        result_sum += calcSum( pListInput, pStartIndex + 2, metadata_index_end, pKnzDebug );
      }
    }

    return result_sum;
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