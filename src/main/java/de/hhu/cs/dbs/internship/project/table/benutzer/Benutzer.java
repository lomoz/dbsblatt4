package de.hhu.cs.dbs.internship.project.table.benutzer;

import com.alexanderthelen.applicationkit.Application;
import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import de.hhu.cs.dbs.internship.project.Project;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Benutzer extends Table {

    @Override
    public String getSelectQueryForTableWithFilter(String s) throws SQLException {

        String selectQuery = "SELECT * FROM Benutzer WHERE E_Mail_Adresse = '" + Application.getInstance().getData().get("loginEmail") + "'";
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {

        String selectQuery = "SELECT * FROM Benutzer WHERE Benutzer.E_Mail_Adresse = '" + data.get("Benutzer.E_Mail_Adresse") + "'";
        return selectQuery;
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {

        String statement = "INSERT INTO Benutzer VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = Project.getInstance().getConnection().prepareStatement(statement);
        preparedStatement.setObject(1, data.get("Benutzer.E_Mail_Adresse"));
        preparedStatement.setObject(2, data.get("Benutzer.Nachname"));
        preparedStatement.setObject(3, data.get("Benutzer.Vorname"));
        preparedStatement.setObject(4, data.get("Benutzer.Passwort"));
        preparedStatement.executeUpdate();
    }

    @Override
    public void updateRowWithData(Data data, Data data1) throws SQLException {

        String statement = "UPDATE Benutzer SET E_Mail_Adresse = ?, Nachname = ?, Vorname = ?, Passwort = ? WHERE E_Mail_Adresse = ? AND Nachname = ? AND Vorname = ? AND Passwort = ?";
        PreparedStatement preparedStatement = Project.getInstance().getConnection().prepareStatement(statement);
        preparedStatement.setObject(1, data1.get("Benutzer.E_Mail_Adresse"));
        preparedStatement.setObject(2, data1.get("Benutzer.Nachname"));
        preparedStatement.setObject(3, data1.get("Benutzer.Vorname"));
        preparedStatement.setObject(4, data1.get("Benutzer.Passwort"));
        preparedStatement.setObject(5, data.get("Benutzer.E_Mail_Adresse"));
        preparedStatement.setObject(6, data.get("Benutzer.Nachname"));
        preparedStatement.setObject(7, data.get("Benutzer.Vorname"));
        preparedStatement.setObject(8, data.get("Benutzer.Passwort"));
        preparedStatement.executeUpdate();

        //Neue E-Mail-Adresse global speichern
        Application.getInstance().getData().put("loginEmail", data1.get("Benutzer.E_Mail_Adresse"));
    }

    @Override
    public void deleteRowWithData(Data data) throws SQLException {

        String statement = "DELETE FROM Benutzer WHERE E_Mail_Adresse = ?";
        PreparedStatement preparedStatement = Project.getInstance().getConnection().prepareStatement(statement);
        preparedStatement.setObject(1, data.get("Benutzer.E_Mail_Adresse"));
        preparedStatement.executeUpdate();
    }
}
