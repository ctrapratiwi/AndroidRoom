package com.example.androidroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidroom.R;
import com.example.androidroom.adapter.RecyclerItemClickListener;
import com.example.androidroom.adapter.EmployeeListAdapter;
import com.example.androidroom.entity.Employee;
import com.example.androidroom.utils.ApplicationStrings;
import com.example.androidroom.viewmodel.EmployeeViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_WORD_ACTIVITY_REQUEST_CODE = 2;

    private EmployeeViewModel mEmployeeViewModel;
    private Button editButton, deleteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final EmployeeListAdapter adapter = new EmployeeListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        // Get a new or existing ViewModel from the ViewModelProvider.
        mEmployeeViewModel = ViewModelProviders.of(this).get(EmployeeViewModel.class);

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        mEmployeeViewModel.getmAllEmployee().observe(this, new Observer<List<Employee>>() {
            @Override
            public void onChanged(@Nullable final List<Employee> employees) {
                // Update the cached copy of the words in the adapter.
                adapter.setmEmployees(employees);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewEmployeeActivity.class);
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });


        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View v, final int position) {
                        editButton = v.findViewById(R.id.button_edit);
                        //Toast.makeText(getApplicationContext(), "" + position, Toast.LENGTH_SHORT).show();
                        deleteButton = v.findViewById(R.id.button_delete);

                        deleteButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Delete button is called"+adapter.getEmployeeAt(position).getEmployeeName() , Toast.LENGTH_SHORT).show();

                                mEmployeeViewModel.delete(adapter.getEmployeeAt(position));
                            }
                        });

                        editButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getApplicationContext(), "Edit button is called"+adapter.getEmployeeAt(position).getEmployeeName() , Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, NewEmployeeActivity.class);
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_EMPLOYEE_ID, adapter.getEmployeeAt(position).getEmployeeId());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_EMPLOYEE_NAME, adapter.getEmployeeAt(position).getEmployeeName());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_EMPLOYEE_NUMBER, adapter.getEmployeeAt(position).getEmployeeNumber());
                                intent.putExtra(ApplicationStrings.EXTRA_REPLY_EMPLOYEE_PHONE,  Integer.toString(adapter.getEmployeeAt(position).getEmployeePhone()));
                                startActivityForResult(intent, EDIT_WORD_ACTIVITY_REQUEST_CODE);
                            }
                        });
                    }
                })
        );


    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            String employeeName = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_EMPLOYEE_NAME);
            String employeeNumber = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_EMPLOYEE_NUMBER);
            String employeePhone = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_EMPLOYEE_PHONE);
            int phone = Integer.parseInt(employeePhone);

            Employee employee = new Employee(employeeName,employeeNumber,phone);
            mEmployeeViewModel.insert(employee);
        } else if (requestCode == EDIT_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            int id = data.getIntExtra(ApplicationStrings.EXTRA_REPLY_EMPLOYEE_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Note can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String employeeName = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_EMPLOYEE_NAME);
            String employeeNumber = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_EMPLOYEE_NUMBER);
            String employeePhone = data.getStringExtra(ApplicationStrings.EXTRA_REPLY_EMPLOYEE_PHONE);
            int phone = Integer.parseInt(employeePhone);

            Employee employee = new Employee(employeeName,employeeNumber, phone);
            employee.setEmployeeId(id);
            mEmployeeViewModel.update(employee);

            Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }



}
