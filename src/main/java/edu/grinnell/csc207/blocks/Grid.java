package edu.grinnell.csc207.blocks;

/**
 * A grid of a single text block.
 *
 * @author Samuel A. Rebelsky
 * @author Mina Bakrac
 * @author Anthony Castleberry
 */
public class Grid implements AsciiBlock {
  // +--------+------------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * One element of the grid.
   */
  AsciiBlock element;

  /**
   * The number of times the element is repeated horizontally.
   */
  int hreps;

  /**
   * The number of times the element is repeated vertically.
   */
  int vreps;

  // +--------------+------------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Build a new grid with the specified arrangement.
   *
   * @param gridElement
   *   The element in the grid.
   * @param horizRepetitions
   *   The number of horizontal repetitions in the grid.
   * @param vertRepetitions
   *   THe number of vertical repetitions in the grid.
   */
  public Grid(AsciiBlock gridElement, int horizRepetitions,
      int vertRepetitions) {
    this.element = gridElement;
    this.hreps = horizRepetitions;
    this.vreps = vertRepetitions;
  } // Grid(AsciiBlock, int, int)

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
    return this.element.row(i % this.element.height()).repeat(hreps);
  } // row(int)

  /**
   * Determine how many rows are in the block.
   *
   * @return the number of rows
   */
  public int height() {
    return element.height() * vreps;
  } // height()

  /**
   * Determine how many columns are in the block.
   *
   * @return the number of columns
   */
  public int width() {
    return element.width() * hreps;
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
      if (other instanceof Grid) {
        if (other.width() == this.width() && this.height() == other.height()) {
          if (this.element.eqv(((Grid) other).element)) {
            for (int k = 0; k < this.height(); k++) {
              for (int j = 0; j < this.width(); j++) {
                if (this.row(k).charAt(j) != other.row(k).charAt(j)) {
                  return false;
                } // if()
              } // for()
            } // for()
            return true;
          } // if()
        } // if()
      } // if()
      return false;
    } catch (Exception e) {
      return false;
    } // try/catch(e)
  } // eqv(AsciiBlock)
} // class Grid
