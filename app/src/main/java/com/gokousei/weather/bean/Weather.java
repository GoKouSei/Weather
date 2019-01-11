package com.gokousei.weather.bean;

import android.databinding.BaseObservable;

import java.io.Serializable;
import java.util.List;

public class Weather extends BaseObservable implements Serializable {
    private List<HeWeather6Bean> HeWeather6;

    public List<HeWeather6Bean> getHeWeather6() {
        return HeWeather6;
    }

    public void setHeWeather6(List<HeWeather6Bean> HeWeather6) {
        this.HeWeather6 = HeWeather6;
    }

    public static class HeWeather6Bean implements Serializable{
        /**
         * basic : {"cid":"CN101020100","location":"上海","parent_city":"上海","admin_area":"上海","cnty":"中国","lat":"31.23170662","lon":"121.47264099","tz":"+8.00"}
         * update : {"loc":"2019-01-08 16:56","utc":"2019-01-08 08:56"}
         * status : ok
         * now : {"cloud":"91","cond_code":"104","cond_txt":"阴","fl":"6","hum":"58","pcpn":"0.0","pres":"1031","tmp":"7","vis":"8","wind_deg":"276","wind_dir":"西风","wind_sc":"0","wind_spd":"1"}
         * daily_forecast : [{"cond_code_d":"104","cond_code_n":"305","cond_txt_d":"阴","cond_txt_n":"小雨","date":"2019-01-08","hum":"72","mr":"08:19","ms":"19:04","pcpn":"1.0","pop":"55","pres":"1031","sr":"06:53","ss":"17:08","tmp_max":"8","tmp_min":"5","uv_index":"1","vis":"2","wind_deg":"355","wind_dir":"北风","wind_sc":"1-2","wind_spd":"8"},{"cond_code_d":"305","cond_code_n":"305","cond_txt_d":"小雨","cond_txt_n":"小雨","date":"2019-01-09","hum":"64","mr":"08:58","ms":"19:57","pcpn":"3.0","pop":"58","pres":"1034","sr":"06:53","ss":"17:09","tmp_max":"7","tmp_min":"5","uv_index":"1","vis":"20","wind_deg":"73","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"11"},{"cond_code_d":"305","cond_code_n":"305","cond_txt_d":"小雨","cond_txt_n":"小雨","date":"2019-01-10","hum":"84","mr":"09:34","ms":"20:50","pcpn":"5.0","pop":"80","pres":"1027","sr":"06:53","ss":"17:10","tmp_max":"10","tmp_min":"8","uv_index":"1","vis":"15","wind_deg":"46","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"3"},{"cond_code_d":"305","cond_code_n":"305","cond_txt_d":"小雨","cond_txt_n":"小雨","date":"2019-01-11","hum":"95","mr":"10:07","ms":"21:44","pcpn":"1.0","pop":"55","pres":"1024","sr":"06:53","ss":"17:11","tmp_max":"11","tmp_min":"7","uv_index":"1","vis":"7","wind_deg":"84","wind_dir":"东风","wind_sc":"3-4","wind_spd":"20"},{"cond_code_d":"305","cond_code_n":"101","cond_txt_d":"小雨","cond_txt_n":"多云","date":"2019-01-12","hum":"95","mr":"10:39","ms":"22:37","pcpn":"1.0","pop":"55","pres":"1021","sr":"06:53","ss":"17:12","tmp_max":"10","tmp_min":"6","uv_index":"1","vis":"14","wind_deg":"334","wind_dir":"西北风","wind_sc":"3-4","wind_spd":"12"},{"cond_code_d":"101","cond_code_n":"101","cond_txt_d":"多云","cond_txt_n":"多云","date":"2019-01-13","hum":"77","mr":"11:10","ms":"23:32","pcpn":"0.0","pop":"0","pres":"1026","sr":"06:53","ss":"17:12","tmp_max":"10","tmp_min":"5","uv_index":"3","vis":"19","wind_deg":"27","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"7"},{"cond_code_d":"101","cond_code_n":"306","cond_txt_d":"多云","cond_txt_n":"中雨","date":"2019-01-14","hum":"73","mr":"11:42","ms":"00:00","pcpn":"1.0","pop":"55","pres":"1027","sr":"06:53","ss":"17:13","tmp_max":"13","tmp_min":"5","uv_index":"3","vis":"20","wind_deg":"83","wind_dir":"东风","wind_sc":"3-4","wind_spd":"19"}]
         * hourly : [{"cloud":"96","cond_code":"104","cond_txt":"阴","dew":"0","hum":"79","pop":"0","pres":"1030","time":"2019-01-08 19:00","tmp":"6","wind_deg":"357","wind_dir":"北风","wind_sc":"1-2","wind_spd":"3"},{"cloud":"85","cond_code":"104","cond_txt":"阴","dew":"0","hum":"80","pop":"1","pres":"1030","time":"2019-01-08 22:00","tmp":"5","wind_deg":"352","wind_dir":"北风","wind_sc":"1-2","wind_spd":"7"},{"cloud":"87","cond_code":"104","cond_txt":"阴","dew":"-2","hum":"81","pop":"17","pres":"1033","time":"2019-01-09 01:00","tmp":"5","wind_deg":"8","wind_dir":"北风","wind_sc":"1-2","wind_spd":"4"},{"cloud":"95","cond_code":"104","cond_txt":"阴","dew":"-2","hum":"81","pop":"55","pres":"1032","time":"2019-01-09 04:00","tmp":"5","wind_deg":"8","wind_dir":"北风","wind_sc":"1-2","wind_spd":"11"},{"cloud":"99","cond_code":"305","cond_txt":"小雨","dew":"-1","hum":"77","pop":"42","pres":"1032","time":"2019-01-09 07:00","tmp":"5","wind_deg":"359","wind_dir":"北风","wind_sc":"1-2","wind_spd":"8"},{"cloud":"100","cond_code":"305","cond_txt":"小雨","dew":"0","hum":"75","pop":"37","pres":"1031","time":"2019-01-09 10:00","tmp":"5","wind_deg":"75","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"2"},{"cloud":"100","cond_code":"305","cond_txt":"小雨","dew":"2","hum":"80","pop":"60","pres":"1029","time":"2019-01-09 13:00","tmp":"5","wind_deg":"79","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"3"},{"cloud":"100","cond_code":"305","cond_txt":"小雨","dew":"3","hum":"84","pop":"49","pres":"1027","time":"2019-01-09 16:00","tmp":"5","wind_deg":"70","wind_dir":"东北风","wind_sc":"1-2","wind_spd":"1"}]
         * lifestyle : [{"type":"comf","brf":"较舒适","txt":"白天天气阴沉，会感到有点儿凉，但大部分人完全可以接受。"},{"type":"drsg","brf":"冷","txt":"天气冷，建议着棉服、羽绒服、皮夹克加羊毛衫等冬季服装。年老体弱者宜着厚棉衣、冬大衣或厚羽绒服。"},{"type":"flu","brf":"易发","txt":"天冷空气湿度大，易发生感冒，请注意适当增加衣服，加强自我防护避免感冒。"},{"type":"sport","brf":"较适宜","txt":"阴天，较适宜进行各种户内外运动。"},{"type":"trav","brf":"适宜","txt":"天气较好，气温稍低，会感觉稍微有点凉，不过也是个好天气哦。适宜旅游，可不要错过机会呦！"},{"type":"uv","brf":"最弱","txt":"属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"},{"type":"cw","brf":"不宜","txt":"不宜洗车，未来24小时内有雨，如果在此期间洗车，雨水和路上的泥水可能会再次弄脏您的爱车。"},{"type":"air","brf":"较差","txt":"气象条件较不利于空气污染物稀释、扩散和清除，请适当减少室外活动时间。"}]
         */

        private BasicBean basic;
        private UpdateBean update;
        private String status;
        private NowBean now;
        private List<DailyForecastBean> daily_forecast;
        private List<HourlyBean> hourly;
        private List<LifestyleBean> lifestyle;

        public BasicBean getBasic() {
            return basic;
        }

        public void setBasic(BasicBean basic) {
            this.basic = basic;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public NowBean getNow() {
            return now;
        }

        public void setNow(NowBean now) {
            this.now = now;
        }

        public List<DailyForecastBean> getDaily_forecast() {
            return daily_forecast;
        }

        public void setDaily_forecast(List<DailyForecastBean> daily_forecast) {
            this.daily_forecast = daily_forecast;
        }

        public List<HourlyBean> getHourly() {
            return hourly;
        }

        public void setHourly(List<HourlyBean> hourly) {
            this.hourly = hourly;
        }

        public List<LifestyleBean> getLifestyle() {
            return lifestyle;
        }

        public void setLifestyle(List<LifestyleBean> lifestyle) {
            this.lifestyle = lifestyle;
        }

        public static class BasicBean implements Serializable{
            /**
             * cid : CN101020100
             * location : 上海
             * parent_city : 上海
             * admin_area : 上海
             * cnty : 中国
             * lat : 31.23170662
             * lon : 121.47264099
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

            public String getCid() {
                return cid;
            }

            public void setCid(String cid) {
                this.cid = cid;
            }

            public String getLocation() {
                return location;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public String getParent_city() {
                return parent_city;
            }

            public void setParent_city(String parent_city) {
                this.parent_city = parent_city;
            }

            public String getAdmin_area() {
                return admin_area;
            }

            public void setAdmin_area(String admin_area) {
                this.admin_area = admin_area;
            }

            public String getCnty() {
                return cnty;
            }

            public void setCnty(String cnty) {
                this.cnty = cnty;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getTz() {
                return tz;
            }

            public void setTz(String tz) {
                this.tz = tz;
            }
        }

        public static class UpdateBean implements Serializable{
            /**
             * loc : 2019-01-08 16:56
             * utc : 2019-01-08 08:56
             */

            private String loc;
            private String utc;

            public String getLoc() {
                return loc;
            }

            public void setLoc(String loc) {
                this.loc = loc;
            }

            public String getUtc() {
                return utc;
            }

            public void setUtc(String utc) {
                this.utc = utc;
            }
        }

        public static class NowBean implements Serializable{
            /**
             * cloud : 91
             * cond_code : 104
             * cond_txt : 阴
             * fl : 6
             * hum : 58
             * pcpn : 0.0
             * pres : 1031
             * tmp : 7
             * vis : 8
             * wind_deg : 276
             * wind_dir : 西风
             * wind_sc : 0
             * wind_spd : 1
             */

            private String cloud;
            private int cond_code;
            private String cond_txt;
            private String fl;
            private String hum;
            private String pcpn;
            private String pres;
            private String tmp;
            private String vis;
            private int wind_deg;
            private String wind_dir;
            private String wind_sc;
            private String wind_spd;

            public String getCloud() {
                return cloud;
            }

            public void setCloud(String cloud) {
                this.cloud = cloud;
            }

            public int getCond_code() {
                return cond_code;
            }

            public void setCond_code(int cond_code) {
                this.cond_code = cond_code;
            }

            public String getCond_txt() {
                return cond_txt;
            }

            public void setCond_txt(String cond_txt) {
                this.cond_txt = cond_txt;
            }

            public String getFl() {
                return fl;
            }

            public void setFl(String fl) {
                this.fl = fl;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public int getWind_deg() {
                return wind_deg;
            }

            public void setWind_deg(int wind_deg) {
                this.wind_deg = wind_deg;
            }

            public String getWind_dir() {
                return wind_dir;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
            }

            public String getWind_sc() {
                return wind_sc;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
            }

            public String getWind_spd() {
                return wind_spd;
            }

            public void setWind_spd(String wind_spd) {
                this.wind_spd = wind_spd;
            }
        }

        public static class DailyForecastBean implements Serializable{
            /**
             * cond_code_d : 104
             * cond_code_n : 305
             * cond_txt_d : 阴
             * cond_txt_n : 小雨
             * date : 2019-01-08
             * hum : 72
             * mr : 08:19
             * ms : 19:04
             * pcpn : 1.0
             * pop : 55
             * pres : 1031
             * sr : 06:53
             * ss : 17:08
             * tmp_max : 8
             * tmp_min : 5
             * uv_index : 1
             * vis : 2
             * wind_deg : 355
             * wind_dir : 北风
             * wind_sc : 1-2
             * wind_spd : 8
             */

            private int cond_code_d;
            private int cond_code_n;
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
            private int wind_deg;
            private String wind_dir;
            private String wind_sc;
            private int wind_spd;

            public int getCond_code_d() {
                return cond_code_d;
            }

            public void setCond_code_d(int cond_code_d) {
                this.cond_code_d = cond_code_d;
            }

            public int getCond_code_n() {
                return cond_code_n;
            }

            public void setCond_code_n(int cond_code_n) {
                this.cond_code_n = cond_code_n;
            }

            public String getCond_txt_d() {
                return cond_txt_d;
            }

            public void setCond_txt_d(String cond_txt_d) {
                this.cond_txt_d = cond_txt_d;
            }

            public String getCond_txt_n() {
                return cond_txt_n;
            }

            public void setCond_txt_n(String cond_txt_n) {
                this.cond_txt_n = cond_txt_n;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getMr() {
                return mr;
            }

            public void setMr(String mr) {
                this.mr = mr;
            }

            public String getMs() {
                return ms;
            }

            public void setMs(String ms) {
                this.ms = ms;
            }

            public String getPcpn() {
                return pcpn;
            }

            public void setPcpn(String pcpn) {
                this.pcpn = pcpn;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getSr() {
                return sr;
            }

            public void setSr(String sr) {
                this.sr = sr;
            }

            public String getSs() {
                return ss;
            }

            public void setSs(String ss) {
                this.ss = ss;
            }

            public String getTmp_max() {
                return tmp_max;
            }

            public void setTmp_max(String tmp_max) {
                this.tmp_max = tmp_max;
            }

            public String getTmp_min() {
                return tmp_min;
            }

            public void setTmp_min(String tmp_min) {
                this.tmp_min = tmp_min;
            }

            public String getUv_index() {
                return uv_index;
            }

            public void setUv_index(String uv_index) {
                this.uv_index = uv_index;
            }

            public String getVis() {
                return vis;
            }

            public void setVis(String vis) {
                this.vis = vis;
            }

            public int getWind_deg() {
                return wind_deg;
            }

            public void setWind_deg(int wind_deg) {
                this.wind_deg = wind_deg;
            }

            public String getWind_dir() {
                return wind_dir;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
            }

            public String getWind_sc() {
                return wind_sc;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
            }

            public int getWind_spd() {
                return wind_spd;
            }

            public void setWind_spd(int wind_spd) {
                this.wind_spd = wind_spd;
            }
        }

        public static class HourlyBean implements Serializable{
            /**
             * cloud : 96
             * cond_code : 104
             * cond_txt : 阴
             * dew : 0
             * hum : 79
             * pop : 0
             * pres : 1030
             * time : 2019-01-08 19:00
             * tmp : 6
             * wind_deg : 357
             * wind_dir : 北风
             * wind_sc : 1-2
             * wind_spd : 3
             */

            private String cloud;
            private int cond_code;
            private String cond_txt;
            private String dew;
            private String hum;
            private String pop;
            private String pres;
            private String time;
            private String tmp;
            private int wind_deg;
            private String wind_dir;
            private String wind_sc;
            private String wind_spd;

            public String getCloud() {
                return cloud;
            }

            public void setCloud(String cloud) {
                this.cloud = cloud;
            }

            public int getCond_code() {
                return cond_code;
            }

            public void setCond_code(int cond_code) {
                this.cond_code = cond_code;
            }

            public String getCond_txt() {
                return cond_txt;
            }

            public void setCond_txt(String cond_txt) {
                this.cond_txt = cond_txt;
            }

            public String getDew() {
                return dew;
            }

            public void setDew(String dew) {
                this.dew = dew;
            }

            public String getHum() {
                return hum;
            }

            public void setHum(String hum) {
                this.hum = hum;
            }

            public String getPop() {
                return pop;
            }

            public void setPop(String pop) {
                this.pop = pop;
            }

            public String getPres() {
                return pres;
            }

            public void setPres(String pres) {
                this.pres = pres;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getTmp() {
                return tmp;
            }

            public void setTmp(String tmp) {
                this.tmp = tmp;
            }

            public int getWind_deg() {
                return wind_deg;
            }

            public void setWind_deg(int wind_deg) {
                this.wind_deg = wind_deg;
            }

            public String getWind_dir() {
                return wind_dir;
            }

            public void setWind_dir(String wind_dir) {
                this.wind_dir = wind_dir;
            }

            public String getWind_sc() {
                return wind_sc;
            }

            public void setWind_sc(String wind_sc) {
                this.wind_sc = wind_sc;
            }

            public String getWind_spd() {
                return wind_spd;
            }

            public void setWind_spd(String wind_spd) {
                this.wind_spd = wind_spd;
            }
        }

        public static class LifestyleBean implements Serializable{
            /**
             * type : comf
             * brf : 较舒适
             * txt : 白天天气阴沉，会感到有点儿凉，但大部分人完全可以接受。
             */

            private String type;
            private String brf;
            private String txt;

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getBrf() {
                return brf;
            }

            public void setBrf(String brf) {
                this.brf = brf;
            }

            public String getTxt() {
                return txt;
            }

            public void setTxt(String txt) {
                this.txt = txt;
            }
        }
    }
}
