package it.polimi.giovanni.firstapp.app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import it.polimi.giovanni.firstapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_linear);
        Button button1 = (Button) findViewById(R.id.button1);

        final MainActivity self = this;

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText) findViewById(R.id.editText1);
                String text = editText.getText().toString();

                TextView textView = (TextView) findViewById(R.id.textView1);
                textView.setText(text);

                Intent intent = new Intent(self, ResultActivity.class);
                intent.putExtra("info", text);
                startActivity(intent);
            }
        });
    }


}
