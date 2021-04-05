package world.ucode.utlits;
import world.ucode.Pokemonchik;

import java.sql.*;
import java.util.ArrayList;


public class Database {
    public static Connection conn = null;
    public static Statement st = null;
    public static ResultSet rs = null;
    public static PreparedStatement pst = null;

    public static void connect() {
        String url = "jdbc:sqlite:pokemon.db";
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("Connection do not work.");
            e.printStackTrace();
        }
    }

    public static void createNewDB() {
        try {
            connect();
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("A new database has been created." +
                        "The driver name is " + meta.getDriverName());
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        createNewTable();
        updDisactive();
    }
    public static void updDisactive() {
        String sql = "UPDATE pokemon SET active=0 WHERE active=1;";
        try {
            st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateActive(int id) {
        String sql = "UPDATE pokemon SET active=1 WHERE id=? ;";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS pokemon (\n" +
                "id integer PRIMARY KEY," +
                " name_pokemon text NOT NULL ," +
                " img_pokemon int," +
                " food int,water int ,health int," +
                " weed int,fatigue int," +
                " active int" +
                ");";
        try {
            st = conn.createStatement();
            st.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void insertPokemonDB(String name_pokemon, int img_pokemon) {
        String sql = "INSERT INTO " +
                "pokemon(name_pokemon,img_pokemon,food,water,health,weed,fatigue,active) " +
                "VALUES(?,?,?,?,?,?,?,?)";
        try {
            pst = conn.prepareStatement(sql);

            pst.setString(1, name_pokemon);
            pst.setInt(2, img_pokemon);
            pst.setInt(3, 50);
            pst.setInt(4, 50);
            pst.setInt(5, 50);
            pst.setInt(6, 50);
            pst.setInt(7, 50);
            pst.setInt(8, 1);

            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public static void loadPokemon(Pokemonchik pokemon){
        String sql = "SELECT * FROM pokemon WHERE active=1";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);

            pokemon.id = rs.getInt("id");
            pokemon.setName_pokemon(rs.getString("name_pokemon"));
            pokemon.setImg_pokemon(rs.getInt("img_pokemon"));
            pokemon.setFood(rs.getInt("food"));
            pokemon.setWater(rs.getInt("water"));
            pokemon.setHealth(rs.getInt("health"));
            pokemon.setWeed(rs.getInt("weed"));
            pokemon.setFatigue(rs.getInt("fatigue"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void saveModel(Pokemonchik pokemonchik) {
        String sql = "UPDATE pokemon SET" +
                " food=?, water=?, health=?, weed=?," +
                " fatigue=?,active=? WHERE id=?;";
        try {
            pst = conn.prepareStatement(sql);

            pst.setInt(1, pokemonchik.getFood());
            pst.setInt(2, pokemonchik.getWater());
            pst.setInt(3, pokemonchik.getHealth());
            pst.setInt(4, pokemonchik.getWeed());
            pst.setInt(5, pokemonchik.getFatigue());
            pst.setInt(6, 0);
            pst.setInt(7, pokemonchik.id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void deletePokemon(int id) {
        String sql = "DELETE FROM pokemon WHERE id=?;";
        try {
            pst = conn.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static ArrayList<String> selectNames() {
        ArrayList<String> names = new ArrayList<>();
        String sql = "SELECT id,name_pokemon FROM pokemon;";
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                names.add(rs.getInt("id") + ". " + rs.getString("name_pokemon"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return names;
    }

    public static void CloseDB() throws SQLException {
        if (rs != null) {
            rs.close();
            rs = null;
        }
        if (st != null) {
            st.close();
            st = null;
        }
        if (pst != null) {
            pst.close();
            pst = null;
        }
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }
}

