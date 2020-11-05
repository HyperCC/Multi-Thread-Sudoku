/*
 * Copyright (c) 2020 Charlie Condorcet - Computer and Informatics Engineer.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE
 * FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package cl.ucn.disc.hpc.charlie.mts;

import cl.ucn.disc.hpc.charlie.mts.solvers.ThreadWithBT;
import cl.ucn.disc.hpc.charlie.mts.sudokupieces.Cell;
import cl.ucn.disc.hpc.charlie.mts.sudokupieces.SudokuGrid;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * The principal class to execute the code
 */
public class App {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(App.class);

    /**
     * The Chrono - to make snapshot of time
     */
    private static final StopWatch stopWatch = StopWatch.createStarted();

    /**
     * Principal main method
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {

        // read the txt with the sudoku to solve
        InputFile inputFile = new InputFile("inputSudokus/sudoku9x9.txt");

        // cant of cores
        //final int maxCores = Runtime.getRuntime().availableProcessors();
        final int maxCores = 16;
        log.debug("max core cantity {}", maxCores);

        // generate a Object SudokuGrid with necessary properties
        SudokuGrid sudokuGrid = new SudokuGrid(
                inputFile.getnCells(),
                inputFile.getGrid()
        );

        // print the sudoku to extracted
        log.debug("The original sudoku");
        System.out.println(sudokuGrid.printSudoku(sudokuGrid.getGrid()));



        // the lienal solver by BT
        ThreadWithBT linealSolver = new ThreadWithBT(
                sudokuGrid.getGrid(),
                sudokuGrid.getnCells());

        linealSolver.start();


        //long Ts = solveSudoku(1, inputFile.getGrid()).getTime(TimeUnit.NANOSECONDS);
        //log.debug("Time to process sequentially: {} nanoseconds", Ts);

        log.debug("Speedup: 1 with 1 Thread");
        log.debug("Efficiency: 1 with 1 Thread");
        log.debug("**********************************************************\n");

        /*
        // number of Threads to use simultaneously from 1 to N cores
        for (int nThreads = 2; nThreads <= maxCores; nThreads++) {

            // restart the time
            stopWatch.reset();
            stopWatch.start();

            // Time to execution with n Threads in nanoseconds
            long Tn = solveSudoku(nThreads, inputFile.getGrid()).getTime(TimeUnit.NANOSECONDS);
            log.debug("Time to process with {} Threads: {} nanoseconds", nThreads, Tn);

            double speedup = (Ts * 1.0 / Tn * 1.0);
            log.debug("Speedup: {} with {} Threads", speedup, nThreads);
            log.debug("Efficiency: {} with {} Threads", (speedup / nThreads * 1.0), nThreads);

            log.debug("**********************************************************\n");
        }

         */

        log.debug("End the application..");
    }

    /**
     * Process the number of prime numbers and time
     *
     * @return time to complete
     */
    public static StopWatch solveSudoku(final int maxThreads, Cell[][] grid) throws InterruptedException {

        // The threads executor - use <n> threads to ..
        final ExecutorService executorService = Executors.newFixedThreadPool(maxThreads);

        executorService.submit(new ThreadWithBT(grid, maxThreads));

        // Don't receive more tasks
        executorService.shutdown();

        if (executorService.awaitTermination(10, TimeUnit.MINUTES)) {

            log.info("Sudoku processed in {} with {} threads.", stopWatch.getNanoTime(), maxThreads);
        } else {

            // The calculate time
            log.info("Done in {} without primes founded", stopWatch.getNanoTime());
        }

        return stopWatch;
    }

}