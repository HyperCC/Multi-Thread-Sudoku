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

package cl.ucn.disc.hpc.charlie.multithreadsudoku;

import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Lineal Solver using Backtracking
 */
public class LinealSolverByBT {

    /**
     * The Logger
     */
    private final Logger log = LoggerFactory.getLogger(App.class);

    /**
     * The Chrono - to make snapshot of time
     */
    private final StopWatch stopWatch = StopWatch.createStarted();

    /**
     * The Grid
     */
    private Cell[][] grid;

    /**
     * State to an empty Cell
     */
    private final int emptyCell = 0;

    /**
     * Dimension n Cells to the Grid
     */
    private int nCells;

    /**
     * The Constructor
     *
     * @param grid
     * @param nCells
     */
    public LinealSolverByBT(Cell[][] grid, int nCells) {
        this.grid = grid;
        this.nCells = nCells;
    }

    /**
     * Start the Sudoku solver with BT
     */
    public long initSolver() {

        // restart the time initial time
        stopWatch.reset();
        stopWatch.start();

        // initialize the resolution
        if (solveSuduko(this.grid, 0, 0)) {

            log.info("Sudoku solved with");
            return stopWatch.getTime(TimeUnit.MILLISECONDS);
        }

        // sudoku unsolved - format error in sudoku inserted
        log.info("unsolvable Sudoku");
        return -1;
    }

    /**
     * Verify if the number exist in the same row
     *
     * @param row
     * @param number
     * @return
     */
    public boolean isInRow(Cell currentGrid[][], int row, int number) {

        // search the number
        for (int i = 0; i < this.nCells; i++) {

            if (currentGrid[row][i].getValue() == number) {
                return true;

            }
        }

        return false;
    }

    /**
     * Verify if the number exist in the same column
     *
     * @param col
     * @param number
     * @return
     */
    public boolean isInCol(Cell currentGrid[][], int col, int number) {

        // search the number
        for (int i = 0; i < this.nCells; i++) {

            if (currentGrid[i][col].getValue() == number) {
                return true;

            }
        }

        return false;
    }

    /**
     * Verify if the number exist in the same Block
     *
     * @param row
     * @param col
     * @return
     */
    public boolean isInBox(Cell currentGrid[][], int row, int col, int number) {

        int startRow = row - row % 3;
        int startCol = col - col % 3;

        for (int i = 0; i < (this.nCells / 3); i++)
            for (int j = 0; j < 3; j++)

                if (currentGrid[i + startRow][j + startCol].getValue() == number)
                    return true;

        return false;
    }

    /**
     * Verify if all rules are fulfilled
     *
     * @param row
     * @param col
     * @param number
     * @return
     */
    public boolean isAcceptable(Cell currentGrid[][], int row, int col, int number) {

        return !isInRow(currentGrid, row, number) &&
                !isInCol(currentGrid, col, number) &&
                !isInBox(currentGrid, row, col, number);
    }

    /**
     * Principal method to solve recursively
     *
     * @param currentGrid
     * @param row
     * @param col
     * @return
     */
    public boolean solveSuduko(Cell[][] currentGrid, int row, int col) {

        // finalization condition
        if (row == this.nCells - 1 && col == this.nCells)
            return true;

        // Check if column value  becomes 9 ,
        if (col == this.nCells) {
            row++;
            col = 0;
        }

        // Check if the current position is "void"
        if (currentGrid[row][col].getValue() != this.emptyCell)
            return solveSuduko(currentGrid, row, col + 1);

        for (int num = 1; num <= this.nCells; num++) {

            // Check if it is safe to place
            if (isAcceptable(currentGrid, row, col, num)) {

                // assigning the num in the current (row,col)
                currentGrid[row][col].setValue(num);

                // Checking for next
                if (solveSuduko(currentGrid, row, col + 1))
                    return true;
            }

            // removing the assigned num
            currentGrid[row][col].setValue(this.emptyCell);
        }
        return false;
    }

    /**
     * Get the Grid
     *
     * @return
     */
    public Cell[][] getGrid() {

        return grid;
    }
}
