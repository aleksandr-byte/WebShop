package ua.nure.webshop.builder;

import ua.nure.webshop.domain.Parameters;

import java.util.List;
import java.util.Set;

public class SqlBuilder {

    private String sqlQuery;
    private int counter1;

    public SqlBuilder(Parameters parameters, String categoryName) {
        createQueryForFilter(parameters, categoryName);
    }

    public SqlBuilder(Set<String> ids) {
        createQueryForCart(ids);
    }

    private void createQueryForFilter(Parameters parameters, String categoryName) {
        StringBuilder sql = new StringBuilder("select p from ");
        sql.append(" Products p INNER JOIN ");
        sql.append(" Characteristics c ON p.id = c.id");
        if (parameters.getDiagonalParams().size() != 0
                || parameters.getResolutionsParams().size() != 0
                || parameters.getMemorySizesParams().size() != 0
                || parameters.getFlashMemorySizesParams().size() != 0
                || parameters.getBatteryCapacitiesParams().size() != 0
                || parameters.getCapacitiesParams().size() != 0
                || parameters.getColorsParams().size() != 0
                || parameters.getCpusParams().size() != 0
                || parameters.getDisplayTypesParams().size() != 0
                || parameters.getManufacturers().size() != 0
                || categoryName != null) {
            sql.append(" WHERE ");
        }
        if (categoryName != null) {
            sql.append(" p.category_id = (select cat.categoriesId from Categories cat WHERE cat.categoryName ='" + categoryName +  "') ");
        }
        if (parameters.getDiagonalParams().size() != 0
                || parameters.getResolutionsParams().size() != 0
                || parameters.getMemorySizesParams().size() != 0
                || parameters.getFlashMemorySizesParams().size() != 0
                || parameters.getBatteryCapacitiesParams().size() != 0
                || parameters.getCapacitiesParams().size() != 0
                || parameters.getColorsParams().size() != 0
                || parameters.getCpusParams().size() != 0
                || parameters.getDisplayTypesParams().size() != 0
                || parameters.getManufacturers().size() != 0) {
            sql.append(" AND ");
        }
        counter1 = 0;
        setSearchParameters(parameters.getDiagonalParams(), sql, "c.diagonal.id");
        setSearchParameters(parameters.getResolutionsParams(), sql, "c.resolution.id");
        setSearchParameters(parameters.getFlashMemorySizesParams(), sql, "c.flashMemorySize.id");
        setSearchParameters(parameters.getBatteryCapacitiesParams(), sql, "c.batteryCapacity.id");
        setSearchParameters(parameters.getColorsParams(), sql, "c.color.id");
        setSearchParameters(parameters.getDisplayTypesParams(), sql, "c.displayType.id");
        setSearchParameters(parameters.getMemorySizesParams(), sql, "c.memorySize.id");
        setSearchParameters(parameters.getCpusParams(), sql, "c.cpu.id");
        setSearchParameters(parameters.getManufacturers(), sql, "p.manufacturer.id");
        counter1 = 0;
        this.sqlQuery = sql.toString();
    }

    private void setSearchParameters (List<String> parameters, StringBuilder sql, String id) {
        if (parameters.size() != 0) {
            if (counter1 > 0) {
                sql.append("AND");
            }
            sql.append(" ( ");
            int counter = 0;
            for (String diagonalID : parameters) {
                if (counter > 0) {
                    sql.append(" OR ");
                }
                sql.append(id + " = " + diagonalID);
                counter++;
            }
            sql.append(" ) ");
            counter1++;
        }
    }

    private void createQueryForCart(Set<String> ids) {
        StringBuilder sql = new StringBuilder("select p from ");
        sql.append(" Products p");
        int counter = 0;
        if (ids.size() > 0) {
            sql.append(" WHERE ");
        }
        for (String id : ids) {
            sql.append("p.id = ").append(id);
            counter++;
            if (counter < ids.size()) {
                sql.append(" OR ");
            }
        }
        this.sqlQuery = sql.toString();
    }

    public String getQuery() {
        return sqlQuery;
    }
}
