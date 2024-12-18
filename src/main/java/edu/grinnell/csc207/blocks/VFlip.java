package edu.grinnell.csc207.blocks;

/**
 * A vertically flipped ASCII block.
 *
 * @author Samuel A. Rebelsky
 * @author Mina Bakrac
 * @author Anthony Castleberry
 */
public class VFlip implements AsciiBlock {
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
  public VFlip(AsciiBlock original) {
    this.block = original;
  } // VFlip(AsciiBlock)

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
    return (this.block.row(this.height() - i - 1));
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
    try {
      if (other instanceof VFlip) {
        if (other.width() == this.width() && this.height() == other.height()) {
          for (int k = 0; k < this.height(); k++) {
            for (int j = 0; j < this.width(); j++) {
              if (this.row(k).charAt(j) != other.row(k).charAt(j)) {
                return false;
              } // if()
            } // for()
          } // for()
          if (!(this.block.eqv(((VFlip) other).block))) {
            return false;
          } // if
          return true;
        } // if()
      } // if()
      return false;
    } catch (Exception e) {
      return false;
    } // try/catch(e)
  } // eqv(AsciiBlock)
} // class VFlip
