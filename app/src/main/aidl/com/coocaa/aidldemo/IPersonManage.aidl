// IPersonManage.aidl
package com.coocaa.aidldemo;
import com.coocaa.aidldemo.Person;
// Declare any non-default types here with import statements

interface IPersonManage {
     void addPerson(inout Person p);
     void deletePerson(inout Person p);
     int getPersonSize();
     List<Person> getPersonData();
}
