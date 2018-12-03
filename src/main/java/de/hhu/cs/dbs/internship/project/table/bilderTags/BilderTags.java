package de.hhu.cs.dbs.internship.project.table.bilderTags;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;

import java.sql.SQLException;

public class BilderTags extends Table {

    @Override
    public String getSelectQueryForTableWithFilter(String s) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForTableWithFilter(Data) nicht implementiert.");

        String selectQuery = "SELECT Bild.* FROM Bild INNER JOIN Eintrag ON Bild.EintragEintragsID = Eintrag.EintragsID INNER JOIN Seite ON Eintrag.SeiteSeitenID = Seite.SeitenID INNER JOIN TagGehoertZuBild ON Bild.BildID = TagGehoertZuBild.BildBildID WHERE Seite.Seitentyp = 'oeffentlich'";


        if (s != null && !s.isEmpty()) {
            String[] tags = s.split("\\s+");
            for (int i = 0; i < tags.length; i++) {
                System.out.println(tags[i]);
                selectQuery += " AND TagGehoertZuBild.TagTagtext LIKE '%" + tags[i] + "%'";
            }
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
