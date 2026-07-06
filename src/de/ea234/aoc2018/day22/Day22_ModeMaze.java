package de.ea234.aoc2018.day22;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 22: Mode Maze ---
 * https://adventofcode.com/2018/day/22
 * 
 * https://www.reddit.com/r/adventofcode/comments/a8i1cy/2018_day_22_solutions/
 * 
 *
 *           1
 * 0123456789012345
 * 
 * M=.|=.|.|=.|=|=.
 * .|=|=|||..|.=...
 * .==|....||=..|==
 * =.|....|.==.|==.
 * =|..==...=.|==..
 * =||.=.=||=|=..|=
 * |.=.===|||..=..|
 * |..==||=.|==|===
 * .=..===..=|.|||.
 * .======|||=|=.|=
 * .===|=|===T===||
 * =|||...|==..|=.|
 * =.=|=.=..=.||==|
 * ||=|=...|==.=|==
 * |=.=||===.|||===
 * ||.|==.|.|.||=||
 * 
 * 
 * 
 * 
 * M..===||...==|||..==
 * =|=.||..===|.|=||=..
 * .==|.|=..==|==...|.|
 * |||....=.==|||=|||.=
 * ==..======|.==|=..|.
 * .|..=.=|===...|=.||=
 * ||...==|==|.=.=.|=.=
 * .|.|.||=.|=|..=|||==
 * |.|..||..|==.|=.|.|.
 * =.|===||=|====.||...
 * .|==.|.=|=T..=|.||..
 * |...|=.=.|=.|.||==..
 * ==.|=.=||==||=||.|.=
 * |.|==..=|.===|====..
 * =.==.|.=|||.===|.==.
 * ..=..=|=.=|||==|.=.|
 * |...=||=||=.|=.==.||
 * ==|===||=|.=|==.||.=
 * .=|||||..==.||.|==.|
 * =..=|.==.||.===|.||.
 * 
 * 
 * Geologic Index
 *            0       48271       96542      144813      193084      241355      289626      337897      386168      434439      482710
 *        16807   145722555    29457600    44918746   144331707    71149568    37892722   150780939    46219239     7540974   251420475
 *        33614    25163505   177774661    39951867    36628480    85191084   188332494    78418209     5271756    59552175    17378590
 *        50421   169008305    54750692   152339320   318917412   122558226    41058360    51615072    34452453     7469970     4257490
 *        67228   119021084    36415358   107429544   102235392    68092920   107504808    81559660      388194    14478790   158301730
 *        84035     9315159    64851216    60431616    38045568    18160033   170837160     5841586    48135443   159199609   117098858
 *       100842     4940722    62125260    10543104     9082824    15769584    62803618   136431335   294191588    78782620   157948585
 *       117649   285905520    34532160   159657120     9938400    63635454   283677620    90659620    85719130    21660759    84059200
 *       134456   191863780    92620080     8110300   154647198   106501176    98119570   192219209    41487390    58109850    61996094
 *       151263    49228464     2117511   330921303     7480890   221476820    97503107   335556270   170483175    63288701   226558166
 *       168070    18764892   292335655     7522585   205610110    59325129   153910054   218797545   258990445    41850694           0
 * 
 * 
 * Erosion Level
 *          510        8415       16320        4042       11947       19852        7574       15479        3201       11106       19011
 *        17317        1805       11113       12081        3584        5003        9741       14439         679       13225        1354
 *        13941       15997        3307       10220       17028       19334        5431        7764        4503       12835        1537
 *        10565       16556       14906       18729        6339        7560        6648        7651         582        2770       19570
 *         7189        2443        5736       16128        9007       16171       10660         667        5227        8089        6971
 *         3813       11306        3747        4224        1123       16026        8758        9209       19681       16798       17785
 *          437       16580        2496        8088         984        7171       14815       14948        4690        8881       17120
 *        17244       13835       19740       10100        8874       19148        6065       18277        2439        4910       17698
 *        13868        4692         803       17427        5562       16178       10517       17010       11835        3503       14611
 *        10492        2637       18989        1345       13690        9271       19727       14405       18067       15506        4501
 *         7116       15395        5593       15019        6399        7802       15189       14335        2699       11845         510
 * 
 * 
 * Risk Level
 *            0           0           0           1           1           1           2           2           0           0           0
 *            1           2           1           0           2           2           0           0           1           1           1
 *            0           1           1           2           0           2           1           0           0           1           1
 *            2           2           2           0           0           0           0           1           0           1           1
 *            1           1           0           0           1           1           1           1           1           1           2
 *            0           2           0           0           1           0           1           2           1           1           1
 *            2           2           0           0           0           1           1           2           1           1           2
 *            0           2           0           2           0           2           2           1           0           2           1
 *            2           0           2           0           0           2           2           0           0           2           1
 *            1           0           2           1           1           1           2           2           1           2           1
 *            0           2           1           1           0           2           0           1           2           1           0
 * 
 * 
 * Result Part 1 93
 * 
 * </pre> 
 */
public class Day22_ModeMaze
{
  private static final Pattern PATTERN           = Pattern.compile( "pos=<(-?\\d+),(-?\\d+),(-?\\d+)>, r=(-?\\d+)" );

  private static final long    MULTIPLICATOR_ROW = 16807;

  private static final long    MULTIPLICATOR_COL = 48271;

  private static final long    MODULO_NUMBER     = 20183;

  private static final char    CHAR_ROCKY        = '.';

  private static final char    CHAR_WET          = '=';

  private static final char    CHAR_NARROW       = '|';

  private static final char    CHAR_TARGET       = 'T';

  private static final char    CHAR_MOUTH        = 'M';

  public static void main( String[] args )
  {
    calculate( 510, 10, 10 );

    System.exit( 0 );
  }

  public static void calculate( int pCaveDepth, int pTargetRow, int pTargetCol )
  {
    int set_map            = 0;
    int set_geologic_index = 1;
    int set_erosion_level  = 2;
    int set_risk_level     = 3;

    long cave_depth = pCaveDepth;
    int target_row  = pTargetRow;
    int target_col  = pTargetCol;

    int grid_width  = Math.max( target_row + 2, 20 );
    int grid_height = Math.max( target_col + 2, 20 );

    long[][][] grid_map = new long[ 4 ][ grid_height + 1 ][ grid_width + 1 ];

    /*
     * *******************************************************************************************************
     * Calculating the map
     * *******************************************************************************************************
     */

    for ( int cur_row = 0; cur_row < grid_height; cur_row++ )
    {
      for ( int cur_col = 0; cur_col < grid_width; cur_col++ )
      {
        long geologic_index = 0;

        if ( cur_row == 0 )
        {
          /*
           * If the region's X (=row) coordinate is 0, the geologic index is its Y coordinate times 48271.
           */
          geologic_index = cur_col * MULTIPLICATOR_COL;
        }
        else if ( cur_col == 0 )
        {
          /*
           * If the region's Y (=col) coordinate is 0, the geologic index is its X coordinate times 16807.
           */
          geologic_index = cur_row * MULTIPLICATOR_ROW;
        }
        else if ( ( cur_row == target_row ) && ( cur_col == target_col ) )
        {
          /*
           * The region at the coordinates of the target has a geologic index of 0.
           */
          geologic_index = 0;
        }
        else if ( ( cur_row > 0 ) || ( cur_col > 0 ) )
        {
          /*
           * Otherwise, the region's geologic index is the result of multiplying 
           * the erosion levels of the regions at X-1,Y and X,Y-1.
           */
          long erosion_level_1 = grid_map[ set_erosion_level ][ cur_row - 1 ][ cur_col ];

          long erosion_level_2 = grid_map[ set_erosion_level ][ cur_row ][ cur_col - 1 ];

          geologic_index = erosion_level_1 * erosion_level_2;
        }

        grid_map[ set_geologic_index ][ cur_row ][ cur_col ] = geologic_index;

        /*
         * A region's erosion level is its geologic index plus the cave system's depth, modulo 20183.
         */
        long erosion_level = ( geologic_index + cave_depth ) % MODULO_NUMBER;

        grid_map[ set_erosion_level ][ cur_row ][ cur_col ] = erosion_level;

        /*
         * Type:
         * - If the erosion level modulo 3 is 0, the region's type is rocky.
         * - If the erosion level modulo 3 is 1, the region's type is wet.
         * - If the erosion level modulo 3 is 2, the region's type is narrow.        
         */
        long region_type = erosion_level % 3l;

             if ( region_type == 0 ) grid_map[ set_map ][ cur_row ][ cur_col ] = CHAR_ROCKY;
        else if ( region_type == 1 ) grid_map[ set_map ][ cur_row ][ cur_col ] = CHAR_WET;
        else if ( region_type == 2 ) grid_map[ set_map ][ cur_row ][ cur_col ] = CHAR_NARROW;

        /*
         * The risk level of for a region: 0 for rocky regions, 1 for wet regions, and 2 for narrow regions.
         * (=equal to the region_type)
         */
        grid_map[ set_risk_level ][ cur_row ][ cur_col ] = region_type;

      }
    }

    grid_map[ set_map ][ target_row ][ target_col ] = CHAR_TARGET;
    grid_map[ set_map ][ 0          ][ 0          ] = CHAR_MOUTH;

    /*
     * *******************************************************************************************************
     * Calculating the risk level for the area to the target
     * *******************************************************************************************************
     */

    long result_part_01 = 0;

    for ( int cur_row = 0; cur_row < target_row; cur_row++ )
    {
      for ( int cur_col = 0; cur_col < target_col; cur_col++ )
      {
        result_part_01 += grid_map[ set_risk_level ][ cur_row ][ cur_col ];
      }
    }

    wl( "" );
    wl( "" );
    wl( getDebugGridChar( grid_map, set_map, 0, 0, grid_height, grid_width ) );
    wl( "" );
    wl( "Geologic Index" );
    wl( getDebugGridNumber( grid_map, set_geologic_index, 0, 0, target_row + 1, target_col + 1 ) );
    wl( "" );
    wl( "Erosion Level" );
    wl( getDebugGridNumber( grid_map, set_erosion_level, 0, 0, target_row + 1, target_col + 1 ) );
    wl( "" );
    wl( "Risk Level" );
    wl( getDebugGridNumber( grid_map, set_risk_level, 0, 0, target_row + 1, target_col + 1 ) );
    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
  }

  private static String getDebugGridChar( long[][][] pGrid, int pSet, int pRowStart, int pColStart, int pRowEnd, int pColEnd )
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

  private static String getDebugGridNumber( long[][][] pGrid, int pSet, int pRowStart, int pColStart, int pRowEnd, int pColEnd )
  {
    StringBuilder dbg_string = new StringBuilder();

    for ( int cur_row = pRowStart; cur_row < pRowEnd; cur_row++ )
    {
      for ( int cur_col = pColStart; cur_col < pColEnd; cur_col++ )
      {
        dbg_string.append( String.format( " %11d", pGrid[ pSet ][ cur_row ][ cur_col ] ) );
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
