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
 * ------------------------------------------------------------------------------------------
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
 * Round   1   Plants   7  ...#...#....#.....#..#..#..#........ 
 * Round   2   Plants  11  ...##..##...##....#..#..#..##........ 
 * Round   3   Plants   9  ..#.#...#..#.#....#..#..#...#........ 
 * Round   4   Plants  11  ...#.#..#...#.#...#..#..##..##........ 
 * Round   5   Plants   9  ....#...##...#.#..#..#...#...#........ 
 * Round   6   Plants  12  ....##.#.#....#...#..##..##..##........ 
 * Round   7   Plants  11  ...#..###.#...##..#...#...#...#........ 
 * Round   8   Plants  14  ...#....##.#.#.#..##..##..##..##........ 
 * Round   9   Plants  12  ...##..#..#####....#...#...#...#........ 
 * Round  10   Plants  14  ..#.#..#...#.##....##..##..##..##........ 
 * Round  11   Plants  10  ...#...##...#.#...#.#...#...#...#........ 
 * Round  12   Plants  14  ...##.#.#....#.#...#.#..##..##..##........ 
 * Round  13   Plants  11  ..#..###.#....#.#...#....#...#...#........ 
 * Round  14   Plants  14  ..#....##.#....#.#..##...##..##..##........ 
 * Round  15   Plants  11  ..##..#..#.#....#....#..#.#...#...#........ 
 * Round  16   Plants  14  .#.#..#...#.#...##...#...#.#..##..##........ 
 * Round  17   Plants  12  ..#...##...#.#.#.#...##...#....#...#........ 
 * Round  18   Plants  18  ..##.#.#....#####.#.#.#...##...##..##........ 
 * Round  19   Plants  20  .#..###.#..#.#.#######.#.#.#..#.#...#........ 
 * Round  20   Plants  19  .#....##....#####...#######....#.#..##........ 
 * Round  21   Plants  14  .##..#.#...#.#.##..#.#...##.....#....#........ 
 * Round  22   Plants  16  #.#...#.#...###.#...#.#.#.#.....##...##........ 
 * Round  23   Plants  18  .#.#...#.#.#..##.#...#####.#...#.#..#.#........ 
 * Round  24   Plants  16  ..#.#...###.....#.#.#.#.###.#...#....#.#........ 
 * Round  25   Plants  19  ...#.#.#..#......########.##.#..##....#.#........ 
 * Round  26   Plants  14  ....###...##....#.#....###..#....#.....#.#........ 
 * Round  27   Plants  14  ...#..#..#.#.....#.#..#..#..##...##.....#.#........ 
 * Round  28   Plants  12  ...#..#...#.#.....#...#..#...#..#.#......#.#........ 
 * Round  29   Plants  15  ...#..##...#.#....##..#..##..#...#.#......#.#........ 
 * Round  30   Plants  14  ...#...#....#.#..#.#..#...#..##...#.#......#.#........ 
 * Round  31   Plants  14  ...##..##....#....#...##..#...#....#.#......#.#........ 
 * Round  32   Plants  17  ..#.#...#....##...##.#.#..##..##....#.#......#.#........ 
 * Round  33   Plants  16  ...#.#..##..#.#..#..###....#...#.....#.#......#.#........ 
 * Round  34   Plants  13  ....#....#...#...#....#....##..##.....#.#......#.#........ 
 * Round  35   Plants  17  ....##...##..##..##...##..#.#...#......#.#......#.#........ 
 * Round  36   Plants  16  ...#.#..#.#...#...#..#.#...#.#..##......#.#......#.#........ 
 * Round  37   Plants  14  ....#....#.#..##..#...#.#...#....#.......#.#......#.#........ 
 * Round  38   Plants  16  ....##....#....#..##...#.#..##...##.......#.#......#.#........ 
 * Round  39   Plants  14  ...#.#....##...#...#....#....#..#.#........#.#......#.#........ 
 * Round  40   Plants  17  ....#.#..#.#...##..##...##...#...#.#........#.#......#.#........ 
 * Round  41   Plants  16  .....#....#.#.#.#...#..#.#...##...#.#........#.#......#.#........ 
 * Round  42   Plants  19  .....##....#####.#..#...#.#.#.#....#.#........#.#......#.#........ 
 * Round  43   Plants  21  ....#.#...#.#.###...##...#####.#....#.#........#.#......#.#........ 
 * Round  44   Plants  21  .....#.#...####.#..#.#..#.#.###.#....#.#........#.#......#.#........ 
 * Round  45   Plants  21  ......#.#.#.####....#....####.##.#....#.#........#.#......#.#........ 
 * Round  46   Plants  24  .......#########....##..#.####..#.#....#.#........#.#......#.#........ 
 * Round  47   Plants  19  ......#.#.....##...#.#...#####...#.#....#.#........#.#......#.#........ 
 * Round  48   Plants  18  .......#.#...#.#....#.#.#.#.##....#.#....#.#........#.#......#.#........ 
 * Round  49   Plants  20  ........#.#...#.#....#######.#.....#.#....#.#........#.#......#.#........ 
 * Round  50   Plants  18  .........#.#...#.#..#.#...###.#.....#.#....#.#........#.#......#.#........ 
 * Round  51   Plants  17  ..........#.#...#....#.#.#..##.#.....#.#....#.#........#.#......#.#........ 
 * Round  52   Plants  17  ...........#.#..##....###.....#.#.....#.#....#.#........#.#......#.#........ 
 * Round  53   Plants  14  ............#....#...#..#......#.#.....#.#....#.#........#.#......#.#........ 
 * Round  54   Plants  17  ............##...##..#..##......#.#.....#.#....#.#........#.#......#.#........ 
 * Round  55   Plants  16  ...........#.#..#.#..#...#.......#.#.....#.#....#.#........#.#......#.#........ 
 * Round  56   Plants  16  ............#....#...##..##.......#.#.....#.#....#.#........#.#......#.#........ 
 * Round  57   Plants  17  ............##...##.#.#...#........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  58   Plants  19  ...........#.#..#..###.#..##........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  59   Plants  15  ............#...#....##....#.........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  60   Plants  18  ............##..##..#.#....##.........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  61   Plants  17  ...........#.#...#...#.#..#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  62   Plants  17  ............#.#..##...#....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  63   Plants  16  .............#....#...##....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  64   Plants  18  .............##...##.#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  65   Plants  19  ............#.#..#..###.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  66   Plants  17  .............#...#....##.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  67   Plants  19  .............##..##..#..#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  68   Plants  18  ............#.#...#..#...#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  69   Plants  19  .............#.#..#..##...#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  70   Plants  17  ..............#...#...#....#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  71   Plants  20  ..............##..##..##....#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  72   Plants  18  .............#.#...#...#.....#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  73   Plants  20  ..............#.#..##..##.....#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  74   Plants  17  ...............#....#...#......#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  75   Plants  20  ...............##...##..##......#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  76   Plants  19  ..............#.#..#.#...#.......#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  77   Plants  19  ...............#....#.#..##.......#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  78   Plants  18  ...............##....#....#........#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  79   Plants  20  ..............#.#....##...##........#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  80   Plants  20  ...............#.#..#.#..#.#.........#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  81   Plants  18  ................#....#....#.#.........#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  82   Plants  20  ................##...##....#.#.........#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  83   Plants  20  ...............#.#..#.#.....#.#.........#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  84   Plants  19  ................#....#.#.....#.#.........#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  85   Plants  20  ................##....#.#.....#.#.........#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  86   Plants  20  ...............#.#.....#.#.....#.#.........#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  87   Plants  20  ................#.#.....#.#.....#.#.........#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  88   Plants  20  .................#.#.....#.#.....#.#.........#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  89   Plants  20  ..................#.#.....#.#.....#.#.........#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  90   Plants  20  ...................#.#.....#.#.....#.#.........#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * Round  91   Plants  20  ....................#.#.....#.#.....#.#.........#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........ 
 * 
 * 
 * Plant Pot Nr    17 ( 49999999926)  Result   49999999926
 * Plant Pot Nr    19 ( 49999999928)  Result   99999999854
 * Plant Pot Nr    25 ( 49999999934)  Result  149999999788
 * Plant Pot Nr    27 ( 49999999936)  Result  199999999724
 * Plant Pot Nr    33 ( 49999999942)  Result  249999999666
 * Plant Pot Nr    35 ( 49999999944)  Result  299999999610
 * Plant Pot Nr    45 ( 49999999954)  Result  349999999564
 * Plant Pot Nr    47 ( 49999999956)  Result  399999999520
 * Plant Pot Nr    53 ( 49999999962)  Result  449999999482
 * Plant Pot Nr    55 ( 49999999964)  Result  499999999446
 * Plant Pot Nr    66 ( 49999999975)  Result  549999999421
 * Plant Pot Nr    68 ( 49999999977)  Result  599999999398
 * Plant Pot Nr    74 ( 49999999983)  Result  649999999381
 * Plant Pot Nr    76 ( 49999999985)  Result  699999999366
 * Plant Pot Nr    81 ( 49999999990)  Result  749999999356
 * Plant Pot Nr    83 ( 49999999992)  Result  799999999348
 * Plant Pot Nr    92 ( 50000000001)  Result  849999999349
 * Plant Pot Nr    94 ( 50000000003)  Result  899999999352
 * Plant Pot Nr   101 ( 50000000010)  Result  949999999362
 * Plant Pot Nr   103 ( 50000000012)  Result  999999999374
 * 
 * 
 * pattern_plant .................#.#.....#.#.....#.#.........#.#.....#.#..........#.#.....#.#....#.#........#.#......#.#........
 * pattern_plant 112
 * 
 *    fity_billion        50000000000
 *  - initial loop                 91
 *  =                     49999999909
 * 
 * 
 * Result Part 1 325
 * Result Part 2 999999999374
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * 
 * pattern_plant ...................#..#..#..#..#..#..#....#..#....#....#..#....#..#..#..#..#..#..#....#....#..#..#..#..#..#....#..#..#....#..#....#..#....#..#..#..#..#..#..#..#..#..#..#..#....#..#..#..#....#........
 * pattern_plant 199
 * 
 * Result Part 1 2911
 * Result Part 2 2500000000695
 * 
 * 
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

    Properties properties_plant_rules = new Properties();

    String current_state = pListInput.get( 0 ).substring( 15 );

    for ( String input_str : pListInput )
    {
      if ( input_str.indexOf( " => #" ) > 0 )
      {
        /*
         * Save only patterns which will produce a plant 
         */
        String pattern = input_str.substring( 0, input_str.indexOf( " => #" ) );

        properties_plant_rules.setProperty( pattern, "#" );

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

    int row_write = 0;

    int row_read = 1;

    int max_loop = 91;

    /*
     * Placing the current status to the plant pot properties.
     * 
     * The current status (puzzle input) starts with plant pot 0.
     */
    Properties properties_plant_pots = new Properties();

    for ( int cur_pot_nr = 0; cur_pot_nr < current_state.length(); cur_pot_nr++ )
    {
      properties_plant_pots.setProperty( "R" + row_write + "C" + cur_pot_nr, "" + current_state.charAt( cur_pot_nr ) );
    }

    while ( cur_round_nr < max_loop )
    {
      if ( row_write == 0 )
      {
        row_write = 1;
        row_read  = 0;
      }
      else
      {
        row_write = 0;
        row_read  = 1;
      }

      int index_last_plant_pot = 0;

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
        String cur_pattern = getKey( properties_plant_pots, row_read, cur_pot_nr );

        /*
         * If the pattern generates a plant, the key was saved to the plant rules properties.
         */
        if ( properties_plant_rules.getProperty( cur_pattern ) != null )
        {
          /*
           * In the plant pot properties, mark the current pot as "Pot with plant" (='#')
           */
          properties_plant_pots.setProperty( "R" + row_write + "C" + cur_pot_nr, "#" );

          /*
           * Increase the result value for part 1, with the pot id (which can be negative)
           */
          result_plant_nr += cur_pot_nr;

          /*
           * Save the ID (=column) of the last pot with a plant 
           */
          index_last_plant_pot = cur_pot_nr;
        }
        else
        {
          /*
           * If the pattern does not generate a plant, the corresponding place is saved with no plant.
           */
          properties_plant_pots.setProperty( "R" + row_write + "C" + cur_pot_nr, "." );
        }
      }

      /*
       * Determine the max plant pot id for the loop
       */
      max_plant_pot_idx = Math.max( max_plant_pot_idx, index_last_plant_pot + 5 );

      if ( cur_round_nr == 20 )
      {
        result_part_01 = result_plant_nr;
      }

      if ( pKnzDebug )
      {
        wl( String.format( "Round %3d   Plants %3d  %s ", cur_round_nr, countRow( properties_plant_pots, row_write, min_plant_pot_idx, max_plant_pot_idx ), getRow( properties_plant_pots, row_write, min_plant_pot_idx, max_plant_pot_idx ) ) );
      }
    }

    if ( pKnzDebug )
    {
      wl( "" );
      wl( "" );
    }

    /*
     * Get 50 Billion as long variable
     */
    long fity_billion = 50_000_000_000l;

    /*
     * Subtract the initial rounds from 50 billion.
     * That results in the shift-value for the periodic loop.
     */
    long id_plant_pot_col_from = fity_billion - max_loop;

    /*
     * Get a string with the last plant pattern
     */
    String pattern_plant = getRow( properties_plant_pots, row_write, 0, max_plant_pot_idx );

    /*
     * Start a loop over the plant pattern
     */
    long result_part_02 = 0;

    for ( int cur_pot_nr = 0; cur_pot_nr < pattern_plant.length(); cur_pot_nr++ )
    {
      if ( pattern_plant.charAt( cur_pot_nr ) == '#' )
      {
        /*
         * If there is a plant, the plant pot-Id has shifted.
         */
        result_part_02 += ( id_plant_pot_col_from + cur_pot_nr );

        if ( pKnzDebug )
        {
          wl( String.format( "Plant Pot Nr %5d (%12d)  Result %13d", cur_pot_nr, ( id_plant_pot_col_from + cur_pot_nr ), result_part_02 ) );
        }
      }
    }

    wl( "" );
    wl( "" );
    wl( "pattern_plant " + pattern_plant          );
    wl( "pattern_plant " + pattern_plant.length() );
    wl( "" );
    wl( String.format( "   fity_billion       %12d", fity_billion          ) );
    wl( String.format( " - initial loop       %12d", max_loop              ) );
    wl( String.format( " =                    %12d", id_plant_pot_col_from ) );
    wl( "" );
    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
  }

  private static String getKey( Properties pProperties, int pRow, int pCol )
  {
    StringBuilder result_key = new StringBuilder();

    for ( int cur_col = pCol - 2; cur_col < pCol + 3; cur_col++ )
    {
      result_key.append( pProperties.getProperty( "R" + pRow + "C" + cur_col, "." ) );
    }

    return result_key.toString();
  }

  private static String getRow( Properties pProperties, int pRow, int pFromCol, int pToCol )
  {
    StringBuilder result_key = new StringBuilder();

    for ( int cur_col = pFromCol; cur_col <= pToCol + 3; cur_col++ )
    {
      result_key.append( pProperties.getProperty( "R" + pRow + "C" + cur_col, "." ) );
    }

    return result_key.toString();
  }

  private static int countRow( Properties pProperties, int pRow, int pFromCol, int pToCol )
  {
    int result_plants = 0;

    for ( int cur_col = pFromCol; cur_col <= pToCol + 3; cur_col++ )
    {
      result_plants += pProperties.getProperty( "R" + pRow + "C" + cur_col, "." ).charAt( 0 ) == '#' ? 1 : 0;
    }

    return result_plants;
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
