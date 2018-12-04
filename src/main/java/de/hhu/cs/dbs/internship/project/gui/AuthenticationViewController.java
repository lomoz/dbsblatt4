package de.hhu.cs.dbs.internship.project.gui;

import com.alexanderthelen.applicationkit.Application;
import com.alexanderthelen.applicationkit.database.Data;
import de.hhu.cs.dbs.internship.project.Project;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationViewController extends com.alexanderthelen.applicationkit.gui.AuthenticationViewController {
    protected AuthenticationViewController(String name) {
        super(name);
    }

    public static AuthenticationViewController createWithName(String name) throws IOException {
        AuthenticationViewController viewController = new AuthenticationViewController(name);
        viewController.loadView();
        return viewController;
    }

    @Override
    public void loginUser(Data data) throws SQLException {

        String loginEmail = data.get("email").toString();
        String loginPassword = data.get("password").toString();

        String statementCheckLogin = "SELECT E_Mail_Adresse, Passwort FROM Benutzer WHERE E_Mail_Adresse = '" + loginEmail + "' and Passwort = '" + loginPassword + "'";
        PreparedStatement preparedStatementCheckLogin = Project.getInstance().getConnection().prepareStatement(statementCheckLogin);
        ResultSet resultSetCheckLogin = preparedStatementCheckLogin.executeQuery();

        if (!resultSetCheckLogin.next()) {
            throw new SQLException("E-Mail-Adresse oder Passwort falsch!");
        }
        else {
            //Login E-Mail-Adresse global als loginEmail speichern
            Application.getInstance().getData().put("loginEmail", loginEmail);

            // Prüfen, ob es sich um einen Benutzer (1) oder einen Autor (2) handelt und Zugriffsrechte entsprechend zuweisen.
            String statementCheckPermission = "SELECT * FROM Autor WHERE BenutzerE_Mail_Adresse  = '" + loginEmail + "'";
            PreparedStatement preparedStatementCheckPermission = Project.getInstance().getConnection().prepareStatement(statementCheckPermission);
            ResultSet resultSetCheckPermission = preparedStatementCheckPermission.executeQuery();
            if (!resultSetCheckPermission.next()) {
                System.out.println("ResultSet leer, also gibt es keinen Autoren mit der Login E-Mail-Adresse! --> Permission = 1");
                Application.getInstance().getData().put("permission", 1);
            }
            else {
                System.out.println("ResultSet enthält ein Ergebnis, also gibt es einen Autoren mit der Login E-Mail-Adresse! --> Permission = 2");
                Application.getInstance().getData().put("permission", 2);
            }
        }
    }

    @Override
    public void registerUser(Data data) throws SQLException {

        if (!data.get("password1").toString().equals(data.get("password2").toString())) {
            throw new SQLException("Die eingegebenen Passwörter stimmen nicht überein!");
        }

        String statement = "INSERT INTO Benutzer VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = Project.getInstance().getConnection().prepareStatement(statement);
        preparedStatement.setObject(1, data.get("eMail"));
        preparedStatement.setObject(2, data.get("lastName"));
        preparedStatement.setObject(3, data.get("firstName"));
        preparedStatement.setObject(4, data.get("password1"));
        preparedStatement.executeUpdate();
    }
}
