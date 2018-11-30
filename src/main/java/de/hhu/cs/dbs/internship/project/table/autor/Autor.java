package de.hhu.cs.dbs.internship.project.table.autor;

import com.alexanderthelen.applicationkit.Application;
import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import de.hhu.cs.dbs.internship.project.Project;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Autor extends Table {

    @FXML
    protected PasswordField passwordPasswordField;


    @Override
    public String getSelectQueryForTableWithFilter(String s) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForTableWithFilter(Data) nicht implementiert.");

        String selectQuery = "SELECT Autor.BenutzerE_Mail_Adresse, Benutzer.Nachname, Benutzer.Vorname, Autor.Pseudonym, Autor.Preis, Autor.Avatar FROM Benutzer INNER JOIN Autor ON Benutzer.E_Mail_Adresse = Autor.BenutzerE_Mail_Adresse";
        if (s != null && !s.isEmpty()) {
            selectQuery += " WHERE Autor.BenutzerE_Mail_Adresse LIKE '%" + s + "%'";
            selectQuery += " OR Benutzer.Nachname LIKE '%" + s + "%'";
            selectQuery += " OR Benutzer.Vorname LIKE '%" + s + "%'";
            selectQuery += " OR Autor.Pseudonym LIKE '%" + s + "%'";
            selectQuery += " OR Autor.Preis LIKE '%" + s + "%'";
        }
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForRowWithData(Data) nicht implementiert.");

        String selectQuery = "SELECT * FROM Autor WHERE BenutzerE_Mail_Adresse = '" + data.get("Autor.BenutzerE_Mail_Adresse") + "'";
        return selectQuery;

    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".insertRowWithData(Data) nicht implementiert.");

        if ((Integer) Project.getInstance().getData().get("permission") == 1) {
            //In Eingabe erstes Feld (AutorE_Mail_Adresse) disablen?

            String statement = "INSERT INTO Autor VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = Project.getInstance().getConnection().prepareStatement(statement);
            preparedStatement.setObject(1, Application.getInstance().getData().get("loginEmail"));
            preparedStatement.setObject(2, data.get("Autor.Pseudonym"));
            preparedStatement.setObject(3, data.get("Autor.Preis"));
            preparedStatement.setObject(4, data.get("Autor.Avatar"));
            preparedStatement.executeUpdate();

            Application.getInstance().getData().put("permission", 2);
        }
        else if ((Integer) Project.getInstance().getData().get("permission") == 2) {
            throw new SQLException("Sie sind bereits als Autor und somit auch als Benutzer registriert!");
        }
    }

    @Override
    public void updateRowWithData(Data data, Data data1) throws SQLException {
        throw new SQLException(getClass().getName() + ".updateRowWithData(Data, Data) nicht implementiert.");
    }

    @Override
    public void deleteRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".deleteRowWithData(Data) nicht implementiert.");

        String statement = "DELETE FROM Autor WHERE BenutzerE_Mail_Adresse = ?";
        PreparedStatement preparedStatement = Project.getInstance().getConnection().prepareStatement(statement);
        preparedStatement.setObject(1, data.get("Autor.BenutzerE_Mail_Adresse"));
        preparedStatement.executeUpdate();
        Application.getInstance().getData().put("permission", 1);
    }
}
