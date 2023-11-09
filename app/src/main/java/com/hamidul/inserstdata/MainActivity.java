package com.hamidul.inserstdata;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.hardware.camera2.CameraExtensionSession;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText edName,edMobile,edEmail,edSearch;
    TextInputLayout layName,ls;
    Button button;
    ProgressBar progressBar;
    HashMap<String,String> hashMap;
    ArrayList< HashMap<String ,String> > arrayList = new ArrayList<>();
    GridView gridView;
    Toast toast;
    SearchView searchView;
    LinearLayout ll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        edName = findViewById(R.id.edName);
//        edMobile = findViewById(R.id.edMobile);
//        edEmail = findViewById(R.id.edEmail);
//        button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar);
//        layName = findViewById(R.id.layName);
        gridView = findViewById(R.id.gridView);
        searchView = findViewById(R.id.searchView);
//        edSearch = findViewById(R.id.edSearch);
//        ls = findViewById(R.id.ls);
//        ll = findViewById(R.id.ll);

//        edName.addTextChangedListener(watcher);
//        edMobile.addTextChangedListener(watcher);
//        edEmail.addTextChangedListener(watcher);

        loadData();


//        edSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//                String s1 = edSearch.getText().toString();
//
//                if (s1.length()>0){
//                    if (edSearch.equals("")){
//                        ll.setVisibility(View.VISIBLE);
//                        loadData();
//                    }else {
//                        search();
//                        ll.setVisibility(View.GONE);
//                    }
//                }else {
//                    ll.setVisibility(View.VISIBLE);
//                    loadData();
//                }
//
//
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ll.setVisibility(View.GONE);
//                String name = edName.getText().toString();
//                String mobile = edMobile.getText().toString();
//                String email = edEmail.getText().toString();
//
//                String url = "https://smhamidulcodding.000webhostapp.com/apps/data.php?name="+name+"&mobile="+mobile+"&email="+email;
//
//                progressBar.setVisibility(View.VISIBLE);
//                StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                        new Response.Listener<String>() {
//                            @Override
//                            public void onResponse(String response) {
//                                progressBar.setVisibility(View.GONE);
////                                Toast.makeText(MainActivity.this, "Data Sent Successes", Toast.LENGTH_SHORT).show();
//                                loadData();
//                            }
//                        }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
//                    }
//                });
//
//                RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
//                requestQueue.add(stringRequest);
//
//            }
//        });

    }

//    private TextWatcher watcher = new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//            String name = edName.getText().toString();
//            String mobile = edMobile.getText().toString();
//            String email = edEmail.getText().toString();
//
//            if (before>count){
//                layName.setError(null);
//            }else {
//                layName.setError(null);
//            }
//
//            button.setEnabled(!name.isEmpty() && !mobile.isEmpty() && !email.isEmpty());
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//        }
//    };

    private void loadData(){

        arrayList = new ArrayList<>();

        progressBar.setVisibility(View.VISIBLE);


        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);

        String url = "https://smhamidulcodding.000webhostapp.com/apps/view.php";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                for (int x = 0; x<response.length(); x++){

                    try {
                        JSONObject jsonObject = response.getJSONObject(x);
                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String mobile = jsonObject.getString("mobile");
                        String email = jsonObject.getString("email");

                        hashMap = new HashMap<>();
                        hashMap.put("id",id);
                        hashMap.put("name",name);
                        hashMap.put("mobile",mobile);
                        hashMap.put("email",email);
                        arrayList.add(hashMap);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                }// for loop end

                if (arrayList.size()>0){
                    MyAdapter myAdapter = new MyAdapter(arrayList);
                    gridView.setAdapter(myAdapter);

                    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            myAdapter.filter(newText);
                            return false;
                        }
                    });
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                toastMessage(error.toString());
            }
        });

        jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(20 * 1000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(jsonArrayRequest);


    }// loadData end


    public class MyAdapter extends BaseAdapter{
        LayoutInflater layoutInflater;
        ArrayList<HashMap<String,String>> itemList;
        ArrayList<HashMap<String,String>> filterList;

        public MyAdapter (ArrayList<HashMap<String,String>> itemList){
            this.itemList=itemList;
            this.filterList=new ArrayList<>(itemList);
        }

        @Override
        public int getCount() {
            return filterList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View myView = layoutInflater.inflate(R.layout.item,parent,false);

            TextView tvName = myView.findViewById(R.id.tvName);
            TextView tvMobile = myView.findViewById(R.id.tvMobile);
            TextView tvEmail = myView.findViewById(R.id.tvEmail);
            Button update = myView.findViewById(R.id.btnUpdate);
            Button delete = myView.findViewById(R.id.btnDelete);

            HashMap<String ,String> hashMap1 = filterList.get(position);
            String id = hashMap1.get("id");
            String name = hashMap1.get("name");
            String mobile = hashMap1.get("mobile");
            String email = hashMap1.get("email");

            tvName.setText("Name : "+name);
            tvMobile.setText("Phone : "+mobile);
            tvEmail.setText("Email : "+email);

//            update.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    progressBar.setVisibility(View.VISIBLE);
//
//                    String url = "https://smhamidulcodding.000webhostapp.com/apps/update.php?id="+id+"&name="+edName.getText().toString()+"&mobile="+edMobile.getText().toString()+"&email="+edEmail.getText().toString();
//
//                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(String response) {
//
//                            progressBar.setVisibility(View.GONE);
//
////                            new AlertDialog.Builder(MainActivity.this)
////                                    .setTitle("Server Response")
////                                    .setMessage(response)
////                                    .show();
//
//                            toastMessage(response.toString());
//
//                            loadData();
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                            progressBar.setVisibility(View.GONE);
//                            toastMessage(error.toString());
//                        }
//                    });
//
//                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
//                    requestQueue.add(stringRequest);
//
//                }
//            });

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("Are You Sure ")
                            .setMessage("Delete This Data !!")
                            .setCancelable(false)
                            .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                }
                            })
                            .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    progressBar.setVisibility(View.VISIBLE);

                                    String url = "https://smhamidulcodding.000webhostapp.com/apps/delete.php?id="+id;

                                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {

                                            progressBar.setVisibility(View.GONE);

                                            toastMessage(response.toString());
                                            loadData();
                                        }
                                    }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            progressBar.setVisibility(View.GONE);
                                            toastMessage(error.toString());
                                        }
                                    });

                                    RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                                    requestQueue.add(stringRequest);
                                }
                            })
                            .show();
                }
            });

            return myView;
        }

        public void filter (String query){
            query=query.toLowerCase();
            filterList.clear();
            if (query.isEmpty()){
                filterList.addAll(itemList);
            }else {
                for (HashMap<String,String> item : itemList){
                    if (item.get("id").toLowerCase().contains(query) || item.get("name").toLowerCase().contains(query)){
                        filterList.add(item);
                    }
                }
            }

            notifyDataSetChanged();

        }


    }// adapter end

//    private void search(){
//        arrayList = new ArrayList<>();
//
//        progressBar.setVisibility(View.VISIBLE);
//
//        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
//
//        String search = edSearch.getText().toString();
//
//        String url = "https://smhamidulcodding.000webhostapp.com/apps/search.php?search="+search;
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                progressBar.setVisibility(View.GONE);
//                for (int x = 0; x<response.length(); x++){
//
//                    try {
//                        JSONObject jsonObject = response.getJSONObject(x);
//                        String id = jsonObject.getString("id");
//                        String name = jsonObject.getString("name");
//                        String mobile = jsonObject.getString("mobile");
//                        String email = jsonObject.getString("email");
//
//                        hashMap = new HashMap<>();
//                        hashMap.put("id",id);
//                        hashMap.put("name",name);
//                        hashMap.put("mobile",mobile);
//                        hashMap.put("email",email);
//                        arrayList.add(hashMap);
//
//                    } catch (JSONException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                }// for loop end
//
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                progressBar.setVisibility(View.GONE);
//                toastMessage(error.toString());
//            }
//        });
//        requestQueue.add(jsonArrayRequest);
//    }


    private void toastMessage(String text){
        if (toast!=null) toast.cancel();
        toast = Toast.makeText(getApplicationContext(),text,Toast.LENGTH_LONG);
        toast.show();
    }



}