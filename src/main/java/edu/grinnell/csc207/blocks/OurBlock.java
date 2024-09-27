package edu.grinnell.csc207.blocks;

/**
 * The outline of an ASCII block.
 *
 * @author Mina Bakrac
 * @author Anthony Castleberry
 */
public class OurBlock implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The original block.
   */
  AsciiBlock block;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new block with the specified contents.
   *
   * @param original
   *   The original block.
   */
  public OurBlock(AsciiBlock original) {
    this.block = original;
  } // HFlip(AsciiBlock)

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
   *   If the row is invalid.
   */
  public String row(int i) throws Exception {
    String stringrow = new String();
    String beginning = new String();
    String end = new String();
    if ((i == 0) || (i == (this.block.height()) - 1)) {
      stringrow = this.block.row(i);
    } // if()
    if ((i > 0) && (i < (this.block.height() - 1))) {
      beginning = this.block.row(i).substring(0, 1);
      end = this.block.row(i).substring(this.block.width() - 1);

      stringrow = beginning.concat(" ".repeat(this.block.width() - 2)).concat(end);
    } // if()
    return stringrow;
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return this.block.height();
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return this.block.width();
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
    return false;
  } // eqv(AsciiBlock)
} // class OurBlock
