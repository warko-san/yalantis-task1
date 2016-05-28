package ua.warko.yalantistask1.ui;

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

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.warko.yalantistask1.R;
import ua.warko.yalantistask1.ui.adapters.PictureAdapter;
import ua.warko.yalantistask1.pojo.ContentDataModel;
import ua.warko.yalantistask1.pojo.Files;
import ua.warko.yalantistask1.util.Constants;
import ua.warko.yalantistask1.util.DateFormatter;

public class CardActivity extends AppCompatActivity {
    @BindView(R.id.cardActivityTitle)
    TextView mCardHeader;
    @BindView(R.id.cardMarker)
    TextView mMark;
    @BindView(R.id.cardCreationDate)
    TextView mCreationDate;
    @BindView(R.id.cardRegisteredDate)
    TextView mRegisteredDate;
    @BindView(R.id.cardSolvingDate)
    TextView mDeadline;
    @BindView(R.id.cardMainText)
    TextView mMainText;
    @BindView(R.id.cardPerformer)
    TextView mPerformer;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private ContentDataModel mModel;
    private List<Files> mPictures;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.card_activity);
        ButterKnife.bind(this);
        mModel = Parcels.unwrap(getIntent().getParcelableExtra(Constants.MODEL_FLAG));
        if (mModel != null) {
            fetchData(mModel);
        }
        initiateToolbar();
        initiateRecyclerView();
    }

    //fills activity with actual data
    private void fetchData(ContentDataModel model) {
        mCardHeader.setText(model.getTitle());
        mMark.setText(model.getState().getName());
        if (model.getStartDate() == 0) {
            mRegisteredDate.setText("");
        } else {
            mRegisteredDate.setText(DateFormatter.getNormalDate(model.getStartDate()));
        }
        if (model.getDeadline() == 0) {
            mDeadline.setText("");
        } else {
            mDeadline.setText(DateFormatter.getNormalDate(model.getDeadline()));
        }
        mCreationDate.setText(DateFormatter.getNormalDate(model.getCreatedDate()));
        mMainText.setText(model.getBody());
        if (model.getPerformers().size() != 0) {
            mPerformer.setText(model.getPerformers().get(0).getOrganization());
        } else
            mPerformer.setText("");

    }

    // Fills our array with data
    private void fetchPictures() {
        if (mModel.getFiles() != null) {
            mPictures = mModel.getFiles();
        }
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
        setSupportActionBar(mToolbar);
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.arrow_left);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            if (mModel.getTicketId() != null) {
                getSupportActionBar().setTitle(mModel.getTicketId());
            } else
                getSupportActionBar().setTitle("");
        }
    }

    private void initiateRecyclerView() {
        if (mRecyclerView != null) {
            mRecyclerView.setHasFixedSize(true);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,
                    LinearLayoutManager.HORIZONTAL, false);
            mRecyclerView.setLayoutManager(layoutManager);
            fetchPictures();
            RecyclerView.Adapter adapter = new PictureAdapter(CardActivity.this, mPictures);
            mRecyclerView.setAdapter(adapter);
        }
    }
}