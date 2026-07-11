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
 * https://github.com/ea234/Advent_of_Code_2018/blob/main/src/de/ea234/aoc2018/day09/Day09_MarbleMania.java
 * 
  * 0 16 8 17 4 18 9 19 2 20 10 21 5 22 11 1 12 6 13 3 14 7 15 0 16 8 
 * 
 * 
 * Player  0  Score      0    
 * Player  1  Score      0    
 * Player  2  Score      0    
 * Player  3  Score      0    
 * Player  5  Score      0    
 * Player  6  Score      0    
 * Player  7  Score      0    
 * Player  8  Score      0    
 * Player  4  Score     27    23,4,
 * 
 *
 * --- Day 17: Spinlock ---
 * https://adventofcode.com/2017/day/17
 * 
 * https://www.reddit.com/r/adventofcode/comments/7kc0xw/2017_day_17_solutions/
 * 
 * https://github.com/ea234/Advent_of_Code_2017/blob/main/src/de/ea234/aoc2017/day17/Day17_Spinlock.java
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

    int cnt = 0;
    int cur_player_nr = -1;

    int cur_marble_nr = 0;

    CurMarble cur_marble0 = new CurMarble( 0 );

    CurMarble cur_marble = cur_marble0;

    while ( cur_marble_nr <= pValueLastMarble )
    {
      /*
       * ***********************************************************
       * PLAYER 
       * ***********************************************************
       */

      /*
       * Increase the current player index
       */
      cur_player_nr++;

      /*
       * If the current player index reaches the end of the 
       * player list, set the index to 0.
       */
      if ( cur_player_nr == player_list.size() )
      {
        cur_player_nr = 0;
      }

      /*
       * Get the player instance from the player list.
       */
      Player cur_player_inst = player_list.get( cur_player_nr );

      /*
       * ***********************************************************
       * MARBLE 
       * ***********************************************************
       */

      /*
       * Increase the current marble number
       */
      cur_marble_nr++;

      /*
       * Check the current marble number for a multiple of 23
       */
      if ( ( cur_marble_nr % 23 ) == 0 )
      {
        /*
         * If it is a multiple of 23, the number is added to the players score
         */
        cur_player_inst.addToScore( cur_marble_nr );

        wl( toString( cur_marble0, 25 ) );
        /*
         * Go 7 counter clockwise (=previous element) in the circular list
         */
        for ( int step_ins_count = 0; step_ins_count < 8; step_ins_count++ )
        {
          cur_marble = cur_marble.getPrev();
        }

        /*
         * Add that value to the players score
         */
        cur_player_inst.addToScore( cur_marble.getValue() );

        /*
         * Remove the current marble from the circular list
         */
        cur_marble.removeX();

        /*
         * Since the "next" reference is still valid, the next 
         * current marble is still get with the "getNext()" Method.
         */
        cur_marble = cur_marble.getNext();
      }
      else
      {
        /* 
         * If the marble is not a multiple of 23, 
         * place the marble into the list.
         * 
         * Into the circle between the marbles that are 1 and 2 marbles clockwise of the current marble.
         */

        cur_marble = cur_marble.getNext();
        cur_marble = cur_marble.getNext();

        cur_marble.insertX( new CurMarble( cur_marble_nr ) );
      }
    }

    wl( "" );
    wl( "" );

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

  private static String toString( CurMarble pMarbleStart, int pDebugCount )
  {
    StringBuilder str_result = new StringBuilder();

    str_result.append( pMarbleStart.getValue() );

    str_result.append( " " );

    for ( int element_nr = 0; element_nr < pDebugCount; element_nr++ )
    {
      pMarbleStart = pMarbleStart.getNext();

      str_result.append( pMarbleStart.getValue() );

      str_result.append( " " );
    }

    return str_result.toString();
  }

  private static class Player
  {
    private int           id            = 0;

    private int           score         = 0;

    private StringBuilder score_numbers = new StringBuilder();

    public Player( int pNr )
    {
      id = pNr;
    }

    public void addToScore( int pNumber )
    {
      score_numbers.append( pNumber );

      score_numbers.append( "," );

      score += pNumber;
    }

    public int getScore()
    {
      return score;
    }

    public String toString()
    {
      return String.format( "Player %2d  Score %6d    %s", id, score, score_numbers.toString() );
    }
  }

  private static class CurMarble
  {
    private CurMarble previous = null;

    private CurMarble next     = null;

    private int       value;

    public CurMarble( int pValue )
    {
      value = pValue;

      previous = this;

      next = this;
    }

    public void setPrev( CurMarble pPrev )
    {
      previous = pPrev;
    }

    public CurMarble getPrev()
    {
      return previous;
    }

    public void setNext( CurMarble pNext )
    {
      next = pNext;
    }

    public CurMarble getNext()
    {
      return next;
    }

    public int getValue()
    {
      return value;
    }

    public void insertX( CurMarble pInstanceToInsert )
    {
      CurMarble old_next_instance = next;

      next = pInstanceToInsert;

      pInstanceToInsert.setPrev( this );

      old_next_instance.setPrev( pInstanceToInsert );

      pInstanceToInsert.setNext( old_next_instance );
    }

    public void removeX()
    {
      /*
       *  previous -> this -> next
       *  
       *  previous -> next
       */

      /*
       * This instance previous instance must reference this next instance
       */
      previous.setNext( next );

      /*
       * This instance next instance must reference this previous instance
       */
      next.setPrev( previous );
    }
  }

  private static void wl( String pString ) // wl = short for "write log"
  {
    System.out.println( pString );
  }
}