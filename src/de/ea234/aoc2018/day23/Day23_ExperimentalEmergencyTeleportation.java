package de.ea234.aoc2018.day23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 23: Experimental Emergency Teleportation ---
 * https://adventofcode.com/2018/day/23
 * 
 * https://www.reddit.com/r/adventofcode/comments/a8s17l/2018_day_23_solutions/
 * 
 * https://github.com/ea234/Advent_of_Code_2018/blob/main/src/de/ea234/aoc2018/day23/Day23_ExperimentalEmergencyTeleportation.java
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * bot_a Bot Nr    0  0,0,0  radius 4   bots in range 0
 * 
 * The nanobot nr    0 at 0,0,0 is distance 0 away - count_bots    1
 * The nanobot nr    1 at 1,0,0 is distance 1 away - count_bots    2
 * The nanobot nr    2 at 4,0,0 is distance 4 away - count_bots    3
 * The nanobot nr    3 at 0,2,0 is distance 2 away - count_bots    4
 * The nanobot nr    4 at 0,5,0 is distance 5 away - count_bots    4
 * The nanobot nr    5 at 0,0,3 is distance 3 away - count_bots    5
 * The nanobot nr    6 at 1,1,1 is distance 3 away - count_bots    6
 * The nanobot nr    7 at 1,1,2 is distance 4 away - count_bots    7
 * The nanobot nr    8 at 1,3,1 is distance 5 away - count_bots    7
 * ------------------------------------------------------------------------------------------
 * bot_a Bot Nr    1  1,0,0  radius 1   bots in range 0
 * 
 * The nanobot nr    0 at 0,0,0 is distance 1 away - count_bots    1
 * The nanobot nr    1 at 1,0,0 is distance 0 away - count_bots    2
 * The nanobot nr    2 at 4,0,0 is distance 3 away - count_bots    2
 * The nanobot nr    3 at 0,2,0 is distance 3 away - count_bots    2
 * The nanobot nr    4 at 0,5,0 is distance 6 away - count_bots    2
 * The nanobot nr    5 at 0,0,3 is distance 4 away - count_bots    2
 * The nanobot nr    6 at 1,1,1 is distance 2 away - count_bots    2
 * The nanobot nr    7 at 1,1,2 is distance 3 away - count_bots    2
 * The nanobot nr    8 at 1,3,1 is distance 4 away - count_bots    2
 * ------------------------------------------------------------------------------------------
 * bot_a Bot Nr    2  4,0,0  radius 3   bots in range 0
 * 
 * The nanobot nr    0 at 0,0,0 is distance 4 away - count_bots    0
 * The nanobot nr    1 at 1,0,0 is distance 3 away - count_bots    1
 * The nanobot nr    2 at 4,0,0 is distance 0 away - count_bots    2
 * The nanobot nr    3 at 0,2,0 is distance 6 away - count_bots    2
 * The nanobot nr    4 at 0,5,0 is distance 9 away - count_bots    2
 * The nanobot nr    5 at 0,0,3 is distance 7 away - count_bots    2
 * The nanobot nr    6 at 1,1,1 is distance 5 away - count_bots    2
 * The nanobot nr    7 at 1,1,2 is distance 6 away - count_bots    2
 * The nanobot nr    8 at 1,3,1 is distance 7 away - count_bots    2
 * ------------------------------------------------------------------------------------------
 * bot_a Bot Nr    3  0,2,0  radius 1   bots in range 0
 * 
 * The nanobot nr    0 at 0,0,0 is distance 2 away - count_bots    0
 * The nanobot nr    1 at 1,0,0 is distance 3 away - count_bots    0
 * The nanobot nr    2 at 4,0,0 is distance 6 away - count_bots    0
 * The nanobot nr    3 at 0,2,0 is distance 0 away - count_bots    1
 * The nanobot nr    4 at 0,5,0 is distance 3 away - count_bots    1
 * The nanobot nr    5 at 0,0,3 is distance 5 away - count_bots    1
 * The nanobot nr    6 at 1,1,1 is distance 3 away - count_bots    1
 * The nanobot nr    7 at 1,1,2 is distance 4 away - count_bots    1
 * The nanobot nr    8 at 1,3,1 is distance 3 away - count_bots    1
 * ------------------------------------------------------------------------------------------
 * bot_a Bot Nr    4  0,5,0  radius 3   bots in range 0
 * 
 * The nanobot nr    0 at 0,0,0 is distance 5 away - count_bots    0
 * The nanobot nr    1 at 1,0,0 is distance 6 away - count_bots    0
 * The nanobot nr    2 at 4,0,0 is distance 9 away - count_bots    0
 * The nanobot nr    3 at 0,2,0 is distance 3 away - count_bots    1
 * The nanobot nr    4 at 0,5,0 is distance 0 away - count_bots    2
 * The nanobot nr    5 at 0,0,3 is distance 8 away - count_bots    2
 * The nanobot nr    6 at 1,1,1 is distance 6 away - count_bots    2
 * The nanobot nr    7 at 1,1,2 is distance 7 away - count_bots    2
 * The nanobot nr    8 at 1,3,1 is distance 4 away - count_bots    2
 * ------------------------------------------------------------------------------------------
 * bot_a Bot Nr    5  0,0,3  radius 1   bots in range 0
 * 
 * The nanobot nr    0 at 0,0,0 is distance 3 away - count_bots    0
 * The nanobot nr    1 at 1,0,0 is distance 4 away - count_bots    0
 * The nanobot nr    2 at 4,0,0 is distance 7 away - count_bots    0
 * The nanobot nr    3 at 0,2,0 is distance 5 away - count_bots    0
 * The nanobot nr    4 at 0,5,0 is distance 8 away - count_bots    0
 * The nanobot nr    5 at 0,0,3 is distance 0 away - count_bots    1
 * The nanobot nr    6 at 1,1,1 is distance 4 away - count_bots    1
 * The nanobot nr    7 at 1,1,2 is distance 3 away - count_bots    1
 * The nanobot nr    8 at 1,3,1 is distance 6 away - count_bots    1
 * ------------------------------------------------------------------------------------------
 * bot_a Bot Nr    6  1,1,1  radius 1   bots in range 0
 * 
 * The nanobot nr    0 at 0,0,0 is distance 3 away - count_bots    0
 * The nanobot nr    1 at 1,0,0 is distance 2 away - count_bots    0
 * The nanobot nr    2 at 4,0,0 is distance 5 away - count_bots    0
 * The nanobot nr    3 at 0,2,0 is distance 3 away - count_bots    0
 * The nanobot nr    4 at 0,5,0 is distance 6 away - count_bots    0
 * The nanobot nr    5 at 0,0,3 is distance 4 away - count_bots    0
 * The nanobot nr    6 at 1,1,1 is distance 0 away - count_bots    1
 * The nanobot nr    7 at 1,1,2 is distance 1 away - count_bots    2
 * The nanobot nr    8 at 1,3,1 is distance 2 away - count_bots    2
 * ------------------------------------------------------------------------------------------
 * bot_a Bot Nr    7  1,1,2  radius 1   bots in range 0
 * 
 * The nanobot nr    0 at 0,0,0 is distance 4 away - count_bots    0
 * The nanobot nr    1 at 1,0,0 is distance 3 away - count_bots    0
 * The nanobot nr    2 at 4,0,0 is distance 6 away - count_bots    0
 * The nanobot nr    3 at 0,2,0 is distance 4 away - count_bots    0
 * The nanobot nr    4 at 0,5,0 is distance 7 away - count_bots    0
 * The nanobot nr    5 at 0,0,3 is distance 3 away - count_bots    0
 * The nanobot nr    6 at 1,1,1 is distance 1 away - count_bots    1
 * The nanobot nr    7 at 1,1,2 is distance 0 away - count_bots    2
 * The nanobot nr    8 at 1,3,1 is distance 3 away - count_bots    2
 * ------------------------------------------------------------------------------------------
 * bot_a Bot Nr    8  1,3,1  radius 1   bots in range 0
 * 
 * The nanobot nr    0 at 0,0,0 is distance 5 away - count_bots    0
 * The nanobot nr    1 at 1,0,0 is distance 4 away - count_bots    0
 * The nanobot nr    2 at 4,0,0 is distance 7 away - count_bots    0
 * The nanobot nr    3 at 0,2,0 is distance 3 away - count_bots    0
 * The nanobot nr    4 at 0,5,0 is distance 4 away - count_bots    0
 * The nanobot nr    5 at 0,0,3 is distance 6 away - count_bots    0
 * The nanobot nr    6 at 1,1,1 is distance 2 away - count_bots    0
 * The nanobot nr    7 at 1,1,2 is distance 3 away - count_bots    0
 * The nanobot nr    8 at 1,3,1 is distance 0 away - count_bots    1
 * 
 * 
 * Bot Nr    0  0,0,0  radius 4   bots in range 7
 * Bot Nr    1  1,0,0  radius 1   bots in range 2
 * Bot Nr    2  4,0,0  radius 3   bots in range 2
 * Bot Nr    3  0,2,0  radius 1   bots in range 1
 * Bot Nr    4  0,5,0  radius 3   bots in range 2
 * Bot Nr    5  0,0,3  radius 1   bots in range 1
 * Bot Nr    6  1,1,1  radius 1   bots in range 2
 * Bot Nr    7  1,1,2  radius 1   bots in range 2
 * Bot Nr    8  1,3,1  radius 1   bots in range 1
 * 
 * 
 * max_count     7
 * 
 * bot_max       Bot Nr    0  0,0,0  radius 4   bots in range 7
 * 
 * Result Part 1 0
 * Result Part 2 0
 * 
 * 
 * </pre> 
 */
public class Day23_ExperimentalEmergencyTeleportation
{
  private static final Pattern PATTERN = Pattern.compile( "pos=<(-?\\d+),(-?\\d+),(-?\\d+)>, r=(-?\\d+)" );

  public static void main( String[] args )
  {
    String test_input = "";

    test_input += ";pos=<0,0,0>, r=4";
    test_input += ";pos=<1,0,0>, r=1";
    test_input += ";pos=<4,0,0>, r=3";
    test_input += ";pos=<0,2,0>, r=1";
    test_input += ";pos=<0,5,0>, r=3";
    test_input += ";pos=<0,0,3>, r=1";
    test_input += ";pos=<1,1,1>, r=1";
    test_input += ";pos=<1,1,2>, r=1";
    test_input += ";pos=<1,3,1>, r=1";

    calculatePart01( test_input, true );

    //calculate01( getListProd(), false );

    System.exit( 0 );
  }

  private static void calculatePart01( String pString, boolean pKnzDebug )
  {
    List< String > converted_string_list = Arrays.stream( pString.split( ";" ) ).map( String::trim ).collect( Collectors.toList() );

    calculate01( converted_string_list, pKnzDebug );
  }

  private static void calculate01( List< String > pListInput, boolean pKnzDebug )
  {

    long result_part_01 = 0;
    long result_part_02 = 0;

    /*
     * *******************************************************************************************************
     * Creating the nanobots from the input list
     * *******************************************************************************************************
     */

    List< Nanobot > n_b_list = new ArrayList< Nanobot >();

    int bot_nr = 0;

    for ( String input_str : pListInput )
    {
      if ( !input_str.isBlank() )
      {
        n_b_list.add( new Nanobot( bot_nr, input_str ) );

        bot_nr++;
      }
    }

    /*
     * *******************************************************************************************************
     * Calculating the manhatten distance
     * *******************************************************************************************************
     */

    long max_count = 0;

    Nanobot bot_max = null;

    for ( Nanobot bot_a : n_b_list )
    {
      if ( pKnzDebug )
      {
        wl( "------------------------------------------------------------------------------------------" );
        wl( "bot_a " + bot_a.toString() );
        wl( "" );
      }

      long count_bots = 0;

      long radius = bot_a.getRadius();

      for ( Nanobot cur_bot : n_b_list )
      {
        long cur_distance = bot_a.getManhattenDistance( cur_bot );

        if ( cur_distance <= radius )
        {
          count_bots++;
        }

        if ( pKnzDebug )
        {
          wl( String.format( "The nanobot nr %4d at %d,%d,%d is distance %d away - count_bots %4d", cur_bot.getBotNr(), cur_bot.getPosX(), cur_bot.getPosY(), cur_bot.getPosZ(), cur_distance, count_bots ) );
        }
      }

      bot_a.setCountBots( count_bots );

      if ( count_bots > max_count )
      {
        max_count = count_bots;

        bot_max = bot_a;
      }
    }

    if ( pKnzDebug )
    {
      wl( "" );
      wl( "" );

      for ( Nanobot cur_bot : n_b_list )
      {
        wl( cur_bot.toString() );
      }

      wl( "" );
    }

    wl( "" );
    wl( "max_count     " + max_count );
    wl( "" );
    wl( "bot_max       " + bot_max.toString() );
    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
  }

  private static class Nanobot
  {
    private int  bot_nr     = 0;

    private long pos_x      = 0;

    private long pos_y      = 0;

    private long pos_z      = 0;

    private long radius     = 0;

    private long count_bots = 0;

    public Nanobot( int pNr, String pInput )
    {
      bot_nr = pNr;

      Matcher matcher = PATTERN.matcher( pInput );

      matcher.find();

      pos_x = Long.parseLong( matcher.group( 1 ) );

      pos_y = Long.parseLong( matcher.group( 2 ) );

      pos_z = Long.parseLong( matcher.group( 3 ) );

      radius = Long.parseLong( matcher.group( 4 ) );
    }

    public long getCountBots()
    {
      return count_bots;
    }

    public void setCountBots( long pCountBots )
    {
      count_bots = pCountBots;
    }

    public long getBotNr()
    {
      return bot_nr;
    }

    public long getPosX()
    {
      return pos_x;
    }

    public long getPosY()
    {
      return pos_y;
    }

    public long getPosZ()
    {
      return pos_z;
    }

    public long getRadius()
    {
      return radius;
    }

    public long getManhattenDistance( Nanobot pOtherNanobot )
    {
      long distance_x = calcDistance( pOtherNanobot.getPosX(), pos_x );
      long distance_y = calcDistance( pOtherNanobot.getPosY(), pos_y );
      long distance_z = calcDistance( pOtherNanobot.getPosZ(), pos_z );

      return distance_x + distance_y + distance_z;
    }

    private long calcDistance( long pPosA, long pPosB )
    {
      if ( ( pPosA >= 0 ) && ( pPosB >= 0 ) )
      {
        return Math.abs( pPosA - pPosB );
      }

      if ( ( pPosA <= 0 ) && ( pPosB <= 0 ) )
      {
        return Math.abs( pPosA - pPosB );
      }

      return Math.abs( pPosA ) + Math.abs( pPosB );
    }

    public String toString()
    {
      return String.format( "Bot Nr %4d  %d,%d,%d  radius %d   bots in range %d", bot_nr, pos_x, pos_y, pos_z, radius, count_bots );
    }
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2018__day23_input.txt";

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
