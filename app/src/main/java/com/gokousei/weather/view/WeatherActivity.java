package com.gokousei.weather.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import com.gokousei.weather.R;
import com.gokousei.weather.adapter.LifesStyleAdapter;
import com.gokousei.weather.adapter.WeatherForecastAdapter;
import com.gokousei.weather.bean.Weather;
import com.gokousei.weather.controller.WeatherController;
import com.gokousei.weather.data.DataController;
import com.gokousei.weather.databinding.ActivityWeatherBinding;
import com.gokousei.weather.net.ApiRealize;
import com.gokousei.weather.net.observer.ObserverGeneral;
import com.gokousei.weather.net.observer.ObserverWithDialog;
import com.gokousei.weather.utils.bitmap.BlurDrawable;
import com.gokousei.weather.view.refreshlayout.OnRefreshListener;
import com.gokousei.weather.view.refreshlayout.RefreshLayout;

public class WeatherActivity extends BaseActivity {

    public static final String SHARED_PREFERENCES_KEY = "Weather";

    WeatherForecastAdapter forecastAdapter;
    LifesStyleAdapter lifesStyleAdapter;
    RecyclerView.LayoutManager layoutManager;
    Weather.HeWeather6Bean weatherBean;
    Weather weather;
    ActivityWeatherBinding binding;
    Context mContext = this;
    WeatherController mWeatherController;
    SearchView mSearchView;
    String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =
                DataBindingUtil.setContentView(this, R.layout.activity_weather);
        setSupportActionBar(binding.customToolbar);
        init();
    }

    void init() {
        mWeatherController = new WeatherController(mContext);
        location = DataController.getInstance().loadLocation(getApplicationContext());
        weather = DataController.getInstance().loadWeatherSP(mContext, SHARED_PREFERENCES_KEY);
        if (weather != null) {
            configData();
        } else {
            if (location != null && !location.isEmpty())
                ApiRealize.getWeather(new ObserverGeneral<Weather>() {
                    @Override
                    public void onSuccess(Weather weather) {
                        configData();
                        DataController.getInstance().saveWeatherSP(mContext, weather, SHARED_PREFERENCES_KEY);
                    }

                    @Override
                    public void onFinish(Status status) {
                        switch (status) {
                            case Complete:
                                break;
                            case Error:
                                break;
                        }
                    }
                }, location);
        }
        binding.scrollViewParent.setBackground(BlurDrawable.getInstance()
                .setAlpha(240)
                .BlurDrawable(mContext, getResources(),
                        BitmapFactory.decodeResource(getResources(), R.drawable.background)));
        RefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void doRefresh() {
                refresh();
            }

            @Override
            public void doLoadMore() {
            }
        });
    }

    void configData() {
        weatherBean = weather.getHeWeather6().get(0);
        binding.setWeather(weatherBean);
        forecastAdapter = new WeatherForecastAdapter(weatherBean.getDaily_forecast());
        lifesStyleAdapter = new LifesStyleAdapter(weatherBean.getLifestyle());
        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerViewWeatherForecast.setLayoutManager(layoutManager);
        binding.recyclerViewWeatherForecast.addItemDecoration(
                new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL));
        binding.recyclerViewWeatherForecast.setAdapter(forecastAdapter);
        layoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        binding.recyclerViewLifestyle.setLayoutManager(layoutManager);
        binding.recyclerViewLifestyle.setAdapter(lifesStyleAdapter);
        binding.weatherUI.setImageResource(mWeatherController.getWeatherUI(weatherBean.getNow().getCond_code()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        binding.city.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                supportInvalidateOptionsMenu();
            }
        });
        getMenuInflater().inflate(R.menu.weather_toolbar, menu);
        binding.citySelect.setHeight(binding.customToolbar.getHeight());
        binding.citySelect.setWidth(binding.customToolbar.getHeight());
        mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();
        mSearchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.citySelect.setVisibility(View.GONE);
                binding.back.setVisibility(View.VISIBLE);
            }
        });
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                binding.citySelect.setVisibility(View.VISIBLE);
                binding.back.setVisibility(View.GONE);
                return false;
            }
        });
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query)) {
                    confirm(mSearchView.getQuery().toString());
                    mSearchView.setQuery("", true);
                    mSearchView.setIconified(true);
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchView.setQuery("", false);
                mSearchView.setIconified(true);
            }
        });
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (binding.city.hasFocus()
                || !(binding.city.getText().toString().trim().length() == 0)) {
            menu.findItem(R.id.confirm).setVisible(true);
        } else {
            menu.findItem(R.id.confirm).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * @param item Menu
     * @return return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.confirm:
                if (!binding.city.getText().toString().equals("")) {
                    confirm(binding.city.getText().toString());
                } else {
                    binding.city.clearFocus();
                }
                break;
            case R.id.refresh:
                refresh();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    void confirm(final String city) {
        ApiRealize.getWeather(new ObserverWithDialog<Weather>(mContext) {
            @Override
            public void onSuccess(Weather weather) {
                weatherBean = weather.getHeWeather6().get(0);
                binding.setWeather(weatherBean);
                binding.weatherUI.setImageResource(mWeatherController.getWeatherUI(weatherBean.getNow().getCond_code()));
                forecastAdapter.updateData(weatherBean.getDaily_forecast());
                lifesStyleAdapter.updateData(weatherBean.getLifestyle());
                forecastAdapter.notifyDataSetChanged();
                lifesStyleAdapter.notifyDataSetChanged();
                DataController.getInstance().saveWeatherSP(mContext, weather, SHARED_PREFERENCES_KEY);
            }

            @Override
            public void onFinish(Status status) {
                switch (status) {
                    case Complete:
                        location = city;
                        DataController.getInstance().saveLocation(getApplicationContext(), city);
                        break;
                    case Error:
                        break;
                }
            }
        }, city);
    }

    void refresh() {
        ApiRealize.getWeather(new ObserverWithDialog<Weather>(mContext) {
            @Override
            public void onSuccess(Weather weather) {
                weatherBean = weather.getHeWeather6().get(0);
                binding.setWeather(weatherBean);
                binding.weatherUI.setImageResource(mWeatherController.getWeatherUI(weatherBean.getNow().getCond_code()));
                forecastAdapter.updateData(weatherBean.getDaily_forecast());
                lifesStyleAdapter.updateData(weatherBean.getLifestyle());
                forecastAdapter.notifyDataSetChanged();
                lifesStyleAdapter.notifyDataSetChanged();
                DataController.getInstance().saveWeatherSP(mContext, weather, SHARED_PREFERENCES_KEY);
            }

            @Override
            public void onFinish(Status status) {
                switch (status) {
                    case Complete:
                        break;
                    case Error:
                        break;
                }
            }
        }, location);
    }
}