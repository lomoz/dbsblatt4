package de.hhu.cs.dbs.internship.project.table.transaktion;

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

public class Transaktion extends Table {

    @Override
    public String getSelectQueryForTableWithFilter(String s) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForTableWithFilter(Data) nicht implementiert.");

        String selectQuery = "SELECT * FROM Transaktion WHERE BenutzerE_Mail_Adresse = '" + Application.getInstance().getData().get("loginEmail") + "'";
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForRowWithData(Data) nicht implementiert.");

        String selectQuery = "SELECT * FROM Transaktion WHERE Transaktion.TransaktionsID = '" + data.get("Transaktion.TransaktionsID") + "'";
        return selectQuery;
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".insertRowWithData(Data) nicht implementiert.");

        //Vor executeUpdate() noch prüfen ob die eingegebene E-Mail der Login E-Mail entspricht.

        //Immer den Preis des entsprechenden Autors einfügen statt den eingegebenen Transaktionsbetrag
        String selectQueryPreis = "SELECT Autor.Preis FROM Autor INNER JOIN Transaktion ON Autor.BenutzerE_Mail_Adresse = Transaktion.AutorBenutzerE_Mail_Adresse WHERE Transaktion.AutorBenutzerE_Mail_Adresse = '" + data.get("Transaktion.AutorBenutzerE_Mail_Adresse") + "'";

        Statement statementPreis = Project.getInstance().getConnection().createStatement();
        ResultSet resultSetPreis = statementPreis.executeQuery(selectQueryPreis);
        resultSetPreis.next();

        Double gutscheinWert;

        if (Double.parseDouble(data.get("Transaktion.Gutschein").toString()) > resultSetPreis.getDouble(1)) {
            gutscheinWert = resultSetPreis.getDouble(1);
            System.out.println(gutscheinWert);
        }
        else {
             gutscheinWert = Double.parseDouble(data.get("Transaktion.Gutschein").toString());
            System.out.println(gutscheinWert);
        }

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(dateTimeFormatter.format(localDateTime));

        String statement = "INSERT INTO Transaktion VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = Project.getInstance().getConnection().prepareStatement(statement);
        preparedStatement.setObject(1, data.get("Transaktion.TransaktionsID"));
        preparedStatement.setObject(2, data.get("Transaktion.Transaktionsdatum"));
        preparedStatement.setObject(3, resultSetPreis.getDouble(1));
        preparedStatement.setObject(4, data.get("Transaktion.Zahlungsmittel"));
        preparedStatement.setObject(5, gutscheinWert);
        preparedStatement.setObject(6, data.get("Transaktion.BenutzerE_Mail_Adresse"));
        preparedStatement.setObject(7, data.get("Transaktion.Transaktionszweck"));
        preparedStatement.setObject(8, data.get("Transaktion.AutorBenutzerE_Mail_Adresse"));

        //Per Select prüfen, ob die eingegebene E-Mail die vom eingeloggten Benutzer ist.
        if (!data.get("Transaktion.BenutzerE_Mail_Adresse").equals(Application.getInstance().getData().get("loginEmail").toString())) {
            throw new SQLException("Sie können keine Transaktion für einen anderen Nutzer tätigen!");
        }

        else {
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
