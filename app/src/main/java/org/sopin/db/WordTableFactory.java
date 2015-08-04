package org.sopin.db;

import android.content.Context;

import org.sopin.myenglish.WordsDBOpenHelper;
import org.sopin.myenglish.WordEntity;

public class WordTableFactory {

    static public Table createService(Context context)
    {

        WordsDBOpenHelper dbHelper = new WordsDBOpenHelper(context);

        WordEntity prototype = new WordEntity();

        ResultSet resultSet = new ResultSet(prototype);

        Sql sql = new Sql();

        TableGateway tableGateway = new TableGateway(dbHelper, resultSet,
                WordsDBOpenHelper.FeedEntry.TABLE_NAME);

        return new Table(tableGateway, sql);
    }
}
