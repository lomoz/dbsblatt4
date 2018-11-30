package de.hhu.cs.dbs.internship.project.table.gesamtverdienst;

import com.alexanderthelen.applicationkit.Application;
import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import de.hhu.cs.dbs.internship.project.Project;

import java.sql.SQLException;

public class Gesamtverdienst extends Table {

    @Override
    public String getSelectQueryForTableWithFilter(String s) throws SQLException {
        throw new SQLException(getClass().getName() + ".getSelectQueryForTableWithFilter(Data) nicht implementiert.");

        // Anfrage wie folgt: Transaktionsbetrag komplett aus DB nehmen, dafür Preis von Autor minus Gutscheinbetrag

        /*
        String selectQuery;

        if ((Integer) Project.getInstance().getData().get("permission") == 1) {
            throw  new SQLException("Nicht die notwendigen Rechte!");
        }
        else {
            selectQuery = "SELECT AutorBenutzerE_Mail_Adresse, sum(Transaktionsbetrag) FROM Transaktion WHERE AutorBenutzerE_Mail_Adresse = '" + Application.getInstance().getData().get("loginEmail") + "' GROUP BY AutorBenutzerE_Mail_Adresse";
        }
        return selectQuery;
        */

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
