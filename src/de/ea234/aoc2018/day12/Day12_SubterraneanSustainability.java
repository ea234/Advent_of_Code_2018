package de.ea234.aoc2018.day12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 12: Subterranean Sustainability ---
 * https://adventofcode.com/2018/day/12
 * 
 * https://www.reddit.com/r/adventofcode/comments/a5eztl/2018_day_12_solutions/
 * 
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * ...## => #
 * ..#.. => #
 * .#... => #
 * .#.#. => #
 * .#.## => #
 * .##.. => #
 * .#### => #
 * #.#.# => #
 * #.### => #
 * ##.#. => #
 * ##.## => #
 * ###.. => #
 * ###.# => #
 * ####. => #
 * 
 * cur_state ....#..#.#..##......###...###....
 * 
 * Nr    1  Plant New     7  Existing     7  Result   119    ......#...#....#.....#..#..#..#.........
 * Nr    2  Plant New    11  Existing    18  Result   176    ......##..##...##....#..#..#..##........
 * Nr    3  Plant New     9  Existing    27  Result   138    .....#.#...#..#.#....#..#..#...#........
 * Nr    4  Plant New    11  Existing    38  Result   198    ......#.#..#...#.#...#..#..##..##.......
 * Nr    5  Plant New     9  Existing    47  Result   151    .......#...##...#.#..#..#...#...#.......
 * Nr    6  Plant New    12  Existing    59  Result   222    .......##.#.#....#...#..##..##..##......
 * Nr    7  Plant New    11  Existing    70  Result   170    ......#..###.#...##..#...#...#...#......
 * Nr    8  Plant New    14  Existing    84  Result   269    ......#....##.#.#.#..##..##..##..##.....
 * Nr    9  Plant New    12  Existing    96  Result   186    ......##..#..#####....#...#...#...#.....
 * Nr   10  Plant New    14  Existing   110  Result   269    .....#.#..#...#.##....##..##..##..##....
 * Nr   11  Plant New    10  Existing   120  Result   176    ......#...##...#.#...#.#...#...#...#....
 * Nr   12  Plant New    14  Existing   134  Result   274    ......##.#.#....#.#...#.#..##..##..##...
 * Nr   13  Plant New    11  Existing   145  Result   177    .....#..###.#....#.#...#....#...#...#...
 * Nr   14  Plant New    14  Existing   159  Result   291    .....#....##.#....#.#..##...##..##..##..
 * Nr   15  Plant New    11  Existing   170  Result   193    .....##..#..#.#....#....#..#.#...#...#..
 * Nr   16  Plant New    14  Existing   184  Result   282    ....#.#..#...#.#...##...#...#.#..##..##.
 * Nr   17  Plant New    12  Existing   196  Result   218    .....#...##...#.#.#.#...##...#....#...#..
 * Nr   18  Plant New    18  Existing   214  Result   352    .....##.#.#....#####.#.#.#...##...##..##.
 * Nr   19  Plant New    20  Existing   234  Result   367    ....#..###.#..#.#.#######.#.#.#..#.#...#..
 * 
 * Index    2  ..#..  =  #     1     Result      2 
 * Index    7  ###.#  =  #     2     Result      9 
 * Index    8  ##.#.  =  #     3     Result     17 
 * Index   13  .#.#.  =  #     4     Result     30 
 * Index   14  #.#.#  =  #     5     Result     44 
 * Index   15  .#.##  =  #     6     Result     59 
 * Index   16  #.###  =  #     7     Result     75 
 * Index   17  .####  =  #     8     Result     92 
 * Index   21  ####.  =  #     9     Result    113 
 * Index   22  ###.#  =  #    10     Result    135 
 * Index   23  ##.#.  =  #    11     Result    158 
 * Index   24  #.#.#  =  #    12     Result    182 
 * Index   25  .#.#.  =  #    13     Result    207 
 * Index   26  #.#.#  =  #    14     Result    233 
 * Index   27  .#.#.  =  #    15     Result    260 
 * Index   32  .#.#.  =  #    16     Result    292 
 * Index   34  .#...  =  #    17     Result    326 
 * Index   37  ..#..  =  #    18     Result    363 
 * Index   38  .#...  =  #    19     Result    401
 *  
 * Nr   20  Plant New    19  Existing   253  Result   401    ....#....##....#####...#######....#.#..##.
 * Nr   21  Plant New    14  Existing   267  Result   244    ....##..#.#...#.#.##..#.#...##.....#....#..
 * Nr   22  Plant New    16  Existing   283  Result   319    ...#.#...#.#...###.#...#.#.#.#.....##...##.
 * Nr   23  Plant New    18  Existing   301  Result   375    ....#.#...#.#.#..##.#...#####.#...#.#..#.#..
 * 
 * 
 * ......#..#.#..##......###...###.............
 * ......#...#....#.....#..#..#..#.............
 * ......##..##...##....#..#..#..##............
 * .....#.#...#..#.#....#..#..#...#............
 * ......#.#..#...#.#...#..#..##..##...........
 * .......#...##...#.#..#..#...#...#...........
 * .......##.#.#....#...#..##..##..##..........
 * ......#..###.#...##..#...#...#...#..........
 * ......#....##.#.#.#..##..##..##..##.........
 * ......##..#..#####....#...#...#...#.........
 * .....#.#..#...#.##....##..##..##..##........
 * ......#...##...#.#...#.#...#...#...#........
 * ......##.#.#....#.#...#.#..##..##..##.......
 * .....#..###.#....#.#...#....#...#...#.......
 * .....#....##.#....#.#..##...##..##..##......
 * .....##..#..#.#....#....#..#.#...#...#......
 * ....#.#..#...#.#...##...#...#.#..##..##.....
 * .....#...##...#.#.#.#...##...#....#...#.....
 * .....##.#.#....#####.#.#.#...##...##..##....
 * ....#..###.#..#.#.#######.#.#.#..#.#...#....
 * ....#....##....#####...#######....#.#..##...
 * ....##..#.#...#.#.##..#.#...##.....#....#...
 * ...#.#...#.#...###.#...#.#.#.#.....##...##..
 * ....#.#...#.#.#..##.#...#####.#...#.#..#.#..
 * 
 * 
 * plant_new 301
 * 
 * Result Part 1 401
 * Result Part 2 0
 * 
 * Check Index    3  Result      3 
 * Check Index    4  Result      7 
 * Check Index    9  Result     16 
 * Check Index   10  Result     26 
 * Check Index   11  Result     37 
 * Check Index   12  Result     49 
 * Check Index   13  Result     62 
 * Check Index   17  Result     79 
 * Check Index   18  Result     97 
 * Check Index   19  Result    116 
 * Check Index   20  Result    136 
 * Check Index   21  Result    157 
 * Check Index   22  Result    179 
 * Check Index   23  Result    202 
 * Check Index   28  Result    230 
 * Check Index   30  Result    260 
 * Check Index   33  Result    293 
 * Check Index   34  Result    327 
 * count_pot_ids 327
 * 
 * </pre> 
 */
public class Day12_SubterraneanSustainability
{
  public static void main( String[] args )
  {
    String test_input = "";

    test_input += "initial state: #..#.#..##......###...###";
    test_input += ",";
    test_input += ",...## => #";
    test_input += ",..#.. => #";
    test_input += ",.#... => #";
    test_input += ",.#.#. => #";
    test_input += ",.#.## => #";
    test_input += ",.##.. => #";
    test_input += ",.#### => #";
    test_input += ",#.#.# => #";
    test_input += ",#.### => #";
    test_input += ",##.#. => #";
    test_input += ",##.## => #";
    test_input += ",###.. => #";
    test_input += ",###.# => #";
    test_input += ",####. => #,";

    calculatePart01( test_input, true );

    String x_inp = "...##....#####...#######....#.#..##.";

    int count_pot_ids = 0;

    for ( int cur_col = 0; cur_col < x_inp.length(); cur_col++ )
    {
      if ( x_inp.charAt( cur_col ) == '#' )
      {
        count_pot_ids += cur_col;

        wl( String.format( "Check Index %4d  Result %6d ", cur_col, count_pot_ids ) );
      }
    }

    wl( "count_pot_ids " + count_pot_ids );

    System.exit( 0 );
  }

  private static void calculatePart01( String pString, boolean pKnzDebug )
  {
    List< String > converted_string_list = Arrays.stream( pString.split( "," ) ).map( String::trim ).collect( Collectors.toList() );

    calculate01( converted_string_list, pKnzDebug );
  }

  private static void calculate01( List< String > pListInput, boolean pKnzDebug )
  {
    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );

    long result_part_01 = 0;

    long result_part_02 = 0;

    Properties properties_keys = new Properties();

    String current_state = "...." + pListInput.get( 0 ).substring( 15 ) + "....";

    for ( String input_str : pListInput )
    {
      if ( input_str.indexOf( " => #" ) > 0 )
      {
        /*
         * Save only patterns which will produce a plant 
         */

        String pattern = input_str.substring( 0, input_str.indexOf( " => #" ) );

        properties_keys.setProperty( pattern, "#" );

        if ( pKnzDebug )
        {
          wl( input_str );
        }
      }
    }

    wl( "" );
    wl( "cur_state " + current_state );
    wl( "" );

    int min_plant_pot_idx = -2;

    int max_plant_pot_idx = current_state.length() + 4;

    int cur_round_nr = 0;

    /*
     * Placing the current status to the plant pot properties.
     * 
     * The current status (puzzle input) starts with plant pot 0.
     */
    Properties properties_plant_pots = new Properties();

    for ( int cur_col = 0; cur_col < current_state.length(); cur_col++ )
    {
      properties_plant_pots.setProperty( "R" + cur_round_nr + "C" + cur_col, "" + current_state.charAt( cur_col ) );
    }

    boolean knz_debug_pattern_match = false;

    int plant_total = 0;

    while ( cur_round_nr <= 22 )
    {
      String next_state = "";

      int index_last_plant_pot = 0;

      int plant_new = 0;

      /*
       * Place the old generation round in the variable "last_round_nr"
       */
      int last_round_nr = cur_round_nr;

      /*
       * Increase the round number
       */
      cur_round_nr++;

      /*
       * Variable for the part 1 result.
       * Here for each round calculated (Debug) 
       */
      int result_plant_nr = 0;

      /*
       * For loop for all the plant pots
       */
      for ( int cur_pot_nr = min_plant_pot_idx; cur_pot_nr <= max_plant_pot_idx; cur_pot_nr++ )
      {
        /*
         * For the current plant pot, get the pattern from the last generation
         */
        String cur_pattern = getKey( properties_plant_pots, last_round_nr, cur_pot_nr );

        /*
         * If the pattern generates a plant, the key was saved to the key properties.
         */
        if ( properties_keys.getProperty( cur_pattern ) != null )
        {
          /*
           * In the plant pot properties, mark the current pot as "Pot with plant" (='#')
           */
          properties_plant_pots.setProperty( "R" + cur_round_nr + "C" + cur_pot_nr, "#" );

          /*
           * Increase the result value for part 1, with the pot id (which can be negative)
           */
          result_plant_nr += cur_pot_nr;

          /*
           * Save the ID (=column) of the last pot with a plant 
           */
          index_last_plant_pot = cur_pot_nr;

          /*
           * Increment the counter for new plants
           */
          plant_new++;

          /*
           * Set a "plant" in the debug-string
           */
          next_state += "#";

          /*
           * Do some debug-stuff
           */
          if ( ( cur_round_nr == 20 ) || ( knz_debug_pattern_match ) )
          {
            wl( String.format( "Index %4d  %s  =  %s  %4d     Result %6d ", cur_pot_nr, cur_pattern, properties_keys.getProperty( cur_pattern, "." ), plant_new, result_plant_nr ) );
          }
        }
        else
        {
          /*
           * If the current key doesn't produce a plant, no further actions are done.
           * 
           * For debug purposes the debug-string is updated
           */
          next_state += ".";

          if ( knz_debug_pattern_match )
          {
            wl( String.format( "Index %4d  %s  =  %s", cur_pot_nr, cur_pattern, properties_keys.getProperty( cur_pattern, "." ) ) );
          }
        }
      }

      /*
       * Determine the max plant pot id for the loop
       */
      max_plant_pot_idx = Math.max( max_plant_pot_idx, index_last_plant_pot + 2 );

      /*
       * Increase the total generated plants
       */
      plant_total += plant_new;

      current_state = next_state;

      if ( cur_round_nr == 20 )
      {
        result_part_01 = result_plant_nr;
      }

      if ( pKnzDebug )
      {
        if ( knz_debug_pattern_match )
        {
          wl( "" );
        }

        wl( String.format( "Nr %4d  Plant New %5d  Existing %5d  Result %5d    %s", cur_round_nr, plant_new, plant_total, result_plant_nr, next_state ) );

        if ( knz_debug_pattern_match )
        {
          wl( "" );
          wl( "" );
        }
      }
    }

    String all_states = getDebugGrid( properties_plant_pots, 0, min_plant_pot_idx, cur_round_nr + 1, max_plant_pot_idx + 1 );

    wl( "" );
    wl( "" );
    wl( all_states );
    wl( "" );
    wl( "plant_new " + plant_total );
    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
  }

  /*
   * 
     Nr   20  Plant New    19  Existing   253  Result   401    ......#....##....#####...#######....#.#..##.
                                                                20: .#....##....#####...#######....#.#..##.
  
  
                 1         2         3     
       0         0         0         0     
  0: ...#..#.#..##......###...###...........
  1: ...#...#....#.....#..#..#..#...........
  2: ...##..##...##....#..#..#..##..........
  3: ..#.#...#..#.#....#..#..#...#..........
  4: ...#.#..#...#.#...#..#..##..##.........
  5: ....#...##...#.#..#..#...#...#.........
  6: ....##.#.#....#...#..##..##..##........
  7: ...#..###.#...##..#...#...#...#........
  8: ...#....##.#.#.#..##..##..##..##.......
  9: ...##..#..#####....#...#...#...#.......
  10: ..#.#..#...#.##....##..##..##..##......
  11: ...#...##...#.#...#.#...#...#...#......
  12: ...##.#.#....#.#...#.#..##..##..##.....
  13: ..#..###.#....#.#...#....#...#...#.....
  14: ..#....##.#....#.#..##...##..##..##....
  15: ..##..#..#.#....#....#..#.#...#...#....
  16: .#.#..#...#.#...##...#...#.#..##..##...
  17: ..#...##...#.#.#.#...##...#....#...#...
  18: ..##.#.#....#####.#.#.#...##...##..##..
  19: .#..###.#..#.#.#######.#.#.#..#.#...#..
  20: .#....##....#####...#######....#.#..##.  
       0123456789012345678901234567890123456
                 1         2         3
                 
          34    91111   1     2    2 3  33   
                 0123   7     3    8 0  34
   */

  private static String getKey( Properties pProperties, int pRoundNr, int pStart )
  {
    String result_key = "";

    for ( int cur_col = pStart - 2; cur_col < pStart + 3; cur_col++ )
    {
      result_key += pProperties.getProperty( "R" + pRoundNr + "C" + cur_col, "." );
    }

    return result_key;
  }

  private static int countPotIndex( Properties pProperties, int pFromRow, int pFromCol, int pToRow, int pToCol )
  {
    int count_pot_ids = 0;

    for ( int cur_row = pFromRow; cur_row < pToCol; cur_row++ )
    {
      for ( int cur_col = pFromCol; cur_col < pToCol; cur_col++ )
      {
        if ( pProperties.getProperty( "R" + cur_row + "C" + cur_col, "." ).charAt( 0 ) == '#' )
        {
          count_pot_ids += cur_col;
        }
      }
    }

    return count_pot_ids;
  }

  private static String getDebugGrid( Properties pProperties, int pFromRow, int pFromCol, int pToRow, int pToCol )
  {
    String result_key = "";

    for ( int cur_row = pFromRow; cur_row < pToRow; cur_row++ )
    {
      for ( int cur_col = pFromCol; cur_col < pToCol; cur_col++ )
      {
        result_key += pProperties.getProperty( "R" + cur_row + "C" + cur_col, "." );
      }

      result_key += "\n";
    }

    return result_key;
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2018__day12_input.txt";

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
