package com.tcs.cmslogin;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AddEngineerDetails extends Fragment {

    View myView;
    Button add;
    TextView name,dept,eid,emailid;
    LoginDataBaseAdapter loginDataBaseAdapter;
    String emp_name,emp_id,emp_email,emp_dept;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ((AdminActivity) getActivity()).setActionBarTitle("Add New Engineer");

        myView=inflater.inflate(R.layout.addengineerdetails,container,false);
        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(getActivity());
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        add=(Button)myView.findViewById(R.id.add);
        name=(TextView)myView.findViewById(R.id.eng_name);
        dept=(TextView)myView.findViewById(R.id.dept);
        eid=(TextView)myView.findViewById(R.id.eid);
        emailid=(TextView)myView.findViewById(R.id.e_id);



        add.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View myview){
                emp_name=name.getText().toString();
                emp_id=eid.getText().toString();
                emp_email=emailid.getText().toString();
                emp_dept=dept.getText().toString();

                loginDataBaseAdapter.insertEntryE(emp_id,emp_name,emp_email,emp_id,emp_dept);
                Toast.makeText(getActivity(), "Entry Inserted Successfully", Toast.LENGTH_LONG).show();
            }
        });


        return myView;
    }
}
