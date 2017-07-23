package com.tcs.cmslogin;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by hema on 28-Jun-17.
 */

public class Logout extends Fragment {

    View myView;
    Button yes;
    LoginDataBaseAdapter loginDataBaseAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ((AdminActivity) getActivity()).setActionBarTitle("Logout");

        myView=inflater.inflate(R.layout.logout,container,false);
        yes=(Button)myView.findViewById(R.id.yes);
        //no=(Button)myView.findViewById(R.id.no);

        yes.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View myview){
                Intent in=new Intent(getActivity(),MainActivity.class);
                startActivity(in);
                getActivity().finish();


            }
        });

        return myView;
    }
}
