package de.hhu.cs.dbs.internship.project.table.eigeneSeiten;

import com.alexanderthelen.applicationkit.Application;
import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import de.hhu.cs.dbs.internship.project.Project;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EigeneSeiten extends Table {

    @Override
    public String getSelectQueryForTableWithFilter(String s) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForTableWithFilter(Data) nicht implementiert.");

        String selectQuery;

        if ((Integer) Project.getInstance().getData().get("permission") == 1) {
            throw  new SQLException("Nicht die notwendigen Rechte! Registrieren Sie sich als Autor um Zugriff zu erhalten!");
        }
        else {
            selectQuery = "SELECT * FROM Seite WHERE AutorBenutzerE_Mail_Adresse = '" + Application.getInstance().getData().get("loginEmail") + "'";

        }
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForRowWithData(Data) nicht implementiert.");

        String selectQuery = "SELECT * FROM Seite WHERE Seite.SeitenID = '" + data.get("Seite.SeitenID") + "'";
        return selectQuery;
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".insertRowWithData(Data) nicht implementiert.");

        //Bei AutorBenutzerE_Mail_Adresse wird immer die E_Mail_Adresse des Logins eingefügt, egal was in das Textfeld geschrieben wurde.

        if ((Integer) Project.getInstance().getData().get("permission") == 1) {
            throw  new SQLException("Nicht die notwendigen Rechte!");
        }
        else {

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime localDate = LocalDateTime.now();
            System.out.println(dateTimeFormatter.format(localDate));

            String currentDate = dateTimeFormatter.format(localDate);
;
            String statement = "INSERT INTO Seite VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = Project.getInstance().getConnection().prepareStatement(statement);
            preparedStatement.setObject(1, data.get("Seite.SeitenID"));
            preparedStatement.setObject(2, data.get("Seite.Seitennummer"));
            preparedStatement.setObject(3, data.get("Seite.Seitentyp"));
            preparedStatement.setObject(4, data.get("Seite.AutorBenutzerE_Mail_Adresse"));
            preparedStatement.setObject(5, currentDate);

            //Per Select prüfen, ob die eingegebene E-Mail die vom eingeloggten Benutzer ist.
            if (!data.get("Seite.AutorBenutzerE_Mail_Adresse").equals(Application.getInstance().getData().get("loginEmail").toString())) {
                throw new SQLException("Sie können keine Seite für einen anderen Autor anlegen!");
            }
            else {
                preparedStatement.executeUpdate();
            }
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
