package com.saresource.drillinginfo.directaccess.pojo.v1;

public class ProductionHeader {
    public String toString() {
        return String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|" +
                             "%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|" +
                             "%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|" +
                             "%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s",
                getId(),
                getEntity_id(),
                getDistrict(),
                getEntity_type(),
                getProd_type(),
                getState_province(),
                getCountry(),
                getLease_name(),
                getApi_uwi(),
                getInitial_completion_date(),
                getField(),
                getReservoir(),
                getRegulatory_number(),
                getCounty_parish(),
                getWell_number(),
                getCurrent_operator(),
                getOil_gatherer(),
                getGas_gatherer(),
                getCurrent_producing_status(),
                getWellbore_orientation(),
                getTotal_depth(),
                getLower_perf(),
                getUpper_perf(),
                getLiq_grav(),
                getGas_grav(),
                getDaily_oil(),
                getDaily_gas(),
                getCum_gas(),
                getCum_oil(),
                getCum_water(),
                getEntity_latitude(),
                getEntity_longitude(),
                getFirst_prod_date(),
                getLast_prod_date(),
                getLatest_well_count(),
                getGor_last_12(),
                getBasin(),
                getPeak_gas(),
                getPeak_oil(),
                getApi_awi_list(),
                getMax_active_wells(),
                getMonths_produced(),
                getMaster_current_operator(),
                getAlloc_plus(),
                getOil_gravity(),
                getFirst_month_oil(),
                getFirst_month_gas(),
                getFirst_month_water(),
                getFirst_12_oil(),
                getFirst_12_gas(),
                getFirst_12_water(),
                getLast_12_oil(),
                getLast_12_gas(),
                getLast_12_water(),
                getSecond_month_gor(),
                getLatest_gor(),
                getLast_12_yield(),
                getSecond_month_yield(),
                getLatest_yield(),
                getSpud_year(),
                getPeak_gas_month(),
                getPeak_oil_month(),
                getPeak_boe(),
                getPeak_boe_month(),
                getPeak_mmcfge(),
                getPeak_mmcfge_month(),
                getLatest_test_year(),
                getLatest_flow_pressure(),
                getLatest_whsip(),
                getFirst_6_oil(),
                getFirst_6_gas(),
                getFirst_6_water(),
                getFirst_24_oil(),
                getFirst_24_gas(),
                getFirst_24_water(),
                getFirst_60_oil(),
                getFirst_60_gas(),
                getFirst_60_water(),
                getFirst_6_boe(),
                getFirst_12_boe(),
                getFirst_24_boe(),
                getFirst_60_boe(),
                getFirst_12_mmcfge(),
                getFirst_24_mmcfge(),
                getFirst_60_mmcfge(),
                getPrac_ip_oil_daily(),
                getPrac_ip_gas_daily(),
                getPrac_ip_cfged(),
                getPrac_ip_boe(),
                getCum_mmcfge(),
                getCum_bcfge(),
                getPrior12_oil(),
                getPrior12_gas(),
                getPrior12_wtr(),
                getCounty_state());
    }

    public String getWell_number() {
        return well_number;
    }

    public void setWell_number(String well_number) {
        this.well_number = well_number;
    }

    public int getFirst_month_gas() {
        return first_month_gas;
    }

    public void setFirst_month_gas(int first_month_gas) {
        this.first_month_gas = first_month_gas;
    }

    public int getPrac_ip_oil_daily() {
        return prac_ip_oil_daily;
    }

    public void setPrac_ip_oil_daily(int prac_ip_oil_daily) {
        this.prac_ip_oil_daily = prac_ip_oil_daily;
    }

    public String getCounty_parish() {
        return county_parish;
    }

    public void setCounty_parish(String county_parish) {
        this.county_parish = county_parish;
    }

    public String getCurrent_producing_status() {
        return current_producing_status;
    }

    public void setCurrent_producing_status(String current_producing_status) {
        this.current_producing_status = current_producing_status;
    }

    public int getDaily_oil() {
        return daily_oil;
    }

    public void setDaily_oil(int daily_oil) {
        this.daily_oil = daily_oil;
    }

    public int getFirst_12_gas() {
        return first_12_gas;
    }

    public void setFirst_12_gas(int first_12_gas) {
        this.first_12_gas = first_12_gas;
    }

    public int getFirst_month_water() {
        return first_month_water;
    }

    public void setFirst_month_water(int first_month_water) {
        this.first_month_water = first_month_water;
    }

    public int getLatest_gor() {
        return latest_gor;
    }

    public void setLatest_gor(int latest_gor) {
        this.latest_gor = latest_gor;
    }

    public int getFirst_60_oil() {
        return first_60_oil;
    }

    public void setFirst_60_oil(int first_60_oil) {
        this.first_60_oil = first_60_oil;
    }

    public String getRegulatory_number() {
        return regulatory_number;
    }

    public void setRegulatory_number(String regulatory_number) {
        this.regulatory_number = regulatory_number;
    }

    public int getFirst_6_oil() {
        return first_6_oil;
    }

    public void setFirst_6_oil(int first_6_oil) {
        this.first_6_oil = first_6_oil;
    }

    public int getFirst_60_gas() {
        return first_60_gas;
    }

    public void setFirst_60_gas(int first_60_gas) {
        this.first_60_gas = first_60_gas;
    }

    public double getEntity_longitude() {
        return entity_longitude;
    }

    public void setEntity_longitude(double entity_longitude) {
        this.entity_longitude = entity_longitude;
    }

    public int getPrior12_wtr() {
        return prior12_wtr;
    }

    public void setPrior12_wtr(int prior12_wtr) {
        this.prior12_wtr = prior12_wtr;
    }

    public int getCum_bcfge() {
        return cum_bcfge;
    }

    public void setCum_bcfge(int cum_bcfge) {
        this.cum_bcfge = cum_bcfge;
    }

    public int getFirst_6_water() {
        return first_6_water;
    }

    public void setFirst_6_water(int first_6_water) {
        this.first_6_water = first_6_water;
    }

    public String getFirst_24_oil() {
        return first_24_oil;
    }

    public void setFirst_24_oil(String first_24_oil) {
        this.first_24_oil = first_24_oil;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public int getFirst_60_boe() {
        return first_60_boe;
    }

    public void setFirst_60_boe(int first_60_boe) {
        this.first_60_boe = first_60_boe;
    }

    public int getFirst_12_mmcfge() {
        return first_12_mmcfge;
    }

    public void setFirst_12_mmcfge(int first_12_mmcfge) {
        this.first_12_mmcfge = first_12_mmcfge;
    }

    public double getGas_grav() {
        return gas_grav;
    }

    public void setGas_grav(double gas_grav) {
        this.gas_grav = gas_grav;
    }

    public int getFirst_60_mmcfge() {
        return first_60_mmcfge;
    }

    public void setFirst_60_mmcfge(int first_60_mmcfge) {
        this.first_60_mmcfge = first_60_mmcfge;
    }

    public int getPeak_oil_month() {
        return peak_oil_month;
    }

    public void setPeak_oil_month(int peak_oil_month) {
        this.peak_oil_month = peak_oil_month;
    }

    public String getCurrent_operator() {
        return current_operator;
    }

    public void setCurrent_operator(String current_operator) {
        this.current_operator = current_operator;
    }

    public int getFirst_24_water() {
        return first_24_water;
    }

    public void setFirst_24_water(int first_24_water) {
        this.first_24_water = first_24_water;
    }

    public int getLast_12_yield() {
        return last_12_yield;
    }

    public void setLast_12_yield(int last_12_yield) {
        this.last_12_yield = last_12_yield;
    }

    public String getInitial_completion_date() {
        return initial_completion_date;
    }

    public void setInitial_completion_date(String initial_completion_date) {
        this.initial_completion_date = initial_completion_date;
    }

    public String getWellbore_orientation() {
        return wellbore_orientation;
    }

    public void setWellbore_orientation(String wellbore_orientation) {
        this.wellbore_orientation = wellbore_orientation;
    }

    public int getLatest_well_count() {
        return latest_well_count;
    }

    public void setLatest_well_count(int latest_well_count) {
        this.latest_well_count = latest_well_count;
    }

    public String getState_province() {
        return state_province;
    }

    public void setState_province(String state_province) {
        this.state_province = state_province;
    }

    public int getFirst_6_gas() {
        return first_6_gas;
    }

    public void setFirst_6_gas(int first_6_gas) {
        this.first_6_gas = first_6_gas;
    }

    public String getLatest_test_year() {
        return latest_test_year;
    }

    public void setLatest_test_year(String latest_test_year) {
        this.latest_test_year = latest_test_year;
    }

    public int getLast_12_oil() {
        return last_12_oil;
    }

    public void setLast_12_oil(int last_12_oil) {
        this.last_12_oil = last_12_oil;
    }

    public int getLatest_whsip() {
        return latest_whsip;
    }

    public void setLatest_whsip(int latest_whsip) {
        this.latest_whsip = latest_whsip;
    }

    public double getEntity_latitude() {
        return entity_latitude;
    }

    public void setEntity_latitude(double entity_latitude) {
        this.entity_latitude = entity_latitude;
    }

    public String getEntity_type() {
        return entity_type;
    }

    public void setEntity_type(String entity_type) {
        this.entity_type = entity_type;
    }

    public int getCum_water() {
        return cum_water;
    }

    public void setCum_water(int cum_water) {
        this.cum_water = cum_water;
    }

    public int getPrac_ip_boe() {
        return prac_ip_boe;
    }

    public void setPrac_ip_boe(int prac_ip_boe) {
        this.prac_ip_boe = prac_ip_boe;
    }

    public String getMaster_current_operator() {
        return master_current_operator;
    }

    public void setMaster_current_operator(String master_current_operator) {
        this.master_current_operator = master_current_operator;
    }

    public int getFirst_12_oil() {
        return first_12_oil;
    }

    public void setFirst_12_oil(int first_12_oil) {
        this.first_12_oil = first_12_oil;
    }

    public int getFirst_12_boe() {
        return first_12_boe;
    }

    public void setFirst_12_boe(int first_12_boe) {
        this.first_12_boe = first_12_boe;
    }

    public long getCum_gas() {
        return cum_gas;
    }

    public void setCum_gas(long cum_gas) {
        this.cum_gas = cum_gas;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDaily_gas() {
        return daily_gas;
    }

    public void setDaily_gas(int daily_gas) {
        this.daily_gas = daily_gas;
    }

    public int getPeak_boe_month() {
        return peak_boe_month;
    }

    public void setPeak_boe_month(int peak_boe_month) {
        this.peak_boe_month = peak_boe_month;
    }

    public int getLast_12_water() {
        return last_12_water;
    }

    public void setLast_12_water(int last_12_water) {
        this.last_12_water = last_12_water;
    }

    public String getGas_gatherer() {
        return gas_gatherer;
    }

    public void setGas_gatherer(String gas_gatherer) {
        this.gas_gatherer = gas_gatherer;
    }

    public int getUpper_perf() {
        return upper_perf;
    }

    public void setUpper_perf(int upper_perf) {
        this.upper_perf = upper_perf;
    }

    public int getPeak_mmcfge() {
        return peak_mmcfge;
    }

    public void setPeak_mmcfge(int peak_mmcfge) {
        this.peak_mmcfge = peak_mmcfge;
    }

    public String getCounty_state() {
        return county_state;
    }

    public void setCounty_state(String county_state) {
        this.county_state = county_state;
    }

    public String getProd_type() {
        return prod_type;
    }

    public void setProd_type(String prod_type) {
        this.prod_type = prod_type;
    }

    public String getApi_awi_list() {
        return api_awi_list;
    }

    public void setApi_awi_list(String api_awi_list) {
        this.api_awi_list = api_awi_list;
    }

    public int getFirst_12_water() {
        return first_12_water;
    }

    public void setFirst_12_water(int first_12_water) {
        this.first_12_water = first_12_water;
    }

    public int getCum_mmcfge() {
        return cum_mmcfge;
    }

    public void setCum_mmcfge(int cum_mmcfge) {
        this.cum_mmcfge = cum_mmcfge;
    }

    public int getFirst_24_mmcfge() {
        return first_24_mmcfge;
    }

    public void setFirst_24_mmcfge(int first_24_mmcfge) {
        this.first_24_mmcfge = first_24_mmcfge;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFirst_24_gas() {
        return first_24_gas;
    }

    public void setFirst_24_gas(int first_24_gas) {
        this.first_24_gas = first_24_gas;
    }

    public int getMonths_produced() {
        return months_produced;
    }

    public void setMonths_produced(int months_produced) {
        this.months_produced = months_produced;
    }

    public double getLiq_grav() {
        return liq_grav;
    }

    public void setLiq_grav(double liq_grav) {
        this.liq_grav = liq_grav;
    }

    public int getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(int entity_id) {
        this.entity_id = entity_id;
    }

    public String getLast_prod_date() {
        return last_prod_date;
    }

    public void setLast_prod_date(String last_prod_date) {
        this.last_prod_date = last_prod_date;
    }

    public int getPeak_gas_month() {
        return peak_gas_month;
    }

    public void setPeak_gas_month(int peak_gas_month) {
        this.peak_gas_month = peak_gas_month;
    }

    public int getMax_active_wells() {
        return max_active_wells;
    }

    public void setMax_active_wells(int max_active_wells) {
        this.max_active_wells = max_active_wells;
    }

    public int getFirst_60_water() {
        return first_60_water;
    }

    public void setFirst_60_water(int first_60_water) {
        this.first_60_water = first_60_water;
    }

    public int getLower_perf() {
        return lower_perf;
    }

    public void setLower_perf(int lower_perf) {
        this.lower_perf = lower_perf;
    }

    public int getPeak_boe() {
        return peak_boe;
    }

    public void setPeak_boe(int peak_boe) {
        this.peak_boe = peak_boe;
    }

    public int getFirst_6_boe() {
        return first_6_boe;
    }

    public void setFirst_6_boe(int first_6_boe) {
        this.first_6_boe = first_6_boe;
    }

    public int getLatest_yield() {
        return latest_yield;
    }

    public void setLatest_yield(int latest_yield) {
        this.latest_yield = latest_yield;
    }

    public int getSecond_month_gor() {
        return second_month_gor;
    }

    public void setSecond_month_gor(int second_month_gor) {
        this.second_month_gor = second_month_gor;
    }

    public int getPrac_ip_gas_daily() {
        return prac_ip_gas_daily;
    }

    public void setPrac_ip_gas_daily(int prac_ip_gas_daily) {
        this.prac_ip_gas_daily = prac_ip_gas_daily;
    }

    public int getPrior12_gas() {
        return prior12_gas;
    }

    public void setPrior12_gas(int prior12_gas) {
        this.prior12_gas = prior12_gas;
    }

    public int getPrac_ip_cfged() {
        return prac_ip_cfged;
    }

    public void setPrac_ip_cfged(int prac_ip_cfged) {
        this.prac_ip_cfged = prac_ip_cfged;
    }

    public int getLatest_flow_pressure() {
        return latest_flow_pressure;
    }

    public void setLatest_flow_pressure(int latest_flow_pressure) {
        this.latest_flow_pressure = latest_flow_pressure;
    }

    public String getReservoir() {
        return reservoir;
    }

    public void setReservoir(String reservoir) {
        this.reservoir = reservoir;
    }

    public int getPeak_gas() {
        return peak_gas;
    }

    public void setPeak_gas(int peak_gas) {
        this.peak_gas = peak_gas;
    }

    public String getOil_gatherer() {
        return oil_gatherer;
    }

    public void setOil_gatherer(String oil_gatherer) {
        this.oil_gatherer = oil_gatherer;
    }

    public int getTotal_depth() {
        return total_depth;
    }

    public void setTotal_depth(int total_depth) {
        this.total_depth = total_depth;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getSecond_month_yield() {
        return second_month_yield;
    }

    public void setSecond_month_yield(int second_month_yield) {
        this.second_month_yield = second_month_yield;
    }

    public int getPeak_mmcfge_month() {
        return peak_mmcfge_month;
    }

    public void setPeak_mmcfge_month(int peak_mmcfge_month) {
        this.peak_mmcfge_month = peak_mmcfge_month;
    }

    public String getApi_uwi() {
        return api_uwi;
    }

    public void setApi_uwi(String api_uwi) {
        this.api_uwi = api_uwi;
    }

    public String getBasin() {
        return basin;
    }

    public void setBasin(String basin) {
        this.basin = basin;
    }

    public String getFirst_prod_date() {
        return first_prod_date;
    }

    public void setFirst_prod_date(String first_prod_date) {
        this.first_prod_date = first_prod_date;
    }

    public int getFirst_month_oil() {
        return first_month_oil;
    }

    public void setFirst_month_oil(int first_month_oil) {
        this.first_month_oil = first_month_oil;
    }

    public int getCum_oil() {
        return cum_oil;
    }

    public void setCum_oil(int cum_oil) {
        this.cum_oil = cum_oil;
    }

    public double getGor_last_12() {
        return gor_last_12;
    }

    public void setGor_last_12(double gor_last_12) {
        this.gor_last_12 = gor_last_12;
    }

    public String getFirst_24_boe() {
        return first_24_boe;
    }

    public void setFirst_24_boe(String first_24_boe) {
        this.first_24_boe = first_24_boe;
    }

    public int getPrior12_oil() {
        return prior12_oil;
    }

    public void setPrior12_oil(int prior12_oil) {
        this.prior12_oil = prior12_oil;
    }

    public int getLast_12_gas() {
        return last_12_gas;
    }

    public void setLast_12_gas(int last_12_gas) {
        this.last_12_gas = last_12_gas;
    }

    public String getAlloc_plus() {
        return alloc_plus;
    }

    public void setAlloc_plus(String alloc_plus) {
        this.alloc_plus = alloc_plus;
    }

    public int getPeak_oil() {
        return peak_oil;
    }

    public void setPeak_oil(int peak_oil) {
        this.peak_oil = peak_oil;
    }

    public String getLease_name() {
        return lease_name;
    }

    public void setLease_name(String lease_name) {
        this.lease_name = lease_name;
    }

    public String getSpud_year() {
        return spud_year;
    }

    public void setSpud_year(String spud_year) {
        this.spud_year = spud_year;
    }

    public double getOil_gravity() {
        return oil_gravity;
    }

    public void setOil_gravity(double oil_gravity) {
        this.oil_gravity = oil_gravity;
    }

    private String well_number;
    private int first_month_gas;
    private int prac_ip_oil_daily;
    private String county_parish;
    private String current_producing_status;
    private int daily_oil;
    private int first_12_gas;
    private int first_month_water;
    private int latest_gor;
    private int first_60_oil;
    private String regulatory_number;
    private int first_6_oil;
    private int first_60_gas;
    private double entity_longitude;
    private int prior12_wtr;
    private int cum_bcfge;
    private int first_6_water;
    private String first_24_oil;
    private String field;
    private int first_60_boe;
    private int first_12_mmcfge;
    private double gas_grav;
    private int first_60_mmcfge;
    private int peak_oil_month;
    private String current_operator;
    private int first_24_water;
    private int last_12_yield;
    private String initial_completion_date;
    private String wellbore_orientation;
    private int latest_well_count;
    private String state_province;
    private int first_6_gas;
    private String latest_test_year;
    private int last_12_oil;
    private int latest_whsip;
    private double entity_latitude;
    private String entity_type;
    private int cum_water;
    private int prac_ip_boe;
    private String master_current_operator;
    private int first_12_oil;
    private int first_12_boe;
    private long cum_gas;
    private String country;
    private int daily_gas;
    private int peak_boe_month;
    private int last_12_water;
    private String gas_gatherer;
    private int upper_perf;
    private int peak_mmcfge;
    private String county_state;
    private String prod_type;
    private String api_awi_list;
    private int first_12_water;
    private int cum_mmcfge;
    private int first_24_mmcfge;
    private int id;
    private int first_24_gas;
    private int months_produced;
    private double liq_grav;
    private int entity_id;
    private String last_prod_date;
    private int peak_gas_month;
    private int max_active_wells;
    private int first_60_water;
    private int lower_perf;
    private int peak_boe;
    private int first_6_boe;
    private int latest_yield;
    private int second_month_gor;
    private int prac_ip_gas_daily;
    private int prior12_gas;
    private int prac_ip_cfged;
    private int latest_flow_pressure;
    private String reservoir;
    private int peak_gas;
    private String oil_gatherer;
    private int total_depth;
    private String district;
    private int second_month_yield;
    private int peak_mmcfge_month;
    private String api_uwi;
    private String basin;
    private String first_prod_date;
    private int first_month_oil;
    private int cum_oil;
    private double gor_last_12;
    private String first_24_boe;
    private int prior12_oil;
    private int last_12_gas;
    private String alloc_plus;
    private int peak_oil;
    private String lease_name;
    private String spud_year;
    private double oil_gravity;
}
