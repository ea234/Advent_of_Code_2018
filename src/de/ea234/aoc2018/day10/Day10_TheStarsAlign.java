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
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * Second   0  -  Distance 216,19309249    -  Min Distance 216,19309249 at second   0      
 * Second   1  -  Distance 199,04624208    -  Min Distance 199,04624208 at second   1      
 * Second   2  -  Distance 193,10117078    -  Min Distance 193,10117078 at second   2      
 * Second   3  -  Distance 201,55896583    -  Min Distance 193,10117078 at second   2      
 * Second   4  -  Distance 219,34383044    -  Min Distance 193,10117078 at second   2      
 * Second   5  -  Distance 247,04809891    -  Min Distance 193,10117078 at second   2      
 * Second   6  -  Distance 280,83379236    -  Min Distance 193,10117078 at second   2      
 * Second   7  -  Distance 323,00603333    -  Min Distance 193,10117078 at second   2      
 * Second   8  -  Distance 369,58506537    -  Min Distance 193,10117078 at second   2      
 * Second   9  -  Distance 417,82309723    -  Min Distance 193,10117078 at second   2      
 * Second  10  -  Distance 467,23464381    -  Min Distance 193,10117078 at second   2      
 * Second  11  -  Distance 517,49074946    -  Min Distance 193,10117078 at second   2      
 * Second  12  -  Distance 568,36963678    -  Min Distance 193,10117078 at second   2      
 * Second  13  -  Distance 619,71911836    -  Min Distance 193,10117078 at second   2      
 * Second  14  -  Distance 671,43185356    -  Min Distance 193,10117078 at second   2      
 * Second  15  -  Distance 723,43007194    -  Min Distance 193,10117078 at second   2      
 * Second  16  -  Distance 775,65612656    -  Min Distance 193,10117078 at second   2      
 * Second  17  -  Distance 828,06647435    -  Min Distance 193,10117078 at second   2      
 * Second  18  -  Distance 880,62769948    -  Min Distance 193,10117078 at second   2      
 * Second  19  -  Distance 933,31380073    -  Min Distance 193,10117078 at second   2      
 * 
 * Min Distance 193,10117078 at second   2      
 * 
 * grid_x_min =>0<
 * grid_x_max =>9<
 * grid_y_min =>0<
 * grid_y_max =>7<
 * 
 * y =>7<
 * x =>9<
 * 
 * 
 * Result Part 1 0
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * 
 * Min Distance 108965,15520811 at second 10310      
 * 
 * grid_x_min =>162<
 * grid_x_max =>293<
 * grid_y_min =>152<
 * grid_y_max =>231<
 * 
 * y =>79<
 * x =>131<
 * 
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

    double min_distance_value = Double.MAX_VALUE;

    int min_distance_sec = 0;

    long grid_x_min = Long.MAX_VALUE;
    long grid_x_max = Long.MIN_VALUE;

    long grid_y_min = Long.MAX_VALUE;
    long grid_y_max = Long.MIN_VALUE;

    long reference_x = 0;
    long reference_y = 0;

    for ( int cur_second = 0; cur_second < pMaxIteration; cur_second++ )
    {
      long cgrid_x_min = Long.MAX_VALUE;
      long cgrid_x_max = Long.MIN_VALUE;

      long cgrid_y_min = Long.MAX_VALUE;
      long cgrid_y_max = Long.MIN_VALUE;

      double cur_qubic_distance = 0.0;

      for ( Star cur_star : star_list )
      {
        cur_star.doMove();

        cur_qubic_distance += cur_star.getDistance( reference_x, reference_y );

        if ( cur_star.getPosX() < cgrid_x_min ) cgrid_x_min = cur_star.getPosX();

        if ( cur_star.getPosX() > cgrid_x_max ) cgrid_x_max = cur_star.getPosX();

        if ( cur_star.getPosY() < cgrid_y_min ) cgrid_y_min = cur_star.getPosY();

        if ( cur_star.getPosY() > cgrid_y_max ) cgrid_y_max = cur_star.getPosY();
      }

      if ( cur_qubic_distance < min_distance_value )
      {
        min_distance_value = cur_qubic_distance;

        min_distance_sec = cur_second;

        grid_x_min = cgrid_x_min;

        grid_x_max = cgrid_x_max;

        grid_y_min = cgrid_y_min;

        grid_y_max = cgrid_y_max;
      }

      if ( pKnzDebug )
      {
        wl( String.format( "Second %3d  -  Distance %10.8f    -  Min Distance %10.8f at second %3d      ", cur_second, cur_qubic_distance, min_distance_value, min_distance_sec ) );
      }
    }

    wl( "" );
    wl( String.format( "Min Distance %10.8f at second %3d      ", min_distance_value, min_distance_sec ) );
    wl( "" );
    wl( "grid_x_min =>" + grid_x_min + "<" );
    wl( "grid_x_max =>" + grid_x_max + "<" );
    wl( "grid_y_min =>" + grid_y_min + "<" );
    wl( "grid_y_max =>" + grid_y_max + "<" );
    wl( "" );
    wl( "y =>" + calcDistance( grid_y_min, grid_y_max ) + "<" );
    wl( "x =>" + calcDistance( grid_x_min, grid_x_max ) + "<" );
    wl( "" );
    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
  }

  private static class Star
  {
    private long pos_x      = 0;

    private long pos_y      = 0;

    private long velocity_x = 0;

    private long velocity_y = 0;

    public Star( String pInput )
    {
      String str_only_numbers = pInput.replaceAll( "position=<", "" ).replaceAll( "> velocity=<", "," ).replaceAll( ">", "" ).replaceAll( " ", "" );

      String[] value_vector = str_only_numbers.split( "," );

      pos_x = Long.parseLong( value_vector[ 0 ] );

      pos_y = Long.parseLong( value_vector[ 1 ] );

      velocity_x = Long.parseLong( value_vector[ 2 ] );

      velocity_y = Long.parseLong( value_vector[ 3 ] );
    }

    public void doMove()
    {
      pos_x += velocity_x;

      pos_y += velocity_y;
    }

    public long getPosX()
    {
      return pos_x;
    }

    public long getPosY()
    {
      return pos_y;
    }

    public double getDistance( long pReferenceX, long pReferenceY )
    {
      double dx = (double) Day10_TheStarsAlign.calcDistance( pos_x, pReferenceX );

      double dy = (double) Day10_TheStarsAlign.calcDistance( pos_y, pReferenceY );

      double dist = Math.hypot( dx, dy );

      return dist;
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
