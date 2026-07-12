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
 * Similar to:
 * 
 * --- Day 17: Spinlock ---
 * https://adventofcode.com/2017/day/17
 * 
 * https://www.reddit.com/r/adventofcode/comments/7kc0xw/2017_day_17_solutions/
 * 
 * https://github.com/ea234/Advent_of_Code_2017/blob/main/src/de/ea234/aoc2017/day17/Day17_Spinlock.java
 * 
 * 
 * ------------------------------------------------------------------------------------------
 * pNumberOfPlayers            9 
 * pValueLastMarble           25 
 * pExpectedValue             32 
 * 
 * Marble Number   1 | List 0 1 
 * Marble Number   2 | List 0 2 1 
 * Marble Number   3 | List 0 2 1 3 
 * Marble Number   4 | List 0 4 2 1 3 
 * Marble Number   5 | List 0 4 2 5 1 3 
 * Marble Number   6 | List 0 4 2 5 1 6 3 
 * Marble Number   7 | List 0 4 2 5 1 6 3 7 
 * Marble Number   8 | List 0 8 4 2 5 1 6 3 7 
 * Marble Number   9 | List 0 8 4 9 2 5 1 6 3 7 
 * Marble Number  10 | List 0 8 4 9 2 10 5 1 6 3 7 
 * Marble Number  11 | List 0 8 4 9 2 10 5 11 1 6 3 7 
 * Marble Number  12 | List 0 8 4 9 2 10 5 11 1 12 6 3 7 
 * Marble Number  13 | List 0 8 4 9 2 10 5 11 1 12 6 13 3 7 
 * Marble Number  14 | List 0 8 4 9 2 10 5 11 1 12 6 13 3 14 7 
 * Marble Number  15 | List 0 8 4 9 2 10 5 11 1 12 6 13 3 14 7 15 
 * Marble Number  16 | List 0 16 8 4 9 2 10 5 11 1 12 6 13 3 14 7 15 
 * Marble Number  17 | List 0 16 8 17 4 9 2 10 5 11 1 12 6 13 3 14 7 15 
 * Marble Number  18 | List 0 16 8 17 4 18 9 2 10 5 11 1 12 6 13 3 14 7 15 
 * Marble Number  19 | List 0 16 8 17 4 18 9 19 2 10 5 11 1 12 6 13 3 14 7 15 
 * Marble Number  20 | List 0 16 8 17 4 18 9 19 2 20 10 5 11 1 12 6 13 3 14 7 15 
 * Marble Number  21 | List 0 16 8 17 4 18 9 19 2 20 10 21 5 11 1 12 6 13 3 14 7 15 
 * Marble Number  22 | List 0 16 8 17 4 18 9 19 2 20 10 21 5 22 11 1 12 6 13 3 14 7 15 
 * Marble Number  24 | List 0 16 8 17 4 18 19 2 24 20 10 21 5 22 11 1 12 6 13 3 14 7 15 
 * Marble Number  25 | List 0 16 8 17 4 18 19 2 24 20 25 10 21 5 22 11 1 12 6 13 3 14 7 15 
 * Marble Number  26 | List 0 16 8 17 4 18 19 2 24 20 25 10 26 21 5 22 11 1 12 6 13 3 14 7 15 
 * 
 * high_score                 32 
 * 
 * 5 Top Player Results
 * Nr.  1  =  Player   4  Score     32    23,9,
 * Nr.  2  =  Player   0  Score      0    
 * Nr.  3  =  Player   1  Score      0    
 * Nr.  4  =  Player   2  Score      0    
 * Nr.  5  =  Player   3  Score      0    
 * 
 * ------------------------------------------------------------------------------------------
 * pNumberOfPlayers           10 
 * pValueLastMarble         1618 
 * pExpectedValue           8317 
 * 
 * high_score               8317 
 * 
 * 5 Top Player Results
 * Nr.  1  =  Player   9  Score   8317    230,99,460,197,690,128,920,74,1150,89,1380,594,1610,696,
 * Nr.  2  =  Player   6  Score   7932    207,37,437,189,667,287,897,385,1127,211,1357,250,1587,294,
 * Nr.  3  =  Player   3  Score   7896    184,33,414,77,644,279,874,377,1104,475,1334,247,1564,290,
 * Nr.  4  =  Player   0  Score   7738    161,66,391,73,621,117,851,369,1081,467,1311,565,1541,124,
 * Nr.  5  =  Player   7  Score   7552    138,58,368,156,598,252,828,64,1058,14,1288,557,1518,655,
 * 
 * ------------------------------------------------------------------------------------------
 * pNumberOfPlayers           13 
 * pValueLastMarble         7999 
 * pExpectedValue         146373 
 * 
 * high_score             146373 
 * 
 * 5 Top Player Results
 * Nr.  1  =  Player  11  Score 146373    207,37,506,95,805,343,1104,475,1403,606,1702,737,2001,374,2300,988,2599,1121,289...
 * Nr.  2  =  Player   1  Score 146287    184,33,483,88,782,336,1081,467,1380,594,1679,312,1978,71,2277,982,2576,1113,2875...
 * Nr.  3  =  Player   7  Score 142223    138,58,437,189,736,139,1035,3,1334,247,1633,704,1932,835,2231,962,2530,86,2829,5...
 * Nr.  4  =  Player   0  Score 141778    92,15,391,73,690,128,989,426,1288,557,1587,294,1886,352,2185,175,2484,1072,2783,...
 * Nr.  5  =  Player  10  Score 140652    115,50,414,77,713,10,1012,434,1311,565,1610,696,1909,356,2208,410,2507,1079,2806...
 * 
 * ------------------------------------------------------------------------------------------
 * pNumberOfPlayers           17 
 * pValueLastMarble         1104 
 * pExpectedValue           2764 
 * 
 * high_score               2764 
 * 
 * 5 Top Player Results
 * Nr.  1  =  Player  15  Score   2764    322,140,713,10,1104,475,
 * Nr.  2  =  Player   9  Score   2720    299,55,690,128,1081,467,
 * Nr.  3  =  Player   8  Score   2513    230,99,621,117,1012,434,
 * Nr.  4  =  Player   2  Score   2509    207,37,598,252,989,426,
 * Nr.  5  =  Player  13  Score   2422    184,33,575,246,966,418,
 * 
 * ------------------------------------------------------------------------------------------
 * pNumberOfPlayers           21 
 * pValueLastMarble         6111 
 * pExpectedValue          54718 
 * 
 * high_score              54718 
 * 
 * 5 Top Player Results
 * Nr.  1  =  Player   4  Score  54718    299,55,782,336,1265,101,1748,753,2231,962,2714,1170,3197,1383,3680,685,4163,1800...
 * Nr.  2  =  Player   2  Score  53563    276,45,759,328,1242,100,1725,745,2208,410,2691,1162,3174,591,3657,1579,4140,60,4...
 * Nr.  3  =  Player  15  Score  52876    184,33,667,287,1150,89,1633,704,2116,396,2599,1121,3082,1330,3565,1538,4048,1751...
 * Nr.  4  =  Player  13  Score  52769    161,66,644,279,1127,211,1610,696,2093,392,2576,1113,3059,569,3542,1530,4025,326,...
 * Nr.  5  =  Player   0  Score  52695    253,107,736,139,1219,524,1702,737,2185,175,2668,1154,3151,1356,3634,1571,4117,13...
 * 
 * ------------------------------------------------------------------------------------------
 * pNumberOfPlayers           30 
 * pValueLastMarble         5807 
 * pExpectedValue          37305 
 * 
 * high_score              37305 
 * 
 * 5 Top Player Results
 * Nr.  1  =  Player  19  Score  37305    230,99,920,74,1610,696,2300,988,2990,1293,3680,685,4370,1890,5060,2183,5750,2487...
 * Nr.  2  =  Player   5  Score  36891    276,45,966,418,1656,711,2346,1015,3036,566,3726,1612,4416,1906,5106,2209,5796,10...
 * Nr.  3  =  Player   3  Score  35719    184,33,874,377,1564,290,2254,974,2944,547,3634,1571,4324,803,5014,2168,5704,2460...
 * Nr.  4  =  Player  12  Score  35421    253,107,943,76,1633,704,2323,80,3013,1301,3703,693,4393,1898,5083,950,5773,2495,...
 * Nr.  5  =  Player  24  Score  35066    115,50,805,343,1495,647,2185,175,2875,1244,3565,1538,4255,1841,4945,919,5635,243...
 * 
 * ------------------------------------------------------------------------------------------
 * pNumberOfPlayers          411 
 * pValueLastMarble        71170 
 * pExpectedValue         425688 
 * 
 * high_score             425688 
 * 
 * 5 Top Player Results
 * Nr.  1  =  Player  58  Score 425688    4991,932,14444,2700,23897,4468,33350,14418,42803,18507,52256,22596,61709,26685,7...
 * Nr.  2  =  Player 262  Score 424639    4784,2066,14237,2660,23690,4428,33143,14327,42596,18417,52049,22506,61502,26595,...
 * Nr.  3  =  Player 170  Score 418988    4692,2029,14145,6114,23598,4410,33051,6178,42504,18375,51957,22465,61410,26554,7...
 * Nr.  4  =  Player 377  Score 417743    4899,2119,14352,2682,23805,4450,33258,6218,42711,18466,52164,22555,61617,26644,7...
 * Nr.  5  =  Player 354  Score 412853    4876,910,14329,2678,23782,10279,33235,14369,42688,18458,52141,22547,61594,26636,...
 * 
 * ------------------------------------------------------------------------------------------
 * pNumberOfPlayers          411 
 * pValueLastMarble      7117000 
 * pExpectedValue     3526561003 
 * 
 * high_score         3526561003 
 * 
 * 5 Top Player Results
 * Nr.  1  =  Player 240  Score 3526561003    8050,1504,17503,3273,26956,11654,36409,15743,45862,19832,55315,23921,64768,12110...
 * Nr.  2  =  Player 378  Score 3526263632    8188,3538,17641,3298,27094,5066,36547,15799,46000,19889,55453,23978,64906,28067,...
 * Nr.  3  =  Player 389  Score 3525805623    6555,2828,16008,559,25461,11008,34914,15097,44367,19186,53820,10063,63273,11831,...
 * Nr.  4  =  Player 194  Score 3525778519    8004,3460,17457,7549,26910,11634,36363,6798,45816,8566,55269,23895,64722,27985,7...
 * Nr.  5  =  Player 286  Score 3525574358    8096,3501,17549,7586,27002,5048,36455,6816,45908,19847,55361,23937,64814,28026,7...
 * 
 * </pre> 
 */
public class Day09_MarbleMania
{
  public static void main( String[] args )
  {
    calculate01( 9,    25,     32, true  );
    calculate01( 10, 1618,   8317, false );
    calculate01( 13, 7999, 146373, false );
    calculate01( 17, 1104,   2764, false );
    calculate01( 21, 6111,  54718, false );
    calculate01( 30, 5807,  37305, false );

    calculate01( 411, 71170,            425688, false );
    calculate01( 411, 71170 * 100, 3526561003l, false );
  }

  private static long calculate01( int pNumberOfPlayers, long pValueLastMarble, long pExpectedValue, boolean pKnzDebug )
  {
    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );
    wl( String.format( "pNumberOfPlayers %12d ", pNumberOfPlayers ) );
    wl( String.format( "pValueLastMarble %12d ", pValueLastMarble ) );
    wl( String.format( "pExpectedValue   %12d ", pExpectedValue   ) );

    if ( pKnzDebug )
    {
      wl( "" );
    }

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

    long cur_marble_nr = 0;

    Marble start_marble_0 = new Marble( 0 );

    Marble cur_marble     = start_marble_0;

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

        /*
         * Go 7 counter clockwise (=previous element) in the circular list
         */
        for ( int step_count = 0; step_count < 7; step_count++ )
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
         */

        /*
         * From the current marble, get the next marble in the list
         */
        cur_marble = cur_marble.getNext();

        /*
         * Insert a new marble, which is returned as new current marble
         */
        cur_marble = cur_marble.insertX( new Marble( cur_marble_nr ) );

        if ( pKnzDebug )
        {
          wl( String.format( "Marble Number %3d | List %s", cur_marble_nr, toString( start_marble_0, 30 ) ) );
        }
      }
    }

    /*
     * *******************************************************************************************************
     * Sorting the list in order of score
     * *******************************************************************************************************
     */

    player_list.sort( Comparator.comparingLong( Player::getScore ).reversed() );

    long high_score = player_list.get( 0 ).getScore();

    wl( "" );
    wl( String.format( "high_score       %12d ", high_score ) );
    wl( "" );
    wl( "5 Top Player Results" );

    for ( int idx = 0; idx < 5; idx++ )
    {
      wl( String.format( "Nr. %2d  =  %s", idx + 1, player_list.get( idx ).toString() ) );
    }

    return high_score;
  }

  private static String toString( Marble pMarbleStart, int pDebugCount )
  {
    StringBuilder str_result = new StringBuilder();

    str_result.append( pMarbleStart.getValue() );

    str_result.append( " " );

    Marble cur_marble = pMarbleStart;

    for ( int element_nr = 0; element_nr < pDebugCount; element_nr++ )
    {
      cur_marble = cur_marble.getNext();

      if ( cur_marble == pMarbleStart )
      {
        break;
      }

      str_result.append( cur_marble.getValue() );

      str_result.append( " " );
    }

    return str_result.toString();
  }

  private static class Player
  {
    private int  id    = 0;

    private long score = 0;

    private StringBuilder score_numbers = new StringBuilder();

    public Player( int pNr )
    {
      id = pNr;
    }

    public void addToScore( long pNumber )
    {
      score_numbers.append( pNumber );

      score_numbers.append( "," );

      score += pNumber;
    }

    public long getScore()
    {
      return score;
    }

    private String getScoreNumbers()
    {
      int min_length = 80;

      if ( score_numbers.length() < min_length )
      {
        return score_numbers.toString();
      }

      return score_numbers.substring( 0, min_length ) + "...";
    }

    public String toString()
    {
      return String.format( "Player %3d  Score %6d    %s", id, score, getScoreNumbers() );
    }
  }

  private static class Marble
  {
    private Marble previous = null;

    private Marble next     = null;

    private long   value;

    public Marble( long pValue )
    {
      value = pValue;

      previous = this;

      next = this;
    }

    public void setPrev( Marble pPrev )
    {
      previous = pPrev;
    }

    public Marble getPrev()
    {
      return previous;
    }

    public void setNext( Marble pNext )
    {
      next = pNext;
    }

    public Marble getNext()
    {
      return next;
    }

    public long getValue()
    {
      return value;
    }

    public Marble insertX( Marble pInstanceToInsert )
    {
      Marble old_next_instance = next;

      next = pInstanceToInsert;

      pInstanceToInsert.setPrev( this );

      old_next_instance.setPrev( pInstanceToInsert );

      pInstanceToInsert.setNext( old_next_instance );
      
      return pInstanceToInsert;
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