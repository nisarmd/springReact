package com.nisar.springReact.SQLite;

import org.hibernate.MappingException;
import org.hibernate.dialect.identity.IdentityColumnSupportImpl;

public class SQLiteColumnIdentitySupport extends IdentityColumnSupportImpl {
    @Override
    public boolean supportsIdentityColumns(){
        return true;
    }
    @Override
    public String getIdentitySelectString(String table, String column, int type)
            throws MappingException {
        return "select last_insert_rowid()";
    }

    @Override
    public String getIdentityColumnString(int type) throws MappingException {
        return "integer";
    }
}
