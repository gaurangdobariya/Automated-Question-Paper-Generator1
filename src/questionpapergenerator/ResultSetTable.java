package questionpapergenerator;



/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ASUS
 */
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * A JTable used to display a SQL ResultSet.
 * @author fahdshariff
 *
 */
public class ResultSetTable extends JTable{

  private final DefaultTableModel dataModel;

  public ResultSetTable(ResultSet rs)
                       throws SQLException{

    super();
    dataModel = new DefaultTableModel();
    setModel(dataModel);

    try {
      //create an array of column names
      ResultSetMetaData mdata = rs.getMetaData();
      int colCount = mdata.getColumnCount();
      String[] colNames = new String[colCount];
      for (int i = 1; i <= colCount; i++) {
        colNames[i - 1] = mdata.getColumnName(i);
      }
      dataModel.setColumnIdentifiers(colNames);

      //now populate the data
      while (rs.next()) {
        String[] rowData = new String[colCount];
        for (int i = 1; i <= colCount; i++) {
          rowData[i - 1] = rs.getString(i);
        }
        dataModel.addRow(rowData);
      }
    }
    finally{
      try {
        rs.close();
      }
      catch (SQLException ignore) {
      }
    }
  }
}
