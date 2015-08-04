package org.sopin.myenglish;

import android.content.Context;

import org.sopin.db.ResultSet;
import org.sopin.db.Sql;
import org.sopin.db.TableGateway;

public class QuizTableFactory {

    static public WordTable createService(Context context)
    {

        WordsDBOpenHelper dbHelper = new WordsDBOpenHelper(context);

        QuizEntity prototype = new QuizEntity();

        ResultSet resultSet = new ResultSet(prototype);

        Sql sql = new Sql(new String[] {});

        TableGateway tableGateway = new TableGateway(dbHelper, resultSet,
                WordsDBOpenHelper.FeedEntry.TABLE_NAME);

        return new WordTable(tableGateway, sql);
    }
}
