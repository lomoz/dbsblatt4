package de.hhu.cs.dbs.internship.project.table.bilderGPS;

import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;

import java.sql.SQLException;

public class BilderGPS extends Table {

    @Override
    public String getSelectQueryForTableWithFilter(String s) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForTableWithFilter(Data) nicht implementiert.");

        String selectQuery = "SELECT Bild.BildID, Bild.Bilddatei FROM Bild INNER JOIN BildHatGPSKoordinaten ON Bild.BildID = BildHatGPSKoordinaten.BildBildID";

        if (s != null && !s.isEmpty()) {
            String[] latLng = s.split("\\s+");
            if (latLng.length != 4) {
                throw new SQLException("Suchformat nicht eingehalten!");
            }
            else {
                String lngFrom = latLng[0];
                String lngTo = latLng[1];
                String latFrom = latLng[2];
                String latTo = latLng[3];
                selectQuery += " INNER JOIN GPSKoordinaten ON BildHatGPSKoordinaten.GPSKoordinatenGPSKoordinatenID = GPSKoordinaten.GPSKoordinatenID WHERE GPSKoordinaten.Laengengrad > " + lngFrom + " AND GPSKoordinaten.Laengengrad < " + lngTo + " AND GPSKoordinaten.Breitengrad > " + latFrom + " AND GPSKoordinaten.Breitengrad < " + latTo;
            }
        }

        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForRowWithData(Data) nicht implementiert.");

        String selectQuery = "SELECT Bild.BildID, Bild.Bilddatei FROM Bild INNER JOIN BildHatGPSKoordinaten ON Bild.BildID = BildHatGPSKoordinaten.BildBildID WHERE Bild.BildID = '" + data.get("Bild.BildID") + "'";
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
