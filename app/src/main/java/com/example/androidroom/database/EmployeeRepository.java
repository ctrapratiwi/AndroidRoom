package com.example.androidroom.database;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.androidroom.dao.EmployeeDAO;
import com.example.androidroom.entity.Employee;

import java.util.List;

public class EmployeeRepository {
    private EmployeeDAO employeeDAO;
    private LiveData<List<Employee>> mAllEmployee;

   public EmployeeRepository(Application application) {
        EmployeeAppDatabase db = EmployeeAppDatabase.getDatabase(application);
        employeeDAO = db.employeeDAO();
       mAllEmployee = employeeDAO.getAllEmployee();
    }

    public LiveData<List<Employee>> getmAllEmployee() {
        return mAllEmployee;
    }


    public void insert (Employee employee) {
        new insertAsyncTask(employeeDAO).execute(employee);
    }

    public void delete(Employee employee) {
        new DeleteEmployeeAsyncTask(employeeDAO).execute(employee);
    }

    public void update(Employee employee) {
        new UpdateEmployeeAsyncTask(employeeDAO).execute(employee);
    }

    private static class insertAsyncTask extends AsyncTask<Employee, Void, Void> {

        private EmployeeDAO mAsyncTaskDao;

        insertAsyncTask(EmployeeDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Employee... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class UpdateEmployeeAsyncTask extends AsyncTask<Employee, Void, Void> {
        private EmployeeDAO employeeDAO;

        private UpdateEmployeeAsyncTask(EmployeeDAO employeeDAO) {
            this.employeeDAO = employeeDAO;
        }

        @Override
        protected Void doInBackground(Employee... employees) {
            employeeDAO.update(employees[0]);
            return null;
        }
    }

    private static class DeleteEmployeeAsyncTask extends AsyncTask<Employee, Void, Void> {
        private EmployeeDAO employeeDAO;

        private DeleteEmployeeAsyncTask(EmployeeDAO employeeDAO) {
            this.employeeDAO = employeeDAO;
        }

        @Override
        protected Void doInBackground(Employee... employees) {
            employeeDAO.delete(employees[0]);
            return null;
        }
    }
}
