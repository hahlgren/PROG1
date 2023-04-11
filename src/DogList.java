// Henrik Ahlgren heah6799

import java.util.Arrays;

public class DogList {

    private Dog[] dogArray = new Dog[0];

    public void addDog(Dog dog){
        boolean dogExists = containsDog(dog);
        if(!dogExists && dog != null){
            dogArray = increaseDogArray(dogArray);
            dogArray[dogArray.length - 1] = dog;
        }

    }

    private Dog[] increaseDogArray(Dog[] dogArray){
        int arrayLength = dogArray.length;
        Dog[] newArray = new Dog[arrayLength + 1];
        for(int i = 0; i < arrayLength ; i++){
            newArray[i] = dogArray[i];
        }
        return newArray;
    }

    public void removeDog(Dog dog){

        if(containsDog(dog) && dog != null){
           Dog[] copy = new Dog[dogArray.length - 1];
           int j = 0;
           for(int i = 0; i < dogArray.length ; i++){
               if(i != getIndex(dogArray, dog)){
                   copy[j++] = dogArray[i];
               }
           }
           dogArray = copy;
        }
    }

    private int getIndex(Dog[] dogArray, Dog dog){
        for(int i = 0; i < dogArray.length ; i++){
            Dog searchedDog = dogArray[i];
            if(searchedDog == dog){
                return i;
            }
        }
        return 0;
    }

    public boolean containsDog(Dog dog){
        for(Dog x : dogArray){
            if(x == dog){
                return true;
            }
        } return false;
    }

    public String toString(){
        String listOfDogs =  Arrays.toString(dogArray);
        return listOfDogs;
    }
}
