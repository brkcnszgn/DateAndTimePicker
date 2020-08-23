package com.brkcnszgn.dateandtimepicker;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.brkcnszgn.dateandtimepickerdialog.ClickListener;
import com.brkcnszgn.dateandtimepickerdialog.DateTimePickerView;

public class MainActivity extends AppCompatActivity {

     DateTimePickerView dateTimePickerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dateTimePickerView = findViewById(R.id.dt);
      //  dateTimePickerView.titleColor(R.color.color_black);
        dateTimePickerView.setOnClickListener(new ClickListener() {
            @Override
            public void onClick() {
            }
        });
    }
}
