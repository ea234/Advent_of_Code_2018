package de.ea234.aoc2018.day05;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

/**
 * <pre>
 * 
 * --- Day 5: Alchemical Reduction ---
 * https://adventofcode.com/2018/day/5
 * 
 * https://www.reddit.com/r/adventofcode/comments/a3912m/2018_day_5_solutions/
 * 
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * Input length = 14
 * 
 * replace nr    1  -  A a    3 and  2   [x, b, a, A, B, x, C, ., ., ., c, x, V, x] 
 * replace nr    2  -  B b    4 and  1   [x, b, ., ., B, x, C, ., ., ., c, x, V, x] 
 * replace nr    3  -  C c    6 and 10   [x, ., ., ., ., x, C, ., ., ., c, x, V, x] 
 * 
 * count_replace_total        3
 * count_replace_total * 2 =  6
 * 
 * Result Part 1 5
 * Result Part 2 0
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * Input length = 16
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    3  -  C c   11 and 10   [d, a, b, ., ., ., ., C, B, A, c, C, c, a, D, A] 
 * 
 * count_replace_total        3
 * count_replace_total * 2 =  6
 * 
 * Result Part 1 10
 * Result Part 2 0
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * Input length = 50001
 * 
 * 
 * count_replace_total       20352
 * count_replace_total * 2 = 40704
 * 
 * Result Part 1 9296
 * Result Part 2 0
 * 
 * </pre> 
 */
public class Day05_AlchemicalReduction
{
  public static void main( String[] args )
  {
    String test_input = "dabAcCaCBAcCcaDA";

    calculate01( "xbaABxC...cxVx", true );

    calculate01( test_input, true );

    calculate01( getListProd(), false );

    System.exit( 0 );
  }

  private static void calculate01( String pInput, boolean pKnzDebug )
  {
    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );
    wl( "" );
    wl( "Input length = " + pInput.length() );
    wl( "" );

    long result_part_01 = 0;

    long result_part_02 = 0;

    char[] cur_string = pInput.toCharArray();

    int count_replace_round = 1;

    int count_replace_total = 0;

    while ( count_replace_round > 0 )
    {
      count_replace_round = 0;

      for ( int index_a = 0; index_a < cur_string.length; index_a++ )
      {
        if ( ( cur_string[ index_a ] >= 'A' ) && ( cur_string[ index_a ] <= 'Z' ) )
        {
          /*
           * Determine the char to be searched for
           * 
           *  A = 65
           *  a = 97
           */
          char search_char = (char) ( ( (int) ( cur_string[ index_a ] ) ) + 32 );

          /*
           * Search the search_char to the front
           */
          int index_b = searchChar( search_char, cur_string, index_a, -1 );

          /*
           * Search the search_char to the end
           */
          if ( index_b == -1 )
          {
            index_b = searchChar( search_char, cur_string, index_a, 1 );
          }

          if ( index_b != -1 )
          {
            count_replace_round++;

            count_replace_total++;

            if ( pKnzDebug )
            {
              wl( String.format( "replace nr %4d  -  %s %s   %2d and %2d   %s ", count_replace_round, cur_string[ index_a ], search_char, index_a, index_b, Arrays.toString( cur_string ) ) );
            }

            cur_string[ index_a ] = '.';

            cur_string[ index_b ] = '.';
          }
        }
      }
    }

    for ( int idx = 0; idx < cur_string.length; idx++ )
    {
      if ( ( cur_string[ idx ] >= 'A' ) && ( cur_string[ idx ] <= 'Z' ) )
      {
        result_part_01++;
      }
      else if ( ( cur_string[ idx ] >= 'a' ) && ( cur_string[ idx ] <= 'z' ) )
      {
        result_part_01++;
      }
    }

    wl( "" );
    wl( "count_replace_total       " + count_replace_total );
    wl( "count_replace_total * 2 = " + ( count_replace_total * 2 ) );
    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
  }

  private static int searchChar( char search_char, char[] cur_string, int pStart, int pDelta )
  {
    int cur_search_index = pStart + pDelta;

    while ( ( ( cur_search_index >= 0 ) && ( cur_search_index < cur_string.length ) ) )
    {
      if ( cur_string[ cur_search_index ] == search_char )
      {
        return cur_search_index;
      }
      else if ( cur_string[ cur_search_index ] != '.' )
      {
        return -1;
      }
      else
      {
        cur_search_index += pDelta;
      }
    }

    return -1;
  }

  private static String getListProd()
  {
    String string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2018__day05_input.txt";

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
