package edu.grinnell.csc207.main;

import edu.grinnell.csc207.blocks.AsciiBlock;
import edu.grinnell.csc207.blocks.HComp;
import edu.grinnell.csc207.blocks.VComp;
import edu.grinnell.csc207.blocks.Rect;
import edu.grinnell.csc207.blocks.VAlignment;
import edu.grinnell.csc207.blocks.HAlignment;
import edu.grinnell.csc207.blocks.OurBlock;
import edu.grinnell.csc207.blocks.Surrounded;

import java.io.PrintWriter;

/**
 * Create and print an amazing 80x24 ASCII artwork.
 *
 * @author Mina Bakrac
 * @author Anthony Castleberry
 */
public class Art80x24 {
  /**
   * Create the artwork.
   *
   * @param args
   *   Command-line arguments (currently ignored).
   *
   * @exception Exception
   *   If something goes wrong with one of the underlying classes.
   */
  public static void main(String[] args) throws Exception {
    PrintWriter pen = new PrintWriter(System.out, true);

    AsciiBlock hedge = new Rect('H', 3, 24);

    AsciiBlock hDash = new Rect('H', 4, 4);

    AsciiBlock h = new HComp(VAlignment.CENTER, new AsciiBlock[] {hedge, hDash, hedge});

    AsciiBlock eEdge = new Rect('E', 3, 24);

    AsciiBlock eLeg = new Rect('E', 7, 4);

    AsciiBlock eSpace = new Rect(' ', 7, 6);

    AsciiBlock eRight = new VComp(HAlignment.CENTER,
                                  new AsciiBlock[] {eLeg, eSpace, eLeg, eSpace, eLeg});

    AsciiBlock e = new HComp(VAlignment.CENTER, new AsciiBlock[] {eEdge, eRight});

    AsciiBlock lLong = new Rect('L', 3, 24);

    AsciiBlock lShort = new Rect('L', 7, 4);

    AsciiBlock l = new HComp(VAlignment.BOTTOM, new AsciiBlock[] {lLong, lShort});

    AsciiBlock o = new Surrounded(new Surrounded(new OurBlock(new Rect('O', 4, 20)), 'O'), 'O');

    AsciiBlock space = new Rect(' ', 5, 24);

    AsciiBlock art = new HComp(VAlignment.BOTTOM,
                              new AsciiBlock[] {h, space, e, space, l, space, l, space, o});


    AsciiBlock.print(pen, art);
    pen.close();
  } // main(String[])
} // class Art80x24
