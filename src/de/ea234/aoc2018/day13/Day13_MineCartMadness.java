package de.ea234.aoc2018.day13;

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
 * --- Day 13: Mine Cart Madness ---
 * https://adventofcode.com/2018/day/13
 * 
 * https://www.reddit.com/r/adventofcode/comments/a5qd71/2018_day_13_solutions/
 * 
 * </pre> 
 */
public class Day13_MineCartMadness
{

//  Round 0
//                                      
//  /---\                  A            
//  |   |  /----\                       
//  | /-+--+-\  |                       
//  | | |  | |  |                       
//  \-+-/  \-+--/                       
//    \------/                          
//  
//  Round 1
//                                      
//  /---\                  AA           
//  |   |  /----\                       
//  | /-+--+-\  |                       
//  | | |  | |  |                       
//  \-+-/  \-+--/                       
//    \------/                          
//  
//  Round 2
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |                       
//  | | |  | |  |                       
//  \-+-/  \-+--/                       
//    \------/                          
//  
//  Round 3
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           A           
//  | | |  | |  |                       
//  \-+-/  \-+--/                       
//    \------/                          
//  
//  Round 4
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AA          
//  | | |  | |  |                       
//  \-+-/  \-+--/                       
//    \------/                          
//  
//  Round 5
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAA         
//  | | |  | |  |                       
//  \-+-/  \-+--/                       
//    \------/                          
//  
//  Round 6
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAAA        
//  | | |  | |  |                       
//  \-+-/  \-+--/                       
//    \------/                          
//  
//  Round 7
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAAAA       
//  | | |  | |  |                       
//  \-+-/  \-+--/                       
//    \------/                          
//  
//  Round 8
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAAAAA      
//  | | |  | |  |                       
//  \-+-/  \-+--/                       
//    \------/                          
//  
//  Round 9
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAAAAA      
//  | | |  | |  |                A      
//  \-+-/  \-+--/                       
//    \------/                          
//  
//  Round 10
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAAAAA      
//  | | |  | |  |                A      
//  \-+-/  \-+--/                A      
//    \------/                          
//  
//  Round 11
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAAAAA      
//  | | |  | |  |                A      
//  \-+-/  \-+--/                A      
//    \------/                   A      
//  
//  Round 12
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAAAAA      
//  | | |  | |  |                A      
//  \-+-/  \-+--/                A      
//    \------/                  AA      
//  
//  Round 13
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAAAAA      
//  | | |  | |  |                A      
//  \-+-/  \-+--/                A      
//    \------/                 AAA      
//  
//  Round 14
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAAAAA      
//  | | |  | |  |                A      
//  \-+-/  \-+--/                A      
//    \------/                AAAA      
//  
//  Round 15
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAAAAA      
//  | | |  | |  |                A      
//  \-+-/  \-+--/                A      
//    \------/               AAAAA      
//  
//  Round 16
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAAAAA      
//  | | |  | |  |                A      
//  \-+-/  \-+--/                A      
//    \------/              AAAAAA      
//  
//  Round 17
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAAAAA      
//  | | |  | |  |                A      
//  \-+-/  \-+--/                A      
//    \------/             AAAAAAA      
//  
//  Round 18
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAAAAA      
//  | | |  | |  |                A      
//  \-+-/  \-+--/                A      
//    \------/            AAAAAAAA      
//  Breakpoint
//  
//  Round 19
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAAAAA      
//  | | |  | |  |                A      
//  \-+-/  \-+--/         A      A      
//    \------/            AAAAAAAA      
//  
//  Round 20
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAAAAA      
//  | | |  | |  |                A      
//  \-+-/  \-+--/        AA      A      
//    \------/            AAAAAAAA      
//  
//  Round 21
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAAAAA      
//  | | |  | |  |                A      
//  \-+-/  \-+--/       AAA      A      
//    \------/            AAAAAAAA      
//  
//  Round 22
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |           AAAAAA      
//  | | |  | |  |       A        A      
//  \-+-/  \-+--/       AAA      A      
//    \------/            AAAAAAAA      
//  
//  Round 23
//                                      
//  /---\                  AA           
//  |   |  /----\           A           
//  | /-+--+-\  |       A   AAAAAA      
//  | | |  | |  |       A        A      
//  \-+-/  \-+--/       AAA      A      
//    \------/            AAAAAAAA      
//  
//  Round 24
//                                      
//  /---\                  AA           
//  |   |  /----\       A   A           
//  | /-+--+-\  |       A   AAAAAA      
//  | | |  | |  |       A        A      
//  \-+-/  \-+--/       AAA      A      
//    \------/            AAAAAAAA      
//  
//  Round 25
//                                      
//  /---\               A  AA           
//  |   |  /----\       A   A           
//  | /-+--+-\  |       A   AAAAAA      
//  | | |  | |  |       A        A      
//  \-+-/  \-+--/       AAA      A      
//    \------/            AAAAAAAA      
//  
//  Round 26
//                                      
//  /---\               AA AA           
//  |   |  /----\       A   A           
//  | /-+--+-\  |       A   AAAAAA      
//  | | |  | |  |       A        A      
//  \-+-/  \-+--/       AAA      A      
//    \------/            AAAAAAAA      
//  
//  Round 27
//                                      
//  /---\               AAAAA           
//  |   |  /----\       A   A           
//  | /-+--+-\  |       A   AAAAAA      
//  | | |  | |  |       A        A      
//  \-+-/  \-+--/       AAA      A      
//    \------/            AAAAAAAA      
//  
//  Round 28
//                                      
//  /---\               AAAAA           
//  |   |  /----\       A   A           
//  | /-+--+-\  |       A   AAAAAA      
//  | | |  | |  |       A        A      
//  \-+-/  \-+--/       AAA      A      
//    \------/            AAAAAAAA      
//  
//  Round 29
//                                      
//  /---\               AAAAA           
//  |   |  /----\       A   A           
//  | /-+--+-\  |       A   AAAAAA      
//  | | |  | |  |       A        A      
//  \-+-/  \-+--/       AAA      A      
//    \------/            AAAAAAAA      

  private static final char   CHAR_MINE_CART_RIGHT    = '>';

  private static final char   CHAR_MINE_CART_LEFT     = '<';

  private static final char   CHAR_MINE_CART_DOWN     = 'v';

  private static final char   CHAR_MINE_CART_UP       = '^';

  private static final char   CHAR_TRACK_HORIZONTAL   = '-';

  private static final char   CHAR_TRACK_VERTICAL     = '|';

  private static final char   CHAR_TRACK_CROSSING     = '+';

  private static final char   CHAR_EMPTY_MAP          = ' ';

  private static final char   CHAR_TRACK_TURN_CURVE_1 = '\\';

  private static final char   CHAR_TRACK_TURN_CURVE_2 = '/';

  private static final String STR_COMBINE_SPACER      = "    ";

  private static final int    SET_MAP_TRACK           = 0;

  private static final int    SET_MAP_CARTS           = 1;

  public static void main( String[] args )
  {
    String test_input = "";

    test_input += ",/->-\\           ";
    test_input += ",|   |  /----\\";
    test_input += ",| /-+--+-\\  |";
    test_input += ",| | |  | |  |";
    test_input += ",\\-+-/  \\-+--/";
    test_input += ",  \\------/";

    calculatePart01( test_input, true );

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

    long result_part_01 = 0;

    long result_part_02 = 0;

    int grid_height = pListInput.size();
    int grid_width = 0;

    for ( String input_str : pListInput )
    {
      grid_width = Math.max( grid_width, input_str.length() );
    }

    List< MineCart > l_ca = new ArrayList< MineCart >();

    int[][][] grid_map = new int[ 2 ][ grid_height ][ grid_width ];

    int cur_row = 0;

    for ( String input_str : pListInput )
    {
      if ( pKnzDebug )
      {
        wl( input_str );

        grid_width = Math.max( grid_width, input_str.length() );

        for ( int cur_idx = 0; cur_idx < input_str.length(); cur_idx++ )
        {
          char cur_char = input_str.charAt( cur_idx );

          if ( ( cur_char == CHAR_MINE_CART_LEFT ) || ( cur_char == CHAR_MINE_CART_RIGHT ) )
          {
            l_ca.add( new MineCart( l_ca.size(), cur_row, cur_idx, cur_char ) );

            cur_char = CHAR_TRACK_HORIZONTAL;
          }
          else if ( ( cur_char == CHAR_MINE_CART_UP ) || ( cur_char == CHAR_MINE_CART_DOWN ) )
          {
            l_ca.add( new MineCart( l_ca.size(), cur_row, cur_idx, cur_char ) );

            cur_char = CHAR_TRACK_VERTICAL;
          }

          grid_map[ SET_MAP_TRACK ][ cur_row ][ cur_idx ] = cur_char;
        }
      }

      cur_row++;
    }

    wl( "" );

    for ( int cur_round = 0; cur_round < 30; cur_round++ )
    {
      if ( cur_round == 19 )
      {
        wl( "Breakpoint" );
      }

      for ( MineCart cur_cart : l_ca )
      {
        cur_cart.doTick( grid_map );
      }

      String debug_map_track = getDebugGridChar( grid_map, SET_MAP_TRACK, 0, 0, grid_height, grid_width );

      String debug_map_carts = getDebugGridChar( grid_map, SET_MAP_CARTS, 0, 0, grid_height, grid_width );

      wl( "" );
      wl( "Round " + cur_round );
      wl( combineStrings( debug_map_track, debug_map_carts ) );
    }

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
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
    private static final int TURN_LEFT        = 1;

    private static final int STRAIGHT         = 2;

    private static final int TURN_RIGHT       = 3;

    public static final char FACING_UP        = '^';

    public static final char FACING_DOWN      = 'v';

    public static final char FACING_LEFT      = '<';

    public static final char FACING_RIGHT     = '>';

    private int              pos_turning      = 0;

    private char             pos_facing       = 0;

    private int              pos_row          = 0;

    private int              pos_col          = 0;

    private char             char_coordinates = ' ';

    private int              id               = 0;

    public MineCart( int pNr, int pRow, int pCol, char pFacing )
    {
      pos_row = pRow;

      pos_col = pCol;

      pos_facing = pFacing;

      pos_turning = TURN_LEFT;

      id = pNr;

      char_coordinates = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".charAt( pNr );

    }

    public char getCharX()
    {
      return char_coordinates;
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
       */
      if ( pos_turning == TURN_LEFT )
      {
        pos_turning = STRAIGHT;
      }
      else if ( pos_turning == STRAIGHT )
      {
        pos_turning = FACING_RIGHT;
      }
      else
      {
        pos_turning = TURN_LEFT;
      }
    }
    private void doTick( int[][][] pGrid )
    {
      /*
       * **********************************************************************
       * First: Handle the Facing
       * - Crossing
       * - Curves
       * 
       *            111
       *  0123456789012
       * 0/---\        
       * 1|   |  /----\
       * 2| /-+--+-\  |
       * 3| | |  X |  |
       * 4\-+-/  \-+--/
       * 5  \------/
      
       * **********************************************************************
       */

      /*
       * Check Crossing
       * If the cart is positioned on a crossing, get a new facing
       */
      if ( pGrid[ SET_MAP_TRACK ][ pos_row ][ pos_col ] == CHAR_TRACK_CROSSING )
      {
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

      int next_pos_row = pos_row;
      int next_pos_col = pos_col;

      if ( pos_facing == FACING_LEFT )
      {
        next_pos_col--;
      }
      else if ( pos_facing == FACING_RIGHT )
      {
        next_pos_col++;
      }
      else if ( pos_facing == FACING_UP )
      {
        next_pos_row--;
      }
      else if ( pos_facing == FACING_DOWN )
      {
        next_pos_row++;
      }

      pGrid[ SET_MAP_CARTS ][ next_pos_row ][ next_pos_col ] = char_coordinates;

      pos_row = next_pos_row;
      pos_col = next_pos_col;
    }

    private int getChar( int[][][] pGrid, int pSet, int pRow, int pCol )
    {
      try
      {
        return pGrid[ pSet ][ pRow ][ pCol ];
      }
      catch ( IndexOutOfBoundsException exp )
      {
      }

      return -1;
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