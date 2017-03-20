package brejas.com.br.brejas.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.Editable;

/**
 * Created by rnas on 15/03/17.
 */

public class Beer implements Parcelable {

    // Database fields
    public final static String ID       = "_id";
    public final static String NAME     = "name";
    public final static String BRAND    = "brand";
//    public final static String TYPE     = "type";
    public final static String CONTENT  = "content";
    public final static String UNITS    = "units";

    private int id; // ?
    private String name;
    private String brand;
//    private String type;
    private int content;
    private int units;

    public Beer() {};

    public Beer(String name, String brand, int content, int units) {
        this.name = name;
        this.brand = brand;
//        this.type = type;
        this.content = content;
        this.units = units;
    }

    protected Beer(Parcel in) {
        id = in.readInt();
        name = in.readString();
        brand = in.readString();
//        type = in.readString();
        content = in.readInt();
        units = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(brand);
//        dest.writeString(type);
        dest.writeInt(content);
        dest.writeInt(units);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Beer> CREATOR = new Creator<Beer>() {
        @Override
        public Beer createFromParcel(Parcel in) {
            return new Beer(in);
        }

        @Override
        public Beer[] newArray(int size) {
            return new Beer[size];
        }
    };

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

//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

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
