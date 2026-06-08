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
 * Nr    0  Plant New     7  Existing     7  ....#...#....#.....#..#..#..#........
 * Nr    1  Plant New    11  Existing    18  ....##..##...##....#..#..#..##...
 * Nr    2  Plant New     8  Existing    26  .....#...#..#.#....#..#..#...#....
 * Nr    3  Plant New    11  Existing    37  .....##..#...#.#...#..#..##..##...
 * Nr    4  Plant New    10  Existing    47  ....#.#..##...#.#..#..#...#...#....
 * Nr    5  Plant New    10  Existing    57  .....#....#....#...#..##..##..##...
 * Nr    6  Plant New    10  Existing    67  .....##...##...##..#...#...#...#....
 * Nr    7  Plant New    14  Existing    81  ....#.#..#.#..#.#..##..##..##..##...
 * Nr    8  Plant New     7  Existing    88  .....#....#....#....#...#...#...#....
 * Nr    9  Plant New    14  Existing   102  .....##...##...##...##..##..##..##...
 * Nr   10  Plant New    11  Existing   113  ....#.#..#.#..#.#..#.#...#...#...#....
 * Nr   11  Plant New    11  Existing   124  .....#....#....#....#.#..##..##..##...
 * Nr   12  Plant New    10  Existing   134  .....##...##...##....#....#...#...#....
 * Nr   13  Plant New    14  Existing   148  ....#.#..#.#..#.#....##...##..##..##...
 * Nr   14  Plant New    10  Existing   158  .....#....#....#.#..#.#..#.#...#...#....
 * Nr   15  Plant New    12  Existing   170  .....##...##....#....#....#.#..##..##...
 * Nr   16  Plant New    11  Existing   181  ....#.#..#.#....##...##....#....#...#....
 * Nr   17  Plant New    13  Existing   194  .....#....#.#..#.#..#.#....##...##..##...
 * Nr   18  Plant New    11  Existing   205  .....##....#....#....#.#..#.#..#.#...#....
 * Nr   19  Plant New    12  Existing   217  ....#.#....##...##....#....#....#.#..##...
 * 
 * ....#..#.#..##......###...###....
 * ....#...#....#.....#..#..#..#
 * ....##..##...##....#..#..#..##
 * .....#...#..#.#....#..#..#...#
 * .....##..#...#.#...#..#..##..##
 * ....#.#..##...#.#..#..#...#...#
 * .....#....#....#...#..##..##..##
 * .....##...##...##..#...#...#...#
 * ....#.#..#.#..#.#..##..##..##..##
 * .....#....#....#....#...#...#...#
 * .....##...##...##...##..##..##..##
 * ....#.#..#.#..#.#..#.#...#...#...#
 * .....#....#....#....#.#..##..##..##
 * .....##...##...##....#....#...#...#
 * ....#.#..#.#..#.#....##...##..##..##
 * .....#....#....#.#..#.#..#.#...#...#
 * .....##...##....#....#....#.#..##..##
 * ....#.#..#.#....##...##....#....#...#
 * .....#....#.#..#.#..#.#....##...##..##
 * .....##....#....#....#.#..#.#..#.#...#
 * ....#.#....##...##....#....#....#.#..##
 * 
 * 
 * plant_new 217
 * 
 * Result Part 1 228
 * Result Part 2 0
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

    Properties prop_keys = new Properties();

    String current_state = "...." + pListInput.get( 0 ).substring( 15 ) + "....";

    for ( String input_str : pListInput )
    {
      if ( input_str.indexOf( " => #" ) > 0 )
      {
        String pattern = input_str.substring( 0, input_str.indexOf( " => #" ) );

        prop_keys.setProperty( pattern, "#" );

        if ( pKnzDebug )
        {
          wl( input_str );
        }
      }
    }

    wl( "" );
    wl( "cur_state " + current_state );
    wl( "" );

    boolean knz_debug_pattern_match = false;

    String all_states = current_state;

    int plant_total = 0;

    for ( int round_nr = 0; round_nr < 20; round_nr++ )
    {
      String next_state = "";

      int plant_new = 0;

      for ( int idx = -2; idx < ( current_state.length() + 2 ); idx++ )
      {
        String cur_pattern = getKey( current_state, idx );

        if ( prop_keys.getProperty( cur_pattern ) != null )
        {
          next_state += "#";

          plant_new++;

          if ( knz_debug_pattern_match )
          {
            wl( String.format( "Index %4d  %s  =  %s  %4d", idx, cur_pattern, prop_keys.getProperty( cur_pattern, "." ), plant_new ) );
          }
        }
        else
        {
          next_state += ".";

          if ( knz_debug_pattern_match )
          {
            wl( String.format( "Index %4d  %s  =  %s", idx, cur_pattern, prop_keys.getProperty( cur_pattern, "." ) ) );
          }
        }
      }

      plant_total += plant_new;

      current_state = trimTail( next_state, '.' );

      all_states += "\n" + current_state;

      if ( pKnzDebug )
      {
        if ( knz_debug_pattern_match )
        {
          wl( "" );
        }

        wl( String.format( "Nr %4d  Plant New %5d  Existing %5d  %s", round_nr, plant_new, plant_total, next_state ) );

        if ( knz_debug_pattern_match )
        {
          wl( "" );
          wl( "" );
        }
      }
    }

    wl( "" );
    wl( all_states );
    wl( "" );

    for ( int idx = 0; idx < all_states.length(); idx++ )
    {
      if ( ( all_states.charAt( idx ) == '#' ) )
      {
        result_part_01++;
      }
    }

    wl( "" );
    wl( "plant_new " + plant_total );
    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
  }

  private static String getKey( String pInput, int pStart )
  {
    String result_key = "";

    if ( pStart <= -3 )
    {
      result_key = ".....";
    }
    else if ( pStart == -2 )
    {
      result_key = "...." + pInput.substring( 0, 1 );
    }
    else if ( pStart == -1 )
    {
      result_key = "..." + pInput.substring( 0, 2 );
    }
    else if ( pStart == 0 )
    {
      result_key = ".." + pInput.substring( 0, 3 );
    }
    else if ( pStart == 1 )
    {
      result_key = "." + pInput.substring( 0, 4 );
    }
    else if ( pStart + 5 < pInput.length() )
    {
      result_key = pInput.substring( pStart, pStart + 5 );
    }
    else
    {
      result_key = ( pInput + "......" ).substring( pStart, pStart + 5 );
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

  private static String trimTail( String pString, char pTrimZeichen )
  {
    if ( pString != null )
    {
      int position_start = 0;

      int position_ende = pString.length();

      char[] char_werte = pString.toCharArray();

      while ( ( position_ende > position_start ) && ( char_werte[ position_ende - 1 ] == ' ' || char_werte[ position_ende - 1 ] == pTrimZeichen ) )
      {
        position_ende--;
      }

      char_werte = null;

      if ( ( position_start > 0 ) || ( position_ende < pString.length() ) )
      {
        return pString.substring( position_start, position_ende );
      }
    }

    return pString;
  }

}