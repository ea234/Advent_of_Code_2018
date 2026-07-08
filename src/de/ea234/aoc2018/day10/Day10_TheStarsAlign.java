package de.ea234.aoc2018.day10;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * 
 * Starting loop 2 - debug from second 0 to 12
 * 
 * LOOP 2: Second   0  -  Distance     216,19  -  Min Distance     193,10 at second   2  -  Hight         11 Width         17 Volume        143 
 * LOOP 2: Second   1  -  Distance     199,05  -  Min Distance     193,10 at second   2  -  Hight          9 Width         13 Volume         99 
 * LOOP 2: Second   2  -  Distance     193,10  -  Min Distance     193,10 at second   2  -  Hight          7 Width          9 Volume         63 
 * LOOP 2: Second   3  -  Distance     201,56  -  Min Distance     193,10 at second   2  -  Hight         10 Width         12 Volume        110 
 * LOOP 2: Second   4  -  Distance     219,34  -  Min Distance     193,10 at second   2  -  Hight         14 Width         16 Volume        182 
 * LOOP 2: Second   5  -  Distance     247,05  -  Min Distance     193,10 at second   2  -  Hight         18 Width         20 Volume        270 
 * LOOP 2: Second   6  -  Distance     280,83  -  Min Distance     193,10 at second   2  -  Hight         22 Width         24 Volume        374 
 * LOOP 2: Second   7  -  Distance     323,01  -  Min Distance     193,10 at second   2  -  Hight         26 Width         28 Volume        494 
 * LOOP 2: Second   8  -  Distance     369,59  -  Min Distance     193,10 at second   2  -  Hight         30 Width         32 Volume        630 
 * LOOP 2: Second   9  -  Distance     417,82  -  Min Distance     193,10 at second   2  -  Hight         34 Width         36 Volume        782 
 * LOOP 2: Second  10  -  Distance     467,23  -  Min Distance     193,10 at second   2  -  Hight         38 Width         40 Volume        950 
 * LOOP 2: Second  11  -  Distance     517,49  -  Min Distance     193,10 at second   2  -  Hight         42 Width         44 Volume       1134 
 * 
 * Min Distance 193,101171 at second   2     min_rect_value         63 at second   2  
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
 * Starting loop 2 - debug from second 10290 to 10320
 * 
 * LOOP 2: Second 10290  -  Distance  111570,93  -  Min Distance  108965,16 at second 10310  -  Hight        139 Width        188 Volume      21962 
 * LOOP 2: Second 10291  -  Distance  111320,53  -  Min Distance  108965,16 at second 10310  -  Hight        129 Width        178 Volume      19737 
 * LOOP 2: Second 10292  -  Distance  111082,53  -  Min Distance  108965,16 at second 10310  -  Hight        119 Width        168 Volume      17612 
 * LOOP 2: Second 10293  -  Distance  110856,98  -  Min Distance  108965,16 at second 10310  -  Hight        109 Width        158 Volume      15587 
 * LOOP 2: Second 10294  -  Distance  110643,93  -  Min Distance  108965,16 at second 10310  -  Hight         99 Width        148 Volume      13662 
 * LOOP 2: Second 10295  -  Distance  110443,43  -  Min Distance  108965,16 at second 10310  -  Hight         89 Width        138 Volume      11837 
 * LOOP 2: Second 10296  -  Distance  110255,53  -  Min Distance  108965,16 at second 10310  -  Hight         79 Width        128 Volume      10112 
 * LOOP 2: Second 10297  -  Distance  110080,25  -  Min Distance  108965,16 at second 10310  -  Hight         69 Width        118 Volume       8487 
 * LOOP 2: Second 10298  -  Distance  109917,64  -  Min Distance  108965,16 at second 10310  -  Hight         59 Width        108 Volume       6962 
 * LOOP 2: Second 10299  -  Distance  109767,75  -  Min Distance  108965,16 at second 10310  -  Hight         49 Width         98 Volume       5537 
 * LOOP 2: Second 10300  -  Distance  109630,60  -  Min Distance  108965,16 at second 10310  -  Hight         39 Width         88 Volume       4212 
 * LOOP 2: Second 10301  -  Distance  109506,22  -  Min Distance  108965,16 at second 10310  -  Hight         29 Width         79 Volume       3016 
 * LOOP 2: Second 10302  -  Distance  109394,65  -  Min Distance  108965,16 at second 10310  -  Hight         19 Width         70 Volume       1900 
 * LOOP 2: Second 10303  -  Distance  109295,90  -  Min Distance  108965,16 at second 10310  -  Hight          9 Width         61 Volume        864 
 * LOOP 2: Second 10304  -  Distance  109210,01  -  Min Distance  108965,16 at second 10310  -  Hight         19 Width         71 Volume       1919 
 * LOOP 2: Second 10305  -  Distance  109136,97  -  Min Distance  108965,16 at second 10310  -  Hight         29 Width         81 Volume       3074 
 * LOOP 2: Second 10306  -  Distance  109076,82  -  Min Distance  108965,16 at second 10310  -  Hight         39 Width         91 Volume       4329 
 * LOOP 2: Second 10307  -  Distance  109029,56  -  Min Distance  108965,16 at second 10310  -  Hight         49 Width        101 Volume       5684 
 * LOOP 2: Second 10308  -  Distance  108995,19  -  Min Distance  108965,16 at second 10310  -  Hight         59 Width        111 Volume       7139 
 * LOOP 2: Second 10309  -  Distance  108973,72  -  Min Distance  108965,16 at second 10310  -  Hight         69 Width        121 Volume       8694 
 * LOOP 2: Second 10310  -  Distance  108965,16  -  Min Distance  108965,16 at second 10310  -  Hight         79 Width        131 Volume      10349 
 * LOOP 2: Second 10311  -  Distance  108969,49  -  Min Distance  108965,16 at second 10310  -  Hight         89 Width        141 Volume      12104 
 * LOOP 2: Second 10312  -  Distance  108986,71  -  Min Distance  108965,16 at second 10310  -  Hight         99 Width        151 Volume      13959 
 * LOOP 2: Second 10313  -  Distance  109016,83  -  Min Distance  108965,16 at second 10310  -  Hight        109 Width        161 Volume      15914 
 * LOOP 2: Second 10314  -  Distance  109059,82  -  Min Distance  108965,16 at second 10310  -  Hight        119 Width        171 Volume      17969 
 * LOOP 2: Second 10315  -  Distance  109115,68  -  Min Distance  108965,16 at second 10310  -  Hight        129 Width        181 Volume      20124 
 * LOOP 2: Second 10316  -  Distance  109184,39  -  Min Distance  108965,16 at second 10310  -  Hight        139 Width        191 Volume      22379 
 * LOOP 2: Second 10317  -  Distance  109265,94  -  Min Distance  108965,16 at second 10310  -  Hight        149 Width        201 Volume      24734 
 * LOOP 2: Second 10318  -  Distance  109360,32  -  Min Distance  108965,16 at second 10310  -  Hight        159 Width        211 Volume      27189 
 * LOOP 2: Second 10319  -  Distance  109467,52  -  Min Distance  108965,16 at second 10310  -  Hight        169 Width        221 Volume      29744 
 * 
 * Min Distance 108965,155208 at second 10310     min_rect_value        549 at second 10303  
 * 
 * grid_x_min =>162<
 * grid_x_max =>293<
 * grid_y_min =>152<
 * grid_y_max =>231<
 * 
 * y =>79<
 * x =>131<
 * 
 * Result Part 1 0
 * 
 * </pre> 
 */
public class Day10_TheStarsAlign
{
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

    int result_part_01 = 0;

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
     * Searching for a minimum qubic distance
     * *******************************************************************************************************
     */
    long min_rect_volume = Long.MAX_VALUE;

    int min_rect_second  = 0;

    double min_distance_value = Double.MAX_VALUE;

    int min_distance_second   = 0;

    long grid_x_min = Long.MAX_VALUE;
    long grid_x_max = Long.MIN_VALUE;

    long grid_y_min = Long.MAX_VALUE;
    long grid_y_max = Long.MIN_VALUE;

    long reference_pos_x = 0;
    long reference_pos_y = 0;

    for ( int cur_second = 0; cur_second < pMaxIteration; cur_second++ )
    {
      long cur_grid_x_min = Long.MAX_VALUE;
      long cur_grid_x_max = Long.MIN_VALUE;

      long cur_grid_y_min = Long.MAX_VALUE;
      long cur_grid_y_max = Long.MIN_VALUE;

      double cur_qubic_distance = 0.0;

      for ( Star cur_star : star_list )
      {
        cur_star.doMove();

        cur_qubic_distance += cur_star.getDistance( reference_pos_x, reference_pos_y );

        if ( cur_star.getPosX() < cur_grid_x_min ) cur_grid_x_min = cur_star.getPosX();

        if ( cur_star.getPosX() > cur_grid_x_max ) cur_grid_x_max = cur_star.getPosX();

        if ( cur_star.getPosY() < cur_grid_y_min ) cur_grid_y_min = cur_star.getPosY();

        if ( cur_star.getPosY() > cur_grid_y_max ) cur_grid_y_max = cur_star.getPosY();
      }

      if ( cur_qubic_distance < min_distance_value )
      {
        min_distance_value = cur_qubic_distance;

        min_distance_second = cur_second;

        grid_x_min = cur_grid_x_min;

        grid_x_max = cur_grid_x_max;

        grid_y_min = cur_grid_y_min;

        grid_y_max = cur_grid_y_max;
      }

      long cur_rect_volume = calcDistance( cur_grid_x_min, grid_x_max ) * calcDistance( cur_grid_y_min, cur_grid_y_max );

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

    long second_from = Math.max( 0, min_distance_second - 20 );

    long second_to = min_distance_second + 10;

    wl( "" );
    wl( "Starting loop 2 - debug from second " + second_from + " to " + second_to );
    wl( "" );

    for ( Star cur_star : star_list )
    {
      cur_star.reset();
    }

    for ( int cur_second = 0; cur_second < second_to; cur_second++ )
    {
      double cur_qubic_distance = 0.0;

      long cur_grid_x_min = Long.MAX_VALUE;
      long cur_grid_x_max = Long.MIN_VALUE;

      long cur_grid_y_min = Long.MAX_VALUE;
      long cur_grid_y_max = Long.MIN_VALUE;

      for ( Star cur_star : star_list )
      {
        cur_star.doMove();

        cur_qubic_distance += cur_star.getDistance( reference_pos_x, reference_pos_y );

        if ( cur_star.getPosX() < cur_grid_x_min ) cur_grid_x_min = cur_star.getPosX();

        if ( cur_star.getPosX() > cur_grid_x_max ) cur_grid_x_max = cur_star.getPosX();

        if ( cur_star.getPosY() < cur_grid_y_min ) cur_grid_y_min = cur_star.getPosY();

        if ( cur_star.getPosY() > cur_grid_y_max ) cur_grid_y_max = cur_star.getPosY();
      }

      long cur_rect_volume = calcDistance( cur_grid_x_min, grid_x_max ) * calcDistance( cur_grid_y_min, cur_grid_y_max );

      if ( cur_second >= second_from )
      {
        wl( String.format( "LOOP 2: Second %3d  -  Distance %10.2f  -  Min Distance %10.2f at second %3d  -  Hight %10d Width %10d Volume %10d ", cur_second, cur_qubic_distance, min_distance_value, min_distance_second, calcDistance( cur_grid_y_min, cur_grid_y_max ), calcDistance( cur_grid_x_min, cur_grid_x_max ), cur_rect_volume ) );
      }
    }

    wl( "" );
    wl( String.format( "Min Distance %f at second %3d     min_rect_value %10d at second %3d  ", min_distance_value, min_distance_second, min_rect_volume, min_rect_second ) );
    wl( "" );
    wl( "grid_x_min =>" + grid_x_min + "<" );
    wl( "grid_x_max =>" + grid_x_max + "<" );
    wl( "grid_y_min =>" + grid_y_min + "<" );
    wl( "grid_y_max =>" + grid_y_max + "<" );
    wl( "" );
    wl( "y =>" + calcDistance( grid_y_min, grid_y_max ) + "<" );
    wl( "x =>" + calcDistance( grid_x_min, grid_x_max ) + "<" );
    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
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

      pos_cur_x  = Long.parseLong( value_vector[ 0 ] );

      pos_cur_y  = Long.parseLong( value_vector[ 1 ] );

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

    public double getDistance( long pReferencePosX, long pReferencePosY )
    {
      double distance_x = (double) Day10_TheStarsAlign.calcDistance( pos_cur_x, pReferencePosX );

      double distance_y = (double) Day10_TheStarsAlign.calcDistance( pos_cur_y, pReferencePosY );

      return Math.hypot( distance_x, distance_y );
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
