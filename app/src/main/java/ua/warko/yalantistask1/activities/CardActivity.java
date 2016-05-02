package ua.warko.yalantistask1.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import ua.warko.yalantistask1.R;
import ua.warko.yalantistask1.adapters.PictureAdapter;
import ua.warko.yalantistask1.models.ContentDataModel;

public class CardActivity extends AppCompatActivity {
    private ArrayList mPictures;
    private TextView mCardHeader, mMark, mCreationDate, mRegisteredDate, mDeadline, mMainText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_activity);
        mCardHeader = (TextView) findViewById(R.id.cardActivityTitle);
        mMark = (TextView) findViewById(R.id.cardMarker);
        mCreationDate = (TextView) findViewById(R.id.cardCreationDate);
        mRegisteredDate = (TextView) findViewById(R.id.cardRegisteredDate);
        mDeadline = (TextView) findViewById(R.id.cardSolvingDate);
        mMainText = (TextView) findViewById(R.id.cardMainText);
        // check for available data
        Intent intent = getIntent();
        ContentDataModel model = intent.getParcelableExtra("model");
        if (model != null) {
            fetchData(model);
        }

        initiateToolbar();
        initiateRecyclerView();
    }

    //fills activity with actual data
    private void fetchData(ContentDataModel model) {
        mCardHeader.setText(model.getHeader());
        mMark.setText(model.getMarker());
        mRegisteredDate.setText(model.getRegisteredDate());
        mCreationDate.setText(model.getCreationDate());
        mDeadline.setText(model.getDeadline());
        mMainText.setText(model.getMainText());
    }

    // Fills our array with data
    private void fetchPictures() {
        mPictures = new ArrayList();
        Collections.addAll(mPictures, getResources().getStringArray(R.array.imageArray));
    }

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

    private void initiateToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (toolbar != null) {
            toolbar.setNavigationIcon(R.drawable.arrow_left);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }
    }

    private void initiateRecyclerView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                    LinearLayoutManager.HORIZONTAL, false);
            recyclerView.setLayoutManager(layoutManager);
            fetchPictures();
            RecyclerView.Adapter adapter = new PictureAdapter(CardActivity.this, mPictures);
            recyclerView.setAdapter(adapter);
        }
    }
}