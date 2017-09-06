package com.saresource;

import com.amazonaws.regions.Regions;
import com.saresource.aws.AwsJsonArray;
import com.saresource.aws.AwsRedshiftDatabaseIO;
import com.saresource.aws.AwsS3IO;
import com.saresource.drillinginfo.directaccess.DrillingInfoData;
import com.saresource.drillinginfo.directaccess.DrillingInfoDataFormat;
import com.saresource.drillinginfo.directaccess.DrillingInfoDirectAccess;
import com.saresource.drillinginfo.directaccess.pojo.v1.ProducingEntityStats;
import com.saresource.drillinginfo.directaccess.pojo.v1.RigAnalytics;
import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AppIT {
    private final DrillingInfoDirectAccess diDa = new DrillingInfoDirectAccess();
    private final AwsRedshiftDatabaseIO dbIo = AwsRedshiftDatabaseIO.build();
    private final AwsS3IO s3IO = new AwsS3IO(Regions.US_EAST_1);
    private final String bucketName = "sa-resources-s3-bucket/drilling-info/load";
    private final String iamRole = "arn:aws:iam::673800128558:role/s3-to-redshift";

    @Test
    public void createS3ContentToCopyTest() throws IOException {
        List<DrillingInfoData> headers = new ArrayList<>(diDa.getProductionHeaders("CO"));

        DrillingInfoData data = new DrillingInfoData(headers);

        Path path = Files.createTempFile("production-headers", ".csv");
        File file = path.toFile();
        file.deleteOnExit();

        try {
            String csv = data.serializeAs(DrillingInfoDataFormat.CSV);
            Files.write(path,
                    (csv + System.lineSeparator()).getBytes(Charsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        s3IO.copyFileToAwsS3(file, bucketName);
    }

    @Test
    public void copyS3DataIntoRedshiftTest() {
        dropAndCreateDatabaseTables();

        String inputFileName = "production-entities.json";
        final File file = new File(this.getClass().getResource(inputFileName).getPath());
        s3IO.copyFileToAwsS3(file, bucketName);

        String query = String.format("copy production_headers from " +
                "'s3://sa-resources-s3-bucket/drilling-info/load/%s' iam_role " +
                "'arn:aws:iam::673800128558:role/s3-to-redshift'", inputFileName);
        int updateQuery = dbIo.updateQuery(query);
        System.out.println("Query result: " + updateQuery);
    }

    /**
     * Grand-daddy test that really represents entire project. Always keep this test working!!!
     */
    @Test
    public void drillingInfoLoadedIntoS3ThenRedshiftTest() {
        dropAndCreateDatabaseTables();

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(this::processProductionHeaders);
        executor.submit(this::processRigAnalytics);
        executor.submit(this::processProducingEntityStats);

        executor.shutdown();
        try {
            executor.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (!executor.isTerminated()) {
                System.out.println("Cancel non-terminated tasks");
            }
            executor.shutdownNow();
            System.out.println("Shutdown finished");
        }

    }

    private void dropAndCreateDatabaseTables() {
        dbIo.updateQuery("drop table if exists production_headers");
        dbIo.updateQuery("drop table if exists rig_analytics");
        dbIo.updateQuery("drop table if exists producing_entity_stats");
        createProductionHeadersTable();
        createRigAnalyticsTable();
        createProducingEntityStatsTable();
    }

    private void createProductionHeadersTable() {
        dbIo.executeQuery("create table if not exists production_headers (\n" +
                "  id integer primary key,\n" +
                "  entity_id\tinteger,\n" +
                "  district\tvarchar(255),\n" +
                "  entity_type\tvarchar(255),\n" +
                "  prod_type\tvarchar(255),\n" +
                "  state_province\tvarchar(255),\n" +
                "  country\tvarchar(255),\n" +
                "  lease_name\tvarchar(255),\n" +
                "  api_uwi\tvarchar(255),\n" +
                "  initial_completion_date\tvarchar(255),\n" +
                "  field\tvarchar(255),\n" +
                "  reservoir\tvarchar(255),\n" +
                "  regulatory_number\tvarchar(255),\n" +
                "  county_parish\tvarchar(255),\n" +
                "  well_number\tvarchar(255),\n" +
                "  current_operator\tvarchar(255),\n" +
                "  oil_gatherer\tvarchar(255),\n" +
                "  gas_gatherer\tvarchar(255),\n" +
                "  current_producing_status\tvarchar(255),\n" +
                "  wellbore_orientation\tvarchar(255),\n" +
                "  total_depth\tinteger,\n" +
                "  lower_perf\tinteger,\n" +
                "  upper_perf\tinteger,\n" +
                "  liq_grav\tnumeric(20,8),\n" +
                "  gas_grav\tnumeric(20,8),\n" +
                "  daily_oil\tinteger,\n" +
                "  daily_gas\tinteger,\n" +
                "  cum_gas\tbigint,\n" +
                "  cum_oil\tinteger,\n" +
                "  cum_water\tinteger,\n" +
                "  entity_latitude\tnumeric(20,8),\n" +
                "  entity_longitude\tnumeric(20,8),\n" +
                "  first_prod_date\tvarchar(255),\n" +
                "  last_prod_date\tvarchar(255),\n" +
                "  latest_well_count\tinteger,\n" +
                "  gor_last_12\tnumeric(20,6),\n" +
                "  basin\tvarchar(255),\n" +
                "  peak_gas\tinteger,\n" +
                "  peak_oil\tinteger,\n" +
                "  api_awi_list\tvarchar(255),\n" +
                "  max_active_wells\tinteger,\n" +
                "  months_produced\tinteger,\n" +
                "  master_current_operator\tvarchar(255),\n" +
                "  alloc_plus\tvarchar(255),\n" +
                "  oil_gravity\tnumeric(20,6),\n" +
                "  first_month_oil\tinteger,\n" +
                "  first_month_gas\tinteger,\n" +
                "  first_month_water\tinteger,\n" +
                "  first_12_oil\tinteger,\n" +
                "  first_12_gas\tinteger,\n" +
                "  first_12_water\tinteger,\n" +
                "  last_12_oil\tinteger,\n" +
                "  last_12_gas\tinteger,\n" +
                "  last_12_water\tinteger,\n" +
                "  second_month_gor\tinteger,\n" +
                "  latest_gor\tinteger,\n" +
                "  last_12_yield\tinteger,\n" +
                "  second_month_yield\tinteger,\n" +
                "  latest_yield\tinteger,\n" +
                "  spud_year\tvarchar(255),\n" +
                "  peak_gas_month\tinteger,\n" +
                "  peak_oil_month\tinteger,\n" +
                "  peak_boe\tinteger,\n" +
                "  peak_boe_month\tinteger,\n" +
                "  peak_mmcfge\tinteger,\n" +
                "  peak_mmcfge_month\tinteger,\n" +
                "  latest_test_year\tvarchar(255),\n" +
                "  latest_flow_pressure\tinteger,\n" +
                "  latest_whsip\tinteger,\n" +
                "  first_6_oil\tinteger,\n" +
                "  first_6_gas\tinteger,\n" +
                "  first_6_water\tinteger,\n" +
                "  first_24_oil\tvarchar(255),\n" +
                "  first_24_gas\tinteger,\n" +
                "  first_24_water\tinteger,\n" +
                "  first_60_oil\tinteger,\n" +
                "  first_60_gas\tinteger,\n" +
                "  first_60_water\tinteger,\n" +
                "  first_6_boe\tinteger,\n" +
                "  first_12_boe\tinteger,\n" +
                "  first_24_boe\tvarchar(255),\n" +
                "  first_60_boe\tinteger,\n" +
                "  first_12_mmcfge\tinteger,\n" +
                "  first_24_mmcfge\tinteger,\n" +
                "  first_60_mmcfge\tinteger,\n" +
                "  prac_ip_oil_daily\tinteger,\n" +
                "  prac_ip_gas_daily\tinteger,\n" +
                "  prac_ip_cfged\tinteger,\n" +
                "  prac_ip_boe\tinteger,\n" +
                "  cum_mmcfge\tinteger,\n" +
                "  cum_bcfge\tinteger,\n" +
                "  prior12_oil\tinteger,\n" +
                "  prior12_gas\tinteger,\n" +
                "  prior12_wtr\tinteger,\n" +
                "  county_state\tvarchar(255),\n" +
                "  \n" +
                "  constraint unique_production_header_entity_id unique (entity_id)\n" +
                ")");
    }

    private void createRigAnalyticsTable() {
        dbIo.executeQuery("create table if not exists rig_analytics (\n" +
                "    generatedspuddate varchar(255),\n" +
                "    countyid integer,\n" +
                "    deviceid varchar(255),\n" +
                "    productionstatus varchar(255),\n" +
                "    jobid varchar(255),\n" +
                "    riglocationsource varchar(255),\n" +
                "    wellnumber varchar(255),\n" +
                "    state varchar(255),\n" +
                "    permitlatitude numeric(20,6),\n" +
                "    releasedate varchar(255),\n" +
                "    wellid integer,\n" +
                "    ratedwaterdepth varchar(255),\n" +
                "    formation varchar(255),\n" +
                "    playpermitanalytics varchar(255),\n" +
                "    daysonlocation integer,\n" +
                "    permittype varchar(255),\n" +
                "    riglatitude numeric(20,6),\n" +
                "    spuddate varchar(255),\n" +
                "    rignumber varchar(255),\n" +
                "    operatororiginal varchar(255),\n" +
                "    apivisitnumber integer,\n" +
                "    wellorientation varchar(255),\n" +
                "    permitlocationcountyname varchar(255),\n" +
                "    wellpurpose varchar(255),\n" +
                "    dialoaddatetime varchar(255),\n" +
                "    temporarylocation varchar(255),\n" +
                "    offshoreflag varchar(255),\n" +
                "    activeflag varchar(255),\n" +
                "    permitnumber varchar(255),\n" +
                "    distancetonext integer,\n" +
                "    drillingtrajectory varchar(255),\n" +
                "    county varchar(255),\n" +
                "    api10 varchar(255),\n" +
                "    permitdepth varchar(255),\n" +
                "    righp varchar(255),\n" +
                "    driller varchar(255),\n" +
                "    rigclass varchar(255),\n" +
                "    riglongitude varchar(255),\n" +
                "    productdescription varchar(255),\n" +
                "    sourceid varchar(255),\n" +
                "    leaseid varchar(255),\n" +
                "    operator varchar(255),\n" +
                "    origin varchar(255),\n" +
                "    rigvisitnumber varchar(255),\n" +
                "    joinflag varchar(255),\n" +
                "    firstproductiondate varchar(255),\n" +
                "    distancefromlast varchar(255),\n" +
                "    formationdepth varchar(255),\n" +
                "    drawworks varchar(255),\n" +
                "    stateid varchar(255),\n" +
                "    api varchar(255),\n" +
                "    maxmove varchar(255),\n" +
                "    rigidapiorpermit varchar(255),\n" +
                "    riglocationcountyname varchar(255),\n" +
                "    rigid varchar(255),\n" +
                "    permitlongitude integer,\n" +
                "    ownernumber integer,\n" +
                "    apiorpermit integer,\n" +
                "    rigtype varchar(255),\n" +
                "    commodity varchar(255),\n" +
                "    leasename varchar(255),\n" +
                "    permitid varchar(255),\n" +
                "    product varchar(255),\n" +
                "    permitapprovaldate varchar(255)\n" +
                ")");
    }

    private void createProducingEntityStatsTable() {
        dbIo.executeQuery("create table if not exists producing_entity_stats (\n" +
                "\tBcfgeCum varchar(255),\n" +
                "\tBeEndDate varchar(255),\n" +
                "\tBeFinalRate varchar(255),\n" +
                "\tBeGasDeltaEur varchar(255),\n" +
                "\tBeGasDeltaEurPct varchar(255),\n" +
                "\tBeGasEur varchar(255),\n" +
                "\tBeGasRrr varchar(255),\n" +
                "\tBeInitialDecline varchar(255),\n" +
                "\tBeInitialRate varchar(255),\n" +
                "\tBeOilDeltaEur varchar(255),\n" +
                "\tBeOilDeltaEurPct varchar(255),\n" +
                "\tBeOilEur varchar(255),\n" +
                "\tBeOilRrr varchar(255),\n" +
                "\tBebFactor varchar(255),\n" +
                "\tBoeCum varchar(255),\n" +
                "\tBoePracIp varchar(255),\n" +
                "\tCfgedCurRate varchar(255),\n" +
                "\tCfgedPracIp varchar(255),\n" +
                "\tCreatedDate varchar(255),\n" +
                "\tDecl12MoGas varchar(255),\n" +
                "\tDecl12MoLiq varchar(255),\n" +
                "\tDecl12MoWtr varchar(255),\n" +
                "\tDecl24MoGas varchar(255),\n" +
                "\tDecl24MoLiq varchar(255),\n" +
                "\tDecl24MoWtr varchar(255),\n" +
                "\tDecl3MoGas varchar(255),\n" +
                "\tDecl3MoLiq varchar(255),\n" +
                "\tDecl3MoWtr varchar(255),\n" +
                "\tDecl60MoGas varchar(255),\n" +
                "\tDecl60MoLiq varchar(255),\n" +
                "\tDecl60MoWtr varchar(255),\n" +
                "\tDeletedDate varchar(255),\n" +
                "\tEntityId varchar(255),\n" +
                "\tEurCalcDate varchar(255),\n" +
                "\tEurPrimaryProduct varchar(255),\n" +
                "\tFirst12Boe varchar(255),\n" +
                "\tFirst12Gas varchar(255),\n" +
                "\tFirst12Liq varchar(255),\n" +
                "\tFirst12Mmcfge varchar(255),\n" +
                "\tFirst12Wtr varchar(255),\n" +
                "\tFirst24Boe varchar(255),\n" +
                "\tFirst24Gas varchar(255),\n" +
                "\tFirst24Liq varchar(255),\n" +
                "\tFirst24Mmcfge varchar(255),\n" +
                "\tFirst24Wtr varchar(255),\n" +
                "\tFirst60Boe varchar(255),\n" +
                "\tFirst60Gas varchar(255),\n" +
                "\tFirst60Liq varchar(255),\n" +
                "\tFirst60Mmcfge varchar(255),\n" +
                "\tFirst60Wtr varchar(255),\n" +
                "\tFirst6Boe varchar(255),\n" +
                "\tFirst6Gas varchar(255),\n" +
                "\tFirst6Liq varchar(255),\n" +
                "\tFirst6Mmcfge varchar(255),\n" +
                "\tFirst6Wtr varchar(255),\n" +
                "\tFirstGas varchar(255),\n" +
                "\tFirstLiq varchar(255),\n" +
                "\tFirstProdDate varchar(255),\n" +
                "\tFirstWtr varchar(255),\n" +
                "\tFullBFactor varchar(255),\n" +
                "\tFullEndDate varchar(255),\n" +
                "\tFullFinalRate varchar(255),\n" +
                "\tFullGasEur varchar(255),\n" +
                "\tFullGasRrr varchar(255),\n" +
                "\tFullInitialDecline varchar(255),\n" +
                "\tFullOilEur varchar(255),\n" +
                "\tFullOilRrr varchar(255),\n" +
                "\tGasCum varchar(255),\n" +
                "\tGasDaily varchar(255),\n" +
                "\tGasMaxDaily varchar(255),\n" +
                "\tGasPracIpDaily varchar(255),\n" +
                "\tGasYear varchar(255),\n" +
                "\tGor varchar(255),\n" +
                "\tGor2ndMo varchar(255),\n" +
                "\tGorCum varchar(255),\n" +
                "\tGorLatestMo varchar(255),\n" +
                "\tIsDeleted varchar(255),\n" +
                "\tLastProdDate varchar(255),\n" +
                "\tLatestFlowRes varchar(255),\n" +
                "\tLatestGas varchar(255),\n" +
                "\tLatestLiq varchar(255),\n" +
                "\tLatestTestDate varchar(255),\n" +
                "\tLatestWcnt varchar(255),\n" +
                "\tLatestWhsip varchar(255),\n" +
                "\tLatestWtr varchar(255),\n" +
                "\tLiqCum varchar(255),\n" +
                "\tLiqDaily varchar(255),\n" +
                "\tLiqMaxDaily varchar(255),\n" +
                "\tLiqPracIpDaily varchar(255),\n" +
                "\tLiqYear varchar(255),\n" +
                "\tMaxActvWells varchar(255),\n" +
                "\tMaxWtrDaily varchar(255),\n" +
                "\tMmcfgeCum varchar(255),\n" +
                "\tMonthsProduced varchar(255),\n" +
                "\tPdenGranularity varchar(255),\n" +
                "\tPeakBoe varchar(255),\n" +
                "\tPeakBoeMonth varchar(255),\n" +
                "\tPeakBoeMonthNo varchar(255),\n" +
                "\tPeakDailyGasMonth varchar(255),\n" +
                "\tPeakDailyGasMonthNo varchar(255),\n" +
                "\tPeakDailyLiqMonth varchar(255),\n" +
                "\tPeakDailyLiqMonthNo varchar(255),\n" +
                "\tPeakGas varchar(255),\n" +
                "\tPeakGasDaily varchar(255),\n" +
                "\tPeakGasMonth varchar(255),\n" +
                "\tPeakGasMonthNo varchar(255),\n" +
                "\tPeakLiq varchar(255),\n" +
                "\tPeakLiqDaily varchar(255),\n" +
                "\tPeakLiqMonth varchar(255),\n" +
                "\tPeakLiqMonthNo varchar(255),\n" +
                "\tPeakMmcfge varchar(255),\n" +
                "\tPeakMmcfgeMonth varchar(255),\n" +
                "\tPeakMmcfgeMonthNo varchar(255),\n" +
                "\tPrior12Gas varchar(255),\n" +
                "\tPrior12Liq varchar(255),\n" +
                "\tPrior12Wtr varchar(255),\n" +
                "\tPriorGasCum varchar(255),\n" +
                "\tPriorLiqCum varchar(255),\n" +
                "\tPriorWtrCum varchar(255),\n" +
                "\tUpdatedDate varchar(255),\n" +
                "\tWtrCum varchar(255),\n" +
                "\tWtrDaily varchar(255),\n" +
                "\tWtrYear varchar(255),\n" +
                "\tYield varchar(255),\n" +
                "\tYield2ndMo varchar(255),\n" +
                "\tYieldLatestMo varchar(255),\n" +
                "\n" +
                "  constraint unique_producing_entity_stats_entity_id unique (EntityId)\n" +
                ")");
    }

    private void processProductionHeaders() {
        List<DrillingInfoData> headers = getDrillingInfoProductionHeaders();
        File jsonFile = writeProductionHeadersToFile(headers);
        File csvFile;
        try {
            AwsJsonArray awsJsonArray = new AwsJsonArray(new FileInputStream(jsonFile));
            csvFile = writeProductionHeaderCsvToFile(awsJsonArray.getCsvString());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        s3IO.copyFileToAwsS3(jsonFile, bucketName);
        dbIo.copyAwsS3FileToRedshift("production_headers", bucketName, csvFile.getName(), iamRole, "csv IGNOREHEADER AS 1");
    }

    private void processRigAnalytics() {
        List<RigAnalytics> analytics = getDrillingInfoRigAnalytics();
        File file = writeRigAnalyticsToFile(analytics);
        s3IO.copyFileToAwsS3(file, bucketName);
        dbIo.copyAwsS3FileToRedshift("rig_analytics", bucketName, file.getName(), iamRole);
    }

    private void processProducingEntityStats() {
        List<ProducingEntityStats> stats = diDa.getProducingEntityStats();
        File file = writeProducingEntityStatsToFile(stats);
        s3IO.copyFileToAwsS3(file, bucketName);
        dbIo.copyAwsS3FileToRedshift("producing_entity_stats", bucketName, file.getName(), iamRole);
    }

    private List<DrillingInfoData> getDrillingInfoProductionHeaders() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<DrillingInfoData> headers = new ArrayList<>(diDa.getProductionHeaders("CO"));
        stopWatch.stop();
        System.out.println("Getting " + headers.size() + " from Drilling Info headers took: " + stopWatch);
        return headers;
    }

    private List<RigAnalytics> getDrillingInfoRigAnalytics() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<RigAnalytics> analytics = new ArrayList<>(diDa.getRigAnalytics("Colorado"));
        stopWatch.stop();
        System.out.println("Getting " + analytics.size() + " from Drilling Info analytics took: " + stopWatch);
        return analytics;
    }

    private File writeProductionHeadersToFile(List<DrillingInfoData> headers) {
        StopWatch fileStopWatch = new StopWatch();
        fileStopWatch.reset();
        fileStopWatch.start();
        Path path;
        try {
            path = Files.createTempFile("productionHeaders", ".json");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        DrillingInfoData data = new DrillingInfoData(headers);

        try {
            String csv = data.serializeAs(DrillingInfoDataFormat.CSV);
            Files.write(path,
                    (csv + System.lineSeparator()).getBytes(Charsets.UTF_8),
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileStopWatch.stop();
        //TODO ru: Was this fast enough?
        System.out.println(String.format("Took %s to create header file", fileStopWatch));

        return path.toFile();
    }

    private File writeProductionHeaderCsvToFile(String headers) {
        StopWatch fileStopWatch = new StopWatch();
        fileStopWatch.reset();
        fileStopWatch.start();
        Path path;
        try {
            path = Files.createTempFile("productionHeaders", ".csv");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File file = path.toFile();
        FileWriter fr = null;
        BufferedWriter br = null;
        try {
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);
            br.write(headers);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fileStopWatch.stop();
        System.out.println("Creating temporary data file took: " + fileStopWatch);
        return file;
    }

    //TODO ru: Can this and other write functions be generalized?
    private File writeProducingEntityStatsToFile(List<ProducingEntityStats> stats) {
        StopWatch fileStopWatch = new StopWatch();
        fileStopWatch.reset();
        fileStopWatch.start();
        Path path;
        try {
            path = Files.createTempFile("producingEntityStatsPipeDelimited", ".txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File file = path.toFile();
        FileWriter fr = null;
        BufferedWriter br = null;
        try {
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);
            for (ProducingEntityStats stat : stats) {
                String dataWithNewLine = stat.toString() + System.lineSeparator();
                try {
                    br.write(dataWithNewLine);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fileStopWatch.stop();
        System.out.println("Creating temporary data file took: " + fileStopWatch);
        return file;
    }

    private File writeRigAnalyticsToFile(List<RigAnalytics> analytics) {
        StopWatch fileStopWatch = new StopWatch();
        fileStopWatch.reset();
        fileStopWatch.start();
        Path path;
        try {
            path = Files.createTempFile("analyticsPipeDelimited", ".txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File file = path.toFile();
        FileWriter fr = null;
        BufferedWriter br = null;
        try {
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);
            for (RigAnalytics analytic : analytics) {
                String dataWithNewLine = analytic.toString() + System.lineSeparator();
                try {
                    br.write(dataWithNewLine);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fileStopWatch.stop();
        System.out.println("Creating temporary data file took: " + fileStopWatch);
        return file;
    }

    //TODO ru: either delete or rewrite to use copy from s3 bucket. Direct insert query is not best way to go.
//    @Test
//    public void drillingInfoLoadedIntoRedshiftTest() {
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//        List<String> headers = new ArrayList<>(diDa.getProductionHeaders("CO"));
//        stopWatch.stop();
//        System.out.println("Getting " + headers.size() + " headers took " + stopWatch);
//
//        dbIo.updateQuery("insert into production_headers (id, entity_type, entity_id, district) " +
//                "values (2, 'test entity type 2', 4, 'test district 2')");
//
//        int[] ints = dbIo.batchUpdateQuery("insert into production_headers " +
//                "(id, entity_id, district, entity_type, prod_type, state_province, country, " +
//                "lease_name, api_uwi, initial_completion_date, field, reservoir, regulatory_number, " +
//                "county_parish, well_number, current_operator, oil_gatherer, gas_gatherer, " +
//                "current_producing_status, wellbore_orientation, total_depth, lower_perf, " +
//                "upper_perf, liq_grav, gas_grav, daily_oil, daily_gas, cum_gas, cum_oil, " +
//                "cum_water, entity_latitude, entity_longitude, first_prod_date, last_prod_date, " +
//                "latest_well_count, gor_last_12, basin, peak_gas, peak_oil, api_awi_list, " +
//                "max_active_wells, months_produced, master_current_operator, alloc_plus, " +
//                "oil_gravity, first_month_oil, first_month_gas, first_month_water, first_12_oil, " +
//                "first_12_gas, first_12_water, last_12_oil, last_12_gas, last_12_water, " +
//                "second_month_gor, latest_gor, last_12_yield, second_month_yield, latest_yield, " +
//                "spud_year, peak_gas_month, peak_oil_month, peak_boe, peak_boe_month, peak_mmcfge, " +
//                "peak_mmcfge_month, latest_test_year, latest_flow_pressure, latest_whsip, " +
//                "first_6_oil, first_6_gas, first_6_water, first_24_oil, first_24_gas, " +
//                "first_24_water, first_60_oil, first_60_gas, first_60_water, first_6_boe, " +
//                "first_12_boe, first_24_boe, first_60_boe, first_12_mmcfge, first_24_mmcfge, " +
//                "first_60_mmcfge, prac_ip_oil_daily, prac_ip_gas_daily, prac_ip_cfged, " +
//                "prac_ip_boe, cum_mmcfge, cum_bcfge, prior12_oil, prior12_gas, prior12_wtr, " +
//                "county_state)" +
//                "values" +
//                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
//                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
//                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " +
//                "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", new BatchPreparedStatementSetter() {
//            @Override
//            public void setValues(PreparedStatement ps, int i) throws SQLException {
//                ProductionHeader header = headers.get(i);
//                ps.setInt(1, header.getId());
//                ps.setInt(2, header.getEntity_id());
//                ps.setString(3, header.getDistrict());
//                ps.setString(4, header.getEntity_type());
//                ps.setString(5, header.getProd_type());
//                ps.setString(6, header.getState_province());
//                ps.setString(7, header.getCountry());
//                ps.setString(8, header.getLease_name());
//                ps.setString(9, header.getApi_uwi());
//                ps.setString(10, header.getInitial_completion_date());
//                ps.setString(11, header.getField());
//                ps.setString(12, header.getReservoir());
//                ps.setString(13, header.getRegulatory_number());
//                ps.setString(14, header.getCounty_parish());
//                ps.setString(15, header.getWell_number());
//                ps.setString(16, header.getCurrent_operator());
//                ps.setString(17, header.getOil_gatherer());
//                ps.setString(18, header.getGas_gatherer());
//                ps.setString(19, header.getCurrent_producing_status());
//                ps.setString(20, header.getWellbore_orientation());
//                ps.setInt(21, header.getTotal_depth());
//                ps.setInt(22, header.getLower_perf());
//                ps.setInt(23, header.getUpper_perf());
//                ps.setDouble(24, header.getLiq_grav());
//                ps.setDouble(25, header.getGas_grav());
//                ps.setInt(26, header.getDaily_oil());
//                ps.setInt(27, header.getDaily_gas());
//                ps.setLong(28, header.getCum_gas());
//                ps.setInt(29, header.getCum_oil());
//                ps.setInt(30, header.getCum_water());
//                ps.setDouble(31, header.getEntity_latitude());
//                ps.setDouble(32, header.getEntity_longitude());
//                ps.setString(33, header.getFirst_prod_date());
//                ps.setString(34, header.getLast_prod_date());
//                ps.setInt(35, header.getLatest_well_count());
//                ps.setDouble(36, header.getGor_last_12());
//                ps.setString(37, header.getBasin());
//                ps.setInt(38, header.getPeak_gas());
//                ps.setInt(39, header.getPeak_oil());
//                ps.setString(40, header.getApi_awi_list());
//                ps.setInt(41, header.getMax_active_wells());
//                ps.setInt(42, header.getMonths_produced());
//                ps.setString(43, header.getMaster_current_operator());
//                ps.setString(44, header.getAlloc_plus());
//                ps.setDouble(45, header.getOil_gravity());
//                ps.setInt(46, header.getFirst_month_oil());
//                ps.setInt(47, header.getFirst_month_gas());
//                ps.setInt(48, header.getFirst_month_water());
//                ps.setInt(49, header.getFirst_12_oil());
//                ps.setInt(50, header.getFirst_12_gas());
//                ps.setInt(51, header.getFirst_12_water());
//                ps.setInt(52, header.getLast_12_oil());
//                ps.setInt(53, header.getLast_12_gas());
//                ps.setInt(54, header.getLast_12_water());
//                ps.setInt(55, header.getSecond_month_gor());
//                ps.setInt(56, header.getLatest_gor());
//                ps.setInt(57, header.getLast_12_yield());
//                ps.setInt(58, header.getSecond_month_yield());
//                ps.setInt(59, header.getLatest_yield());
//                ps.setString(60, header.getSpud_year());
//                ps.setInt(61, header.getPeak_gas_month());
//                ps.setInt(62, header.getPeak_oil_month());
//                ps.setInt(63, header.getPeak_boe());
//                ps.setInt(64, header.getPeak_boe_month());
//                ps.setInt(65, header.getPeak_mmcfge());
//                ps.setInt(66, header.getPeak_mmcfge_month());
//                ps.setString(67, header.getLatest_test_year());
//                ps.setInt(68, header.getLatest_flow_pressure());
//                ps.setInt(69, header.getLatest_whsip());
//                ps.setInt(70, header.getFirst_6_oil());
//                ps.setInt(71, header.getFirst_6_gas());
//                ps.setInt(72, header.getFirst_6_water());
//                ps.setString(73, header.getFirst_24_oil());
//                ps.setInt(74, header.getFirst_24_gas());
//                ps.setInt(75, header.getFirst_24_water());
//                ps.setInt(76, header.getFirst_60_oil());
//                ps.setInt(77, header.getFirst_60_gas());
//                ps.setInt(78, header.getFirst_60_water());
//                ps.setInt(79, header.getFirst_6_boe());
//                ps.setInt(80, header.getFirst_12_boe());
//                ps.setString(81, header.getFirst_24_boe());
//                ps.setInt(82, header.getFirst_60_boe());
//                ps.setInt(83, header.getFirst_12_mmcfge());
//                ps.setInt(84, header.getFirst_24_mmcfge());
//                ps.setInt(85, header.getFirst_60_mmcfge());
//                ps.setInt(86, header.getPrac_ip_oil_daily());
//                ps.setInt(87, header.getPrac_ip_gas_daily());
//                ps.setInt(88, header.getPrac_ip_cfged());
//                ps.setInt(89, header.getPrac_ip_boe());
//                ps.setInt(90, header.getCum_mmcfge());
//                ps.setInt(91, header.getCum_bcfge());
//                ps.setInt(92, header.getPrior12_oil());
//                ps.setInt(93, header.getPrior12_gas());
//                ps.setInt(94, header.getPrior12_wtr());
//                ps.setString(95, header.getCounty_state());
//            }
//
//            @Override
//            public int getBatchSize() {
//                return headers.size();
//            }
//        });
//
//        System.out.println("Returned integers: " + Arrays.toString(ints));
//    }
}
