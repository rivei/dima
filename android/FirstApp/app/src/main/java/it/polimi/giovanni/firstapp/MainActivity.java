package it.polimi.giovanni.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_linear);
    }

    public void clickMe(View view){
        System.out.println("Click Me");
    }

    public void clickYou(View view){
        System.out.println("Click You");
    }
}
