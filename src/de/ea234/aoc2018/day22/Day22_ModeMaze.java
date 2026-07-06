package de.ea234.aoc2018.day22;

/**
 * <pre>
 * 
 * --- Day 22: Mode Maze ---
 * https://adventofcode.com/2018/day/22
 * 
 * https://www.reddit.com/r/adventofcode/comments/a8i1cy/2018_day_22_solutions/
 * 
 * https://github.com/ea234/Advent_of_Code_2018/blob/main/src/de/ea234/aoc2018/day22/Day22_ModeMaze.java
 * 
 * 
 * Generated Map
 * M=.|=.|.|=.|=|=.|=.=
 * .|=|=|||..|.=....==.
 * .==|....||=..|==.||.
 * =.|....|.==.|==..=|=
 * =|..==...=.|==..==||
 * =||.=.=||=|=..|=|=|.
 * |.=.===|||..=..||||=
 * |..==||=.|==|====|.=
 * .=..===..=|.|||.|=..
 * .======|||=|=.|=||=|
 * .===|=|===T===||=.=|
 * =|||...|==..|=.|.=..
 * =.=|=.=..=.||==||||=
 * ||=|=...|==.=|====|=
 * |=.=||===.|||===.=.=
 * ||.|==.|.|.||=||=.||
 * .|.|..|||||=.=..=|=.
 * .=||.|=|..|=|===.|=|
 * =...||.=|.....=.|..|
 * =.|=.===....=..||=|.
 * 
 * 
 * Geologic Index
 *            0       16807       33614       50421       67228       84035      100842      117649      134456      151263      168070
 *        48271   145722555    25163505   169008305   119021084     9315159     4940722   285905520   191863780    49228464    18764892
 *        96542    29457600   177774661    54750692    36415358    64851216    62125260    34532160    92620080     2117511   292335655
 *       144813    44918746    39951867   152339320   107429544    60431616    10543104   159657120     8110300   330921303     7522585
 *       193084   144331707    36628480   318917412   102235392    38045568     9082824     9938400   154647198     7480890   205610110
 *       241355    71149568    85191084   122558226    68092920    18160033    15769584    63635454   106501176   221476820    59325129
 *       289626    37892722   188332494    41058360   107504808   170837160    62803618   283677620    98119570    97503107   153910054
 *       337897   150780939    78418209    51615072    81559660     5841586   136431335    90659620   192219209   335556270   218797545
 *       386168    46219239     5271756    34452453      388194    48135443   294191588    85719130    41487390   170483175   258990445
 *       434439     7540974    59552175     7469970    14478790   159199609    78782620    21660759    58109850    63288701    41850694
 *       482710   251420475    17378590     4257490   158301730   117098858   157948585    84059200    61996094   226558166           0
 * 
 * 
 * Erosion Level
 *          510       17317       13941       10565        7189        3813         437       17244       13868       10492        7116
 *         8415        1805       15997       16556        2443       11306       16580       13835        4692        2637       15395
 *        16320       11113        3307       14906        5736        3747        2496       19740         803       18989        5593
 *         4042       12081       10220       18729       16128        4224        8088       10100       17427        1345       15019
 *        11947        3584       17028        6339        9007        1123         984        8874        5562       13690        6399
 *        19852        5003       19334        7560       16171       16026        7171       19148       16178        9271        7802
 *         7574        9741        5431        6648       10660        8758       14815        6065       10517       19727       15189
 *        15479       14439        7764        7651         667        9209       14948       18277       17010       14405       14335
 *         3201         679        4503         582        5227       19681        4690        2439       11835       18067        2699
 *        11106       13225       12835        2770        8089       16798        8881        4910        3503       15506       11845
 *        19011        1354        1537       19570        6971       17785       17120       17698       14611        4501         510
 * 
 * 
 * Risk Level
 *            0           1           0           2           1           0           2           0           2           1           0
 *            0           2           1           2           1           2           2           2           0           0           2
 *            0           1           1           2           0           0           0           0           2           2           1
 *            1           0           2           0           0           0           0           2           0           1           1
 *            1           2           0           0           1           1           0           0           0           1           0
 *            1           2           2           0           1           0           1           2           2           1           2
 *            2           0           1           0           1           1           1           2           2           2           0
 *            2           0           0           1           1           2           2           1           0           2           1
 *            0           1           0           0           1           1           1           0           0           1           2
 *            0           1           1           1           1           1           1           2           2           2           1
 *            0           1           1           1           2           1           2           1           1           1           0
 * 
 * 
 * Result Part 1 114
 * 
 * Result Part 1 7915
 * 
 * </pre> 
 */
public class Day22_ModeMaze
{
  private static final long MULTIPLICATOR_Y     = 48271;

  private static final long MULTIPLICATOR_X     = 16807;

  private static final long MODULO_NUMBER       = 20183;

  private static final int  SET_MAP             = 0;

  private static final int  SET_GEOLOGIC_INDEX  = 1;

  private static final int  SET_EROSION_LEVEL   = 2;

  private static final int  SET_RISK_LEVEL      = 3;

  private static final char CHAR_ROCKY          = '.';

  private static final char CHAR_WET            = '=';

  private static final char CHAR_NARROW         = '|';

  private static final char CHAR_TARGET         = 'T';

  private static final char CHAR_MOUTH          = 'M';

  public static void main( String[] args )
  {
    calculate(  510, 10,  10, true  );

    calculate( 3339, 10, 715, false );

    System.exit( 0 );
  }

  public static void calculate( int pCaveDepth, int pTargetX, int pTargetY, boolean pKnzDebug )
  {
    int grid_height = Math.max( pTargetY + 5, 10 );

    int grid_width  = Math.max( pTargetX + 5, 10 );

    long[][][] grid_map = new long[ 4 ][ grid_height + 1 ][ grid_width + 1 ];

    /*
     * *******************************************************************************************************
     * Calculating the map
     * *******************************************************************************************************
     */

    for ( int cur_y = 0; cur_y < grid_height; cur_y++ )
    {
      for ( int cur_x = 0; cur_x < grid_width; cur_x++ )
      {
        long geologic_index = 0;

        if ( cur_y == 0 )
        {
          /*
           * If the region's Y coordinate is 0, the geologic index is its X coordinate times 16807.
           */
          geologic_index = cur_x * MULTIPLICATOR_X;
        }
        else if ( cur_x == 0 )
        {
          /*
           * If the region's X coordinate is 0, the geologic index is its Y coordinate times 48271.
           */
          geologic_index = cur_y * MULTIPLICATOR_Y;
        }
        else if ( ( cur_y == pTargetY ) && ( cur_x == pTargetX ) )
        {
          /*
           * The region at the coordinates of the target has a geologic index of 0.
           */
          geologic_index = 0;
        }
        else if ( ( cur_y > 0 ) || ( cur_x > 0 ) )
        {
          /*
           * Otherwise, the region's geologic index is the result of multiplying 
           * the erosion levels of the regions at X-1,Y and X,Y-1.
           */
          long erosion_level_1 = grid_map[ SET_EROSION_LEVEL ][ cur_y - 1 ][ cur_x     ];

          long erosion_level_2 = grid_map[ SET_EROSION_LEVEL ][ cur_y     ][ cur_x - 1 ];

          geologic_index = erosion_level_1 * erosion_level_2;
        }

        grid_map[ SET_GEOLOGIC_INDEX ][ cur_y ][ cur_x ] = geologic_index;

        /*
         * A region's erosion level is its geologic index plus the cave system's depth, modulo 20183.
         */
        long erosion_level = ( geologic_index + pCaveDepth ) % MODULO_NUMBER;

        grid_map[ SET_EROSION_LEVEL ][ cur_y ][ cur_x ] = erosion_level;

        /*
         * Type:
         * - If the erosion level modulo 3 is 0, the region's type is rocky.
         * - If the erosion level modulo 3 is 1, the region's type is wet.
         * - If the erosion level modulo 3 is 2, the region's type is narrow.        
         */
        long region_type = erosion_level % 3l;

             if ( region_type == 0 ) grid_map[ SET_MAP ][ cur_y ][ cur_x ] = CHAR_ROCKY;
        else if ( region_type == 1 ) grid_map[ SET_MAP ][ cur_y ][ cur_x ] = CHAR_WET;
        else if ( region_type == 2 ) grid_map[ SET_MAP ][ cur_y ][ cur_x ] = CHAR_NARROW;

        /*
         * The risk level of for a region: 0 for rocky regions, 1 for wet regions, and 2 for narrow regions.
         * (=equal to the type)
         */
        grid_map[ SET_RISK_LEVEL ][ cur_y ][ cur_x ] = region_type;
      }
    }

    grid_map[ SET_MAP ][ pTargetY ][ pTargetX ] = CHAR_TARGET;
    grid_map[ SET_MAP ][ 0        ][ 0        ] = CHAR_MOUTH;

    /*
     * *******************************************************************************************************
     * Calculating the risk level for the area to the target
     * *******************************************************************************************************
     */

    long result_part_01 = 0;

    for ( int cur_row = 0; cur_row <= pTargetY; cur_row++ )
    {
      for ( int cur_col = 0; cur_col <= pTargetX; cur_col++ )
      {
        result_part_01 += grid_map[ SET_RISK_LEVEL ][ cur_row ][ cur_col ];
      }
    }

    if ( pKnzDebug )
    {
      wl( "Generated Map" );
      wl( getDebugGridChar( grid_map, SET_MAP, 0, 0, grid_height, grid_width ) );
      wl( "" );
      wl( "Geologic Index" );
      wl( getDebugGridNumber( grid_map, SET_GEOLOGIC_INDEX, 0, 0, pTargetY + 1, pTargetX + 1 ) );
      wl( "" );
      wl( "Erosion Level" );
      wl( getDebugGridNumber( grid_map, SET_EROSION_LEVEL, 0, 0, pTargetY + 1, pTargetX + 1 ) );
      wl( "" );
      wl( "Risk Level" );
      wl( getDebugGridNumber( grid_map, SET_RISK_LEVEL, 0, 0, pTargetY + 1, pTargetX + 1 ) );
    }

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
