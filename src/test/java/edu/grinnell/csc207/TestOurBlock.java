package edu.grinnell.csc207;

import edu.grinnell.csc207.blocks.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestOurBlock {

    Line B = new Line("B");
    AsciiBlock Bbox = new Grid(B, 3, 3);

      @Test
  public void ThreeByThree() {
    assertEquals("""
                 BBB
                 B B
                 BBB
                 """,
        TestUtils.toString(new OurBlock(Bbox)));
  } // ThreeByThree()

  Line Hello = new Line("Hello");
  AsciiBlock Hi = new Grid(Hello, 2, 3);

  @Test
  public void HelloGrid() {
    assertEquals("""
                 HelloHello
                 H        o
                 HelloHello
                 """,
        TestUtils.toString(new OurBlock(Hi)));
  } // HelloGrid()

  @Test
  public void EmptyBlock() {
    assertEquals("""

                 """,
        TestUtils.toString(new OurBlock(new Line(""))));
  } // EmptyBlock()

  Line x = new Line("x");
  AsciiBlock xbox = new Grid(x, 2, 2);

  @Test
  public void TwoByTwo() {
    assertEquals("""
                 xx
                 xx
                 """,
        TestUtils.toString(new OurBlock(xbox)));
  } // TwoByTwo())

  

  @Test
  public void SingleChar() {
    assertEquals("""
                 x
                 """,
        TestUtils.toString(new OurBlock(x)));
  } // SingleChar()

  @Test
  public void OneLine() {
    assertEquals("""
                 Hello
                 """,
        TestUtils.toString(new OurBlock(Hello)));
  } // OneLine()

  @Test
  public void SurroundedBlock() {
    assertEquals("""
                 xxxxx
                 x   x
                 x   x
                 x   x
                 xxxxx
                 """,
        TestUtils.toString(new OurBlock(new Surrounded(Bbox, 'x'))));
  } // SurroundedBlock()



    
}
