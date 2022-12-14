/*
 *          Notification
 *
 *  Description: An activity of showing the notification of reminders
 *
 *
 * updated: August 3rd 2022
 */
package com.company.dementiacare.notificaion;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.company.dementiacare.R;

public class NotificationActivity extends AppCompatActivity {
    /*
     * Textview for the Title Page, MedicineName, MedicineDetails
     * */
    TextView patient;
    TextView medName;
    TextView medDosage;
    TextView medColor;
    TextView medType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_notification);
        super.onCreate(savedInstanceState);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        /*
         * Fetching details for medicine details from the notification
         * */
        Bundle extras = getIntent().getExtras();
        patient=findViewById(R.id.medicineDisplay_patient);
        medName=findViewById(R.id.medicineDisplay_name);
        medDosage=findViewById(R.id.medicineDisplay_dosage);
        medType=findViewById(R.id.medicineDisplay_type);
        medColor=findViewById(R.id.medicineDisplay_color);
        /*
         * Displaying the details
         * */

        patient.setText("For Patient:" + " " + extras.getString("patient"));
        medName.setText(extras.getString("name"));
        medDosage.setText("Dosage" + " " + extras.getString("dosage") + extras.getString("unit"));
        medColor.setText("Color" + " " + extras.getString("color"));
        medType.setText("Type" + " " + extras.getString("type"));
    }
}