package com.coocaa.aidldemo;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MyService extends Service {
    List<Person> mPersonList ;
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initData();
    }

    private void initData() {
        mPersonList = new ArrayList<>();
        Person person = new Person();
        person.setDate("20190909");
        person.setSelfId("43019119932312932312");
        person.setName("Andrew");
        person.setSex("woman");
        mPersonList.add(person);
        Toast.makeText(this,"service is start",Toast.LENGTH_SHORT).show();
    }

    IPersonManage.Stub mManage = new IPersonManage.Stub() {
        @Override
        public void addPerson(Person p) throws RemoteException {
            if (!mPersonList.contains(p)) mPersonList.add(p);
        }

        @Override
        public void deletePerson(Person p) throws RemoteException {
            if (mPersonList.contains(p)) mPersonList.remove(p);
        }

        @Override
        public int getPersonSize() throws RemoteException {
            return mPersonList.size();
        }

        @Override
        public List<Person> getPersonData() throws RemoteException {
            return mPersonList;
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
      return mManage;
    }

}
