package com.example;
import java.util.Random;
public class JokeFromJavaLib {
    public static String JOKES[] = {"A: I have the perfect son. \n" +
            "B: Does he smoke? \n" +
            "A: No, he doesn't. \n" +
            "B: Does he drink whiskey? \n" +
            "A: No, he doesn't. \n" +
            "B: Does he ever come home late? \n" +
            "A: No, he doesn't. \n" +
            "B: I guess you really do have the perfect son. How old is he? \n" +
            "A: He will be six months old next Wednesday.",
            "The doctor to the patient: 'You are very sick' \n" +
                    "The patient to the doctor: 'Can I get a second opinion?' \n" +
                    "The doctor again: 'Yes, you are very ugly too...'\n" +
                    "I use this joke for retelling in reported speech. ",
            "Two boys were arguing when the teacher entered the room.\n" +
                    "The teacher says, \"Why are you arguing?\"\n" +
                    "\n" +
                    "One boy answers, \"We found a ten dollor bill and decided to give it to whoever tells the biggest lie.\"\n" +
                    "\n" +
                    "\"You should be ashamed of yourselves,\" said the teacher, \"When I was your age I didn't even know what a lie was.\"\n" +
                    "\n" +
                    "The boys gave the ten dollars to the teacher.\n" +
                    "\n"
    };

    public static void main(String args[]) {
        getJokeFromJava();
    }

    /**
     *
     * @return  random joke from array any position.
     */
    public static String getJokeFromJava() {
        int randomJokePos = new Random().nextInt(JOKES.length);
        return JOKES[randomJokePos];
    }
}
