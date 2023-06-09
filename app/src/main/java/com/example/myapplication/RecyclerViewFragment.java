package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
     * Demonstrates the use of {@link RecyclerView} with a {@link LinearLayoutManager} and a
     * {@link GridLayoutManager}.
     */
    public class RecyclerViewFragment extends Fragment {

        private static final String TAG = "RecyclerViewFragment";
        private static final String KEY_LAYOUT_MANAGER = "layoutManager";
        private static final int SPAN_COUNT = 2;
        private static final int DATASET_COUNT = 60;

        private enum LayoutManagerType {
            GRID_LAYOUT_MANAGER,
            LINEAR_LAYOUT_MANAGER
        }

        protected LayoutManagerType mCurrentLayoutManagerType;

        protected RadioButton mLinearLayoutRadioButton;
        protected RadioButton mGridLayoutRadioButton;

        protected RecyclerView mRecyclerView;
        protected RecycleViewAdaptor mAdapter;
        protected RecyclerView.LayoutManager mLayoutManager;
         protected ArrayList<Character> letters;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            // Initialize dataset, this data would usually come from a local content provider or
            // remote server.
            initDataset();
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_first, container, false);
            rootView.setTag(TAG);

            // BEGIN_INCLUDE(initializeRecyclerView)
            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.inputsView);

            // LinearLayoutManager is used here, this will layout the elements in a similar fashion
            // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
            // elements are laid out.
            mLayoutManager = new LinearLayoutManager(getActivity());

            mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

            if (savedInstanceState != null) {
                // Restore saved layout manager type.
                mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                        .getSerializable(KEY_LAYOUT_MANAGER);
            }
            setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

            mAdapter = new RecycleViewAdaptor(letters);
            // Set CustomAdapter as the adapter for RecyclerView.
            mRecyclerView.setAdapter(mAdapter);
            // END_INCLUDE(initializeRecyclerView)

            return rootView;
        }

        /**
         * Set RecyclerView's LayoutManager to the one given.
         *
         * @param layoutManagerType Type of layout manager to switch to.
         */
        public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
            int scrollPosition = 0;

            // If a layout manager has already been set, get current scroll position.
            if (mRecyclerView.getLayoutManager() != null) {
                scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                        .findFirstCompletelyVisibleItemPosition();
            }

            switch (layoutManagerType) {
                case GRID_LAYOUT_MANAGER:
                    mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                    mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                    break;
                case LINEAR_LAYOUT_MANAGER:
                    mLayoutManager = new LinearLayoutManager(getActivity());
                    mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                    break;
                default:
                    mLayoutManager = new LinearLayoutManager(getActivity());
                    mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
            }

            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.scrollToPosition(scrollPosition);
        }

        @Override
        public void onSaveInstanceState(Bundle savedInstanceState) {
            // Save currently selected layout manager.
            savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
            super.onSaveInstanceState(savedInstanceState);
        }

        /**
         * Generates Strings for RecyclerView's adapter. This data would usually come
         * from a local content provider or remote server.
         */
        private void initDataset() {

        }
}
