package util;

import java.util.Scanner;

public class UtilMenu {
   private static final String DELIMETER = ",";
   static int MENU_SIZE = 2;
   static int MENU_QUIT = MENU_SIZE;
   static Scanner scanner = null;

   public static void showMenuOptions() {
      System.out.println("=============================================");
      System.out.println("Simple Bytecode Engineering Example Program");
      System.out.println("=============================================");
      System.out.println("\tMenu Options:");
      System.out.println("\t1. Enter the class you want to modify followd by field name");
      System.out.println("\t2. Exit the program");
      System.out.println("=============================================");
      System.out.print("Enter your input:");
   }

   public static int getOption() {
      scanner = new Scanner(System.in);
      int input = scanner.nextInt();

      if (input < 0 || input > MENU_SIZE) {
         System.out.println("You have entered an invalid selection, please try again\n");
      } else if (input == MENU_QUIT) {
         System.out.println("You have quit the program\n");
         System.exit(1);
      } else {
         System.out.println("You have entered " + input + "\n");
         return input;
      }
      return 0;
   }

   public static String[] getArguments() {
      scanner = new Scanner(System.in);
      String input = null;

      while (scanner.hasNextLine()) {
         input = scanner.nextLine();
         System.out.println(input);
         if (input != null)
            break;
      }

      if (input != null && !input.trim().isEmpty()) {
         String[] tokens = input.trim().split(DELIMETER);
         for (int i = 0 ; i < tokens.length; i++) {
            tokens[i] = tokens[i].trim();
         }
         return tokens;
      }
      return null;
   }
}
