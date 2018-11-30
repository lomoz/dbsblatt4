package de.hhu.cs.dbs.internship.project.table.account;

import com.alexanderthelen.applicationkit.Application;
import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import de.hhu.cs.dbs.internship.project.Project;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Account extends Table {

    @Override
    public String getSelectQueryForTableWithFilter(String s) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForTableWithFilter(Data) nicht implementiert.");

        System.out.println(Application.getInstance().getData().get("loginEmail"));
        String selectQuery = "";

        if ((Integer) Project.getInstance().getData().get("permission") == 1) {
            selectQuery = "SELECT * FROM Benutzer WHERE E_Mail_Adresse = '" + Application.getInstance().getData().get("loginEmail") + "'";
        }
        else if ((Integer) Project.getInstance().getData().get("permission") == 2) {
            //Alles von Benutzer + alles von Autor außer dem Avatarbild im SELECT damit die GUI nicht abstürzt?
            selectQuery = "SELECT Benutzer.*, Autor.Pseudonym, Autor.Preis, Autor.Avatar FROM Benutzer INNER JOIN Autor ON Benutzer.E_Mail_Adresse = Autor.BenutzerE_Mail_Adresse WHERE Benutzer.E_Mail_Adresse = '" + Application.getInstance().getData().get("loginEmail") + "'";
        }
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForRowWithData(Data) nicht implementiert.");

        String selectQuery = "";

        if ((Integer) Project.getInstance().getData().get("permission") == 1) {
            selectQuery = "SELECT * FROM Benutzer WHERE E_Mail_Adresse = '" + data.get("Benutzer.E_Mail_Adresse") + "'";
        }
        else if ((Integer) Project.getInstance().getData().get("permission") == 2) {
            //Alles von Benutzer + alles von Autor außer dem Avatarbild im SELECT damit die GUI nicht abstürzt?
            selectQuery = "SELECT Benutzer.*, Autor.Pseudonym, Autor.Preis, Autor.Avatar FROM Benutzer INNER JOIN Autor ON Benutzer.E_Mail_Adresse = Autor.BenutzerE_Mail_Adresse WHERE Benutzer.E_Mail_Adresse = '" + data.get("Benutzer.E_Mail_Adresse") + "'";
        }
        return selectQuery;
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".insertRowWithData(Data) nicht implementiert.");

        if ((Integer) Project.getInstance().getData().get("permission") == 1) {
            //Ermöglichen, dass man sich als Autor registrieren kann.
            //Nach erfolgreicher Eingabe müssen die Zugriffsrechte "permission" von 1 auf 2 geändert werden.
            //throw new SQLException("Als Autor registrieren noch nicht implementiert!");

            String statement = "INSERT INTO Autor VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = Project.getInstance().getConnection().prepareStatement(statement);
            preparedStatement.setObject(2, data.get("Autor.Pseudonym"));
            preparedStatement.setObject(3, data.get("Autor.Preis"));
            preparedStatement.setObject(4, data.get("Autor.Avatar"));
            preparedStatement.executeUpdate();
        }
        else if ((Integer) Project.getInstance().getData().get("permission") == 2) {
            throw new SQLException("Sie sind bereits als Autor und somit auch als Benutzer registriert!");
        }
    }

    @Override
    public void updateRowWithData(Data data, Data data1) throws SQLException {
        //throw new SQLException(getClass().getName() + ".updateRowWithData(Data, Data) nicht implementiert.");

        if ((Integer) Project.getInstance().getData().get("permission") == 1) {
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
        else if ((Integer) Project.getInstance().getData().get("permission") == 2) {
            //UPDATE Attribute Benutzer
            String statementBenutzer = "UPDATE Benutzer SET E_Mail_Adresse = ?, Nachname = ?, Vorname = ?, Passwort = ? WHERE E_Mail_Adresse = ? AND Nachname = ? AND Vorname = ? AND Passwort = ?";
            PreparedStatement preparedStatementBenutzer = Project.getInstance().getConnection().prepareStatement(statementBenutzer);
            preparedStatementBenutzer.setObject(1, data1.get("Benutzer.E_Mail_Adresse"));
            preparedStatementBenutzer.setObject(2, data1.get("Benutzer.Nachname"));
            preparedStatementBenutzer.setObject(3, data1.get("Benutzer.Vorname"));
            preparedStatementBenutzer.setObject(4, data1.get("Benutzer.Passwort"));
            preparedStatementBenutzer.setObject(5, data.get("Benutzer.E_Mail_Adresse"));
            preparedStatementBenutzer.setObject(6, data.get("Benutzer.Nachname"));
            preparedStatementBenutzer.setObject(7, data.get("Benutzer.Vorname"));
            preparedStatementBenutzer.setObject(8, data.get("Benutzer.Passwort"));
            preparedStatementBenutzer.executeUpdate();

            //Neue E-Mail-Adresse global speichern
            Application.getInstance().getData().put("loginEmail", data1.get("Benutzer.E_Mail_Adresse"));

            //UPDATE Attribute Autor
            String statementAutor = "UPDATE Autor SET Pseudonym = ?, Preis = ?, Avatar = ? WHERE Pseudonym = ? AND Preis = ? AND Avatar = ?";
            PreparedStatement preparedStatementAutor = Project.getInstance().getConnection().prepareStatement(statementAutor);
            preparedStatementAutor.setObject(1, data1.get("Autor.Pseudonym"));
            preparedStatementAutor.setObject(2, data1.get("Autor.Preis"));
            preparedStatementAutor.setObject(3, data1.get("Autor.Avatar"));
            preparedStatementAutor.setObject(4, data.get("Autor.Pseudonym"));
            preparedStatementAutor.setObject(5, data.get("Autor.Preis"));
            preparedStatementAutor.setObject(6, data.get("Autor.Avatar"));
            preparedStatementAutor.executeUpdate();
        }
    }

    @Override
    public void deleteRowWithData(Data data) throws SQLException {
        throw new SQLException(getClass().getName() + ".deleteRowWithData(Data) nicht implementiert.");
    }
}
