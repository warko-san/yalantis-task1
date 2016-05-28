package ua.warko.yalantistask1.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import ua.warko.yalantistask1.R;
import ua.warko.yalantistask1.ui.adapters.RealmContentAdapter;
import ua.warko.yalantistask1.pojo.ContentDataModel;
import ua.warko.yalantistask1.presenter.DataManager;
import ua.warko.yalantistask1.util.Constants;
import ua.warko.yalantistask1.util.RecyclerItemClickListener;

/**
 * Created by Warko on 12.04.2016.
 */
public class RecyclerFragment extends Fragment {
    @BindView(R.id.tab_recycler)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout mCoordinatorLayout;
    private static final String KEY_FILE = "file";
    private ContentDataModel mModel;
    private Intent mIntent;
    private RealmResults<ContentDataModel> mData;
    private DataManager mDataManager;


    public static Fragment newInstance(int tabIndex) {
        RecyclerFragment fragment = new RecyclerFragment();
        Bundle args = new Bundle();
        args.putInt(KEY_FILE, tabIndex);
        fragment.setArguments(args);
        return fragment;
    }

    public int getPage() {
        return (getArguments().getInt(KEY_FILE));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_fragment_layout, container, false);
        ButterKnife.bind(this, view);
        mIntent = new Intent(getContext(), CardActivity.class);
        mDataManager = new DataManager();
        loadData();
        initRecyclerView();
        initSwipeToRefresh();
        return view;
    }

    private void initSwipeToRefresh() {
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mDataManager.refreshRealm();
                loadData();
                initSnackbar();
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });
    }

    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        mModel = mData.get(position);
                        switch (getPage()) {
                            case Constants.STATE_PROCESSING:
                                mIntent.putExtra(Constants.MODEL_FLAG, Parcels.wrap(mModel));
                                getActivity().startActivity(mIntent);
                                break;
                            case Constants.STATE_DONE:
                                mIntent.putExtra(Constants.MODEL_FLAG, Parcels.wrap(mModel));
                                getActivity().startActivity(mIntent);
                                break;
                            case Constants.STATE_PENDING:
                                mIntent.putExtra(Constants.MODEL_FLAG, Parcels.wrap(mModel));
                                getActivity().startActivity(mIntent);
                                break;
                        }
                    }
                })
        );
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalCards = mRecyclerView.getAdapter().getItemCount();
                if (totalCards > 0 && dy > 0) {
                    int currentPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                            .findLastVisibleItemPosition();
                    if (currentPosition >= (totalCards - 1)) {
                        int currentPage = totalCards / Constants.QUERY_AMOUNT;
                        int offset = currentPage * Constants.QUERY_AMOUNT;
                        switch (getPage()) {
                            case Constants.STATE_PROCESSING:
                                mDataManager.loadMoreCards(Constants.TAB_ONE_STATE,
                                        Constants.QUERY_AMOUNT, offset);

                                break;
                            case Constants.STATE_DONE:
                                mDataManager.loadMoreCards(Constants.TAB_TWO_STATE,
                                        Constants.QUERY_AMOUNT, offset);

                                break;
                            case Constants.STATE_PENDING:
                                mDataManager.loadMoreCards(Constants.TAB_THREE_STATE,
                                        Constants.QUERY_AMOUNT, offset);

                                break;
                        }
                        initSnackbar();
                    }
                }
            }
        });
    }

    private void loadData() {
        mDataManager.loadData(Constants.STATE_PROCESSING);
        mDataManager.loadData(Constants.STATE_DONE);
        mDataManager.loadData(Constants.STATE_PENDING);
        RealmContentAdapter adapter;
        if (getPage() == Constants.STATE_PROCESSING) {

            mData = mDataManager.findByState(ContentDataModel.class,
                    Constants.STATE_PROCESSING);
            adapter = new RealmContentAdapter(getContext(), mData);
            mRecyclerView.setAdapter(adapter);
        } else if (getPage() == Constants.STATE_DONE) {

            mData = mDataManager.findByState(ContentDataModel.class,
                    Constants.STATE_DONE);
            adapter = new RealmContentAdapter(getContext(), mData);
            mRecyclerView.setAdapter(adapter);
        } else {

            mData = mDataManager.findByState(ContentDataModel.class,
                    Constants.STATE_PENDING);
            adapter = new RealmContentAdapter(getContext(), mData);
            mRecyclerView.setAdapter(adapter);
        }
    }

    private void initSnackbar() {
        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, R.string.snackbar_title,
                Snackbar.LENGTH_SHORT);
        Snackbar.SnackbarLayout snackView = (Snackbar.SnackbarLayout) snackbar.getView();
        snackView.addView(new ProgressBar(getContext()));
        snackbar.show();
    }
}


