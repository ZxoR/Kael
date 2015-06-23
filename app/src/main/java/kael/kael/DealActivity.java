package kael.kael;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DealActivity extends Activity {
    ListView buyersListView;
    Date date;
    EditText dealNameInput, dealWithInput, dealLocationInput, dealUnitInput, dealPriceInput, dealDateInput;
    Button buyersListButton, summaryButton, removeButton, pickDateButton, saveDataButton;
    static DealManager.Deal currentDeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(kael.kael.R.layout.activity_deal_activity);
        currentDeal = DealsListActivity.selectedDeal;
        //declare variables
        date = currentDeal.getDealDate();
        dealNameInput = (EditText) findViewById(kael.kael.R.id.dealNameInput);
        pickDateButton = (Button) findViewById(kael.kael.R.id.pickDateButton);
        dealWithInput = (EditText) findViewById(kael.kael.R.id.dealWithInput);
        dealUnitInput = (EditText) findViewById(kael.kael.R.id.dealUnitInput);
        dealPriceInput = (EditText) findViewById(kael.kael.R.id.dealPriceInput);
        dealDateInput = (EditText) findViewById(kael.kael.R.id.dealDateInput);
        dealLocationInput = (EditText) findViewById(kael.kael.R.id.dealLocationInput);
        buyersListButton = (Button) findViewById(kael.kael.R.id.openBuyersList);
        summaryButton = (Button) findViewById(kael.kael.R.id.summaryButton);
        removeButton = (Button) findViewById(kael.kael.R.id.removeButton);
        saveDataButton = (Button) findViewById(kael.kael.R.id.saveDataButton);
        //EOF variables

        dealNameInput.setText(currentDeal.getDealName());
        dealWithInput.setText(currentDeal.getDealWith());
        dealUnitInput.setText(currentDeal.getUnit());
        dealPriceInput.setText("" + currentDeal.getPricePerUnit());

        DateFormat df = new SimpleDateFormat("dd/MM/yy");

        dealDateInput.setText(df.format(date));
        dealLocationInput.setText(currentDeal.getDealLocation());
        buyersListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBuyersListDialog();
            }
        });
        summaryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSummaryDialog();
            }
        });
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DealsListActivity.dm.removeDealById(currentDeal.getDealId());
                finish();
            }
        });

        pickDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDatePicker(date);
            }
        });
        saveDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fixme
                //Utils.showToast(?????, "Data applied successfully", Toast.LENGTH_SHORT);
                finish();
            }
        });
    }

    private void startDatePicker(Date date) {
        Intent i = new Intent(this, DatePickerActivity.class);
        i.putExtra("date", date);
        startActivityForResult(i, Utils.RequestCodes.REQUEST_RESULT);

    }

    @Override
    public void onPause() {
        currentDeal.setDealName(dealNameInput.getText().toString());
        currentDeal.setDealWith(dealWithInput.getText().toString());
        currentDeal.setDealLocation(dealLocationInput.getText().toString());
        currentDeal.setUnit(dealUnitInput.getText().toString());
        currentDeal.setPricePerUnit(Double.parseDouble(dealPriceInput.getText().toString()));
        currentDeal.setDealDate(date);
        super.onPause();
    }

    private void openSummaryDialog() {
        Intent intent = new Intent(getApplicationContext(), Deal_Summary.class);
        startActivity(intent);
    }

    private void openBuyersListDialog() {
        Intent intent = new Intent(getApplicationContext(), buyersListActivity.class);
        startActivity(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Utils.RequestCodes.REQUEST_RESULT) {
            if (resultCode == Utils.ResultCodes.RESULT_OK) {
                Bundle b = data.getExtras();
                if (b.get("date") != null) {
                    this.date = (Date) b.get("date");
                    this.dealDateInput.setText(Utils.DateToString(date)); //change the label
                }
            }
        }
    }
}



