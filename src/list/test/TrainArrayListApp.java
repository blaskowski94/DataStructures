package list.test;

import java.util.ArrayList;

public class TrainArrayListApp {
    private ArrayList<TrainCar> train = new ArrayList<>();

    public static void main(String[] args) {
        TrainArrayListApp app = new TrainArrayListApp();

        long start = System.currentTimeMillis();
        app.buildInitialTrain();

        //print out the train size
//        System.out.println(app.trainSize());
//
//        //first stop, we remove a car and add a couple more
//        app.firstStop();
//
//        //print out the train size
//        System.out.println("After First Stop train size: " + app.trainSize());
//
//        //second stop, we need to remove all the tankers
//        app.secondStop();
//
//        //print out the train size
//        System.out.println("After Second Stop train size: " + app.trainSize());
//
//        //at the last stop we remove all of the train cars and we're done
//        app.lastStop();
//
//        //print out the train size
//        System.out.println("After Last Stop train size: " + app.trainSize());

        long stop = System.currentTimeMillis();
        System.out.println("Time: " + (stop - start));
    }

    private int trainSize() {
        return train.size();
    }

    private void buildInitialTrain() {
        //build the train for it's initial trip
        for (int i = 0; i < 1000000; i++) {
            TrainCar car = new TrainCar(CarType.BOXCAR, Integer.toString(i));
            train.add(car);
        }
    }

    private void firstStop() {
        //at this stop we need to pull off the first box car and insert a new BoxCar after the farm machinery
        TrainCar boxcar = train.remove(0);

        System.out.println("First Stop: Removed - " + boxcar);

        TrainCar newBoxcar = new TrainCar(CarType.BOXCAR, "Farm Fence Posts and Barbwire");
        train.add(1, newBoxcar);

        //print out the train cars
        System.out.println(train);
    }

    private void secondStop() {
        //at this stop we need to pull off all of the tanker cars.  They should start at position 5 and there's 3 of them
        TrainCar car = train.remove(5);
        System.out.println("Second Stop: Removed - " + car);

        car = train.remove(5);
        System.out.println("Second Stop: Removed - " + car);

        car = train.remove(5);
        System.out.println("Second Stop: Removed - " + car);

        //print out the train cars
        System.out.println(train);
    }

    private void lastStop() {
        //at this stop we simply pull the remaining cars off of the train until we have no more train.

        try{
            while(true) {
                TrainCar car = train.remove(0);
                System.out.println("Last Stop: Removed - " + car);
            }
        } catch (IndexOutOfBoundsException ex) {
            //when we get an ise that means we don't have any more cars to remove and the train is now empty
        }

        //print out the train cars
        System.out.println(train);
    }

    class TrainCar {
        private CarType type;
        private String contents;

        TrainCar(CarType carType, String carContents) {
            this.type = carType;
            this.contents = carContents;
        }

        public String toString() {
            return type.toString() + " - " + contents;
        }
    }

    enum CarType {
        BOXCAR, TANKER, FLATBED, HOPPER
    }
}