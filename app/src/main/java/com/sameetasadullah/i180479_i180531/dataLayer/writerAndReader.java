package com.sameetasadullah.i180479_i180531.dataLayer;

import android.content.Context;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sameetasadullah.i180479_i180531.logicLayer.Customer;
import com.sameetasadullah.i180479_i180531.logicLayer.Hotel;
import com.sameetasadullah.i180479_i180531.logicLayer.Room;
import com.sameetasadullah.i180479_i180531.logicLayer.Vendor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class writerAndReader {
    Context context;

    public writerAndReader(Context context) {
        this.context = context;
    }

    public void insertCustomerIntoServer(Customer customer) {
        String url="http://192.168.18.81/smd_project/insert_data.php";
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
                data.put("tableName", "customers");
                data.put("name", customer.getName());
                data.put("email", customer.getEmail());
                data.put("password", customer.getPassword());
                data.put("phoneno", customer.getPhoneNo());
                data.put("cnic", customer.getCNIC());
                data.put("accountno", customer.getAccountNo());
                data.put("address", customer.getAddress());
                return data;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }

    public void getCustomersFromServer(Vector<Customer> customers) {
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
                                for (int i=0;i<contacts.length();i++)
                                {
                                    customers.add(
                                            new Customer(
                                                    contacts.getJSONObject(i).getInt("id"),
                                                    contacts.getJSONObject(i).getString("name"),
                                                    contacts.getJSONObject(i).getString("email"),
                                                    contacts.getJSONObject(i).getString("password"),
                                                    contacts.getJSONObject(i).getString("phoneno"),
                                                    contacts.getJSONObject(i).getString("cnic"),
                                                    contacts.getJSONObject(i).getString("accountno"),
                                                    contacts.getJSONObject(i).getString("address")
                                            )
                                    );
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
                data.put("tableName", "customers");
                return data;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }

    public void insertVendorIntoServer(Vendor vendor) {
        String url="http://192.168.18.81/smd_project/insert_data.php";
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
                data.put("tableName", "vendors");
                data.put("name", vendor.getName());
                data.put("email", vendor.getEmail());
                data.put("password", vendor.getPassword());
                data.put("phoneno", vendor.getPhoneNo());
                data.put("cnic", vendor.getCNIC());
                data.put("accountno", vendor.getAccountNo());
                data.put("address", vendor.getAddress());
                return data;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }

    public void getVendorsFromServer(Vector<Vendor> vendors) {
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
                                for (int i=0;i<contacts.length();i++)
                                {
                                    vendors.add(
                                            new Vendor(
                                                    contacts.getJSONObject(i).getInt("id"),
                                                    contacts.getJSONObject(i).getString("name"),
                                                    contacts.getJSONObject(i).getString("email"),
                                                    contacts.getJSONObject(i).getString("password"),
                                                    contacts.getJSONObject(i).getString("phoneno"),
                                                    contacts.getJSONObject(i).getString("cnic"),
                                                    contacts.getJSONObject(i).getString("accountno"),
                                                    contacts.getJSONObject(i).getString("address")
                                            )
                                    );
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
                data.put("tableName", "vendors");
                return data;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }

    public void insertHotelIntoServer(Hotel hotel) {
        String url="http://192.168.18.81/smd_project/insert_data.php";
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
                data.put("tableName", "hotels");
                data.put("name", hotel.getName());
                data.put("address", hotel.getAddress());
                data.put("location", hotel.getLocation());
                data.put("single_rooms", hotel.getSingleRooms());
                data.put("double_rooms", hotel.getDoubleRooms());
                data.put("single_room_price", hotel.getSingleRoomPrice());
                data.put("double_room_price", hotel.getDoubleRoomPrice());
                return data;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }

    public void getHotelsFromServer(Vector<Hotel> hotels) {
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
                                for (int i=0;i<contacts.length();i++)
                                {
                                    hotels.add(
                                            new Hotel(
                                                    contacts.getJSONObject(i).getInt("id"),
                                                    contacts.getJSONObject(i).getString("name"),
                                                    contacts.getJSONObject(i).getString("address"),
                                                    contacts.getJSONObject(i).getString("location"),
                                                    contacts.getJSONObject(i).getString("single_rooms"),
                                                    contacts.getJSONObject(i).getString("double_rooms"),
                                                    contacts.getJSONObject(i).getString("single_room_price"),
                                                    contacts.getJSONObject(i).getString("double_room_price")
                                                    )
                                    );
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
                data.put("tableName", "hotels");
                return data;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }

    public void insertRoomsIntoServer(Hotel hotel) {
        String url="http://192.168.18.81/smd_project/insert_data.php";
        for (int i = 0; i < hotel.getRooms().size(); ++i) {
            Room room = hotel.getRooms().get(i);
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
                    data.put("tableName", "rooms");
                    data.put("hotel_id", String.valueOf(hotel.getID()));
                    data.put("roomno", String.valueOf(room.getNumber()));
                    data.put("type", room.getType());
                    if (room.getAvailableDate() == null) {
                        data.put("available_date", "0000-00-00");
                    } else {
                        data.put("available_date", room.getAvailableDate().toString());
                    }
                    data.put("is_available", String.valueOf(room.isAvailable()));
                    return data;
                }
            };
            Volley.newRequestQueue(context).add(request);
        }
    }

    public void getRoomsFromServer(Hotel hotel) {
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
                                    int hotel_id = contacts.getJSONObject(i).getInt("hotel_id");
                                    if (hotel_id == hotel.getID()) {
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
                                }
                                hotel.setRooms(rooms);
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

    public void truncateATable(String tableName) {
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
