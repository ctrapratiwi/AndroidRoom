package com.example.androidroom.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "employees")
public class Employee {

    @PrimaryKey(autoGenerate = true)
    private int employeeId;

    @NonNull
    @ColumnInfo(name = "employee_name")
    private String employeeName;

    @NonNull
    @ColumnInfo(name = "employee_number")
    private String employeeNumber;

    @NonNull
    @ColumnInfo(name = "employee_phone")
    private int employeePhone;


    public Employee(@NonNull String employeeName, @NonNull String employeeNumber, int employeePhone) {
        this.employeeName = employeeName;
        this.employeeNumber = employeeNumber;
        this.employeePhone = employeePhone;

    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @NonNull
    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(@NonNull String employeeName) {
        this.employeeName = employeeName;
    }

    @NonNull
    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(@NonNull String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public int getEmployeePhone() {
        return employeePhone;
    }

    public void setEmployeePhone(int employeePhone) {
        this.employeePhone = employeePhone;
    }

}
