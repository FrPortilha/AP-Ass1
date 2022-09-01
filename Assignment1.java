import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Assignment1 {
    
    public static void main(String[] args) throws FileNotFoundException{
        int questionNumber = Integer.parseInt(args[0]);
        String inputFileName = args[1];
        
        Scanner inputFile;
        switch(questionNumber){
            case 1:
            inputFile = new Scanner(new File(inputFileName));
            String word = inputFile.nextLine ();
            question1(word);
            inputFile.close();
            case 2:
            case 3:
            case 4:
            case 5:
            default:
            System.out.println("Invalid question number!");


        }
    }
    public static void question1(String input){
        System.out.println(input);
    }
}
