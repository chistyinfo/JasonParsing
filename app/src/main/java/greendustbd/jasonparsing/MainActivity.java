package greendustbd.jasonparsing;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.aapbd.appbajarlib.network.AAPBDHttpClient;
import com.aapbd.appbajarlib.network.NetInfo;
import com.aapbd.appbajarlib.notification.AlertMessage;
import com.aapbd.appbajarlib.notification.BusyDialog;
import com.google.gson.Gson;

import java.util.concurrent.Executors;

import greendustbd.jasonparsing.Model.JSonParse;
import greendustbd.jasonparsing.Utilits.JsonUrls;

public class MainActivity extends AppCompatActivity {
    public static final String TAG ="MainActivity";
    private Context con;
    private JSonParse jsonparse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con=this;


    }

    public void apiCall(View v){


//        AlertMessage.showMessage(con,"I m the title","I am the button");

        getDataFromServer();

    }
    /*
    get data from server
     */

    private void getDataFromServer() {

        if(!NetInfo.isOnline(con))
        {
            AlertMessage.showMessage(con,"Warning ","No Internet!");
            return;
        }
        final BusyDialog busydialog=new BusyDialog(con,true,"Server is loading");
        busydialog.show();
        Executors.newSingleThreadExecutor().submit(new Runnable() {
            @Override
            public void run() {

                /*
                Call server data
                 */
                String response= AAPBDHttpClient.get(JsonUrls.JSONURL).body();
                /*
                Insilization Gson
                 */
                Gson gson=new Gson();
                /*
                Apply json to parse
                 */

                jsonparse=gson.fromJson(response,JSonParse.class);

                /*
                Back to UI
                 */

                runOnUiThread(new Runnable() {
                                  @Override
                                  public void run() {
                                      if (busydialog != null) {

                                          busydialog.dismis();

                                      }

                                      updateuri();
                                  }

                              });


            }

            /*
                Update the Ui with data
                 */

            private void updateuri() {

                TextView tv=(TextView)findViewById(R.id.txt);
                tv.setText(jsonparse.getTitle()+"\n "+jsonparse.getDate()+"\n"+jsonparse.getTime());
            }
        });
    }
}
