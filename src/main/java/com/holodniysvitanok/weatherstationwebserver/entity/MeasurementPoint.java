package com.holodniysvitanok.weatherstationwebserver.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


// точка измерения
@Entity
@Table(name = "measurement_point")
public class MeasurementPoint implements Serializable {

    private static final long serialVersionUID = -14543544554654356L;

    @Id
    @Column(name = "id_measurement_point")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // id точки измерения

    @Column(name = "value_measurement_point")
    private int value; // значение точки

    @Column(name = "date_measurement_point")
    private Date datePoint; // время точки,

    @Enumerated(EnumType.STRING)
    private TypeMeasurement typeMeasurement;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    @JoinColumn(name = "id_measuring_sensor")
    private MeasuringSensor measuringSensor; // датчик который сделал это измерение

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Date getDatePoint() {
        return datePoint;
    }

    public void setDatePoint(Date datePoint) {
        this.datePoint = datePoint;
    }

    public TypeMeasurement getTypeMeasurement() {
        return typeMeasurement;
    }

    public void setTypeMeasurement(TypeMeasurement typeMeasurement) {
        this.typeMeasurement = typeMeasurement;
    }

    public MeasuringSensor getMeasuringSensor() {
        return measuringSensor;
    }

    public void setMeasuringSensor(MeasuringSensor measuringSensor) {
        this.measuringSensor = measuringSensor;
    }

    public MeasuringSensor getSensor() {
        return measuringSensor;
    }

    public void setSensor(MeasuringSensor measuringSensor) {
        this.measuringSensor = measuringSensor;
    }

    public static enum TypeMeasurement {
        Temperature("Temperature"), // температура
        Pressure("Pressure"), // давление
        Humidity("Humidity"); // влажность

        private String text;

        private TypeMeasurement(String text) {
            this.text = text;
        }

        public String getText() {
            return this.text;
        }

        public static TypeMeasurement fromString(String text) {
            if (text != null) {
                for (TypeMeasurement type : TypeMeasurement.values()) {
                    if (text.equalsIgnoreCase(type.text)) {
                        return type;
                    }
                }
            }
            return null;
        }
    }

	public MeasurementPoint(long id) {
		this.id = id;
	}

	public MeasurementPoint() {
	}

	@Override
	public String toString() {
		return "MeasurementPoint [id=" + id + ", value=" + value + ", datePoint=" + datePoint + ", typeMeasurement=" + typeMeasurement + "]";
	}
   
    
    
}
