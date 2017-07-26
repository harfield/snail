package com.harfield.snail.tool;


import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class VaneePgcPlayRtbData  {
    public static final String[] FIELDS = new String[]{
            "play_id",
            "dat",
            "ta_gender_2500100010",
            "ta_gender_2500100020",
            "ta_age_2600100001",
            "ta_age_2600100002",
            "ta_age_2600100003",
            "ta_age_2600100004",
            "ta_age_2600100005",
            "ta_inter_240111",
            "ta_inter_240030",
            "ta_inter_240090",
            "ta_inter_240190",
            "ta_inter_240160",
            "ta_inter_240150",
            "ta_inter_240180",
            "ta_inter_240010",
            "ta_inter_240110",
            "ta_inter_240170",
            "ta_net_1",
            "ta_net_20",
            "ta_net_99",
            "ta_device_4",
            "ta_device_2",
            "ta_device_1",
            "ta_os_ios",
            "ta_os_android",
            "ta_edu_230001",
            "ta_edu_230002",
            "ta_edu_230003",
            "ta_edu_230004",
            "geo_1156130000",
            "geo_1156370000",
            "geo_1156210000",
            "geo_1156230000",
            "geo_1156220000",
            "geo_1156620000",
            "geo_1156630000",
            "geo_1156410000",
            "geo_1156320000",
            "geo_1156420000",
            "geo_1156430000",
            "geo_1156360000",
            "geo_1156330000",
            "geo_1156440000",
            "geo_1156530000",
            "geo_1156350000",
            "geo_1156460000",
            "geo_1156140000",
            "geo_1156510000",
            "geo_1156610000",
            "geo_1156520000",
            "geo_1156340000",
            "geo_1156500000",
            "geo_1156110000",
            "geo_1156310000",
            "geo_1156120000",
            "geo_1156450000",
            "geo_1156150000",
            "geo_1156540000",
            "geo_1156650000",
            "geo_1156640000",
            "pv",
            "audience_pv",
            "active_user",
            "play_time" ,
            "create_time",
            "remark"
    };
    static String[] cammalFiled ;
    private Long playId;
    private Date dat;
    private long taGender2500100010;
    private long taGender2500100020;
    private long taAge2600100001;
    private long taAge2600100002;
    private long taAge2600100003;
    private long taAge2600100004;
    private long taAge2600100005;
    private long taInter240111;
    private long taInter240030;
    private long taInter240090;
    private long taInter240190;
    private long taInter240160;
    private long taInter240150;
    private long taInter240180;
    private long taInter240010;
    private long taInter240110;
    private long taInter240170;
    private long taNet1;
    private long taNet20;
    private long taNet99;
    private long taDevice4;
    private long taDevice2;
    private long taDevice1;
    private long taOsIos;
    private long taOsAndroid;
    private long taEdu230001;
    private long taEdu230002;
    private long taEdu230003;
    private long taEdu230004;
    private long geo1156130000;
    private long geo1156370000;
    private long geo1156210000;
    private long geo1156230000;
    private long geo1156220000;
    private long geo1156620000;
    private long geo1156630000;
    private long geo1156410000;
    private long geo1156320000;
    private long geo1156420000;
    private long geo1156430000;
    private long geo1156360000;
    private long geo1156330000;
    private long geo1156440000;
    private long geo1156530000;
    private long geo1156350000;
    private long geo1156460000;
    private long geo1156140000;
    private long geo1156510000;
    private long geo1156610000;
    private long geo1156520000;
    private long geo1156340000;
    private long geo1156500000;
    private long geo1156110000;
    private long geo1156310000;
    private long geo1156120000;
    private long geo1156450000;
    private long geo1156150000;
    private long geo1156540000;
    private long geo1156650000;
    private long geo1156640000;
    private long pv;
    private long audiencePv;
    private long activeUser;
    private long playTime;
    private Date createTime;
    private String remark;
    private int count;//记录更新次数，用来判断是否写出

    public VaneePgcPlayRtbData(){
        if(cammalFiled == null){
            cammalFiled = new String[FIELDS.length];
            int pos=0;
            for(String f:FIELDS){
                char[] chars = f.toCharArray();
                boolean leading = false;
                for(int i=0;i<chars.length;i++){
                    if(chars[i] == '_'){
                        leading = true;
                    }else{
                        if(leading && chars[i]>='a' && chars[i]<='z'){
                            chars[i] = (char) (chars[i] - 32);
                            leading = false;
                        }
                    }
                }
                cammalFiled[pos++] = String.valueOf(chars).replace("_","");
            }
        }
    }
    public void write(PreparedStatement statement) throws SQLException {
      int i=1;
        statement.setLong(i++, playId);
        statement.setDate(i++, new java.sql.Date(dat.getTime()));
        statement.setLong(i++, taGender2500100010);
        statement.setLong(i++, taGender2500100020);
        statement.setLong(i++, taAge2600100001);
        statement.setLong(i++, taAge2600100002);
        statement.setLong(i++, taAge2600100003);
        statement.setLong(i++, taAge2600100004);
        statement.setLong(i++, taAge2600100005);
        statement.setLong(i++, taInter240111);
        statement.setLong(i++, taInter240030);
        statement.setLong(i++, taInter240090);
        statement.setLong(i++, taInter240190);
        statement.setLong(i++, taInter240160);
        statement.setLong(i++, taInter240150);
        statement.setLong(i++, taInter240180);
        statement.setLong(i++, taInter240010);
        statement.setLong(i++, taInter240110);
        statement.setLong(i++, taInter240170);
        statement.setLong(i++, taNet1);
        statement.setLong(i++, taNet20);
        statement.setLong(i++, taNet99);
        statement.setLong(i++, taDevice4);
        statement.setLong(i++, taDevice2);
        statement.setLong(i++, taDevice1);
        statement.setLong(i++, taOsIos);
        statement.setLong(i++, taOsAndroid);
        statement.setLong(i++, taEdu230001);
        statement.setLong(i++, taEdu230002);
        statement.setLong(i++, taEdu230003);
        statement.setLong(i++, taEdu230004);
        statement.setLong(i++, geo1156130000);
        statement.setLong(i++, geo1156370000);
        statement.setLong(i++, geo1156210000);
        statement.setLong(i++, geo1156230000);
        statement.setLong(i++, geo1156220000);
        statement.setLong(i++, geo1156620000);
        statement.setLong(i++, geo1156630000);
        statement.setLong(i++, geo1156410000);
        statement.setLong(i++, geo1156320000);
        statement.setLong(i++, geo1156420000);
        statement.setLong(i++, geo1156430000);
        statement.setLong(i++, geo1156360000);
        statement.setLong(i++, geo1156330000);
        statement.setLong(i++, geo1156440000);
        statement.setLong(i++, geo1156530000);
        statement.setLong(i++, geo1156350000);
        statement.setLong(i++, geo1156460000);
        statement.setLong(i++, geo1156140000);
        statement.setLong(i++, geo1156510000);
        statement.setLong(i++, geo1156610000);
        statement.setLong(i++, geo1156520000);
        statement.setLong(i++, geo1156340000);
        statement.setLong(i++, geo1156500000);
        statement.setLong(i++, geo1156110000);
        statement.setLong(i++, geo1156310000);
        statement.setLong(i++, geo1156120000);
        statement.setLong(i++, geo1156450000);
        statement.setLong(i++, geo1156150000);
        statement.setLong(i++, geo1156540000);
        statement.setLong(i++, geo1156650000);
        statement.setLong(i++, geo1156640000);
        statement.setLong(i++, pv);
        statement.setLong(i++, audiencePv);
        statement.setLong(i++, activeUser);
        statement.setLong(i++, playTime);
        statement.setDate(i++,new java.sql.Date(new Date().getTime()));
        statement.setString(i++,remark);
    }

    public void readFields(ResultSet resultSet) throws SQLException {

    }

    public void cleanData(){
        playId = null;
        dat = null;
        taGender2500100010 = 0;
        taGender2500100020 = 0;
        taAge2600100001 = 0;
        taAge2600100002 = 0;
        taAge2600100003 = 0;
        taAge2600100004 = 0;
        taAge2600100005 = 0;
        taInter240111 = 0;
        taInter240030 = 0;
        taInter240090 = 0;
        taInter240190 = 0;
        taInter240160 = 0;
        taInter240150 = 0;
        taInter240180 = 0;
        taInter240010 = 0;
        taInter240110 = 0;
        taInter240170 = 0;
        taNet1 = 0;
        taNet20 = 0;
        taNet99 = 0;
        taDevice4 = 0;
        taDevice2 = 0;
        taDevice1 = 0;
        taOsIos = 0;
        taOsAndroid = 0;
        taEdu230001 = 0;
        taEdu230002 = 0;
        taEdu230003 = 0;
        taEdu230004 = 0;
        geo1156130000 = 0;
        geo1156370000 = 0;
        geo1156210000 = 0;
        geo1156230000 = 0;
        geo1156220000 = 0;
        geo1156620000 = 0;
        geo1156630000 = 0;
        geo1156410000 = 0;
        geo1156320000 = 0;
        geo1156420000 = 0;
        geo1156430000 = 0;
        geo1156360000 = 0;
        geo1156330000 = 0;
        geo1156440000 = 0;
        geo1156530000 = 0;
        geo1156350000 = 0;
        geo1156460000 = 0;
        geo1156140000 = 0;
        geo1156510000 = 0;
        geo1156610000 = 0;
        geo1156520000 = 0;
        geo1156340000 = 0;
        geo1156500000 = 0;
        geo1156110000 = 0;
        geo1156310000 = 0;
        geo1156120000 = 0;
        geo1156450000 = 0;
        geo1156150000 = 0;
        geo1156540000 = 0;
        geo1156650000 = 0;
        geo1156640000 = 0;
        pv = 0;
        audiencePv = 0;
        activeUser = 0;
        playTime = 0;
        count = 0;
    }

    public void increment(String value,long cnt) throws NoSuchFieldException, IllegalAccessException {
        //可优化部分
        for(String f:cammalFiled){
            if(f.toLowerCase().endsWith(value)){
                Field declaredField = this.getClass().getDeclaredField(f);
                long aLong = declaredField.getLong(this);
                declaredField.set(this,aLong + cnt);
                count ++;
                break;
            }
        }
    }

    public void printIf(String value,long cnt) throws NoSuchFieldException, IllegalAccessException {
        for(String f:cammalFiled){
                Field declaredField = this.getClass().getDeclaredField(f);
            String name = declaredField.getName();
            if(declaredField.getType() == long.class){
                System.out.println( "else if (\""+ name.toLowerCase() +"\".endsWith(value)){"+ name+ " += cnt;}");
            }

//                long aLong = declaredField.getLong(this);
//                declaredField.set(this,aLong + cnt);
        }
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        VaneePgcPlayRtbData vaneePgcPlayRtbData = new VaneePgcPlayRtbData();
        vaneePgcPlayRtbData.printIf("abc",0);
    }

    public Long getPlayId() {
        return playId;
    }

    public void setPlayId(Long playId) {
        this.playId = playId;
    }

    public Date getDat() {
        return dat;
    }

    public void setDat(Date dat) {
        this.dat = dat;
    }

    public long getTaGender2500100010() {
        return taGender2500100010;
    }

    public void setTaGender2500100010(long taGender2500100010) {
        this.taGender2500100010 = taGender2500100010;
    }

    public long getTaGender2500100020() {
        return taGender2500100020;
    }

    public void setTaGender2500100020(long taGender2500100020) {
        this.taGender2500100020 = taGender2500100020;
    }

    public long getTaAge2600100001() {
        return taAge2600100001;
    }

    public void setTaAge2600100001(long taAge2600100001) {
        this.taAge2600100001 = taAge2600100001;
    }

    public long getTaAge2600100002() {
        return taAge2600100002;
    }

    public void setTaAge2600100002(long taAge2600100002) {
        this.taAge2600100002 = taAge2600100002;
    }

    public long getTaAge2600100003() {
        return taAge2600100003;
    }

    public void setTaAge2600100003(long taAge2600100003) {
        this.taAge2600100003 = taAge2600100003;
    }

    public long getTaAge2600100004() {
        return taAge2600100004;
    }

    public void setTaAge2600100004(long taAge2600100004) {
        this.taAge2600100004 = taAge2600100004;
    }

    public long getTaAge2600100005() {
        return taAge2600100005;
    }

    public void setTaAge2600100005(long taAge2600100005) {
        this.taAge2600100005 = taAge2600100005;
    }

    public long getTaInter240111() {
        return taInter240111;
    }

    public void setTaInter240111(long taInter240111) {
        this.taInter240111 = taInter240111;
    }

    public long getTaInter240030() {
        return taInter240030;
    }

    public void setTaInter240030(long taInter240030) {
        this.taInter240030 = taInter240030;
    }

    public long getTaInter240090() {
        return taInter240090;
    }

    public void setTaInter240090(long taInter240090) {
        this.taInter240090 = taInter240090;
    }

    public long getTaInter240190() {
        return taInter240190;
    }

    public void setTaInter240190(long taInter240190) {
        this.taInter240190 = taInter240190;
    }

    public long getTaInter240160() {
        return taInter240160;
    }

    public void setTaInter240160(long taInter240160) {
        this.taInter240160 = taInter240160;
    }

    public long getTaInter240150() {
        return taInter240150;
    }

    public void setTaInter240150(long taInter240150) {
        this.taInter240150 = taInter240150;
    }

    public long getTaInter240180() {
        return taInter240180;
    }

    public void setTaInter240180(long taInter240180) {
        this.taInter240180 = taInter240180;
    }

    public long getTaInter240010() {
        return taInter240010;
    }

    public void setTaInter240010(long taInter240010) {
        this.taInter240010 = taInter240010;
    }

    public long getTaInter240110() {
        return taInter240110;
    }

    public void setTaInter240110(long taInter240110) {
        this.taInter240110 = taInter240110;
    }

    public long getTaInter240170() {
        return taInter240170;
    }

    public void setTaInter240170(long taInter240170) {
        this.taInter240170 = taInter240170;
    }

    public long getTaNet1() {
        return taNet1;
    }

    public void setTaNet1(long taNet1) {
        this.taNet1 = taNet1;
    }

    public long getTaNet20() {
        return taNet20;
    }

    public void setTaNet20(long taNet20) {
        this.taNet20 = taNet20;
    }

    public long getTaNet99() {
        return taNet99;
    }

    public void setTaNet99(long taNet99) {
        this.taNet99 = taNet99;
    }

    public long getTaDevice4() {
        return taDevice4;
    }

    public void setTaDevice4(long taDevice4) {
        this.taDevice4 = taDevice4;
    }

    public long getTaDevice2() {
        return taDevice2;
    }

    public void setTaDevice2(long taDevice2) {
        this.taDevice2 = taDevice2;
    }

    public long getTaDevice1() {
        return taDevice1;
    }

    public void setTaDevice1(long taDevice1) {
        this.taDevice1 = taDevice1;
    }

    public long getTaOsIos() {
        return taOsIos;
    }

    public void setTaOsIos(long taOsIos) {
        this.taOsIos = taOsIos;
    }

    public long getTaOsAndroid() {
        return taOsAndroid;
    }

    public void setTaOsAndroid(long taOsAndroid) {
        this.taOsAndroid = taOsAndroid;
    }

    public long getTaEdu230001() {
        return taEdu230001;
    }

    public void setTaEdu230001(long taEdu230001) {
        this.taEdu230001 = taEdu230001;
    }

    public long getTaEdu230002() {
        return taEdu230002;
    }

    public void setTaEdu230002(long taEdu230002) {
        this.taEdu230002 = taEdu230002;
    }

    public long getTaEdu230003() {
        return taEdu230003;
    }

    public void setTaEdu230003(long taEdu230003) {
        this.taEdu230003 = taEdu230003;
    }

    public long getTaEdu230004() {
        return taEdu230004;
    }

    public void setTaEdu230004(long taEdu230004) {
        this.taEdu230004 = taEdu230004;
    }

    public long getGeo1156130000() {
        return geo1156130000;
    }

    public void setGeo1156130000(long geo1156130000) {
        this.geo1156130000 = geo1156130000;
    }

    public long getGeo1156370000() {
        return geo1156370000;
    }

    public void setGeo1156370000(long geo1156370000) {
        this.geo1156370000 = geo1156370000;
    }

    public long getGeo1156210000() {
        return geo1156210000;
    }

    public void setGeo1156210000(long geo1156210000) {
        this.geo1156210000 = geo1156210000;
    }

    public long getGeo1156230000() {
        return geo1156230000;
    }

    public void setGeo1156230000(long geo1156230000) {
        this.geo1156230000 = geo1156230000;
    }

    public long getGeo1156220000() {
        return geo1156220000;
    }

    public void setGeo1156220000(long geo1156220000) {
        this.geo1156220000 = geo1156220000;
    }

    public long getGeo1156620000() {
        return geo1156620000;
    }

    public void setGeo1156620000(long geo1156620000) {
        this.geo1156620000 = geo1156620000;
    }

    public long getGeo1156630000() {
        return geo1156630000;
    }

    public void setGeo1156630000(long geo1156630000) {
        this.geo1156630000 = geo1156630000;
    }

    public long getGeo1156410000() {
        return geo1156410000;
    }

    public void setGeo1156410000(long geo1156410000) {
        this.geo1156410000 = geo1156410000;
    }

    public long getGeo1156320000() {
        return geo1156320000;
    }

    public void setGeo1156320000(long geo1156320000) {
        this.geo1156320000 = geo1156320000;
    }

    public long getGeo1156420000() {
        return geo1156420000;
    }

    public void setGeo1156420000(long geo1156420000) {
        this.geo1156420000 = geo1156420000;
    }

    public long getGeo1156430000() {
        return geo1156430000;
    }

    public void setGeo1156430000(long geo1156430000) {
        this.geo1156430000 = geo1156430000;
    }

    public long getGeo1156360000() {
        return geo1156360000;
    }

    public void setGeo1156360000(long geo1156360000) {
        this.geo1156360000 = geo1156360000;
    }

    public long getGeo1156330000() {
        return geo1156330000;
    }

    public void setGeo1156330000(long geo1156330000) {
        this.geo1156330000 = geo1156330000;
    }

    public long getGeo1156440000() {
        return geo1156440000;
    }

    public void setGeo1156440000(long geo1156440000) {
        this.geo1156440000 = geo1156440000;
    }

    public long getGeo1156530000() {
        return geo1156530000;
    }

    public void setGeo1156530000(long geo1156530000) {
        this.geo1156530000 = geo1156530000;
    }

    public long getGeo1156350000() {
        return geo1156350000;
    }

    public void setGeo1156350000(long geo1156350000) {
        this.geo1156350000 = geo1156350000;
    }

    public long getGeo1156460000() {
        return geo1156460000;
    }

    public void setGeo1156460000(long geo1156460000) {
        this.geo1156460000 = geo1156460000;
    }

    public long getGeo1156140000() {
        return geo1156140000;
    }

    public void setGeo1156140000(long geo1156140000) {
        this.geo1156140000 = geo1156140000;
    }

    public long getGeo1156510000() {
        return geo1156510000;
    }

    public void setGeo1156510000(long geo1156510000) {
        this.geo1156510000 = geo1156510000;
    }

    public long getGeo1156610000() {
        return geo1156610000;
    }

    public void setGeo1156610000(long geo1156610000) {
        this.geo1156610000 = geo1156610000;
    }

    public long getGeo1156520000() {
        return geo1156520000;
    }

    public void setGeo1156520000(long geo1156520000) {
        this.geo1156520000 = geo1156520000;
    }

    public long getGeo1156340000() {
        return geo1156340000;
    }

    public void setGeo1156340000(long geo1156340000) {
        this.geo1156340000 = geo1156340000;
    }

    public long getGeo1156500000() {
        return geo1156500000;
    }

    public void setGeo1156500000(long geo1156500000) {
        this.geo1156500000 = geo1156500000;
    }

    public long getGeo1156110000() {
        return geo1156110000;
    }

    public void setGeo1156110000(long geo1156110000) {
        this.geo1156110000 = geo1156110000;
    }

    public long getGeo1156310000() {
        return geo1156310000;
    }

    public void setGeo1156310000(long geo1156310000) {
        this.geo1156310000 = geo1156310000;
    }

    public long getGeo1156120000() {
        return geo1156120000;
    }

    public void setGeo1156120000(long geo1156120000) {
        this.geo1156120000 = geo1156120000;
    }

    public long getGeo1156450000() {
        return geo1156450000;
    }

    public void setGeo1156450000(long geo1156450000) {
        this.geo1156450000 = geo1156450000;
    }

    public long getGeo1156150000() {
        return geo1156150000;
    }

    public void setGeo1156150000(long geo1156150000) {
        this.geo1156150000 = geo1156150000;
    }

    public long getGeo1156540000() {
        return geo1156540000;
    }

    public void setGeo1156540000(long geo1156540000) {
        this.geo1156540000 = geo1156540000;
    }

    public long getGeo1156650000() {
        return geo1156650000;
    }

    public void setGeo1156650000(long geo1156650000) {
        this.geo1156650000 = geo1156650000;
    }

    public long getGeo1156640000() {
        return geo1156640000;
    }

    public void setGeo1156640000(long geo1156640000) {
        this.geo1156640000 = geo1156640000;
    }

    public long getPv() {
        return pv;
    }

    public void setPv(long pv) {
        this.pv = pv;
    }

    public long getActiveUser() {
        return activeUser;
    }

    public void setActiveUser(long activeUser) {
        this.activeUser = activeUser;
    }

    public long getPlayTime() {
        return playTime;
    }

    public void setPlayTime(long playTime) {
        this.playTime = playTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public long getAudiencePv() {
        return audiencePv;
    }

    public void setAudiencePv(long audiencePv) {
        this.audiencePv = audiencePv;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

