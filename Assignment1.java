import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Assignment1 {
    
    public static void main(String[] args) throws FileNotFoundException{
        int questionNumber = Integer.parseInt(args[0]);
        String inputFileName = args[1];
        
        Scanner inputFile;
        switch(questionNumber){
            case 1:
            inputFile = new Scanner(new File(inputFileName));
            String word1 = inputFile.nextLine ();
            question1(word1);
            inputFile.close();
            break;
            case 2:
            inputFile = new Scanner(new File(inputFileName));
            String word2 = inputFile.nextLine ();
            question2(word2);
            inputFile.close();
            break;
            case 3:
            inputFile = new Scanner(new File(inputFileName));
            String word3 = inputFile.nextLine ();
            question3(word3);
            inputFile.close();
            break;
            case 4:
            inputFile = new Scanner(new File(inputFileName));
            //String word4 = inputFile.nextLine ();
           // question4(word4);
            inputFile.close();
            break;
            case 5:
            break;
            default:
            System.out.println("Invalid question number!");


        }
    }
    /**
     * This method computes question 1 and prints the result
     * @param input string from text file 
     */
    public static void question1(String input){
        String[] values = input.split("[,]", 0);
        int nCities = Integer.parseInt(values[0]);
        int nStops = Integer.parseInt(values[1]);
        //int nComb = fact(nCities)/fact(nCities-nStops);
        String combinations = getComb(nCities, nStops,values);
        System.out.print(combinations);
    }
    /**
     * This method computes question 2 and prints the result
     * @param input string from text file
     */
    public static void question2(String input){
        String[] types = input.split("[, ]", 0);
        System.out.println(count(types, types.length-1));
    }
    /**
     * This method compUtes question 3 and prints the result
     * @param input string from text file
     */
    public static void question3(String input){
        int number = Integer.parseInt(input);
        System.out.println(doCheops(number));
    }
    
    /**
     * This method computes question 4 and prints the result 
     * @param input string from text file
     */
    //public static void question4(String input){
    //    String[] numbers = input.split("[,]",0);
    //    String words = doWords(numbers, numbers.length-1);
    //    System.out.println(words);
    //}

    public static void question5(String input){
        String[] vowels = {"a","e","i","o","u"};
        int totalCount = 0;
        for(String vowel : vowels){
            totalCount += countVowelIn(vowel,input);
        }
        System.out.println(totalCount);
    }

    /**
     * This method returns an array of strings with of all possible ordered combinations of the given cities
     * @param values the array of strings with the number of total cities, number of stops and the numbers of the cities
     * @return combinations the array of strings with the ordered combination of the cities names
     */
    private static String getComb(int N, int r, String[] values){
        List<int[]> combList = new ArrayList<>();
        doCombs(combList, N-1, 0, 0, new int[r]);
        String combString = "";
        for(int i = 0; i < combList.size() ; i++){
            String citiesComb = "";
            for(int j = 0; j < r; j++){
                citiesComb = citiesComb + values[2 + combList.get(i)[j]];
            }
            combString += citiesComb + ",";
        }
        return combString;
    }

    private static void doCombs(List<int[]> combList, int N, int index, int n, int[] comb){
        if(index == comb.length){
            int[] c = comb.clone();
            combList.add(c);
        }
        else if(n <= N){
            comb[index] = n;
            doCombs(combList, N, index+1, n+1, comb);
            doCombs(combList, N, index, n+1, comb);
        }
    }
    /**
     * This method computes the factorial of a number
     * @param number to compute factorial of
     * @return the factorial of the number
     */
    //private static int fact(int number){
    //    if(number <= 1) return 1;
    //    return number*fact(number-1);
    //}
    /**
     * This method counts how many strings in an array are equal to the first
     * This is solved by recursion
     * @param types the array of strings to be analysed
     * @param index the index of the string being analysed and counted
     * @return the total count of occurances of strings equal to the first
     */
    private static int count(String[] types, int index){
        if (index == 0) return 0;
        int c = 0;
        if (types[0].equals(types[index])){ c = 1;}
        return c + count(types, index - 1);
    }
    /**
     * This method retuns the cheops number of a triangle with side length number
     * This is solved by recursion
     * @param number the length of the cheops triangle
     * @return the total number of balls in the cheops triangle
     */
    private static int doCheops(int number){
        if (number == 1) return 1;
        return number + doCheops(number - 1);
    }

    //private static String[] doWords(String[] numbers, int index){
    //    int[] choices = options(numbers);
    //    int totalComb = multiply(choices);
    //    String computeWords
    //    String[] word
    //    return word + "," + doWords(numbers, index + 1); 
    //    
    //}
    /**
     * This method computes how many letters each number corresponds to and returns it in an array
     * @param numbers of the phonne number to be checked
     * @return an array with the number of letters each number corresponds to
     */
    //private static int[] options(String[] numbers){
    //    int[] choices = new int[numbers.length];
    //    for(int i = 0; i < numbers.length; i++) {
    //        if(numbers[i].equals(7)||numbers[i].equals(9)){
    //            choices[i] = 4;
    //        }
    //        else{ choices[i] = 3;}
    //        System.out.print(choices[i]);
    //    }
    //    return choices;
    //}
    ///**
    // * This method multiplies all elementes of an array 
    // * @param choices the array to be mutiplied
    // * @return the result of the multiplication
    // */
    //private static int multiply(int[] choices){
    //    int result = 1;
    //    for(int choice :choices){
    //        result *= choice;
    //    }
    //    return result;
    //}
    /**
     * This method counts the number of occurences of a specific character in a string
     * This is solved by recursion
     * @param vowel the specific character to be counted
     * @param input the string to be analysed
     * @return the total count of occurances
     */
    private static int countVowelIn(String vowel,String input){
        if(input.contains(vowel)){
            return 1 + countVowelIn(vowel, input.substring(input.indexOf(vowel)));
        }
        else{
            return 0;
        }
    }
}
