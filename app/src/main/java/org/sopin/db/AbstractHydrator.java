package org.sopin.db;

import android.content.ContentValues;

public abstract class AbstractHydrator {

    abstract public ContentValues extract(Object object);

    abstract public void hydrate(ContentValues data, Object object);
}
