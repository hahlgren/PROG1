// Henrik Ahlgren heah6799

public class Dog {

    private static final double DACHSHUND_TAIL_LENGTH = 3.7;
    
    private String name;
    private String breed;
    private int age;
    private int weight;

    private double tailLength;

    private Owner owner;


    public Dog(String name, String breed, int age, int weight){
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.weight = weight;
        this.tailLength = getTailLength();
        this.owner = owner;
    }


    public String getName(){
        return name;
    }

    public String getBreed(){
        return breed;
    }

    public int getAge(){
        return age;
    }

    public int getWeight(){
        return weight;
    }

    public double getTailLength(){
        if(breed.toLowerCase().contains("tax") || breed.toLowerCase().contains("dachshund")){
            tailLength = DACHSHUND_TAIL_LENGTH;
            return tailLength;
        } else {
            tailLength = this.age * this.weight / (double) 10;
            return tailLength;
        }
    }

    public Owner getOwner() { return owner;}

    public void setOwner(Owner ownerToAdd){
        if( hasOwner() ){
            return;
        }
        if(this.owner == null){
            this.owner = ownerToAdd;
            if(!owner.checkIfOwns(this)){
                owner.addDog(this);
            }
        }
    }

    public int updateAge(int addedAge){
        if (addedAge >= 0) {
            this.age += addedAge;
            return age;
        } else {
            return this.age;
        }
    }

    public void removeOwner(){

        if(owner.checkIfOwns(this)){
            owner.removeDog(this);
        }

        owner = null;

    }

    public boolean hasOwner(){
        return owner != null;
    }

    public String toString(){
        return getName() + " är en " + getBreed() + " och är " + getAge() +
            " år och väger " + getWeight() + " kg och har en svanslängd på " + getTailLength() + " cm.";
    }

}