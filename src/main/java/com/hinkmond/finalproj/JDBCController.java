package com.hinkmond.finalproj;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.web.bind.annotation.*;


@RestController
public class JDBCController {
    private final static String KEYFILEPATH = "./keyFile.key";

    @CrossOrigin
    @RequestMapping(value = "/helloworld", method = RequestMethod.GET)
    public String printCryptTest() {
        AESUtils aesUtils = new AESUtils();

        String encryptedStr = aesUtils.encrypt("Hello World!", KEYFILEPATH);
        return ("Decrypt = " + aesUtils.decrypt(encryptedStr, KEYFILEPATH));
    }

    @CrossOrigin
    @RequestMapping(value = "/printAllUsers", method = RequestMethod.GET)
    public String printAllUsers() {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        StringBuilder resultStr = new StringBuilder();

        String queryStr = "SELECT * from user_info;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("user_id")).append(", ")
                    .append(sqlRowSet.getString("first_name")).append(", ")
                    .append(sqlRowSet.getString("last_name")).append(", ")
                    .append(sqlRowSet.getString("addr")).append(", ")
                    .append(sqlRowSet.getString("phone")).append(", ")
                    .append(sqlRowSet.getString("email")).append(", ")
                    .append(sqlRowSet.getString("created_at"))
                    .append("\n");
        }
        return ("SELECT * from user_info:\n" + resultStr);
    }

    @CrossOrigin
    @RequestMapping(value = "/printRichHeirs", method = RequestMethod.GET)
    public String printRichHeirs() {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        StringBuilder resultStr = new StringBuilder();

        String queryStr = "SELECT * from heir_info WHERE asset > 2000000;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("heir_id")).append(", ")
                    .append(sqlRowSet.getString("first_name")).append(", ")
                    .append(sqlRowSet.getString("last_name")).append(", ")
                    .append(sqlRowSet.getString("age")).append(", ")
                    .append(sqlRowSet.getString("asset")).append(", ")
                    .append(sqlRowSet.getString("income")).append(", ")
                    .append(sqlRowSet.getString("partner_name")).append(", ")
                    .append(sqlRowSet.getString("hobby")).append(", ")
                    .append(sqlRowSet.getString("gambling"))
                    .append("\n");
        }
        return ("SELECT * from heir_info:\n" + resultStr);
    }

    @CrossOrigin
    @RequestMapping(value = "/printGullibleHeirs", method = RequestMethod.GET)
    public String printGullibleHeirs() {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        StringBuilder resultStr = new StringBuilder();

        String queryStr = "SELECT * from heir_info WHERE asset > 2000000 AND gambling ='Yes';";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("heir_id")).append(", ")
                    .append(sqlRowSet.getString("first_name")).append(", ")
                    .append(sqlRowSet.getString("last_name")).append(", ")
                    .append(sqlRowSet.getString("age")).append(", ")
                    .append(sqlRowSet.getString("asset")).append(", ")
                    .append(sqlRowSet.getString("income")).append(", ")
                    .append(sqlRowSet.getString("partner_name")).append(", ")
                    .append(sqlRowSet.getString("hobby"))
                    .append("\n");
        }
        return ("SELECT * from heir_info:\n" + resultStr);
    }


    @CrossOrigin
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@RequestBody AddUserData addUserData) {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        String queryStr = "INSERT INTO user_info (first_name, last_name, addr, email) " +
                "VALUES (" +
                "'" + addUserData.getFirstName() + "'," +
                "'" + addUserData.getLastName() + "'," +
                "'" + addUserData.getAddress() + "'," +
                "'" + addUserData.getEmail() + "'" +
                ");";
        int rowsUpdated = jdbcTemplate.update(queryStr);
        return ("Rows updated: " + rowsUpdated);
    }

    @CrossOrigin
    @RequestMapping(value = "/addHeir", method = RequestMethod.POST)
    public String addHeir(@RequestBody AddHeirData addHeirData) {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        String queryStr = "INSERT INTO heir_info (first_name, last_name, parent_id, age, asset, income, hobby,gambling) " +
                "VALUES (" +
                "'" + addHeirData.getFirstName() + "'," +
                "'" + addHeirData.getLastName() + "'," +
                "'" + addHeirData.getParent_id() + "'," +
                "'" + addHeirData.getAge() + "'," +
                "'" + addHeirData.getAsset() + "'," +
                "'" + addHeirData.getIncome() + "'," +
                "'" + addHeirData.getHobby() + "'," +
                "'" + addHeirData.getGambling() + "'" +
                ");";
        int rowsUpdated = jdbcTemplate.update(queryStr);
        return ("Rows updated: " + rowsUpdated);
    }


    @CrossOrigin
    @RequestMapping(value = "/printAllHeirs", method = RequestMethod.GET)
    public String printAllHeirs() {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        StringBuilder resultStr = new StringBuilder();

        String queryStr = "SELECT * from heir_info ;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("heir_id")).append(", ")
                    .append(sqlRowSet.getString("first_name")).append(", ")
                    .append(sqlRowSet.getString("last_name")).append(", ")
                    .append(sqlRowSet.getString("age")).append(", ")
                    .append(sqlRowSet.getString("asset")).append(", ")
                    .append(sqlRowSet.getString("income")).append(", ")
                    .append(sqlRowSet.getString("partner_name")).append(", ")
                    .append(sqlRowSet.getString("hobby")).append(", ")
                    .append(sqlRowSet.getString("gambling"))
                    .append("\n");
        }
        return ("SELECT * from heir_info:\n" + resultStr);
    }


    @CrossOrigin
    @RequestMapping(value = "/printAllHeirs2", method = RequestMethod.GET)
    public String printAllHeirs2() {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        StringBuilder resultStr = new StringBuilder();

        String queryStr = "SELECT heir_info.first_name as first_name, heir_info.last_name as last_name, user_info.first_name as father_name, asset from heir_info, user_info WHERE heir_info.parent_id = user_info.user_id;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("heir_id")).append(", ")
                    .append(sqlRowSet.getString("first_name")).append(", ")
                    .append(sqlRowSet.getString("last_name")).append(", ")
                    .append(sqlRowSet.getString("father_name"))
    //                .append(sqlRowSet.getString("father_name")).append(", ")
    //                .append(sqlRowSet.getString("age")).append(", ")
    //                .append(sqlRowSet.getString("asset")).append(", ")
    //                .append(sqlRowSet.getString("income")).append(", ")
    //                .append(sqlRowSet.getString("partner_name")).append(", ")
    //                .append(sqlRowSet.getString("hobby")).append(", ")
    //                .append(sqlRowSet.getString("gambling"))
                    .append("\n");
        }
        return ("SELECT * from heir_info:\n" + resultStr);
    }



    @CrossOrigin
    @RequestMapping(value = "/printAllAccts", method = RequestMethod.GET)
    public String printAllAccts() {
        JdbcTemplate jdbcTemplate = JDBCConnector.getJdbcTemplate();
        StringBuilder resultStr = new StringBuilder();

        String queryStr = "SELECT * from acct_info;";
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet(queryStr);
        while (sqlRowSet.next()) {
            resultStr.append(sqlRowSet.getString("user_id")).append(", ")
                    .append(sqlRowSet.getString("acct_num")).append(", ")
                    .append(sqlRowSet.getString("balance"))
                    .append("\n");
        }
        return ("SELECT * from acct_info:\n" + resultStr);
    }
}
