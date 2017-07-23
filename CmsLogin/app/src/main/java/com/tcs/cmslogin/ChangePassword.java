package com.tcs.cmslogin;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hema on 28-Jun-17.
 */

public class ChangePassword extends Fragment {

    View myView;
    Button change_passw;
    LoginDataBaseAdapter loginDataBaseAdapter;
    TextView current_pass,new_pass,confirm_pass;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ((AdminActivity) getActivity()).setActionBarTitle("ChangePassword");

        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(getActivity());
        loginDataBaseAdapter=loginDataBaseAdapter.open();

        myView=inflater.inflate(R.layout.changepassword,container,false);

        change_passw=(Button)myView.findViewById(R.id.change_pass);
        current_pass=(TextView)myView.findViewById(R.id.current_pass);
        new_pass=(TextView)myView.findViewById(R.id.new_pass);
        confirm_pass=(TextView)myView.findViewById(R.id.confirm_pass);



        change_passw.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View myview){
                Log.d("going","gone");
                String check=loginDataBaseAdapter.getSingleEntryA("admin");
                if(!current_pass.getText().toString().equals(check))
                    Toast.makeText(getActivity(), "Please Enter Correct Password", Toast.LENGTH_LONG).show();
                else if(!confirm_pass.getText().toString().equals(new_pass.getText().toString()))
                    Toast.makeText(getActivity(), "Password does not match", Toast.LENGTH_LONG).show();
                else
                    loginDataBaseAdapter.updateEntryA(confirm_pass.getText().toString());
            }
        });
        return myView;
    }






}
