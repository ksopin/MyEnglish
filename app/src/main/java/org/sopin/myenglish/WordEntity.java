package org.sopin.myenglish;

import android.content.ContentValues;
import android.database.Cursor;

import org.sopin.db.EntityInterface;
import org.sopin.db.ExtractableInterface;
import org.sopin.db.HydrateableInterface;

public class WordEntity
        implements EntityInterface, HydrateableInterface, ExtractableInterface


{

    private Integer id;
    private String word;
    private String translate;
    private Boolean isPhrase;
    private Integer level;
    private Boolean learnt;
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getTranslate() {
        return translate;
    }

    public void setTranslate(String translate) {
        this.translate = translate;
    }

    public Boolean getIsPhrase() {
        return isPhrase;
    }

    public void setIsPhrase(Boolean isPhrase) {
        this.isPhrase = isPhrase;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Boolean getLearnt() {
        return learnt;
    }

    public void setLearnt(Boolean learnt) {
        this.learnt = learnt;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void hydrate (Cursor cursor) {
        setId(cursor.getInt(0));
        setWord(cursor.getString(1));
        setTranslate(cursor.getString(2));
        setIsPhrase(cursor.getInt(3) > 0);
        setLevel(cursor.getInt(4));
        setLearnt(cursor.getInt(5) > 0);
        setStatus(cursor.getInt(6));
    }

    public ContentValues extract () {
        ContentValues newValues = new ContentValues();
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_WORD, getWord());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_TRANSLATE, getTranslate());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_IS_PHRASE, getIsPhrase());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_LEVEL, getLevel());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_LEARNT, getLearnt());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_STATUS, getStatus());
        return newValues;
    }
}