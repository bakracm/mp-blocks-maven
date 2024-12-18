package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The horizontal composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Mina Bakrac
 * @author Anthony Castleberry
 */
public class HComp implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The blocks.
   */
  AsciiBlock[] blocks;

  /**
   * How the blocks are aligned.
   */
  VAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a horizontal composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param leftBlock
   *   The block on the left.
   * @param rightBlock
   *   The block on the right.
   */
  public HComp(VAlignment alignment, AsciiBlock leftBlock,
      AsciiBlock rightBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {leftBlock, rightBlock};
  } // HComp(VAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a horizontal composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public HComp(VAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // HComp(Alignment, AsciiBLOCK[])

  // +---------+-----------------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Get one row from the block.
   *
   * @param i the number of the row
   *
   * @return row i.
   *
   * @exception Exception
   *   if i is outside the range of valid rows.
   */
  public String row(int i) throws Exception {

    String stringrow = new String();

    if (this.align.equals(VAlignment.TOP)) {
      for (int j = 0; j < this.blocks.length; j++) {
        if (i < this.blocks[j].height()) {
          stringrow = stringrow.concat(this.blocks[j].row(i));
        } else {
          stringrow = stringrow.concat(" ".repeat(this.blocks[j].width()));
        } // if checks if row is less than the height of the block
      } // for loops through blocks array
    } else if (this.align.equals(VAlignment.CENTER)) {
      for (int j = 0; j < this.blocks.length; j++) {
        int diff = this.height() - this.blocks[j].height();
        if (i >= diff / 2 && i < diff / 2 + this.blocks[j].height()) {
          stringrow = stringrow.concat(this.blocks[j].row(i - diff / 2));
        } else {
          stringrow = stringrow.concat(" ".repeat(this.blocks[j].width()));
        } // If the row should be empty or taken from the block
      } // for loops through blocks array
    } else if (this.align.equals(VAlignment.BOTTOM)) {
      for (int j = 0; j < this.blocks.length; j++) {
        int diff = this.height() - this.blocks[j].height();
        if (i >= diff) {
          stringrow = stringrow.concat(this.blocks[j].row(i - diff));
        } else {
          stringrow = stringrow.concat(" ".repeat(this.blocks[j].width()));
        } // if the row should be empty or taken from the block
      } // for loops through blocks array
    } // if checks what the alignment is
    return stringrow;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int height = this.blocks[0].height();
    for (int i = 1; i < this.blocks.length; i++) {
      if (height < this.blocks[i].height()) {
        height = this.blocks[i].height();
      } // if height of block is less than the block after it
    } // for loops through blocks array
    return height;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    int width = 0;
    for (int i = 0; i < this.blocks.length; i++) {
      width += this.blocks[i].width();
    } // for loops through blocks array
    return width;
  } // width()

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(AsciiBlock other) {
    return ((other instanceof HComp) && (this.eqv((HComp) other)));
  } // eqv(AsciiBlock)

  /**
   * Determine if another block is structurally equivalent to this block.
   *
   * @param other
   *   The block to compare to this block.
   *
   * @return true if the two blocks are structurally equivalent and
   *    false otherwise.
   */
  public boolean eqv(HComp other) {

    int inputLength = 0;

    if (this.blocks.length < ((HComp) other).blocks.length) {
      inputLength = this.blocks.length;
    } else {
      inputLength = ((HComp) other).blocks.length;
    } // if


    for (int i = 0; i < inputLength; i++) {
      if (!(this.blocks[i].eqv(((HComp) other).blocks[i]))) {
        return false;
      } // if
    } // for
    return this.align == ((HComp) other).align;
  } // eqv(Grid)
} // class HComp
