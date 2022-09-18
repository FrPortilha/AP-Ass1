import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
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
     * This method computes question 1 and prints the result
     * 
     * @param input string from text file
     */
    public static void question1(String input) {
        String[] values = input.split("[,]", 0);
        int nCities = Integer.parseInt(values[0]);
        int nStops = Integer.parseInt(values[1]);
        getComb(nCities, nStops, values);
        //System.out.print(combinations);
    }

    /**
     * This method computes question 2 and prints the result
     * 
     * @param input string from text file
     */
    public static void question2(String input) {
        String[] types = input.split("[, ]", 0);
        System.out.println(count(types, types.length - 1));
    }

    /**
     * This method compUtes question 3 and prints the result
     * 
     * @param input string from text file
     */
    public static void question3(String input) {
        int number = Integer.parseInt(input);
        System.out.println(doCheops(number));
    }

    /**
     * This method computes question 4 and prints the result
     * 
     * @param input string from text file
     */
    public static void question4(String input) {
        String words = getWords(input);
        System.out.println(words);
    }

    public static void question5(String input) {
        String[] vowels = { "a", "e", "i", "o", "u" };
        int totalCount = 0;
        for (String vowel : vowels) {
            totalCount += countVowelIn(vowel, input.toLowerCase());
        }
        System.out.println(totalCount);
    }

    /**
     * This method returns an array of strings with of all possible ordered
     * combinations of the given cities
     * 
     * @param values the array of strings with the number of total cities, number of
     *               stops and the numbers of the cities
     * @return combinations the array of strings with the ordered combination of the
     *         cities names
     */
    private static String getComb(int N, int r, String[] values) {
        List<int[]> combList = new ArrayList<>();
        doCombs(combList, N - 1, 0, 0, new int[r]);
        String combString = "";
        for (int i = 0; i < combList.size(); i++) {
            String citiesComb = "";
            for (int j = 0; j < r; j++) {
                citiesComb = citiesComb + values[2 + combList.get(i)[j]];
            }
            permutations(citiesComb, "");
            combString += citiesComb + ",";
        }
        return combString;
    }

    private static void doCombs(List<int[]> combList, int N, int index, int n, int[] comb) {
        if (index == comb.length) {
            //List<Integer> list = new ArrayList<>();
            //for( int i = 0; i <comb.length;i++){
            //    list.add(comb[i]);
            //}
            //System.out.println("Lists : " + list);
            //List<int[]> permutations = new ArrayList<>();
            //permute(list, permutations, new int[comb.length], 0);
            //combList.addAll(permutations);
            int[] c = comb.clone();
            combList.add(c);
        } else if (n <= N) {
            comb[index] = n;
            doCombs(combList, N, index + 1, n + 1, comb);
            doCombs(combList, N, index, n + 1, comb);
        }
    }

    //private static void permute(List<Integer> list, List<int[]> permutations, int[] iter, int index){
    //    if(list.size() == 0){
    //        int[] p = iter.clone();
    //        System.out.println(Arrays.toString(p));
    //        permutations.add(p);
    //        return;
    //    }
    //    for(int i = 0; i < list.size();i++){
    //        System.out.println();
    //        iter[index] = list.get(i);
    //        list.remove(i);
    //        permute(list, permutations, iter, index + 1);
    //        
    //    }
    // }
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
     * This is solved by recursion
     * 
     * @param types the array of strings to be analysed
     * @param index the index of the string being analysed and counted
     * @return the total count of occurances of strings equal to the first
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
     * This method retuns the cheops number of a triangle with side length number
     * This is solved by recursion
     * 
     * @param number the length of the cheops triangle
     * @return the total number of balls in the cheops triangle
     */
    private static int doCheops(int number) {
        if (number == 1)
            return 1;
        return number + doCheops(number - 1);
    }

    private static String getWords(String input) {
        //String clean = clean(input);
        String[] numbers = input.split("[,]", 0);
        int nul = countVowelIn("1", input) + countVowelIn("0", input);
        int[] choices = options(numbers, nul);
        int[] initComb = initComb(numbers, nul);
        //System.out.println("Initial comb : " + Arrays.toString(initComb) + " choices : " + Arrays.toString(choices));
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
        return words;
    }

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
    //private static String clean(String input){
    //    String[] unwanted = {"0","1"};
    //    for (String s : unwanted) {
    //        while(input.contains(s)){
    //            input = input.substring(0, input.indexOf(s)) + input.substring(input.indexOf(s)+1);
    //        }
    //    }
    //    return input;
    //}
    private static int[] initComb(String[] numbers, int nul) {
        int[] initComb = new int[numbers.length - nul];
        int[] key = new int[] { 0, 0, 1, 4, 7, 10, 13, 16, 20, 23 };
        int index = 0;
        for (int i = 0; i < numbers.length; i++) {
            int n = Integer.parseInt(numbers[i]);
            if (n != 0 && n != 1) {
                initComb[index] = key[n];
                index++;
            }
        }
        return initComb;
    }

    /**
     * This method computes how many letters each number corresponds to and returns
     * it in an array
     * 
     * @param numbers of the phonne number to be checked
     * @return an array with the number of letters each number corresponds to
     */
    private static int[] options(String[] numbers, int nul) {
        int[] choices = new int[numbers.length - nul];
        int[] key = new int[] { 0, 0, 3, 3, 3, 3, 3, 4, 3, 4 };
        int index = 0;
        for (int i = 0; i < numbers.length; i++) {
            int n = Integer.parseInt(numbers[i]);
            if (n != 0 && n != 1) {
                choices[index] = key[n];
                index++;
            }
        }
        return choices;
    }

    /**
     * This method counts the number of occurences of a specific character in a
     * string
     * This is solved by recursion
     * 
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
