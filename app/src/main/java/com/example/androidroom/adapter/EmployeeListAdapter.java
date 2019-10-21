package com.example.androidroom.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidroom.R;
import com.example.androidroom.entity.Employee;
import com.example.androidroom.viewmodel.EmployeeViewModel;

import java.util.List;

public class EmployeeListAdapter extends RecyclerView.Adapter<EmployeeListAdapter.EmployeeViewHolder> {
private EmployeeViewModel employeeViewModel;

    class EmployeeViewHolder extends RecyclerView.ViewHolder {
        private final TextView employeeNameView, employeeNumberView, employeePhoneView;
        private final Button editButton, deleteButton;
        private EmployeeViewHolder(final View itemView) {
            super(itemView);
            employeeNameView = itemView.findViewById(R.id.textView_employee_name);
            employeeNumberView = itemView.findViewById(R.id.textView_employee_number);
            employeePhoneView = itemView.findViewById(R.id.textView_employee_phone);

            editButton = itemView.findViewById(R.id.button_edit);
            deleteButton = itemView.findViewById(R.id.button_delete);

        }
    }

    private final LayoutInflater mInflater;
    private List<Employee> mEmployees; // Cached copy of words

    public EmployeeListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public EmployeeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new EmployeeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final EmployeeViewHolder holder, int position) {
        if (mEmployees != null) {
            Employee current = mEmployees.get(position);
            holder.employeeNumberView.setText("NIM      : "+current.getEmployeeNumber());
            holder.employeeNameView.setText("Name   : "+current.getEmployeeName());
            holder.employeePhoneView.setText("Phone  : "+current.getEmployeePhone());


        } else {
            // Covers the case of data not being ready yet.
            holder.employeeNameView.setText("No Employee");
        }

    }

    public void setmEmployees(List<Employee> employees){
        mEmployees = employees;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mEmployees != null)
            return mEmployees.size();
        else return 0;
    }

    public Employee getEmployeeAt(int position) {
        return mEmployees.get(position);
    }
}
