package de.hhu.cs.dbs.internship.project.table.seitenPublic;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;

import java.sql.SQLException;

public class SeitenPublic extends Table {

    @Override
    public String getSelectQueryForTableWithFilter(String s) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForTableWithFilter(Data) nicht implementiert.");

        String selectQuery = "SELECT Seite.*, Eintrag.EintragsID, Eintrag.Eintragstitel, Eintrag.Eintragstext, Eintrag.Eintragsuhrzeit FROM Seite INNER JOIN Eintrag ON Seite.SeitenID = Eintrag.SeiteSeitenID WHERE Seite.Seitentyp = 'oeffentlich'";

        if (s != null && !s.isEmpty()) {
            //String am ersten Leerzeichen trennen
            String[] dateAndTitle = s.split("\\s+");
            if (dateAndTitle.length != 2) {
                throw new SQLException("Suchformat nicht eingehalten!");
            }
            else {
                selectQuery += " AND Seite.Seitendatum LIKE '%" + dateAndTitle[0] + "%'";
                selectQuery += " AND Eintrag.Eintragstitel LIKE '%" + dateAndTitle[1] + "%'";
            }
        }

        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForRowWithData(Data) nicht implementiert.");

        String selectQuery = "SELECT Seite.*, Eintrag.EintragsID, Eintrag.Eintragstitel, Eintrag.Eintragstext, Eintrag.Eintragsuhrzeit FROM Seite INNER JOIN Eintrag ON Seite.SeitenID = Eintrag.SeiteSeitenID WHERE Seite.SeitenID = '" + data.get("Seite.SeitenID") + "'";
        return selectQuery;
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
