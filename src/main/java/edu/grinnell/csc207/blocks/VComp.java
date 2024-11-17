package edu.grinnell.csc207.blocks;

import java.util.Arrays;

/**
 * The vertical composition of blocks.
 *
 * @author Samuel A. Rebelsky
 * @author Mina Bakrac
 * @author Anthony Castleberry
 */
public class VComp implements AsciiBlock {
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
  HAlignment align;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a vertical composition of two blocks.
   *
   * @param alignment
   *   The way in which the blocks should be aligned.
   * @param topBlock
   *   The block on the top.
   * @param bottomBlock
   *   The block on the bottom.
   */
  public VComp(HAlignment alignment, AsciiBlock topBlock,
      AsciiBlock bottomBlock) {
    this.align = alignment;
    this.blocks = new AsciiBlock[] {topBlock, bottomBlock};
  } // VComp(HAlignment, AsciiBlock, AsciiBlock)

  /**
   * Build a vertical composition of multiple blocks.
   *
   * @param alignment
   *   The alignment of the blocks.
   * @param blocksToCompose
   *   The blocks we will be composing.
   */
  public VComp(HAlignment alignment, AsciiBlock[] blocksToCompose) {
    this.align = alignment;
    this.blocks = Arrays.copyOf(blocksToCompose, blocksToCompose.length);
  } // VComp(HAlignment, AsciiBLOCK[])

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

    if (this.align.equals(HAlignment.LEFT)) {

      int counter = 0;

      for (int j = 0; j < this.blocks.length; j++) {
        for (int k = 0; k < this.blocks[j].height(); k++) {
          if (counter + k == i) {
            stringrow = this.blocks[j].row(k)
                        .concat(" ".repeat(this.width() - this.blocks[j].width()));
          } // if()
        } // for()
        counter += this.blocks[j].height();
      } // for()
    } //if()
    if (this.align.equals(HAlignment.CENTER)) {

      int counter = 0;
      int diff = 0;

      for (int j = 0; j < this.blocks.length; j++) {
        for (int k = 0; k < this.blocks[j].height(); k++) {
          if (counter + k == i) {

            diff = this.width() - this.blocks[j].width();

            stringrow = " ".repeat(diff / 2)
                        .concat(this.blocks[j].row(k)).concat(" ".repeat(diff / 2));

            if (stringrow.length() != this.width()) {
              stringrow = stringrow.concat(" ");
            } // if()
          } // if()
        } // for()
        counter += this.blocks[j].height();
      } // for()
    } // if(CENTER)
    if (this.align.equals(HAlignment.RIGHT)) {

      int counter = 0;

      for (int j = 0; j < this.blocks.length; j++) {
        for (int k = 0; k < this.blocks[j].height(); k++) {
          if (counter + k == i) {
            stringrow = " ".repeat(this.width() - this.blocks[j].width())
                        .concat(this.blocks[j].row(k));
          } // if()
        } // for()
        counter += this.blocks[j].height();
      } // for()
    } // if (RIGHT)
    return stringrow;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    int height = 0;

    for (int i = 0; i < this.blocks.length; i++) {
      height += this.blocks[i].height();
    } // for()
    return height;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {

    if (this.blocks.length == 0) {
      return 0;
    } // if()

    int width = this.blocks[0].width();
    for (int i = 1; i < this.blocks.length; i++) {
      if (width < this.blocks[i].width()) {
        width = this.blocks[i].width();
      } // if()
    } // for()
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
    return ((other instanceof VComp) && (this.eqv((VComp) other)));
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
  public boolean eqv(VComp other) {

    int inputLength = 0;

    if (this.blocks.length < ((VComp) other).blocks.length) {
      inputLength = this.blocks.length;
    } else {
      inputLength = ((VComp) other).blocks.length;
    } // if

    for (int i = 0; i < inputLength; i++) {
      if (!(this.blocks[i].eqv(((VComp) other).blocks[i]))) {
        return false;
      } // if
    } // for
    return this.align == ((VComp) other).align;
  } // eqv(Grid)
} // class VComp
