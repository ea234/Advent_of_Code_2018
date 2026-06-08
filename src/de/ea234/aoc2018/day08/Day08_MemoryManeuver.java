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
 * AAA   BBBBBBBBBBBB   CCC   DDDDDD   CC  AAAAAAAAA
 * 2 3   0 3 10 11 12   1 1   0 1 99    2    1  1  2
 * 2 3   0 3 20 20 20   1 1   0 1 20   20   20 20 20
 * 
 * 
 * 
 * --------------------------------------------------------------------
 * 
 * List Input 29
 * 
 * List Index     0 -          1 
 * List Index     1 -          1 
 * List Index     2 -          2 
 * List Index     3 -          2 
 * List Index     4 -          0 
 * List Index     5 -          3 
 * List Index     6 -         30 
 * List Index     7 -         30 
 * List Index     8 -         30 
 * List Index     9 -          0 
 * List Index    10 -          4 
 * List Index    11 -         40 
 * List Index    12 -         40 
 * List Index    13 -         40 
 * List Index    14 -         40 
 * List Index    15 -          1 
 * List Index    16 -          5 
 * List Index    17 -          0 
 * List Index    18 -          2 
 * List Index    19 -         60 
 * List Index    20 -         60 
 * List Index    21 -         50 
 * List Index    22 -         50 
 * List Index    23 -         50 
 * List Index    24 -         50 
 * List Index    25 -         50 
 * List Index    26 -         20 
 * List Index    27 -         20 
 * List Index    28 -         10 
 * 
 * Node Info Start     0  End    28    quantity_of_child_nodes    1    quantity_of_metadata_entries    1 
 * 
 * CalcSum   From     28 -    28 
 * CalcSum            28 -    10 
 * 
 * Node Info Start     2  End    27    quantity_of_child_nodes    2    quantity_of_metadata_entries    2 
 * 
 * CalcSum   From     26 -    27 
 * CalcSum            27 -    20 
 * CalcSum            26 -    20 
 * 
 * Node Info Start     4  End    25    quantity_of_child_nodes    0    quantity_of_metadata_entries    3 
 * 
 * CalcSum   From      6 -     8 
 * CalcSum             8 -    30 
 * CalcSum             7 -    30 
 * CalcSum             6 -    30 
 * 
 * Node Info Start     9  End    25    quantity_of_child_nodes    0    quantity_of_metadata_entries    4 
 * 
 * CalcSum   From     11 -    14 
 * CalcSum            14 -    40 
 * CalcSum            13 -    40 
 * CalcSum            12 -    40 
 * CalcSum            11 -    40 
 * 
 * Node Info Start    15  End    25    quantity_of_child_nodes    1    quantity_of_metadata_entries    5 
 * 
 * CalcSum   From     21 -    25 
 * CalcSum            25 -    50 
 * CalcSum            24 -    50 
 * CalcSum            23 -    50 
 * CalcSum            22 -    50 
 * CalcSum            21 -    50 
 * 
 * Node Info Start    17  End    20    quantity_of_child_nodes    0    quantity_of_metadata_entries    2 
 * 
 * CalcSum   From     19 -    20 
 * CalcSum            20 -    60 
 * CalcSum            19 -    60 
 * 
 * Result Part 01 670
 * 
 * 
 * --------------------------------------------------------------------
 * 
 * List Input 16
 * 
 * List Index     0 -          2 
 * List Index     1 -          3 
 * List Index     2 -          0 
 * List Index     3 -          3 
 * List Index     4 -         10 
 * List Index     5 -         11 
 * List Index     6 -         12 
 * List Index     7 -          1 
 * List Index     8 -          1 
 * List Index     9 -          0 
 * List Index    10 -          1 
 * List Index    11 -         99 
 * List Index    12 -          2 
 * List Index    13 -          1 
 * List Index    14 -          1 
 * List Index    15 -          2 
 * 
 * Node Info Start     0  End    15    quantity_of_child_nodes    2    quantity_of_metadata_entries    3 
 * 
 * CalcSum   From     13 -    15 
 * CalcSum            15 -     2 
 * CalcSum            14 -     1 
 * CalcSum            13 -     1 
 * 
 * Node Info Start     2  End    12    quantity_of_child_nodes    0    quantity_of_metadata_entries    3 
 * 
 * CalcSum   From      4 -     6 
 * CalcSum             6 -    12 
 * CalcSum             5 -    11 
 * CalcSum             4 -    10 
 * 
 * Node Info Start     7  End    12    quantity_of_child_nodes    1    quantity_of_metadata_entries    1 
 * 
 * CalcSum   From     12 -    12 
 * CalcSum            12 -     2 
 * 
 * Node Info Start     9  End    11    quantity_of_child_nodes    0    quantity_of_metadata_entries    1 
 * 
 * CalcSum   From     11 -    11 
 * CalcSum            11 -    99 
 * 
 * Result Part 01 138
 * 
 * 
 * Result Part 01 36745
 * </pre> 
 */
public class Day08_MemoryManeuver
{
  public static void main( String[] args )
  {
    //calculatePart01( "1 1    2 2   0 3 30 30 30   0 4 40 40 40 40   1 5   0 2 60 60   50 50 50 50 50    20 20   10", true );

    //                  AAA   BBBBBBBBBBBB   CCC   DDDDDD   CC  AAAAAAAAA
    //calculatePart01( "2 3   0 3 10 11 12   1 1   0 1 99    2    1  1  2", true );
    //calculatePart01( "2 3   0 3 20 20 20   1 1   0 1 20   20   20 20 20", true );

    calculatePart01( getListProd(), false );

    System.exit( 0 );
  }

  private static void calculatePart01( String pString, boolean pKnzDebug )
  {
    List< Integer > converted_integer_list = Arrays.stream( pString.split( "\\s+" ) ).map( Integer::parseInt ).collect( Collectors.toList() );

    if ( pKnzDebug )
    {
      wl( "" );
      wl( "--------------------------------------------------------------------" );
      wl( "" );
      wl( "List Input " + converted_integer_list.size() );
      wl( "" );

      int max_index_dbg = Math.min( converted_integer_list.size(), 30 );

      for ( int metadata_index_cur = 0; metadata_index_cur < max_index_dbg; metadata_index_cur++ )
      {
        wl( String.format( "List Index %5d - %10d ", metadata_index_cur, converted_integer_list.get( metadata_index_cur ) ) );
      }
    }

    long sum_metadata = calcSum( converted_integer_list, 0, converted_integer_list.size() - 1, pKnzDebug );

    wl( "" );
    wl( "Result Part 01 " + sum_metadata );
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
     */

    int quantity_of_child_nodes      = pListInput.get( pStartIndex ).intValue();

    int quantity_of_metadata_entries = pListInput.get( pStartIndex + 1 ).intValue();

    int size_of_data = ( 2 + quantity_of_metadata_entries );

    int metadata_index_start = pEndIndex;

    if ( quantity_of_child_nodes == 0 )
    {
      metadata_index_start = ( pStartIndex + size_of_data ) - 1;
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
      wl( String.format( "Node Info Start %5d  End %5d    quantity_of_child_nodes %4d    quantity_of_metadata_entries %4d ", pStartIndex, pEndIndex, quantity_of_child_nodes, quantity_of_metadata_entries ) );
      wl( "" );
      wl( String.format( "CalcSum   From  %5d - %5d ", metadata_index_end + 1, metadata_index_start ) );
    }

    long result_sum = 0;

    for ( int metadata_index_cur = metadata_index_start; metadata_index_cur > metadata_index_end; metadata_index_cur-- )
    {
      if ( pKnzDebug )
      {
        wl( String.format( "CalcSum         %5d - %5d ", metadata_index_cur, pListInput.get( metadata_index_cur ) ) );
      }

      result_sum += pListInput.get( metadata_index_cur ).intValue();
    }

    /*
     * ********************************************************************************* 
     * Doing some recursive stuff
     */

    if ( ( quantity_of_child_nodes == 0 ) && ( ( metadata_index_start + 1 ) < pEndIndex ) )
    {
      /*
       * If the quantity of child nodes is 0, then 
       * ... jump over the current node 
       *     which is the start index for the metadata plus 1
       *     the end index is still the index from above
       */
      result_sum += calcSum( pListInput, metadata_index_start + 1, pEndIndex, pKnzDebug );
    }
    else 
    {
      /*
       * If the quantity of child nodes is greater than 0, then 
       * ... jump over the header info from the current node 
       *     which is the start index from "pStartIndex" plus 2
       *     
       *     the end index is the end index for the metadata, which is 
       *     already 1 before the actual end for the current node metadata. 
       */
      result_sum += calcSum( pListInput, pStartIndex + 2, metadata_index_end, pKnzDebug );
    }

    return result_sum;
  }

  private static String getListProd()
  {
    String string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2018__day08_input.txt";

    try
    {
      string_array = Files.readString( Path.of( datei_input ) );
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
