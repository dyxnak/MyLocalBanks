package sg.edu.rp.c346.id21035817.mylocalbanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView tvDBS, tvOCBC, tvUOB;
    String bankClicked = "";
    String url = "";
    String phoneNo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDBS = findViewById(R.id.textViewDBS);
        tvOCBC = findViewById(R.id.textViewOCBC);
        tvUOB = findViewById(R.id.textViewUOB);

        registerForContextMenu(tvDBS);
        registerForContextMenu(tvOCBC);
        registerForContextMenu(tvUOB);

        tvDBS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ "18001111111"));
                startActivity(intentCall);

            }
        });

        tvOCBC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ "18003633333"));
                startActivity(intentCall);

            }
        });

        tvUOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ "18002222121"));
                startActivity(intentCall);

            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if (v == tvDBS) {
            bankClicked = "DBS";
        } else if (v == tvOCBC) {
            bankClicked = "OCBC";
        } else if (v == tvUOB){
            bankClicked = "UOB";
        } else {
            bankClicked = "";
        }

        menu.add(0,0,0,"Website");
        menu.add(0,1,1,"Contact the bank");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        TextView tvSelected = null;

        if (bankClicked.equalsIgnoreCase("DBS")) {
            url = "https://www.dbs.com.sg";
            phoneNo = "18001111111";
            tvSelected = tvDBS;
        } else if (bankClicked.equalsIgnoreCase("OCBC")) {
            url = "https://www.ocbc.com";
            phoneNo = "18003633333";
            tvSelected = tvOCBC;
        } else if (bankClicked.equalsIgnoreCase("UOB")) {
            url = "https://www.uob.com.sg";
            phoneNo = "18002222121";
            tvSelected = tvUOB;
        } else {
            url = "";
            phoneNo = "";
        }

        if(item.getItemId()==0) { //check whether the selected menu item ID is 0

            Intent intentCall = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intentCall);
        }
        else if(item.getItemId()==1) { //check if the selected menu item ID is 1
            Intent intentCall = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ phoneNo));
            startActivity(intentCall);
        }
        return super.onContextItemSelected(item); //pass menu item to the superclass implementation
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_banks, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();

        if (item.getItemId() == R.id.EnglishSelection) {
            tvDBS.setText("DBS");
            tvOCBC.setText("OCBC");
            tvUOB.setText("UOB");
            return true;

        } else if (item.getItemId() == R.id.ChineseSelection) {
            tvDBS.setText("星展银行");
            tvOCBC.setText("华侨银行");
            tvUOB.setText("大华银行");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}