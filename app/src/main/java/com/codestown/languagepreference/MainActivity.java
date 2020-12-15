package com.codestown.languagepreference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView languageTextView;
    String language = " ";
    SharedPreferences sharedPreferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.english:
                selectLanguage("English");
                return true;
            case R.id.urdu:
                selectLanguage("Urdu");
                return true;
            case R.id.pashto:
                selectLanguage("Pashto");
                return true;
            case R.id.punjabi:
                selectLanguage("Punjabi");
                return true;
            case R.id.spanish:
                selectLanguage("Spanish");
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPreferences = this.getSharedPreferences("com.codestown.languagepreference", Context.MODE_PRIVATE);
        language = sharedPreferences.getString("language", "Error");
        languageTextView = findViewById(R.id.languageTextView);
        if (language.equals("Error")) {
            new AlertDialog.Builder(this).setTitle("Select your Language")
                    .setMessage("We need your language preference please select accordingly")
                    .setIcon(android.R.drawable.ic_btn_speak_now)
                    .setPositiveButton("English", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            language = "English";
                            selectLanguage(language);
                        }
                    })
                    .setNegativeButton("Urdu", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            language = "Urdu";
                            selectLanguage(language);
                        }
                    }).show();
        } else {
            selectLanguage(language);
        }

    }

    public void selectLanguage(String language) {
        sharedPreferences.edit().putString("language", language).apply();
        languageTextView.setText(language);
    }
}