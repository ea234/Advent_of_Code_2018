package de.ea234.aoc2018.day16;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 * 
 * --- Day 16: Chronal Classification ---
 * https://adventofcode.com/2018/day/16
 * 
 * https://www.reddit.com/r/adventofcode/comments/a6mf8a/2018_day_16_solutions/
 * 
 * Your puzzle answer was 500.
 * 
 * 
 * Sample  285 - OpCode  0 - Instructions  2 - Before Set 0    2    0    1    0    After Set 1    2    3    1    0    Instructions Set 3    0    0    2    1 
 * Sample  290 - OpCode  0 - Instructions  2 - Before Set 0    0    3    0    3    After Set 1    0    3    3    3    Instructions Set 3    0    2    1    2 
 * Sample  291 - OpCode  0 - Instructions  2 - Before Set 0    2    1    1    0    After Set 1    3    1    1    0    Instructions Set 3    0    2    0    0 
 * Sample  309 - OpCode  0 - Instructions  2 - Before Set 0    2    1    0    3    After Set 1    3    1    0    3    Instructions Set 3    0    1    0    0 
 * Sample  314 - OpCode  0 - Instructions  2 - Before Set 0    3    1    1    1    After Set 1    3    1    3    1    Instructions Set 3    0    2    0    2 
 * Sample  323 - OpCode  0 - Instructions  2 - Before Set 0    2    3    1    1    After Set 1    2    3    1    3    Instructions Set 3    0    0    2    3 
 * Sample  324 - OpCode  0 - Instructions  2 - Before Set 0    1    2    2    1    After Set 1    1    2    2    3    Instructions Set 3    0    1    0    3 
 * Sample  336 - OpCode  0 - Instructions  2 - Before Set 0    1    2    2    3    After Set 1    3    2    2    3    Instructions Set 3    0    1    0    0 
 * Sample  344 - OpCode  0 - Instructions  2 - Before Set 0    0    3    0    2    After Set 1    2    3    0    2    Instructions Set 3    0    0    3    0 
 * Sample  347 - OpCode  0 - Instructions  2 - Before Set 0    2    0    2    1    After Set 1    2    0    2    1    Instructions Set 3    0    1    0    0 
 * Sample  423 - OpCode  0 - Instructions  2 - Before Set 0    0    0    3    3    After Set 1    0    3    3    3    Instructions Set 3    0    0    2    1 
 * Sample  437 - OpCode  0 - Instructions  2 - Before Set 0    0    1    1    2    After Set 1    0    1    1    1    Instructions Set 3    0    0    2    3 
 * Sample  443 - OpCode  0 - Instructions  2 - Before Set 0    1    2    3    3    After Set 1    1    2    3    3    Instructions Set 3    0    0    1    2 
 * Sample  457 - OpCode  0 - Instructions  2 - Before Set 0    2    1    1    2    After Set 1    2    1    3    2    Instructions Set 3    0    1    0    2 
 * Sample  477 - OpCode  0 - Instructions  2 - Before Set 0    3    3    0    2    After Set 1    3    3    0    2    Instructions Set 3    0    2    0    1 
 * Sample  491 - OpCode  0 - Instructions  2 - Before Set 0    1    3    1    3    After Set 1    1    3    1    3    Instructions Set 3    0    0    1    1 
 * Sample  505 - OpCode  0 - Instructions  2 - Before Set 0    1    2    2    1    After Set 1    1    2    2    3    Instructions Set 3    0    2    0    3 
 * Sample  515 - OpCode  0 - Instructions  2 - Before Set 0    0    3    2    1    After Set 1    0    3    3    1    Instructions Set 3    0    0    1    2 
 * Sample  556 - OpCode  0 - Instructions  2 - Before Set 0    2    0    2    2    After Set 1    2    0    2    2    Instructions Set 3    0    1    3    0 
 * Sample  560 - OpCode  0 - Instructions  2 - Before Set 0    3    1    0    0    After Set 1    3    1    0    3    Instructions Set 3    0    2    0    3 
 * Sample  594 - OpCode  0 - Instructions  2 - Before Set 0    1    0    2    2    After Set 1    1    3    2    2    Instructions Set 3    0    2    0    1 
 * Sample  647 - OpCode  0 - Instructions  2 - Before Set 0    0    3    1    2    After Set 1    0    2    1    2    Instructions Set 3    0    0    3    1 
 * Sample  648 - OpCode  0 - Instructions  2 - Before Set 0    0    3    1    3    After Set 1    3    3    1    3    Instructions Set 3    0    2    1    0 
 * Sample  661 - OpCode  0 - Instructions  2 - Before Set 0    2    2    1    1    After Set 1    3    2    1    1    Instructions Set 3    0    1    2    0 
 * Sample  681 - OpCode  0 - Instructions  2 - Before Set 0    0    2    2    0    After Set 1    0    2    2    0    Instructions Set 3    0    0    1    2 
 * Sample  296 - OpCode  1 - Instructions  4 - Before Set 0    2    0    2    2    After Set 1    2    0    2    4    Instructions Set 3    1    3    2    3 
 * Sample  311 - OpCode  1 - Instructions  4 - Before Set 0    1    0    2    2    After Set 1    4    0    2    2    Instructions Set 3    1    2    2    0 
 * Sample  312 - OpCode  1 - Instructions  4 - Before Set 0    2    3    2    3    After Set 1    2    3    4    3    Instructions Set 3    1    2    2    2 
 * Sample  318 - OpCode  1 - Instructions  4 - Before Set 0    0    3    2    0    After Set 1    0    3    2    4    Instructions Set 3    1    2    2    3 
 * Sample  348 - OpCode  1 - Instructions  4 - Before Set 0    0    3    2    3    After Set 1    0    3    4    3    Instructions Set 3    1    2    2    2 
 * Sample  359 - OpCode  1 - Instructions  4 - Before Set 0    0    2    2    0    After Set 1    0    2    4    0    Instructions Set 3    1    1    2    2 
 * Sample  379 - OpCode  1 - Instructions  4 - Before Set 0    1    3    2    1    After Set 1    4    3    2    1    Instructions Set 3    1    2    2    0 
 * Sample  383 - OpCode  1 - Instructions  4 - Before Set 0    3    2    2    0    After Set 1    3    2    4    0    Instructions Set 3    1    1    2    2 
 * Sample  455 - OpCode  1 - Instructions  4 - Before Set 0    3    1    2    0    After Set 1    3    4    2    0    Instructions Set 3    1    2    2    1 
 * Sample  485 - OpCode  1 - Instructions  4 - Before Set 0    2    3    2    0    After Set 1    4    3    2    0    Instructions Set 3    1    2    2    0 
 * Sample  516 - OpCode  1 - Instructions  4 - Before Set 0    3    2    2    2    After Set 1    3    4    2    2    Instructions Set 3    1    3    2    1 
 * Sample  541 - OpCode  1 - Instructions  4 - Before Set 0    2    2    2    1    After Set 1    4    2    2    1    Instructions Set 3    1    2    2    0 
 * Sample  544 - OpCode  1 - Instructions  4 - Before Set 0    2    1    2    3    After Set 1    4    1    2    3    Instructions Set 3    1    0    2    0 
 * Sample  581 - OpCode  1 - Instructions  4 - Before Set 0    3    3    2    0    After Set 1    3    4    2    0    Instructions Set 3    1    2    2    1 
 * Sample  623 - OpCode  1 - Instructions  4 - Before Set 0    1    0    2    3    After Set 1    1    0    4    3    Instructions Set 3    1    2    2    2 
 * Sample  660 - OpCode  1 - Instructions  4 - Before Set 0    3    2    2    2    After Set 1    3    4    2    2    Instructions Set 3    1    1    2    1 
 * Sample  667 - OpCode  1 - Instructions  4 - Before Set 0    1    3    2    2    After Set 1    1    3    2    4    Instructions Set 3    1    3    2    3 
 * Sample  677 - OpCode  1 - Instructions  4 - Before Set 0    2    2    2    1    After Set 1    2    4    2    1    Instructions Set 3    1    2    2    1 
 * Sample  679 - OpCode  1 - Instructions  4 - Before Set 0    3    2    2    2    After Set 1    3    2    2    4    Instructions Set 3    1    1    2    3 
 * Sample  683 - OpCode  1 - Instructions  4 - Before Set 0    0    1    2    0    After Set 1    4    1    2    0    Instructions Set 3    1    2    2    0 
 * Sample  694 - OpCode  1 - Instructions  4 - Before Set 0    0    2    2    2    After Set 1    4    2    2    2    Instructions Set 3    1    1    2    0 
 * Sample  310 - OpCode  2 - Instructions 10 - Before Set 0    3    1    1    3    After Set 1    1    1    1    3    Instructions Set 3    2    2    1    0 
 * Sample  326 - OpCode  2 - Instructions 10 - Before Set 0    1    1    1    0    After Set 1    1    1    1    0    Instructions Set 3    2    2    1    2 
 * Sample  360 - OpCode  2 - Instructions 10 - Before Set 0    0    1    1    1    After Set 1    1    1    1    1    Instructions Set 3    2    3    1    0 
 * Sample  382 - OpCode  2 - Instructions 10 - Before Set 0    3    1    1    1    After Set 1    3    1    1    1    Instructions Set 3    2    2    1    3 
 * Sample  402 - OpCode  2 - Instructions 10 - Before Set 0    1    1    1    2    After Set 1    1    1    1    2    Instructions Set 3    2    2    1    1 
 * Sample  414 - OpCode  2 - Instructions 10 - Before Set 0    3    1    1    2    After Set 1    3    1    1    2    Instructions Set 3    2    2    1    1 
 * Sample  417 - OpCode  2 - Instructions 10 - Before Set 0    1    1    1    2    After Set 1    1    1    1    1    Instructions Set 3    2    2    1    3 
 * Sample  424 - OpCode  2 - Instructions 10 - Before Set 0    0    1    3    1    After Set 1    1    1    3    1    Instructions Set 3    2    3    1    0 
 * Sample  446 - OpCode  2 - Instructions 10 - Before Set 0    3    1    2    1    After Set 1    3    1    1    1    Instructions Set 3    2    3    1    2 
 * Sample  480 - OpCode  2 - Instructions 10 - Before Set 0    1    1    1    1    After Set 1    1    1    1    1    Instructions Set 3    2    2    1    2 
 * Sample  489 - OpCode  2 - Instructions 10 - Before Set 0    3    1    0    1    After Set 1    3    1    0    1    Instructions Set 3    2    3    1    3 
 * Sample  495 - OpCode  2 - Instructions 10 - Before Set 0    2    1    0    1    After Set 1    1    1    0    1    Instructions Set 3    2    3    1    0 
 * Sample  504 - OpCode  2 - Instructions 10 - Before Set 0    0    1    1    2    After Set 1    0    1    1    2    Instructions Set 3    2    2    1    2 
 * Sample  507 - OpCode  2 - Instructions 10 - Before Set 0    0    1    1    2    After Set 1    0    1    1    1    Instructions Set 3    2    2    1    3 
 * Sample  513 - OpCode  2 - Instructions 10 - Before Set 0    0    1    1    1    After Set 1    0    1    1    1    Instructions Set 3    2    2    1    2 
 * Sample  527 - OpCode  2 - Instructions 10 - Before Set 0    2    1    0    1    After Set 1    2    1    0    1    Instructions Set 3    2    3    1    1 
 * Sample  528 - OpCode  2 - Instructions 10 - Before Set 0    2    1    1    1    After Set 1    2    1    1    1    Instructions Set 3    2    3    1    3 
 * Sample  564 - OpCode  2 - Instructions 10 - Before Set 0    0    1    1    3    After Set 1    0    1    1    3    Instructions Set 3    2    2    1    1 
 * Sample  573 - OpCode  2 - Instructions 10 - Before Set 0    1    1    1    3    After Set 1    1    1    1    3    Instructions Set 3    2    2    1    0 
 * Sample  622 - OpCode  2 - Instructions 10 - Before Set 0    2    1    1    3    After Set 1    2    1    1    3    Instructions Set 3    2    2    1    1 
 * Sample  638 - OpCode  2 - Instructions 10 - Before Set 0    0    1    0    1    After Set 1    1    1    0    1    Instructions Set 3    2    3    1    0 
 * Sample  672 - OpCode  2 - Instructions 10 - Before Set 0    2    1    1    0    After Set 1    2    1    1    0    Instructions Set 3    2    2    1    2 
 * Sample  294 - OpCode  3 - Instructions  2 - Before Set 0    0    3    0    2    After Set 1    0    3    0    4    Instructions Set 3    3    3    2    3 
 * Sample  345 - OpCode  3 - Instructions  2 - Before Set 0    1    1    3    2    After Set 1    1    1    4    2    Instructions Set 3    3    3    2    2 
 * Sample  346 - OpCode  3 - Instructions  2 - Before Set 0    1    2    0    0    After Set 1    1    2    0    4    Instructions Set 3    3    1    2    3 
 * Sample  353 - OpCode  3 - Instructions  2 - Before Set 0    2    2    0    1    After Set 1    2    2    4    1    Instructions Set 3    3    1    2    2 
 * Sample  363 - OpCode  3 - Instructions  2 - Before Set 0    2    2    3    0    After Set 1    2    4    3    0    Instructions Set 3    3    1    2    1 
 * Sample  364 - OpCode  3 - Instructions  2 - Before Set 0    2    2    3    3    After Set 1    2    2    3    4    Instructions Set 3    3    0    2    3 
 * Sample  390 - OpCode  3 - Instructions  2 - Before Set 0    2    0    3    3    After Set 1    2    0    4    3    Instructions Set 3    3    0    2    2 
 * Sample  406 - OpCode  3 - Instructions  2 - Before Set 0    1    2    1    1    After Set 1    1    2    4    1    Instructions Set 3    3    1    2    2 
 * Sample  407 - OpCode  3 - Instructions  2 - Before Set 0    2    3    0    2    After Set 1    4    3    0    2    Instructions Set 3    3    3    2    0 
 * Sample  432 - OpCode  3 - Instructions  2 - Before Set 0    3    1    1    2    After Set 1    4    1    1    2    Instructions Set 3    3    3    2    0 
 * Sample  450 - OpCode  3 - Instructions  2 - Before Set 0    2    2    0    3    After Set 1    2    2    4    3    Instructions Set 3    3    0    2    2 
 * Sample  454 - OpCode  3 - Instructions  2 - Before Set 0    2    2    1    2    After Set 1    4    2    1    2    Instructions Set 3    3    3    2    0 
 * Sample  486 - OpCode  3 - Instructions  2 - Before Set 0    3    2    1    0    After Set 1    3    4    1    0    Instructions Set 3    3    1    2    1 
 * Sample  512 - OpCode  3 - Instructions  2 - Before Set 0    0    2    0    2    After Set 1    0    2    4    2    Instructions Set 3    3    1    2    2 
 * Sample  518 - OpCode  3 - Instructions  2 - Before Set 0    2    0    3    2    After Set 1    2    0    3    4    Instructions Set 3    3    0    2    3 
 * Sample  547 - OpCode  3 - Instructions  2 - Before Set 0    2    0    0    1    After Set 1    2    0    4    1    Instructions Set 3    3    0    2    2 
 * Sample  568 - OpCode  3 - Instructions  2 - Before Set 0    0    1    1    2    After Set 1    0    1    4    2    Instructions Set 3    3    3    2    2 
 * Sample  570 - OpCode  3 - Instructions  2 - Before Set 0    2    0    1    3    After Set 1    2    0    4    3    Instructions Set 3    3    0    2    2 
 * Sample  575 - OpCode  3 - Instructions  2 - Before Set 0    3    2    0    2    After Set 1    3    2    4    2    Instructions Set 3    3    3    2    2 
 * Sample  576 - OpCode  3 - Instructions  2 - Before Set 0    1    2    0    3    After Set 1    4    2    0    3    Instructions Set 3    3    1    2    0 
 * Sample  627 - OpCode  3 - Instructions  2 - Before Set 0    0    1    3    2    After Set 1    0    4    3    2    Instructions Set 3    3    3    2    1 
 * Sample  634 - OpCode  3 - Instructions  2 - Before Set 0    1    2    3    0    After Set 1    1    4    3    0    Instructions Set 3    3    1    2    1 
 * Sample  642 - OpCode  3 - Instructions  2 - Before Set 0    3    2    3    2    After Set 1    4    2    3    2    Instructions Set 3    3    1    2    0 
 * Sample  657 - OpCode  3 - Instructions  2 - Before Set 0    2    2    0    1    After Set 1    2    2    4    1    Instructions Set 3    3    0    2    2 
 * Sample  658 - OpCode  3 - Instructions  2 - Before Set 0    1    2    0    1    After Set 1    1    2    0    4    Instructions Set 3    3    1    2    3 
 * Sample  665 - OpCode  3 - Instructions  2 - Before Set 0    2    2    0    3    After Set 1    2    2    0    4    Instructions Set 3    3    1    2    3 
 * Sample  671 - OpCode  3 - Instructions  2 - Before Set 0    3    2    0    2    After Set 1    4    2    0    2    Instructions Set 3    3    3    2    0 
 * Sample  325 - OpCode  4 - Instructions  6 - Before Set 0    1    2    2    1    After Set 1    1    1    2    1    Instructions Set 3    4    3    1    1 
 * Sample  350 - OpCode  4 - Instructions  6 - Before Set 0    3    2    3    1    After Set 1    1    2    3    1    Instructions Set 3    4    3    1    0 
 * Sample  358 - OpCode  4 - Instructions  6 - Before Set 0    2    3    1    1    After Set 1    2    3    1    1    Instructions Set 3    4    2    1    2 
 * Sample  365 - OpCode  4 - Instructions  6 - Before Set 0    3    2    0    1    After Set 1    3    2    0    1    Instructions Set 3    4    3    1    3 
 * Sample  376 - OpCode  4 - Instructions  6 - Before Set 0    0    3    1    1    After Set 1    0    3    1    1    Instructions Set 3    4    2    1    2 
 * Sample  396 - OpCode  4 - Instructions  6 - Before Set 0    3    2    3    1    After Set 1    3    2    1    1    Instructions Set 3    4    3    1    2 
 * Sample  422 - OpCode  4 - Instructions  6 - Before Set 0    1    3    0    2    After Set 1    1    3    0    2    Instructions Set 3    4    0    1    0 
 * Sample  448 - OpCode  4 - Instructions  6 - Before Set 0    1    3    0    3    After Set 1    1    3    0    3    Instructions Set 3    4    0    1    0 
 * Sample  470 - OpCode  4 - Instructions  6 - Before Set 0    3    3    1    1    After Set 1    3    1    1    1    Instructions Set 3    4    2    1    1 
 * Sample  497 - OpCode  4 - Instructions  6 - Before Set 0    3    3    1    1    After Set 1    1    3    1    1    Instructions Set 3    4    2    1    0 
 * Sample  501 - OpCode  4 - Instructions  6 - Before Set 0    2    3    1    1    After Set 1    2    1    1    1    Instructions Set 3    4    2    1    1 
 * Sample  503 - OpCode  4 - Instructions  6 - Before Set 0    1    3    0    0    After Set 1    1    3    0    0    Instructions Set 3    4    0    1    0 
 * Sample  514 - OpCode  4 - Instructions  6 - Before Set 0    1    3    0    3    After Set 1    1    1    0    3    Instructions Set 3    4    0    1    1 
 * Sample  524 - OpCode  4 - Instructions  6 - Before Set 0    0    3    1    2    After Set 1    0    1    1    2    Instructions Set 3    4    2    1    1 
 * Sample  546 - OpCode  4 - Instructions  6 - Before Set 0    1    3    1    3    After Set 1    1    1    1    3    Instructions Set 3    4    2    1    1 
 * Sample  561 - OpCode  4 - Instructions  6 - Before Set 0    1    3    2    3    After Set 1    1    1    2    3    Instructions Set 3    4    0    1    1 
 * Sample  565 - OpCode  4 - Instructions  6 - Before Set 0    2    2    1    1    After Set 1    2    2    1    1    Instructions Set 3    4    3    1    2 
 * Sample  577 - OpCode  4 - Instructions  6 - Before Set 0    1    2    3    1    After Set 1    1    2    1    1    Instructions Set 3    4    3    1    2 
 * Sample  600 - OpCode  4 - Instructions  6 - Before Set 0    1    3    1    2    After Set 1    1    3    1    2    Instructions Set 3    4    0    1    0 
 * Sample  610 - OpCode  4 - Instructions  6 - Before Set 0    2    3    1    0    After Set 1    2    3    1    0    Instructions Set 3    4    2    1    2 
 * Sample  613 - OpCode  4 - Instructions  6 - Before Set 0    0    2    1    1    After Set 1    0    2    1    1    Instructions Set 3    4    3    1    2 
 * Sample  650 - OpCode  4 - Instructions  6 - Before Set 0    0    3    1    1    After Set 1    0    3    1    1    Instructions Set 3    4    2    1    3 
 * Sample  659 - OpCode  4 - Instructions  6 - Before Set 0    0    2    0    1    After Set 1    0    1    0    1    Instructions Set 3    4    3    1    1 
 * Sample  662 - OpCode  4 - Instructions  6 - Before Set 0    1    3    3    3    After Set 1    1    3    3    1    Instructions Set 3    4    0    1    3 
 * Sample  289 - OpCode  5 - Instructions 12 - Before Set 0    3    0    2    2    After Set 1    3    0    0    2    Instructions Set 3    5    1    0    2 
 * Sample  321 - OpCode  5 - Instructions 12 - Before Set 0    3    0    2    0    After Set 1    3    0    0    0    Instructions Set 3    5    1    0    2 
 * Sample  331 - OpCode  5 - Instructions 12 - Before Set 0    3    1    0    2    After Set 1    3    0    0    2    Instructions Set 3    5    2    0    1 
 * Sample  374 - OpCode  5 - Instructions 12 - Before Set 0    0    1    2    1    After Set 1    0    0    2    1    Instructions Set 3    5    0    3    1 
 * Sample  377 - OpCode  5 - Instructions 12 - Before Set 0    3    0    0    0    After Set 1    0    0    0    0    Instructions Set 3    5    1    0    0 
 * Sample  378 - OpCode  5 - Instructions 12 - Before Set 0    2    0    0    3    After Set 1    2    0    0    3    Instructions Set 3    5    1    0    1 
 * Sample  401 - OpCode  5 - Instructions 12 - Before Set 0    0    3    2    0    After Set 1    0    3    0    0    Instructions Set 3    5    0    1    2 
 * Sample  433 - OpCode  5 - Instructions 12 - Before Set 0    0    3    1    1    After Set 1    0    3    1    0    Instructions Set 3    5    0    3    3 
 * Sample  447 - OpCode  5 - Instructions 12 - Before Set 0    0    2    2    0    After Set 1    0    2    2    0    Instructions Set 3    5    0    2    3 
 * Sample  464 - OpCode  5 - Instructions 12 - Before Set 0    3    3    0    3    After Set 1    3    3    0    3    Instructions Set 3    5    2    0    2 
 * Sample  529 - OpCode  5 - Instructions 12 - Before Set 0    0    0    0    0    After Set 1    0    0    0    0    Instructions Set 3    5    3    0    0 
 * Sample  536 - OpCode  5 - Instructions 12 - Before Set 0    3    0    0    2    After Set 1    3    0    0    2    Instructions Set 3    5    2    0    2 
 * Sample  549 - OpCode  5 - Instructions 12 - Before Set 0    0    1    3    1    After Set 1    0    1    3    1    Instructions Set 3    5    0    1    0 
 * Sample  580 - OpCode  5 - Instructions 12 - Before Set 0    0    3    1    3    After Set 1    0    3    1    0    Instructions Set 3    5    0    3    3 
 * Sample  587 - OpCode  5 - Instructions 12 - Before Set 0    0    3    2    1    After Set 1    0    3    2    1    Instructions Set 3    5    0    2    0 
 * Sample  597 - OpCode  5 - Instructions 12 - Before Set 0    0    2    3    3    After Set 1    0    2    0    3    Instructions Set 3    5    0    3    2 
 * Sample  626 - OpCode  5 - Instructions 12 - Before Set 0    0    2    3    3    After Set 1    0    0    3    3    Instructions Set 3    5    0    2    1 
 * Sample  643 - OpCode  5 - Instructions 12 - Before Set 0    0    2    3    3    After Set 1    0    2    3    3    Instructions Set 3    5    0    3    0 
 * Sample  644 - OpCode  5 - Instructions 12 - Before Set 0    3    3    0    1    After Set 1    3    0    0    1    Instructions Set 3    5    2    0    1 
 * Sample  653 - OpCode  5 - Instructions 12 - Before Set 0    0    3    0    0    After Set 1    0    3    0    0    Instructions Set 3    5    2    0    2 
 * Sample  682 - OpCode  5 - Instructions 12 - Before Set 0    0    0    0    0    After Set 1    0    0    0    0    Instructions Set 3    5    1    0    1 
 * Sample  690 - OpCode  5 - Instructions 12 - Before Set 0    3    3    0    2    After Set 1    3    0    0    2    Instructions Set 3    5    2    0    1 
 * Sample  293 - OpCode  6 - Instructions  9 - Before Set 0    1    3    2    1    After Set 1    1    3    2    1    Instructions Set 3    6    3    0    3 
 * Sample  297 - OpCode  6 - Instructions  9 - Before Set 0    1    3    0    1    After Set 1    1    3    0    1    Instructions Set 3    6    3    0    3 
 * Sample  300 - OpCode  6 - Instructions  9 - Before Set 0    1    1    3    1    After Set 1    1    1    3    1    Instructions Set 3    6    3    0    0 
 * Sample  301 - OpCode  6 - Instructions  9 - Before Set 0    1    0    2    1    After Set 1    1    1    2    1    Instructions Set 3    6    3    0    1 
 * Sample  327 - OpCode  6 - Instructions  9 - Before Set 0    1    0    1    1    After Set 1    1    0    1    1    Instructions Set 3    6    2    0    0 
 * Sample  333 - OpCode  6 - Instructions  9 - Before Set 0    1    1    1    1    After Set 1    1    1    1    1    Instructions Set 3    6    3    0    2 
 * Sample  354 - OpCode  6 - Instructions  9 - Before Set 0    1    2    1    3    After Set 1    1    1    1    3    Instructions Set 3    6    2    0    1 
 * Sample  367 - OpCode  6 - Instructions  9 - Before Set 0    1    1    2    1    After Set 1    1    1    1    1    Instructions Set 3    6    3    0    2 
 * Sample  370 - OpCode  6 - Instructions  9 - Before Set 0    1    2    1    0    After Set 1    1    2    1    0    Instructions Set 3    6    2    0    0 
 * Sample  381 - OpCode  6 - Instructions  9 - Before Set 0    1    2    1    1    After Set 1    1    2    1    1    Instructions Set 3    6    2    0    2 
 * Sample  385 - OpCode  6 - Instructions  9 - Before Set 0    1    1    3    1    After Set 1    1    1    3    1    Instructions Set 3    6    3    0    1 
 * Sample  387 - OpCode  6 - Instructions  9 - Before Set 0    1    2    1    0    After Set 1    1    2    1    0    Instructions Set 3    6    2    0    2 
 * Sample  392 - OpCode  6 - Instructions  9 - Before Set 0    1    3    1    3    After Set 1    1    1    1    3    Instructions Set 3    6    2    0    1 
 * Sample  415 - OpCode  6 - Instructions  9 - Before Set 0    1    0    0    1    After Set 1    1    0    1    1    Instructions Set 3    6    3    0    2 
 * Sample  416 - OpCode  6 - Instructions  9 - Before Set 0    1    0    1    2    After Set 1    1    0    1    2    Instructions Set 3    6    2    0    2 
 * Sample  418 - OpCode  6 - Instructions  9 - Before Set 0    1    1    1    1    After Set 1    1    1    1    1    Instructions Set 3    6    2    0    1 
 * Sample  426 - OpCode  6 - Instructions  9 - Before Set 0    1    2    1    0    After Set 1    1    1    1    0    Instructions Set 3    6    2    0    1 
 * Sample  436 - OpCode  6 - Instructions  9 - Before Set 0    1    0    1    1    After Set 1    1    0    1    1    Instructions Set 3    6    3    0    0 
 * Sample  438 - OpCode  6 - Instructions  9 - Before Set 0    1    1    3    1    After Set 1    1    1    3    1    Instructions Set 3    6    3    0    3 
 * Sample  441 - OpCode  6 - Instructions  9 - Before Set 0    1    2    1    1    After Set 1    1    2    1    1    Instructions Set 3    6    3    0    0 
 * Sample  452 - OpCode  6 - Instructions  9 - Before Set 0    1    3    1    2    After Set 1    1    3    1    1    Instructions Set 3    6    2    0    3 
 * Sample  461 - OpCode  6 - Instructions  9 - Before Set 0    1    2    1    3    After Set 1    1    2    1    3    Instructions Set 3    6    2    0    0 
 * Sample  462 - OpCode  6 - Instructions  9 - Before Set 0    1    0    1    3    After Set 1    1    0    1    1    Instructions Set 3    6    2    0    3 
 * Sample  496 - OpCode  6 - Instructions  9 - Before Set 0    1    0    1    1    After Set 1    1    1    1    1    Instructions Set 3    6    3    0    1 
 * Sample  509 - OpCode  6 - Instructions  9 - Before Set 0    1    3    1    1    After Set 1    1    3    1    1    Instructions Set 3    6    2    0    0 
 * Sample  521 - OpCode  6 - Instructions  9 - Before Set 0    1    0    1    2    After Set 1    1    0    1    2    Instructions Set 3    6    2    0    0 
 * Sample  532 - OpCode  6 - Instructions  9 - Before Set 0    1    1    1    1    After Set 1    1    1    1    1    Instructions Set 3    6    3    0    3 
 * Sample  555 - OpCode  6 - Instructions  9 - Before Set 0    1    2    1    0    After Set 1    1    2    1    1    Instructions Set 3    6    2    0    3 
 * Sample  572 - OpCode  6 - Instructions  9 - Before Set 0    1    1    1    0    After Set 1    1    1    1    0    Instructions Set 3    6    2    0    0 
 * Sample  592 - OpCode  6 - Instructions  9 - Before Set 0    1    2    0    1    After Set 1    1    2    1    1    Instructions Set 3    6    3    0    2 
 * Sample  603 - OpCode  6 - Instructions  9 - Before Set 0    1    3    3    1    After Set 1    1    3    1    1    Instructions Set 3    6    3    0    2 
 * Sample  609 - OpCode  6 - Instructions  9 - Before Set 0    1    0    1    2    After Set 1    1    1    1    2    Instructions Set 3    6    2    0    1 
 * Sample  617 - OpCode  6 - Instructions  9 - Before Set 0    1    1    1    3    After Set 1    1    1    1    3    Instructions Set 3    6    2    0    0 
 * Sample  618 - OpCode  6 - Instructions  9 - Before Set 0    1    3    1    3    After Set 1    1    3    1    3    Instructions Set 3    6    2    0    2 
 * Sample  633 - OpCode  6 - Instructions  9 - Before Set 0    1    1    1    1    After Set 1    1    1    1    1    Instructions Set 3    6    2    0    3 
 * Sample  645 - OpCode  6 - Instructions  9 - Before Set 0    1    1    1    0    After Set 1    1    1    1    0    Instructions Set 3    6    2    0    2 
 * Sample  646 - OpCode  6 - Instructions  9 - Before Set 0    1    3    1    0    After Set 1    1    1    1    0    Instructions Set 3    6    2    0    1 
 * Sample  649 - OpCode  6 - Instructions  9 - Before Set 0    1    2    3    1    After Set 1    1    1    3    1    Instructions Set 3    6    3    0    1 
 * Sample  680 - OpCode  6 - Instructions  9 - Before Set 0    1    3    2    1    After Set 1    1    1    2    1    Instructions Set 3    6    3    0    1 
 * Sample  691 - OpCode  6 - Instructions  9 - Before Set 0    1    1    1    1    After Set 1    1    1    1    1    Instructions Set 3    6    3    0    1 
 * Sample  288 - OpCode  7 - Instructions  2 - Before Set 0    3    3    0    3    After Set 1    9    3    0    3    Instructions Set 3    7    0    3    0 
 * Sample  338 - OpCode  7 - Instructions  2 - Before Set 0    1    2    2    3    After Set 1    1    2    2    2    Instructions Set 3    7    0    1    3 
 * Sample  349 - OpCode  7 - Instructions  2 - Before Set 0    2    1    2    0    After Set 1    2    1    2    0    Instructions Set 3    7    1    2    2 
 * Sample  362 - OpCode  7 - Instructions  2 - Before Set 0    1    2    2    1    After Set 1    1    2    2    1    Instructions Set 3    7    3    1    1 
 * Sample  388 - OpCode  7 - Instructions  2 - Before Set 0    3    0    2    1    After Set 1    6    0    2    1    Instructions Set 3    7    0    2    0 
 * Sample  420 - OpCode  7 - Instructions  2 - Before Set 0    3    1    1    3    After Set 1    3    1    9    3    Instructions Set 3    7    3    3    2 
 * Sample  456 - OpCode  7 - Instructions  2 - Before Set 0    1    2    2    3    After Set 1    1    2    2    6    Instructions Set 3    7    1    3    3 
 * Sample  476 - OpCode  7 - Instructions  2 - Before Set 0    1    3    2    0    After Set 1    1    6    2    0    Instructions Set 3    7    1    2    1 
 * Sample  508 - OpCode  7 - Instructions  2 - Before Set 0    1    1    0    3    After Set 1    1    1    0    9    Instructions Set 3    7    3    3    3 
 * Sample  559 - OpCode  7 - Instructions  2 - Before Set 0    1    3    2    3    After Set 1    1    3    2    6    Instructions Set 3    7    2    3    3 
 * Sample  574 - OpCode  7 - Instructions  2 - Before Set 0    1    2    2    1    After Set 1    1    2    2    2    Instructions Set 3    7    0    2    3 
 * Sample  629 - OpCode  7 - Instructions  2 - Before Set 0    3    3    3    2    After Set 1    3    3    3    6    Instructions Set 3    7    2    3    3 
 * Sample  655 - OpCode  7 - Instructions  2 - Before Set 0    2    2    3    3    After Set 1    2    6    3    3    Instructions Set 3    7    1    3    1 
 * Sample  656 - OpCode  7 - Instructions  2 - Before Set 0    1    2    2    3    After Set 1    2    2    2    3    Instructions Set 3    7    0    1    0 
 * Sample  669 - OpCode  7 - Instructions  2 - Before Set 0    3    3    2    1    After Set 1    3    3    6    1    Instructions Set 3    7    0    2    2 
 * Sample  687 - OpCode  7 - Instructions  2 - Before Set 0    2    3    0    3    After Set 1    2    3    6    3    Instructions Set 3    7    0    3    2 
 * Sample  693 - OpCode  7 - Instructions  2 - Before Set 0    2    1    2    2    After Set 1    2    1    2    2    Instructions Set 3    7    1    2    0 
 * Sample  299 - OpCode  8 - Instructions  6 - Before Set 0    1    0    0    3    After Set 1    1    3    0    3    Instructions Set 3    8    3    3    1 
 * Sample  308 - OpCode  8 - Instructions  6 - Before Set 0    0    3    2    2    After Set 1    0    2    2    2    Instructions Set 3    8    2    0    1 
 * Sample  316 - OpCode  8 - Instructions  6 - Before Set 0    1    3    3    3    After Set 1    1    3    3    3    Instructions Set 3    8    3    3    2 
 * Sample  330 - OpCode  8 - Instructions  6 - Before Set 0    3    3    0    3    After Set 1    3    3    0    3    Instructions Set 3    8    3    3    0 
 * Sample  335 - OpCode  8 - Instructions  6 - Before Set 0    0    2    0    3    After Set 1    0    3    0    3    Instructions Set 3    8    3    0    1 
 * Sample  340 - OpCode  8 - Instructions  6 - Before Set 0    0    1    3    3    After Set 1    0    1    3    3    Instructions Set 3    8    3    1    3 
 * Sample  342 - OpCode  8 - Instructions  6 - Before Set 0    0    0    2    1    After Set 1    2    0    2    1    Instructions Set 3    8    2    2    0 
 * Sample  372 - OpCode  8 - Instructions  6 - Before Set 0    0    1    0    3    After Set 1    0    3    0    3    Instructions Set 3    8    3    0    1 
 * Sample  380 - OpCode  8 - Instructions  6 - Before Set 0    2    3    2    3    After Set 1    2    3    2    3    Instructions Set 3    8    2    0    0 
 * Sample  384 - OpCode  8 - Instructions  6 - Before Set 0    2    0    3    3    After Set 1    2    3    3    3    Instructions Set 3    8    3    3    1 
 * Sample  397 - OpCode  8 - Instructions  6 - Before Set 0    0    3    2    0    After Set 1    0    3    2    0    Instructions Set 3    8    2    0    2 
 * Sample  419 - OpCode  8 - Instructions  6 - Before Set 0    2    1    2    3    After Set 1    2    1    2    3    Instructions Set 3    8    3    1    3 
 * Sample  425 - OpCode  8 - Instructions  6 - Before Set 0    3    1    1    3    After Set 1    3    1    3    3    Instructions Set 3    8    3    0    2 
 * Sample  429 - OpCode  8 - Instructions  6 - Before Set 0    0    0    2    3    After Set 1    0    0    2    2    Instructions Set 3    8    2    0    3 
 * Sample  435 - OpCode  8 - Instructions  6 - Before Set 0    1    2    0    3    After Set 1    3    2    0    3    Instructions Set 3    8    3    3    0 
 * Sample  439 - OpCode  8 - Instructions  6 - Before Set 0    1    1    2    3    After Set 1    1    1    2    3    Instructions Set 3    8    2    2    2 
 * Sample  451 - OpCode  8 - Instructions  6 - Before Set 0    2    1    2    2    After Set 1    2    1    2    2    Instructions Set 3    8    2    0    3 
 * Sample  466 - OpCode  8 - Instructions  6 - Before Set 0    3    0    0    3    After Set 1    3    0    0    3    Instructions Set 3    8    3    3    3 
 * Sample  472 - OpCode  8 - Instructions  6 - Before Set 0    1    3    2    0    After Set 1    1    3    2    2    Instructions Set 3    8    2    2    3 
 * Sample  488 - OpCode  8 - Instructions  6 - Before Set 0    2    0    1    3    After Set 1    2    0    1    3    Instructions Set 3    8    3    3    3 
 * Sample  510 - OpCode  8 - Instructions  6 - Before Set 0    3    1    3    3    After Set 1    3    1    3    3    Instructions Set 3    8    3    3    0 
 * Sample  517 - OpCode  8 - Instructions  6 - Before Set 0    0    3    2    3    After Set 1    0    3    2    2    Instructions Set 3    8    2    2    3 
 * Sample  534 - OpCode  8 - Instructions  6 - Before Set 0    1    0    3    3    After Set 1    1    0    3    3    Instructions Set 3    8    3    3    2 
 * Sample  539 - OpCode  8 - Instructions  6 - Before Set 0    3    3    3    3    After Set 1    3    3    3    3    Instructions Set 3    8    3    1    3 
 * Sample  540 - OpCode  8 - Instructions  6 - Before Set 0    0    0    2    0    After Set 1    2    0    2    0    Instructions Set 3    8    2    0    0 
 * Sample  550 - OpCode  8 - Instructions  6 - Before Set 0    0    3    1    3    After Set 1    3    3    1    3    Instructions Set 3    8    3    0    0 
 * Sample  557 - OpCode  8 - Instructions  6 - Before Set 0    0    1    2    1    After Set 1    2    1    2    1    Instructions Set 3    8    2    2    0 
 * Sample  563 - OpCode  8 - Instructions  6 - Before Set 0    3    1    2    3    After Set 1    3    1    3    3    Instructions Set 3    8    3    3    2 
 * Sample  589 - OpCode  8 - Instructions  6 - Before Set 0    0    2    2    3    After Set 1    0    2    2    3    Instructions Set 3    8    3    0    3 
 * Sample  596 - OpCode  8 - Instructions  6 - Before Set 0    2    2    3    3    After Set 1    2    3    3    3    Instructions Set 3    8    3    3    1 
 * Sample  598 - OpCode  8 - Instructions  6 - Before Set 0    0    1    2    2    After Set 1    2    1    2    2    Instructions Set 3    8    2    2    0 
 * Sample  599 - OpCode  8 - Instructions  6 - Before Set 0    2    2    2    2    After Set 1    2    2    2    2    Instructions Set 3    8    2    2    3 
 * Sample  606 - OpCode  8 - Instructions  6 - Before Set 0    0    0    3    3    After Set 1    0    0    3    3    Instructions Set 3    8    3    3    2 
 * Sample  612 - OpCode  8 - Instructions  6 - Before Set 0    2    3    2    3    After Set 1    3    3    2    3    Instructions Set 3    8    3    1    0 
 * Sample  615 - OpCode  8 - Instructions  6 - Before Set 0    0    1    2    1    After Set 1    0    1    2    2    Instructions Set 3    8    2    2    3 
 * Sample  620 - OpCode  8 - Instructions  6 - Before Set 0    1    2    0    3    After Set 1    1    2    3    3    Instructions Set 3    8    3    3    2 
 * Sample  624 - OpCode  8 - Instructions  6 - Before Set 0    3    2    2    1    After Set 1    3    2    2    2    Instructions Set 3    8    2    2    3 
 * Sample  663 - OpCode  8 - Instructions  6 - Before Set 0    1    3    1    3    After Set 1    3    3    1    3    Instructions Set 3    8    3    3    0 
 * Sample  668 - OpCode  8 - Instructions  6 - Before Set 0    3    0    2    1    After Set 1    2    0    2    1    Instructions Set 3    8    2    2    0 
 * Sample  287 - OpCode  9 - Instructions  5 - Before Set 0    3    1    0    0    After Set 1    3    1    1    0    Instructions Set 3    9    3    1    2 
 * Sample  320 - OpCode  9 - Instructions  5 - Before Set 0    3    1    2    0    After Set 1    1    1    2    0    Instructions Set 3    9    3    1    0 
 * Sample  329 - OpCode  9 - Instructions  5 - Before Set 0    3    1    2    0    After Set 1    3    1    2    1    Instructions Set 3    9    3    1    3 
 * Sample  334 - OpCode  9 - Instructions  5 - Before Set 0    3    1    0    0    After Set 1    3    1    0    1    Instructions Set 3    9    2    1    3 
 * Sample  357 - OpCode  9 - Instructions  5 - Before Set 0    1    1    0    2    After Set 1    1    1    0    1    Instructions Set 3    9    2    1    3 
 * Sample  403 - OpCode  9 - Instructions  5 - Before Set 0    0    1    0    1    After Set 1    0    1    1    1    Instructions Set 3    9    2    1    2 
 * Sample  410 - OpCode  9 - Instructions  5 - Before Set 0    1    1    1    0    After Set 1    1    1    1    0    Instructions Set 3    9    3    1    0 
 * Sample  427 - OpCode  9 - Instructions  5 - Before Set 0    1    1    0    0    After Set 1    1    1    0    0    Instructions Set 3    9    3    1    0 
 * Sample  428 - OpCode  9 - Instructions  5 - Before Set 0    3    1    0    1    After Set 1    3    1    0    1    Instructions Set 3    9    2    1    1 
 * Sample  440 - OpCode  9 - Instructions  5 - Before Set 0    0    1    2    0    After Set 1    0    1    1    0    Instructions Set 3    9    3    1    2 
 * Sample  442 - OpCode  9 - Instructions  5 - Before Set 0    2    1    1    0    After Set 1    2    1    1    0    Instructions Set 3    9    3    1    1 
 * Sample  459 - OpCode  9 - Instructions  5 - Before Set 0    0    1    0    0    After Set 1    0    1    0    0    Instructions Set 3    9    3    1    1 
 * Sample  468 - OpCode  9 - Instructions  5 - Before Set 0    3    1    3    0    After Set 1    3    1    3    1    Instructions Set 3    9    3    1    3 
 * Sample  469 - OpCode  9 - Instructions  5 - Before Set 0    0    1    0    3    After Set 1    0    1    1    3    Instructions Set 3    9    2    1    2 
 * Sample  483 - OpCode  9 - Instructions  5 - Before Set 0    0    1    1    0    After Set 1    1    1    1    0    Instructions Set 3    9    3    1    0 
 * Sample  484 - OpCode  9 - Instructions  5 - Before Set 0    1    1    2    0    After Set 1    1    1    2    1    Instructions Set 3    9    3    1    3 
 * Sample  545 - OpCode  9 - Instructions  5 - Before Set 0    3    1    1    0    After Set 1    1    1    1    0    Instructions Set 3    9    3    1    0 
 * Sample  566 - OpCode  9 - Instructions  5 - Before Set 0    2    1    0    0    After Set 1    2    1    0    0    Instructions Set 3    9    3    1    1 
 * Sample  582 - OpCode  9 - Instructions  5 - Before Set 0    1    1    0    2    After Set 1    1    1    1    2    Instructions Set 3    9    2    1    2 
 * Sample  588 - OpCode  9 - Instructions  5 - Before Set 0    3    1    3    0    After Set 1    3    1    3    0    Instructions Set 3    9    3    1    1 
 * Sample  602 - OpCode  9 - Instructions  5 - Before Set 0    3    1    1    0    After Set 1    3    1    1    0    Instructions Set 3    9    3    1    1 
 * Sample  604 - OpCode  9 - Instructions  5 - Before Set 0    3    1    0    2    After Set 1    3    1    0    2    Instructions Set 3    9    2    1    1 
 * Sample  611 - OpCode  9 - Instructions  5 - Before Set 0    2    1    2    0    After Set 1    2    1    2    0    Instructions Set 3    9    3    1    1 
 * Sample  678 - OpCode  9 - Instructions  5 - Before Set 0    3    1    3    0    After Set 1    1    1    3    0    Instructions Set 3    9    3    1    0 
 * Sample  686 - OpCode  9 - Instructions  5 - Before Set 0    1    1    0    1    After Set 1    1    1    0    1    Instructions Set 3    9    2    1    0 
 * Sample  319 - OpCode 10 - Instructions  1 - Before Set 0    3    1    2    0    After Set 1    3    1    2    6    Instructions Set 3   10    2    3    3 
 * Sample  355 - OpCode 10 - Instructions  1 - Before Set 0    3    3    1    0    After Set 1    3    3    9    0    Instructions Set 3   10    1    3    2 
 * Sample  356 - OpCode 10 - Instructions  1 - Before Set 0    3    1    0    1    After Set 1    2    1    0    1    Instructions Set 3   10    1    2    0 
 * Sample  366 - OpCode 10 - Instructions  1 - Before Set 0    1    2    0    2    After Set 1    2    2    0    2    Instructions Set 3   10    0    2    0 
 * Sample  375 - OpCode 10 - Instructions  1 - Before Set 0    3    3    3    2    After Set 1    3    3    9    2    Instructions Set 3   10    0    3    2 
 * Sample  398 - OpCode 10 - Instructions  1 - Before Set 0    3    0    2    0    After Set 1    3    0    2    6    Instructions Set 3   10    2    3    3 
 * Sample  411 - OpCode 10 - Instructions  1 - Before Set 0    2    2    3    1    After Set 1    2    2    9    1    Instructions Set 3   10    2    3    2 
 * Sample  478 - OpCode 10 - Instructions  1 - Before Set 0    3    2    0    0    After Set 1    3    6    0    0    Instructions Set 3   10    1    3    1 
 * Sample  479 - OpCode 10 - Instructions  1 - Before Set 0    3    2    1    0    After Set 1    9    2    1    0    Instructions Set 3   10    0    3    0 
 * Sample  494 - OpCode 10 - Instructions  1 - Before Set 0    1    3    3    1    After Set 1    2    3    3    1    Instructions Set 3   10    0    2    0 
 * Sample  530 - OpCode 10 - Instructions  1 - Before Set 0    1    2    2    1    After Set 1    1    2    2    6    Instructions Set 3   10    1    3    3 
 * Sample  535 - OpCode 10 - Instructions  1 - Before Set 0    1    3    1    1    After Set 1    1    3    6    1    Instructions Set 3   10    1    2    2 
 * Sample  584 - OpCode 10 - Instructions  1 - Before Set 0    3    3    0    1    After Set 1    3    9    0    1    Instructions Set 3   10    0    3    1 
 * Sample  585 - OpCode 10 - Instructions  1 - Before Set 0    0    2    3    1    After Set 1    0    2    6    1    Instructions Set 3   10    1    3    2 
 * Sample  586 - OpCode 10 - Instructions  1 - Before Set 0    2    0    2    0    After Set 1    2    0    2    6    Instructions Set 3   10    2    3    3 
 * Sample  595 - OpCode 10 - Instructions  1 - Before Set 0    2    2    0    2    After Set 1    2    2    0    6    Instructions Set 3   10    0    3    3 
 * Sample  616 - OpCode 10 - Instructions  1 - Before Set 0    1    1    0    1    After Set 1    1    2    0    1    Instructions Set 3   10    3    2    1 
 * Sample  621 - OpCode 10 - Instructions  1 - Before Set 0    2    1    1    3    After Set 1    6    1    1    3    Instructions Set 3   10    3    2    0 
 * Sample  652 - OpCode 10 - Instructions  1 - Before Set 0    3    1    3    2    After Set 1    3    1    3    9    Instructions Set 3   10    0    3    3 
 * Sample  664 - OpCode 10 - Instructions  1 - Before Set 0    3    1    1    1    After Set 1    3    1    1    9    Instructions Set 3   10    0    3    3 
 * Sample  692 - OpCode 10 - Instructions  1 - Before Set 0    2    3    0    3    After Set 1    2    6    0    3    Instructions Set 3   10    3    2    1 
 * Sample  695 - OpCode 10 - Instructions  1 - Before Set 0    0    3    1    2    After Set 1    0    3    6    2    Instructions Set 3   10    3    3    2 
 * Sample  303 - OpCode 11 - Instructions  2 - Before Set 0    2    2    3    1    After Set 1    2    2    3    1    Instructions Set 3   11    2    1    0 
 * Sample  315 - OpCode 11 - Instructions  2 - Before Set 0    1    2    3    2    After Set 1    1    2    3    2    Instructions Set 3   11    2    3    3 
 * Sample  332 - OpCode 11 - Instructions  2 - Before Set 0    2    1    3    2    After Set 1    2    1    3    2    Instructions Set 3   11    2    3    3 
 * Sample  341 - OpCode 11 - Instructions  2 - Before Set 0    2    2    3    3    After Set 1    2    2    3    2    Instructions Set 3   11    2    1    3 
 * Sample  389 - OpCode 11 - Instructions  2 - Before Set 0    0    2    3    1    After Set 1    0    2    2    1    Instructions Set 3   11    2    1    2 
 * Sample  393 - OpCode 11 - Instructions  2 - Before Set 0    1    2    3    2    After Set 1    2    2    3    2    Instructions Set 3   11    2    3    0 
 * Sample  404 - OpCode 11 - Instructions  2 - Before Set 0    2    3    3    3    After Set 1    2    3    3    2    Instructions Set 3   11    2    0    3 
 * Sample  421 - OpCode 11 - Instructions  2 - Before Set 0    2    2    3    1    After Set 1    2    2    3    1    Instructions Set 3   11    2    1    1 
 * Sample  458 - OpCode 11 - Instructions  2 - Before Set 0    0    3    3    2    After Set 1    0    2    3    2    Instructions Set 3   11    2    3    1 
 * Sample  460 - OpCode 11 - Instructions  2 - Before Set 0    1    3    3    2    After Set 1    1    3    2    2    Instructions Set 3   11    2    3    2 
 * Sample  481 - OpCode 11 - Instructions  2 - Before Set 0    2    1    3    3    After Set 1    2    2    3    3    Instructions Set 3   11    2    0    1 
 * Sample  500 - OpCode 11 - Instructions  2 - Before Set 0    2    2    3    2    After Set 1    2    2    3    2    Instructions Set 3   11    2    0    3 
 * Sample  502 - OpCode 11 - Instructions  2 - Before Set 0    1    3    3    2    After Set 1    1    3    3    2    Instructions Set 3   11    2    3    3 
 * Sample  519 - OpCode 11 - Instructions  2 - Before Set 0    1    2    3    1    After Set 1    1    2    3    1    Instructions Set 3   11    2    1    1 
 * Sample  520 - OpCode 11 - Instructions  2 - Before Set 0    0    2    3    0    After Set 1    0    2    3    2    Instructions Set 3   11    2    1    3 
 * Sample  523 - OpCode 11 - Instructions  2 - Before Set 0    0    0    3    2    After Set 1    2    0    3    2    Instructions Set 3   11    2    3    0 
 * Sample  533 - OpCode 11 - Instructions  2 - Before Set 0    2    3    3    0    After Set 1    2    2    3    0    Instructions Set 3   11    2    0    1 
 * Sample  538 - OpCode 11 - Instructions  2 - Before Set 0    3    3    3    2    After Set 1    3    3    3    2    Instructions Set 3   11    2    3    3 
 * Sample  542 - OpCode 11 - Instructions  2 - Before Set 0    2    2    3    0    After Set 1    2    2    3    0    Instructions Set 3   11    2    1    1 
 * Sample  552 - OpCode 11 - Instructions  2 - Before Set 0    2    2    3    2    After Set 1    2    2    2    2    Instructions Set 3   11    2    3    2 
 * Sample  569 - OpCode 11 - Instructions  2 - Before Set 0    0    2    3    0    After Set 1    2    2    3    0    Instructions Set 3   11    2    1    0 
 * Sample  571 - OpCode 11 - Instructions  2 - Before Set 0    3    2    3    2    After Set 1    3    2    3    2    Instructions Set 3   11    2    1    3 
 * Sample  579 - OpCode 11 - Instructions  2 - Before Set 0    2    1    3    2    After Set 1    2    1    2    2    Instructions Set 3   11    2    3    2 
 * Sample  605 - OpCode 11 - Instructions  2 - Before Set 0    2    0    3    0    After Set 1    2    0    3    2    Instructions Set 3   11    2    0    3 
 * Sample  619 - OpCode 11 - Instructions  2 - Before Set 0    2    3    3    2    After Set 1    2    3    3    2    Instructions Set 3   11    2    3    0 
 * Sample  636 - OpCode 11 - Instructions  2 - Before Set 0    0    2    3    1    After Set 1    0    2    3    2    Instructions Set 3   11    2    1    3 
 * Sample  640 - OpCode 11 - Instructions  2 - Before Set 0    0    1    3    2    After Set 1    0    2    3    2    Instructions Set 3   11    2    3    1 
 * Sample  676 - OpCode 11 - Instructions  2 - Before Set 0    3    1    3    2    After Set 1    3    2    3    2    Instructions Set 3   11    2    3    1 
 * Sample  684 - OpCode 11 - Instructions  2 - Before Set 0    2    0    3    3    After Set 1    2    0    2    3    Instructions Set 3   11    2    0    2 
 * Sample  689 - OpCode 11 - Instructions  2 - Before Set 0    0    2    3    3    After Set 1    0    2    3    2    Instructions Set 3   11    2    1    3 
 * Sample  286 - OpCode 12 - Instructions  5 - Before Set 0    0    1    1    2    After Set 1    0    1    1    3    Instructions Set 3   12    3    1    3 
 * Sample  304 - OpCode 12 - Instructions  5 - Before Set 0    2    0    2    1    After Set 1    3    0    2    1    Instructions Set 3   12    3    2    0 
 * Sample  306 - OpCode 12 - Instructions  5 - Before Set 0    3    2    2    1    After Set 1    3    3    2    1    Instructions Set 3   12    3    2    1 
 * Sample  343 - OpCode 12 - Instructions  5 - Before Set 0    1    3    3    1    After Set 1    1    3    3    1    Instructions Set 3   12    3    2    1 
 * Sample  351 - OpCode 12 - Instructions  5 - Before Set 0    2    1    3    1    After Set 1    3    1    3    1    Instructions Set 3   12    3    2    0 
 * Sample  394 - OpCode 12 - Instructions  5 - Before Set 0    1    1    2    2    After Set 1    1    3    2    2    Instructions Set 3   12    3    1    1 
 * Sample  400 - OpCode 12 - Instructions  5 - Before Set 0    1    1    2    2    After Set 1    1    1    3    2    Instructions Set 3   12    3    1    2 
 * Sample  408 - OpCode 12 - Instructions  5 - Before Set 0    3    1    3    2    After Set 1    3    1    3    3    Instructions Set 3   12    3    1    3 
 * Sample  412 - OpCode 12 - Instructions  5 - Before Set 0    0    1    1    2    After Set 1    3    1    1    2    Instructions Set 3   12    3    1    0 
 * Sample  445 - OpCode 12 - Instructions  5 - Before Set 0    2    1    3    2    After Set 1    2    3    3    2    Instructions Set 3   12    3    1    1 
 * Sample  449 - OpCode 12 - Instructions  5 - Before Set 0    2    1    2    2    After Set 1    2    1    2    3    Instructions Set 3   12    3    1    3 
 * Sample  471 - OpCode 12 - Instructions  5 - Before Set 0    0    1    3    1    After Set 1    0    3    3    1    Instructions Set 3   12    3    2    1 
 * Sample  493 - OpCode 12 - Instructions  5 - Before Set 0    1    2    2    1    After Set 1    1    2    3    1    Instructions Set 3   12    3    2    2 
 * Sample  506 - OpCode 12 - Instructions  5 - Before Set 0    2    0    3    1    After Set 1    3    0    3    1    Instructions Set 3   12    3    2    0 
 * Sample  558 - OpCode 12 - Instructions  5 - Before Set 0    0    2    2    1    After Set 1    0    2    2    3    Instructions Set 3   12    3    2    3 
 * Sample  567 - OpCode 12 - Instructions  5 - Before Set 0    3    1    2    2    After Set 1    3    1    2    2    Instructions Set 3   12    3    1    0 
 * Sample  583 - OpCode 12 - Instructions  5 - Before Set 0    3    0    2    1    After Set 1    3    0    2    3    Instructions Set 3   12    3    2    3 
 * Sample  608 - OpCode 12 - Instructions  5 - Before Set 0    3    3    2    1    After Set 1    3    3    2    3    Instructions Set 3   12    3    2    3 
 * Sample  625 - OpCode 12 - Instructions  5 - Before Set 0    1    1    0    2    After Set 1    1    1    0    3    Instructions Set 3   12    3    1    3 
 * Sample  641 - OpCode 12 - Instructions  5 - Before Set 0    3    1    1    2    After Set 1    3    1    3    2    Instructions Set 3   12    3    1    2 
 * Sample  651 - OpCode 12 - Instructions  5 - Before Set 0    1    1    1    2    After Set 1    1    1    1    3    Instructions Set 3   12    3    1    3 
 * Sample  654 - OpCode 12 - Instructions  5 - Before Set 0    2    1    1    2    After Set 1    3    1    1    2    Instructions Set 3   12    3    1    0 
 * Sample  675 - OpCode 12 - Instructions  5 - Before Set 0    1    0    3    1    After Set 1    3    0    3    1    Instructions Set 3   12    3    2    0 
 * Sample  688 - OpCode 12 - Instructions  5 - Before Set 0    2    3    3    1    After Set 1    2    3    3    1    Instructions Set 3   12    3    2    2 
 * Sample  292 - OpCode 13 - Instructions 13 - Before Set 0    0    0    2    1    After Set 1    0    0    2    1    Instructions Set 3   13    0    0    1 
 * Sample  295 - OpCode 13 - Instructions 13 - Before Set 0    0    3    3    3    After Set 1    0    3    3    3    Instructions Set 3   13    0    0    0 
 * Sample  298 - OpCode 13 - Instructions 13 - Before Set 0    0    2    3    1    After Set 1    0    2    3    0    Instructions Set 3   13    0    0    3 
 * Sample  302 - OpCode 13 - Instructions 13 - Before Set 0    0    1    2    3    After Set 1    0    0    2    3    Instructions Set 3   13    0    0    1 
 * Sample  317 - OpCode 13 - Instructions 13 - Before Set 0    0    3    3    2    After Set 1    0    3    3    2    Instructions Set 3   13    0    0    0 
 * Sample  322 - OpCode 13 - Instructions 13 - Before Set 0    0    2    3    3    After Set 1    0    2    0    3    Instructions Set 3   13    0    0    2 
 * Sample  328 - OpCode 13 - Instructions 13 - Before Set 0    0    0    1    1    After Set 1    0    0    1    1    Instructions Set 3   13    0    0    1 
 * Sample  352 - OpCode 13 - Instructions 13 - Before Set 0    0    1    0    2    After Set 1    0    1    0    2    Instructions Set 3   13    0    0    2 
 * Sample  368 - OpCode 13 - Instructions 13 - Before Set 0    0    3    2    3    After Set 1    0    0    2    3    Instructions Set 3   13    0    0    1 
 * Sample  444 - OpCode 13 - Instructions 13 - Before Set 0    0    1    0    0    After Set 1    0    0    0    0    Instructions Set 3   13    0    0    1 
 * Sample  467 - OpCode 13 - Instructions 13 - Before Set 0    0    3    2    0    After Set 1    0    0    2    0    Instructions Set 3   13    0    0    1 
 * Sample  473 - OpCode 13 - Instructions 13 - Before Set 0    0    0    3    1    After Set 1    0    0    0    1    Instructions Set 3   13    0    0    2 
 * Sample  474 - OpCode 13 - Instructions 13 - Before Set 0    0    2    3    2    After Set 1    0    2    3    0    Instructions Set 3   13    0    0    3 
 * Sample  475 - OpCode 13 - Instructions 13 - Before Set 0    0    0    3    1    After Set 1    0    0    3    1    Instructions Set 3   13    0    0    0 
 * Sample  490 - OpCode 13 - Instructions 13 - Before Set 0    0    1    1    0    After Set 1    0    1    1    0    Instructions Set 3   13    0    0    3 
 * Sample  498 - OpCode 13 - Instructions 13 - Before Set 0    0    3    0    3    After Set 1    0    3    0    3    Instructions Set 3   13    0    0    0 
 * Sample  499 - OpCode 13 - Instructions 13 - Before Set 0    0    1    0    3    After Set 1    0    1    0    3    Instructions Set 3   13    0    0    0 
 * Sample  522 - OpCode 13 - Instructions 13 - Before Set 0    0    0    2    3    After Set 1    0    0    2    3    Instructions Set 3   13    0    0    0 
 * Sample  526 - OpCode 13 - Instructions 13 - Before Set 0    0    1    2    2    After Set 1    0    1    2    2    Instructions Set 3   13    0    0    0 
 * Sample  562 - OpCode 13 - Instructions 13 - Before Set 0    0    2    2    1    After Set 1    0    2    2    1    Instructions Set 3   13    0    0    0 
 * Sample  590 - OpCode 13 - Instructions 13 - Before Set 0    0    3    0    1    After Set 1    0    3    0    1    Instructions Set 3   13    0    0    2 
 * Sample  591 - OpCode 13 - Instructions 13 - Before Set 0    0    0    0    1    After Set 1    0    0    0    1    Instructions Set 3   13    0    0    0 
 * Sample  614 - OpCode 13 - Instructions 13 - Before Set 0    0    0    0    1    After Set 1    0    0    0    0    Instructions Set 3   13    0    0    3 
 * Sample  631 - OpCode 13 - Instructions 13 - Before Set 0    0    2    3    0    After Set 1    0    2    0    0    Instructions Set 3   13    0    0    2 
 * Sample  632 - OpCode 13 - Instructions 13 - Before Set 0    0    1    0    3    After Set 1    0    1    0    3    Instructions Set 3   13    0    0    2 
 * Sample  639 - OpCode 13 - Instructions 13 - Before Set 0    0    3    1    1    After Set 1    0    3    1    0    Instructions Set 3   13    0    0    3 
 * Sample  305 - OpCode 14 - Instructions  2 - Before Set 0    0    3    3    3    After Set 1    0    3    3    2    Instructions Set 3   14    2    2    3 
 * Sample  313 - OpCode 14 - Instructions  2 - Before Set 0    1    3    2    0    After Set 1    1    2    2    0    Instructions Set 3   14    1    2    1 
 * Sample  339 - OpCode 14 - Instructions  2 - Before Set 0    3    3    3    3    After Set 1    3    2    3    3    Instructions Set 3   14    2    2    1 
 * Sample  361 - OpCode 14 - Instructions  2 - Before Set 0    1    3    2    1    After Set 1    1    2    2    1    Instructions Set 3   14    1    2    1 
 * Sample  369 - OpCode 14 - Instructions  2 - Before Set 0    3    1    2    2    After Set 1    3    1    2    2    Instructions Set 3   14    0    2    3 
 * Sample  371 - OpCode 14 - Instructions  2 - Before Set 0    3    3    3    0    After Set 1    3    2    3    0    Instructions Set 3   14    2    2    1 
 * Sample  373 - OpCode 14 - Instructions  2 - Before Set 0    1    3    2    1    After Set 1    2    3    2    1    Instructions Set 3   14    1    2    0 
 * Sample  386 - OpCode 14 - Instructions  2 - Before Set 0    0    0    2    3    After Set 1    0    0    2    2    Instructions Set 3   14    3    2    3 
 * Sample  391 - OpCode 14 - Instructions  2 - Before Set 0    0    3    3    2    After Set 1    0    3    3    2    Instructions Set 3   14    2    2    3 
 * Sample  395 - OpCode 14 - Instructions  2 - Before Set 0    3    1    3    3    After Set 1    2    1    3    3    Instructions Set 3   14    2    2    0 
 * Sample  399 - OpCode 14 - Instructions  2 - Before Set 0    1    0    3    2    After Set 1    2    0    3    2    Instructions Set 3   14    2    2    0 
 * Sample  430 - OpCode 14 - Instructions  2 - Before Set 0    0    2    3    3    After Set 1    2    2    3    3    Instructions Set 3   14    2    2    0 
 * Sample  465 - OpCode 14 - Instructions  2 - Before Set 0    3    3    3    3    After Set 1    2    3    3    3    Instructions Set 3   14    2    2    0 
 * Sample  482 - OpCode 14 - Instructions  2 - Before Set 0    2    3    3    3    After Set 1    2    3    3    3    Instructions Set 3   14    2    2    0 
 * Sample  511 - OpCode 14 - Instructions  2 - Before Set 0    3    3    2    2    After Set 1    2    3    2    2    Instructions Set 3   14    0    2    0 
 * Sample  531 - OpCode 14 - Instructions  2 - Before Set 0    3    3    2    3    After Set 1    3    2    2    3    Instructions Set 3   14    1    2    1 
 * Sample  537 - OpCode 14 - Instructions  2 - Before Set 0    3    1    3    0    After Set 1    2    1    3    0    Instructions Set 3   14    2    2    0 
 * Sample  543 - OpCode 14 - Instructions  2 - Before Set 0    3    2    2    3    After Set 1    3    2    2    2    Instructions Set 3   14    0    2    3 
 * Sample  548 - OpCode 14 - Instructions  2 - Before Set 0    2    0    3    2    After Set 1    2    0    2    2    Instructions Set 3   14    2    2    2 
 * Sample  553 - OpCode 14 - Instructions  2 - Before Set 0    0    3    3    3    After Set 1    0    2    3    3    Instructions Set 3   14    2    2    1 
 * Sample  554 - OpCode 14 - Instructions  2 - Before Set 0    1    0    3    0    After Set 1    2    0    3    0    Instructions Set 3   14    2    2    0 
 * Sample  578 - OpCode 14 - Instructions  2 - Before Set 0    3    2    2    1    After Set 1    3    2    2    1    Instructions Set 3   14    0    2    2 
 * Sample  601 - OpCode 14 - Instructions  2 - Before Set 0    2    2    3    1    After Set 1    2    2    3    1    Instructions Set 3   14    2    2    0 
 * Sample  628 - OpCode 14 - Instructions  2 - Before Set 0    0    2    3    1    After Set 1    0    2    3    2    Instructions Set 3   14    2    2    3 
 * Sample  630 - OpCode 14 - Instructions  2 - Before Set 0    1    3    2    3    After Set 1    1    2    2    3    Instructions Set 3   14    3    2    1 
 * Sample  637 - OpCode 14 - Instructions  2 - Before Set 0    1    3    2    3    After Set 1    2    3    2    3    Instructions Set 3   14    1    2    0 
 * Sample  670 - OpCode 14 - Instructions  2 - Before Set 0    3    3    2    3    After Set 1    3    3    2    3    Instructions Set 3   14    1    2    2 
 * Sample  673 - OpCode 14 - Instructions  2 - Before Set 0    1    0    3    3    After Set 1    1    2    3    3    Instructions Set 3   14    2    2    1 
 * Sample  674 - OpCode 14 - Instructions  2 - Before Set 0    1    3    2    2    After Set 1    1    3    2    2    Instructions Set 3   14    1    2    2 
 * Sample  685 - OpCode 14 - Instructions  2 - Before Set 0    3    3    2    2    After Set 1    3    2    2    2    Instructions Set 3   14    1    2    1 
 * Sample  307 - OpCode 15 - Instructions  4 - Before Set 0    3    1    0    3    After Set 1    3    1    3    3    Instructions Set 3   15    2    3    2 
 * Sample  337 - OpCode 15 - Instructions  4 - Before Set 0    1    1    3    3    After Set 1    1    3    3    3    Instructions Set 3   15    1    2    1 
 * Sample  405 - OpCode 15 - Instructions  4 - Before Set 0    0    0    1    3    After Set 1    0    3    1    3    Instructions Set 3   15    2    3    1 
 * Sample  409 - OpCode 15 - Instructions  4 - Before Set 0    3    0    1    3    After Set 1    3    0    1    3    Instructions Set 3   15    2    3    3 
 * Sample  413 - OpCode 15 - Instructions  4 - Before Set 0    0    0    0    3    After Set 1    0    3    0    3    Instructions Set 3   15    0    3    1 
 * Sample  431 - OpCode 15 - Instructions  4 - Before Set 0    2    1    1    1    After Set 1    2    1    1    3    Instructions Set 3   15    0    1    3 
 * Sample  434 - OpCode 15 - Instructions  4 - Before Set 0    0    0    2    0    After Set 1    0    2    2    0    Instructions Set 3   15    3    2    1 
 * Sample  453 - OpCode 15 - Instructions  4 - Before Set 0    0    1    2    0    After Set 1    0    1    2    1    Instructions Set 3   15    0    1    3 
 * Sample  463 - OpCode 15 - Instructions  4 - Before Set 0    0    2    2    2    After Set 1    0    2    2    2    Instructions Set 3   15    0    2    1 
 * Sample  487 - OpCode 15 - Instructions  4 - Before Set 0    1    3    2    3    After Set 1    1    3    2    3    Instructions Set 3   15    0    2    1 
 * Sample  492 - OpCode 15 - Instructions  4 - Before Set 0    1    0    2    2    After Set 1    1    3    2    2    Instructions Set 3   15    0    2    1 
 * Sample  525 - OpCode 15 - Instructions  4 - Before Set 0    0    1    1    2    After Set 1    3    1    1    2    Instructions Set 3   15    2    3    0 
 * Sample  551 - OpCode 15 - Instructions  4 - Before Set 0    1    2    3    1    After Set 1    1    2    3    1    Instructions Set 3   15    0    2    2 
 * Sample  593 - OpCode 15 - Instructions  4 - Before Set 0    2    3    2    0    After Set 1    2    2    2    0    Instructions Set 3   15    3    2    1 
 * Sample  607 - OpCode 15 - Instructions  4 - Before Set 0    3    1    3    1    After Set 1    3    1    3    1    Instructions Set 3   15    1    2    0 
 * Sample  635 - OpCode 15 - Instructions  4 - Before Set 0    0    0    3    3    After Set 1    0    3    3    3    Instructions Set 3   15    0    3    1 
 * Sample  666 - OpCode 15 - Instructions  4 - Before Set 0    1    1    3    1    After Set 1    1    1    3    3    Instructions Set 3   15    0    2    3 
 * 
 * </pre> 
 */
public class Day16_ChronalClassification
{
  private static int SET_0               = 0;

  private static int SET_1               = 1;

  private static int SET_TEST_OUTPUT     = 2;

  private static int SET_INSTRUCTIONS    = 3;

  private static int REGISTER_0          = 0;

  private static int REGISTER_1          = 1;

  private static int REGISTER_2          = 2;

  private static int REGISTER_3          = 3;

  private static int INSTRUCTION_OPCODDE = 0;

  private static int INSTRUCTION_PARAM_A = 1;

  private static int INSTRUCTION_PARAM_B = 2;

  private static int INSTRUCTION_PARAM_C = 3;

  private static int BIT_MASK            = 0xFFFF;

  public static void main( String[] args )
  {
    String test_input = "";

    test_input += "Before: [3, 2, 1, 1]";
    test_input += ";9 2 1 2";
    test_input += ";After:  [3, 2, 2, 1]";

    calculatePart01( test_input, true );

    calculate01( getListProd(), false );
  }

  private static void calculatePart01( String pString, boolean pKnzDebug )
  {
    List< String > converted_string_list = Arrays.stream( pString.split( ";" ) ).collect( Collectors.toList() );

    calculate01( converted_string_list, pKnzDebug );
  }

  private static void calculate01( List< String > pListInput, boolean pKnzDebug )
  {
    wl( "" );
    wl( "------------------------------------------------------------------------------------------" );

    int[][] register = new int[ 4 ][ 4 ];

    int result_part_01 = 0;

    int sample_count  = 0;

    int set_input     = SET_0;

    int set_output    = SET_1;

    for ( int cur_idx = 0; cur_idx < pListInput.size(); cur_idx++ )
    {
      String input_str = pListInput.get( cur_idx );

      if ( input_str.isBlank() )
      {
        if ( ( pListInput.get( cur_idx + 1 ).isBlank() ) && ( pListInput.get( cur_idx + 2 ).isBlank() ) )
        {
          break;
        }
      }
      else
      {
        if ( input_str.contains( "Before:" ) )
        {
          sample_count++;

          String before_str = input_str;

          cur_idx++;

          String instructions = pListInput.get( cur_idx );

          cur_idx++;

          String after_str = pListInput.get( cur_idx );

          setValues( before_str,   register, set_input, set_output );

          setValues( after_str,    register, set_input, set_output );

          setValues( instructions, register, set_input, set_output );

          int behave_count = doTestBehave( register, set_input, set_output );

          wl( String.format( "Sample %4d - OpCode %2d - Instructions %2d - Before %s   After %s   Instructions %s", sample_count, register[ SET_INSTRUCTIONS ][ INSTRUCTION_OPCODDE ], behave_count, getDebugStr( register, set_input ), getDebugStr( register, set_output ), getDebugStr( register, SET_INSTRUCTIONS ) ) );

          result_part_01 += behave_count > 3 ? 1 : 0;
        }
      }
    }

    wl( "" );
    wl( "Result Part 1 " + result_part_01 );
  }

  private static int doTestBehave( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    int sum_check = 0;

    for ( int test_inst = 0; test_inst < 16; test_inst++ )
    {
      /*
       * Copy the Register-Values from the Before-State to the Test set
       */
      copyValues( pRegister, pSetInput, SET_TEST_OUTPUT );

      /*
       * Do the Instruction
       */
           if ( test_inst ==  0 ) doADDR( pRegister, pSetInput, SET_TEST_OUTPUT );
      else if ( test_inst ==  1 ) doADDI( pRegister, pSetInput, SET_TEST_OUTPUT );
      else if ( test_inst ==  2 ) doMULR( pRegister, pSetInput, SET_TEST_OUTPUT );
      else if ( test_inst ==  3 ) doMULI( pRegister, pSetInput, SET_TEST_OUTPUT );
      else if ( test_inst ==  4 ) doBANR( pRegister, pSetInput, SET_TEST_OUTPUT );
      else if ( test_inst ==  5 ) doBANI( pRegister, pSetInput, SET_TEST_OUTPUT );
      else if ( test_inst ==  6 ) doBORR( pRegister, pSetInput, SET_TEST_OUTPUT );
      else if ( test_inst ==  7 ) doBORI( pRegister, pSetInput, SET_TEST_OUTPUT );
      else if ( test_inst ==  8 ) doSETR( pRegister, pSetInput, SET_TEST_OUTPUT );
      else if ( test_inst ==  9 ) doSETI( pRegister, pSetInput, SET_TEST_OUTPUT );
      else if ( test_inst == 10 ) doGTIR( pRegister, pSetInput, SET_TEST_OUTPUT );
      else if ( test_inst == 11 ) doGTRI( pRegister, pSetInput, SET_TEST_OUTPUT );
      else if ( test_inst == 12 ) doGTRR( pRegister, pSetInput, SET_TEST_OUTPUT );
      else if ( test_inst == 13 ) doEQIR( pRegister, pSetInput, SET_TEST_OUTPUT );
      else if ( test_inst == 14 ) doEQRI( pRegister, pSetInput, SET_TEST_OUTPUT );
      else if ( test_inst == 15 ) doEQRR( pRegister, pSetInput, SET_TEST_OUTPUT );

      /*
       * Check the Registervalues agains the given AFTER-State
       */
      sum_check += doCheck( pRegister, pSetOutput, SET_TEST_OUTPUT );
    }

    return sum_check;
  }

  private static int doCheck( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    for ( int cur_idx = 0; cur_idx < 4; cur_idx++ )
    {
      if ( pRegister[ pSetInput ][ cur_idx ] != pRegister[ pSetOutput ][ cur_idx ] )
      {
        return 0;
      }
    }

    return 1;
  }

  private static void copyValues( int[][] pRegister, int pSetInput, int pSetOutput )
  {
    for ( int cur_idx = 0; cur_idx < 4; cur_idx++ )
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

  private static String getDebugStr( int[][] pRegister, int pSet )
  {
    return String.format( "Set %1d  %3d  %3d  %3d  %3d ", pSet, pRegister[ pSet ][ 0 ], pRegister[ pSet ][ 1 ], pRegister[ pSet ][ 2 ], pRegister[ pSet ][ 3 ] );
  }

  private static void setValues( String pInputStr, int[][] pRegister, int pSetInput, int pSetOutput )
  {
    int set_to_write = SET_INSTRUCTIONS;

    if ( pInputStr.contains( "Before:" ) )
    {
      set_to_write = pSetInput;
    }
    else if ( pInputStr.contains( "After:" ) )
    {
      set_to_write = pSetOutput;
    }

    String[] values_v;

    if ( set_to_write != SET_INSTRUCTIONS )
    {
      values_v = pInputStr.substring( 9, 19 ).split( ", " );
    }
    else
    {
      values_v = pInputStr.split( " " );
    }

    pRegister[ set_to_write ][ REGISTER_0 ] = Integer.parseInt( values_v[ 0 ] );
    pRegister[ set_to_write ][ REGISTER_1 ] = Integer.parseInt( values_v[ 1 ] );
    pRegister[ set_to_write ][ REGISTER_2 ] = Integer.parseInt( values_v[ 2 ] );
    pRegister[ set_to_write ][ REGISTER_3 ] = Integer.parseInt( values_v[ 3 ] );
  }

  private static List< String > getListProd()
  {
    List< String > string_array = null;

    String datei_input = "/mnt/hd4tbb/daten/zdownload/advent_of_code_2018__day16_input.txt";

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
