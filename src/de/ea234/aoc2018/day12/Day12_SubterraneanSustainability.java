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
 * cur_state "#..#.#..##......###...###"
 * 
 * Nr    1    Result    91    ...|#...#....#.....#..#..#..#.....
 * Nr    2    Result   132    ...|##..##...##....#..#..#..##....
 * Nr    3    Result   102    ..#|.#...#..#.#....#..#..#...#....
 * Nr    4    Result   154    ...|#.#..#...#.#...#..#..##..##...
 * Nr    5    Result   115    ...|.#...##...#.#..#..#...#...#...
 * Nr    6    Result   174    ...|.##.#.#....#...#..##..##..##..
 * Nr    7    Result   126    ...|#..###.#...##..#...#...#...#..
 * Nr    8    Result   213    ...|#....##.#.#.#..##..##..##..##.
 * Nr    9    Result   138    ...|##..#..#####....#...#...#...#..
 * Nr   10    Result   213    ..#|.#..#...#.##....##..##..##..##.
 * Nr   11    Result   136    ...|#...##...#.#...#.#...#...#...#..
 * Nr   12    Result   218    ...|##.#.#....#.#...#.#..##..##..##.
 * Nr   13    Result   133    ..#|..###.#....#.#...#....#...#...#..
 * Nr   14    Result   235    ..#|....##.#....#.#..##...##..##..##.
 * Nr   15    Result   149    ..#|#..#..#.#....#....#..#.#...#...#..
 * Nr   16    Result   226    .#.|#..#...#.#...##...#...#.#..##..##.
 * Nr   17    Result   170    ..#|...##...#.#.#.#...##...#....#...#..
 * Nr   18    Result   280    ..#|#.#.#....#####.#.#.#...##...##..##.
 * Nr   19    Result   287    .#.|.###.#..#.#.#######.#.#.#..#.#...#..
 * 
 * Index   -2   ..#..  =  #    Result     -2 
 * Index    3   ###.#  =  #    Result      1 
 * Index    4   ##.#.  =  #    Result      5 
 * Index    9   .#.#.  =  #    Result     14 
 * Index   10   #.#.#  =  #    Result     24 
 * Index   11   .#.##  =  #    Result     35 
 * Index   12   #.###  =  #    Result     47 
 * Index   13   .####  =  #    Result     60 
 * Index   17   ####.  =  #    Result     77 
 * Index   18   ###.#  =  #    Result     95 
 * Index   19   ##.#.  =  #    Result    114 
 * Index   20   #.#.#  =  #    Result    134 
 * Index   21   .#.#.  =  #    Result    155 
 * Index   22   #.#.#  =  #    Result    177 
 * Index   23   .#.#.  =  #    Result    200 
 * Index   28   .#.#.  =  #    Result    228 
 * Index   30   .#...  =  #    Result    258 
 * Index   33   ..#..  =  #    Result    291 
 * Index   34   .#...  =  #    Result    325
 *  
 * Nr   20    Result   325    .#.|...##....#####...#######....#.#..##.
 * 
 * ...|#..#.#..##......###...###............
 * ...|#...#....#.....#..#..#..#............
 * ...|##..##...##....#..#..#..##...........
 * ..#|.#...#..#.#....#..#..#...#...........
 * ...|#.#..#...#.#...#..#..##..##..........
 * ...|.#...##...#.#..#..#...#...#..........
 * ...|.##.#.#....#...#..##..##..##.........
 * ...|#..###.#...##..#...#...#...#.........
 * ...|#....##.#.#.#..##..##..##..##........
 * ...|##..#..#####....#...#...#...#........
 * ..#|.#..#...#.##....##..##..##..##.......
 * ...|#...##...#.#...#.#...#...#...#.......
 * ...|##.#.#....#.#...#.#..##..##..##......
 * ..#|..###.#....#.#...#....#...#...#......
 * ..#|....##.#....#.#..##...##..##..##.....
 * ..#|#..#..#.#....#....#..#.#...#...#.....
 * .#.|#..#...#.#...##...#...#.#..##..##....
 * ..#|...##...#.#.#.#...##...#....#...#....
 * ..#|#.#.#....#####.#.#.#...##...##..##...
 * .#.|.###.#..#.#.#######.#.#.#..#.#...#...
 * .#.|...##....#####...#######....#.#..##..
 * 
 * 
 * 
 * Result Part 1 325
 * 
 * ------------------------------------------------------------------------------------------
 * Index   16   .#..#  =  #    Result     16 
 * Index   19   .#..#  =  #    Result     35 
 * Index   22   .#..#  =  #    Result     57 
 * Index   23   #..##  =  #    Result     80 
 * Index   24   ..###  =  #    Result    104 
 * Index   26   ###.#  =  #    Result    130 
 * Index   27   ##.##  =  #    Result    157 
 * Index   28   #.##.  =  #    Result    185 
 * Index   30   ##..#  =  #    Result    215 
 * Index   31   #..##  =  #    Result    246 
 * Index   34   ##..#  =  #    Result    280 
 * Index   35   #..##  =  #    Result    315 
 * Index   38   ##..#  =  #    Result    353 
 * Index   39   #..##  =  #    Result    392 
 * Index   42   ##..#  =  #    Result    434 
 * Index   43   #..##  =  #    Result    477 
 * Index   44   ..###  =  #    Result    521 
 * Index   46   ###.#  =  #    Result    567 
 * Index   47   ##.##  =  #    Result    614 
 * Index   48   #.##.  =  #    Result    662 
 * Index   50   ##..#  =  #    Result    712 
 * Index   51   #..##  =  #    Result    763 
 * Index   52   ..###  =  #    Result    815 
 * Index   54   ###.#  =  #    Result    869 
 * Index   55   ##.##  =  #    Result    924 
 * Index   56   #.##.  =  #    Result    980 
 * Index   58   ##..#  =  #    Result   1038 
 * Index   59   #..##  =  #    Result   1097 
 * Index   62   ##..#  =  #    Result   1159 
 * Index   63   #..##  =  #    Result   1222 
 * Index   64   ..###  =  #    Result   1286 
 * Index   66   ###..  =  #    Result   1352 
 * Index   67   ##..#  =  #    Result   1419 
 * Index   70   .#..#  =  #    Result   1489 
 * Index   73   .#..#  =  #    Result   1562 
 * Index   76   .#..#  =  #    Result   1638 
 * Index   79   .#..#  =  #    Result   1717 
 * Index   82   .#..#  =  #    Result   1799 
 * Index   85   .#..#  =  #    Result   1884 
 * Index   88   .#..#  =  #    Result   1972 
 * Index   91   .#..#  =  #    Result   2063 
 * Index   94   .#..#  =  #    Result   2157 
 * Index   97   .#..#  =  #    Result   2254 
 * Index  100   .#...  =  #    Result   2354 
 * Index  105   .#..#  =  #    Result   2459 
 * Index  108   .#..#  =  #    Result   2567 
 * Index  111   .#..#  =  #    Result   2678 
 * Index  114   .#...  =  #    Result   2792 
 * Index  119   .#...  =  #    Result   2911 
 * 
 * Result Part 1 2911
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

    calculate01( getListProd(), false );

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

    String current_state = pListInput.get( 0 ).substring( 15 );

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

    if ( pKnzDebug )
    {
      wl( "" );
      wl( "cur_state \"" + current_state + "\"" );
      wl( "" );
    }

    int min_plant_pot_idx = -3;

    int max_plant_pot_idx = current_state.length() + 4;

    int cur_round_nr = 0;

    /*
     * Placing the current status to the plant pot properties.
     * 
     * The current status (puzzle input) starts with plant pot 0.
     */
    Properties properties_plant_pots = new Properties();

    for ( int cur_pot_nr = 0; cur_pot_nr < current_state.length(); cur_pot_nr++ )
    {
      properties_plant_pots.setProperty( "R" + cur_round_nr + "C" + cur_pot_nr, "" + current_state.charAt( cur_pot_nr ) );
    }

    boolean knz_debug_pattern_match = false;

    while ( cur_round_nr < 20 )
    {
      String next_state = "";

      int index_last_plant_pot = 0;

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
        if ( cur_pot_nr == 0 )
        {
          next_state += "|";
        }

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
           * Set a "plant" in the debug-string
           */
          next_state += "#";

          /*
           * Do some debug-stuff
           */
          if ( ( cur_round_nr == 20 ) || ( knz_debug_pattern_match ) )
          {
            wl( String.format( "Index %4d   %s  =  %s    Result %6d ", cur_pot_nr, cur_pattern, properties_keys.getProperty( cur_pattern, "." ), result_plant_nr ) );
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
            wl( String.format( "Index %4d   %s  =  %s", cur_pot_nr, cur_pattern, properties_keys.getProperty( cur_pattern, "." ) ) );
          }
        }
      }

      /*
       * Determine the max plant pot id for the loop
       */
      max_plant_pot_idx = Math.max( max_plant_pot_idx, index_last_plant_pot + 2 );

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

        wl( String.format( "Nr %4d    Result %5d    %s", cur_round_nr, result_plant_nr, next_state ) );

        if ( knz_debug_pattern_match )
        {
          wl( "" );
          wl( "" );
        }
      }
    }

    if ( pKnzDebug )
    {
      String all_states = getDebugGrid( properties_plant_pots, 0, min_plant_pot_idx, cur_round_nr + 1, max_plant_pot_idx + 1 );

      wl( "" );
      wl( all_states );
      wl( "" );
    }
    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
  }

  private static String getKey( Properties pProperties, int pRoundNr, int pStart )
  {
    String result_key = "";

    for ( int cur_col = pStart - 2; cur_col < pStart + 3; cur_col++ )
    {
      result_key += pProperties.getProperty( "R" + pRoundNr + "C" + cur_col, "." );
    }

    return result_key;
  }

  private static String getDebugGrid( Properties pProperties, int pFromRow, int pFromCol, int pToRow, int pToCol )
  {
    String result_key = "";

    for ( int cur_row = pFromRow; cur_row < pToRow; cur_row++ )
    {
      for ( int cur_col = pFromCol; cur_col < pToCol; cur_col++ )
      {
        if ( cur_col == 0 )
        {
          result_key += "|";
        }

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
