package ua.warko.yalantistask1.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import ua.warko.yalantistask1.GlobalState;
import ua.warko.yalantistask1.R;
import ua.warko.yalantistask1.activities.CardActivity;
import ua.warko.yalantistask1.adapters.ListAdapter;
import ua.warko.yalantistask1.models.ContentDataModel;

/**
 * Created by Warko on 12.04.2016.
 */
public class ListFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment_layout, container, false);
        ListView listView = (ListView) view.findViewById(R.id.tab_listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), CardActivity.class);
                ContentDataModel model = (ContentDataModel) parent.getAdapter().getItem(position);
                intent.putExtra("model", model);
                view.getContext().startActivity(intent);
            }
        });
        final FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == SCROLL_STATE_TOUCH_SCROLL || scrollState == SCROLL_STATE_FLING) {
                    fab.hide();
                } else {
                    fab.show();
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
            }
        });
        listView.setAdapter(new ListAdapter(getContext(), GlobalState.waitingList()));
        return view;
    }

}
