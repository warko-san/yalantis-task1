package ua.warko.yalantistask1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

//[Comment] Wrong toolbar and status bar colors.
public class MainActivity extends AppCompatActivity {
    private static ArrayList<PictureData> sPictures; //[Comment] Why static? It's a bad practice in Android. And use abstraction instead of realization
    private static RecyclerView sRecyclerView; //[Comment] The same.
    private RecyclerView.LayoutManager mLayoutManager;
    private static RecyclerView.Adapter sAdapter; //[Comment] Most of these fields should be local

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar(); //[Comment] Please optimize import
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        sRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        sRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        sRecyclerView.setLayoutManager(mLayoutManager);

        fetchPictures();
        sAdapter = new PictureAdapter(MainActivity.this, sPictures);
        sRecyclerView.setAdapter(sAdapter);
    }
    // Fills our array with data
    private void fetchPictures() {
        sPictures = new ArrayList<>();

        sPictures.add(new PictureData("cat")); //[Comment] Looks like hardcode. Please create <string-array />
        sPictures.add(new PictureData("dog"));
        sPictures.add(new PictureData("turtle"));
        sPictures.add(new PictureData("monkey"));
        sPictures.add(new PictureData("mouse"));

    }
    // Makes back arrow on actionBar to close the app
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish(); //[Comment] Without this
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showToastTextView(View view) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "TextView", Toast.LENGTH_SHORT); //[Comment] Hardcode view.getClass.getSimpleName
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    public void showToastImageView(View view) {
        Toast toast = Toast.makeText(getApplicationContext(),
                "ImageView", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
