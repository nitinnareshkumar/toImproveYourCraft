import java.util.ArrayList;
import java.util.Collections;

/*********************************************************
 * Program to find the next int value for the number such that digits in the new
 * number are same as that of original number --
 *********************************************************/
public class TestNextValuePermutation {

  public static void main(String args[]) {

    // int number = 2147481647;
    int number = 6519327;
    // to print entire chain
    // while ( number>0){
    // number = getNextNumber(number);
    // System.out.println(number);
    // }

    // to just get the next number
    number = getNextNumber(number);
    System.out.println(number);

  }

  public static int getNextNumber(int number) {

    // check for 0 , single digit and int max value and non negative values
    if ((number == 0) || (number < 10) || (number >= Integer.MAX_VALUE)) {
      System.out.println("not a valid candidate to get a next higher number using same digits ");
    }

    // check for same digits
    char[] digitsInNumber = Integer.valueOf(number).toString().toCharArray();
    int sameDigits = 1;
    for (char c : digitsInNumber) {
      if (digitsInNumber[0] != c) {
        sameDigits = 0;
        break;
      }
    }
    if (sameDigits == 1) {
      System.out.println("not a valid candidate to get a next higher as all digits are same ");

    }

    // get the next number having the same digits

    char[] digitsInOrigNumber = Integer.valueOf(number).toString().toCharArray();
    int currentPosition = 2;
    int length = digitsInOrigNumber.length;
    char temp = 0;
    while (Integer.parseInt(new String(digitsInOrigNumber)) <= number && (currentPosition <= length)) {

      if (currentPosition == 2) {
        if (digitsInOrigNumber[length - currentPosition] < digitsInOrigNumber[length - currentPosition + 1]) {
          temp = digitsInOrigNumber[length - currentPosition];
          digitsInOrigNumber[length - currentPosition] = digitsInOrigNumber[length - currentPosition + 1];
          digitsInOrigNumber[length - currentPosition + 1] = temp;
        }
      } else {
        // get the immediate bigger digit from right

        // first get just next bigger digit
        int biggerDigitFound = 0;
        char charInConsideration = digitsInOrigNumber[length - currentPosition];
        for (int i = 1; i <= 9; i++) {
          int count = 0;
          int nextCharCount = 0;
          while (length - currentPosition + count < length) {
            nextCharCount = length - currentPosition + count;
            if (charInConsideration + i == digitsInOrigNumber[nextCharCount]) {

              char temp1 = digitsInOrigNumber[length - currentPosition];
              digitsInOrigNumber[length - currentPosition] = digitsInOrigNumber[length - currentPosition + count];
              digitsInOrigNumber[length - currentPosition + count] = temp1;
              biggerDigitFound = 1;
              i = 10;
              break;

            }
            count = count + 1;
          }
        }

        if (biggerDigitFound == 1) {
          sortCurrentpostiion(digitsInOrigNumber, currentPosition);

        }

      }

      currentPosition = currentPosition + 1;

    }

    if (Integer.parseInt(new String(digitsInOrigNumber)) <= number) {
      System.out.println("Bigger number with same digits not found ");
      return -1;
    }
    return Integer.parseInt(new String(digitsInOrigNumber));
  }

  public static void sortCurrentpostiion(char[] digitsInOrigNumber, int postion) {
    int length = digitsInOrigNumber.length;
    ArrayList<Character> tempCharArray = new ArrayList<Character>();
    for (int i = 1; i < postion; i++) {
      tempCharArray.add(digitsInOrigNumber[length - postion + i]);

    }
    Collections.sort(tempCharArray);
    for (int i = 1; i < postion; i++) {
      digitsInOrigNumber[length - postion + i] = tempCharArray.get(i - 1);

    }

  }
}