package de.ea234.aoc2018.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 10: The Stars Align ---
 * https://adventofcode.com/2018/day/10
 * 
 * https://www.reddit.com/r/adventofcode/comments/a4skra/2018_day_10_solutions/
 * 
 * https://github.com/ea234/Advent_of_Code_2018/blob/main/src/de/ea234/aoc2018/day10/Day10_TheStarsAlign.java
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * 
 * Starting loop 2 - debug from second 0 to 18     min_rect_second 3
 * 
 * LOOP 2: Second   1   -  Height         11 Width         17 Volume        187 
 * LOOP 2: Second   2   -  Height          9 Width         13 Volume        117 
 * LOOP 2: Second   3   -  Height          7 Width          9 Volume         63 
 * 
 * #...#..###.
 * #...#...#..
 * #...#...#..
 * #####...#..
 * #...#...#..
 * #...#...#..
 * #...#...#..
 * #...#..###.
 * ...........
 * 
 * LOOP 2: Second   4   -  Height         10 Width         12 Volume        120 
 * LOOP 2: Second   5   -  Height         14 Width         16 Volume        224 
 * LOOP 2: Second   6   -  Height         18 Width         20 Volume        360 
 * LOOP 2: Second   7   -  Height         22 Width         24 Volume        528 
 * LOOP 2: Second   8   -  Height         26 Width         28 Volume        728 
 * LOOP 2: Second   9   -  Height         30 Width         32 Volume        960 
 * LOOP 2: Second  10   -  Height         34 Width         36 Volume       1224 
 * LOOP 2: Second  11   -  Height         38 Width         40 Volume       1520 
 * LOOP 2: Second  12   -  Height         42 Width         44 Volume       1848 
 * LOOP 2: Second  13   -  Height         46 Width         48 Volume       2208 
 * LOOP 2: Second  14   -  Height         50 Width         52 Volume       2600 
 * LOOP 2: Second  15   -  Height         54 Width         56 Volume       3024 
 * LOOP 2: Second  16   -  Height         58 Width         60 Volume       3480 
 * LOOP 2: Second  17   -  Height         62 Width         64 Volume       3968 
 * 
 * grid_x_min =>0<
 * grid_x_max =>9<
 * grid_y_min =>0<
 * grid_y_max =>7<
 * 
 * y =>7<
 * x =>9<
 * 
 * Result Part 1 0
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * 
 * Starting loop 2 - debug from second 10289 to 10319     min_rect_second 10304
 * 
 * LOOP 2: Second 10289   -  Height        159 Width        208 Volume      33072 
 * LOOP 2: Second 10290   -  Height        149 Width        198 Volume      29502 
 * LOOP 2: Second 10291   -  Height        139 Width        188 Volume      26132 
 * LOOP 2: Second 10292   -  Height        129 Width        178 Volume      22962 
 * LOOP 2: Second 10293   -  Height        119 Width        168 Volume      19992 
 * LOOP 2: Second 10294   -  Height        109 Width        158 Volume      17222 
 * LOOP 2: Second 10295   -  Height         99 Width        148 Volume      14652 
 * LOOP 2: Second 10296   -  Height         89 Width        138 Volume      12282 
 * LOOP 2: Second 10297   -  Height         79 Width        128 Volume      10112 
 * LOOP 2: Second 10298   -  Height         69 Width        118 Volume       8142 
 * LOOP 2: Second 10299   -  Height         59 Width        108 Volume       6372 
 * LOOP 2: Second 10300   -  Height         49 Width         98 Volume       4802 
 * LOOP 2: Second 10301   -  Height         39 Width         88 Volume       3432 
 * LOOP 2: Second 10302   -  Height         29 Width         79 Volume       2291 
 * LOOP 2: Second 10303   -  Height         19 Width         70 Volume       1330 
 * LOOP 2: Second 10304   -  Height          9 Width         61 Volume        549 
 * 
 * #####.....##....#....#..#.......#####.....##....#####...#####..
 * #....#...#..#...##...#..#.......#....#...#..#...#....#..#....#.
 * #....#..#....#..##...#..#.......#....#..#....#..#....#..#....#.
 * #....#..#....#..#.#..#..#.......#....#..#....#..#....#..#....#.
 * #####...#....#..#.#..#..#.......#####...#....#..#####...#####..
 * #.......######..#..#.#..#.......#.......######..#.......#..#...
 * #.......#....#..#..#.#..#.......#.......#....#..#.......#...#..
 * #.......#....#..#...##..#.......#.......#....#..#.......#...#..
 * #.......#....#..#...##..#.......#.......#....#..#.......#....#.
 * #.......#....#..#....#..######..#.......#....#..#.......#....#.
 * ...............................................................
 * 
 * LOOP 2: Second 10305   -  Height         19 Width         71 Volume       1349 
 * LOOP 2: Second 10306   -  Height         29 Width         81 Volume       2349 
 * LOOP 2: Second 10307   -  Height         39 Width         91 Volume       3549 
 * LOOP 2: Second 10308   -  Height         49 Width        101 Volume       4949 
 * LOOP 2: Second 10309   -  Height         59 Width        111 Volume       6549 
 * LOOP 2: Second 10310   -  Height         69 Width        121 Volume       8349 
 * LOOP 2: Second 10311   -  Height         79 Width        131 Volume      10349 
 * LOOP 2: Second 10312   -  Height         89 Width        141 Volume      12549 
 * LOOP 2: Second 10313   -  Height         99 Width        151 Volume      14949 
 * LOOP 2: Second 10314   -  Height        109 Width        161 Volume      17549 
 * LOOP 2: Second 10315   -  Height        119 Width        171 Volume      20349 
 * LOOP 2: Second 10316   -  Height        129 Width        181 Volume      23349 
 * LOOP 2: Second 10317   -  Height        139 Width        191 Volume      26549 
 * LOOP 2: Second 10318   -  Height        149 Width        201 Volume      29949 
 * 
 * grid_x_min =>197<
 * grid_x_max =>258<
 * grid_y_min =>187<
 * grid_y_max =>196<
 * 
 * y =>9<
 * x =>61<
 * 
 * Result Part 1 PANLPAPR
 * Result Part 2 10304
 * 
 * </pre> 
 */
public class Day10_TheStarsAlign
{
  /*
   * Credit:
   * https://www.reddit.com/user/Leirbagosaurus/
   * https://imgur.com/4p2J3rK
   */
  private static final String STR_CHRISTMAS_TREE  = new String(Character.toChars(0x1F384)) + "\uFE0F";
  
  private static final String STR_STAR = "\u2B50\uFE0F";
  
  public static void main( String[] args )
  {
    String test_input = "";

    test_input += ";position=< 9,  1> velocity=< 0,  2>";
    test_input += ";position=< 7,  0> velocity=<-1,  0>";
    test_input += ";position=< 3, -2> velocity=<-1,  1>";
    test_input += ";position=< 6, 10> velocity=<-2, -1>";
    test_input += ";position=< 2, -4> velocity=< 2,  2>";
    test_input += ";position=<-6, 10> velocity=< 2, -2>";
    test_input += ";position=< 1,  8> velocity=< 1, -1>";
    test_input += ";position=< 1,  7> velocity=< 1,  0>";
    test_input += ";position=<-3, 11> velocity=< 1, -2>";
    test_input += ";position=< 7,  6> velocity=<-1, -1>";
    test_input += ";position=<-2,  3> velocity=< 1,  0>";
    test_input += ";position=<-4,  3> velocity=< 2,  0>";
    test_input += ";position=<10, -3> velocity=<-1,  1>";
    test_input += ";position=< 5, 11> velocity=< 1, -2>";
    test_input += ";position=< 4,  7> velocity=< 0, -1>";
    test_input += ";position=< 8, -2> velocity=< 0,  1>";
    test_input += ";position=<15,  0> velocity=<-2,  0>";
    test_input += ";position=< 1,  6> velocity=< 1,  0>";
    test_input += ";position=< 8,  9> velocity=< 0, -1>";
    test_input += ";position=< 3,  3> velocity=<-1,  1>";
    test_input += ";position=< 0,  5> velocity=< 0, -1>";
    test_input += ";position=<-2,  2> velocity=< 2,  0>";
    test_input += ";position=< 5, -2> velocity=< 1,  2>";
    test_input += ";position=< 1,  4> velocity=< 2,  1>";
    test_input += ";position=<-2,  7> velocity=< 2, -2>";
    test_input += ";position=< 3,  6> velocity=<-1, -1>";
    test_input += ";position=< 5,  0> velocity=< 1,  0>";
    test_input += ";position=<-6,  0> velocity=< 2,  0>";
    test_input += ";position=< 5,  9> velocity=< 1, -2>";
    test_input += ";position=<14,  7> velocity=<-2,  0>";
    test_input += ";position=<-3,  6> velocity=< 2, -1>";

    calculatePart01( test_input, 20, true );

    calculate01( getListProd(), 200_000, false );
  }

  private static void calculatePart01( String pString, int pMaxIteration, boolean pKnzDebug )
  {
    List< String > converted_string_list = Arrays.stream( pString.split( ";" ) ).collect( Collectors.toList() );

    calculate01( converted_string_list, pMaxIteration, pKnzDebug );
  }

  private static void calculate01( List< String > pListInput, int pMaxIteration, boolean pKnzDebug )
  {
    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );
    wl( "" );

    /*
     * *******************************************************************************************************
     * Creating the list of stars from the input
     * *******************************************************************************************************
     */

    List< Star > star_list = new ArrayList< Day10_TheStarsAlign.Star >();

    for ( int cur_idx = 0; cur_idx < pListInput.size(); cur_idx++ )
    {
      String input_str = pListInput.get( cur_idx );

      if ( !input_str.isBlank() )
      {
        star_list.add( new Star( input_str ) );
      }
    }

    /*
     * *******************************************************************************************************
     * Searching for a minimum rectangle volume
     * *******************************************************************************************************
     */
    long min_rect_volume = Long.MAX_VALUE;

    int min_rect_second  = 0;

    for ( int cur_second = 1; cur_second < pMaxIteration; cur_second++ )
    {
      long cur_grid_x_min = Long.MAX_VALUE;
      long cur_grid_x_max = Long.MIN_VALUE;

      long cur_grid_y_min = Long.MAX_VALUE;
      long cur_grid_y_max = Long.MIN_VALUE;

      for ( Star cur_star : star_list )
      {
        cur_star.doMove();

        if ( cur_star.getPosX() < cur_grid_x_min ) cur_grid_x_min = cur_star.getPosX();

        if ( cur_star.getPosX() > cur_grid_x_max ) cur_grid_x_max = cur_star.getPosX();

        if ( cur_star.getPosY() < cur_grid_y_min ) cur_grid_y_min = cur_star.getPosY();

        if ( cur_star.getPosY() > cur_grid_y_max ) cur_grid_y_max = cur_star.getPosY();
      }

      long cur_rect_volume = calcDistance( cur_grid_x_min, cur_grid_x_max ) * calcDistance( cur_grid_y_min, cur_grid_y_max );

      if ( cur_rect_volume < min_rect_volume )
      {
        min_rect_volume = cur_rect_volume;

        min_rect_second = cur_second;
      }
    }

    /*
     * *******************************************************************************************************
     * Reset the stars and doing a second loop with debug output 
     * *******************************************************************************************************
     */
    long second_from = Math.max( 0, min_rect_second - 15 );

    long second_to   = min_rect_second + 15;

    wl( "" );
    wl( "Starting loop 2 - debug from second " + second_from + " to " + second_to );
    wl( "" );

    for ( Star cur_star : star_list )
    {
      cur_star.reset();
    }

    for ( int cur_second = 1; cur_second < second_to; cur_second++ )
    {
      long cur_grid_x_min = Long.MAX_VALUE;
      long cur_grid_x_max = Long.MIN_VALUE;

      long cur_grid_y_min = Long.MAX_VALUE;
      long cur_grid_y_max = Long.MIN_VALUE;

      for ( Star cur_star : star_list )
      {
        cur_star.doMove();

        if ( cur_star.getPosX() < cur_grid_x_min ) cur_grid_x_min = cur_star.getPosX();

        if ( cur_star.getPosX() > cur_grid_x_max ) cur_grid_x_max = cur_star.getPosX();

        if ( cur_star.getPosY() < cur_grid_y_min ) cur_grid_y_min = cur_star.getPosY();

        if ( cur_star.getPosY() > cur_grid_y_max ) cur_grid_y_max = cur_star.getPosY();
      }

      long cur_grid_height = calcDistance( cur_grid_y_min, cur_grid_y_max );

      long cur_grid_width = calcDistance( cur_grid_x_min, cur_grid_x_max );

      long cur_rect_volume = cur_grid_width * cur_grid_height;

      if ( cur_second >= second_from )
      {
        wl( String.format( "LOOP 2: Second %3d   -  Height %10d Width %10d Volume %10d ", cur_second, cur_grid_height, cur_grid_width, cur_rect_volume ) );
      }

      if ( cur_second == min_rect_second )
      {
        Properties star_grid = new Properties();

        for ( Star cur_star : star_list )
        {
          star_grid.setProperty( "R" + cur_star.getPosY() + "C" + cur_star.getPosX(), "#" );
        }

        wl( "" );
        wl( getDebugMap( star_grid, cur_grid_y_min, cur_grid_x_min, cur_grid_y_max + 2, cur_grid_x_max + 2 , false ) );
        wl( "" );
        wl( getDebugMap( star_grid, cur_grid_y_min- 1, cur_grid_x_min- 1, cur_grid_y_max + 2, cur_grid_x_max + 2 , true ) );      }
      }
    }

    wl( "" );
    wl( "Result Part 2 " + min_rect_second );
  }

  private static String getDebugMap( Properties pGrid, long pGridX1, long pGridY1, long pGridX2, long pGridY2, boolean pKnzFancyPancy )
  {
    StringBuilder debug_map = new StringBuilder();

    for ( long cur_x = pGridX1; cur_x < pGridX2; cur_x++ )
    {
      for ( long cur_y = pGridY1; cur_y < pGridY2; cur_y++ )
      {
        if ( pKnzFancyPancy ) 
        {
          debug_map.append( pGrid.getProperty( "R" + cur_x + "C" + cur_y, "." ).charAt( 0 ) == '.' ? STR_CHRISTMAS_TREE : STR_STAR );
          
        }
        else
        {
        debug_map.append( pGrid.getProperty( "R" + cur_x + "C" + cur_y, "." ) );
        }
      }

      debug_map.append( "\n" );
    }

    return debug_map.toString();
  }

  private static class Star
  {
    private long pos_start_x = 0;

    private long pos_start_y = 0;

    private long pos_cur_x   = 0;

    private long pos_cur_y   = 0;

    private long velocity_x  = 0;

    private long velocity_y  = 0;

    public Star( String pInput )
    {
      String str_only_numbers = pInput.replaceAll( "position=<", "" ).replaceAll( "> velocity=<", "," ).replaceAll( ">", "" ).replaceAll( " ", "" );

      String[] value_vector = str_only_numbers.split( "," );

      pos_cur_x = Long.parseLong( value_vector[ 0 ] );

      pos_cur_y = Long.parseLong( value_vector[ 1 ] );

      velocity_x = Long.parseLong( value_vector[ 2 ] );

      velocity_y = Long.parseLong( value_vector[ 3 ] );

      pos_start_x = pos_cur_x;

      pos_start_y = pos_cur_y;
    }

    public void reset()
    {
      pos_cur_x = pos_start_x;

      pos_cur_y = pos_start_y;
    }

    public void doMove()
    {
      pos_cur_x += velocity_x;

      pos_cur_y += velocity_y;
    }

    public long getPosX()
    {
      return pos_cur_x;
    }

    public long getPosY()
    {
      return pos_cur_y;
    }
  }

  private static long calcDistance( long pPosA, long pPosB )
  {
    /*
     * see also:
     * 
     * --- Day 23: Experimental Emergency Teleportation ---
     * https://adventofcode.com/2018/day/23
     * 
     * https://www.reddit.com/r/adventofcode/comments/a8s17l/2018_day_23_solutions/
     * 
     * https://github.com/ea234/Advent_of_Code_2018/blob/main/src/de/ea234/aoc2018/day23/Day23_ExperimentalEmergencyTeleportation.java
     */

    if ( ( pPosA >= 0 ) && ( pPosB >= 0 ) )
    {
      if ( pPosB >= pPosA )
      {
        return pPosB - pPosA;
      }

      return pPosA - pPosB;
    }

    if ( ( pPosA <= 0 ) && ( pPosB <= 0 ) )
    {
      if ( pPosB >= pPosA )
      {
        return Math.abs( pPosB - pPosA );
      }

      return Math.abs( pPosA - pPosB );
    }

    return Math.abs( pPosA ) + Math.abs( pPosB );
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2018__day10_input.txt";

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
