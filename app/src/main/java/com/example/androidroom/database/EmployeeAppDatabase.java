package com.example.androidroom.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.androidroom.dao.EmployeeDAO;
import com.example.androidroom.entity.Employee;

@Database(entities = {Employee.class}, version = 1)
public abstract  class EmployeeAppDatabase extends RoomDatabase {

    public abstract EmployeeDAO employeeDAO();

    private static volatile EmployeeAppDatabase INSTANCE;

    static EmployeeAppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EmployeeAppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EmployeeAppDatabase.class, "employee_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
