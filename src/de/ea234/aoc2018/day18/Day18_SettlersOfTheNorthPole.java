package de.ea234.aoc2018.day18;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 18: Settlers of The North Pole ---
 * https://adventofcode.com/2018/day/18
 * 
 * https://www.reddit.com/r/adventofcode/comments/a77xq6/2018_day_18_solutions/
 * 
 * 
 * 
 * After 0 minutes: 
 * .#.#...|#.    .......##.
 * .....#|##|    ......|###
 * .|..|...#.    .|..|...#.
 * ..|#.....#    ..|#||...#
 * #.#|||#|#|    ..##||.|#|
 * ...#.||...    ...#||||..
 * .|....|...    ||...|||..
 * ||...#|.#|    |||||.||.|
 * |.||||..|.    ||||||||||
 * ...#.|..|.    ....||..|.
 * 
 * After 1 minutes: 
 * .......##.    .......#..
 * ......|###    ......|#..
 * .|..|...#.    .|.|||....
 * ..|#||...#    ..##|||..#
 * ..##||.|#|    ..###|||#|
 * ...#||||..    ...#|||||.
 * ||...|||..    |||||||||.
 * |||||.||.|    ||||||||||
 * ||||||||||    ||||||||||
 * ....||..|.    .|||||||||
 * 
 * After 2 minutes: 
 * .......#..    .......#..
 * ......|#..    ....|||#..
 * .|.|||....    .|.||||...
 * ..##|||..#    ..###|||.#
 * ..###|||#|    ...##|||#|
 * ...#|||||.    .||##|||||
 * |||||||||.    ||||||||||
 * ||||||||||    ||||||||||
 * ||||||||||    ||||||||||
 * .|||||||||    ||||||||||
 * 
 * After 3 minutes: 
 * .......#..    .....|.#..
 * ....|||#..    ...||||#..
 * .|.||||...    .|.#||||..
 * ..###|||.#    ..###||||#
 * ...##|||#|    ...###||#|
 * .||##|||||    |||##|||||
 * ||||||||||    ||||||||||
 * ||||||||||    ||||||||||
 * ||||||||||    ||||||||||
 * ||||||||||    ||||||||||
 * 
 * After 4 minutes: 
 * .....|.#..    ....|||#..
 * ...||||#..    ...||||#..
 * .|.#||||..    .|.##||||.
 * ..###||||#    ..####|||#
 * ...###||#|    .|.###||#|
 * |||##|||||    |||###||||
 * ||||||||||    ||||||||||
 * ||||||||||    ||||||||||
 * ||||||||||    ||||||||||
 * ||||||||||    ||||||||||
 * 
 * After 5 minutes: 
 * ....|||#..    ...||||#..
 * ...||||#..    ...||||#..
 * .|.##||||.    .|.###|||.
 * ..####|||#    ..#.##|||#
 * .|.###||#|    |||#.##|#|
 * |||###||||    |||###||||
 * ||||||||||    ||||#|||||
 * ||||||||||    ||||||||||
 * ||||||||||    ||||||||||
 * ||||||||||    ||||||||||
 * 
 * After 6 minutes: 
 * ...||||#..    ...||||#..
 * ...||||#..    ..||#|##..
 * .|.###|||.    .|.####||.
 * ..#.##|||#    ||#..##||#
 * |||#.##|#|    ||##.##|#|
 * |||###||||    |||####|||
 * ||||#|||||    |||###||||
 * ||||||||||    ||||||||||
 * ||||||||||    ||||||||||
 * ||||||||||    ||||||||||
 * 
 * After 7 minutes: 
 * ...||||#..    ..||||##..
 * ..||#|##..    ..|#####..
 * .|.####||.    |||#####|.
 * ||#..##||#    ||#...##|#
 * ||##.##|#|    ||##..###|
 * |||####|||    ||##.###||
 * |||###||||    |||####|||
 * ||||||||||    ||||#|||||
 * ||||||||||    ||||||||||
 * ||||||||||    ||||||||||
 * 
 * After 8 minutes: 
 * ..||||##..    ..||###...
 * ..|#####..    .||#####..
 * |||#####|.    ||##...##.
 * ||#...##|#    ||#....###
 * ||##..###|    |##....##|
 * ||##.###||    ||##..###|
 * |||####|||    ||######||
 * ||||#|||||    |||###||||
 * ||||||||||    ||||||||||
 * ||||||||||    ||||||||||
 * 
 * After 9 minutes: 
 * ..||###...    .||##.....
 * .||#####..    ||###.....
 * ||##...##.    ||##......
 * ||#....###    |##.....##
 * |##....##|    |##.....##
 * ||##..###|    |##....##|
 * ||######||    ||##.####|
 * |||###||||    ||#####|||
 * ||||||||||    ||||#|||||
 * ||||||||||    ||||||||||
 * 
 * 
 * 
 * .||##.....
 * ||###.....
 * ||##......
 * |##.....##
 * |##.....##
 * |##....##|
 * ||##.####|
 * ||#####|||
 * ||||#|||||
 * ||||||||||
 * 
 * 
 * count_trees       37
 * count_lumberyards 31
 * 
 * Result Part 1 1147
 * Result Part 2 0
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * ........#|||||#.....|||||||||||||||||||||||||||...
 * .......####||##....|||||||||||||||||||||||||||||..
 * .........###|##....|||||||||||||||||||||#|||||||||
 * ..........#####....##|||||#|||||||||||||#||||||||.
 * ...........#####...##||||#|||||#|||||||||||||||||.
 * ...........######.###|||||||||#|||#||||||||||||||.
 * ............##|#####|||#|||||||||||#||||#|||||||||
 * ...........##||||#||||#|||||||||||||||||#|||||||||
 * ##........###||||||||||||||||||||||||#||||||||||||
 * ###......###|||||###|||||||||||||||####|||||||||||
 * |##......##|||||#####||||##|||||||###.##||||||||||
 * ###......##|||||##..##|||###|||||##...#|||##||||#|
 * ##.......###|||####.##|||######|###...||||##|||###
 * ..........##|||#######||||########....|||||||||###
 * .........###|||########|||##...###.....||||||||##|
 * ........||||||||||##..#|||##.............||||###||
 * ........|||||###||#....||||................||####|
 * ........|||#####||......||...................#..#.
 * ..........###.......................####..........
 * ..|................................#####..........
 * ||||.......####....................##||##.........
 * ||||#....######..................###|||||.......##
 * ||||##.####|||##.................####||||......###
 * ||||#####|||||###....###.........#####||......###|
 * ||||||#||||||||##....#######.........#.......####|
 * ||||||||||||#|||##..########................####||
 * ||||##|||||####|######||####...............###||||
 * ||||##||||##.###||###|||####...............##|||||
 * ||||||||||##..##|||||||||##......###......##||||||
 * ||||||||||###.##|||||||...#....#####......##|||#||
 * |##||||||||####||||||||......####|##.....##||||#||
 * |##||#|||||||#|||||||||.....###||||##..####|||||||
 * |||||#||||||||#||||||||....###|||||##||###||||||||
 * ||||||||||||#####||||||....##|||||||||||||||||||||
 * |||||||#######.###|||||#..##|||||#||||||||||||||#|
 * ||||||||####.....##||||#####||||||#||||||||||||#||
 * |||||##|###......#|||||###||||||||||||||||||||##||
 * |||########........|###||||########||||||||||####|
 * ||###..#####.........###|###########||||###||#.###
 * |##.....####..........#####.......##|||####||...##
 * |##......#####........###..........##||##.........
 * |##........#####.....###...........##|##..........
 * |##........#######.######...........####..........
 * |##......####|||#########|||......###||##.........
 * |##.....####||||||#|#####||||.....|#|||##.......##
 * ..#.....##||||||||||##..#|||||...|||||||##...##|||
 * ........##||||||||||##..##||||...|||||||###.###|||
 * .......##|||||||||||#####|||||...||||||||#####|||.
 * .......##||||||||||||###||||||....|||||||||#||||..
 * .|......|||||||||||||||||||||.....||||||||||||||..
 * 
 * 
 * count_trees       1020
 * count_lumberyards 632
 * 
 * Result Part 1 644640
 * Result Part 2 0
 * 
 * 
 * </pre> 
 */
public class Day18_SettlersOfTheNorthPole
{

  private static final char   CHAR_OPEN_GROUND   = '.';

  private static final char   CHAR_TREES         = '|';

  private static final char   CHAR_LUMBERYARD    = '#';

  private static final int    MAP_1              = 0;

  private static final int    MAP_2              = 1;

  private static final String STR_COMBINE_SPACER = "    ";

  public static void main( String[] args )
  {
    String test_content = ".#.#...|#.,.....#|##|,.|..|...#.,..|#.....#,#.#|||#|#|,...#.||...,.|....|...,||...#|.#|,|.||||..|.,...#.|..|.";

    calculatePart01( test_content, 10, true );

    calculate01( getListProd(), 10, false );
  }

  private static void calculatePart01( String pString, int pRepeat, boolean pKnzDebug )
  {
    List< String > converted_string_list = Arrays.stream( pString.split( "," ) ).collect( Collectors.toList() );

    calculate01( converted_string_list, pRepeat, pKnzDebug );
  }

  private static void calculate01( List< String > pListInput, int pRepeat, boolean pKnzDebug )
  {
    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );

    long result_part_01 = 0;

    long result_part_02 = 0;

    int grid_height = pListInput.size();
    int grid_width  = pListInput.get( 0 ).length();

    int[][][] grid_map = new int[ 2 ][ grid_height ][ grid_width ];

    int cur_row = 0;

    int grid_write = MAP_1;

    int grid_read = MAP_2;

    for ( String input_str : pListInput )
    {
      for ( int cur_idx = 0; cur_idx < input_str.length(); cur_idx++ )
      {
        grid_map[ grid_write ][ cur_row ][ cur_idx ] = input_str.charAt( cur_idx );
      }

      cur_row++;
    }

    /*
     * ********************************************************************************************
     */

    for ( int round_nr = 0; round_nr < pRepeat; round_nr++ )
    {
      if ( grid_read == MAP_1 )
      {
        grid_read  = MAP_2;

        grid_write = MAP_1;
      }
      else
      {
        grid_read  = MAP_1;

        grid_write = MAP_2;
      }

      for ( cur_row = 0; cur_row < grid_height; cur_row++ )
      {
        for ( int cur_col = 0; cur_col < grid_width; cur_col++ )
        {
          int count_trees       = getAdjacentFields( grid_map, grid_read, cur_row, cur_col, CHAR_TREES      );

          int count_lumberyards = getAdjacentFields( grid_map, grid_read, cur_row, cur_col, CHAR_LUMBERYARD );

          /*
           * Copy the current state from the read-grid into the write grid.
           */
          grid_map[ grid_write ][ cur_row ][ cur_col ] = grid_map[ grid_read ][ cur_row ][ cur_col ];

          /*
           * An open acre will become filled with trees if three or more adjacent acres contained trees. Otherwise, nothing happens.
           */
          if ( grid_map[ grid_read ][ cur_row ][ cur_col ] == CHAR_OPEN_GROUND )
          {
            if ( count_trees >= 3 )
            {
              grid_map[ grid_write ][ cur_row ][ cur_col ] = CHAR_TREES;
            }
          }

          /*
           * An acre filled with trees will become a lumberyard if three or more adjacent acres were lumberyards. Otherwise, nothing happens.
           */
          else if ( grid_map[ grid_read ][ cur_row ][ cur_col ] == CHAR_TREES )
          {
            if ( count_lumberyards >= 3 )
            {
              grid_map[ grid_write ][ cur_row ][ cur_col ] = CHAR_LUMBERYARD;
            }
          }

          /*
           * An acre containing a lumberyard will 
           * ...remain a lumberyard if it was adjacent to at least one other lumberyard and at least one acre containing trees. 
           * Otherwise, it becomes open.
           */
          else if ( grid_map[ grid_read ][ cur_row ][ cur_col ] == CHAR_LUMBERYARD )
          {
            if ( ( count_lumberyards >= 1 ) && ( count_trees >= 1 ) )
            {
              grid_map[ grid_write ][ cur_row ][ cur_col ] = CHAR_LUMBERYARD;
            }
            else
            {
              grid_map[ grid_write ][ cur_row ][ cur_col ] = CHAR_OPEN_GROUND;
            }
          }
        }
      }

      if ( pKnzDebug )
      {
        wl( "" );
        wl( "After " + round_nr + " minutes: " );
        wl( combineStrings( getDebugGrid( grid_map, grid_read, 0, 0, grid_height, grid_width ), getDebugGrid( grid_map, grid_write, 0, 0, grid_height, grid_width ) ) );
      }
    }

    wl( "" );
    wl( "" );
    wl( "" );
    wl( getDebugGrid( grid_map, grid_write, 0, 0, grid_height, grid_width ) );
    wl( "" );

    int count_trees       = countGrid( grid_map, grid_write, 0, 0, grid_height, grid_width, CHAR_TREES      );
    int count_lumberyards = countGrid( grid_map, grid_write, 0, 0, grid_height, grid_width, CHAR_LUMBERYARD );

    result_part_01 = count_lumberyards * count_trees;

    wl( "count_trees       " + count_trees );
    wl( "count_lumberyards " + count_lumberyards );
    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
  }

  private static int getAdjacentFields( int[][][] pGrid, int pSet, int pRow, int pCol, char pChar )
  {
    int neighbour_count = 0;

    neighbour_count += getState( pGrid, pSet, pRow - 1, pCol - 1, pChar );
    neighbour_count += getState( pGrid, pSet, pRow - 1, pCol,     pChar );
    neighbour_count += getState( pGrid, pSet, pRow - 1, pCol + 1, pChar );

    neighbour_count += getState( pGrid, pSet, pRow,     pCol - 1, pChar );
    neighbour_count += getState( pGrid, pSet, pRow,     pCol + 1, pChar );

    neighbour_count += getState( pGrid, pSet, pRow + 1, pCol - 1, pChar );
    neighbour_count += getState( pGrid, pSet, pRow + 1, pCol,     pChar );
    neighbour_count += getState( pGrid, pSet, pRow + 1, pCol + 1, pChar );

    return neighbour_count;
  }

  private static int getState( int[][][] pGrid, int pSet, int pRow, int pCol, char pChar )
  {
    try
    {
      return pGrid[ pSet ][ pRow ][ pCol ] == pChar ? 1 : 0;
    }
    catch ( Exception e )
    {
    }

    return 0;
  }

  private static String getDebugGrid( int[][][] pGrid, int pSet, int pRowStart, int pColStart, int pRowEnd, int pColEnd )
  {
    StringBuilder dbg_string = new StringBuilder();

    for ( int cur_row = pRowStart; cur_row < pRowEnd; cur_row++ )
    {
      for ( int cur_col = pColStart; cur_col < pColEnd; cur_col++ )
      {
        dbg_string.append( (char) pGrid[ pSet ][ cur_row ][ cur_col ] );
      }

      dbg_string.append( "\n" );
    }

    return dbg_string.toString();
  }

  private static int countGrid( int[][][] pGrid, int pSet, int pRowStart, int pColStart, int pRowEnd, int pColEnd, char pChar )
  {
    int result = 0;

    for ( int cur_row = pRowStart; cur_row < pRowEnd; cur_row++ )
    {
      for ( int cur_col = pColStart; cur_col < pColEnd; cur_col++ )
      {
        result += pGrid[ pSet ][ cur_row ][ cur_col ] == pChar ? 1 : 0;
      }
    }

    return result;
  }

  private static String combineStrings( String pString1, String pString2 )
  {
    String[] lines1 = pString1 != null ? pString1.split( "\r?\n" ) : new String[ 0 ];

    String[] lines2 = pString2 != null ? pString2.split( "\r?\n" ) : new String[ 0 ];

    int max_lines = Math.max( lines1.length, lines2.length );

    StringBuilder string_builder = new StringBuilder();

    for ( int line_index = 0; line_index < max_lines; line_index++ )
    {
      String str_a = line_index < lines1.length ? lines1[ line_index ] : "";

      String str_b = line_index < lines2.length ? lines2[ line_index ] : "";

      string_builder.append( str_a ).append( STR_COMBINE_SPACER ).append( str_b );

      if ( line_index < max_lines - 1 )
      {
        string_builder.append( "\n" );
      }
    }

    return string_builder.toString();
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2018__day18_input.txt";

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

  private static void wl( String pString )
  {
    System.out.println( pString );
  }
}
