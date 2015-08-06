package org.sopin.db;

import android.content.ContentValues;

abstract public class AbstractTable {

    protected Sql sql;

    protected TableGateway tableGateway;

    public AbstractTable(TableGateway tableGateway, Sql sql) {
        this.tableGateway = tableGateway;
        this.sql = sql;
    }

    public EntityInterface getNew() {
        return tableGateway.getResultSetPrototype();
    }

    public void save(ExtractableInterface object) {
        ContentValues newValues = object.extract();
        if (object.getId() != null) {
            this.tableGateway.update(newValues, object.getId().toString());
        } else {
            this.tableGateway.insert(newValues);
        }
    }

    public void delete(EntityInterface object) {
        this.tableGateway.delete(object.getId().toString());
    }
}
