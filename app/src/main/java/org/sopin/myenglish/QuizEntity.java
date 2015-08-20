package org.sopin.myenglish;

import java.util.ArrayList;

public class QuizEntity {

    private String word;
    private String correctOption;
    private ArrayList<String> options;
    private WordEntity wordEntity;


    public QuizEntity() {
        options = new ArrayList<String>();
    }

    public boolean isCorrect(String value) {
        return correctOption.equals(value);
    }


    public WordEntity getWordEntity() {
        return wordEntity;
    }

    public void setWordEntity (WordEntity wordEntity) {
        this.wordEntity = wordEntity;
    }


    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }



    public String getCorrectOption() {
        return correctOption;
    }

    public void setCorrectOption(String correctOption) {
        this.correctOption = correctOption;
    }



    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public void addOption(String option) {
        options.add(0, option);
    }
}