package de.hhu.cs.dbs.internship.project.table.tagZuBild;

import com.alexanderthelen.applicationkit.Application;
import com.alexanderthelen.applicationkit.database.Data;
import com.alexanderthelen.applicationkit.database.Table;
import de.hhu.cs.dbs.internship.project.Project;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TagZuBild extends Table {

    @Override
    public String getSelectQueryForTableWithFilter(String s) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForTableWithFilter(Data) nicht implementiert.");

        //Nur selbst zugeordnete Bilder anzeigen

        String selectQuery;

        if ((Integer) Project.getInstance().getData().get("permission") == 1) {
            throw  new SQLException("Nicht die notwendigen Rechte! Registrieren Sie sich als Autor um Zugriff zu erhalten!");
        }
        else {
            selectQuery = "SELECT TagGehoertZuBild.* FROM TagGehoertZuBild INNER JOIN Bild ON TagGehoertZuBild.BildBildID = Bild.BildID INNER JOIN Eintrag ON Bild.EintragEintragsID = Eintrag.EintragsID INNER JOIN Seite ON Eintrag.SeiteSeitenID = Seite.SeitenID WHERE Seite.AutorBenutzerE_Mail_Adresse = '" + Application.getInstance().getData().get("loginEmail") + "'";
        }
        return selectQuery;
    }

    @Override
    public String getSelectQueryForRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".getSelectQueryForRowWithData(Data) nicht implementiert.");

        String selectQuery = "SELECT * FROM TagGehoertZuBild WHERE TagGehoertZuBild.TagTagtext = '" + data.get("TagGehoertZuBild.TagTagtext") + "' AND TagGehoertZuBild.BildBildID = '" + data.get("TagGehoertZuBild.BildBildID") + "'";
        return selectQuery;
    }

    @Override
    public void insertRowWithData(Data data) throws SQLException {
        //throw new SQLException(getClass().getName() + ".insertRowWithData(Data) nicht implementiert.");

        if ((Integer) Project.getInstance().getData().get("permission") == 1) {
            throw  new SQLException("Nicht die notwendigen Rechte!");
        }
        else {
            String statement = "INSERT INTO TagGehoertZuBild VALUES (?, ?)";
            PreparedStatement preparedStatement = Project.getInstance().getConnection().prepareStatement(statement);
            preparedStatement.setObject(1, data.get("TagGehoertZuBild.TagTagtext"));
            preparedStatement.setObject(2, data.get("TagGehoertZuBild.BildBildID"));

            //Prüfen, ob die gewählte BildID vom entsprechenden Autor angelegt wurde
            String selectQuerySeitenID = "SELECT BildBildID FROM TagGehoertZuBild INNER JOIN Bild ON '" + data.get("TagGehoertZuBild.BildBildID") + "' = Bild.BildID INNER JOIN Eintrag ON Bild.EintragEintragsID = Eintrag.EintragsID INNER JOIN Seite ON Eintrag.SeiteSeitenID = Seite.SeitenID WHERE Seite.AutorBenutzerE_Mail_Adresse ='" + Application.getInstance().getData().get("loginEmail") + "'";
            Statement statementSeitenID = Project.getInstance().getConnection().createStatement();
            ResultSet resultSetSeitenID = statementSeitenID.executeQuery(selectQuerySeitenID);

            if (!resultSetSeitenID.next()) {
                System.out.println(resultSetSeitenID.getRow());
                throw new SQLException("Das gewählte Bild wurde nicht von Ihnen hinzugefügt!");
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
