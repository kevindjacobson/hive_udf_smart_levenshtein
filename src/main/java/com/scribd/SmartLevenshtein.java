/*
* Levenshtein Distance implementation from
* http://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Levenshtein_distance
*/

package com.scribd;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.udf.UDFType;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;

@UDFType(deterministic = true)
@Description(
  name="smart_levenshtein",
  value="smart_levenshtein('book', 'cook', 2) -> 1.",
  extended="SELECT smart_levenshtein(col1, col2, max_dist) from foo;")
class SmartLevenshtein extends UDF {

    /**
     * Compute the Levenshtein distance between the two strings. With a Max!
     */
    
    public static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

    public IntWritable evaluate(Text col1, Text col2, Text col3) {
        if (col1 == null || col2 == null || col3 == null) {
            return null;
        }
        String str1 = col1.toString();
        String str2 = col2.toString();
        int max_dist = Integer.parseInt(col3.toString());
        int[][] distance = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++)
            distance[i][0] = i;
        for (int j = 1; j <= str2.length(); j++)
            distance[0][j] = j;

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                distance[i][j] = minimum(
                        distance[i - 1][j] + 1,
                        distance[i][j - 1] + 1,
                        distance[i - 1][j - 1]+ ((str1.charAt(i - 1) == str2.charAt(j - 1)) ? 0 : 1));
                if(distance[i][j] > max_dist) {
                    return new IntWritable(Math.max(str1.length(), str2.length()) + 1);
                }
            }
        }

        return new IntWritable(distance[str1.length()][str2.length()]);    
    }
}
    
