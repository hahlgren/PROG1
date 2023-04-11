// Henrik Ahlgren heah6799

public class Owner {

    private String name;
    private DogList ownedDogs = new DogList();

    public Owner(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public String getOwnedDogs() {
         return ownedDogs.toString();
    }


    public void addDog(Dog dog) {
        if(dog.getOwner() == this || dog.getOwner() == null){
            if (!ownedDogs.containsDog(dog)) {
                ownedDogs.addDog(dog);
                if(dog.getOwner() != this){
                    dog.setOwner(this);
                }
            }
        }
    }

    public void removeDog(Dog dog){
        if(dog.getOwner() == this){
            ownedDogs.removeDog(dog);

            if(dog.hasOwner()){
                dog.removeOwner();
            }

        }
    }

    public boolean checkIfOwns(Dog dog) {
        return ownedDogs.containsDog(dog);
    }

    public String toString(){
        return name + " äger " + ownedDogs.toString();
    }

}


