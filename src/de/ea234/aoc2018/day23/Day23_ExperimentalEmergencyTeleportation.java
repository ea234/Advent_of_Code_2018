package de.ea234.aoc2018.day23;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
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
 * 
 * Using your torch to search the darkness of the rocky cavern, you finally locate
 * the man's friend: a small reindeer.
 * 
 * You're not sure how it got so far in this cave. It looks sick - too sick to walk
 * - and too heavy for you to carry all the way back. Sleighs won't be invented for
 * another 1500 years, of course.
 * 
 * The only option is experimental emergency teleportation.
 * 
 * You hit the "experimental emergency teleportation" button on the device and push
 * I accept the risk on no fewer than 18 different warning messages. Immediately, the
 * device deploys hundreds of tiny nanobots which fly around the cavern, apparently
 * assembling themselves into a very specific formation. The device lists the X,Y,Z
 * position (pos) for each nanobot as well as its signal radius (r) on its tiny screen
 * (your puzzle input).
 * 
 * Each nanobot can transmit signals to any integer coordinate which is a distance
 * away from it less than or equal to its signal radius (as measured by Manhattan distance).
 * Coordinates a distance away of less than or equal to a nanobot's signal radius are
 * said to be in range of that nanobot.
 * 
 * Before you start the teleportation process, you should determine which nanobot
 * is the strongest (that is, which has the largest signal radius) and then, for that
 * nanobot, the total number of nanobots that are in range of it, including itself.
 * 
 * For example, given the following nanobots:
 * 
 * pos=<0,0,0>, r=4
 * pos=<1,0,0>, r=1
 * pos=<4,0,0>, r=3
 * pos=<0,2,0>, r=1
 * pos=<0,5,0>, r=3
 * pos=<0,0,3>, r=1
 * pos=<1,1,1>, r=1
 * pos=<1,1,2>, r=1
 * pos=<1,3,1>, r=1
 * 
 * The strongest nanobot is the first one (position 0,0,0) because its signal radius,
 * 4 is the largest. Using that nanobot's location and signal radius, the following
 * nanobots are in or out of range:
 * 
 *     The nanobot at 0,0,0 is distance 0 away, and so it is in range.
 *     The nanobot at 1,0,0 is distance 1 away, and so it is in range.
 *     The nanobot at 4,0,0 is distance 4 away, and so it is in range.
 *     The nanobot at 0,2,0 is distance 2 away, and so it is in range.
 *     The nanobot at 0,5,0 is distance 5 away, and so it is not in range.
 *     The nanobot at 0,0,3 is distance 3 away, and so it is in range.
 *     The nanobot at 1,1,1 is distance 3 away, and so it is in range.
 *     The nanobot at 1,1,2 is distance 4 away, and so it is in range.
 *     The nanobot at 1,3,1 is distance 5 away, and so it is not in range.
 *     
 * In this example, in total, 7 nanobots are in range of the nanobot with the largest
 * signal radius.
 * 
 * Find the nanobot with the largest signal radius. How many nanobots are in range
 * of its signals?
 * 
 * To begin, get your puzzle input.
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

    //calculate01( getListProd(), true );

    System.exit( 0 );
  }

  private static void calculatePart01( String pString, boolean pKnzDebug )
  {
    List< String > converted_string_list = Arrays.stream( pString.split( ";" ) ).map( String::trim ).collect( Collectors.toList() );

    calculate01( converted_string_list, pKnzDebug );
  }

  private static void calculate01( List< String > pListInput, boolean pKnzDebug )
  {
    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );

    long result_part_01 = 0;
    long result_part_02 = 0;

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

    wl( "" );
    wl( "" );

    for ( Nanobot cur_bot : n_b_list )
    {
      wl( cur_bot.toString() );
    }

    wl( "" );
    wl( "" );

    Nanobot bot_a = n_b_list.get( 0 );

    //for ( Nanobot bot_a : n_b_list )
    {
      for ( Nanobot cur_bot : n_b_list )
      {
        long cur_distance = bot_a.getManhattenDistance( cur_bot );

        wl( String.format( "The nanobot nr %4d at %d,%d,%d is distance %d away", cur_bot.getBotNr(), cur_bot.getPosX(), cur_bot.getPosY(), cur_bot.getPosZ(), cur_distance ) );
      }
    }

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
    wl( "Result Part 2 " + result_part_02 );
    wl( "" );
  }

  private static class Nanobot
  {
    private int  bot_nr = 0;

    private long pos_x  = 0;

    private long pos_y  = 0;

    private long pos_z  = 0;

    private long radius = 0;

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
      return String.format( "Bot Nr %4d    x %12d   y %12d   z %12d   r %12d   ", bot_nr, pos_x, pos_y, pos_z, radius );
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