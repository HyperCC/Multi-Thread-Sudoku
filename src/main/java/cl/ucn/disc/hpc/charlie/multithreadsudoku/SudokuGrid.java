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

public final class SudokuGrid {

    /**
     * The grid dimension
     */
    int nBlocks;

    /**
     * The principal matriz of the sudoku
     */
    int[][] grid;

    /**
     * The constructor
     *
     * @param nBlocks
     */
    public SudokuGrid(int nBlocks) {
        this.nBlocks = nBlocks;
        this.grid = new int[nBlocks][nBlocks];

        for (int i = 0; i < this.nBlocks; i++) {
            for (int j = 0; j < this.nBlocks; j++) {
                this.grid[i][j] = 0;
            }
        }
    }

    public void printSudoku() {
        for (int i = 0; i < this.nBlocks; i++) {
            for (int j = 0; j < this.nBlocks; j++) {
                System.out.print(this.grid[i][j] + " ");
            }
            System.out.println();
        }
    }

}
