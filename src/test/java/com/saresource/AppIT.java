package com.saresource;

import com.amazonaws.regions.Regions;
import com.saresource.aws.AwsRedshiftDatabaseIO;
import com.saresource.aws.AwsS3IO;
import com.saresource.drillinginfo.directaccess.DrillingInfoDirectAccess;
import com.saresource.drillinginfo.directaccess.pojo.v1.ProductionHeader;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppIT {
    private final DrillingInfoDirectAccess diDa = new DrillingInfoDirectAccess();
    private final AwsRedshiftDatabaseIO dbIo = AwsRedshiftDatabaseIO.build();

    @Test
    public void createS3ContentToCopyTest() throws IOException {
        List<ProductionHeader> headers = new ArrayList<>(diDa.getProductionHeaders("CO"));

        Path path = Files.createTempFile("headersPipeDelimited", ".txt");
        File file = path.toFile();
        file.deleteOnExit();
        headers.forEach(productionHeader -> {
            try {
                Files.write(path,
                        (productionHeader.toString() + System.lineSeparator()).getBytes(Charsets.UTF_8),
                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        AwsS3IO s3IO = new AwsS3IO(Regions.US_EAST_1);
        final String existingBucketName = "sa-resources-s3-bucket/drilling-info/load";
        final String newFileName = file.getName();

        s3IO.upload(existingBucketName, newFileName, file);
    }

    @Test
    public void copyS3DataIntoRedshiftTest() {
        String query = "copy production_headers_2 from " +
                "'s3://sa-resources-s3-bucket/drilling-info/load' iam_role " +
                "'arn:aws:iam::673800128558:role/s3-to-redshift'";

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int updateQuery = dbIo.updateQuery(query);
        stopWatch.stop();
        System.out.println("Copy took: " + stopWatch);
        System.out.println("Query result: " + updateQuery);
    }

    @Test
    public void drillingInfoLoadedIntoS3ThenRedshiftTest() throws IOException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<ProductionHeader> headers = new ArrayList<>(diDa.getProductionHeaders("CO"));
        stopWatch.stop();
        System.out.println("Getting " + headers.size() + " from Drilling Info headers took: " + stopWatch);

        stopWatch.reset();
        stopWatch.start();
        Path path = Files.createTempFile("headersPipeDelimited", ".txt");
        File file = path.toFile();
        FileChannel rwChannel = new RandomAccessFile(file.getName(), "rw").getChannel();
        file.deleteOnExit();
        headers.forEach(productionHeader -> {
            byte[] buffer = (productionHeader.toString() + System.lineSeparator()).getBytes();
            ByteBuffer wrBuf;
            try {
                wrBuf = rwChannel.map(FileChannel.MapMode.READ_WRITE, 0, buffer.length);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            wrBuf.put(buffer);
//            try {
//                Files.write(path,
//                        (productionHeader.toString() + System.lineSeparator()).getBytes(Charsets.UTF_8),
//                        StandardOpenOption.CREATE, StandardOpenOption.APPEND);
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        });
        stopWatch.stop();
        System.out.println("Creating temporary data file took: " + stopWatch);

        stopWatch.reset();
        stopWatch.start();
        AwsS3IO s3IO = new AwsS3IO(Regions.US_EAST_1);
        final String existingBucketName = "sa-resources-s3-bucket/drilling-info/load";
        final String newFileName = file.getName();
        s3IO.upload(existingBucketName, newFileName, file);
        stopWatch.stop();
        System.out.println("Uploading temporary data file to S3 took: " + stopWatch);

        String query = "copy production_headers_2 from " +
                "'s3://sa-resources-s3-bucket/drilling-info/load' iam_role " +
                "'arn:aws:iam::673800128558:role/s3-to-redshift'";

        stopWatch.reset();
        stopWatch.start();
        int updateQuery = dbIo.updateQuery(query);
        stopWatch.stop();
        System.out.println("Copy from S3 to Redshift took: " + stopWatch);
        System.out.println("Copy query result: " + updateQuery);
    }

    @Test
    public void drillingInfoLoadedIntoRedshiftTest() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<ProductionHeader> headers = new ArrayList<>(diDa.getProductionHeaders("CO"));
        stopWatch.stop();
        System.out.println("Getting " + headers.size() + " headers took " + stopWatch);

//        dbIo.updateQuery("insert into production_headers (id, entity_type, entity_id, district) " +
//                "values (2, 'test entity type 2', 4, 'test district 2')");

        int[] ints = dbIo.batchUpdateQuery("insert into production_headers_2 " +
                "(id, entity_id, district, entity_type, prod_type, state_province, country, " +
                "lease_name, api_uwi, initial_completion_date, field, reservoir, regulatory_number, " +
                "county_parish, well_number, current_operator, oil_gatherer, gas_gatherer, " +
                "current_producing_status, wellbore_orientation, total_depth, lower_perf, " +
                "upper_perf, liq_grav, gas_grav, daily_oil, daily_gas, cum_gas, cum_oil, " +
                "cum_water, entity_latitude, entity_longitude, first_prod_date, last_prod_date, " +
                "latest_well_count, gor_last_12, basin, peak_gas, peak_oil, api_awi_list, " +
                "max_active_wells, months_produced, master_current_operator, alloc_plus, " +
                "oil_gravity, first_month_oil, first_month_gas, first_month_water, first_12_oil, " +
                "first_12_gas, first_12_water, last_12_oil, last_12_gas, last_12_water, " +
                "second_month_gor, latest_gor, last_12_yield, second_month_yield, latest_yield, " +
                "spud_year, peak_gas_month, peak_oil_month, peak_boe, peak_boe_month, peak_mmcfge, " +
                "peak_mmcfge_month, latest_test_year, latest_flow_pressure, latest_whsip, " +
                "first_6_oil, first_6_gas, first_6_water, first_24_oil, first_24_gas, " +
                "first_24_water, first_60_oil, first_60_gas, first_60_water, first_6_boe, " +
                "first_12_boe, first_24_boe, first_60_boe, first_12_mmcfge, first_24_mmcfge, " +
                "first_60_mmcfge, prac_ip_oil_daily, prac_ip_gas_daily, prac_ip_cfged, " +
                "prac_ip_boe, cum_mmcfge, cum_bcfge, prior12_oil, prior12_gas, prior12_wtr, " +
                "county_state)" +
                "values" +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ProductionHeader header = headers.get(i);
                ps.setInt(1, header.getId());
                ps.setInt(2, header.getEntity_id());
                ps.setString(3, header.getDistrict());
                ps.setString(4, header.getEntity_type());
                ps.setString(5, header.getProd_type());
                ps.setString(6, header.getState_province());
                ps.setString(7, header.getCountry());
                ps.setString(8, header.getLease_name());
                ps.setString(9, header.getApi_uwi());
                ps.setString(10, header.getInitial_completion_date());
                ps.setString(11, header.getField());
                ps.setString(12, header.getReservoir());
                ps.setString(13, header.getRegulatory_number());
                ps.setString(14, header.getCounty_parish());
                ps.setString(15, header.getWell_number());
                ps.setString(16, header.getCurrent_operator());
                ps.setString(17, header.getOil_gatherer());
                ps.setString(18, header.getGas_gatherer());
                ps.setString(19, header.getCurrent_producing_status());
                ps.setString(20, header.getWellbore_orientation());
                ps.setInt(21, header.getTotal_depth());
                ps.setInt(22, header.getLower_perf());
                ps.setInt(23, header.getUpper_perf());
                ps.setDouble(24, header.getLiq_grav());
                ps.setDouble(25, header.getGas_grav());
                ps.setInt(26, header.getDaily_oil());
                ps.setInt(27, header.getDaily_gas());
                ps.setLong(28, header.getCum_gas());
                ps.setInt(29, header.getCum_oil());
                ps.setInt(30, header.getCum_water());
                ps.setDouble(31, header.getEntity_latitude());
                ps.setDouble(32, header.getEntity_longitude());
                ps.setString(33, header.getFirst_prod_date());
                ps.setString(34, header.getLast_prod_date());
                ps.setInt(35, header.getLatest_well_count());
                ps.setDouble(36, header.getGor_last_12());
                ps.setString(37, header.getBasin());
                ps.setInt(38, header.getPeak_gas());
                ps.setInt(39, header.getPeak_oil());
                ps.setString(40, header.getApi_awi_list());
                ps.setInt(41, header.getMax_active_wells());
                ps.setInt(42, header.getMonths_produced());
                ps.setString(43, header.getMaster_current_operator());
                ps.setString(44, header.getAlloc_plus());
                ps.setDouble(45, header.getOil_gravity());
                ps.setInt(46, header.getFirst_month_oil());
                ps.setInt(47, header.getFirst_month_gas());
                ps.setInt(48, header.getFirst_month_water());
                ps.setInt(49, header.getFirst_12_oil());
                ps.setInt(50, header.getFirst_12_gas());
                ps.setInt(51, header.getFirst_12_water());
                ps.setInt(52, header.getLast_12_oil());
                ps.setInt(53, header.getLast_12_gas());
                ps.setInt(54, header.getLast_12_water());
                ps.setInt(55, header.getSecond_month_gor());
                ps.setInt(56, header.getLatest_gor());
                ps.setInt(57, header.getLast_12_yield());
                ps.setInt(58, header.getSecond_month_yield());
                ps.setInt(59, header.getLatest_yield());
                ps.setString(60, header.getSpud_year());
                ps.setInt(61, header.getPeak_gas_month());
                ps.setInt(62, header.getPeak_oil_month());
                ps.setInt(63, header.getPeak_boe());
                ps.setInt(64, header.getPeak_boe_month());
                ps.setInt(65, header.getPeak_mmcfge());
                ps.setInt(66, header.getPeak_mmcfge_month());
                ps.setString(67, header.getLatest_test_year());
                ps.setInt(68, header.getLatest_flow_pressure());
                ps.setInt(69, header.getLatest_whsip());
                ps.setInt(70, header.getFirst_6_oil());
                ps.setInt(71, header.getFirst_6_gas());
                ps.setInt(72, header.getFirst_6_water());
                ps.setString(73, header.getFirst_24_oil());
                ps.setInt(74, header.getFirst_24_gas());
                ps.setInt(75, header.getFirst_24_water());
                ps.setInt(76, header.getFirst_60_oil());
                ps.setInt(77, header.getFirst_60_gas());
                ps.setInt(78, header.getFirst_60_water());
                ps.setInt(79, header.getFirst_6_boe());
                ps.setInt(80, header.getFirst_12_boe());
                ps.setString(81, header.getFirst_24_boe());
                ps.setInt(82, header.getFirst_60_boe());
                ps.setInt(83, header.getFirst_12_mmcfge());
                ps.setInt(84, header.getFirst_24_mmcfge());
                ps.setInt(85, header.getFirst_60_mmcfge());
                ps.setInt(86, header.getPrac_ip_oil_daily());
                ps.setInt(87, header.getPrac_ip_gas_daily());
                ps.setInt(88, header.getPrac_ip_cfged());
                ps.setInt(89, header.getPrac_ip_boe());
                ps.setInt(90, header.getCum_mmcfge());
                ps.setInt(91, header.getCum_bcfge());
                ps.setInt(92, header.getPrior12_oil());
                ps.setInt(93, header.getPrior12_gas());
                ps.setInt(94, header.getPrior12_wtr());
                ps.setString(95, header.getCounty_state());
            }

            @Override
            public int getBatchSize() {
                return headers.size();
            }
        });

        System.out.println("Returned integers: " + Arrays.toString(ints));
    }
}
