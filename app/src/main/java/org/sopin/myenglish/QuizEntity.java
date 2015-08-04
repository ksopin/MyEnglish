package org.sopin.myenglish;


public class QuizEntity {

    private String word;
    private String correctOption;
    private String[] options;

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

    public boolean isCorrect(String value) {
        return correctOption.equals(value);
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public void addOption(String option) {
        options[options.length] = option;
    }

}