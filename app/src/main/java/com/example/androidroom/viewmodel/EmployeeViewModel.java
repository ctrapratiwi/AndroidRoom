package com.example.androidroom.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.androidroom.database.EmployeeRepository;
import com.example.androidroom.entity.Employee;

import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {

    private EmployeeRepository mRepository;

    private LiveData<List<Employee>> mAllEmployee;

    public EmployeeViewModel(Application application) {
        super(application);
        mRepository = new EmployeeRepository(application);
        mAllEmployee = mRepository.getmAllEmployee();
    }

    public LiveData<List<Employee>> getmAllEmployee() { return mAllEmployee; }

    public void insert(Employee employee) { mRepository.insert(employee); }
    public void update(Employee employee) { mRepository.update(employee); }
    public void delete(Employee employee) { mRepository.delete(employee); }
}