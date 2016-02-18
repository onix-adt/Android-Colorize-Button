package com.onix.control.colorizebutton.demo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.onix.control.colorizebutton.demo.R;
import com.onix.control.colorizebutton.demo.adapter.HomeListAdapter;


public class HomeFragment extends Fragment {

    private TextView mTextTitle;
    private SelectMenuListener mCallback;

    public interface SelectMenuListener {
        public void onSelectMenuItem(int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frgm_home, container, false);
        ListView mListView = (ListView) view.findViewById(R.id.lview);
        mTextTitle = (TextView) view.findViewById(R.id.title_text);

        Spanned str = Html.fromHtml(getActivity().getString(R.string.home_title_text));
        mTextTitle.setText(str);
        mTextTitle.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    Spanned str = Html.fromHtml(getActivity().getString(R.string.home_title_text_click));
                    mTextTitle.setText(str);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Spanned str = Html.fromHtml(getActivity().getString(R.string.home_title_text));
                    mTextTitle.setText(str);
                    return true;
                }
                return false;
            }

        });

        HomeListAdapter adapter = new HomeListAdapter(getContext(), getActivity().getResources().getStringArray(R.array.home_menu_items));
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.onSelectMenuItem(position);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (SelectMenuListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnSelectMenuItem");
        }
    }

}
