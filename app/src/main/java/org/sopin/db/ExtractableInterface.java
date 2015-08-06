package org.sopin.db;

import android.content.ContentValues;

/**
 * Created by konstantin on 8/4/15.
 */
public interface ExtractableInterface extends EntityInterface {
    public ContentValues extract();
}
