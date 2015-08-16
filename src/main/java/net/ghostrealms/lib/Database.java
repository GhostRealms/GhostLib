package net.ghostrealms.lib;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Database {
  
  public enum SQL {
    H2,
    MYSQL,
    SQLITE;
  }

  public static int test;
  
  private final JavaPlugin plugin;
  private final String db;
  private SQL mode;
  
  private Connection connection;
  
  private String mysql_user;
  private String mysql_pass;
  private String mysql_host;
  private String mysql_database;
  private int    mysql_port;
  
  private ArrayList<String> updateQueue = new ArrayList<String>();
  
  public Database(String db, JavaPlugin plugin, SQL mode) {
    this.db = db;
    this.plugin = plugin;
    this.mode = mode;

    //make sure config exists
    File configFile = new File(plugin.getDataFolder() + File.separator + "config.yml");
    if(!configFile.exists())
    {
      try {
        configFile.createNewFile();
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }

    if(mode == SQL.MYSQL) {
      FileConfiguration config = plugin.getConfig();

        if(!config.contains("mysql.database"))
            config.set("mysql.database", "dbname");
        if(!config.contains("mysql.host"))
            config.set("mysql.host", "localhost");
        if(!config.contains("mysql.user"))
            config.set("mysql.user", "root");
        if(!config.contains("mysql.pass"))
            config.set("mysql.pass", "password");
        if(!config.contains("mysql.port"))
              config.set("mysql.port", 3306);

      try
      {
        config.save(configFile);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }

      mysql_database = config.getString("mysql.database");
      mysql_host     = config.getString("mysql.host");
      mysql_user     = config.getString("mysql.user");
      mysql_pass     = config.getString("mysql.pass");
      mysql_port     = config.getInt("mysql.port");
    }
    
    setupConnection();
  }
  
  private void setupConnection() {
    
    switch(mode) {
      default:
      case H2:
        try {
          Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
          ex.printStackTrace();
          connection = null;
          break;
        }

        String url = "jdbc:h2:" + plugin.getDataFolder() + File.separator + db;

        try {
          connection = DriverManager.getConnection(url);
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        break;
      case MYSQL:
        try {
          Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
          ex.printStackTrace();
          connection = null;
          break;
        }
        
        try {
          connection = DriverManager.getConnection("jdbc:mysql://" + mysql_host + ":" + mysql_port + 
                                                   "/" + mysql_database,  mysql_user, mysql_pass);
        } catch (SQLException ex) {
          ex.printStackTrace();
          connection = null;
          break;
        }
        break;
      case SQLITE:
        try {
          Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ex) {
          ex.printStackTrace();
          connection = null;
          break;
        }
        
        try {
          connection = DriverManager.getConnection("jdbc:sqlite:" + plugin.getDataFolder() +
                                                   File.separator + db);
        } catch (SQLException ex) {
          ex.printStackTrace();
          connection = null;
          break;
        }
        break;
    }
  }
  
  protected String getDatabase() {
    return db;
  }
  
  private String getDatabaseURL() {
    return "jdbc:h2:" + plugin.getDataFolder() + File.separator + db;
  }
  
  public void close() {
    try {
      if(!connection.isClosed()) {
        connection.close();
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }

  /**
   * This will add an Update SQL statement to the queue to be updated upon calling #runQueue* 
   * @param statement
   */
  public void queue(String statement) {
    updateQueue.add(statement);
  }
  
  public void runQueue() {
    for(String sql : updateQueue) {
      update(sql);
      updateQueue.remove(sql);
    }
  }
  
  public boolean write(String sql, Object...args) {
    try {
      PreparedStatement stmt = connection.prepareStatement(sql);

      for(int i = 0; i < args.length; i++)
      {
        //+1 because the first parameter is 1, not 0
        stmt.setObject(i + 1, args[i]);
      }

      boolean status = stmt.execute();
      return status;
    } catch (SQLException ex) {
      ex.printStackTrace();
      return false;
    }
  }
  
  public int update(String sql, Object...args) {
    try {
      PreparedStatement stmt = connection.prepareStatement(sql);

      for(int i = 0; i < args.length; i++)
      {
        //+1 because the first parameter is 1, not 0
        stmt.setObject(i + 1, args[i]);
      }

      int status = stmt.executeUpdate();
      return status;
    } catch (SQLException ex) {
      ex.printStackTrace();
      return -1;
    }
  }
  
  public ResultSet read(String sql, Object...args) {
    try {
      PreparedStatement stmt = connection.prepareStatement(sql);

      for(int i = 0; i < args.length; i++)
      {
        //+1 because the first parameter is 1, not 0
        stmt.setObject(i + 1, args[i]);
      }

      ResultSet results = stmt.executeQuery();
      return results;
    } catch (SQLException ex) {
      ex.printStackTrace();
      return null;
    }
  }
  
  protected void setMode(SQL mode) {
    this.mode = mode;
  }
  
  protected SQL getMode() {
    return mode;
  }

}