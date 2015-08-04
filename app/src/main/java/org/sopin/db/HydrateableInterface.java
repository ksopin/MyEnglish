package org.sopin.db;

import android.database.Cursor;

/**
 * Created by konstantin on 8/4/15.
 */
public interface HydrateableInterface {

    public void hydrate (Cursor cursor);
}
