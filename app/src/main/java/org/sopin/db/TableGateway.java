package org.sopin.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 
 */
public class TableGateway {

    protected SQLiteOpenHelper dbHelper;

    protected ResultSet resultSet;

    protected String tableName;

    protected String key;

    public TableGateway(SQLiteOpenHelper dbHelper, ResultSet resultSet, String tableName, String key) {
        this.dbHelper = dbHelper;
        this.resultSet = resultSet;
        this.tableName = tableName;
        this.key = key;
    }

    public EntityInterface getResultSetPrototype() {
        return this.resultSet.getPrototype();
    }

    public String getTableName() {
        return tableName;
    }

    protected ResultSet mapResultSet(Cursor cursor) {
        resultSet = resultSet.clone();
        resultSet.setCursor(cursor);
        return resultSet;
    }

    public SQLiteOpenHelper getDbHelper() {
        return dbHelper;
    }

    public ResultSet select(Sql sql) {

        Cursor cursor = this.dbHelper.getReadableDatabase()
                .query(this.tableName, sql.getColumns(), sql.getSelection(), sql.getSelectionArgs(),
                        sql.getGroupBy(), sql.getHaving(), sql.getOrderBy(), sql.getLimit());

        return mapResultSet(cursor);
    }

    public ResultSet select(String sql, String[] args) {
        Cursor cursor = this.dbHelper.getReadableDatabase().rawQuery(sql, args);

        return mapResultSet(cursor);
    }

    public Cursor selectCursor(String sql, String[] args) {
        Cursor cursor = this.dbHelper.getReadableDatabase().rawQuery(sql, args);
        return cursor;
    }

    public boolean insert(ContentValues newValues) {
        this.dbHelper.getWritableDatabase().insert(this.tableName, null, newValues);
        return true;
    }

    public boolean update(ContentValues newValues, String id) {
        this.dbHelper.getWritableDatabase().update(this.tableName, newValues, key + " = ?", new String[]{id});
        return true;
    }

    public boolean delete(String id) {
        this.dbHelper.getWritableDatabase().delete(this.tableName, key + " = ?", new String[]{id});
        return true;
    }

}
