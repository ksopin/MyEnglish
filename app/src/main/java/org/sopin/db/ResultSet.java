package org.sopin.db;

import android.database.Cursor;

public class ResultSet {

    protected EntityInterface prototype;

    protected Cursor cursor;

    public ResultSet(EntityInterface prototype) {
        this.prototype = prototype;
    }

    public void setPrototype(EntityInterface prototype) {
        this.prototype = prototype;
    }

    protected ResultSet (ResultSet another) {
        //another.setCursor(cursor);
        another.setPrototype(prototype);
    }

    public ResultSet clone () {
        return new ResultSet(prototype);
    }

    public EntityInterface getPrototype() {
        return this.prototype;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
        this.cursor.moveToFirst();
    }

    public EntityInterface fetch() {
        EntityInterface clone = (EntityInterface) prototype.clone();
        clone.hydrate(cursor);
        return clone;
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
