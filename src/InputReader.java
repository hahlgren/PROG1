// Henrik Ahlgren heah6799

import java.util.Scanner;
import java.io.InputStream;
import java.util.ArrayList;

public class InputReader {

    private static ArrayList<InputStream> currentInputStream = new ArrayList<>();
    private Scanner input;

    public InputReader(){
        this(System.in);
    }


    public InputReader(InputStream inputForm) throws IllegalStateException {
        if (currentInputStream.contains(inputForm)) {
            throw new IllegalStateException("Instance of this class already exists");
        }
        currentInputStream.add(inputForm);
        input = new Scanner(inputForm);
    }


    public String readStringInput(String textForString){
        System.out.print(textForString + "?> ");
        return input.nextLine();
    }

    public int readIntegerInput(String textForInt){
        System.out.print(textForInt + "?> ");
        int inputValue = input.nextInt();
        input.nextLine();
        return inputValue;
    }

    public double readDoubleInput(String textForDouble){
        System.out.print(textForDouble + "?> ");
        double inputValue = input.nextDouble();
        input.nextLine();
        return inputValue;
    }

    public void closeInputReader(){
        input.close();
    }

}
