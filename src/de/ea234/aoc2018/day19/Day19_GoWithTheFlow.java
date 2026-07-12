package de.ea234.aoc2018.day19;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * --- Day 19: Go With The Flow ---
 * https://adventofcode.com/2018/day/16
 *
 * 
 * Refers to:
 * --- Day 16: Chronal Classification ---
 * https://adventofcode.com/2018/day/16
 * 
 * https://www.reddit.com/r/adventofcode/comments/a6mf8a/2018_day_16_solutions/
 * 
 * https://github.com/ea234/Advent_of_Code_2018/blob/main/src/de/ea234/aoc2018/day16/Day16_ChronalClassification.java
 *
 * ------------------------------------------------------------------------------------------
 * REGISTER_IP = 0
 * 
 * ip=   0 [   0   0   0   0   0   0] seti 5 0 1      [   0   5   0   0   0   0]
 * ip=   1 [   1   5   0   0   0   0] seti 6 0 2      [   1   5   6   0   0   0]
 * ip=   2 [   2   5   6   0   0   0] addi 0 1 0      [   3   5   6   0   0   0]
 * ip=   4 [   4   5   6   0   0   0] setr 1 0 0      [   5   5   6   0   0   0]
 * ip=   6 [   6   5   6   0   0   0] seti 9 0 5      [   6   5   6   0   0   9]
 * 
 * PRG END
 * Register 0 = 7
 * 
 * ------------------------------------------------------------------------------------------
 * 
 * PRG END
 * Register 0 = 1836
 * 
 * 
 * </pre> 
 */
public class Day19_GoWithTheFlow
{
  private static int SET_BEFORE          = 0;

  private static int SET_AFTER           = 1;

  private static int SET_INSTRUCTIONS    = 2;

  private static int REGISTER_0          = 0;

  private static int REGISTER_IP         = 4;

  private static int INSTRUCTION_OPCODDE = 0;

  private static int INSTRUCTION_PARAM_A = 1;

  private static int INSTRUCTION_PARAM_B = 2;

  private static int INSTRUCTION_PARAM_C = 3;

  private static int BIT_MASK            = 0xFFFF;

  public static void main( String[] args )
  {
    String test_input = "";

    test_input += "#ip 0";
    test_input += ",seti 5 0 1";
    test_input += ",seti 6 0 2";
    test_input += ",addi 0 1 0";
    test_input += ",addr 1 2 3";
    test_input += ",setr 1 0 0";
    test_input += ",seti 8 0 4";
    test_input += ",seti 9 0 5";

    calculatePart01( test_input, 0, true );

    calculate01( getListProd(), 0, false );
    // Endless loop calculate01( getListProd(), 1, false );
  }

  private static void calculatePart01( String pString, int pStartValueRegister0, boolean pKnzDebug )
  {
    List< String > converted_string_list = Arrays.stream( pString.split( "," ) ).collect( Collectors.toList() );

    calculate01( converted_string_list, pStartValueRegister0, pKnzDebug );
  }

  private static void calculate01( List< String > pListInput, int pStartValueRegister0, boolean pKnzDebug )
  {
    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );

    /*
     * Read the register for the instruction pointer
     */
    REGISTER_IP = Integer.parseInt( pListInput.get( 0 ).substring( 3 ).trim() );

    if ( pKnzDebug )
    {
      wl( "REGISTER_IP = " + REGISTER_IP );
      wl( "" );
    }

    /*
     * Remove the first element from the list.
     */
    pListInput.remove( 0 );

    /*
     * Declare the registers
     * 1 = Before state
     * 2 = After state
     * 3 = Instruction
     */
    int[][] register = new int[ 3 ][ 6 ];

    /*
     * Set the instruction pointer to the first instruction 
     */
    register[ SET_AFTER ][ REGISTER_IP ] = 0;

    /*
     * Part 2:
     * Set start value for register 0 
     */
    register[ SET_AFTER ][ REGISTER_0 ] = pStartValueRegister0;

    /*
     * Loop until the instruction pointer is greater then the input list.
     */
    while ( register[ SET_AFTER ][ REGISTER_IP ] < pListInput.size() )
    {
      /*
       * Get the current instruction from the input list.
       */
      String cur_instruction = pListInput.get( register[ SET_AFTER ][ REGISTER_IP ] );

      /*
       * Split the current instruction and set the values to the Instruction-Set.
       */
      String[] value_vector = cur_instruction.split( " " );

      register[ SET_INSTRUCTIONS ][ INSTRUCTION_OPCODDE ] = 0; // not used
      register[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_A ] = Integer.parseInt( value_vector[ 1 ] );
      register[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_B ] = Integer.parseInt( value_vector[ 2 ] );
      register[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_C ] = Integer.parseInt( value_vector[ 3 ] );

      /*
       * Copy the values from the last AFTER-Set to the BEFORE-Set
       */
      copyValues( register, SET_AFTER, SET_BEFORE );

      /*
       * Excecute the instruction
       */
           if ( cur_instruction.startsWith( "addr" ) ) doADDR( register, SET_BEFORE, SET_AFTER );
      else if ( cur_instruction.startsWith( "addi" ) ) doADDI( register, SET_BEFORE, SET_AFTER );
      else if ( cur_instruction.startsWith( "mulr" ) ) doMULR( register, SET_BEFORE, SET_AFTER );
      else if ( cur_instruction.startsWith( "muli" ) ) doMULI( register, SET_BEFORE, SET_AFTER );
      else if ( cur_instruction.startsWith( "banr" ) ) doBANR( register, SET_BEFORE, SET_AFTER );
      else if ( cur_instruction.startsWith( "bani" ) ) doBANI( register, SET_BEFORE, SET_AFTER );
      else if ( cur_instruction.startsWith( "borr" ) ) doBORR( register, SET_BEFORE, SET_AFTER );
      else if ( cur_instruction.startsWith( "bori" ) ) doBORI( register, SET_BEFORE, SET_AFTER );
      else if ( cur_instruction.startsWith( "setr" ) ) doSETR( register, SET_BEFORE, SET_AFTER );
      else if ( cur_instruction.startsWith( "seti" ) ) doSETI( register, SET_BEFORE, SET_AFTER );
      else if ( cur_instruction.startsWith( "gtir" ) ) doGTIR( register, SET_BEFORE, SET_AFTER );
      else if ( cur_instruction.startsWith( "gtri" ) ) doGTRI( register, SET_BEFORE, SET_AFTER );
      else if ( cur_instruction.startsWith( "gtrr" ) ) doGTRR( register, SET_BEFORE, SET_AFTER );
      else if ( cur_instruction.startsWith( "eqir" ) ) doEQIR( register, SET_BEFORE, SET_AFTER );
      else if ( cur_instruction.startsWith( "eqri" ) ) doEQRI( register, SET_BEFORE, SET_AFTER );
      else if ( cur_instruction.startsWith( "eqrr" ) ) doEQRR( register, SET_BEFORE, SET_AFTER );

      /*
       * Do some debug stuff.
       */
      if ( pKnzDebug )
      {
        wl( String.format( "ip=%4d [%s] %-15s [%s]", register[ SET_BEFORE ][ REGISTER_IP ], getDebugStr( register, SET_BEFORE ), cur_instruction, getDebugStr( register, SET_AFTER ) ) );
      }

      /*
       * Increase the instruction pointer
       */
      register[ SET_AFTER ][ REGISTER_IP ]++;
    }

    wl( "" );
    wl( "PRG END" );
    wl( "Register 0 = " + register[ SET_AFTER ][ REGISTER_0 ] );
  }

  private static String getDebugStr( int[][] pRegister, int pSet )
  {
    StringBuilder str_result = new StringBuilder();

    for ( int cur_idx = 0; cur_idx < 6; cur_idx++ )
    {
      str_result.append( String.format( " %3d", pRegister[ pSet ][ cur_idx ] ) );
    }

    return str_result.toString();
  }

  private static void copyValues( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    for ( int cur_idx = 0; cur_idx < 6; cur_idx++ )
    {
      pRegister[ pSetOutput ][ cur_idx ] = pRegister[ pSetInput ][ cur_idx ];
    }
  }

  private static void doADDR( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    /*
     * addr (add register) stores into register C the result of adding register A and register B.
     */

    int value_a = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_A ] ];
    int value_b = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_B ] ];

    pRegister[ pSetOutput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_C ] ] = value_a + value_b;
  }

  private static void doADDI( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    /*
     * addi (add immediate) stores into register C the result of adding register A and value B.
     */

    int value_a = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_A ] ];
    int value_b = pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_B ];

    pRegister[ pSetOutput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_C ] ] = value_a + value_b;
  }

  private static void doMULR( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    /*
     * mulr (multiply register) stores into register C the result of multiplying register A and register B.
     */

    int value_a = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_A ] ];
    int value_b = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_B ] ];

    pRegister[ pSetOutput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_C ] ] = value_a * value_b;
  }

  private static void doMULI( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    /*
     * muli (multiply immediate) stores into register C the result of multiplying register A and value B.
     */

    int value_a = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_A ] ];
    int value_b = pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_B ];

    pRegister[ pSetOutput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_C ] ] = value_a * value_b;
  }

  private static void doBANR( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    /*
     * banr (bitwise AND register) stores into register C the result of the bitwise AND of register A and register B.
     */

    int value_a = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_A ] ];
    int value_b = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_B ] ];

    /*
     * Bitwise AND and only the 16 lowest bits
     */
    pRegister[ pSetOutput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_C ] ] = ( value_a & value_b ) & BIT_MASK;
  }

  private static void doBANI( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    /*
     * bani (bitwise AND immediate) stores into register C the result of the bitwise AND of register A and value B.
     */

    int value_a = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_A ] ];
    int value_b = pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_B ];

    pRegister[ pSetOutput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_C ] ] = ( value_a & value_b ) & BIT_MASK;
  }

  private static void doBORR( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    /*
     * borr (bitwise OR register) stores into register C the result of the bitwise OR of register A and register B.
     */

    int value_a = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_A ] ];
    int value_b = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_B ] ];

    pRegister[ pSetOutput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_C ] ] = ( value_a | value_b ) & BIT_MASK;
  }

  private static void doBORI( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    /*
     * bori (bitwise OR immediate) stores into register C the result of the bitwise OR of register A and value B.
     */

    int value_a = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_A ] ];
    int value_b = pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_B ];

    pRegister[ pSetOutput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_C ] ] = ( value_a | value_b ) & BIT_MASK;
  }

  private static void doSETR( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    /*
     * setr (set register) copies the contents of register A into register C. (Input B is ignored.)
     */

    int value_a = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_A ] ];

    pRegister[ pSetOutput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_C ] ] = value_a;
  }

  private static void doSETI( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    /*
     * seti (set immediate) stores value A into register C. (Input B is ignored.)
     */

    int value_a = pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_A ];

    pRegister[ pSetOutput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_C ] ] = value_a;
  }

  private static void doGTIR( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    /*
     * gtir (greater-than immediate/register) sets register C to 1 if value A is greater than register B. Otherwise, register C is set to 0.
     */

    int value_a = pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_A ];
    int value_b = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_B ] ];

    pRegister[ pSetOutput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_C ] ] = value_a > value_b ? 1 : 0;
  }

  private static void doGTRI( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    /*
     * gtri (greater-than register/immediate) sets register C to 1 if register A is greater than value B. Otherwise, register C is set to 0.
     */

    int value_a = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_A ] ];
    int value_b = pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_B ];

    pRegister[ pSetOutput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_C ] ] = value_a > value_b ? 1 : 0;
  }

  private static void doGTRR( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    /*
     * gtrr (greater-than register/register) sets register C to 1 if register A is greater than register B. Otherwise, register C is set to 0.
     */

    int value_a = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_A ] ];
    int value_b = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_B ] ];

    pRegister[ pSetOutput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_C ] ] = value_a > value_b ? 1 : 0;
  }

  private static void doEQIR( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    /*
     * eqir (equal immediate/register) sets register C to 1 if value A is equal to register B. Otherwise, register C is set to 0.
     */

    int value_a = pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_A ];
    int value_b = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_B ] ];

    pRegister[ pSetOutput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_C ] ] = value_a == value_b ? 1 : 0;
  }

  private static void doEQRI( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    /*
     * eqri (equal register/immediate) sets register C to 1 if register A is equal to value B. Otherwise, register C is set to 0.
     */

    int value_a = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_A ] ];
    int value_b = pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_B ];

    pRegister[ pSetOutput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_C ] ] = value_a == value_b ? 1 : 0;
  }

  private static void doEQRR( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    /*
     * eqrr (equal register/register) sets register C to 1 if register A is equal to register B. Otherwise, register C is set to 0.
     */

    int value_a = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_A ] ];
    int value_b = pRegister[ pSetInput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_B ] ];

    pRegister[ pSetOutput ][ pRegister[ SET_INSTRUCTIONS ][ INSTRUCTION_PARAM_C ] ] = value_a == value_b ? 1 : 0;
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2018__day19_input.txt";

    datei_input = "C:/Daten/00_Daten/advent_of_code_2018__day19_input.txt";

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