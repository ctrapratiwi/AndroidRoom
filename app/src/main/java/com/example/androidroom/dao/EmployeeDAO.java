package com.example.androidroom.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.androidroom.entity.Employee;

import java.util.List;

@Dao
public interface EmployeeDAO {
    @Insert
    void insert(Employee employee);

    @Update
    void update(Employee employee);

    @Delete
    void delete(Employee employee);

    @Query("DELETE FROM employees")
    void deleteAll();

    @Query("SELECT * from employees ORDER BY employee_name ASC")
    LiveData<List<Employee>> getAllEmployee();
}
