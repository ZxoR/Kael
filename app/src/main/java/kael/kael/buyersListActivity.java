package kael.kael;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class buyersListActivity extends Activity {
    ListView buyersListView;
    Button addBuyerButton;
    static DealManager.Deal currentDeal;
    static DealManager.Deal.Buyer buyer_edit_selected_buyer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(kael.kael.R.layout.activity_buyers_list);
        buyersListView = (ListView) findViewById(kael.kael.R.id.buyersListView);
        addBuyerButton = (Button) findViewById(kael.kael.R.id.openBuyersList);
        currentDeal = DealActivity.currentDeal;
        refreshBuyersListView();
        buyersListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        buyersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pattern pattern = Pattern.compile("\\((\\d+)\\)");
                Matcher matcher = pattern.matcher(buyersListView.getItemAtPosition(position).toString());
                while (matcher.find()) {
                    buyer_edit_selected_buyer = currentDeal.getBuyerByID(Integer.parseInt(matcher.group(1).toString()));
                    Intent addDialog = new Intent(getApplicationContext(), buyer_edit.class);
                    startActivityForResult(addDialog, Utils.RequestCodes.REQUEST_EDIT);
                    break;
                }

            }
        });

        addBuyerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buyer_edit_selected_buyer = currentDeal.addBuyer("New buyer", 0, false);
                Intent addDialog = new Intent(getApplicationContext(), buyer_edit.class);
                startActivityForResult(addDialog, Utils.RequestCodes.REQUEST_ADD);
            }
        });
    }

    private void refreshBuyersListView() {
        buyersListView.setAdapter(currentDeal.getBuyersAdapter(this));
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Utils.RequestCodes.REQUEST_EDIT) {
            if (resultCode == Utils.ResultCodes.EDITED_OK) {
                refreshBuyersListView(); //do refresh
                Utils.showToast(this, "The user was edited successfully", Toast.LENGTH_SHORT);
            }
        }
        if ((requestCode == Utils.RequestCodes.REQUEST_EDIT) || (requestCode == Utils.RequestCodes.REQUEST_ADD)) {
            if (resultCode == Utils.ResultCodes.DELETED_OK) {
                refreshBuyersListView(); //do refresh
                Utils.showToast(this, "The user was removed successfully", Toast.LENGTH_SHORT);
            }
        }
        if (requestCode == Utils.RequestCodes.REQUEST_ADD) {
            if (resultCode == Utils.ResultCodes.EDITED_OK) {
                refreshBuyersListView(); //do refresh
                Utils.showToast(this, "The user was added successfully", Toast.LENGTH_SHORT);
            } else {
                //On exit
                currentDeal.deleteBuyerByID(buyer_edit_selected_buyer.getID());
                Utils.showToast(this, "The user was not added", Toast.LENGTH_SHORT);
                refreshBuyersListView(); //do refresh
            }
        }
    }


}
