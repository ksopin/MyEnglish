package org.sopin.myenglish;

import android.content.Context;

import org.sopin.db.ResultSet;
import org.sopin.db.Sql;
import org.sopin.db.TableGateway;


public class WordTableFactory {

    static public WordTable createService(Context context)
    {

        WordsDBOpenHelper dbHelper = new WordsDBOpenHelper(context);

        WordEntity prototype = new WordEntity();

        ResultSet resultSet = new ResultSet(prototype);

        Sql sql = new Sql(new String[]{
                WordsDBOpenHelper.FeedEntry._ID,
                WordsDBOpenHelper.FeedEntry.COLUMN_NAME_WORD,
                WordsDBOpenHelper.FeedEntry.COLUMN_NAME_TRANSLATE,
                WordsDBOpenHelper.FeedEntry.COLUMN_NAME_CONTEXT,
                WordsDBOpenHelper.FeedEntry.COLUMN_NAME_IS_PHRASE,
                WordsDBOpenHelper.FeedEntry.COLUMN_NAME_LEVEL
        });

        TableGateway tableGateway = new TableGateway(dbHelper, resultSet,
                WordsDBOpenHelper.FeedEntry.TABLE_NAME, WordsDBOpenHelper.FeedEntry._ID);

        return new WordTable(tableGateway, sql);
    }
}
