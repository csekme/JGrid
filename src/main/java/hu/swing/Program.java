package hu.swing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * Temporary class for developing phase
 * @author Csekme
 */
public class Program {

    private static final Logger logger = LoggerFactory.getLogger(Program.class);

    /**
     * Entry point
     * @param args accept only one argument for switch case
     */
    public static void main(String[] args) {

        if (args.length==1) {
            switch (args[0].toUpperCase()) {
                case "BALLONYI":
                    branchBallonyi();
                    break;
                case "CSEKME":
                    branchCsekme();
                    break;
                default:
                    logger.warn("use one of the following discrete arguments: ballonyi,csekme");
            }
        } else {
            logger.warn("use one of the following discrete arguments: ballonyi,csekme");
        }
    }

    /**
     * Branch of Ballonyi
     */
    public static void branchBallonyi() {
        logger.info("Switch to branch of Ballonyi");
    }

    /**
     * Branch of Csekme
     */
    public static void branchCsekme() {
        logger.info("Switch to branch of Csekme");
        try {
            Class.forName("org.sqlite.JDBC");
            try (Connection conn = DriverManager.getConnection("jdbc:sqlite::resource:db.db"); Statement stmt = conn.createStatement()) {

                ResultSet rs = stmt.executeQuery("SELECT * from telepulesek GROUP by ksh_kod order by nev");

                while (rs.next()) {
                    logger.info(rs.getString("nev"));
                }

            } catch (SQLException e) {
                logger.warn(e.getMessage());
            }
        } catch (ClassNotFoundException e) {
            logger.error("Missing JDBC sqlite driver");
        }
    }



}
