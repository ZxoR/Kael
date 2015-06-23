package kael.kael;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


public class buyer_edit extends Activity {
    TextView title;
    Button applyButton, removeButton;
    EditText nameEditText, orderedEditText;
    CheckBox hasPaid;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(kael.kael.R.layout.activity_buyer_edit);
        //initialize variables
        title = (TextView) findViewById(kael.kael.R.id.titleLabel);
        nameEditText = (EditText) findViewById(kael.kael.R.id.nameEditText);
        orderedEditText = (EditText) findViewById(kael.kael.R.id.orderedEditText);
        hasPaid = (CheckBox) findViewById(kael.kael.R.id.hasPaidCheckBox);
        applyButton = (Button) findViewById(kael.kael.R.id.applyButton);
        removeButton = (Button) findViewById(kael.kael.R.id.removeButton);

        title.setText("Customer: " + buyersListActivity.buyer_edit_selected_buyer.getBuyerName());
        nameEditText.setText(buyersListActivity.buyer_edit_selected_buyer.getBuyerName());
        orderedEditText.setText("" + buyersListActivity.buyer_edit_selected_buyer.getOrderedWeight());
        hasPaid.setChecked(buyersListActivity.buyer_edit_selected_buyer.isHasPaid());

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyersListActivity.buyer_edit_selected_buyer.setBuyerName(nameEditText.getText().toString());
                buyersListActivity.buyer_edit_selected_buyer.setOrderedWeight(Double.parseDouble(orderedEditText.getText().toString()));
                buyersListActivity.buyer_edit_selected_buyer.setHasPaid(hasPaid.isChecked());

                Intent data = new Intent();
                if (getParent() == null) {
                    setResult(Utils.ResultCodes.EDITED_OK, data);
                } else {
                    getParent().setResult(Utils.ResultCodes.EDITED_OK, data);
                }
                finish();
            }
        });

        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //On remove
                DealActivity.currentDeal.deleteBuyerByID(buyersListActivity.buyer_edit_selected_buyer.getID());
                Intent data = new Intent();
                if (getParent() == null) {
                    setResult(Utils.ResultCodes.DELETED_OK, data);
                } else {
                    getParent().setResult(Utils.ResultCodes.DELETED_OK, data);
                }
                finish();
            }
        });
    }
}
