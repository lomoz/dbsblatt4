package de.hhu.cs.dbs.internship.project.table.bilderTags;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;

import java.sql.SQLException;

public class BilderTags extends Table {

    @Override
    public String getSelectQueryForTableWithFilter(String s) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForTableWithFilter(Data) nicht implementiert.");

        String selectQuery = "";

        if (s != null && !s.isEmpty()) {
            String[] tags = s.split("\\s+");
            for (int i = 0; i < tags.length-1; i++) {
                System.out.println(tags[i]);
                selectQuery += "SELECT Seite.SeitenID, Seite.Seitentyp, Eintrag.EintragsID, Bild.BildID FROM Bild INNER JOIN Eintrag ON Bild.EintragEintragsID = Eintrag.EintragsID INNER JOIN Seite ON Eintrag.SeiteSeitenID = Seite.SeitenID INNER JOIN TagGehoertZuBild ON Bild.BildID = TagGehoertZuBild.BildBildID WHERE TagGehoertZuBild.TagTagtext LIKE '%" + tags[i] + "%' INTERSECT ";
            }
            selectQuery += "SELECT Seite.SeitenID, Seite.Seitentyp, Eintrag.EintragsID, Bild.BildID FROM Bild INNER JOIN Eintrag ON Bild.EintragEintragsID = Eintrag.EintragsID INNER JOIN Seite ON Eintrag.SeiteSeitenID = Seite.SeitenID INNER JOIN TagGehoertZuBild ON Bild.BildID = TagGehoertZuBild.BildBildID WHERE TagGehoertZuBild.TagTagtext LIKE '%" + tags[tags.length-1] + "%'";

        }
        else {
            selectQuery = "SELECT Seite.SeitenID, Seite.Seitentyp, Eintrag.EintragsID, Bild.BildID FROM Bild INNER JOIN Eintrag ON Bild.EintragEintragsID = Eintrag.EintragsID INNER JOIN Seite ON Eintrag.SeiteSeitenID = Seite.SeitenID INNER JOIN TagGehoertZuBild ON Bild.BildID = TagGehoertZuBild.BildBildID";
        }

        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForRowWithData(Data) nicht implementiert.");

        //Prüfen, ob die angeklickte Zeile zu einer öffentlichen oder privaten Seite gehört und entsprechende Daten ausgeben.
        String selectQuery;
        if (data.get("Seite.Seitentyp").equals("privat")) {
            selectQuery = "SELECT Seite.AutorBenutzerE_Mail_Adresse FROM Seite INNER JOIN Eintrag ON Seite.SeitenID = Eintrag.SeiteSeitenID INNER JOIN Bild ON Eintrag.EintragsID = Bild.EintragEintragsID WHERE Bild.BildID = '" + data.get("Bild.BildID") + "'";
        }
        else {
            selectQuery = "SELECT Eintrag.*, Bild.Bilddatei FROM Seite INNER JOIN Eintrag ON Seite.SeitenID = Eintrag.SeiteSeitenID INNER JOIN Bild ON Eintrag.EintragsID = Bild.EintragEintragsID WHERE Bild.BildID = '" + data.get("Bild.BildID") + "'";

        }

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
