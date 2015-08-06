package org.sopin.myenglish;


import android.database.Cursor;

import org.sopin.db.ResultSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Quiz {

    private WordEntity wordEntity;
    private String word;
    private String correctOption;
    private ArrayList<String> options;
    private WordTable table;

    public Quiz (WordTable table) {
        this.table = table;
    }

    public WordEntity getWordEntity() {
        return wordEntity;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public void setWord(WordEntity word) {
        wordEntity = word;
        options = new ArrayList<String>();
        setWord(word.getWord());
        setCorrectOption(word.getTranslate());
        addOption(word.getTranslate());

        ResultSet result = table.fetchQuizOptions(word.getId());

        if (result.getCount() > 0) {
            do {
                WordEntity wordEntity = (WordEntity) result.fetch();
                addOption(wordEntity.getTranslate());
            } while (result.moveToNext());
        }

        Collections.shuffle(options, new Random(System.nanoTime()));
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

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public void addOption(String option) {
        options.add(0, option);
    }

    public void levelUp() {
        if (wordEntity.getLevel() < 10) {
            wordEntity.setLevel(wordEntity.getLevel() + 1);
            table.save(wordEntity);
        }
    }

    public void levelDown() {
        if (wordEntity.getLevel() > 0) {
            wordEntity.setLevel(wordEntity.getLevel() - 1);
            table.save(wordEntity);
        }
    }
}
