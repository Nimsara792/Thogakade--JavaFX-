package Controller.itemViewController;

import javafx.beans.Observable;
import javafx.collections.ObservableList;
import modle.Item;

public interface itemViewService {
    void addItems(Item item);
    void upadateItems(Item item);
    void deleteItems(String id);
    ObservableList<Item> getAllItemDetails();
}
