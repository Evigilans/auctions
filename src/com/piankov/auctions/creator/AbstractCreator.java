package com.piankov.auctions.creator;

import com.piankov.auctions.entity.Entity;
import com.piankov.auctions.exception.DAOException;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public abstract class AbstractCreator<T extends Entity> {
    public abstract T buildEntityFromMap(Map<String, String[]> parameterMap, Object... objects);

    public abstract T buildEntityFromResultSet(ResultSet resultSet) throws DAOException;

    public abstract List<T> buildListFromResultSet(ResultSet resultSet) throws DAOException;
}
