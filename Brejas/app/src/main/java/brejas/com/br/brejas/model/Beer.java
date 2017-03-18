package brejas.com.br.brejas.model;

import android.text.Editable;

/**
 * Created by rnas on 15/03/17.
 */

public class Beer {

    private int id; // ?
    private String name;
    private String brand;
    private String type;
    private int content;
    private int units;

    public Beer(String name, String brand, String type, int content, int units) {
        this.name = name;
        this.brand = brand;
        this.type = type;
        this.content = content;
        this.units = units;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getContent() {
        return content;
    }

    public void setContent(int content) {
        this.content = content;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }
}
