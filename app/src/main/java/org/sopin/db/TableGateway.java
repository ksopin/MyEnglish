package org.sopin.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

public class TableGateway {

    protected SQLiteOpenHelper dbHelper;

    protected ResultSet resultSet;

    protected String tableName;

    public TableGateway(SQLiteOpenHelper dbHelper, ResultSet resultSet, String tableName) {
        this.dbHelper = dbHelper;
        this.resultSet = resultSet;
        this.tableName = tableName;
    }

    public Object getResultSetPrototype() {
        return this.resultSet.getPrototype();
    }

    public ResultSet select(Sql sql) {

        Cursor cursor = this.dbHelper.getReadableDatabase()
                .query(this.tableName, sql.getColumns(), sql.getSelection(), sql.getSelectionArgs(),
                        sql.getGroupBy(), sql.getHaving(), sql.getOrderBy(), sql.getLimit());

        this.resultSet.setCursor(cursor);

        return this.resultSet;
    }

    public boolean insert(ContentValues newValues) {
        this.dbHelper.getWritableDatabase().insert(this.tableName, null, newValues);
        return true;
    }

    public boolean update(ContentValues newValues, String id) {
        this.dbHelper.getWritableDatabase().update(this.tableName, newValues, "id = ?", new String[]{id});
        return true;
    }

    public boolean delete(String id) {
        this.dbHelper.getWritableDatabase().delete(this.tableName, "id = ?", new String[]{id});
        return true;
    }

}
