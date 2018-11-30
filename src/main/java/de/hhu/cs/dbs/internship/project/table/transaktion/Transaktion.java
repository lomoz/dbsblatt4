package de.hhu.cs.dbs.internship.project.table.transaktion;

import com.alexanderthelen.applicationkit.Application;
import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import de.hhu.cs.dbs.internship.project.Project;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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

        String statement = "INSERT INTO Transaktion VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = Project.getInstance().getConnection().prepareStatement(statement);
        preparedStatement.setObject(1, data.get("Transaktion.TransaktionsID"));
        preparedStatement.setObject(2, data.get("Transaktion.Transaktionsdatum"));
        preparedStatement.setObject(3, data.get("Transaktion.Zahlungsmittel"));
        preparedStatement.setObject(4, data.get("Transaktion.Gutschein"));
        preparedStatement.setObject(5, data.get("Transaktion.BenutzerE_Mail_Adresse"));
        preparedStatement.setObject(6, data.get("Transaktion.Transaktionszweck"));
        preparedStatement.setObject(7, data.get("Transaktion.AutorBenutzerE_Mail_Adresse"));
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
