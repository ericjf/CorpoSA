package com.corposa.corposa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class AgendaAdicionar extends Activity {
    private DatePicker datePicker;
    private TimePicker timePicker;
    private EditText editText;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Button buyButton = new Button(this);
        this.setContentView(buyButton);
        super.onCreate(savedInstanceState);
        setTitle("Agenda");

        getActionBar().setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_agenda_adicionar);
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        timePicker = (TimePicker) findViewById(R.id.timePicker);
        editText = (EditText) findViewById(R.id.desc);
    }

    public void onDateSelectedButtonClick(View v){
        int day =  datePicker.getDayOfMonth();
        int month =  datePicker.getMonth() + 1;
        int year =  datePicker.getYear();

        int hour = timePicker.getCurrentHour();
        int min = timePicker.getCurrentMinute();

        String desc = editText.getText().toString();
        String dateString = day + "/" + month + "/" + year + " " + hour + ":" + min;

        DBAgenda dbAgenda = new DBAgenda(this);
        Compromisso compromisso = new Compromisso();
        compromisso.setPhoneNumber(dateString);
        compromisso.setName(desc);
        dbAgenda.addContact(compromisso);



        Intent intent = new Intent(this, Agenda.class);
        startActivity(intent);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.agenda_adicionar, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Intent intent = new Intent(this, Compromissos.class);
        startActivity(intent);


        return true;
    }

}
