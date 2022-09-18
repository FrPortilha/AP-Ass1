import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment1 {

    public static void main(String[] args) throws FileNotFoundException {
        int questionNumber = Integer.parseInt(args[0]);
        String inputFileName = args[1];

        Scanner inputFile;
        switch (questionNumber) {
            case 1:
                inputFile = new Scanner(new File(inputFileName));
                String word1 = inputFile.nextLine();
                question1(word1);
                inputFile.close();
                break;
            case 2:
                inputFile = new Scanner(new File(inputFileName));
                String word2 = inputFile.nextLine();
                question2(word2);
                inputFile.close();
                break;
            case 3:
                inputFile = new Scanner(new File(inputFileName));
                String word3 = inputFile.nextLine();
                question3(word3);
                inputFile.close();
                break;
            case 4:
                inputFile = new Scanner(new File(inputFileName));
                String word4 = inputFile.nextLine();
                question4(word4);
                inputFile.close();
                break;
            case 5:
                inputFile = new Scanner(new File(inputFileName));
                String word5 = inputFile.nextLine();
                question5(word5);
                inputFile.close();
                break;
            default:
                System.out.println("Invalid question number!");

        }
    }

    /**
     * This method computes question 1 and prints the result with all possible ordered combinations 
     * of the given cities. This is done by first sovling all possible combinations of the cities, 
     * and then printing out all possible permutations for each combination.
     * @param input string from text file.
     */
    public static void question1(String input) {
        String[] values = input.split("[,]", 0);
        int N = Integer.parseInt(values[0]);
        int r = Integer.parseInt(values[1]);
        List<int[]> combList = new ArrayList<>();
        doCombs(combList, N - 1, 0, 0, new int[r]);
        for (int i = 0; i < combList.size(); i++) {
            String citiesComb = "";
            for (int j = 0; j < r; j++) {
                citiesComb = citiesComb + values[2 + combList.get(i)[j]];
            }
            permutations(citiesComb, "");
        }
    }

    /**
     * This method computes question 2 and prints the result.
     * @param input string from text file
     */
    public static void question2(String input) {
        String[] types = input.split("[, ]", 0);
        System.out.println(count(types, types.length - 1));
    }

    /**
     * This method computes question 3 and prints the result, the cheops number.
     * @param input string from text file.
     */
    public static void question3(String input) {
        int number = Integer.parseInt(input);
        System.out.println(doCheops(number));
    }

    /**
     * This method computes question 4 and prints the result, all the possible words from the phone number, 
     * it ignores 0's, 1's and numbers that are repeated.
     * @param input string from text file.
     */
    public static void question4(String input) {
        String[] numbers = input.split("[,]", 0);
        int nul = countVowelIn("1", input) + countVowelIn("0", input) + countSame(numbers,0,0);
        int[] choices = options(numbers, nul);
        int[] initComb = initComb(numbers, nul);
        List<int[]> wordList = new ArrayList<>();
        doWords(wordList, choices, 0, 0, initComb, new int[initComb.length]);
        String words = "";
        for (int i = 0; i < wordList.size(); i++) {
            String word = "";
            for (int j = 0; j < choices.length; j++) {
                word = word + (char) (96 + wordList.get(i)[j]);
            }
            words += word;
            if(i<wordList.size()-1){
                words += ",";
            }
        }
        System.out.println(words);
    }
    /**
     * This method computes question 5 and prints the result, the number of vowels in the input file.
     * @param input string from text file.
     */
    public static void question5(String input) {
        String[] vowels = { "a", "e", "i", "o", "u" };
        int totalCount = 0;
        for (String vowel : vowels) {
            totalCount += countVowelIn(vowel, input.toLowerCase());
        }
        System.out.println(totalCount);
    }
    /**
     * This method computes all the possible combinations of cities represented by arrays of numbers.
     * This method is solved by recursion.
     * @param combList the list with the possible combinations.
     * @param N the number of total cities.
     * @param index the index of thr element in current combination that is being computed.
     * @param n the number representing the a city that is being registered in the current combination.
     * @param comb the current combination.
     */
    private static void doCombs(List<int[]> combList, int N, int index, int n, int[] comb) {
        if (index == comb.length) {
            int[] c = comb.clone();
            combList.add(c);
        } else if (n <= N) {
            comb[index] = n;
            doCombs(combList, N, index + 1, n + 1, comb);
            doCombs(combList, N, index, n + 1, comb);
        }
    }
    /**
     * This method prints all the possible permutations of a given string. This method is solved by recursion.
     * @param comb the string to be permutated and printed.
     * @param iter the current iteration of a permutation to be printed.
     */
    private static void permutations(String comb, String iter){
       if(comb.length()==0){
           System.out.print(iter+",");
           return;
       }
       for(int i = 0; i< comb.length(); i++){
           char c = comb.charAt(i);
           String next = comb.substring(0, i) + comb.substring(i+1);
           permutations(next, iter + c);
       }
    }
    /** 
     * This method counts how many strings in an array are equal to the first
     * This is solved by recursion.
     * @param types the array of strings to be analysed.
     * @param index the index of the string being analysed and counted.
     * @return the total count of occurances of strings equal to the first.
     */
    private static int count(String[] types, int index) {
        if (index == 0)
            return 0;
        int c = 0;
        if (types[0].equals(types[index])) {
            c = 1;
        }
        return c + count(types, index - 1);
    }
    /**
     * This method returns the cheops number of a triangle with side of length number.
     * This is solved by recursions.
     * @param number the length of the cheops triangle
     * @return the total number of balls in the cheops triangle
     */
    private static int doCheops(int number) {
        if (number == 1)
            return 1;
        return number + doCheops(number - 1);
    }
    /**
     * This method computes the possible combinations of letters for all words possible from the phone number. 
     * This method is solved by recursion.
     * @param wordList list of words that are possible from the phone number.
     * @param choices array with number of letters corresponding to each number.
     * @param index index of the digit that is being represented to a possible letter.
     * @param n number of choices that this index has already computed.
     * @param initComb the initial combination of the possible words.
     * @param comb the current combination that is being computed.
     */
    private static void doWords(List<int[]> wordList, int[] choices, int index, int n, int[] initComb, int[] comb) {
        if (index == comb.length) {
            int[] c = comb.clone();
            wordList.add(c);
        } else if (n < choices[index]) {
            comb[index] = initComb[index] + n;
            doWords(wordList, choices, index + 1, 0, initComb, comb);
            doWords(wordList, choices, index, n + 1, initComb, comb);
        }
    }
    /**
     * This method computes the initial combination of letters for the given phone number, it ignores 0's and 1's.
     * @param numbers of the phonne number to be checked.
     * @param nul number of 0's and 1's and equal consecutive numbers to ignore.
     * @return an array with the initial letters (in corresponding numbers).
     */    
    private static int[] initComb(String[] numbers, int nul) {
        int[] initComb = new int[numbers.length - nul];
        int[] key = new int[] { 0, 0, 1, 4, 7, 10, 13, 16, 20, 23 };
        int index = 0;
        for (int i = 0; i < numbers.length; i++) {
            int n = Integer.parseInt(numbers[i]);
            if (n != 0 && n != 1 ){
                if((i>0 && n!=Integer.parseInt(numbers[i-1]))||i==0){
                    initComb[index] = key[n];
                    index++;
                }
            }
        }
        return initComb;
    }
    /**
     * This method computes how many letters each number corresponds to and returns it in an array.
     * @param numbers of the phonne number to be checked.
     * @param nul number of 0's and 1's and equal consecutive numbers to ignore.
     * @return an array with the number of letters each number corresponds to.
     */
    private static int[] options(String[] numbers, int nul) {
        int[] choices = new int[numbers.length - nul];
        int[] key = new int[] { 0, 0, 3, 3, 3, 3, 3, 4, 3, 4 };
        int index = 0;
        for (int i = 0; i < numbers.length; i++) {
            int n = Integer.parseInt(numbers[i]);
            if (n != 0 && n != 1) {
                if((i>0 && n!=Integer.parseInt(numbers[i-1]))||i==0){
                    choices[index] = key[n];
                    index++;
                }
            }
        }
        return choices;
    }
    /**
     * This method counts how many conseccutive numbers are equal. This is solved by recursion.
     * @param numbers the numbers to be checked.
     * @param count the count of consecutive equal numbers.
     * @param index the index of the number being checked.
     * @return the count of consectutive equal numbers.
     */
    private static int countSame(String[] numbers, int count, int index){
        if(index == numbers.length -1) return count;
        else if(numbers[index].equals(numbers[index+1])){
            count++;
        }
         return countSame(numbers,count, index + 1);
    }
    /**
     * This method counts the number of occurences of a specific character in a
     * string. This is solved by recursion.
     * @param vowel the specific character to be counted
     * @param input the string to be analysed
     * @return the total count of occurances
     */
    private static int countVowelIn(String vowel, String input) {
        if (input.contains(vowel)) {
            return 1 + countVowelIn(vowel, input.substring(input.indexOf(vowel) + 1));
        } else
            return 0;
    }
}
