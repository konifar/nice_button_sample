package com.konifar.nice_button_sample.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.konifar.nice_button_sample.R;
import com.konifar.nice_button_sample.api.FlickrApiClient;
import com.konifar.nice_button_sample.databinding.FragmentCatsBinding;
import com.konifar.nice_button_sample.databinding.ItemPhotoBinding;
import com.konifar.nice_button_sample.model.Photo;
import com.konifar.nice_button_sample.model.PhotoSource;
import com.konifar.nice_button_sample.widget.ListLoadingView;
import com.konifar.nice_button_sample.widget.OnLoadMoreScrollListener;

import java.util.ArrayList;

import javax.inject.Inject;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


public class CatsFragment extends BaseFragment {

    private static final String TAG = CatsFragment.class.getSimpleName();

    @Inject
    FlickrApiClient client;
    @Inject
    CompositeSubscription compositeSubscription;

    private FragmentCatsBinding binding;
    private PhotosArrayAdapter adapter;
    private ListLoadingView loadingFooter;

    public static CatsFragment newInstance() {
        CatsFragment fragment = new CatsFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentCatsBinding.inflate(inflater, container, false);
        initGridView();
        initSwipeRefresh();
        showList(1);
        return binding.getRoot();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }

    private void initSwipeRefresh() {
        binding.swipeRefresh.setColorSchemeResources(R.color.theme500);
        binding.swipeRefresh.setOnRefreshListener(() -> showList(1));
    }

    private void initGridView() {
        adapter = new PhotosArrayAdapter(getActivity());
        loadingFooter = new ListLoadingView(getActivity());
        binding.listView.addFooterView(loadingFooter);
        binding.listView.setAdapter(adapter);
        binding.listView.setOnItemClickListener((parent, view, position, id) -> {
            Photo photo = adapter.getItem(position);
//        PhotoDetailActivity.start(getActivity(), view, photo);
        });
    }

    private void showList(final int page) {
        if (page > 1) loadingFooter.switchVisible(true);

        Subscription sub = client.getCatPhotos(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        this::onLoadSuccess,
                        this::onLoadFailure
                );

        compositeSubscription.add(sub);
    }

    private void initListViewScrollListener() {
        binding.listView.setOnScrollListener(new OnLoadMoreScrollListener() {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                showList(page);
            }
        });
    }

    private void onLoadCompleted() {
        if (binding.swipeRefresh.isRefreshing()) {
            binding.swipeRefresh.setRefreshing(false);
            adapter.clear();
        }

        if (binding.loading.getVisibility() == View.VISIBLE) {
            initListViewScrollListener();
            binding.loading.setVisibility(View.GONE);
        }

        loadingFooter.switchVisible(false);
    }

    private void onLoadSuccess(PhotoSource photoSource) {
        adapter.addAll(photoSource.getPhotos());
        onLoadCompleted();
    }

    private void onLoadFailure(Throwable throwable) {
        Log.e(TAG, "Load failed. ", throwable);
        onLoadCompleted();
    }

    public class PhotosArrayAdapter extends ArrayAdapter<Photo> {

        private ItemPhotoBinding binding;

        public PhotosArrayAdapter(Context context) {
            super(context, R.layout.item_photo, new ArrayList<>());
        }

        @Override
        public View getView(int pos, View view, ViewGroup parent) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
            binding = DataBindingUtil.bind(view);

            binding.setPhoto(getItem(pos));
            initListeners(pos, view, parent);

            return view;
        }

        private void initListeners(final int pos, final View view, final ViewGroup parent) {
            binding.getRoot().setOnClickListener(v -> ((ListView) parent).performItemClick(view, pos, 0L));
        }

    }

}