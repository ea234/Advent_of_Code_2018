package de.ea234.aoc2015.day11;

/**
 * <pre>
 * 
 * --- Day 11: Chronal Charge ---
 * https://adventofcode.com/2018/day/11
 * 
 * https://www.reddit.com/r/adventofcode/comments/a53r6i/2018_day_11_solutions/
 * 
 *
 * ------------------------------------------------------------------------------------------
 * 
 * pGridSerialNumber 18
 * 
 *  -2 -4  4  4  4
 *  -4  4  4  4 -5
 *   4  3  3  4 -4
 *   1  1  2  4 -3
 *  -1  0  2 -5 -2
 * 
 * 
 * Result Part 1 33,45
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * pGridSerialNumber 42
 * 
 *  -3  4  2  2  2
 *  -4  4  3  3  4
 *  -5  3  3  4 -4
 *   4  3  3  4 -3
 *   3  3  3 -5 -1
 * 
 * 
 * Result Part 1 21,61
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * pGridSerialNumber 1955
 * 
 *  -1 -5  2  2  3
 *  -2  4  2  2  4
 *  -3  4  3  3 -4
 *  -4  4  3  4 -3
 *  -5  3  3 -5 -1
 * 
 * 
 * Result Part 1 21,93
 * 
 * </pre> 
 */
public class Day11_ChronalCharge
{
  public static void main( String[] args )
  {
    calculate01( 18, true );

    calculate01( 42, true );

    calculate01( 1955, true );
  }

  private static void calculate01( int pGridSerialNumber, boolean pKnzDebug )
  {
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
     * Search the most powerful 3x3 square
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

    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );
    wl( "" );
    wl( "pGridSerialNumber " + pGridSerialNumber );
    wl( "" );
    wl( getDebugGridNumber( grid_map, p1_max_pos_y - 1, p1_max_pos_x - 1, p1_max_pos_y + 4, p1_max_pos_x + 4 ) );
    wl( "" );
    wl( "Result Part 1 " + p1_max_pos_x + "," + p1_max_pos_y );
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

  private static void wl( String pString ) // wl = short for "write log"
  {
    System.out.println( pString );
  }
}
