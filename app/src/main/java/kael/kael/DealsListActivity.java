package kael.kael;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class DealsListActivity extends Activity {

    ListView dealsList;
    static DealManager.Deal selectedDeal;
    static DealManager dm;
    Button addButton; //rem = remove

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(kael.kael.R.layout.activity_deals_list);
        dealsList = (ListView) findViewById(kael.kael.R.id.dealsList);
        addButton = (Button) findViewById(kael.kael.R.id.addDealButton);

        init();
        dealsList.setAdapter(dm.getDealsAdapter(this));
        dealsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Pattern pattern = Pattern.compile("\\((\\d+)\\)");
                Matcher matcher = pattern.matcher(dealsList.getItemAtPosition(position).toString());
                while (matcher.find()) {
                    selectedDeal = dm.getDealById(Integer.parseInt(matcher.group(1).toString()));
                    Intent addDialog = new Intent(getApplicationContext(), DealActivity.class);
                    startActivityForResult(addDialog, Utils.RequestCodes.REQUEST_EDIT);
                    break;
                }

            }
        });
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDeal = dm.addDeal("New deal");
                Intent addDialog = new Intent(getApplicationContext(), DealActivity.class);
                startActivityForResult(addDialog, Utils.RequestCodes.REQUEST_ADD);
            }
        });
    }

    private void init() {
        //for debug only TODO: delete
        dm = new DealManager();
        dm.addDeal("such a lonely deal");
        dm.addDeal("and its mine");
        dm.addDeal("and it will always be lonely");

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        refreshDealsListView();
    }

    private void refreshDealsListView() {
        dealsList.setAdapter(dm.getDealsAdapter(this));
    }

}
