package de.hhu.cs.dbs.internship.project.table.eigeneEintraege;

import com.alexanderthelen.applicationkit.Application;
import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import de.hhu.cs.dbs.internship.project.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EigeneEintraege extends Table {

    @Override
    public String getSelectQueryForTableWithFilter(String s) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForTableWithFilter(Data) nicht implementiert.");

        String selectQuery;

        if ((Integer) Project.getInstance().getData().get("permission") == 1) {
            throw  new SQLException("Nicht die notwendigen Rechte! Registrieren Sie sich als Autor um Zugriff zu erhalten!");
        }
        else {
            selectQuery = "SELECT Eintrag.* FROM Eintrag INNER JOIN Seite ON Eintrag.SeiteSeitenID = Seite.SeitenID WHERE Seite.AutorBenutzerE_Mail_Adresse = '" + Application.getInstance().getData().get("loginEmail") + "'";

        }

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

        if ((Integer) Project.getInstance().getData().get("permission") == 1) {
            throw  new SQLException("Nicht die notwendigen Rechte!");
        }
        else {

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime localTime = LocalDateTime.now();
            System.out.println(dateTimeFormatter.format(localTime));

            String currentTime = dateTimeFormatter.format(localTime);

            String statement = "INSERT INTO Eintrag VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = Project.getInstance().getConnection().prepareStatement(statement);
            preparedStatement.setObject(1, data.get("Eintrag.EintragsID"));
            preparedStatement.setObject(2, data.get("Eintrag.Eintragstitel"));
            preparedStatement.setObject(3, data.get("Eintrag.Eintragstext"));
            preparedStatement.setObject(4, currentTime);
            preparedStatement.setObject(5, data.get("Eintrag.SeiteSeitenID"));

            //Per Select prüfen, ob die eingegebene SeitenID vom eingeloggten Benutzer ist.
            //String selectQuerySeitenID = "SELECT * FROM Seite INNER JOIN Eintrag ON Seite.SeitenID = Eintrag.SeiteSeitenID WHERE Seite.AutorBenutzerE_Mail_Adresse = '" + Application.getInstance().getData().get("loginEmail") + "' AND Seite.SeitenID = '" + data.get("Eintrag.SeiteSeitenID") + "'";
            String selectQuerySeitenID = "SELECT Seite.SeitenID FROM Seite, Eintrag WHERE Seite.SeitenID = '" + data.get("Eintrag.SeiteSeitenID") + "' AND Seite.AutorBenutzerE_Mail_Adresse ='" + Application.getInstance().getData().get("loginEmail") + "'";

            Statement statementSeitenID = Project.getInstance().getConnection().createStatement();
            ResultSet resultSetSeitenID = statementSeitenID.executeQuery(selectQuerySeitenID);

            if (!resultSetSeitenID.next()) {
                throw new SQLException("Ausgewählte Seite nicht von Ihnen erstellt!");
            }
            else {
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public void updateRowWithData(Data data, Data data1) throws SQLException {
        //throw new SQLException(getClass().getName() + ".updateRowWithData(Data, Data) nicht implementiert.");

        String statement = "UPDATE Eintrag SET Eintragstitel = ?, Eintragstext = ?, Eintragsuhrzeit = ? WHERE Eintragstitel = ? AND Eintragstext = ? AND Eintragsuhrzeit = ?";
        PreparedStatement preparedStatement = Project.getInstance().getConnection().prepareStatement(statement);
        preparedStatement.setObject(1, data1.get("Eintrag.Eintragstitel"));
        preparedStatement.setObject(2, data1.get("Eintrag.Eintragstext"));
        preparedStatement.setObject(3, data1.get("Eintrag.Eintragsuhrzeit"));
        preparedStatement.setObject(4, data.get("Eintrag.Eintragstitel"));
        preparedStatement.setObject(5, data.get("Eintrag.Eintragstext"));
        preparedStatement.setObject(6, data.get("Eintrag.Eintragsuhrzeit"));
        preparedStatement.executeUpdate();
    }

    @Override
    public void deleteRowWithData(Data data) throws SQLException {
        throw new SQLException(getClass().getName() + ".deleteRowWithData(Data) nicht implementiert.");
    }
}
