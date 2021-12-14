package com.sameetasadullah.i180479_i180531.dataLayer;

import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sameetasadullah.i180479_i180531.R;
import com.sameetasadullah.i180479_i180531.logicLayer.Hotel;
import com.sameetasadullah.i180479_i180531.logicLayer.Room;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class writerAndReader {
    Context context;

    public writerAndReader(Context context) {
        this.context = context;
    }

    public void readRoomsFromServer(Hotel hotel)
    {
        String url="http://192.168.18.81/smd_project/get_data.php";
        StringRequest request=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            if(object.getInt("reqcode")==1){
                                JSONArray contacts=object.getJSONArray("data");
                                Vector<Room> rooms = new Vector<>();
                                for (int i=0;i<contacts.length();i++)
                                {
                                    int ID = contacts.getJSONObject(i).getInt("id");
                                    if (ID == hotel.getID()) {
                                        Room r = new Room(
                                                contacts.getJSONObject(i).getInt("roomno"),
                                                contacts.getJSONObject(i).getString("type")
                                        );
                                        String date = contacts.getJSONObject(i).getString("available_date");
                                        if (date.equals("0000-00-00")) {
                                            r.setAvailableDate(null);
                                        } else {
                                            r.setAvailableDate(LocalDate.parse(date));
                                        }
                                        r.setAvailable(Boolean.parseBoolean(contacts.getJSONObject(i).getString("is_available")));
                                        rooms.add(r);
                                    }
                                    hotel.setRooms(rooms);
                                }
                            }
                            else {
                                Toast.makeText(context,
                                        "Failed to load data",
                                        Toast.LENGTH_LONG
                                ).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,
                                error.toString(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            protected Map<String,String> getParams()
            {
                Map<String,String> data=new HashMap<String,String>();
                data.put("tableName", "rooms");
                return data;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }

    public void truncateATable(String tableName){
        String url="http://192.168.18.81/smd_project/truncate_table.php";
        StringRequest request=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // do nothing
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,
                                error.toString(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        ){
            protected Map<String,String> getParams()
            {
                Map<String,String> data=new HashMap<String,String>();
                data.put("tableName", tableName);
                return data;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }
}
