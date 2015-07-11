package com.jpdevs.spotifystreamer.activities.search;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jpdevs.spotifystreamer.model.ParcelableArtist;
import com.jpdevs.spotifystreamer.spotify.ArtistsSearchTask;
import com.jpdevs.spotifystreamer.R;
import com.jpdevs.spotifystreamer.spotify.SpotifyController;
import com.jpdevs.spotifystreamer.utils.SimpleDividerItemDecoration;

public class MainActivityFragment extends Fragment {
    private static final String DATA_SEARCH_RESULTS = "current_artists_matching";

    private ParcelableArtist[] mSearchResults;

    private View mNoResults;
    private TextView mNoResultMsg;
    private ImageView mNoResultsImg;
    private RecyclerView mSearchResultsList;
    private ArtistsSearchAdapter mSearchAdapter;
    private SpotifyController mSpotifyController;

    public MainActivityFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        SearchView searchBox = (SearchView) rootView.findViewById(R.id.search_box);
        mNoResults = rootView.findViewById(R.id.no_results_container);
        mSearchResultsList = (RecyclerView) rootView.findViewById(R.id.search_results);
        mSearchResultsList.setLayoutManager(new LinearLayoutManager(getActivity()));
        mSearchResultsList.addItemDecoration(new SimpleDividerItemDecoration(
                getActivity().getResources().getDrawable(R.drawable.line_divider)));
        mSearchAdapter = new ArtistsSearchAdapter();
        mSearchResultsList.setAdapter(mSearchAdapter);
        mSpotifyController = new SpotifyController();
        mNoResultMsg = (TextView) rootView.findViewById(R.id.no_results_msg);
        mNoResultsImg = (ImageView) rootView.findViewById(R.id.no_results_img);

        final boolean isRecreating = savedInstanceState != null;
        if(isRecreating){
            Parcelable[] parcels = savedInstanceState.getParcelableArray(DATA_SEARCH_RESULTS);

            if(parcels != null) {
                mSearchResults = new ParcelableArtist[parcels.length];
                for (int i = 0; i < parcels.length; ++i){
                    mSearchResults[i] = (ParcelableArtist) parcels[i];
                }
            }
        }


        searchBox.setOnQueryTextListener(new ArtistQueryListener(new ArtistQueryListener.SearchQueryListener() {
            private boolean mflag = isRecreating;
            private String mCurrentQuery;

            @Override
            public void performSearch(String query) {
                mCurrentQuery = query;

                // this flag prevents from trying to search when recreating the fragment because of
                // change of state. It instead uses the search results stored on the savedState
                if (mflag) {
                    mflag = false;
                    update(mSearchResults);
                    return;
                }

                mSpotifyController.getArtistsSearchTask(new ArtistsSearchTask.ArtistSearchListener() {
                    @Override
                    public void reportSearchResults(ParcelableArtist[] artistsFound) {
                        mSearchResults = artistsFound;
                        update(artistsFound);
                    }
                }).execute(query);
            }

            private void update(ParcelableArtist[] artistsFound) {
                if (artistsFound != null && artistsFound.length > 0) {
                    mNoResults.setVisibility(View.GONE);
                    mSearchResultsList.setVisibility(View.VISIBLE);

                    mSearchAdapter.updateArtists(artistsFound);
                } else {
                    // when no results show the appropriate message on the screen
                    mNoResults.setVisibility(View.VISIBLE);
                    mSearchResultsList.setVisibility(View.GONE);

                    if (TextUtils.isEmpty(mCurrentQuery)) {
                        mNoResultMsg.setText(getString(R.string.lets_search));
                        mNoResultsImg.setImageDrawable(getResources().getDrawable(R.drawable.music));
                    } else {
                        mNoResultMsg.setText(String.format("%s %s", getString(R.string.no_result_msg), mCurrentQuery));
                        mNoResultsImg.setImageDrawable(getResources().getDrawable(R.drawable.broken_guitar));
                    }
                }
            }
        }));

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArray(DATA_SEARCH_RESULTS, mSearchResults);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        mNoResults = null;
        mSearchResultsList = null;
        mSearchAdapter = null;
        mSpotifyController = null;
        mNoResultMsg = null;
        mNoResultsImg = null;
    }
}
