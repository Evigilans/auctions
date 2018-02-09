package com.piankov.auctions.creator;

import com.piankov.auctions.entity.Entity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public abstract class AbstractCreator<T extends Entity> {
    public abstract T buildEntityFromMap(Map<String, String[]> parameterMap);

    public abstract T buildEntityFromResultSet(ResultSet resultSet) throws SQLException;

    public abstract List<T> buildListFromResultSet(ResultSet resultSet) throws SQLException;
}
