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

/**
 * The principal class to execute the code
 */
public class App {

    /**
     * Principal main method
     *
     * @param args
     */
    public static void main(String[] args) {

        // read the txt with the sudoku to solve
        InputFile inputFile = new InputFile("inputSudokus/sudoku9x9.txt");

        // generate a Object SudokuGrid with necessary properties
        SudokuGrid sudokuGrid = new SudokuGrid(
                inputFile.getnCells(),
                inputFile.getGrid()
        );

        // print the sudoku to extracted
        sudokuGrid.printSudoku(sudokuGrid.getGrid());
    }

}
