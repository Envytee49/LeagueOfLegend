package leagueoflegend.executiondata;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import leagueoflegend.model.GeneralInfo;

import java.util.ArrayList;
import java.util.List;

public class Data <T extends GeneralInfo>{
//    private ObservableList<T> data = FXCollections.observableArrayList();
    private ArrayList<T> data = new ArrayList<>();

//    public void setData(ArrayList<T> d) {
//        this.data = FXCollections.observableArrayList(d);
//    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    public T getById(Integer id){
        for(T entity : data){
            if(entity.getId() == id) return entity;
        }
        return null;
    }
    public T findName(String name){
        for (T entity : data){
            if (entity.getName().equals(name)) return entity;
        }
        return null;
    }

//    public ObservableList<T> getData() {
//        return this.data;
//    }

    public List<T> getData() {
        return  this.data;
    }

    public List<T> searchByName(String name) {
        return data.stream().filter(entity -> entity.containsNameForSearch(name)).toList();
//        return new FilteredList<>(data, entity -> entity.containsNameForSearch(name));
    }

    public List<T> searchByID(int id) {
        return data.stream().filter(entity -> entity.containsID(id)).toList();
//        return new FilteredList<>(data, entity -> entity.containsID(id));
    }

}
