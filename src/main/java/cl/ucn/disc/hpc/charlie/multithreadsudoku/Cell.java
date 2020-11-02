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
 * The Cell class
 */
public class Cell {

    /**
     * The enum to define a Block
     */
    public enum Block {
        PRIMERO, SEGUNDO, TERCERO,
        CUARTO, QUINTO, SEXTO,
        SEPTIMO, OCTAVO, NOVENO
    }

    /**
     * The value
     */
    private int value;

    /**
     * The Block
     */
    private Block block;

    /**
     * The constructor to Cell
     *
     * @param value
     * @param block
     */
    public Cell(int value, Block block) {
        this.value = value;
        this.block = block;
    }

    /**
     * Get the value
     *
     * @return the Cell value
     */
    public int getValue() {
        return value;
    }

    /**
     * Get the block
     *
     * @return the actual Block to this Cell
     */
    public Block getBlock() {
        return block;
    }

    /**
     * Set to the block
     *
     * @param value to update
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Set to the block
     *
     * @param block to update
     */
    public void setBlock(Block block) {
        this.block = block;
    }
}
