package org.sopin.myenglish;


import android.database.Cursor;

public interface HydratorInterface {
    public Object hydrate (Cursor cursor, Object object);
}
