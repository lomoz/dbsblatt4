package de.hhu.cs.dbs.internship.project.table.eigeneEinträge;

import com.alexanderthelen.applicationkit.Application;
import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import de.hhu.cs.dbs.internship.project.Project;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EigeneEinträge extends Table {

    @Override
    public String getSelectQueryForTableWithFilter(String s) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForTableWithFilter(Data) nicht implementiert.");

        String selectQuery = "SELECT Eintrag.* FROM Eintrag INNER JOIN Seite ON Eintrag.SeiteSeitenID = Seite.SeitenID WHERE Seite.AutorBenutzerE_Mail_Adresse = '" + Application.getInstance().getData().get("loginEmail") + "'";
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForRowWithData(Data) nicht implementiert.");

        String selectQuery = "SELECT * FROM Eintrag WHERE Eintrag.EintragsID = '" + data.get("Eintrag.EintragsID") + "'";
        return selectQuery;
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".insertRowWithData(Data) nicht implementiert.");

        //Wie prüfen, dass die angegebene SeitenID auch zu einer Seite gehört, die vom eingeloggten Autor angelegt wurde?

        if ((Integer) Project.getInstance().getData().get("permission") == 1) {
            throw  new SQLException("Nicht die notwendigen Rechte!");
        }
        else {
            String statement = "INSERT INTO Eintrag VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = Project.getInstance().getConnection().prepareStatement(statement);
            preparedStatement.setObject(1, data.get("Eintrag.EintragsID"));
            preparedStatement.setObject(2, data.get("Eintrag.Eintragstitel"));
            preparedStatement.setObject(3, data.get("Eintrag.Eintragstext"));
            preparedStatement.setObject(4, data.get("Eintrag.Eintragsuhrzeit"));
            preparedStatement.setObject(5, data.get("Eintrag.SeiteSeitenID"));
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
