package org.example.javasuitejavafx;
import java.util.Random;

public class Hangman {

    // returns a random word in a String
    public static String generateSecretWord() {
        String[] wordList = {"apple",
                "banana",
                "cherry",
                "grape",
                "melon",
                "orange",
                "peach",
                "pear",
                "plum",
                "lemon",
                "kiwi",
                "berry",
                "fruit",
                "mango",
                "guava",
                "papaya",
                "water",
                "apricot",
                "avocado",
                "juicy",
                "tasty",
                "sweet",
                "sour",
                "flavor",
                "pineapple",
                "blueberry",
                "blackberry",
                "strawberry",
                "raspberry",
                "coconut",
                "lime",
                "fig",
                "dragonfruit",
                "kiwifruit",
                "pomegranate",
                "passionfruit",
                "cat",
                "dog",
                "elephant",
                "giraffe",
                "lion",
                "tiger",
                "zebra",
                "penguin",
                "monkey",
                "kangaroo",
                "dolphin",
                "whale",
                "shark",
                "turtle",
                "ostrich",
                "ocean",
                "forest",
                "mountain",
                "desert",
                "beach",
                "castle",
                "rainbow",
                "unicorn",
                "wizard",
                "planet",
                "moon",
                "star",
                "spaceship",
                "astronaut"};
        Random random = new Random();
        int randomNumber = random.nextInt(wordList.length);
        return wordList[randomNumber];
    }

    public static void main(String[] args) {
        System.out.println(generateSecretWord());
    }

}
