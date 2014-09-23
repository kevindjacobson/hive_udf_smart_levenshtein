package com.scribd;

import junit.framework.Assert;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.junit.Test;

public class SmartLevenshteinTest {

  @Test
  public void testLUDFNullCheck() {
    SmartLevenshtein example = new SmartLevenshtein();
    Assert.assertNull(example.evaluate(null, null, new Text("1")));
    Assert.assertNull(example.evaluate(null, new Text("word"), new Text("3")));
    Assert.assertNull(example.evaluate(new Text("apple"), null, new Text("20")));
  }

  @Test
  public void testLUDFMaxCheck() {
    SmartLevenshtein example = new SmartLevenshtein();
    Assert.assertEquals(5, example.evaluate(new Text("book"), new Text("back"), new Text("1")).get());
    Assert.assertEquals(4, example.evaluate(new Text("CA"), new Text("ABC"), new Text("1")).get());
  }

  @Test
  public void testLUDFCheck() {
    SmartLevenshtein example = new SmartLevenshtein();
    Assert.assertEquals(2, example.evaluate(new Text("book"), new Text("back"), new Text("5")).get());
    Assert.assertEquals(3, example.evaluate(new Text("CA"), new Text("ABC"), new Text("4")).get());
  }
}



