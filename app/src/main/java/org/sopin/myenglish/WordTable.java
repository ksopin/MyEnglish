package org.sopin.myenglish;

import android.content.ContentValues;

import org.sopin.db.AbstractTable;
import org.sopin.db.ResultSet;
import org.sopin.db.Sql;
import org.sopin.db.TableGateway;


public class WordTable extends AbstractTable {

    public WordTable(TableGateway tableGateway, Sql sql)
    {
        super(tableGateway, sql);
    }

    public WordEntity getById(Integer id) {

        sql.setSelection(WordsDBOpenHelper.FeedEntry._ID + " = ?");
        sql.setSelectionArgs(new String[]{id.toString()});

        //ResultSet resultSet = tableGateway.select(this.sql);

        WordEntity word = (WordEntity) tableGateway.select(this.sql).fetch();


//        WordEntity word = new WordEntity();
//
//        word.setId(1);
//        word.setWord("test");
//        word.setTranslate("translate");
//        word.setLevel(6);
//        word.setLearnt(false);
//        word.setIsPhrase(false);
//        word.setStatus(1);

        return word;
    }

    public ResultSet fetchAll() {
        return this.tableGateway.select(this.sql);
    }

    public ResultSet fetchQuizOptions(Integer id) {

        sql.setSelection("word != '' AND translate != '' AND " + WordsDBOpenHelper.FeedEntry._ID
                + " != ? AND level < 10");
        sql.setSelectionArgs(new String[]{id.toString()});
        sql.setOrderBy("RANDOM()");
        sql.setLimit("3");

        return this.tableGateway.select(this.sql);
    }

    public ResultSet fetchRecentAdded() {
        this.sql.setSelection("word != '' AND translate != ''");
        this.sql.setOrderBy(WordsDBOpenHelper.FeedEntry._ID + " DESC");
        this.sql.setLimit("30");

        return this.tableGateway.select(this.sql);
    }

    public ResultSet fetchRand() {
        this.sql.setSelection("word != '' AND translate != '' AND level < 10");
        this.sql.setOrderBy("RANDOM()");
        this.sql.setLimit("30");

        return this.tableGateway.select(this.sql);
    }

    public ResultSet fetchNotTranslated() {
        this.sql.setSelection("translate = '' OR word = ''");
        return this.tableGateway.select(this.sql);
    }
}
