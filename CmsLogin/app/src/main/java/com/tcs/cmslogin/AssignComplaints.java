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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by hema on 21-Jul-17.
 */

public class AssignComplaints extends Fragment implements AdapterView.OnItemSelectedListener{

    View myView;
    TextView complaint,dept,name;
    Spinner eid,cid;
    Button assign_complaint;
    LoginDataBaseAdapter loginDataBaseAdapter;
    String comp_id,emp_id;
    int position;
    ArrayList<String> empid;
    ArrayList<String> compid;
    String emp_name,emp_dept,emp_email,complaint_;
    //String paths[]={"CMS01","CMS02"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ((AdminActivity) getActivity()).setActionBarTitle("Assign Complaint");

        myView=inflater.inflate(R.layout.assigncomplaints,container,false);
        dept=(TextView)myView.findViewById(R.id.dept);
        complaint=(TextView)myView.findViewById(R.id.complaint);
        name=(TextView) myView.findViewById(R.id.name);
        assign_complaint=(Button)myView.findViewById(R.id.assign);
        loginDataBaseAdapter=new LoginDataBaseAdapter(getActivity());

        empid=loginDataBaseAdapter.getSelect_EmpId();
        compid=loginDataBaseAdapter.getSelect_CompId();

        /*********************EMPLOYEE ID **********************/
        eid = (Spinner)myView.findViewById(R.id.eid);

        eid.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item,empid);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eid.setAdapter(adapter);

        /*********************** COMPLAINT ID *******************/
        cid = (Spinner)myView.findViewById(R.id.cid);

        cid.setOnItemSelectedListener(this);

        ArrayAdapter<String> adapterc = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_item,compid);

        adapterc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cid.setAdapter(adapterc);



        assign_complaint.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View myview){

                loginDataBaseAdapter.assignComplaint(emp_id,comp_id);
                Toast.makeText(getActivity(), "Complaint Assigned", Toast.LENGTH_LONG).show();
            }
        });

        return myView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner) parent;
        if(spinner.getId() == R.id.cid)
        {
            comp_id = parent.getItemAtPosition(position).toString();
            this.position=position;

             String complaints=loginDataBaseAdapter.getSelect_Complaint(comp_id);

            if(complaints==null){}

            else
            {
                complaint.setText(complaints);
                // emailid.setText(details.get(2));

            }
        }
        else if(spinner.getId() == R.id.eid)
        {
            emp_id = parent.getItemAtPosition(position).toString();
            this.position=position;

            ArrayList<String> details=loginDataBaseAdapter.getDetails(emp_id);


            if(details==null){}

            else
            {
                name.setText(details.get(0));
                dept.setText(details.get(1));
                // emailid.setText(details.get(2));

            }
        }



    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
