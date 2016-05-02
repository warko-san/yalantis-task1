package ua.warko.yalantistask1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.warko.yalantistask1.GlobalState;
import ua.warko.yalantistask1.R;
import ua.warko.yalantistask1.RecyclerItemClickListener;
import ua.warko.yalantistask1.activities.CardActivity;
import ua.warko.yalantistask1.adapters.ContentAdapter;
import ua.warko.yalantistask1.models.ContentDataModel;

/**
 * Created by Warko on 12.04.2016.
 */
public class RecyclerFragment extends Fragment {
    private static final String KEY_FILE = "file";
    private ContentDataModel mModel;
    private Intent mIntent;

    public static Fragment newInstance(String file) {
        RecyclerFragment fragment = new RecyclerFragment();
        Bundle args = new Bundle();
        args.putString(KEY_FILE, file);
        fragment.setArguments(args);
        return fragment;
    }

    public String getPage() {
        return (getArguments().getString(KEY_FILE));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_fragment_layout, container, false);
        mIntent = new Intent(getContext(), CardActivity.class);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.tab_recycler);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (getPage().equals(getString(R.string.processing_flag))) {
                            mModel = GlobalState.processingList().get(position);
                            mIntent.putExtra(getString(R.string.model_flag), mModel);
                            view.getContext().startActivity(mIntent);
                        } else if (getPage().equals(getString(R.string.done_flag))) {
                            mModel = GlobalState.doneList().get(position);
                            mIntent.putExtra(getString(R.string.model_flag), mModel);
                            view.getContext().startActivity(mIntent);
                        }
                    }
                })
        );

        RecyclerView.Adapter adapter;
        if (getPage().equals(getString(R.string.processing_flag))) {
            adapter = new ContentAdapter(getContext(), GlobalState.processingList());
            recyclerView.setAdapter(adapter);
        } else if (getPage().equals(getString(R.string.done_flag))) {
            adapter = new ContentAdapter(getContext(), GlobalState.doneList());
            recyclerView.setAdapter(adapter);
        }


        return view;
    }


}