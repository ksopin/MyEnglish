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
    private String context;
    private Boolean isPhrase;
    private Integer level;

    public WordEntity () {}

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

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
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

    public void hydrate (Cursor cursor) {
        setId(cursor.getInt(0));
        setWord(cursor.getString(1));
        setTranslate(cursor.getString(2));
        setContext(cursor.getString(3));
        setIsPhrase(cursor.getInt(4) > 0);
        setLevel(cursor.getInt(5));
    }

    public ContentValues extract () {
        ContentValues newValues = new ContentValues();
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_WORD, getWord());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_TRANSLATE, getTranslate());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_CONTEXT, getContext());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_IS_PHRASE, getIsPhrase());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_LEVEL, getLevel());
        return newValues;
    }

    @Override
    public String toString() {
        return this.word + " - " + this.translate + " ("+this.level+")";
    }

    public Object clone()
    {
        return new WordEntity(this);
    }

    protected WordEntity(WordEntity another) {

    }

}