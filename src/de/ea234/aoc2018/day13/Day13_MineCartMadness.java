package de.ea234.aoc2018.day13;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 13: Mine Cart Madness ---
 * https://adventofcode.com/2018/day/13
 * 
 * https://www.reddit.com/r/adventofcode/comments/a5qd71/2018_day_13_solutions/
 * 
 * https://github.com/ea234/Advent_of_Code_2018/blob/main/src/de/ea234/aoc2018/day13/Day13_MineCartMadness.java
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * 
 * Tick 0
 * /---\                  A                   A            
 * |   |  /----\                                           
 * | /-+--+-\  |                                           
 * | | |  | |  |                                           
 * \-+-/  \-+--/                B                   B      
 *   |      |                                              
 *   |      |                                              
 *   \------/                                              
 * 

 * Tick 4
 * /---\                  AA                               
 * |   |  /----\           A                               
 * | /-+--+-\  |           AA                   A          
 * | | |  | |  |                   B                   B   
 * \-+-/  \-+--/                BBBB                       
 *   |      |                                              
 *   |      |                                              
 *   \------/                                              
 * 
 * Tick 9
 * /---\                  AA                               
 * |   |  /----\           A    BBBB                B      
 * | /-+--+-\  |           AAAAAA  B                       
 * | | |  | |  |                A  B                A      
 * \-+-/  \-+--/                BBBB                       
 *   |      |                                              
 *   |      |                                              
 *   \------/                                              
 * 
 *
 * Tick 12
 * /---\                  AA                               
 * |   |  /----\           A  BBBBBB                       
 * | /-+--+-\  |           AAABAA  B              B        
 * | | |  | |  |                A  B                       
 * \-+-/  \-+--/              AAABBB              A        
 *   |      |                                              
 *   |      |                                              
 *   \------/                                              
 * 
 * Tick 13
 * /---\                  AA                               
 * |   |  /----\           A  BBBBBB                       
 * | /-+--+-\  |           AAABAA  B                       
 * | | |  | |  |              A A  B              X        
 * \-+-/  \-+--/              AAABBB                       
 *   |      |                                              
 *   |      |                                              
 *   \------/                                              
 * 
 * Crash after 13 ticks
 * 
 * Result Part 1   7,3
 * Result Part 2   0
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * PART 2
 * ------------------------------------------------------------------------------------------
 * 
 * 
 * Tick 0
 * /---\                   B                     X               
 * |   |                                                         
 * | /-+-\                 C                     C               
 * | | | |                                                       
 * \-+-/ |                 F   G                 X   X           
 *   |   |                                                       
 *   \---/                 H   I                 H   I           
 * 
 * Tick 1
 * /---\                   B                                     
 * |   |                                                         
 * | /-+-\                 C                                     
 * | | | |                 C                     C               
 * \-+-/ |                 F   G                                 
 *   |   |                 H   I                 H   I           
 *   \---/                 H   I                                 
 * 
 * Tick 2
 * /---\                   B                                     
 * |   |                                                         
 * | /-+-\                 C                                     
 * | | | |                 C                                     
 * \-+-/ |                 H   I                 X   I           
 *   |   |                 H   I                                 
 *   \---/                 H   I                                 
 * 
 * Last mine cart running after 2 ticks
 * 
 * Result Part 1   2,4
 * Result Part 2   6,4
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * Last mine cart running after 18553 ticks
 * 
 * Crashed mine carts
 * ID  6  G  X  117  Y   62  IsCrashed   true
 * ID 13  N  X  117  Y   62  IsCrashed   true
 * ID  7  H  X   64  Y  107  IsCrashed   true
 * ID  0  A  X   64  Y  107  IsCrashed   true
 * ID 12  M  X  117  Y   53  IsCrashed   true
 * ID  8  I  X  117  Y   53  IsCrashed   true
 * ID  5  F  X  117  Y  149  IsCrashed   true
 * ID  9  J  X  117  Y  149  IsCrashed   true
 * ID  2  C  X   21  Y   53  IsCrashed   true
 * ID 16  Q  X   21  Y   53  IsCrashed   true
 * ID  1  B  X  118  Y   26  IsCrashed   true
 * ID 15  P  X  118  Y   26  IsCrashed   true
 * ID  3  D  X   11  Y  135  IsCrashed   true
 * ID 10  K  X   11  Y  135  IsCrashed   true
 * ID 11  L  X   63  Y   19  IsCrashed   true
 * ID 14  O  X   63  Y   19  IsCrashed   true
 * 
 * Remaining mine cart
 * ID  4  E  X  138  Y   89  IsCrashed  false
 * 
 * Result Part 1   63,19
 * Result Part 2   138,89
 * 
 * 
 * </pre> 
 */
public class Day13_MineCartMadness
{
  private static final String STR_COMBINE_SPACER      = "    ";

  private static final char   CHAR_TRACK_TURN_CURVE_2 = '/';

  private static final char   CHAR_MINE_CART_RIGHT    = '>';

  private static final char   CHAR_MINE_CART_LEFT     = '<';

  private static final char   CHAR_MINE_CART_DOWN     = 'v';

  private static final char   CHAR_MINE_CART_UP       = '^';

  private static final char   CHAR_TRACK_HORIZONTAL   = '-';

  private static final char   CHAR_CRASH              = 'X';

  private static final char   CHAR_TRACK_VERTICAL     = '|';

  private static final char   CHAR_TRACK_CROSSING     = '+';

  private static final char   CHAR_EMPTY_MAP          = ' ';

  private static final char   CHAR_TRACK_TURN_CURVE_1 = '\\';

  private static final int    SET_MAP_TRACK           = 0;

  private static final int    SET_MAP_CARTS           = 1;

  private static final int    SET_MAP_TRAIL           = 2;

  public static void main( String[] args )
  {
    String test_input = "";

    test_input += "/->-\\           ";
    test_input += ",|   |  /----\\";
    test_input += ",| /-+--+-\\  |";
    test_input += ",| | |  | v  |";
    test_input += ",\\-+-/  \\-+--/";
    test_input += ",  |      |";
    test_input += ",  |      |";
    test_input += ",  \\------/";

    calculatePart01( test_input, true );

    String j_str = "";

    j_str += "/>-<\\";
    j_str += ",|   |";
    j_str += ",| /<+-\\           ";
    j_str += ",| | | v";
    j_str += ",\\>+</ |";
    j_str += ",  |   ^";
    j_str += ",  \\<->/";

    calculatePart01( j_str, true );

    calculate01( getListProd(), false );

    System.exit( 0 );
  }

  private static void calculatePart01( String pString, boolean pKnzDebug )
  {
    List< String > converted_string_list = Arrays.stream( pString.split( "," ) ).collect( Collectors.toList() );

    calculate01( converted_string_list, pKnzDebug );
  }

  private static void calculate01( List< String > pListInput, boolean pKnzDebug )
  {
    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );

    /*
     * *******************************************************************************************************
     * Determine the grid size
     * *******************************************************************************************************
     */

    int grid_height = pListInput.size();
    int grid_width = 0;

    for ( String input_str : pListInput )
    {
      grid_width = Math.max( grid_width, input_str.length() );
    }

    int[][][] grid_map = new int[ 3 ][ grid_height ][ grid_width ];

    /*
     * *******************************************************************************************************
     * Getting the map from the input list and building the list of mine carts
     * *******************************************************************************************************
     */

    List< MineCart > mine_cart_list_active = new ArrayList< MineCart >();

    List< MineCart > mine_cart_list_crashed = new ArrayList< MineCart >();

    int cur_row = 0;

    for ( String input_str : pListInput )
    {
      for ( int cur_idx = 0; cur_idx < input_str.length(); cur_idx++ )
      {
        char cur_char = input_str.charAt( cur_idx );

        if ( ( cur_char == CHAR_MINE_CART_LEFT ) || ( cur_char == CHAR_MINE_CART_RIGHT ) )
        {
          mine_cart_list_active.add( new MineCart( mine_cart_list_active.size(), cur_row, cur_idx, cur_char ) );

          cur_char = CHAR_TRACK_HORIZONTAL;
        }
        else if ( ( cur_char == CHAR_MINE_CART_UP ) || ( cur_char == CHAR_MINE_CART_DOWN ) )
        {
          mine_cart_list_active.add( new MineCart( mine_cart_list_active.size(), cur_row, cur_idx, cur_char ) );

          cur_char = CHAR_TRACK_VERTICAL;
        }

        grid_map[ SET_MAP_TRACK ][ cur_row ][ cur_idx ] = cur_char;
      }

      cur_row++;
    }

    wl( "" );

    /*
     * *******************************************************************************************************
     * Doing the ticks and crash detection
     * *******************************************************************************************************
     */

    int knz_crash_detected = 0;

    MineCart first_crash_vehicle = null;

    for ( int cur_tick = 0; cur_tick < 2000000; cur_tick++ )
    {
      /*
       * Do the current tick for all mine carts
       */
      for ( MineCart cur_mine_cart : mine_cart_list_active )
      {
        knz_crash_detected = cur_mine_cart.doTick( grid_map );

        if ( ( first_crash_vehicle == null ) && ( knz_crash_detected == 1 ) )
        {
          first_crash_vehicle = cur_mine_cart;
        }
      }

      /*
       * Check all mine carts for crash
       */
      for ( MineCart cur_mine_cart_a : mine_cart_list_active )
      {
        if ( cur_mine_cart_a.isNotCrashed() )
        {
          for ( MineCart cur_mine_cart_b : mine_cart_list_active )
          {
            cur_mine_cart_a.setKnzIsCrashed( cur_mine_cart_b );
          }
        }
      }

      if ( pKnzDebug )
      {
        String debug_map_track = getDebugGridChar( grid_map, SET_MAP_TRACK, 0, 0, grid_height, grid_width );

        String debug_map_trail = getDebugGridChar( grid_map, SET_MAP_TRAIL, 0, 0, grid_height, grid_width );

        String debug_map_carts = getDebugGridChar( grid_map, SET_MAP_CARTS, 0, 0, grid_height, grid_width );

        wl( "" );
        wl( "Tick " + cur_tick );
        wl( combineStrings( combineStrings( debug_map_track, debug_map_trail ), debug_map_carts ) );
      }

      /*
       * Remove crashed mine carts from the active list
       */
      int idx = 0;

      while ( idx < mine_cart_list_active.size() )
      {
        MineCart cur_mine_cart_a = mine_cart_list_active.get( idx );

        if ( cur_mine_cart_a.isCrashed() )
        {
          /*
           * Clear the spot on the carts map, so other vehicles don't crash into the same spot.
           */
          cur_mine_cart_a.clearMap( grid_map );

          /*
           * Remove the mine cart from the active list of mine carts.
           */
          mine_cart_list_active.remove( cur_mine_cart_a );

          /*
           * Add the mine cart to the crash list
           */
          mine_cart_list_crashed.add( cur_mine_cart_a );
        }
        else
        {
          idx++;
        }
      }

      mine_cart_list_active.sort( Comparator.comparingInt( MineCart::getSortValue ) );

      if ( mine_cart_list_active.size() < 2 )
      {
        wl( "" );
        wl( "Last mine cart running after " + cur_tick + " ticks" );

        break;
      }
    }

    wl( "" );
    wl( "Crashed mine carts" );

    for ( MineCart cur_mine_cart_a : mine_cart_list_crashed )
    {
      wl( cur_mine_cart_a.toString() );
    }

    if ( mine_cart_list_active.size() == 1 )
    {
      wl( "" );
      wl( "Remaining mine cart" );
      wl( mine_cart_list_active.get( 0 ).toString() );
      wl( "" );
    }

    wl( "" );
    wl( "Result Part 1   " + ( first_crash_vehicle == null ? "-----" : first_crash_vehicle.getCurPos() ) );
    wl( "Result Part 2   " + ( mine_cart_list_active.size() == 0 ? "-----" : mine_cart_list_active.get( 0 ).getCurPos() ) );
    wl( "" );
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
          dbg_string.append( CHAR_EMPTY_MAP );
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

  private static class MineCart
  {
    private static final int TURN_LEFT     = 1;

    private static final int STRAIGHT      = 2;

    private static final int TURN_RIGHT    = 3;

    public static final char FACING_UP     = '^';

    public static final char FACING_DOWN   = 'v';

    public static final char FACING_LEFT   = '<';

    public static final char FACING_RIGHT  = '>';

    private int              pos_turning   = 0;

    private char             pos_facing    = 0;

    private int              pos_row       = 0;

    private int              pos_col       = 0;

    private char             cart_map_char = ' ';

    private int              id            = 0;

    public MineCart( int pNr, int pRow, int pCol, char pFacing )
    {
      pos_row = pRow;

      pos_col = pCol;

      pos_facing = pFacing;

      pos_turning = TURN_LEFT;

      id = pNr;

      cart_map_char = "ABCDEFGHIJKLMNOPQRSTUVWYZabcdefghijklmnopqrstuvwyz".charAt( pNr );
    }

    public int getSortValue()
    {
      return ( pos_row * 1000 ) + pos_col;
    }

    public int getID()
    {
      return id;
    }

    private boolean is_crashed = false;

    public boolean setKnzIsCrashed( MineCart pMineCartB )
    {
      if ( pMineCartB.getID() != this.getID() )
      {
        if ( pMineCartB.getSortValue() == this.getSortValue() )
        {
          is_crashed = true;
        }
      }

      return is_crashed;
    }

    public boolean isCrashed()
    {
      return is_crashed;
    }

    public boolean isNotCrashed()
    {
      return is_crashed == false;
    }

    private void getNewFacing()
    {
      /*
       * Each time a cart has the option to turn, it turns
       * 
       *   - left the first time, 
       *   - straight the second time
       *   - right the third time
       *   
       * and then repeats those directions
       * 
       * 
       * If the turning mode is STRAIGHT, the facing doesn't change.
       */
      if ( pos_facing == FACING_UP )
      {
             if ( pos_turning == TURN_LEFT  ) pos_facing = FACING_LEFT;
        else if ( pos_turning == TURN_RIGHT ) pos_facing = FACING_RIGHT;
      }
      else if ( pos_facing == FACING_DOWN )
      {
             if ( pos_turning == TURN_LEFT  ) pos_facing = FACING_RIGHT;
        else if ( pos_turning == TURN_RIGHT ) pos_facing = FACING_LEFT;
      }
      else if ( pos_facing == FACING_LEFT )
      {
             if ( pos_turning == TURN_LEFT  ) pos_facing = FACING_DOWN;
        else if ( pos_turning == TURN_RIGHT ) pos_facing = FACING_UP;
      }
      else if ( pos_facing == FACING_RIGHT )
      {
             if ( pos_turning == TURN_LEFT  ) pos_facing = FACING_UP;
        else if ( pos_turning == TURN_RIGHT ) pos_facing = FACING_DOWN;
      }

      /*
       * Set the turning mode, for the next crossing
       * 
       * LEFT    -> STRAIGHT
       * STRAIGT -> RIGHT
       * RIGHT   -> LEFT
       */
      if ( pos_turning == TURN_LEFT )
      {
        pos_turning = STRAIGHT;
      }
      else if ( pos_turning == STRAIGHT )
      {
        pos_turning = TURN_RIGHT;
      }
      else
      {
        pos_turning = TURN_LEFT;
      }
    }

    private int doTick( int[][][] pGrid )
    {
      /*
       * **********************************************************************
       * First: Handle the Facing
       * - Crossing
       * - Curves
       * **********************************************************************
       */

      if ( pGrid[ SET_MAP_TRACK ][ pos_row ][ pos_col ] == CHAR_TRACK_CROSSING )
      {
        /*
         * If the cart is on a crossing, get a new facing
         */
        getNewFacing();
      }
      else if ( pGrid[ SET_MAP_TRACK ][ pos_row ][ pos_col ] == CHAR_TRACK_TURN_CURVE_1 ) // '\'
      {
        if ( pos_facing == FACING_LEFT )
        {
          /*
           * If the current is "left", then the next direction is up
           * 
           * |
           * \--<
           */
          pos_facing = FACING_UP;
        }
        else if ( pos_facing == FACING_RIGHT )
        {
          /*
           * If the current is "right", then the next direction is down
           * 
           * >---\        
           *     |  
           */
          pos_facing = FACING_DOWN;
        }
        else if ( pos_facing == FACING_UP )
        {
          /*
           * If the current is "right", then the next direction is down
           * 
           *  ---\        
           *     |  
           *     ^
           */
          pos_facing = FACING_LEFT;
        }
        else if ( pos_facing == FACING_DOWN )
        {
          /*
           * If the current is "right", then the next direction is down
           *
           * V
           * |
           * \--
           */
          pos_facing = FACING_RIGHT;
        }
      }
      else if ( pGrid[ SET_MAP_TRACK ][ pos_row ][ pos_col ] == CHAR_TRACK_TURN_CURVE_2 ) // '/'
      {
        if ( pos_facing == FACING_LEFT )
        {
          /*
           * If the current is "left", then the next direction is down
           * 
           * /--<
           * |
           * |
           */
          pos_facing = FACING_DOWN;
        }
        else if ( pos_facing == FACING_RIGHT )
        {
          /*
           * If the current is "right", then the next direction is up
           *
           *    |
           * >--/
           */
          pos_facing = FACING_UP;
        }
        else if ( pos_facing == FACING_UP )
        {
          /*
           * If the current is "right", then the next direction is down
           * 
           *  /---        
           *  |  
           *  ^
           */
          pos_facing = FACING_RIGHT;
        }
        else if ( pos_facing == FACING_DOWN )
        {
          /*
           * If the current is "right", then the next direction is down
           * 
           *     V
           *     |
           *  ---/        
           */
          pos_facing = FACING_LEFT;
        }
      }

      /*
       * Clear the position on old coordinates
       */
      pGrid[ SET_MAP_CARTS ][ pos_row ][ pos_col ] = 0;

      /*
       * Get the new position from the facing
       */
      if ( pos_facing == FACING_LEFT )
      {
        pos_col--;
      }
      else if ( pos_facing == FACING_RIGHT )
      {
        pos_col++;
      }
      else if ( pos_facing == FACING_UP )
      {
        pos_row--;
      }
      else if ( pos_facing == FACING_DOWN )
      {
        pos_row++;
      }

      int knz_crash_detected = 0;

      if ( pGrid[ SET_MAP_CARTS ][ pos_row ][ pos_col ] > 0 )
      {
        /*
         * If the new coordinations are occupied, a crash is detected.
         * Set the crash-detection-variable to 1.
         */
        pGrid[ SET_MAP_CARTS ][ pos_row ][ pos_col ] = CHAR_CRASH;

        knz_crash_detected = 1;
      }
      else
      {
        /*
         * If the new coordinates are free, set the mine cart char.
         */
        pGrid[ SET_MAP_CARTS ][ pos_row ][ pos_col ] = cart_map_char;
      }

      pGrid[ SET_MAP_TRAIL ][ pos_row ][ pos_col ] = cart_map_char;

      return knz_crash_detected;
    }

    private void clearMap( int[][][] pGrid )
    {
      pGrid[ SET_MAP_CARTS ][ pos_row ][ pos_col ] = 0;
    }

    public String getCurPos()
    {
      return pos_col + "," + pos_row;
    }

    public String toString()
    {
      return String.format( "ID %2d  %s  X %4d  Y %4d  IsCrashed %6b", id, cart_map_char, pos_col, pos_row, is_crashed );
    }
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

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2018__day13_input.txt";

    datei_input = "C:/Daten/00_Daten/advent_of_code_2018__day13_input.txt";

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