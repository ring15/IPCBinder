// IPersonAidlInterface.aidl
package com.founq.sdk.ipcbinder;
import com.founq.sdk.ipcbinder.Student;

// Declare any non-default types here with import statements

interface IPersonAidlInterface {
    List<Student> getStudentList();
    //定向tag
    void addStudent(in Student student);
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);
}
