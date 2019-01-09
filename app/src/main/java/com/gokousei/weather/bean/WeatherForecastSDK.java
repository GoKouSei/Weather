package com.gokousei.weather.bean;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.gokousei.weather.BR;

import java.util.List;

public class WeatherForecastSDK implements Observable {
    /**
     * daily_forecast : [{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2018-05-30","hum":"20","mr":"20:06","ms":"05:25","pcpn":"0.0","pop":"0","sr":"04:49","ss":"19:36","tmp_max":"33","tmp_min":"18","uv_index":"7","vis":"10","wind_deg":"0","wind_dir":"无持续风向","wind_sc":"1-2","wind_spd":"5"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2018-05-31","hum":"31","mr":"21:00","ms":"06:06","pcpn":"0.0","pop":"0","sr":"04:49","ss":"19:37","tmp_max":"35","tmp_min":"19","uv_index":"8","vis":"20","wind_deg":"179","wind_dir":"南风","wind_sc":"1-2","wind_spd":"7"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2018-06-01","hum":"23","mr":"21:50","ms":"06:51","pcpn":"0.0","pop":"0","sr":"04:48","ss":"19:37","tmp_max":"36","tmp_min":"21","uv_index":"9","vis":"20","wind_deg":"251","wind_dir":"西南风","wind_sc":"3-4","wind_spd":"19"}]
     * basic : {"admin_area":"北京","cid":"CN101010100","cnty":"中国","lat":"39.90498734","location":"北京","lon":"116.4052887","parent_city":"北京","tz":"+8.00"}
     * status : ok
     * update : {"loc":"2018-05-30 17:51","utc":"2018-05-30 09:51"}
     */

    private BasicBean basic;
    private String status;
    private UpdateBean update;
    private List<DailyForecastBean> daily_forecast;
    private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();

    private static WeatherForecastSDK weatherForecast;

    public WeatherForecastSDK() {
    }

    public static WeatherForecastSDK getWeatherForecast() {
        if (weatherForecast == null) {
            synchronized (WeatherForecastSDK.class) {
                if (weatherForecast == null) {
                    weatherForecast = new WeatherForecastSDK();
                }
            }
        }
        return weatherForecast;
    }

    @Bindable
    public BasicBean getBasic() {
        return basic;
    }

    public void setBasic(BasicBean basic) {
        this.basic = basic;
        notifyChange(BR.basic);
    }

    @Bindable
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
        notifyChange(BR.status);
    }

    @Bindable
    public UpdateBean getUpdate() {
        return update;
    }

    public void setUpdate(UpdateBean update) {
        this.update = update;
        notifyChange(BR.update);
    }

    @Bindable
    public List<DailyForecastBean> getDaily_forecast() {
        return daily_forecast;
    }

    public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
        this.daily_forecast = daily_forecast;
        notifyChange(BR.daily_forecast);
    }

    private synchronized void notifyChange(int propertyId) {
        if (propertyChangeRegistry == null) {
            propertyChangeRegistry = new PropertyChangeRegistry();
        }
        propertyChangeRegistry.notifyChange(this, propertyId);
    }

    @Override
    public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (propertyChangeRegistry == null) {
            propertyChangeRegistry = new PropertyChangeRegistry();
        }
        propertyChangeRegistry.add(callback);

    }

    @Override
    public synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        if (propertyChangeRegistry != null) {
            propertyChangeRegistry.remove(callback);
        }
    }

    public static class BasicBean implements Observable {
        /**
         * admin_area : 北京
         * cid : CN101010100
         * cnty : 中国
         * lat : 39.90498734
         * location : 北京
         * lon : 116.4052887
         * parent_city : 北京
         * tz : +8.00
         */

        private String admin_area;
        private String cid;
        private String cnty;
        private String lat;
        private String location;
        private String lon;
        private String parent_city;
        private String tz;
        private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();

        @Bindable
        public String getAdmin_area() {
            return admin_area;
        }

        public void setAdmin_area(String admin_area) {
            this.admin_area = admin_area;
            notifyChange(BR.admin_area);
        }

        @Bindable
        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
            notifyChange(BR.cid);
        }

        @Bindable
        public String getCnty() {
            return cnty;
        }

        public void setCnty(String cnty) {
            this.cnty = cnty;
            notifyChange(BR.cnty);
        }

        @Bindable
        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
            notifyChange(BR.lat);
        }

        @Bindable
        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
            notifyChange(BR.location);
        }

        @Bindable
        public String getLon() {
            return lon;
        }

        public void setLon(String lon) {
            this.lon = lon;
            notifyChange(BR.lon);
        }

        @Bindable
        public String getParent_city() {
            return parent_city;
        }

        public void setParent_city(String parent_city) {
            this.parent_city = parent_city;
            notifyChange(BR.parent_city);
        }

        @Bindable
        public String getTz() {
            return tz;
        }

        public void setTz(String tz) {
            this.tz = tz;
            notifyChange(BR.tz);
        }

        private synchronized void notifyChange(int propertyId) {
            if (propertyChangeRegistry == null) {
                propertyChangeRegistry = new PropertyChangeRegistry();
            }
            propertyChangeRegistry.notifyChange(this, propertyId);
        }

        @Override
        public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
            if (propertyChangeRegistry == null) {
                propertyChangeRegistry = new PropertyChangeRegistry();
            }
            propertyChangeRegistry.add(callback);

        }

        @Override
        public synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
            if (propertyChangeRegistry != null) {
                propertyChangeRegistry.remove(callback);
            }
        }
    }

    public static class UpdateBean implements Observable {
        /**
         * loc : 2018-05-30 17:51
         * utc : 2018-05-30 09:51
         */

        private String loc;
        private String utc;
        private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();

        @Bindable
        public String getLoc() {
            return loc;
        }

        public void setLoc(String loc) {
            this.loc = loc;
            notifyChange(BR.loc);
        }

        @Bindable
        public String getUtc() {
            return utc;
        }

        public void setUtc(String utc) {
            this.utc = utc;
            notifyChange(BR.utc);
        }

        private synchronized void notifyChange(int propertyId) {
            if (propertyChangeRegistry == null) {
                propertyChangeRegistry = new PropertyChangeRegistry();
            }
            propertyChangeRegistry.notifyChange(this, propertyId);
        }

        @Override
        public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
            if (propertyChangeRegistry == null) {
                propertyChangeRegistry = new PropertyChangeRegistry();
            }
            propertyChangeRegistry.add(callback);

        }

        @Override
        public synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
            if (propertyChangeRegistry != null) {
                propertyChangeRegistry.remove(callback);
            }
        }
    }

    public static class DailyForecastBean implements Observable {
        /**
         * cond_code_d : 100
         * cond_code_n : 100
         * cond_txt_d : 晴
         * cond_txt_n : 晴
         * date : 2018-05-30
         * hum : 20
         * mr : 20:06
         * ms : 05:25
         * pcpn : 0.0
         * pop : 0
         * sr : 04:49
         * ss : 19:36
         * tmp_max : 33
         * tmp_min : 18
         * uv_index : 7
         * vis : 10
         * wind_deg : 0
         * wind_dir : 无持续风向
         * wind_sc : 1-2
         * wind_spd : 5
         */

        private String cond_code_d;
        private String cond_code_n;
        private String cond_txt_d;
        private String cond_txt_n;
        private String date;
        private String hum;
        private String mr;
        private String ms;
        private String pcpn;
        private String pop;
        private String sr;
        private String ss;
        private String tmp_max;
        private String tmp_min;
        private String uv_index;
        private String vis;
        private String wind_deg;
        private String wind_dir;
        private String wind_sc;
        private String wind_spd;
        private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();

        @Bindable
        public String getCond_code_d() {
            return cond_code_d;
        }

        public void setCond_code_d(String cond_code_d) {
            this.cond_code_d = cond_code_d;
            notifyChange(BR.cond_code_d);
        }

        @Bindable
        public String getCond_code_n() {
            return cond_code_n;
        }

        public void setCond_code_n(String cond_code_n) {
            this.cond_code_n = cond_code_n;
            notifyChange(BR.cond_code_n);
        }

        @Bindable
        public String getCond_txt_d() {
            return cond_txt_d;
        }

        public void setCond_txt_d(String cond_txt_d) {
            this.cond_txt_d = cond_txt_d;
            notifyChange(BR.cond_txt_d);
        }

        @Bindable
        public String getCond_txt_n() {
            return cond_txt_n;
        }

        public void setCond_txt_n(String cond_txt_n) {
            this.cond_txt_n = cond_txt_n;
            notifyChange(BR.cond_txt_n);
        }

        @Bindable
        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
            notifyChange(BR.date);
        }

        @Bindable
        public String getHum() {
            return hum;
        }

        public void setHum(String hum) {
            this.hum = hum;
            notifyChange(BR.hum);
        }

        @Bindable
        public String getMr() {
            return mr;
        }

        public void setMr(String mr) {
            this.mr = mr;
            notifyChange(BR.mr);
        }

        @Bindable
        public String getMs() {
            return ms;
        }

        public void setMs(String ms) {
            this.ms = ms;
            notifyChange(BR.ms);
        }

        @Bindable
        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
            notifyChange(BR.pcpn);
        }

        @Bindable
        public String getPop() {
            return pop;
        }

        public void setPop(String pop) {
            this.pop = pop;
            notifyChange(BR.pop);
        }

        @Bindable
        public String getSr() {
            return sr;
        }

        public void setSr(String sr) {
            this.sr = sr;
            notifyChange(BR.sr);
        }

        @Bindable
        public String getSs() {
            return ss;
        }

        public void setSs(String ss) {
            this.ss = ss;
            notifyChange(BR.ss);
        }

        @Bindable
        public String getTmp_max() {
            return tmp_max;
        }

        public void setTmp_max(String tmp_max) {
            this.tmp_max = tmp_max;
            notifyChange(BR.tmp_max);
        }

        @Bindable
        public String getTmp_min() {
            return tmp_min;
        }

        public void setTmp_min(String tmp_min) {
            this.tmp_min = tmp_min;
            notifyChange(BR.tmp_min);
        }

        @Bindable
        public String getUv_index() {
            return uv_index;
        }

        public void setUv_index(String uv_index) {
            this.uv_index = uv_index;
            notifyChange(BR.uv_index);
        }

        @Bindable
        public String getVis() {
            return vis;
        }

        public void setVis(String vis) {
            this.vis = vis;
            notifyChange(BR.vis);
        }

        @Bindable
        public String getWind_deg() {
            return wind_deg;
        }

        public void setWind_deg(String wind_deg) {
            this.wind_deg = wind_deg;
            notifyChange(BR.wind_deg);
        }

        @Bindable
        public String getWind_dir() {
            return wind_dir;
        }

        public void setWind_dir(String wind_dir) {
            this.wind_dir = wind_dir;
            notifyChange(BR.wind_dir);
        }

        @Bindable
        public String getWind_sc() {
            return wind_sc;
        }

        public void setWind_sc(String wind_sc) {
            this.wind_sc = wind_sc;
            notifyChange(BR.wind_sc);
        }

        @Bindable
        public String getWind_spd() {
            return wind_spd;
        }

        public void setWind_spd(String wind_spd) {
            this.wind_spd = wind_spd;
            notifyChange(BR.wind_spd);
        }

        private synchronized void notifyChange(int propertyId) {
            if (propertyChangeRegistry == null) {
                propertyChangeRegistry = new PropertyChangeRegistry();
            }
            propertyChangeRegistry.notifyChange(this, propertyId);
        }

        @Override
        public synchronized void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
            if (propertyChangeRegistry == null) {
                propertyChangeRegistry = new PropertyChangeRegistry();
            }
            propertyChangeRegistry.add(callback);

        }

        @Override
        public synchronized void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
            if (propertyChangeRegistry != null) {
                propertyChangeRegistry.remove(callback);
            }
        }
    }
}
