package com.piankov.auctions.creator;

import com.piankov.auctions.entity.Entity;
import com.piankov.auctions.exception.EntityCreationException;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

public abstract class AbstractCreator<T extends Entity> {
    public abstract T buildEntityFromMap(Map<String, String[]> parameterMap, Object... objects);

    public abstract T createEntityFromResultSet(ResultSet resultSet) throws EntityCreationException;

    public abstract List<T> createListFromResultSet(ResultSet resultSet) throws EntityCreationException;
}
