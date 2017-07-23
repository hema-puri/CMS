package com.tcs.cmslogin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hema on 21-Jul-17.
 */

public class EditEngineerDetails extends Fragment implements AdapterView.OnItemSelectedListener{

    View myView;
    EditText dept,emailid,name;
    Spinner eid;
    Button update;
    LoginDataBaseAdapter loginDataBaseAdapter;
    String item;
    int position;
    ArrayList<String> empid;
    String emp_name,emp_dept,emp_email;
    //String paths[]={"CMS01","CMS02"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ((AdminActivity) getActivity()).setActionBarTitle("Edit Engineer Details");

        myView=inflater.inflate(R.layout.editengineerdetails,container,false);
        dept=(EditText)myView.findViewById(R.id.dept);
        emailid=(EditText)myView.findViewById(R.id.email_id);
        name=(EditText)myView.findViewById(R.id.ename);
        update=(Button)myView.findViewById(R.id.update);
        loginDataBaseAdapter=new LoginDataBaseAdapter(getActivity());

       empid=loginDataBaseAdapter.getSelect_EmpId();

        eid = (Spinner)myView.findViewById(R.id.cid);

        eid.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item,empid);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eid.setAdapter(adapter);



        update.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View myview){
                emp_name=name.getText().toString();

                emp_email=emailid.getText().toString();
                emp_dept=dept.getText().toString();

                loginDataBaseAdapter.updateEntryE(item,emp_name,emp_email,emp_dept);
                Toast.makeText(getActivity(), "Record Updated Successfully", Toast.LENGTH_LONG).show();
            }
        });

        return myView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        item = parent.getItemAtPosition(position).toString();
        this.position=position;

        ArrayList<String> details=loginDataBaseAdapter.getDetails(item);
        if(details==null){}

        else
        {
                name.setText(details.get(0));
                dept.setText(details.get(1));
                emailid.setText(details.get(2));

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
