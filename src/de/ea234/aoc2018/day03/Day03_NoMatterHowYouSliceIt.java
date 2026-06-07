package de.ea234.aoc2018.day03;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 3: No Matter How You Slice It ---
 * https://adventofcode.com/2018/day/3
 * 
 * https://www.reddit.com/r/adventofcode/comments/a2lesz/2018_day_3_solutions/
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * claim id 1            row   1  col   3   width    4    height    4 
 * claim id 2            row   3  col   1   width    4    height    4 
 * claim id 3            row   5  col   5   width    2    height    2 
 * 
 * Result Part 1 4
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * Result Part 1 115304
 * 
 * </pre> 
 */
public class Day03_NoMatterHowYouSliceIt
{
  public static void main( String[] args )
  {
    calculatePart01( "#1 @ 1,3: 4x4;#2 @ 3,1: 4x4;#3 @ 5,5: 2x2", true );

    calculate01( getListProd(), false );

    System.exit( 0 );
  }

  private static void calculatePart01( String pString, boolean pKnzDebug )
  {
    List< String > converted_string_list = Arrays.stream( pString.split( ";" ) ).map( String::trim ).collect( Collectors.toList() );

    calculate01( converted_string_list, pKnzDebug );
  }

  private static void calculate01( List< String > pListInput, boolean pKnzDebug )
  {
    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );

    int[][] matrix = new int[ 1000 ][ 1000 ];

    long result_part_01 = 0;

    for ( String input_str : pListInput )
    {
      int index_at = input_str.indexOf( '@' );

      int index_comma = input_str.indexOf( ',', index_at );

      int index_colon = input_str.indexOf( ':', index_comma );

      int index_x = input_str.indexOf( 'x', index_colon );

      String claim_id = input_str.substring( 1, index_at ).trim();

      int claim_start_row = Integer.parseInt( input_str.substring( index_at + 1, index_comma ).trim() );

      int claim_start_col = Integer.parseInt( input_str.substring( index_comma + 1, index_colon ).trim() );

      int claim_width = Integer.parseInt( input_str.substring( index_colon + 1, index_x ).trim() );

      int claim_height = Integer.parseInt( input_str.substring( index_x + 1 ).trim() );

      int claim_end_row = claim_start_row + claim_width;
      int claim_end_col = claim_start_col + claim_height;

      if ( pKnzDebug )
      {
        wl( String.format( "claim id %-10s   row %3d  col %3d   width %4d    height %4d ", claim_id, claim_start_row, claim_start_col, claim_width, claim_height ) );
      }

      for ( int cur_row = claim_start_row; cur_row < claim_end_row; cur_row++ )
      {
        for ( int cur_col = claim_start_col; cur_col < claim_end_col; cur_col++ )
        {
          matrix[ cur_row ][ cur_col ]++;
        }
      }
    }

    for ( int cur_row = 0; cur_row < 1000; cur_row++ )
    {
      for ( int cur_col = 0; cur_col < 1000; cur_col++ )
      {
        if ( matrix[ cur_row ][ cur_col ] > 1 )
        {
          result_part_01++;
        }
      }
    }

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "" );
    wl( "" );
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2018__day03_input.txt";

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
