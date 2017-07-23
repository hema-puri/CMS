package com.tcs.cmslogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Edit extends AppCompatActivity {

    TextView id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        id=(TextView)findViewById(R.id.empid);

        String s=getIntent().getStringExtra("EmpId");
        id.setText(s);
    }
}
