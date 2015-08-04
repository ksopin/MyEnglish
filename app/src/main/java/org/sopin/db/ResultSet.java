package org.sopin.db;

import android.database.Cursor;

public class ResultSet {

    protected EntityInterface prototype;

    protected Cursor cursor;

    public ResultSet(EntityInterface prototype) {
        this.prototype = prototype;
    }

    public EntityInterface getPrototype() {
        return this.prototype;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
        this.cursor.moveToFirst();
    }

    public EntityInterface fetch() {
        prototype.hydrate(cursor);
        return prototype;
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
