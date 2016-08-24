package com.holodniysvitanok.weatherstationwebserver.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

// описание датчика который делает замеры 
@Entity
@Table(name = "measuring_sensor")
public class MeasuringSensor implements Serializable {

    private static final long serialVersionUID = -754524575452743525L;
    
    @Id
    @Column(name = "id_measuring_sensor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // id датчика

    @Column(name = "location_measuring_sensor")
    private String location;

    @Column(name = "password_measuring_sensor")
    private String password; // пароль датчика
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "measuringSensor") // одно поле это таблицы будет указывать на много полей в другой таблиц
    private Set<MeasurementPoint> measurementPoint;

    @Column(name="active_sensor")
    private Boolean active;
    


    public MeasuringSensor(int id, String location, String password, Boolean active) {
		this.id = id;
		this.location = location;
		this.password = password;
		this.active = active;
	}
    
    public MeasuringSensor( String location, String password, Boolean active) {
    	this.location = location;
    	this.password = password;
    	this.active = active;
    }

	public MeasuringSensor() {
    }
    
    public MeasuringSensor(int id) {
		this.id = id;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<MeasurementPoint> getMeasurementPoint() {
        return measurementPoint;
    }

    public void setMeasurementPoint(Set<MeasurementPoint> measurementPoint) {
        this.measurementPoint = measurementPoint;
    }

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

    
    
}
