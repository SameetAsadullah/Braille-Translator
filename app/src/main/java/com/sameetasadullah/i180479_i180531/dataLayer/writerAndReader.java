package com.sameetasadullah.i180479_i180531.dataLayer;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sameetasadullah.i180479_i180531.logicLayer.Customer;
import com.sameetasadullah.i180479_i180531.logicLayer.HRS;
import com.sameetasadullah.i180479_i180531.logicLayer.Hotel;
import com.sameetasadullah.i180479_i180531.logicLayer.Room;
import com.sameetasadullah.i180479_i180531.logicLayer.Vendor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class writerAndReader {
    Context context;
    String directoryUrl = "http://192.168.18.81/smd_project/";

    public writerAndReader(Context context) {
        this.context = context;
    }

    @NonNull
    private byte[] getByteArray(Uri image) {
        Bitmap pic = null;
        try {
            pic = MediaStore.Images.Media.getBitmap(context.getContentResolver(), image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        pic.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public void insertCustomerDataIntoServer(Customer customer, Uri dp, VolleyCallBack volleyCallBack) {
        String url = directoryUrl + "insert_image.php";
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(
                Request.Method.POST,
                url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            if (obj.getString("code").equals("1")) {
                                String imageUrl = directoryUrl + obj.getString("url");
                                customer.setDp(imageUrl);
                                insertCustomerIntoServer(customer,imageUrl, volleyCallBack);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context.getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("GotError",""+error.getMessage());
                    }
                }
        ){
            @Override
            protected Map<String, DataPart> getByteData() throws AuthFailureError {
                Map<String, DataPart> params = new HashMap<>();
                long imageName = System.currentTimeMillis();
                params.put("image", new DataPart(imageName + ".png", getByteArray(dp)));
                return params;
            }
        };
        Volley.newRequestQueue(context).add(volleyMultipartRequest);
    }

    private void insertCustomerIntoServer(Customer customer, String imageUrl, VolleyCallBack volleyCallBack) {
        String url = directoryUrl + "insert_data.php";
        StringRequest request=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        volleyCallBack.onSuccess();
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
                data.put("dp", imageUrl);
                return data;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }

    public void getCustomersFromServer(Vector<Customer> customers) {
        String url = directoryUrl + "get_data.php";
        StringRequest request=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            if(object.getInt("reqcode")==1){
                                JSONArray data=object.getJSONArray("data");
                                for (int i=0;i<data.length();i++)
                                {
                                    customers.add(
                                            new Customer(
                                                    data.getJSONObject(i).getInt("id"),
                                                    data.getJSONObject(i).getString("email"),
                                                    data.getJSONObject(i).getString("password"),
                                                    data.getJSONObject(i).getString("name"),
                                                    data.getJSONObject(i).getString("address"),
                                                    data.getJSONObject(i).getString("phoneno"),
                                                    data.getJSONObject(i).getString("cnic"),
                                                    data.getJSONObject(i).getString("accountno"),
                                                    data.getJSONObject(i).getString("dp")
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

    public void insertVendorDataIntoServer(Vendor vendor, Uri dp, VolleyCallBack volleyCallBack) {
        String url = directoryUrl + "insert_image.php";
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(
                Request.Method.POST,
                url,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            if (obj.getString("code").equals("1")) {
                                String imageUrl = directoryUrl + obj.getString("url");
                                vendor.setDp(imageUrl);
                                insertVendorIntoServer(vendor,
                                        imageUrl,
                                        volleyCallBack);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context.getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        Log.e("GotError",""+error.getMessage());
                    }
                }
        ){
            @Override
            protected Map<String, DataPart> getByteData() throws AuthFailureError {
                Map<String, DataPart> params = new HashMap<>();
                long imageName = System.currentTimeMillis();
                params.put("image", new DataPart(imageName + ".png", getByteArray(dp)));
                return params;
            }
        };
        Volley.newRequestQueue(context).add(volleyMultipartRequest);
    }

    private void insertVendorIntoServer(Vendor vendor, String imageUrl, VolleyCallBack volleyCallBack) {
        String url = directoryUrl + "insert_data.php";
        StringRequest request=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        volleyCallBack.onSuccess();
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
                data.put("dp", imageUrl);
                return data;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }

    public void getVendorsFromServer(Vector<Vendor> vendors) {
        String url = directoryUrl + "get_data.php";
        StringRequest request=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            if(object.getInt("reqcode")==1){
                                JSONArray data=object.getJSONArray("data");
                                for (int i=0;i<data.length();i++)
                                {
                                    vendors.add(
                                            new Vendor(
                                                    data.getJSONObject(i).getInt("id"),
                                                    data.getJSONObject(i).getString("email"),
                                                    data.getJSONObject(i).getString("password"),
                                                    data.getJSONObject(i).getString("name"),
                                                    data.getJSONObject(i).getString("address"),
                                                    data.getJSONObject(i).getString("phoneno"),
                                                    data.getJSONObject(i).getString("cnic"),
                                                    data.getJSONObject(i).getString("accountno"),
                                                    data.getJSONObject(i).getString("dp")
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
        String url = directoryUrl + "insert_data.php";
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
                data.put("registered_by", hotel.getRegistered_by());
                return data;
            }
        };
        Volley.newRequestQueue(context).add(request);
    }

    public void getHotelsFromServer(Vector<Hotel> hotels, VolleyCallBack volleyCallBack) {
        String url = directoryUrl + "get_data.php";
        StringRequest request=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            if(object.getInt("reqcode")==1){
                                JSONArray data=object.getJSONArray("data");
                                for (int i=0;i<data.length();i++)
                                {
                                    hotels.add(
                                            new Hotel(
                                                    data.getJSONObject(i).getInt("id"),
                                                    data.getJSONObject(i).getString("name"),
                                                    data.getJSONObject(i).getString("address"),
                                                    data.getJSONObject(i).getString("location"),
                                                    data.getJSONObject(i).getString("single_rooms"),
                                                    data.getJSONObject(i).getString("double_rooms"),
                                                    data.getJSONObject(i).getString("single_room_price"),
                                                    data.getJSONObject(i).getString("double_room_price"),
                                                    data.getJSONObject(i).getString("registered_by")
                                                    )
                                    );
                                }
                                volleyCallBack.onSuccess();
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
        String url = directoryUrl + "insert_data.php";
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
                        data.put("available_date", "null");
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
        String url = directoryUrl + "get_data.php";
        StringRequest request=new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object=new JSONObject(response);
                            if(object.getInt("reqcode")==1){
                                JSONArray data=object.getJSONArray("data");
                                Vector<Room> rooms = new Vector<>();
                                for (int i=0;i<data.length();i++)
                                {
                                    int hotel_id = data.getJSONObject(i).getInt("hotel_id");
                                    if (hotel_id == hotel.getID()) {
                                        Room r = new Room(
                                                data.getJSONObject(i).getInt("roomno"),
                                                data.getJSONObject(i).getString("type")
                                        );
                                        String date = data.getJSONObject(i).getString("available_date");
                                        if (date.equals("null")) {
                                            r.setAvailableDate(null);
                                        } else {
                                            r.setAvailableDate(LocalDate.parse(date));
                                        }
                                        r.setAvailable(Boolean.parseBoolean(data.getJSONObject(i).getString("is_available")));
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
        String url = directoryUrl + "truncate_table.php";
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
