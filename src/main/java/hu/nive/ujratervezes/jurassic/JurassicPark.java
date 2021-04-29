package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {
        List<String> dinosOverPop = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            PreparedStatement st = connection.prepareStatement(
                    "SELECT breed" +
                            " FROM dinosaur " +
                            " WHERE actual > expected" +
                            " ORDER BY breed DESC");

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                dinosOverPop.add(rs.getString("breed"));
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return dinosOverPop;
    }


}
