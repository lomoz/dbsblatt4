package de.hhu.cs.dbs.internship.project.table.tagZuBild;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import de.hhu.cs.dbs.internship.project.Project;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TagZuBild extends Table {

    @Override
    public String getSelectQueryForTableWithFilter(String s) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForTableWithFilter(Data) nicht implementiert.");

        String selectQuery = "SELECT * FROM TagGehoertZuBild";
        if (s != null && !s.isEmpty()) {
            selectQuery += " WHERE TagGehoertZuBild.TagTagtext LIKE '%" + s + "%'";
            selectQuery += " OR TagGehoertZuBild.BildBildID LIKE '%" + s + "%'";
        }
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForRowWithData(Data) nicht implementiert.");

        String selectQuery = "SELECT * FROM TagGehoertZuBild WHERE TagGehoertZuBild.TagTagtext = '" + data.get("TagGehoertZuBild.TagTagtext") + "' AND TagGehoertZuBild.BildBildID = '" + data.get("TagGehoertZuBild.BildBildID") + "'";
        return selectQuery;
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".insertRowWithData(Data) nicht implementiert.");

        if ((Integer) Project.getInstance().getData().get("permission") == 1) {
            throw  new SQLException("Nicht die notwendigen Rechte!");
        }
        else {
            String statement = "INSERT INTO TagGehoertZuBild VALUES (?, ?)";
            PreparedStatement preparedStatement = Project.getInstance().getConnection().prepareStatement(statement);
            preparedStatement.setObject(1, data.get("TagGehoertZuBild.TagTagtext"));
            preparedStatement.setObject(2, data.get("TagGehoertZuBild.BildBildID"));
            preparedStatement.executeUpdate();
        }
    }

    @Override
    public void updateRowWithData(Data data, Data data1) throws SQLException {
        throw new SQLException(getClass().getName() + ".updateRowWithData(Data, Data) nicht implementiert.");
    }

    @Override
    public void deleteRowWithData(Data data) throws SQLException {
        throw new SQLException(getClass().getName() + ".deleteRowWithData(Data) nicht implementiert.");
    }
}
