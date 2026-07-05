package de.ea234.aoc2018.day14;

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
 * --- Day 14: Chocolate Charts ---
 * https://adventofcode.com/2018/day/14
 * 
 * https://www.reddit.com/r/adventofcode/comments/a61ojp/2018_day_14_solutions/
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * Elf 1   Index 141188   Score 7
 * Elf 2   Index 326376   Score 8
 * 
 * score_board_index 327911
 * recipe_count      327911
 * 
 * 1 1 1 5 3 1 7 1 1 5 

 * </pre> 
 */
public class Day14_ChocolateCharts
{
  public static void main( String[] args )
  {
    calculate01( 327901 );

    System.exit( 0 );
  }

  public static void calculate01( int pRecipeMax )
  {
    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );

    String result_part_01 = null;
    int result_part_02 = -1;

    Elf elf_1 = new Elf( 1, 3, 0 );
    Elf elf_2 = new Elf( 2, 7, 1 );

    int[] score_board_vector = new int[ 10_000_000 ];

    int score_board_index = 2;

    int recipe_count = 2;
    int recipe_max   = pRecipeMax + 10;

    int[] digits = String.valueOf( pRecipeMax ).chars().map( c -> c - '0' ).toArray();

    score_board_vector[ 0 ] = 3;
    score_board_vector[ 1 ] = 7;

    int round_nr = 0;

    while ( round_nr < 7_000_000 )
    {
      /*
       * Calculate the new score value
       */
      int new_score = elf_1.getRecipeScore() + elf_2.getRecipeScore();

      if ( new_score < 10 )
      {
        /*
         * If the score value is less than 10, only 1 recipe will be created
         */
        score_board_vector[ score_board_index ] = new_score;

        score_board_index++;

        recipe_count++;

        if ( ( result_part_02 == -1 ) && ( checkDigits( score_board_vector, digits, score_board_index ) ) )
        {
          result_part_02 = recipe_count;
        }
      }
      else
      {
        /*
         * If the score value is greater than 9, two new recipes will be created
         */
        score_board_vector[ score_board_index ] = new_score / 10;

        score_board_index++;

        recipe_count++;

        if ( ( result_part_02 == -1 ) && ( checkDigits( score_board_vector, digits, score_board_index ) ) )
        {
          result_part_02 = recipe_count;
        }

        score_board_vector[ score_board_index ] = new_score % 10;

        score_board_index++;

        recipe_count++;

        if ( ( result_part_02 == -1 ) && ( checkDigits( score_board_vector, digits, score_board_index ) ) )
        {
          result_part_02 = recipe_count;
        }
      }

      //wl( Arrays.toString( score_board_vector ) );

      if ( recipe_count == recipe_max )
      {
        result_part_01 = getVString( score_board_vector, score_board_index - 10, score_board_index );
      }

      /*
       * --------------------------------------------------------
       */

      if ( ( result_part_01 != null ) && ( result_part_02 != -1 ) )
      {
        break;
      }

      elf_1.moveCurIndex( score_board_index );
      elf_2.moveCurIndex( score_board_index );

      elf_1.setRecipeScore( score_board_vector[ elf_1.getCurIndex() ] );
      elf_2.setRecipeScore( score_board_vector[ elf_2.getCurIndex() ] );

      round_nr++;
    }

    wl( "" );
    wl( elf_1.toString() );
    wl( elf_2.toString() );
    wl( "" );
    wl( "score_board_index " + score_board_index );
    wl( "recipe_count      " + recipe_count );
    wl( "round_nr          " + round_nr );
    wl( "" );
    wl( "result_part_01    " + result_part_01 );
    wl( "result_part_02    " + result_part_02 );
    wl( "" );
  }

  private static boolean checkDigits( int[] pVectorA, int[] pVectorB, int pVectorLength )
  {
    if ( pVectorLength < pVectorB.length )
    {
      return false;
    }

    int index_vector_a = pVectorLength - 1;

    for ( int index_vector_b = pVectorB.length - 1; index_vector_b >= 0; index_vector_b-- )
    {
      if ( pVectorA[ index_vector_a ] != pVectorB[ index_vector_b ] )
      {
        return false;
      }

      index_vector_a--;
    }

    return true;
  }

  private static String getVString( int[] pVector, int pIndexFrom, int pIndexTo )
  {
    StringBuilder x = new StringBuilder();

    for ( int cur_index = pIndexFrom; cur_index < pIndexTo; cur_index++ )
    {
      x.append( pVector[ cur_index ] );
    }

    return x.toString();
  }

  private static class Elf
  {
    private int elf_nr       = 0;

    private int recipe_score = 0;

    private int cur_index    = 0;

    public Elf( int pElfNr, int pRecipeScore, int pCurIndex )
    {
      recipe_score = pRecipeScore;

      cur_index = pCurIndex;

      elf_nr = pElfNr;
    }

    public int getRecipeScore()
    {
      return recipe_score;
    }

    public void setRecipeScore( int pRecipeScore )
    {
      recipe_score = pRecipeScore;
    }

    public int getCurIndex()
    {
      return cur_index;
    }

    public int moveCurIndex( int pVectorLength )
    {
      int move_amount = 1 + recipe_score;

      if ( move_amount > pVectorLength )
      {
        move_amount = move_amount % pVectorLength;
      }

      cur_index += move_amount;

      if ( cur_index >= pVectorLength )
      {
        cur_index -= pVectorLength;
      }

      return cur_index;
    }

    public String toString()
    {
      return String.format( "Elf %d   Index %5d   Score %d", elf_nr, cur_index, recipe_score );
    }
  }

  private static void wl( String pString ) // wl = short for "write log"
  {
    System.out.println( pString );
  }
}
