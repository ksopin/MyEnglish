package org.sopin.db;

import android.content.Context;

import org.sopin.myenglish.QuizEntity;
import org.sopin.myenglish.WordsDBOpenHelper;

public class QuizTableFactory {

    static public Table createService(Context context)
    {

        WordsDBOpenHelper dbHelper = new WordsDBOpenHelper(context);

        QuizEntity prototype = new QuizEntity();

        ResultSet resultSet = new ResultSet(prototype);

        Sql sql = new Sql();

        TableGateway tableGateway = new TableGateway(dbHelper, resultSet,
                WordsDBOpenHelper.FeedEntry.TABLE_NAME);

        return new Table(tableGateway, sql);
    }
}
