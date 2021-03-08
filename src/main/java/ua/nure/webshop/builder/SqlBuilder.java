package ua.nure.webshop.builder;

import ua.nure.webshop.domain.Parameters;

import java.util.Set;

public class SqlBuilder {

    private String sqlQuery;

    public SqlBuilder(Parameters parameters, String categoryName) {
        createQueryForFilter(parameters, categoryName);
    }

    public SqlBuilder(Set<String> ids) {
        createQueryForCart(ids);
    }

    private void createQueryForFilter(Parameters parameters, String categoryName) {
        StringBuilder sql = new StringBuilder("select p from ");
        sql.append(" Products p INNER JOIN ");
        sql.append(categoryName);
        sql.append(" c ON p.id = c.id");
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
            sql.append(" WHERE ");
        }
        int counter = 0;
        if (parameters.getManufacturers().size() != 0) {
            for (String manufacturerID : parameters.getManufacturers()) {
                if (counter > 0) {
                    sql.append(" OR ");
                }
                sql.append("p.manufacturer.id = " + manufacturerID);
                counter++;
            }
        }
        if (parameters.getDiagonalParams().size() != 0) {
            for (String diagonalID : parameters.getDiagonalParams()) {
                if (counter > 0) {
                    sql.append(" OR ");
                }
                sql.append("c.memorySize.id = " + diagonalID);
                counter++;
            }
        }
        if (parameters.getResolutionsParams().size() != 0) {
            for (String resolutionID : parameters.getResolutionsParams()) {
                if (counter > 0) {
                    sql.append(" OR ");
                }
                sql.append("c.resolution.id = " + resolutionID);
                counter++;
            }
        }
        if (parameters.getFlashMemorySizesParams().size() != 0) {
            for (String flashMemoryID : parameters.getFlashMemorySizesParams()) {
                if (counter > 0) {
                    sql.append(" OR ");
                }
                sql.append("c.flashMemorySize.id = " + flashMemoryID);
                counter++;
            }
        }
        if (parameters.getBatteryCapacitiesParams().size() != 0) {
            for (String batteryCapacityID : parameters.getBatteryCapacitiesParams()) {
                if (counter > 0) {
                    sql.append(" OR ");
                }
                sql.append("c.batteryCapacity.id = " + batteryCapacityID);
                counter++;
            }
        }
        if (parameters.getColorsParams().size() != 0) {
            for (String colorID : parameters.getColorsParams()) {
                if (counter > 0) {
                    sql.append(" OR ");
                }
                sql.append("c.color.id = " + colorID);
                counter++;
            }
        }
        if (parameters.getDisplayTypesParams().size() != 0) {
            for (String displayTypeID : parameters.getDisplayTypesParams()) {
                if (counter > 0) {
                    sql.append(" OR ");
                }
                sql.append("c.displayType.id = " + displayTypeID);
                counter++;
            }
        }
        if (parameters.getMemorySizesParams().size() != 0) {
            for (String memorySizeID : parameters.getMemorySizesParams()) {
                if (counter > 0) {
                    sql.append(" OR ");
                }
                sql.append("c.memorySize.id = " + memorySizeID);
                counter++;
            }
        }
        if (parameters.getCpusParams().size() != 0) {
            for (String cpuID : parameters.getCpusParams()) {
                if (counter > 0) {
                    sql.append(" OR ");
                }
                sql.append("c.cpu.id = " + cpuID);
                counter++;
            }
        }
        this.sqlQuery = sql.toString();
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
