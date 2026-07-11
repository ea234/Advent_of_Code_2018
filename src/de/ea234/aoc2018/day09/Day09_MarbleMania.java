package de.ea234.aoc2018.day09;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * <pre>
 * 
 * --- Day 9: Marble Mania ---
 * https://adventofcode.com/2018/day/9
 * 
 * https://www.reddit.com/r/adventofcode/comments/a4i97s/2018_day_9_solutions/
 * 
 * 
 * Player  8  Score     25   Last Number     17
 * Player  0  Score     27   Last Number     18
 * Player  1  Score     30   Last Number     19
 * Player  2  Score     33   Last Number     20
 * Player  3  Score     36   Last Number     21
 * Player  4  Score     39   Last Number     22
 * Player  5  Score     42   Last Number     23
 * Player  6  Score     45   Last Number     24
 * Player  7  Score     48   Last Number     25
 * 
 * </pre> 
 */
public class Day09_MarbleMania
{
  public static void main( String[] args )
  {
    calculate01( 9, 25, true );

    //calculate01( 411, 71170, true );
  }

  private static void calculate01( int pNumberOfPlayers, int pValueLastMarble, boolean pKnzDebug )
  {
    int[][] circle_map = new int[ 2 ][ 2_000_000 ];

    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );
    wl( "" );

    /*
     * *******************************************************************************************************
     * Creating the list of players
     * *******************************************************************************************************
     */

    List< Player > player_list = new ArrayList< Player >();

    for ( int cur_idx = 0; cur_idx < pNumberOfPlayers; cur_idx++ )
    {
      player_list.add( new Player( cur_idx ) );
    }

    /*
     * *******************************************************************************************************
     * Doing the loop
     * *******************************************************************************************************
     */

    int cur_player_nr = -1;

    for ( int cur_marble_nr = 0; cur_marble_nr <= pValueLastMarble; cur_marble_nr++ )
    {
      cur_player_nr++;

      if ( cur_player_nr == player_list.size() )
      {
        cur_player_nr = 0;
      }

      Player cur_player_inst = player_list.get( cur_player_nr );

      cur_player_inst.setLastPlayedNumber( cur_marble_nr );
    }

    /*
     * *******************************************************************************************************
     * Sorting the list in order of score
     * *******************************************************************************************************
     */

    player_list.sort( Comparator.comparingInt( Player::getScore ) );

    for ( Player cur_player_inst : player_list )
    {
      wl( cur_player_inst.toString() );
    }

    wl( "" );
    wl( "" );
    wl( "" );
  }

  private static class Player
  {
    private int id          = 0;

    private int score       = 0;

    private int last_number = 0;

    public Player( int pNr )
    {
      id = pNr;
    }

    public void setLastPlayedNumber( int pNumber )
    {
      last_number = pNumber;

      score += pNumber;
    }

    public int getScore()
    {
      return score;
    }

    public String toString()
    {
      return String.format( "Player %2d  Score %6d   Last Number %6d", id, score, last_number );
    }
  }

  private static void wl( String pString ) // wl = short for "write log"
  {
    System.out.println( pString );
  }
}