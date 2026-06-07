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
 * 
 * replace nr    1  -  A a    3 and  2   [x, b, a, A, B, x, C, ., ., ., c, x, V, x] 
 * replace nr    2  -  B b    4 and  1   [x, b, ., ., B, x, C, ., ., ., c, x, V, x] 
 * replace nr    3  -  C c    6 and 10   [x, ., ., ., ., x, C, ., ., ., c, x, V, x] 
 * 
 * 
 * Result Part 1 5
 * Result Part 2 1 X
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * Input length = 16
 * 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * 
 * replace nr    1  -  C c    5 and  4   [d, ., b, ., c, C, ., C, B, ., c, C, c, ., D, .] 
 * replace nr    2  -  C c   11 and 10   [d, ., b, ., ., ., ., C, B, ., c, C, c, ., D, .] 
 * Nr  0  A         6 
 * 
 * replace nr    1  -  A a    3 and  1   [d, a, ., A, c, C, a, C, ., A, c, C, c, a, D, A] 
 * replace nr    2  -  C c    5 and  4   [d, ., ., ., c, C, a, C, ., A, c, C, c, a, D, A] 
 * replace nr    3  -  C c   11 and 10   [d, ., ., ., ., ., a, C, ., A, c, C, c, a, D, A] 
 * Nr  1  B         8 
 * 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, ., B, A, ., ., ., a, D, A] 
 * replace nr    2  -  B b    8 and  2   [d, a, b, ., ., ., ., ., B, A, ., ., ., a, D, A] 
 * replace nr    3  -  A a    9 and  1   [d, a, ., ., ., ., ., ., ., A, ., ., ., a, D, A] 
 * Nr  2  C         4 
 * 
 * replace nr    1  -  C c    5 and  4   [., a, b, A, c, C, a, C, B, A, c, C, c, a, ., A] 
 * replace nr    2  -  C c   11 and 10   [., a, b, A, ., ., a, C, B, A, c, C, c, a, ., A] 
 * replace nr    3  -  A a   15 and 13   [., a, b, A, ., ., a, C, B, A, ., ., c, a, ., A] 
 * replace nr    1  -  A a    3 and  6   [., a, b, A, ., ., a, C, B, A, ., ., c, ., ., .] 
 * Nr  3  D         6 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr  4  E        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr  5  F        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr  6  G        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr  7  H        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr  8  I        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr  9  J        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr 10  K        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr 11  L        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr 12  M        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr 13  N        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr 14  O        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr 15  P        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr 16  Q        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr 17  R        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr 18  S        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr 19  T        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr 20  U        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr 21  V        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr 22  W        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr 23  X        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr 24  Y        10 
 * 
 * replace nr    1  -  C c    5 and  4   [d, a, b, A, c, C, a, C, B, A, c, C, c, a, D, A] 
 * replace nr    2  -  C c   11 and 10   [d, a, b, A, ., ., a, C, B, A, c, C, c, a, D, A] 
 * replace nr    1  -  A a    3 and  6   [d, a, b, A, ., ., a, C, B, A, ., ., c, a, D, A] 
 * Nr 25  Z        10 
 * 
 * Result Part 1 10
 * Result Part 2 4 C
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * Input length = 50001
 * 
 * Nr  0  A      8936 
 * Nr  1  B      8986 
 * Nr  2  C      8902 
 * Nr  3  D      8930 
 * Nr  4  E      8964 
 * Nr  5  F      8928 
 * Nr  6  G      8870 
 * Nr  7  H      8942 
 * Nr  8  I      8902 
 * Nr  9  J      8914 
 * Nr 10  K      8948 
 * Nr 11  L      8918 
 * Nr 12  M      8944 
 * Nr 13  N      8944 
 * Nr 14  O      5534 
 * Nr 15  P      8890 
 * Nr 16  Q      8926 
 * Nr 17  R      8908 
 * Nr 18  S      8926 
 * Nr 19  T      8894 
 * Nr 20  U      8926 
 * Nr 21  V      8930 
 * Nr 22  W      8938 
 * Nr 23  X      8952 
 * Nr 24  Y      8904 
 * Nr 25  Z      8954 
 * 
 * Result Part 1 9296
 * Result Part 2 5534 O
 * 
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

    long result_part_01 = reduceString( pInput, '-', pKnzDebug );

    long result_part_02 = pInput.length();

    String alpha_beth = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    char result_char = '-';

    for ( int index_a = 0; index_a < alpha_beth.length(); index_a++ )
    {
      long result_cur = reduceString( pInput, alpha_beth.charAt( index_a ), pKnzDebug );

      wl( String.format( "Nr %2d  %s  %8d ", index_a, alpha_beth.charAt( index_a ), result_cur ) );

      if ( result_cur < result_part_02 )
      {
        result_part_02 = result_cur;

        result_char = alpha_beth.charAt( index_a );
      }

    }

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 + " " + result_char );
    wl( "" );
  }

  private static long reduceString( String pInput, char pCharA, boolean pKnzDebug )
  {
    if ( pKnzDebug )
    {
      wl( "" );
    }

    long result_length = 0;

    char[] cur_string = pInput.toCharArray();

    if ( ( pCharA >= 'A' ) && ( pCharA <= 'Z' ) )
    {
      char search_char = (char) ( ( (int) ( pCharA ) ) + 32 );

      for ( int index_a = 0; index_a < cur_string.length; index_a++ )
      {
        if ( ( cur_string[ index_a ] == pCharA ) || ( cur_string[ index_a ] == search_char ) )
        {
          cur_string[ index_a ] = '.';
        }
      }
    }

    int count_replace_round = 1;

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
        result_length++;
      }
      else if ( ( cur_string[ idx ] >= 'a' ) && ( cur_string[ idx ] <= 'z' ) )
      {
        result_length++;
      }
    }

    return result_length;
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
