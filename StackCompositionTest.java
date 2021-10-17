// StackCompositionTest.java
// Class StackCompositionTest.
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class StackCompositionTest
{
   static MyStack<Integer> stack = new MyStack<>();
   private static final String QUIT = "quit";
   private static final String VAR = "var";
   private static final String CLEAR = "clear";

   private static ArrayList<String> variableNames = new ArrayList<String>();
   private static ArrayList<Double> variableValues = new ArrayList<Double>();
   public static void main(String[] args) throws IOException {

      String[] expressions = {"2 3 + 7 *",
              "2 6 + 9 /",
              "14 2 + 160 /",
              "99 2 + 50 / 24 -",
              "100 4 / 3 * 20 +",
              "14 3 * 19 + 12 / 3 + 7 *",
              "23 7 + 10 * 14 + 19 - 22 * 16 /"

      };

      for (String expression : expressions){
         System.out.println(expression);
         evaluate(expression);
      }


      // use push method
      stack.push(-1);
      System.out.println("pushed -1");
      stack.print();
      stack.push(0);
      System.out.println("pushed 0");
      stack.print();
      stack.push(1);
      System.out.println("pushed 1");
      stack.print();
      stack.push(5);
      System.out.println("pushed 5");
      stack.print();
      // remove items from stack

         int removedItem;
         while (!stack.isEmpty())
         {
            try {
               removedItem = stack.pop();
               System.out.printf("%n%d popped%n", removedItem);
               stack.print();

            } catch (NullPointerException ex) {
               System.err.println(ex.getMessage());
            }

         }

   }
//   public static void evaluate(String operator){
//      int secondElement = Integer.parseInt(String.valueOf(stack.pop()));
//      int firstElement = Integer.parseInt(String.valueOf(stack.pop()));
//
//      int returnValue = 0;
//      String operators = "+-*/";
//
//      //for (String s : )
//      }
//




   public static void evaluate(String s) {

      if(s.equals(QUIT)) {
         System.exit(0);
      } else if(s.equals(VAR)) {
         for(int i = 0; i < variableNames.size(); i++) {
            System.out.println("\t" + variableNames.get(i) + ": " + variableValues.get(i));
         }
      } else if(s.equals(CLEAR)) {
         variableNames.clear();
         variableValues.clear();
      } else {
         calculate(s);
      }
   }

   public static void calculate(String s) {
      ArrayList<String> input = new ArrayList<String>();
      Collections.addAll(input, s.trim().split(" "));
      input.removeAll(Arrays.asList(null, ""));
      if(input.size() == 1) {
         double d = getValue(s);
         if(!Double.isNaN(d)) System.out.println("\t" + d);
         return;
      }

      boolean hasVariableAssignment = input.contains("=");
      String var = "";
      int startIndex = 0;
      if(hasVariableAssignment) {
         var = input.get(0);
         startIndex = 2;
      } else {
         startIndex = 0;
      }
      for(int i = startIndex; i < input.size(); i++) {
         String n = input.get(i);
         if(isOperator(n)) {
            if(stack.size() > 1) {
               stack.push((int) doOperation(n));
            } else {
               System.out.println("\tOperation not valid!");
               return;
            }
         } else {
            int d = (int) getValue(n);
            if(!Double.isNaN(d)) {
               stack.push(d);
            } else {
               return;
            }
         }
      }
      double result = stack.pop();
      if(stack.size() > 0) {
         System.out.println("\tOperation not valid!");
         stack = null;
         return;
      }

      if(hasVariableAssignment) {
         replaceAddValue(var, result);
      }
      System.out.println("\t" + result);
   }

   public static double doOperation(String s) {
      char op = s.charAt(0);
      double a = stack.pop();
      double b = stack.pop();
      switch (op) {
         case '+':
            return b + a;
         case '-':
            return b - a;
         case '*':
            return b * a;
         case '/':
            return b / a;
         default:
            return Double.NaN;
      }
   }

   public static double getValue(String s) {
      try {
         return Double.parseDouble(s);
      } catch (NumberFormatException ex) {
         try {
            return variableValues.get(variableNames.indexOf(s));
         } catch (Exception e) {
            System.out.printf("\t%s not found.\n", s);
            return Double.NaN;
         }
      }
   }

   public static void replaceAddValue(String var, double value) {

      if(variableNames.contains(var)) {
         int index = variableNames.indexOf(var);
         variableValues.set(index, value);
      } else {
         variableNames.add(var);
         variableValues.add(value);
      }
   }

   public static boolean isOperator(String s) {
      char c = s.charAt(0);
      return c == '+' || c == '-' || c == '/' || c == '*';
   }
   }



