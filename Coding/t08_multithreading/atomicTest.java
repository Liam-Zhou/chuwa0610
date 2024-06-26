package com.chuwa.exercise.t08_multithreading;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class atomicTest {
    public static void main(String[] args) {
        // Example with AtomicInteger
        AtomicInteger atomicInteger = new AtomicInteger(0);

        // Increment by 1
        atomicInteger.incrementAndGet(); // returns 1

        // Add a value
        atomicInteger.addAndGet(5); // returns 6

        // Get the current value
        int currentValue = atomicInteger.get(); // returns 6
        System.out.println("AtomicInteger - Current Value: " + currentValue);

        // Compare and set
        boolean success = atomicInteger.compareAndSet(6, 10); // returns true
        System.out.println("AtomicInteger - Compare and Set Successful: " + success);

        // Get the updated value
        int updatedValue = atomicInteger.get(); // returns 10
        System.out.println("AtomicInteger - Updated Value: " + updatedValue);

        // Example with AtomicBoolean
        AtomicBoolean atomicBoolean = new AtomicBoolean(false);

        // Set to true
        atomicBoolean.set(true);

        // Get the current value
        boolean currentBooleanValue = atomicBoolean.get(); // returns true
        System.out.println("AtomicBoolean - Current Value: " + currentBooleanValue);

        // Compare and set
        boolean booleanSuccess = atomicBoolean.compareAndSet(true, false); // returns true
        System.out.println("AtomicBoolean - Compare and Set Successful: " + booleanSuccess);

        // Get the updated value
        boolean updatedBooleanValue = atomicBoolean.get(); // returns false
        System.out.println("AtomicBoolean - Updated Value: " + updatedBooleanValue);

        // Example with AtomicReference
        AtomicReference<String> atomicReference = new AtomicReference<>("initial");

        // Get the current value
        String currentReferenceValue = atomicReference.get(); // returns "initial"
        System.out.println("AtomicReference - Current Value: " + currentReferenceValue);

        // Set a new value
        atomicReference.set("updated");

        // Compare and set
        boolean referenceSuccess = atomicReference.compareAndSet("updated", "final"); // returns true
        System.out.println("AtomicReference - Compare and Set Successful: " + referenceSuccess);

        // Get the updated value
        String updatedReferenceValue = atomicReference.get(); // returns "final"
        System.out.println("AtomicReference - Updated Value: " + updatedReferenceValue);
    }
}
