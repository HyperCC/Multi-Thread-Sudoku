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
 * Principal unique Sudoku grid class
 */
public final class SudokuGrid {

    /**
     * The Logger
     */
    private static final Logger log = LoggerFactory.getLogger(App.class);

    /**
     * The grid dimension
     */
    private int nCells;

    /**
     * The principal matriz of the sudoku
     */
    private Cell[][] grid;

    /**
     * The constructor
     *
     * @param nCells
     */
    public SudokuGrid(int nCells, Cell[][] grid) {

        this.nCells = nCells;
        this.grid = grid;
    }

    /**
     * Print a complete grid
     *
     * @param grid
     */
    public String printSudoku(Cell[][] grid) {

        // the Stringbuilder to all Grid
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid.length; j++) {
                output.append(grid[i][j].getValue()).append(" ");
            }
            output.append("\n");
        }

        // formated Grid in a String
        return output.toString();
    }

    /**
     * Getter to the nBlocks
     *
     * @return the nBlocks to the Grid
     */
    public int getnCells() {

        return nCells;
    }

    /**
     * Getter to the Grid
     *
     * @return the nBlocks to the Grid
     */
    public Cell[][] getGrid() {

        return this.grid;
    }
}
