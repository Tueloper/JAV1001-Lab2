/*
 * Name: Tochukwu Ozurumba
 * Student Id: A0023457787
 * Description: This is a array tool application, aim is to show competence with using and manipulating arrays. It's an assignment done by Tochukwu Ozurumba.
 *
 * */
package com.array;

import java.util.Scanner;
import java.util.Arrays;

public class ArrayTools {
    //    This is a global variable input, that is initialized once and used across all methods and functions
    static Scanner input = new Scanner(System.in);

    static int[] defaultArray = { 44, 78, 45, 77, 44, 98, 67, 68, 91, 54 };

    public static void main(String[] args) {
//        Welcome print notes
        System.out.println("Welcome to Array Tools Application");

//        declaring variables
        int selectedOptionNumber;

//        Introducing the various conversion units available
        System.out.println("Please select from the options below");
        System.out.println("""
            1. Encrypt a word (Caesar Cipher Function)\s
            2. Calculate the average Value of a list; E.g [ 44 78 45 77 44 98 67 68 91 54 ], average = 66.6\s
            3. Search for a value in an Array\s
            4. Reverse an Array\s
            """
        );

        selectedOptionNumber = getAndValidateInteger(
                "Please select your option",
                "Please your option must be a valid number From 1 - 4.",
                1,
                4
        );


//        check where your selection option falls into
        switch (selectedOptionNumber) {
            case 1 -> {
                System.out.println("ENCRYPT STRING CALCULATING...");

                String stringToBeEncrypted = getAndValidateString();

                int offsetNumber = getAndValidateInteger(
                        "Please Enter a valid offset number",
                        "Error!, Number is not valid, please input a valida number",
                        -100,
                        100
                );

                StringBuilder encryptedString = encryptStringWithCipher(stringToBeEncrypted, offsetNumber);

                System.out.print("Encrypted string is " + encryptedString);

            }
            case 2 -> {
                System.out.println("AVERAGE VALUES IN AN ARRAY");

                int[] selectedArray = chooseArray();
                System.out.println(Arrays.toString(selectedArray));

                double average = calculateArrayAverage(selectedArray);
                System.out.println("Average Value of the selected array : "+ average);
            }
            case 3 -> {
                System.out.println("SEARCH VALUES IN AN ARRAY");

                int[] selectedArray = chooseArray();

                int getSearchValue = getAndValidateInteger(
                        "Please Enter a valid search integer",
                        "Error!, Number is not valid, please input a valid number",
                        -1000000000,
                        100000000
                );

                boolean isExist = searchValueInAnArray(selectedArray, getSearchValue);

                if (isExist)
                    System.out.println(getSearchValue + " exist in array " + Arrays.toString(selectedArray));
                else
                    System.out.println(getSearchValue + " does not exist in array " + Arrays.toString(selectedArray));
            }
            case 4 -> {
                System.out.println("REVERSE VALUES IN AN ARRAY");

                int[] selectedArray = chooseArray();

                int[] reversedArray = reverseArray(selectedArray);
                System.out.println("Reverse selected array : "+ Arrays.toString(reversedArray));
            }
        }
    }

    private static boolean checkIfValueIsAnInteger(String value) {
        try {
            Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return false;
        }

        return true;
    }

    private static int getAndValidateInteger(String requestString, String errorString, int startRange, int endRange) {
        int validatedInteger = 0;
        while (true) //noinspection SuspiciousIndentAfterControlStatement
        {
            System.out.print(requestString + ": ");
            String option = input.nextLine().trim();

            if (checkIfValueIsAnInteger(option))
                validatedInteger = Integer.parseInt(option);
                if (validatedInteger >= startRange && validatedInteger <= endRange)
                    break;

            System.out.println(errorString);
        }

        return validatedInteger;
    }

    private static int[] getAndValidateArray() {
        System.out.println("Enter the required size of the array :: ");
        Scanner s = new Scanner(System.in);
        int size = s.nextInt();
        int myArray[] = new int[size];
        System.out.println("Enter the elements of the array one by one ");
        for(int i=0; i<size; i++) {
            myArray[i] = s.nextInt();
        }

        return myArray;
    }

    private static String getAndValidateString() {
        String validatedString;
        while (true) {
            System.out.print("Please Enter the string to be encrypted" + ": ");
            validatedString = input.nextLine().trim();

            if (checkIfValueIsAnInteger(validatedString))
                System.out.println("Error!, string is not valid, please input a valid string");
            else
                break;
        }

        return validatedString.toLowerCase();
    }

    private static StringBuilder encryptStringWithCipher(String stringToBeEncrypted, int offsetNumber) {
        StringBuilder result = new StringBuilder();
        for (char character : stringToBeEncrypted.toCharArray()) {
            if (character != ' ') {
                int originalAlphabetPosition = character - 'a';
                int newAlphabetPosition = (originalAlphabetPosition + offsetNumber) % 26;
                char newCharacter = (char) ('a' + newAlphabetPosition);
                result.append(newCharacter);
            } else {
                result.append(character);
            }
        }

        return result;
    }

    private static int[] chooseArray() {
        int[] selectedArray = new int[0];

        System.out.println("Please select from the options below");
        System.out.println("""
            1. Use Default Array\s
            2. Input your own Array\s
            
            N/B: Inputted array must be all integers only
            """
        );

        int optionArray = getAndValidateInteger(
                "Please select option",
                "Error!, Number is not valid, please input a valid number of 1 or 2",
                1,
                2
        );

        if (optionArray == 1)
            selectedArray = defaultArray;
        if (optionArray == 2)
            selectedArray = getAndValidateArray();

        return selectedArray;
    }

    private static double calculateArrayAverage(int[] arr) {
        int length = arr.length;
        int sum = 0;

        // sum of all values in array
        for (int j : arr) {
            sum += j;
        }

        return sum / length;
    }

    private static boolean searchValueInAnArray(int[] arr, int key) {
        return Arrays.stream(arr).anyMatch(i -> i == key);
    }

    private static int[] reverseArray(int[] arr) {
        int y = 0;
        int[] result = new int[arr.length];

        //Loop through the array in reverse order
        for (int i = arr.length - 1; i >= 0; i--) {
            result[y] = arr[i];
            ++y;
        }

        return result;
    }
}
