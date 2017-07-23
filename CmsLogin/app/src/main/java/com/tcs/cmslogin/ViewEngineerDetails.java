package com.tcs.cmslogin;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by hema on 27-Jun-17.
 */

public class ViewEngineerDetails extends Fragment  {
    View myView;
    TableLayout stk;
    TableRow tr;
    LoginDataBaseAdapter loginDataBaseAdapter;
    TextView tv0;
    String id=null;
    int count=0;
    ArrayList<TextView> tv=new ArrayList<>();

    public void addHeaders(){

        /** Create a TableRow dynamically **/
        TableLayout stk = (TableLayout) myView.findViewById(R.id.t_l);

        TableRow tbrow0 = new TableRow(this.getActivity());

        TextView tv0 = new TextView(this.getActivity());
        tv0.setText(" Employee-Id ");
        tv0.setTextColor(Color.BLACK);
        tv0.setPadding(30,30,30,30);
        tv0.setTypeface(null, Typeface.BOLD);
        tbrow0.addView(tv0);

        TextView tv1 = new TextView(this.getActivity());
        tv1.setText(" Name ");
        tv1.setTextColor(Color.BLACK);
        tv1.setPadding(30,30,30,30);
        tv1.setTypeface(null, Typeface.BOLD);
        tbrow0.addView(tv1);

        TextView tv2 = new TextView(this.getActivity());
        tv2.setText(" Department ");
        tv2.setTextColor(Color.BLACK);
        tv2.setPadding(30,30,30,30);
        tv2.setTypeface(null, Typeface.BOLD);
        tbrow0.addView(tv2);

        stk.addView(tbrow0, new TableLayout.LayoutParams(
                TableRow.LayoutParams.FILL_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT));

        addData();
           }

    public void addData()
    {
        ArrayList<String> empid=loginDataBaseAdapter.getEmpId();
        ArrayList<String> empname=loginDataBaseAdapter.getEmpName();
        ArrayList<String> empdept=loginDataBaseAdapter.getEmpDept();

        if(empid==null)
        {

        }
        else {
            int i = empid.size() - 1;
            int j = 0;
            TableLayout stk = (TableLayout) myView.findViewById(R.id.t_l);
            while (i >= 0) {
                TableRow tbrow0 = new TableRow(this.getActivity());

                tv0 = new TextView(this.getActivity());
                id = empid.get(j);

                tv.add(tv0);
                //tv.get(j).setTag(j);

                tv0.setText(id);
                tv0.setTextColor(Color.BLACK);
                tv0.setPadding(30, 30, 30, 30);
                tbrow0.addView(tv0);

                TextView tv1 = new TextView(this.getActivity());
                tv1.setText(empname.get(j));
                tv1.setTextColor(Color.BLACK);
                tv1.setPadding(30, 30, 30, 30);
                tbrow0.addView(tv1);

                TextView tv2 = new TextView(this.getActivity());
                tv2.setText(empdept.get(j));
                tv2.setTextColor(Color.BLACK);
                tv2.setPadding(30, 30, 30, 30);
                tbrow0.addView(tv2);

                j++;
                i--;

                stk.addView(tbrow0, new TableLayout.LayoutParams(
                        TableRow.LayoutParams.FILL_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));
            }
        }

      }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        ((AdminActivity) getActivity()).setActionBarTitle("Engineer Details");

        myView=inflater.inflate(R.layout.viewengineerdetails,container,false);

        // create a instance of SQLite Database
        loginDataBaseAdapter=new LoginDataBaseAdapter(getActivity());
        //loginDataBaseAdapter=loginDataBaseAdapter.open();

       // tl = (TableLayout)myView.findViewById(R.id.t_l);
        addHeaders();
       // addData();
        /*Button add=(Button)myView.findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                //Intent in=new Intent(ViewEngineerDetails.this,AddEngineerDetails.class);
                // do something
            }
        });*/
        return myView;
    }
}
