package kael.kael;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;


public class Deal_Summary extends Activity {
    TextView dealNameText, dealWithText, unitText, pricePerUnitText, customersText,
            alreadyPayText, unpaidYetText, dealTotalValueText, dealTotalWeightText, dateText, locationText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //No title
        setContentView(kael.kael.R.layout.activity_deal_summary);


        //variables
        locationText = (TextView) findViewById(kael.kael.R.id.locationText);
        dateText = (TextView) findViewById(kael.kael.R.id.dateText);
        dealNameText = (TextView) findViewById(kael.kael.R.id.dealNameText);
        dealWithText = (TextView) findViewById(kael.kael.R.id.dealWithText);
        unitText = (TextView) findViewById(kael.kael.R.id.unitText);
        pricePerUnitText = (TextView) findViewById(kael.kael.R.id.pricePerUnitText);
        customersText = (TextView) findViewById(kael.kael.R.id.customersText);
        alreadyPayText = (TextView) findViewById(kael.kael.R.id.alreadyPayText);
        unpaidYetText = (TextView) findViewById(kael.kael.R.id.unpaidYetText);
        dealTotalValueText = (TextView) findViewById(kael.kael.R.id.dealTotalValueText);
        dealTotalWeightText = (TextView) findViewById(kael.kael.R.id.dealTotalWeightText);
        //eof variables

        init();


    }

    private void init() {
        dateText.setText(Utils.DateToString(DealActivity.currentDeal.getDealDate()));
        locationText.setText(DealActivity.currentDeal.getDealLocation());
        dealNameText.setText(DealActivity.currentDeal.getDealName());
        dealWithText.setText(DealActivity.currentDeal.getDealWith());
        unitText.setText(DealActivity.currentDeal.getUnit());
        pricePerUnitText.setText("" + DealActivity.currentDeal.getPricePerUnit());
        customersText.setText("" + DealActivity.currentDeal.getBuyersAmount());
        alreadyPayText.setText("" + DealActivity.currentDeal.orderedPaidPrice());
        unpaidYetText.setText("" + DealActivity.currentDeal.orderedUnpaidPrice());
        dealTotalValueText.setText("" + DealActivity.currentDeal.orderedTotalPrice());
        dealTotalWeightText.setText("" + DealActivity.currentDeal.orderedGram());
    }
}
