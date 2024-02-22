package com.example.devproject.DbUtils;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "city_and_country")
public class CityandCountry {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "city_name")
    private String cityName;

    @ColumnInfo(name = "country_name")
    private String countryName;

    public CityandCountry(String cityName, String countryName) {
        this.cityName = cityName;
        this.countryName = countryName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
