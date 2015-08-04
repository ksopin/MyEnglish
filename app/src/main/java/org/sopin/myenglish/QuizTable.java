package org.sopin.myenglish;

import android.content.ContentValues;

import org.sopin.db.ResultSet;
import org.sopin.db.Sql;
import org.sopin.db.TableGateway;

public class QuizTable
{

    protected Sql sql;

    protected TableGateway tableGateway;

    public QuizTable(TableGateway tableGateway, Sql sql)
    {
        this.tableGateway = tableGateway;
        this.sql = sql;
    }

    public Object getNew()
    {
        return tableGateway.getResultSetPrototype();
    }

    public WordEntity getById(Integer id) {

        WordEntity word = new WordEntity();

        return word;
    }

    public ResultSet fetchAll() {
        return this.tableGateway.select(this.sql);
    }

    public ResultSet fetchRecentAdded() {
        this.sql.setSelection("word != '' AND translate != ''");
        this.sql.setOrderBy(WordsDBOpenHelper.FeedEntry._ID + " DESC");
        this.sql.setLimit("30");

        return this.tableGateway.select(this.sql);
    }

    public ResultSet fetchNotTranslated() {
        this.sql.setSelection("translate = '' OR word = ''");
        return this.tableGateway.select(this.sql);
    }

    public void save(WordEntity word) {

        ContentValues newValues = new ContentValues();
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_WORD, word.getWord());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_TRANSLATE, word.getTranslate());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_IS_PHRASE, word.getIsPhrase());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_LEVEL, word.getLevel());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_LEARNT, word.getLearnt());
        newValues.put(WordsDBOpenHelper.FeedEntry.COLUMN_NAME_STATUS, word.getStatus());

        if (word.getId() != null) {
            this.tableGateway.update(newValues, word.getId().toString());
        } else {
            this.tableGateway.insert(newValues);
        }


    }

    public void delete(WordEntity word) {
        this.tableGateway.delete(word.getId().toString());
    }

}
