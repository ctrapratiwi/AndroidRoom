package com.example.androidroom.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidroom.R;

public class NewEmployeeActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY_EMPLOYEE_ID = "com.example.androidroom.EXTRA_ID";
    public static final String EXTRA_REPLY_EMPLOYEE_NAME = "com.example.androidroom.NAME";
    public static final String EXTRA_REPLY_EMPLOYEE_NUMBER = "com.example.androidroom.NIM";
    public static final String EXTRA_REPLY_EMPLOYEE_PHONE = "com.example.androidroom.PHONE";

    private EditText mEditEmployeeNameView, mEditEmployeeNumberView, mEditEmployeePhoneView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_employee);

        mEditEmployeeNameView = findViewById(R.id.edit_employee_name);
        mEditEmployeeNumberView = findViewById(R.id.edit_employee_number);
        mEditEmployeePhoneView = findViewById(R.id.edit_employee_phone);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_REPLY_EMPLOYEE_ID)) {
            setTitle("Edit Employee");
            mEditEmployeeNameView.setText(intent.getStringExtra(EXTRA_REPLY_EMPLOYEE_NAME));
            mEditEmployeeNumberView.setText(intent.getStringExtra(EXTRA_REPLY_EMPLOYEE_NUMBER));
            mEditEmployeePhoneView.setText(intent.getStringExtra(EXTRA_REPLY_EMPLOYEE_PHONE));

        } else {
            setTitle("Add Employee");
        }

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditEmployeeNameView.getText()) || TextUtils.isEmpty(mEditEmployeeNumberView.getText()) || TextUtils.isEmpty(mEditEmployeePhoneView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String employeeName = mEditEmployeeNameView.getText().toString();
                    String employeeNumber = mEditEmployeeNumberView.getText().toString();
                    String employeePhone = mEditEmployeePhoneView.getText().toString();

                    replyIntent.putExtra(EXTRA_REPLY_EMPLOYEE_NAME, employeeName);
                    replyIntent.putExtra(EXTRA_REPLY_EMPLOYEE_NUMBER, employeeNumber);
                    replyIntent.putExtra(EXTRA_REPLY_EMPLOYEE_PHONE, employeePhone);

                    int id = getIntent().getIntExtra(EXTRA_REPLY_EMPLOYEE_ID, -1);
                    if (id != -1) {
                        replyIntent.putExtra(EXTRA_REPLY_EMPLOYEE_ID, id);
                    }

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
