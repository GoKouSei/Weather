package com.gokousei.weather.bean;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.gokousei.weather.BR;

import java.util.List;

public class WeatherForecast {
    private List<HeWeather6Bean> HeWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean extends BaseObservable {
        /**
         * basic : {"cid":"CN101010100","location":"北京","parent_city":"北京","admin_area":"北京","cnty":"中国","lat":"39.90498734","lon":"116.4052887","tz":"+8.00"}
         * update : {"loc":"2018-12-31 14:56","utc":"2018-12-31 06:56"}
         * status : ok
         * daily_forecast : [{"cond_code_d":"101","cond_code_n":"101","cond_txt_d":"多云","cond_txt_n":"多云","date":"2018-12-31","hum":"19","mr":"01:38","ms":"13:18","pcpn":"0.0","pop":"0","pres":"1045","sr":"07:35","ss":"16:59","tmp_max":"-2","tmp_min":"-10","uv_index":"2","vis":"20","wind_deg":"184","wind_dir":"南风","wind_sc":"1-2","wind_spd":"11"},{"cond_code_d":"100","cond_code_n":"101","cond_txt_d":"晴","cond_txt_n":"多云","date":"2019-01-01","hum":"21","mr":"02:42","ms":"13:49","pcpn":"0.0","pop":"0","pres":"1042","sr":"07:36","ss":"17:00","tmp_max":"1","tmp_min":"-10","uv_index":"3","vis":"20","wind_deg":"3","wind_dir":"北风","wind_sc":"3-4","wind_spd":"18"},{"cond_code_d":"101","cond_code_n":"101","cond_txt_d":"多云","cond_txt_n":"多云","date":"2019-01-02","hum":"30","mr":"03:45","ms":"14:23","pcpn":"0.0","pop":"0","pres":"1040","sr":"07:36","ss":"17:01","tmp_max":"2","tmp_min":"-9","uv_index":"0","vis":"20","wind_deg":"177","wind_dir":"南风","wind_sc":"1-2","wind_spd":"8"},{"cond_code_d":"101","cond_code_n":"100","cond_txt_d":"多云","cond_txt_n":"晴","date":"2019-01-03","hum":"30","mr":"04:46","ms":"15:00","pcpn":"0.0","pop":"0","pres":"1036","sr":"07:36","ss":"17:02","tmp_max":"3","tmp_min":"-7","uv_index":"2","vis":"20","wind_deg":"274","wind_dir":"西风","wind_sc":"1-2","wind_spd":"11"},{"cond_code_d":"100","cond_code_n":"101","cond_txt_d":"晴","cond_txt_n":"多云","date":"2019-01-04","hum":"17","mr":"05:46","ms":"15:42","pcpn":"0.0","pop":"0","pres":"1040","sr":"07:36","ss":"17:03","tmp_max":"3","tmp_min":"-6","uv_index":"2","vis":"20","wind_deg":"40","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"1"},{"cond_code_d":"101","cond_code_n":"100","cond_txt_d":"多云","cond_txt_n":"晴","date":"2019-01-05","hum":"17","mr":"06:41","ms":"16:27","pcpn":"0.0","pop":"0","pres":"1040","sr":"07:36","ss":"17:04","tmp_max":"2","tmp_min":"-7","uv_index":"2","vis":"20","wind_deg":"0","wind_dir":"北风","wind_sc":"1-2","wind_spd":"3"},{"cond_code_d":"100","cond_code_n":"100","cond_txt_d":"晴","cond_txt_n":"晴","date":"2019-01-06","hum":"19","mr":"07:33","ms":"17:17","pcpn":"0.0","pop":"0","pres":"1034","sr":"07:36","ss":"17:05","tmp_max":"2","tmp_min":"-7","uv_index":"2","vis":"20","wind_deg":"228","wind_dir":"西南风","wind_sc":"1-2","wind_spd":"3"}]
         */

        private BasicBean basic;
        private UpdateBean update;
        private String status;
        private List<DailyForecastBean> daily_forecast;

        @Bindable
        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
            notifyPropertyChanged(BR.basic);
        }

        @Bindable
        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
            notifyPropertyChanged(BR.update);
        }

        @Bindable
        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
            notifyPropertyChanged(BR.status);
        }

        @Bindable
        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
            notifyPropertyChanged(BR.daily_forecast);
        }

        public static class BasicBean extends BaseObservable {
            /**
             * cid : CN101010100
             * location : 北京
             * parent_city : 北京
             * admin_area : 北京
             * cnty : 中国
             * lat : 39.90498734
             * lon : 116.4052887
             * tz : +8.00
             */

            private String cid;
            private String location;
            private String parent_city;
            private String admin_area;
            private String cnty;
            private String lat;
            private String lon;
            private String tz;

            @Bindable
            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
                notifyPropertyChanged(BR.cid);
            }

            @Bindable
            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
                notifyPropertyChanged(BR.location);
            }

            @Bindable
            public String getParent_city() {
                return parent_city;
            }

            public void setParent_city(String parent_city) {
                this.parent_city = parent_city;
                notifyPropertyChanged(BR.parent_city);
            }

            @Bindable
            public String getAdmin_area() {
                return admin_area;
            }

            public void setAdmin_area(String admin_area) {
                this.admin_area = admin_area;
                notifyPropertyChanged(BR.admin_area);
            }

            @Bindable
            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
                notifyPropertyChanged(BR.cnty);
            }

            @Bindable
            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
                notifyPropertyChanged(BR.lat);
            }

            @Bindable
            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
                notifyPropertyChanged(BR.lon);
            }

            @Bindable
            public String getTz() {
                return tz;
            }

            public void setTz(String tz) {
                this.tz = tz;
                notifyPropertyChanged(BR.tz);
            }
        }

        public static class UpdateBean extends BaseObservable {
            /**
             * loc : 2018-12-31 14:56
             * utc : 2018-12-31 06:56
             */

            private String loc;
            private String utc;

            @Bindable
            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
                notifyPropertyChanged(BR.loc);
            }

            @Bindable
            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
                notifyPropertyChanged(BR.utc);
            }
        }

        public static class DailyForecastBean extends BaseObservable {
            /**
             * cond_code_d : 101
             * cond_code_n : 101
             * cond_txt_d : 多云
             * cond_txt_n : 多云
             * date : 2018-12-31
             * hum : 19
             * mr : 01:38
             * ms : 13:18
             * pcpn : 0.0
             * pop : 0
             * pres : 1045
             * sr : 07:35
             * ss : 16:59
             * tmp_max : -2
             * tmp_min : -10
             * uv_index : 2
             * vis : 20
             * wind_deg : 184
             * wind_dir : 南风
             * wind_sc : 1-2
             * wind_spd : 11
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
            private String pres;
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

            @Bindable
            public String getCond_code_d() {
                return cond_code_d;
            }

            public void setCond_code_d(String cond_code_d) {
                this.cond_code_d = cond_code_d;
                notifyPropertyChanged(BR.cond_code_d);
            }

            @Bindable
            public String getCond_code_n() {
                return cond_code_n;
            }

            public void setCond_code_n(String cond_code_n) {
                this.cond_code_n = cond_code_n;
                notifyPropertyChanged(BR.cond_code_n);
            }

            @Bindable
            public String getCond_txt_d() {
                return cond_txt_d;
            }

            public void setCond_txt_d(String cond_txt_d) {
                this.cond_txt_d = cond_txt_d;
                notifyPropertyChanged(BR.cond_txt_d);
            }

            @Bindable
            public String getCond_txt_n() {
                return cond_txt_n;
            }

            public void setCond_txt_n(String cond_txt_n) {
                this.cond_txt_n = cond_txt_n;
                notifyPropertyChanged(BR.cond_txt_n);
            }

            @Bindable
            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
                notifyPropertyChanged(BR.date);
            }

            @Bindable
            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
                notifyPropertyChanged(BR.hum);
            }

            @Bindable
            public String getMr() {
                return mr;
            }

            public void setMr(String mr) {
                this.mr = mr;
                notifyPropertyChanged(BR.mr);
            }

            @Bindable
            public String getMs() {
                return ms;
            }

            public void setMs(String ms) {
                this.ms = ms;
                notifyPropertyChanged(BR.ms);
            }

            @Bindable
            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
                notifyPropertyChanged(BR.pcpn);
            }

            @Bindable
            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
                notifyPropertyChanged(BR.pop);
            }

            @Bindable
            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
                notifyPropertyChanged(BR.pres);
            }

            @Bindable
            public String getSr() {
                return sr;
            }

            public void setSr(String sr) {
                this.sr = sr;
                notifyPropertyChanged(BR.sr);
            }

            @Bindable
            public String getSs() {
                return ss;
            }

            public void setSs(String ss) {
                this.ss = ss;
                notifyPropertyChanged(BR.ss);
            }

            @Bindable
            public String getTmp_max() {
                return tmp_max;
            }

            public void setTmp_max(String tmp_max) {
                this.tmp_max = tmp_max;
                notifyPropertyChanged(BR.tmp_max);
            }

            @Bindable
            public String getTmp_min() {
                return tmp_min;
            }

            public void setTmp_min(String tmp_min) {
                this.tmp_min = tmp_min;
                notifyPropertyChanged(BR.tmp_min);
            }

            @Bindable
            public String getUv_index() {
                return uv_index;
            }

            public void setUv_index(String uv_index) {
                this.uv_index = uv_index;
                notifyPropertyChanged(BR.uv_index);
            }

            @Bindable
            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
                notifyPropertyChanged(BR.vis);
            }

            @Bindable
            public String getWind_deg() {
                return wind_deg;
            }

            public void setWind_deg(String wind_deg) {
                this.wind_deg = wind_deg;
                notifyPropertyChanged(BR.wind_deg);
            }

            @Bindable
            public String getWind_dir() {
                return wind_dir;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
                notifyPropertyChanged(BR.wind_dir);
            }

            @Bindable
            public String getWind_sc() {
                return wind_sc;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
                notifyPropertyChanged(BR.wind_sc);
            }

            @Bindable
            public String getWind_spd() {
                return wind_spd;
            }

            public void setWind_spd(String wind_spd) {
                this.wind_spd = wind_spd;
                notifyPropertyChanged(BR.wind_spd);
            }
        }
    }
}
