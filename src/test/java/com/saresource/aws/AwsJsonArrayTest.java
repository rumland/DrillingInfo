package com.saresource.aws;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class AwsJsonArrayTest {
    @Test
    public void validJsonArrayToCsvTest() throws IOException {
        AwsJsonArray awsJsonArray = new AwsJsonArray(this.getClass().getResourceAsStream("production-entities.json"));

        String expectedCsvData =
                "county_parish,max_active_wells,alloc_plus,country,peak_boe_month,prod_type,prior12_gas,first_12_boe,peak_mmcfge_month,latest_yield,latest_whsip,latest_flow_pressure,liq_grav,cum_gas,cum_mmcfge,months_produced,county_state,latest_well_count,first_6_oil,oil_gravity,first_24_gas,prac_ip_cfged,cum_oil,first_6_boe,last_12_gas,peak_gas,id,peak_oil,first_24_oil,first_12_gas,first_12_water,prac_ip_boe,first_60_water,gor_last_12,first_month_water,peak_oil_month,initial_completion_date,wellbore_orientation,first_12_oil,latest_test_year,basin,entity_id,cum_water,api_uwi,first_12_mmcfge,daily_gas,daily_oil,peak_mmcfge,total_depth,first_month_oil,field,well_number,first_60_gas,upper_perf,peak_boe,district,gas_gatherer,api_awi_list,first_month_gas,first_24_water,peak_gas_month,first_prod_date,first_60_mmcfge,latest_gor,regulatory_number,lower_perf,cum_bcfge,entity_latitude,first_60_oil,entity_longitude,first_24_mmcfge,oil_gatherer,lease_name,last_prod_date,first_60_boe,current_operator,current_producing_status,prac_ip_gas_daily,first_6_water,prac_ip_oil_daily,spud_year,second_month_gor,second_month_yield,last_12_water,prior12_wtr,state_province,master_current_operator,last_12_oil,first_24_boe,entity_type,first_6_gas,gas_grav,prior12_oil,reservoir,last_12_yield\n" +
                "LAS ANIMAS,1,X,USA,108,CBM,68760,4569.0,108,0.0,0.0,0.0,null,337041.0,337.0,86,LAS ANIMAS (CO),1,0.0,null,46871.0,99710.0,0.0,2468.0,123968.0,10868.0,833857,0.0,0.0,27413,8066,17.0,24460.0,0.0,760,0,2002-03-20,U,0,2002-04-19,RATON,104222836,131019.0,05-071-07567-00,27.0,0,0,11.0,2575.0,0,PURGATOIRE RIVER,16-12V,99359.0,2172.0,1811.0,(N/A),(N/A),050710756700,1935.0,10439.0,108,2002-04-01,99.0,0.0,null,2434.0,0.0,36.9954499,0.0,-104.89976,47.0,(N/A),HILL RANCH,2012-02-01,16560.0,\"XTO ENERGY, INC.\",INACTIVE,100.0,5518.0,0.0,2002-03-08,0.0,0.0,27727.0,23389,CO,XTO ENERGY,0.0,7812.0,WELL,14805.0,null,0,VERMEJO COAL,0.0\n" +
                "LAS ANIMAS,1,X,USA,2,CBM,27455,13851.0,2,0.0,0.0,0.0,null,447723.0,448.0,122,LAS ANIMAS (CO),1,0.0,null,170326.0,321484.0,0.0,5086.0,24009.0,9966.0,833858,0.0,0.0,83105,41033,54.0,272943.0,0.0,407,0,2002-04-16,U,0,2002-04-19,RATON,104222837,317198.0,05-071-07568-00,83.0,0,0,10.0,2920.0,0,PURGATOIRE RIVER,18-01V,277711.0,2500.0,1661.0,(N/A),(N/A),050710756800,909.0,192694.0,2,2002-04-01,278.0,0.0,null,2738.0,0.0,37.0029099,0.0,-104.92321,170.0,(N/A),HILL RANCH,2012-06-01,46285.0,\"XTO ENERGY, INC.\",INACTIVE,321.0,20027.0,0.0,2002-03-05,0.0,0.0,10848.0,10112,CO,XTO ENERGY,0.0,28388.0,WELL,30516.0,null,0,VERMEJO COAL,0.0\n" +
                "LAS ANIMAS,1,X,USA,41,CBM,38853,4352.0,41,0.0,0.0,0.0,null,1070945.0,1071.0,170,LAS ANIMAS (CO),1,0.0,null,79851.0,5267.0,0.0,616.0,23046.0,15361.0,833859,0.0,0.0,26110,432901,1.0,3434121.0,0.0,643,0,2002-10-10,U,0,2003-05-11,RATON,104222838,6454109.0,05-071-07569-00,26.0,66,0,15.0,2390.0,0,PURGATOIRE RIVER,14-2,540973.0,1330.0,2560.0,(N/A),(N/A),050710756900,0.0,1021600.0,41,2003-05-01,541.0,0.0,null,2196.0,1.0,37.2812799,0.0,-104.75668,80.0,(N/A),CHEETAH,2017-06-01,90162.0,\"PIONEER NATURAL RESOURCES USA, INC.\",ACTIVE,5.0,156008.0,0.0,2002-07-16,0.0,0.0,333863.0,384572,CO,PIONEER,0.0,13308.0,WELL,3694.0,null,0,RATON-VERMEJO COALS,0.0\n";

        Assert.assertEquals(expectedCsvData, awsJsonArray.getCsvString());
    }
}
