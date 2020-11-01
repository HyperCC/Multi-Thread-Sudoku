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

import java.io.*;
import java.util.Arrays;

/**
 * Class to read and define the txt sudoku
 */
public final class InputFile {

    /**
     * The file with the sudoku to charge
     */
    private final File archivo;

    private int[][] grid;

    /**
     * Principal Constructor
     *
     * @param filePath
     */
    public InputFile(String filePath) {

        this.archivo = new File(filePath);
        this.grid = readFile();
    }

    /**
     * Read the file and initialize the sudoku
     */
    public int[][] readFile() {

        // the txt file reader
        try {

            // open the file to read
            FileReader fr = new FileReader(this.archivo);
            // buffer to read the file line by line
            BufferedReader br = new BufferedReader(fr);

            // current linea and row
            String linea;
            int fila = 0;

            // initialize the principal grid
            int n = Integer.parseInt(br.readLine());
            int[][] currentGrid = new int[n][n];

            // read and split all the values until de end file
            while ((linea = br.readLine()) != null) {

                String[] numeros = linea.strip().split(" ");

                // poblate the sudoku
                for (int i = 0; i < numeros.length; i++) {

                    currentGrid[fila][i] = Integer.valueOf(numeros[i]);
                    fila++;
                }

                fila = 0;
                //Arrays.asList(numeros).forEach(System.out::println);
            }

            // Finally ever we have to close the file
            fr.close();

            return currentGrid;

            //Arrays.asList(currentGrid).forEach(System.out::println);

            // catch BufferReader exceptions
            // catch FileReader exceptions
        } catch (IOException e) {

            e.printStackTrace();
        }

        // lenght 0 if exist errors
        return new int[0][0];
    }

}
