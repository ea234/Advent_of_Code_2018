package de.ea234.aoc2015.day11;

/**
 * <pre>
 * 
 * --- Day 11: Chronal Charge ---
 * https://adventofcode.com/2018/day/11
 * 
 * https://www.reddit.com/r/adventofcode/comments/a53r6i/2018_day_11_solutions/
 * 
 * https://github.com/ea234/Advent_of_Code_2018/blob/main/src/de/ea234/aoc2018/day11/Day11_ChronalCharge.java
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * pGridSerialNumber 18
 * 
 * Grid Size   5   P2 MAX Power    0  0,0,0 
 * Grid Size   6   P2 MAX Power   47  243,45,5 
 * Grid Size   7   P2 MAX Power   48  234,249,6 
 * Grid Size   8   P2 MAX Power   61  233,251,7 
 * Grid Size   9   P2 MAX Power   68  232,249,8 
 * Grid Size  10   P2 MAX Power   77  231,249,9 
 * Grid Size  11   P2 MAX Power   82  230,247,10 
 * Grid Size  12   P2 MAX Power   86  235,246,11 
 * Grid Size  13   P2 MAX Power   90  234,245,12 
 * Grid Size  14   P2 MAX Power  100  233,244,13 
 * Grid Size  15   P2 MAX Power  100  233,244,13 
 * Grid Size  16   P2 MAX Power  106  90,269,15 
 * Grid Size  17   P2 MAX Power  113  90,269,16 
 * Grid Size  18   P2 MAX Power  113  90,269,16 
 * Grid Size  19   P2 MAX Power  113  90,269,16 
 * 
 *  -2 -4  4  4  4   |    -2 -4  4  4  4 -5 -3  0  4 -2  4  0 -3 -5  4  4 -5 -3
 *  -4  4  4  4 -5   |    -4  4  4  4 -5 -3  0  4 -1  4  1 -2 -4 -4 -4 -3 -1  2
 *   4  3  3  4 -4   |     4  3  3  4 -4 -1  3 -2  4  0 -2 -4 -4 -4 -3 -1  2 -4
 *   1  1  2  4 -3   |     1  1  2  4 -3  1 -4  2 -1 -4 -5  4 -5 -4 -1  2 -4  1
 *  -1  0  2 -5 -2   |    -1  0  2 -5 -2  3 -1 -4  4  2  2  2  4 -4  0  4  0 -4
 *                   |    -4 -2  1 -5  0 -5  2  0 -1 -2 -1  0  3 -3  1 -3  3  1
 *                   |     4 -3  0 -5  1 -3 -5  4  4  4 -4 -1  2 -3  3 -1 -3 -4
 *                   |     2 -5  0 -5  2  0 -2 -2 -1  0  3 -3  1 -3  4  2  1  0
 *                   |    -1  4 -1 -4  3  2  1  2  4 -4  0 -5  1 -3 -5  4  4 -5
 *                   |    -3  2 -1 -4  4  4  4 -4 -1  2 -3  3  0 -2 -3 -3 -2  0
 *                   |    -5  1 -2 -4 -5 -4 -3  0  4 -2  4  1 -1 -2 -2 -1  2 -5
 *                   |     2 -1 -3 -4 -3 -2  0  4 -1  4  1 -1 -2 -2 -1  2 -5  0
 *                   |     0 -2 -3 -3 -2  0  3 -2  4  0 -2 -3 -3 -2  1  4 -1  4
 *                   |    -2 -4 -4 -3 -1  2 -4  2 -1 -4 -5 -5 -4 -1  2 -3  2 -1
 *                   |    -5 -5 -5 -3  0  4 -1 -4  4  2  2  3 -4 -1  3 -1 -4  4
 *                   |     3  3 -5 -3  1 -4  3  0 -1 -2 -1  1 -5 -1 -5  2  0 -1
 *                   |     0  2  4 -2  2 -2 -4  4  4  4 -4 -1  4 -1 -4  4  3  4
 *                   |    -2  0  3 -2  4  0 -1 -2 -1  0  3 -2  3  0 -2 -3 -3 -1
 * 
 * Result Part 1 33,45
 * Result Part 2 90,269,16
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * pGridSerialNumber 42
 * 
 * Grid Size   5   P2 MAX Power    0  0,0,0 
 * Grid Size   6   P2 MAX Power   44  235,255,5 
 * Grid Size   7   P2 MAX Power   55  165,103,6 
 * Grid Size   8   P2 MAX Power   64  233,253,7 
 * Grid Size   9   P2 MAX Power   76  232,253,8 
 * Grid Size  10   P2 MAX Power   89  235,254,9 
 * Grid Size  11   P2 MAX Power   95  235,253,10 
 * Grid Size  12   P2 MAX Power  108  233,252,11 
 * Grid Size  13   P2 MAX Power  119  232,251,12 
 * Grid Size  14   P2 MAX Power  119  232,251,12 
 * Grid Size  15   P2 MAX Power  119  232,251,12 
 * Grid Size  16   P2 MAX Power  119  232,251,12 
 * Grid Size  17   P2 MAX Power  119  232,251,12 
 * Grid Size  18   P2 MAX Power  119  232,251,12 
 * Grid Size  19   P2 MAX Power  119  232,251,12 
 * 
 *  -3  4  2  2  2   |    -3  4  2  2  2  4 -3  1 -3  3  1  0  1  2
 *  -4  4  3  3  4   |    -4  4  3  3  4 -4  0 -5  1 -1 -3 -3 -2  0
 *  -5  3  3  4 -4   |    -5  3  3  4 -4 -1  3 -1 -4  4  3  4 -4 -1
 *   4  3  3  4 -3   |     4  3  3  4 -3  1 -4  3  0 -1 -1  1  3 -3
 *   3  3  3 -5 -1   |     3  3  3 -5 -1  3 -1 -4 -5  4 -5 -2  1 -4
 *                   |     2  2  4 -4  0 -5  2  0 -1  0  1  4 -1  4
 *                   |     1  2  4 -3  2 -2 -5  4  4 -5 -3  1 -4  3
 *                   |     0  1  4 -2  3  0 -2 -3 -2  0  3 -2  4  1
 *                   |    -1  1  4 -1 -5  2  1  1  2 -5 -1 -5  2  0
 *                   |    -2  1 -5  0 -4  4  4 -5 -3  0 -5  2 -1 -2
 *                   |    -3  0 -5  1 -2 -3 -3 -2  1 -4  1 -2 -3 -3
 *                   |    -4  0 -5  2  0 -1  0  2 -4  1 -3 -5 -5 -5
 *                   |    -5 -1 -5  2  1  1  3 -4  0 -4  3  2  2  4
 *                   |     4 -1 -5  3  3  3 -4 -1 -5  1 -1 -1  0  2
 * 
 * Result Part 1 21,61
 * Result Part 2 232,251,12
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * pGridSerialNumber 1955
 * 
 * Grid Size   5   P2 MAX Power    0  0,0,0 
 * Grid Size   6   P2 MAX Power   63  235,116,5 
 * Grid Size   7   P2 MAX Power   71  234,115,6 
 * Grid Size   8   P2 MAX Power  104  235,114,7 
 * Grid Size   9   P2 MAX Power  107  234,113,8 
 * Grid Size  10   P2 MAX Power  136  233,113,9 
 * Grid Size  11   P2 MAX Power  142  232,113,10 
 * Grid Size  12   P2 MAX Power  150  231,111,11 
 * Grid Size  13   P2 MAX Power  166  233,110,12 
 * Grid Size  14   P2 MAX Power  168  232,109,13 
 * Grid Size  15   P2 MAX Power  171  231,108,14 
 * Grid Size  16   P2 MAX Power  171  231,108,14 
 * Grid Size  17   P2 MAX Power  171  231,108,14 
 * Grid Size  18   P2 MAX Power  171  231,108,14 
 * Grid Size  19   P2 MAX Power  171  231,108,14 
 * 
 *  -1 -5  2  2  3   |    -1 -5  2  2  3 -4  1 -3 -4 -4 -1  3 -2 -4 -4 -3
 *  -2  4  2  2  4   |    -2  4  2  2  4 -2  4  1  0  1 -5 -1 -4 -5 -5 -2
 *  -3  4  3  3 -4   |    -3  4  3  3 -4  0 -3 -5 -5 -3  1 -4  4  3 -5 -2
 *  -4  4  3  4 -3   |    -4  4  3  4 -3  3  0 -2 -1  2 -3  3  1  2  4 -2
 *  -5  3  3 -5 -1   |    -5  3  3 -5 -1 -5  2  2  4 -3  3  0 -1  0  3 -2
 *                   |     4  3  3 -4  1 -3 -5 -4 -2  2 -1 -3 -3 -1  3 -1
 *                   |     3  2  4 -3  2 -1 -2 -1  3 -2 -5  3  4 -3  2 -1
 *                   |     2  2  4 -2  4  2  1  3 -3  3  1  0  2 -4  1 -1
 *                   |     1  2  4 -1 -5  4  4 -3  1 -2 -3 -3  0  4  1 -1
 *                   |     0  1  4  0 -3 -4 -3  1 -4  3  3  4 -3  3  0  0
 *                   |    -1  1 -5  0 -2 -2  0  4  0 -2 -1  1 -5  1 -1  0
 *                   |    -2  0 -5  1  0  1  3 -2 -5  4 -5 -3  3  0 -1  0
 *                   |    -3  0 -5  2  1  3 -4  2 -1 -1  1  4  0 -2 -2  0
 *                   |    -4  0 -5  3  3 -5 -1 -5  4  4 -3  1 -2 -3 -2  1
 *                   |    -5 -1 -4  4 -5 -3  2 -1 -2 -1  3 -2 -5 -5 -3  1
 *                   |     4 -1 -4 -5 -4  0 -5  3  2  4 -1 -5  3  4 -4  1
 * 
 * Result Part 1 21,93
 * Result Part 2 231,108,14
 * 
 * </pre> 
 */
public class Day11_ChronalCharge
{
  private static final String STR_COMBINE_SPACER = "   |   ";

  public static void main( String[] args )
  {
    calculate01( 18, true );

    calculate01( 42, true );

    calculate01( 1955, true );
  }

  private static void calculate01( int pGridSerialNumber, boolean pKnzDebug )
  {
    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );
    wl( "" );
    wl( "pGridSerialNumber " + pGridSerialNumber );
    wl( "" );

    /*
     * *******************************************************************************************************
     * Calculating the grid
     * *******************************************************************************************************
     */

    int grid_width  = 300;
    int grid_height = 300;

    /*
     * Give the grid some extra space
     */
    int[][] grid_map = new int[ grid_height + 3 ][ grid_width + 3 ];

    for ( int cur_y = 0; cur_y < grid_height; cur_y++ )
    {
      for ( int cur_x = 0; cur_x < grid_width; cur_x++ )
      {
        /*
         * Rack ID = X coordinate plus 10.
         */
        int rack_id = cur_x + 10;

        /*
         * Begin with a power level of the rack ID times the Y coordinate.
         */
        int power_level = rack_id * cur_y;

        /*
         * Increase the power level by the value of the grid serial number (your puzzle input).
         */
        power_level += pGridSerialNumber;

        /*
         * Set the power level to itself multiplied by the rack ID.
         */
        power_level *= rack_id;

        /*
         * Keep only the hundreds digit of the power level (so 12345 becomes 3; numbers with no hundreds digit become 0).
         */
        power_level = ( power_level / 100 ) % 10;

        /*
         * Subtract 5 from the power level.
         */
        power_level -= 5;

        grid_map[ cur_y ][ cur_x ] = power_level;
      }
    }

    /*
     * *******************************************************************************************************
     * Part 1: Search the most powerful 3x3 square
     * *******************************************************************************************************
     */

    int p1_max_power = 0;

    int p1_max_pos_x = 0;
    int p1_max_pos_y = 0;

    for ( int cur_y = 0; cur_y < grid_height; cur_y++ )
    {
      for ( int cur_x = 0; cur_x < grid_width; cur_x++ )
      {
        int cur_power = getPowerGridSquare( grid_map, cur_y, cur_x, 3 );

        if ( cur_power > p1_max_power )
        {
          p1_max_power = cur_power;

          p1_max_pos_x = cur_x;
          p1_max_pos_y = cur_y;
        }
      }
    }

    /*
     * *******************************************************************************************************
     * Part 2: Search the most powerful square with open boundaries
     * *******************************************************************************************************
     */
    int p2_max_power = 0;

    int p2_max_size = 0;

    int p2_max_pos_x = 0;
    int p2_max_pos_y = 0;

    for ( int grid_size = 5; grid_size < 20; grid_size++ )
    {
      wl( String.format( "Grid Size %3d   P2 MAX Power %4d  %d,%d,%d ", grid_size, p2_max_power, p2_max_pos_x, p2_max_pos_y, p2_max_size ) );

      for ( int cur_y = 0; cur_y < grid_height; cur_y++ )
      {
        for ( int cur_x = 0; cur_x < grid_width; cur_x++ )
        {
          int cur_power = getPowerGridSquare( grid_map, cur_y, cur_x, grid_size );

          if ( cur_power > p2_max_power )
          {
            p2_max_power = cur_power;

            p2_max_pos_x = cur_x;
            p2_max_pos_y = cur_y;
            p2_max_size = grid_size;
          }
        }
      }
    }

    String dbg_grid_1 = getDebugGridNumber( grid_map, p1_max_pos_y - 1, p1_max_pos_x - 1, p1_max_pos_y + 4, p1_max_pos_x + 4 );

    String dbg_grid_2 = getDebugGridNumber( grid_map, p1_max_pos_y - 1, p1_max_pos_x - 1, p1_max_pos_y + p2_max_size + 1, p1_max_pos_x + p2_max_size + 1 );

    wl( "" );
    wl( combineStrings( dbg_grid_1, dbg_grid_2 ) );
    wl( "" );
    wl( "Result Part 1 " + p1_max_pos_x + "," + p1_max_pos_y );
    wl( "Result Part 2 " + p2_max_pos_x + "," + p2_max_pos_y + "," + p2_max_size );
  }

  private static int getPowerGridSquare( int[][] grid_map, int pCurY, int pCurX, int pSize )
  {
    int sum_square = 0;

    for ( int cur_y = 0; cur_y < pSize; cur_y++ )
    {
      for ( int cur_x = 0; cur_x < pSize; cur_x++ )
      {
        try
        {
          sum_square += grid_map[ pCurY + cur_y ][ pCurX + cur_x ];
        }
        catch ( IndexOutOfBoundsException exp )
        {
        }
      }
    }

    return sum_square;
  }

  private static String getDebugGridNumber( int[][] pGrid, int pRowStart, int pColStart, int pRowEnd, int pColEnd )
  {
    StringBuilder dbg_string = new StringBuilder();

    for ( int cur_row = pRowStart; cur_row < pRowEnd; cur_row++ )
    {
      for ( int cur_col = pColStart; cur_col < pColEnd; cur_col++ )
      {
        dbg_string.append( String.format( " %2d", pGrid[ cur_row ][ cur_col ] ) );
      }

      dbg_string.append( "\n" );
    }

    return dbg_string.toString();
  }

  private static String combineStrings( String pString1, String pString2 )
  {
    String[] lines1 = pString1 != null ? pString1.split( "\r?\n" ) : new String[ 0 ];

    String[] lines2 = pString2 != null ? pString2.split( "\r?\n" ) : new String[ 0 ];

    int max_lines = Math.max( lines1.length, lines2.length );

    StringBuilder string_builder = new StringBuilder();

    for ( int line_index = 0; line_index < max_lines; line_index++ )
    {
      String str_a = line_index < lines1.length ? lines1[ line_index ] : "               ";

      String str_b = line_index < lines2.length ? lines2[ line_index ] : "";

      string_builder.append( str_a ).append( STR_COMBINE_SPACER ).append( str_b );

      if ( line_index < max_lines - 1 )
      {
        string_builder.append( "\n" );
      }
    }

    return string_builder.toString();
  }

  private static void wl( String pString ) // wl = short for "write log"
  {
    System.out.println( pString );
  }
}
