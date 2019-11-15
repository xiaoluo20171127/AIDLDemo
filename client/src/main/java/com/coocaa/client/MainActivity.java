package com.coocaa.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.coocaa.aidldemo.IPersonManage;
import com.coocaa.aidldemo.Person;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button getDataBtn;
    Button addDataBtn;
    Button deleteDataBtn;
    Button bindBtn;
    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        getDataBtn = findViewById(R.id.getSizeBtn);
        addDataBtn = findViewById(R.id.addDataBtn);
        deleteDataBtn = findViewById(R.id.deleteData);
        bindBtn = findViewById(R.id.bindBtn);
        getDataBtn.setOnClickListener(this);
        addDataBtn.setOnClickListener(this);
        deleteDataBtn.setOnClickListener(this);
        bindBtn.setOnClickListener(this);
        mTextView = findViewById(R.id.textView);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bindBtn: {
                bind();
                break;
            }
            case R.id.getSizeBtn: {
                try {
                    List<Person> list = mManage.getPersonData();
                    String personName = "";
                    for (Person p : list) {
                        personName = personName + p.getName() + "  ";
                    }
                    mTextView.setText(personName);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            }
            case R.id.addDataBtn:
            {
                Person p = new Person();
                p.setName("peanut");
                p.setSex("man");
                try {
                    mManage.addPerson(p);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void bind() {
        Intent intent = new Intent();
//        intent.setPackage("com.coocaa.aidldemo");
//        intent.setAction("com.coocaa.bindservice");
        intent.setClassName("com.coocaa.aidldemo","com.coocaa.aidldemo.MyService");
        bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    IPersonManage mManage;

    ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mManage = IPersonManage.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
