package de.hhu.cs.dbs.internship.project.table.bewertung;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import de.hhu.cs.dbs.internship.project.Project;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Bewertung extends Table {

    @Override
    public String getSelectQueryForTableWithFilter(String s) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForTableWithFilter(Data) nicht implementiert.");

        String selectQuery = "SELECT * FROM BenutzerBewertetAutor";
        if (s != null && !s.isEmpty()) {
            selectQuery += " WHERE BenutzerBewertetAutor.BenutzerE_Mail_Adresse LIKE '%" + s + "%'";
            selectQuery += " OR BenutzerBewertetAutor.AutorBenutzerE_Mail_Adresse LIKE '%" + s + "%'";
            selectQuery += " OR BenutzerBewertetAutor.Bewertungstext LIKE '%" + s + "%'";
            selectQuery += " OR BenutzerBewertetAutor.Benotung LIKE '%" + s + "%'";
        }
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForRowWithData(Data) nicht implementiert.");

        String selectQuery = "SELECT * FROM BenutzerBewertetAutor WHERE BenutzerBewertetAutor.BenutzerE_Mail_Adresse = '" + data.get("BenutzerBewertetAutor.BenutzerE_Mail_Adresse") + "' AND BenutzerBewertetAutor.AutorBenutzerE_Mail_Adresse = '" + data.get("BenutzerBewertetAutor.AutorBenutzerE_Mail_Adresse") + "'";
        return selectQuery;
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".insertRowWithData(Data) nicht implementiert.");

        String statement = "INSERT INTO BenutzerBewertetAutor VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = Project.getInstance().getConnection().prepareStatement(statement);
        preparedStatement.setObject(1, data.get("BenutzerBewertetAutor.BenutzerE_Mail_Adresse"));
        preparedStatement.setObject(2, data.get("BenutzerBewertetAutor.AutorBenutzerE_Mail_Adresse"));
        preparedStatement.setObject(3, data.get("BenutzerBewertetAutor.Bewertungstext"));
        preparedStatement.setObject(4, data.get("BenutzerBewertetAutor.Benotung"));
        preparedStatement.executeUpdate();
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
