package de.hhu.cs.dbs.internship.project.table.seitenGekauft;

import com.alexanderthelen.applicationkit.Application;
import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;

import java.sql.SQLException;

public class SeitenGekauft extends Table {

    @Override
    public String getSelectQueryForTableWithFilter(String s) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForTableWithFilter(Data) nicht implementiert.");

        //Reicht es die Seiten anzuzeigen oder müssen auch alle Einträge angezeigt werden?

        String selectQuery = "SELECT Seite.*, Eintrag.EintragsID, Eintrag.Eintragstitel, Eintrag.Eintragstext, Eintrag.Eintragsuhrzeit FROM Seite INNER JOIN Eintrag ON Seite.SeitenID = Eintrag.SeiteSeitenID INNER JOIN Transaktion ON Seite.AutorBenutzerE_Mail_Adresse = Transaktion.AutorBenutzerE_Mail_Adresse WHERE Transaktion.BenutzerE_Mail_Adresse = '" + Application.getInstance().getData().get("loginEmail") + "'";

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

        String selectQuery = "SELECT Seite.*, Eintrag.EintragsID, Eintrag.Eintragstitel, Eintrag.Eintragstext, Eintrag.Eintragsuhrzeit FROM Seite INNER JOIN Eintrag ON Seite.SeitenID = Eintrag.SeiteSeitenID WHERE Seite.SeitenID = '" + data.get("Seite.SeitenID") + "' AND Eintrag.EintragsID = '" + data.get("Eintrag.EintragsID") + "'";
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
