package org.sopin.db;

import org.sopin.myenglish.WordsDBOpenHelper;

/**
 * Created by konstantin on 7/25/15.
 */
public class Sql {

    protected String[] columns;

    protected String selection;

    protected String[] selectionArgs;

    protected String having;

    protected String groupBy;

    protected String orderBy;

    protected String limit;


    public Sql () {
        setColumns(new String[]{
                WordsDBOpenHelper.FeedEntry._ID,
                WordsDBOpenHelper.FeedEntry.COLUMN_NAME_WORD,
                WordsDBOpenHelper.FeedEntry.COLUMN_NAME_TRANSLATE,
                WordsDBOpenHelper.FeedEntry.COLUMN_NAME_IS_PHRASE,
                WordsDBOpenHelper.FeedEntry.COLUMN_NAME_LEVEL,
                WordsDBOpenHelper.FeedEntry.COLUMN_NAME_LEARNT,
                WordsDBOpenHelper.FeedEntry.COLUMN_NAME_STATUS,
        });
    }

    public void setColumns(String[] columns) {
        this.columns = columns;
    }

    public String[] getColumns()
    {
        return this.columns;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String[] getSelectionArgs() {
        return selectionArgs;
    }

    public void setSelectionArgs(String[] selectionArgs) {
        this.selectionArgs = selectionArgs;
    }

    public String getHaving() {
        return having;
    }

    public void setHaving(String having) {
        this.having = having;
    }

    public String getGroupBy() {
        return groupBy;
    }

    public void setGroupBy(String groupBy) {
        this.groupBy = groupBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
