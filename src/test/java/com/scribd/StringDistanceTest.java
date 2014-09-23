package com.scribd;

import junit.framework.Assert;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.junit.Test;

public class StringDistanceTest {
  
  @Test
  public void testLUDF() {
    Levenshtein example = new SmartLevenshtein();
    Assert.assertEquals(2, example.evaluate(new Text("book"), new Text("back"), 3).get());
    Assert.assertEquals(3, example.evaluate(new Text("CA"), new Text("ABC"), 3).get());
  }
  
  @Test
  public void testLUDFNullCheck() {
    Levenshtein example = new SmartLevenshtein();
    Assert.assertNull(example.evaluate(null, null), 1);
    Assert.assertNull(example.evaluate(null, new Text("word"), 1));
    Assert.assertNull(example.evaluate(new Text("apple"), null), 20);
  }

  @Test
  public void testLUDFMaxCheck() {
    Levenshtein example = new SmartLevenshtein();
    Assert.assertEquals(1, example.evaluate(new Text("book"), new Text("back"), 1).get());
    Assert.assertEquals(2, example.evaluate(new Text("CA"), new Text("ABC"), 2).get());
  }
}



