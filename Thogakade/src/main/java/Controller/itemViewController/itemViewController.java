package Controller.itemViewController;

import Database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modle.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class itemViewController implements itemViewService{
    ObservableList<Item> items= FXCollections.observableArrayList();
    @Override
    public void addItems(Item item) {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement( "INSERT INTO item (ItemCode, Description, PackSize, UnitPrice, QtyOnHand) VALUES (?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, item.getItemCode());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setString(3, item.getPackSize());
            preparedStatement.setDouble(4, item.getUnitPrice());
            preparedStatement.setInt(5, item.getQtyOnHand());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void upadateItems(Item item) {
        Connection connection= null;
        try {
            connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement("UPDATE item SET UnitPrice = ?, QtyOnHand = ? WHERE ItemCode = ?");
            preparedStatement.setDouble(1,item.getUnitPrice());
            preparedStatement.setInt(2,item.getQtyOnHand());
            preparedStatement.setString(3,item.getItemCode());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteItems(String ItemCode) {
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("DELETE FROM item WHERE ItemCode = ?");
            preparedStatement.setString(1, ItemCode);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public ObservableList<Item> getAllItemDetails() {
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT * FROM item");
            ResultSet resultSet=preparedStatement.executeQuery();

            while (resultSet.next()){
                items.add(new Item(
                    resultSet.getString("ItemCode"),
                    resultSet.getString("Description"),
                    resultSet.getString("PackSize"),
                    resultSet.getDouble("UnitPrice"),
                    resultSet.getInt("QtyOnHand")
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }
}
