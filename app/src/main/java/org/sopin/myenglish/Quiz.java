package org.sopin.myenglish;


import android.database.Cursor;

import org.sopin.db.ResultSet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Quiz {


    private WordTable table;
    private ArrayList<QuizEntity> quizList;
    private Integer cursor;

    public Quiz (WordTable table) {
        this.table = table;
        quizList = new ArrayList<QuizEntity>();

        ResultSet resultSet = table.fetchRand();

        if (resultSet.getCount() <= 0) {
            return;
        }

        //cursor = 0;
        do {
            WordEntity word = (WordEntity) resultSet.fetch();
            QuizEntity quizEntity = mapQuizEntity(word);
            quizList.add(0, quizEntity);
            //cursor++;
        } while (resultSet.moveToNext());
        cursor = 0;

    }


    private QuizEntity mapQuizEntity(WordEntity word) {
        QuizEntity quizEntity = new QuizEntity();

        quizEntity.setWordEntity(word);
        quizEntity.setWord(word.getWord());
        quizEntity.setCorrectOption(word.getTranslate());

        quizEntity.addOption(word.getTranslate());


        ResultSet result = table.fetchQuizOptions(word.getId());

        if (result.getCount() > 0) {
            do {
                WordEntity wordEntity = (WordEntity) result.fetch();
                quizEntity.addOption(wordEntity.getTranslate());
            } while (result.moveToNext());
        }

        return quizEntity;
    }

    public QuizEntity current() {
        Collections.shuffle(quizList.get(cursor).getOptions(), new Random(System.nanoTime()));
        return quizList.get(cursor);
    }

    public void moveToNext() {
        cursor++;
    }

    public void moveToFirst() {
        cursor = 0;
        Collections.shuffle(quizList, new Random(System.nanoTime()));
    }

    public boolean isLast() {
        if (cursor + 1 >= quizList.size()) {
            return true;
        }
        return false;
    }

    public Integer count() {
        return quizList.size();
    }

    public void levelUp(QuizEntity quizEntity) {
        if (quizEntity.getWordEntity().getLevel() < 10) {
            quizEntity.getWordEntity().setLevel(quizEntity.getWordEntity().getLevel() + 1);
            table.save(quizEntity.getWordEntity());
        }
    }

    public void levelDown(QuizEntity quizEntity) {
        if (quizEntity.getWordEntity().getLevel() > 0) {
            quizEntity.getWordEntity().setLevel(quizEntity.getWordEntity().getLevel() - 1);
            table.save(quizEntity.getWordEntity());
        }
    }
}
