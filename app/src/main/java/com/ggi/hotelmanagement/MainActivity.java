package com.ggi.hotelmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText YourName,Room;
    TextView CheckIn,CheckOut,Results;
    Button Calcu;
    Spinner spCountry,spRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Binding();
        Validation();
        String countries[]={"Select your Country","Nepal","India","China","NewZerlandUK","UK","Canada","Nepal","China","New Zerland","UK","Canada"};
        ArrayAdapter adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,countries);
        spCountry.setAdapter(adapter);

        String roomType[]={"Select RoomType","Deluxe","Platinum","Presidential"};
        ArrayAdapter aa=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,roomType);
        spRoom.setAdapter(aa);


        Calcu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                operation();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.etCheckin:{
                final Calendar ca=Calendar.getInstance();

                int day=ca.get(Calendar.DAY_OF_MONTH);
                int month=ca.get(Calendar.MONTH);
                int years=ca.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String Date=dayOfMonth+"/"+month+1+"/"+year;
                        CheckIn.setText(Date);

                    }

                },day,month,years);

                datePickerDialog.show();
                break;

            }
            case R.id.etCheckOut:{
                Calendar ca=Calendar.getInstance();
                int day,month,years;
                day=ca.get(Calendar.DAY_OF_MONTH);
                month=ca.get(Calendar.MONTH);
                years=ca.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String Date=dayOfMonth+"/"+month+1+"/"+year;
                        CheckOut.setText(Date);
                    }

                },day,month,years);
                datePickerDialog.show();
                break;

            }

        }


    }

    public void Binding(){
        spCountry=findViewById(R.id.spCountry);
        spRoom=findViewById(R.id.spRoom);
        YourName=findViewById(R.id.etname);
        CheckIn=findViewById(R.id.etCheckin);
        CheckOut=findViewById(R.id.etCheckOut);
        Calcu=findViewById(R.id.btCalculate);
        Room=findViewById(R.id.etRoom);
        Results=findViewById(R.id.etResults);

        CheckIn.setOnClickListener(this);
        CheckOut.setOnClickListener(this);

    }


    public void operation()
    {

        String checkIN=CheckIn.getText().toString();
        String checkOUT=CheckOut.getText().toString();

        String Entry[]=checkIN.split("/",3);
        String Exit[]=checkOUT.split("/",3);

        int CheckOutCon=Integer.parseInt(Exit[1]);
        int CheckInCon=Integer.parseInt(Entry[1]);

        if(CheckOutCon>CheckInCon)
        {
        int Cal=Integer.parseInt(Exit[1])-Integer.parseInt(Entry[1]);

        int RoomNo=Integer.parseInt(Room.getText().toString());

        if(spRoom.getSelectedItem().toString()=="Deluxe"){
            int price=2000;
            int FinalCal=price*Cal*RoomNo;
            Results.setText("The result is"+" "+FinalCal);
        }

       else if(spRoom.getSelectedItem().toString()=="Platinum"){
            int price=4000;
            int FinalCal=price*Cal*RoomNo;
            Results.setText("The result is"+" "+FinalCal);
        }

       else if (spRoom.getSelectedItem().toString()=="Presidential"){
            int price=7000;
            int FinalCal=price*Cal*RoomNo;
            Results.setText("The result is"+" "+FinalCal);
        }
       else
           CheckOut.setError("Please select valid Date");
    }
        }
    public void Validation(){
        if(TextUtils.isEmpty(YourName.getText()));
        YourName.requestFocus();
        return;
    }


}
