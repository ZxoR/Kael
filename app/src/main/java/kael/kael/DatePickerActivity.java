package kael.kael;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import java.util.Calendar;
import java.util.Date;


public class DatePickerActivity extends Activity {


    private Button applyDateButton;
    private DatePicker datePicker;
    private Date dealDate;
    Bundle currentBundle, senderBundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(kael.kael.R.layout.activity_date_picker);
        assignViews();
        senderBundle = savedInstanceState;

        applyDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.putExtra("date", DateBuilder(datePicker));
                setResult(Utils.ResultCodes.RESULT_OK, i);
                finish();
            }
        });
    }


    private void assignViews() {
        applyDateButton = (Button) findViewById(kael.kael.R.id.applyDateButton);
        datePicker = (DatePicker) findViewById(kael.kael.R.id.datePicker);
        currentBundle = getIntent().getExtras();

        if (currentBundle.get("date") != null) {
            dealDate = (Date) currentBundle.get("date");
            datePicker.init(dealDate.getYear(), dealDate.getMonth(), dealDate.getDay(), null);
        }
    }

    private Date DateBuilder(DatePicker datePicker) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());
        cal.set(Calendar.MONTH, datePicker.getMonth());
        cal.set(Calendar.YEAR, datePicker.getYear());
        return cal.getTime();
    }

}
