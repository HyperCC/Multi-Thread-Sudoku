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

import cl.ucn.disc.hpc.charlie.mts.sudokupieces.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Class to read and define the txt sudoku
 */
public final class InputFile {

    /**
     * The Logger
     */
    private final Logger log = LoggerFactory.getLogger(App.class);

    /**
     * The file with the sudoku to charge
     */
    private final File archivo;

    /**
     * The grid generated with the txt
     */
    private Cell[][] grid;

    /**
     * The lenght to the complete grid
     */
    private int nCells;

    /**
     * Principal Constructor
     *
     * @param filePath
     */
    public InputFile(String filePath) {

        this.archivo = new File(filePath);
        this.grid = readFile();
        // the nBlocks is initialized in readFile() method
    }

    /**
     * Read the file and initialize the sudoku
     */
    public Cell[][] readFile() {

        log.debug("Initializing the Sudoku reader..");

        // the txt file reader
        try {

            // open the file to read
            FileReader fr = new FileReader(this.archivo);
            // buffer to read the file line by line
            BufferedReader br = new BufferedReader(fr);

            // initialize the principal nBlocks
            this.nCells = Integer.parseInt(br.readLine());
            log.debug("{}x{} dimension to the Sudoku inserted!", this.nCells, this.nCells);

            // inicialize the grid to return
            Cell[][] currentGrid = new Cell[this.nCells][this.nCells];

            log.debug("Grid from the reader generated!");

            // current linea and row
            String linea;
            int fila = 0;

            // read and split all the values until de end file
            while ((linea = br.readLine()) != null) {

                String[] numerosCrudos = linea.strip().split(" ");
                //Arrays.asList(numeros).forEach(System.out::print);
                String[] numeros = quitZeros(numerosCrudos);

                // poblate the sudoku
                for (int i = 0; i < numeros.length; i++) {

                    Cell cell = new Cell(
                            Integer.parseInt(numeros[i]),
                            assignBlock(fila, i)
                    );
                    currentGrid[fila][i] = cell;
                }

                fila++;
                //Arrays.asList(numeros).forEach(System.out::println);
            }

            // Finally ever we have to close the file
            fr.close();
            log.debug("Ending the Grid creation..");
            return currentGrid;

            // catch BufferReader exceptions
            // catch FileReader exceptions
        } catch (IOException e) {

            log.debug("Error in the txt reader. Details: {}", e.getMessage());
        }

        // lenght 0 if exist errors
        log.debug("Wrong assignment of values in Grid!");
        return new Cell[0][0];
    }

    /**
     * Block to assign in a Cell
     *
     * @param fila
     * @param columna
     * @return
     */
    public Cell.Block assignBlock(int fila, int columna) {

        if (fila < this.nCells / 3) {

            if (columna < this.nCells / 3) {
                return Cell.Block.PRIMERO;

            } else if (columna < (this.nCells / 3) * 2) {
                return Cell.Block.SEGUNDO;

            } else {
                return Cell.Block.TERCERO;
            }

        } else if (fila < (this.nCells / 3) * 2) {

            if (columna < this.nCells / 3) {
                return Cell.Block.CUARTO;

            } else if (columna < (this.nCells / 3) * 2) {
                return Cell.Block.QUINTO;

            } else {
                return Cell.Block.SEXTO;
            }

        } else {

            if (columna < this.nCells / 3) {
                return Cell.Block.SEPTIMO;

            } else if (columna < (this.nCells / 3) * 2) {
                return Cell.Block.OCTAVO;

            } else {
                return Cell.Block.NOVENO;
            }

        }
    }

    /**
     * Delete the zeros from the zudoku txt format (00 01 04 ..)
     *
     * @param numeros
     * @return formated numbers
     */
    public String[] quitZeros(String[] numeros) {

        String[] numerosFormateados = new String[numeros.length];

        for (int i = 0; i < numeros.length; i++) {
            if (numeros[i].charAt(0) == '0') {

                numerosFormateados[i] = numeros[i].substring(1);
            }
        }

        return numerosFormateados;
    }

    /**
     * Getter to the Grid
     *
     * @return the Grid
     */
    public Cell[][] getGrid() {

        return this.grid;
    }

    /**
     * Getter to the nBlocks
     *
     * @return the nBlocks to the Grid
     */
    public int getnCells() {

        return this.nCells;
    }

}
