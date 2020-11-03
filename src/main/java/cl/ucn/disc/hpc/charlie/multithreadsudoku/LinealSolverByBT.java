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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lineal Solver using Backtracking
 */
public class LinealSolverByBT {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(App.class);

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

        // initial time
        long t0 = System.currentTimeMillis();

        // initialize the resolution
        if (solve()) {

            log.info("Sudoku solved with");
            return System.currentTimeMillis() - t0;
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
    public boolean isInRow(int row, int number) {

        // search the number
        for (int i = 0; i < this.nCells; i++) {

            if (grid[row][i].getValue() == number) {
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
    public boolean isInCol(int col, int number) {

        // search the number
        for (int i = 0; i < this.nCells; i++) {

            if (grid[i][col].getValue() == number) {
                return true;

            }
        }

        return false;
    }

    // TODO: fix the method to search by Sector

    /**
     * Verify if the number exist in the same Block
     *
     * @param row
     * @param col
     * @param number
     * @return
     */
    public boolean isInBox(int row, int col, int number) {

        int currentRow = row - (row % 3);
        int currentCol = col - (col % 3);

        for (int i = currentRow; i < currentRow; i++) {
            for (int j = currentCol; j < currentCol; j++) {

                if (grid[i][j].getValue() == number) {
                    return true;

                }
            }
        }
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
    public boolean isAcceptable(int row, int col, int number) {

        return !isInRow(row, number) &&
                !isInCol(col, number) &&
                !isInBox(row, col, number);
    }

    /**
     * Principal method to solve recursively
     *
     * @return
     */
    public boolean solve() {

        // search in all the Grid
        for (int row = 0; row < this.nCells; row++) {
            for (int col = 0; col < this.nCells; col++) {

                // find the Cell wihtout values
                if (this.grid[row][col].getValue() == this.emptyCell) {

                    for (int number = 1; number <= this.nCells; number++) {

                        // verify if an possible new value
                        if (isAcceptable(row, col, number)) {
                            this.grid[row][col].setValue(number);

                            // recursive call
                            if (solve()) {

                                return true;
                            } else {

                                this.grid[row][col].setValue(this.emptyCell);
                            }
                        }
                    }
                    // no empty Cell
                    return false;
                }
            }
        }
        // no more Cell to travel
        return true;
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
