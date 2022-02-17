package com.example.courseappmzc;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
EditText ed1,ed2,ed3,ed4,ed5;
AppCompatButton b1;
String getCoursetitle,getcourseDes,getcourseVenue,getcourseDate,getcoursedura;
String apiUrl="http://mountzioncollege.herokuapp.com/addcourse";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed1=(EditText) findViewById(R.id.ctitle);
        ed2=(EditText) findViewById(R.id.cdes);
        ed3=(EditText) findViewById(R.id.cvenue);
        ed4=(EditText) findViewById(R.id.cdate);
        ed5=(EditText) findViewById(R.id.cdura);
        b1=(AppCompatButton) findViewById(R.id.sub);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCoursetitle=ed1.getText().toString();
                getcourseDes=ed2.getText().toString();
                getcourseVenue=ed3.getText().toString();
                getcourseDate=ed4.getText().toString();
                getcoursedura=ed5.getText().toString();
                StringRequest sr=new StringRequest(Request.Method.POST,
                        apiUrl,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                ed1.setText("");
                                ed2.setText("");
                                ed3.setText("");
                                ed4.setText("");
                                ed5.setText("");
                                Toast.makeText(getApplicationContext(),response, Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        })
                {

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> params=new HashMap<>();
                        params.put("courseDuration",getcoursedura);
                        params.put("courseTitle",getCoursetitle);
                        params.put("courseVenue",getcourseVenue);
                        params.put("courseDate",getcourseDate);
                        params.put("courseDescription",getcourseDes);
                        return params;
                        }
                };
                RequestQueue rq= Volley.newRequestQueue(getApplicationContext());
                rq.add(sr);

            }
        });
    }
}