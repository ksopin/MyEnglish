package org.sopin.db;

import android.database.Cursor;

import org.sopin.myenglish.WordEntity;


public class ResultSet
{

    protected WordEntity prototype;

    protected Cursor cursor;

    public ResultSet(WordEntity prototype) {

        this.prototype = prototype;
    }

    public WordEntity getPrototype() {
        return this.prototype;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
        this.cursor.moveToFirst();
    }

    public WordEntity fetch() {

        prototype.setId(cursor.getInt(0));
        prototype.setWord(cursor.getString(1));
        prototype.setTranslate(cursor.getString(2));
        prototype.setIsPhrase(cursor.getInt(3) > 0);
        prototype.setLevel(cursor.getInt(4));
        prototype.setLearnt(cursor.getInt(5) > 0);
        prototype.setStatus(cursor.getInt(6));

        return this.prototype;
    }

    public boolean isFirst() {
        return cursor.isFirst();
    }


    public boolean isLast() {
        return cursor.isLast();
    }

    public boolean moveToNext() {
        return cursor.moveToNext();
    }

    public boolean moveToPrevious() {
        return cursor.moveToPrevious();
    }

    public boolean moveToFirst() {
        return cursor.moveToFirst();
    }

    public boolean moveToLast() {
        return cursor.moveToLast();
    }

    public Integer getCount() {
        return cursor.getCount();
    }
}
