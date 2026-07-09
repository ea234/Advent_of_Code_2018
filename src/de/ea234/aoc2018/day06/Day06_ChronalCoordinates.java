package de.ea234.aoc2018.day06;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import de.system.FkLogger;

/**
 * <pre>
 * 
 * --- Day 6: Chronal Coordinates ---
 * https://adventofcode.com/2018/day/6
 * 
 * https://www.reddit.com/r/adventofcode/comments/a3kr4r/2018_day_6_solutions/
 * 
 * https://github.com/ea234/Advent_of_Code_2018/blob/main/src/de/ea234/aoc2018/day06/Day06_ChronalCoordinates.java
 * 
 * ..........    AAAAA.CCCC    AAAAA.CCCC     0     2    1    2    3    4    0    5    4    3    4
 * .A........    A-AAA.CCCC    A........C     1     1    0    1    2    3    0    4    3    2    3
 * ..........    AAADDECCCC    A........C     2     2    1    2    2    3    3    3    2    1    2
 * ........C.    AADDDECC-C    A........C     3     3    2    2    1    2    2    2    1    0    1
 * ...D......    ..D-DEECCC    .........C     4     0    0    1    0    1    1    2    2    1    2
 * .....E....    BB.DE-EECC    B........C     5     2    1    0    1    1    0    1    2    2    3
 * .B........    B-B.EEEE..    B.........     6     1    0    1    0    2    1    2    3    0    0
 * ..........    BBB.EEEFFF    B........F     7     2    1    2    0    3    2    3    3    2    3
 * ..........    BBB.EEFFFF    B........F     8     3    2    3    0    4    3    3    2    1    2
 * ........F.    BBB.FFFF-F    BBB.FFFF-F     9     4    3    4    0    4    3    2    1    0    1
 * 
 * 
 * ..........     0    54   48   46   44   44   44   46   48   50   56
 * ..........     1    48   42   40   38   38   38   40   42   44   50
 * ..........     2    44   38   36   34   34   34   36   38   40   46
 * ...###....     3    40   34   32   30   30   30   32   34   36   42
 * ..#####...     4    38   32   30   28   28   28   30   32   34   40
 * ..#####...     5    38   32   30   28   28   28   30   32   34   40
 * ...###....     6    40   34   32   30   30   30   32   34   36   42
 * ..........     7    44   38   36   34   34   34   36   38   40   46
 * ..........     8    48   42   40   38   38   38   40   42   44   50
 * ..........     9    52   46   44   42   42   42   44   46   48   54
 * 
 * 
 * List of coordinates
 * ID  0  A   X   1  Y   1    Count     15
 * ID  1  B   X   1  Y   6    Count     14
 * ID  2  C   X   8  Y   3    Count     21
 * ID  3  D   X   3  Y   4    Count      9
 * ID  4  E   X   5  Y   5    Count     17
 * ID  5  F   X   8  Y   9    Count     13
 * 
 * Result Part 1  17  Char E
 * Result Part 2  16
 * 
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * 
 * List of coordinates
 * ID  0  A   X  66  Y 204    Count   1891
 * ID  1  B   X  55  Y 226    Count    258
 * ID  2  C   X 231  Y 196    Count   1709
 * ID  3  D   X  69  Y 211    Count    457
 * ID  4  E   X  69  Y 335    Count   1959
 * ID  5  F   X 133  Y 146    Count   2523
 * ID  6  G   X 321  Y 136    Count    938
 * ID  7  H   X 220  Y 229    Count   1435
 * ID  8  I   X 148  Y 138    Count   3647
 * ID  9  J   X  42  Y 319    Count   6577
 * ID 10  K   X 304  Y 181    Count   1585
 * ID 11  L   X 101  Y 329    Count   4234
 * ID 12  M   X  72  Y 244    Count   2394
 * ID 13  N   X 242  Y 117    Count   2555
 * ID 14  O   X  83  Y 237    Count   1680
 * ID 15  P   X 169  Y 225    Count   2752
 * ID 16  Q   X 311  Y 212    Count   1267
 * ID 17  R   X 348  Y 330    Count   8923
 * ID 18  S   X 233  Y 268    Count   2255
 * ID 19  T   X  99  Y 301    Count   1684
 * ID 20  U   X 142  Y 293    Count   3305
 * ID 21  V   X 239  Y 288    Count   2962
 * ID 22  W   X 200  Y 216    Count   1596
 * ID 23  X   X  44  Y 215    Count   3066
 * ID 24  Y   X 353  Y 289    Count   3341
 * ID 25  Z   X  54  Y  73    Count   7750
 * ID 26  a   X  73  Y 317    Count    912
 * ID 27  b   X  55  Y 216    Count    174
 * ID 28  c   X 305  Y 134    Count   1734
 * ID 29  d   X 343  Y 233    Count   1920
 * ID 30  e   X 227  Y  75    Count   2693
 * ID 31  f   X 139  Y 285    Count   1998
 * ID 32  g   X 264  Y 179    Count   1878
 * ID 33  h   X 349  Y 263    Count   2543
 * ID 34  i   X  48  Y 116    Count   4373
 * ID 35  j   X 223  Y  60    Count   6775
 * ID 36  k   X 247  Y 148    Count   1369
 * ID 37  l   X 320  Y 232    Count   2027
 * ID 38  m   X  60  Y 230    Count    246
 * ID 39  n   X 292  Y  78    Count  12807
 * ID 40  o   X 247  Y 342    Count   8897
 * ID 41  p   X  59  Y 326    Count   1315
 * ID 42  q   X 333  Y 210    Count   2762
 * ID 43  r   X 186  Y 291    Count   4718
 * ID 44  s   X 218  Y 146    Count   2539
 * ID 45  t   X 205  Y 246    Count   1271
 * ID 46  u   X 124  Y 204    Count   3099
 * ID 47  v   X  76  Y 121    Count   3020
 * ID 48  w   X 333  Y 137    Count   6193
 * ID 49  x   X 117  Y  68    Count   8016
 * 
 * Result Part 1  3647  Char I
 * Result Part 2  41605
 *  *
 * </pre> 
 */
public class Day06_ChronalCoordinates
{
  private static final char   CHAR_SPOT_SUM                   = '#';

  private static final char   CHAR_NO_VALUE                   = ' ';

  private static final char   CHAR_ORIGIN_COORDINATE          = '-';

  private static final char   CHAR_SPACE                      = '.';

  private static final int    SET_COORDINATES                 = 0;

  private static final int    SET_OUTPUT_CHAR                 = 1;

  private static final int    SET_OUTPUT_INFI_CHARS           = 2;

  private static final int    SET_OUTPUT_DISTANCE             = 3;

  private static final int    SET_OUTPUT_PART_2__SUM_MD_CHAR  = 4;

  private static final int    SET_OUTPUT_PART_2__SUM_MD_VALUE = 5;

  private static final String STR_COMBINE_SPACER              = "    ";

  public static void main( String[] args )
  {
    String test_input = "";

    test_input += "1, 1";
    test_input += ";1, 6";
    test_input += ";8, 3";
    test_input += ";3, 4";
    test_input += ";5, 5";
    test_input += ";8, 9";

    calculatePart01( test_input, 10, 10, 32, true );

    calculate01( getListProd(), 400, 400, 10_000, false );
  }

  private static void calculatePart01( String pString, int pGridHeight, int pGridWidth, int pManhattenDistanceP2, boolean pKnzDebug )
  {
    List< String > converted_string_list = Arrays.stream( pString.split( ";" ) ).collect( Collectors.toList() );

    calculate01( converted_string_list, pGridHeight, pGridWidth, pManhattenDistanceP2, pKnzDebug );
  }

  private static void calculate01( List< String > pListInput, int pGridHeight, int pGridWidth, int pManhattenDistanceP2, boolean pKnzDebug )
  {
    int grid_height = pGridHeight;

    int grid_width = pGridWidth;

    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );
    wl( "" );

    /*
     * *******************************************************************************************************
     * Creating the list of stars from the input
     * *******************************************************************************************************
     */

    List< Coordinate > coordinates_list = new ArrayList< Coordinate >();

    for ( int cur_idx = 0; cur_idx < pListInput.size(); cur_idx++ )
    {
      String input_str = pListInput.get( cur_idx );

      if ( !input_str.isBlank() )
      {
        coordinates_list.add( new Coordinate( cur_idx, input_str ) );
      }
    }

    /*
     * *******************************************************************************************************
     * Creating Grid
     * *******************************************************************************************************
     */

    int[][][] grid_map = new int[ 6 ][ pGridHeight ][ pGridWidth ];

    for ( Coordinate coordinate_inst : coordinates_list )
    {
      grid_map[ SET_COORDINATES ][ coordinate_inst.getPosY() ][ coordinate_inst.getPosX() ] = coordinate_inst.getCharX();
    }

    for ( int cur_y = 0; cur_y < grid_height; cur_y++ )
    {
      for ( int cur_x = 0; cur_x < grid_width; cur_x++ )
      {
        int manhatten_distance_min_value = Integer.MAX_VALUE;

        char grid_coordinates_char = CHAR_NO_VALUE;

        /*
         * For part 2, calculate the sum of all manhatten distances to the current position 
         */
        int manhatten_distance_sum = 0;

        /*
         * 1. Calculate the min manhatten distance
         */
        for ( Coordinate coordinate_inst : coordinates_list )
        {
          int manhatten_distance = coordinate_inst.calcManhattenDistance( cur_x, cur_y );

          manhatten_distance_sum += manhatten_distance;

          if ( manhatten_distance < manhatten_distance_min_value )
          {
            manhatten_distance_min_value = manhatten_distance;

            grid_coordinates_char = coordinate_inst.getCharX();
          }
        }

        /*
         * Part 2
         */
        grid_map[ SET_OUTPUT_PART_2__SUM_MD_VALUE ][ cur_y ][ cur_x ] = manhatten_distance_sum;

        if ( manhatten_distance_sum < pManhattenDistanceP2 )
        {
          grid_map[ SET_OUTPUT_PART_2__SUM_MD_CHAR ][ cur_y ][ cur_x ] = CHAR_SPOT_SUM;
        }

        /*
         * 2. Checkrun, wether the same min distance is found another time
         */
        for ( Coordinate coordinate_inst : coordinates_list )
        {
          if ( grid_coordinates_char != coordinate_inst.getCharX() )
          {
            int manhatten_distance = coordinate_inst.calcManhattenDistance( cur_x, cur_y );

            if ( manhatten_distance == manhatten_distance_min_value )
            {
              grid_coordinates_char = CHAR_SPACE;
            }
          }
        }

        /*
         * Char Output Grid
         * If the min manhatten distance is 0, than its a coordinate point.
         * This will be represented as a '-' char.
         * 
         * Otherwise, the char 
         */
        grid_map[ SET_OUTPUT_CHAR ][ cur_y ][ cur_x ] = manhatten_distance_min_value == 0 ? CHAR_ORIGIN_COORDINATE : grid_coordinates_char;

        /*
         * Distance Output Grid
         */
        grid_map[ SET_OUTPUT_DISTANCE ][ cur_y ][ cur_x ] = grid_coordinates_char == CHAR_SPACE ? 0 : manhatten_distance_min_value;
      }
    }

    /*
     * *******************************************************************************************************
     * Determine the infinity coordinates
     * *******************************************************************************************************
     */

    /*
     * Infinity Characters are found at the boundaries of the grid.
     * Put all characters as key from the boundaries in a Proper
     */
    Properties infinity_char = new Properties();

    for ( int cur_y = 0; cur_y < grid_height; cur_y++ )
    {
      infinity_char.setProperty( "" + ( (char) grid_map[ SET_OUTPUT_CHAR ][ cur_y ][ 0              ] ), "x" );
      infinity_char.setProperty( "" + ( (char) grid_map[ SET_OUTPUT_CHAR ][ cur_y ][ grid_width - 1 ] ), "x" );

      grid_map[ SET_OUTPUT_INFI_CHARS ][ cur_y ][ 0              ] = grid_map[ SET_OUTPUT_CHAR ][ cur_y ][ 0              ];
      grid_map[ SET_OUTPUT_INFI_CHARS ][ cur_y ][ grid_width - 1 ] = grid_map[ SET_OUTPUT_CHAR ][ cur_y ][ grid_width - 1 ];
    }

    for ( int cur_x = 0; cur_x < grid_width; cur_x++ )
    {
      infinity_char.setProperty( "" + ( (char) grid_map[ SET_OUTPUT_CHAR ][ 0               ][ cur_x ] ), "x" );
      infinity_char.setProperty( "" + ( (char) grid_map[ SET_OUTPUT_CHAR ][ grid_height - 1 ][ cur_x ] ), "x" );

      grid_map[ SET_OUTPUT_INFI_CHARS ][ 0               ][ cur_x ] = grid_map[ SET_OUTPUT_CHAR ][ 0               ][ cur_x ];
      grid_map[ SET_OUTPUT_INFI_CHARS ][ grid_height - 1 ][ cur_x ] = grid_map[ SET_OUTPUT_CHAR ][ grid_height - 1 ][ cur_x ];
    }

    /*
     * *******************************************************************************************************
     * Counting the chars in the output grid.
     * *******************************************************************************************************
     */

    int result_part_01 = 0;

    char result_char = CHAR_SPOT_SUM;

    for ( Coordinate coordinate_inst : coordinates_list )
    {
      /*
       * Count the number of chars in the output char grid
       * Add one for the input coordinate itself. 
       */
      int count_nr = countGridChar( grid_map, SET_OUTPUT_CHAR, 0, 0, grid_height, grid_width, coordinate_inst.getCharX() ) + 1;

      /*
       * Set the count result in the coordinate instance.
       */
      coordinate_inst.setCountNr( count_nr );

      /*
       * Check if the char is not in the infinity props
       */
      if ( infinity_char.getProperty( "" + coordinate_inst.getCharX() ) == null )
      {
        /*
         * If the count number is greater than the current max value, 
         * and the char is not infinity, then this is the new best value.
         */
        if ( count_nr > result_part_01 )
        {
          result_part_01 = count_nr;

          result_char = coordinate_inst.getCharX();
        }
      }
    }

    /*
     * *******************************************************************************************************
     * Do some debug stuff
     * *******************************************************************************************************
     */

    if ( pKnzDebug )
    {
      String debug_map_coordinates        = getDebugGridChar( grid_map, SET_COORDINATES,       0, 0, grid_height, grid_width );

      String debug_map_output_char        = getDebugGridChar( grid_map, SET_OUTPUT_CHAR,       0, 0, grid_height, grid_width );

      String debug_map_output_ichar       = getDebugGridChar( grid_map, SET_OUTPUT_INFI_CHARS, 0, 0, grid_height, grid_width );

      String debug_map_output_sum_md_char = getDebugGridChar( grid_map, SET_OUTPUT_PART_2__SUM_MD_CHAR, 0, 0, grid_height, grid_width );

      String debug_map_output_distance    = getDebugGridNumber( grid_map, SET_OUTPUT_DISTANCE,             0, 0, grid_height, grid_width );

      String debug_map_output_sum_md      = getDebugGridNumber( grid_map, SET_OUTPUT_PART_2__SUM_MD_VALUE, 0, 0, grid_height, grid_width );

      wl( "" );
      wl( combineStrings( combineStrings( combineStrings( debug_map_coordinates, debug_map_output_char ), debug_map_output_ichar ), debug_map_output_distance ) );
      wl( "" );
      wl( "" );
      wl( combineStrings( debug_map_output_sum_md_char, debug_map_output_sum_md ) );
      wl( "" );
    }

    int result_part_02 = countGridChar( grid_map, SET_OUTPUT_PART_2__SUM_MD_CHAR, 0, 0, grid_height, grid_width, CHAR_SPOT_SUM );

    wl( "" );
    wl( "List of coordinates" );

    for ( Coordinate coordinate_inst : coordinates_list )
    {
      wl( coordinate_inst.toString() );
    }

    wl( "" );
    wl( "Result Part 1  " + result_part_01 + "  Char " + result_char );
    wl( "Result Part 2  " + result_part_02 );
    wl( "" );
    wl( "" );
  }

  private static int countGridChar( int[][][] pGrid, int pSet, int pRowStart, int pColStart, int pRowEnd, int pColEnd, char pChar )
  {
    int result_nr = 0;

    for ( int cur_row = pRowStart; cur_row < pRowEnd; cur_row++ )
    {
      for ( int cur_col = pColStart; cur_col < pColEnd; cur_col++ )
      {
        if ( pGrid[ pSet ][ cur_row ][ cur_col ] == pChar )
        {
          result_nr++;
        }
      }
    }

    return result_nr;
  }

  private static String getDebugGridChar( int[][][] pGrid, int pSet, int pRowStart, int pColStart, int pRowEnd, int pColEnd )
  {
    StringBuilder dbg_string = new StringBuilder();

    for ( int cur_row = pRowStart; cur_row < pRowEnd; cur_row++ )
    {
      for ( int cur_col = pColStart; cur_col < pColEnd; cur_col++ )
      {
        if ( pGrid[ pSet ][ cur_row ][ cur_col ] == 0 )
        {
          dbg_string.append( CHAR_SPACE );
        }
        else
        {
          dbg_string.append( (char) pGrid[ pSet ][ cur_row ][ cur_col ] );
        }
      }

      dbg_string.append( "\n" );
    }

    return dbg_string.toString();
  }

  private static String getDebugGridNumber( int[][][] pGrid, int pSet, int pRowStart, int pColStart, int pRowEnd, int pColEnd )
  {
    StringBuilder dbg_string = new StringBuilder();

    for ( int cur_row = pRowStart; cur_row < pRowEnd; cur_row++ )
    {
      dbg_string.append( " " + cur_row + " " );

      for ( int cur_col = pColStart; cur_col < pColEnd; cur_col++ )
      {
        dbg_string.append( String.format( " %4d", pGrid[ pSet ][ cur_row ][ cur_col ] ) );
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

  private static class Coordinate
  {
    private char char_coordinates = ' ';

    private int  id               = 0;

    private int  pos_x            = 0;

    private int  pos_y            = 0;

    private int  count_nr         = 0;

    public Coordinate( int pNr, String pInput )
    {
      id = pNr;

      char_coordinates = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".charAt( pNr );

      String[] value_vector = pInput.replaceAll( " ", "" ).split( "," );

      pos_x = Integer.parseInt( value_vector[ 0 ] );

      pos_y = Integer.parseInt( value_vector[ 1 ] );
    }

    public int getPosX()
    {
      return pos_x;
    }

    public int getPosY()
    {
      return pos_y;
    }

    public char getCharX()
    {
      return char_coordinates;
    }

    public void setCountNr( int pCountNr )
    {
      count_nr = pCountNr;
    }

    public int calcManhattenDistance( int pPointX, int pPointY )
    {
      return calcDistance( pos_x, pPointX ) + calcDistance( pos_y, pPointY );
    }

    public String toString()
    {
      return String.format( "ID %2d  %s   X %3d  Y %3d    Count %6d", id, char_coordinates, pos_x, pos_y, count_nr );
    }
  }

  private static int calcDistance( int pPosA, int pPosB )
  {
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

    String datei_input = "C:/Daten/00_Daten/advent_of_code_2018__day06_input.txt";
    //String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2018__day06_inputb.txt";

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