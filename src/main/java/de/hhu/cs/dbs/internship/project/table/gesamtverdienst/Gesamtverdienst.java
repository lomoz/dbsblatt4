package de.hhu.cs.dbs.internship.project.table.gesamtverdienst;

import com.alexanderthelen.applicationkit.Application;
import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import de.hhu.cs.dbs.internship.project.Project;

import java.sql.SQLException;

public class Gesamtverdienst extends Table {

    @Override
    public String getSelectQueryForTableWithFilter(String s) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForTableWithFilter(Data) nicht implementiert.");

        // Vielleicht noch Überschrift der Zeile von "(sum(Autor.Preis) - sum(Transaktion.Gutschein))" auf Gesamtverdienst ändern und den Betrag auf 2 Nachkommastellen runden.
        String selectQuery;

        if ((Integer) Project.getInstance().getData().get("permission") == 1) {
            throw  new SQLException("Nicht die notwendigen Rechte!");
        }
        else {
            selectQuery = "SELECT Autor.BenutzerE_Mail_Adresse, (sum(Autor.Preis) - sum(Transaktion.Gutschein)) FROM Autor INNER JOIN Transaktion ON Autor.BenutzerE_Mail_Adresse = Transaktion.AutorBenutzerE_Mail_Adresse WHERE Autor.BenutzerE_Mail_Adresse = '" + Application.getInstance().getData().get("loginEmail") + "' GROUP BY Autor.BenutzerE_Mail_Adresse";
        }
        return selectQuery;


    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        throw new SQLException(getClass().getName() + ".getSelectQueryForRowWithData(Data) nicht implementiert.");
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        throw new SQLException(getClass().getName() + ".insertRowWithData(Data) nicht implementiert.");
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
