// Henrik Ahlgren heah6799
import java.util.ArrayList;

public class DogRegister {

    private InputReader inputReader = new InputReader();
    private ArrayList<Dog> listOfDogs = new ArrayList<Dog>();
    private ArrayList<Owner> listOfOwners = new ArrayList<>();
    private boolean running;

    public static void main(String[] args) { new DogRegister().run(); }

    private void run(){
        startUp();
        runCommandoLoop();
        shutDown();
    }

    private void startUp(){
        running = true;
        System.out.println("Welcome to the dog register!");
        printAllCommands();

//        Dog rufus = new Dog("Rufus", "Tax", 11, 30);
//        listOfDogs.add(rufus);
//        Dog arufus = new Dog("Arufus", "Golden Retriever", 11, 10);
//        listOfDogs.add(arufus);
//        Dog brufus  = new Dog("Brufus", "Golden Retriever", 12, 40);
//        listOfDogs.add(brufus);
//        Dog tom  = new Dog("Tom", "Tax", 11, 40);
//        listOfDogs.add(tom);
//        Dog frufus  = new Dog("frufus", "Golden Retriever", 1, 4);
//        listOfDogs.add(frufus);
//        Owner kalle = new Owner("Kalle");
//        listOfOwners.add(kalle);
    }

    private void runCommandoLoop(){
        while (running){
            String command = readCommando();
            handleCommand(command);
        }
    }

    private void shutDown() {
        inputReader.closeInputReader();
    }

    private String readCommando(){
        return inputReader.readStringInput("Vilket kommando vill du välja");
    }


    private void handleCommand(String command){
        switch (command){
            case "register new dog":
                registerNewDog();
                break;
            case "list dogs":
                listDogs();
                break;
            case "increase age":
                increaseAge();
                break;
            case "remove dog":
                removeDog();
                break;
            case "register new owner":
                registerNewOwner();
                break;
            case "give dog":
                giveDog();
                break;
            case "list owners":
                listOwners();
                break;
            case "remove owned dog":
                removeOwnedDog();
                break;
            case "remove owner":
                removeOwnerFromRegister();
                break;
            case "exit":
                running = false;
                break;
            case "help":
                printAllCommands();
                break;
            default:
                System.out.println("Error: no such command");
        }
    }

    /// Skriver ut alla kommandon

    private void printAllCommands(){
        System.out.println("The possible commands are:\n" +
                "\tregister new dog\n" +
                "\tlist dogs\n" +
                "\tincrease age\n" +
                "\tremove dog\n" +
                "\tregister new owner\n" +
                "\tgive dog\n" +
                "\tlist owners\n" +
                "\tremove owned dog\n" +
                "\tremove owner\n" +
                "\texit\n" +
                "\thelp\n");
    }

    /// Register new dog

    private void registerNewDog(){
        String dogName = getStringInput("Name");
        String dogBreed = getStringInput("Breed");
        int dogAge = inputReader.readIntegerInput("Age");
        int dogWeight = inputReader.readIntegerInput("Weight");


        Dog dog = new Dog(dogName, dogBreed, dogAge, dogWeight);

        listOfDogs.add(dog);

        System.out.println(dogName + " added to the register");

    }

    /// List dogs

    private void listDogs(){
        if(listOfDogs.isEmpty()){
            System.out.println("Error: no dogs in register");
        } else {
            double wishedLength = inputReader.readDoubleInput("Smallest tail length to display");
            listDogsByTail(wishedLength);
        }
    }

    ///  Listar hundar i sorterad ordning och med viss svanslängd

    private void listDogsByTail(double tailLength){

        if(listOfDogs.size() == 0) {
            System.out.println("Error: no dog have a tail that long");
        } else {
            sortDogList();
            for(int i = 0; i < listOfDogs.size(); i++){
                String ownerName = null;
                if (listOfDogs.get(i).getTailLength() >= tailLength){
                    if(listOfDogs.get(i).hasOwner()) ownerName = listOfDogs.get(i).getOwner().getName();

                    System.out.println(listOfDogs.get(i) + " owned by " + ownerName);

                }
            }
        }
    }

    /// Hjälpmetoder för att lista hundar

    private void sortDogList(){
        for(int i = 0; i < listOfDogs.size()-1; i++){
            swapDogs(i,findSmallestDog(i));
        }
    }

    private int swapDogs(int indexFirstDog, int indexSecondDog){

        if(indexFirstDog < 0 || indexSecondDog < 0 || indexFirstDog == indexSecondDog){
            return 0;
        }

        Dog firstDog = listOfDogs.get(indexFirstDog);
        Dog secondDog = listOfDogs.get(indexSecondDog);

        listOfDogs.set(indexFirstDog, secondDog);
        listOfDogs.set(indexSecondDog, firstDog);

        return 1;
    }

    private Dog compareDogs(Dog firstDog, Dog secondDog){
        double firstDogTailLength = firstDog.getTailLength();
        double secondDogTailLength = secondDog.getTailLength();

        if(firstDogTailLength > secondDogTailLength){
            return secondDog;
        }
        else if(secondDogTailLength > firstDogTailLength) {
            return firstDog;
        } else {
            Dog dogToReturn = compareDogName(firstDog, secondDog);
            return dogToReturn;
        }

    }
    private Dog compareDogName(Dog firstDog, Dog secondDog){
        String firstDogName = firstDog.getName();
        String secondDogName = secondDog.getName();

        int lengthToCheck;

        if(firstDogName.length() > secondDogName.length()) lengthToCheck = secondDogName.length();
        else if (secondDogName.length() > firstDogName.length()) lengthToCheck = firstDogName.length();
        else lengthToCheck = firstDogName.length();

        for(int i = 0 ; i < lengthToCheck ; i++){
            int difference = firstDogName.toLowerCase().charAt(i) - secondDogName.toLowerCase().charAt(i);

            if(difference > 0) return secondDog;
            else if(difference < 0) return firstDog;
        }
        if(firstDogName.length() > secondDogName.length()) return secondDog;
        else if (secondDogName.length() > firstDogName.length()) return firstDog;
        else return firstDog;
    }

    private int findSmallestDog(int index){

        Dog smallestDog = listOfDogs.get(index);

        for(int i = index; i < listOfDogs.size() ; i++){
            Dog currentDog = listOfDogs.get(i);
            smallestDog = compareDogs(smallestDog,currentDog);
        }

        return listOfDogs.indexOf(smallestDog);
    }

    /// Increase age

    private void increaseAge() {
        int checkIfInvalid = 0;

        String inputData = inputReader.readStringInput("Enter the name of the dog?");

        for (Dog dog : listOfDogs) {
            if (dog.getName().equalsIgnoreCase(inputData)) {
                dog.updateAge(1);
                System.out.println(dog.getName() + " is now one year older");
                checkIfInvalid++;
            }
        }
        if(checkIfInvalid == 0){
            System.out.println("Error: no such dog");
        }
    }

    /// Remove dog

    private void removeDog(){

        String inputData = getAndTrimString("Enter the name of the dog");

        Dog dog = findDog(inputData);

        if(dog != null){
            if(dog.hasOwner()){
                dog.removeOwner();
            }
            listOfDogs.remove(dog);
            System.out.println(dog.getName() + " is removed from the register");
        } else {
            System.out.println("Error: no such dog");
        }

    }

    /// Register new owner

    private void registerNewOwner(){
        String ownerName = getStringInput("Name?");

        Owner owner = new Owner(ownerName);

        listOfOwners.add(owner);

        System.out.println(ownerName + " added to the register");
    }

    /// Give dog

    private void giveDog() {
        String dogName = getAndTrimString("Enter the name of the dog?");

        if (listOfDogs.contains(findDog(dogName))) {

            if (!findDog(dogName).hasOwner()) {
                String ownerName = getAndTrimString("Enter the name of the new owner?");


                if (listOfOwners.contains(findOwner(ownerName))) {
                    Dog dog = findDog(dogName);
                    Owner owner = findOwner(ownerName);

                    owner.addDog(dog);

                    System.out.println(ownerName + " now owns " + dogName);

                } else {
                    System.out.println("Error: no such owner");
                }

            } else {
                System.out.println("Error: the dog already has an owner");
            }
        } else {
            System.out.println("Error: no dog with that name");
        }

    }

    /// List owners

    private void listOwners() {
        if (listOfOwners.isEmpty()) {
            System.out.println("Error: no owners in register");
        } else {
            for(Owner owner : listOfOwners) {
                System.out.println(owner);
            }
        }
    }

    /// Remove owned dog

    private void removeOwnedDog(){
        String dogName = getAndTrimString("Enter the name of the dog?");

        Dog dog = findDog(dogName);

        if(dog != null){
            if(!dog.hasOwner()){
                System.out.println("Error: " + dog.getName() + " is not owned by anyone");
            } else {

                String previousOwnerName = dog.getOwner().getName();

                dog.removeOwner();

                System.out.println(dog.getName() + " is removed " + previousOwnerName);

            }
        } else {
            System.out.println("Error: no such dog");
        }

    }

    /// Remove owner

    private void removeOwnerFromRegister(){

        String ownerName = getAndTrimString("Enter the name of the user");

        Owner owner = findOwner(ownerName);

        if(listOfOwners.contains(owner)){

            removeDogWithOwner(owner);

            listOfOwners.remove(owner);

            System.out.println(ownerName + " is removed from the register");

        } else {
            System.out.println("Error: no such owner");
        }

    }

    private void removeDogWithOwner(Owner owner) {
        if(!listOfDogs.isEmpty()) {
            for(int i = listOfDogs.size() - 1; i >= 0; i--) {
                    if(owner.checkIfOwns(listOfDogs.get(i))){
                        listOfDogs.remove(listOfDogs.get(i));
                    }
                }
            }
        }


    /// Hjälpmetoder för att hantera inputs

    private String getStringInput(String textForString){
        String input = "";

        while(input.equals("")) {
            input = inputReader.readStringInput(textForString).strip();

            if(input.equals("")){
                System.out.println("Error; invalid input");
            }
        }
        return input;
    }

    private String getAndTrimString(String string){
        String stringToReturn = "";

        while(stringToReturn.length() == 0){
            stringToReturn = inputReader.readStringInput(string);
            stringToReturn = stringToReturn.trim();
            if(stringToReturn.length() == 0){
                System.out.println("Error: not valid input");
            }
        }
        return stringToReturn;
    }

    /// Hjälpmetod för att hitta hund och ägare

    private Dog findDog(String dogName){
        for(Dog dog : listOfDogs){
            if(dog.getName().equalsIgnoreCase(dogName)){
                return dog;
            }
        }
        return null;
    }

    private Owner findOwner(String ownerName){
        for(Owner owner : listOfOwners){
            if(owner.getName().equalsIgnoreCase(ownerName)){
                return owner;
            }
        }
        return null;
    }

}
