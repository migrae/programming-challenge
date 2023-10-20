//package main.java.de.bcxp.challenge;
package de.bcxp.challenge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class JsonReader {
  
  private String file_;
  private String valueOne_;
  private String valueTwo_;
  private int keyJump_;
  private String keyCategory_;
  private Map<String, Pair<Float, Float>> data_ = new HashMap<>();

  public JsonReader(String file, String valueOne, String valueTwo) {
    this.file_ = file;
    this.valueOne_ = valueOne;
    this.valueTwo_ = valueTwo;
  }

/*   public Map<String, Pair<Float, Float>> read() {
    try (BufferedReader br = new BufferedReader(new FileReader(file))) {

        if (columnValueOne == -1 || columnValueTwo == -1) {
            System.err.println("Invalid Column Names");
            return null;
        }

        while ((line = br.readLine()) != null) {
            if (loadData(line) == -1) {
                System.err.println("Could not transform Values in line: " + line);
                return null;
            }
        }
        if (data.size()	== 0) {
             System.err.println("Empty csv file");
        }
    } catch (IOException e) {
        System.err.println("Can't open file");
        return null;
    }
    return data;
  } */

/*   public int getKeyJump(){
    BufferedReader br = new BufferedReader(new FileReader(this.file_));
    String line;
    while(line = br.readLine(this.file_) != null && !line.contains("\"")){
      System.out.printf("in Jumpline %s%n", line);
      String cleanLine = line.replaceAll("\\s", "");
    }
  } */

  public int getKeyJump() {
    try{
      BufferedReader br = new BufferedReader(new FileReader(this.file_));
      String line;
      while ((line = br.readLine()) != null) {
          if (line.contains("\"")) {
              break;
          }
          System.out.printf("in Jumpline %s%n", line);
          //String cleanLine = line.replaceAll("\\s", "");
      }
      this.keyCategory_ = getValueInsideQuotes(line);
      System.out.printf("KeyCategory %s%n", this.keyCategory_);
      while ((line = br.readLine()) != null) {
          if (line.contains(this.keyCategory_)) {
              break;
          }
          this.keyJump_++;
        }
        this.keyJump_++;
      System.out.println(this.keyJump_);
    } catch(IOException e) {
        System.err.println("Can't open file");
        return 1;
    }
    return 0;
  }

  private String getValueInsideQuotes(String line) {
        Pattern pattern = Pattern.compile("\"([^\"]*)\"");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null; 

  }
}
