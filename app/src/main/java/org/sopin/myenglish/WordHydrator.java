package org.sopin.myenglish;

import android.content.ContentValues;
import android.database.Cursor;

public class WordHydrator
{

    public WordEntity hydrate (Cursor cursor, WordEntity word) {

        word.setId(cursor.getInt(0));
        word.setWord(cursor.getString(1));
        word.setTranslate(cursor.getString(2));
        word.setIsPhrase(cursor.getInt(3) > 0);
        word.setLevel(cursor.getInt(4));
        word.setLearnt(cursor.getInt(5) > 0);
        word.setStatus(cursor.getInt(6));

        return word;
    }

    public ContentValues extract(WordEntity word) {
        ContentValues newValues = new ContentValues();
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_WORD, word.getWord());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_TRANSLATE, word.getTranslate());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_IS_PHRASE, word.getIsPhrase());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_LEVEL, word.getLevel());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_LEARNT, word.getLearnt());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_STATUS, word.getStatus());

        return newValues;
    }
}
