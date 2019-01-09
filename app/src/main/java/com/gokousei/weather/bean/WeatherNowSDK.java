package com.gokousei.weather.bean;

import android.databinding.Bindable;
import android.databinding.Observable;
import android.databinding.PropertyChangeRegistry;

import com.gokousei.weather.BR;

public class WeatherNowSDK implements Observable {
    /**
     * now : {"cloud":"3","cond_code":"101","cond_txt":"多云","fl":"26","hum":"40","pcpn":"0.0","pres":"1002","tmp":"27","vis":"20","wind_deg":"180","wind_dir":"南风","wind_sc":"2","wind_spd":"10"}
     * basic : {"admin_area":"北京","cid":"CN101010100","cnty":"中国","lat":"39.90498734","locationSave":"北京","lon":"116.4052887","parent_city":"北京","tz":"+8.00"}
     * status : ok
     * update : {"loc":"2018-05-25 19:51","utc":"2018-05-25 11:51"}
     */

    private NowBean now;
    private BasicBean basic;
    private String status;
    private UpdateBean update;
    private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();

    private static WeatherNowSDK weatherNow;

    public WeatherNowSDK() {

    }

    public static WeatherNowSDK getWeatherNow() {
        if (weatherNow==null){
            synchronized (WeatherNowSDK.class){
                if (weatherNow==null){
                    weatherNow=new WeatherNowSDK();
                }
            }
        }
        return weatherNow;
    }

    @Bindable
    public NowBean getNow() {
        return now;
    }

    public void setNow(NowBean now) {
        this.now = now;
        notifyChange(BR.now);
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

    public static class NowBean implements Observable {
        /**
         * cloud : 3
         * cond_code : 101
         * cond_txt : 多云
         * fl : 26
         * hum : 40
         * pcpn : 0.0
         * pres : 1002
         * tmp : 27
         * vis : 20
         * wind_deg : 180
         * wind_dir : 南风
         * wind_sc : 2
         * wind_spd : 10
         */

        private String cloud;
        private String cond_code;
        private String cond_txt;
        private String fl;
        private String hum;
        private String pcpn;
        private String pres;
        private String tmp;
        private String vis;
        private String wind_deg;
        private String wind_dir;
        private String wind_sc;
        private String wind_spd;
        private transient PropertyChangeRegistry propertyChangeRegistry = new PropertyChangeRegistry();

        @Bindable
        public String getCloud() {
            return cloud;
        }

        public void setCloud(String cloud) {
            this.cloud = cloud;
            notifyChange(BR.cloud);
        }

        @Bindable
        public String getCond_code() {
            return cond_code;
        }

        public void setCond_code(String cond_code) {
            this.cond_code = cond_code;
            notifyChange(BR.cond_code);
        }

        @Bindable
        public String getCond_txt() {
            return cond_txt;
        }

        public void setCond_txt(String cond_txt) {
            this.cond_txt = cond_txt;
            notifyChange(BR.cond_txt);
        }

        @Bindable
        public String getFl() {
            return fl;
        }

        public void setFl(String fl) {
            this.fl = fl;
            notifyChange(BR.fl);
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
        public String getPcpn() {
            return pcpn;
        }

        public void setPcpn(String pcpn) {
            this.pcpn = pcpn;
            notifyChange(BR.pcpn);
        }

        @Bindable
        public String getPres() {
            return pres;
        }

        public void setPres(String pres) {
            this.pres = pres;
            notifyChange(BR.pres);
        }

        @Bindable
        public String getTmp() {
            return tmp;
        }

        public void setTmp(String tmp) {
            this.tmp = tmp;
            notifyChange(BR.tmp);
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

    public static class BasicBean implements Observable {
        /**
         * admin_area : 北京
         * cid : CN101010100
         * cnty : 中国
         * lat : 39.90498734
         * locationSave : 北京
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
         * loc : 2018-05-25 19:51
         * utc : 2018-05-25 11:51
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
}
