package com.test.bestrouteplannerfordhakacity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class Input extends AppCompatActivity {

    private  static RadioGroup radioGroup;
    private static RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.check(R.id.costButton);
    }

    public void onClickResult(View v)
    {

        Spinner sourceSpinner =(Spinner) findViewById(R.id.sourceSpinner);
        String src = sourceSpinner.getSelectedItem().toString();

        Spinner destSpinner =(Spinner) findViewById(R.id.destSpinner);
        String dest = destSpinner.getSelectedItem().toString();


        int selectedButton = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedButton);

        String method = radioButton.getText().toString();


        Intent intent = new Intent(Input.this, Result.class);
        intent.putExtra("src", src);
        intent.putExtra("dest", dest);
        intent.putExtra("method", method);
        startActivity(intent);
    }


}
