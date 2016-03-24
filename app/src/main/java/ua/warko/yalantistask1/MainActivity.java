package ua.warko.yalantistask1;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private ArrayList sPictures;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        RecyclerView sRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        sRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        sRecyclerView.setLayoutManager(mLayoutManager);

        fetchPictures();
        RecyclerView.Adapter sAdapter = new PictureAdapter(MainActivity.this, sPictures);
        sRecyclerView.setAdapter(sAdapter);
    }

    // Fills our array with data
    private void fetchPictures() {
        sPictures = new ArrayList();
        Collections.addAll(sPictures, getResources().getStringArray(R.array.imageArray));


    }

    // Makes back arrow on actionBar to close the app
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showToast(View view) {
        Toast toast = Toast.makeText(getApplicationContext(),
                view.getClass().getSimpleName(), Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


}
