package com.saresource.drillinginfo.directaccess.pojo.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProducingEntityStats {
    private String BcfgeCum;
    private String BeEndDate;
    private String BeFinalRate;
    private String BeGasDeltaEur;
    private String BeGasDeltaEurPct;
    private String BeGasEur;
    private String BeGasRrr;
    private String BeInitialDecline;
    private String BeInitialRate;
    private String BeOilDeltaEur;
    private String BeOilDeltaEurPct;
    private String BeOilEur;
    private String BeOilRrr;
    private String BebFactor;
    private String BoeCum;
    private String BoePracIp;
    private String CfgedCurRate;
    private String CfgedPracIp;
    private String CreatedDate;
    private String Decl12MoGas;
    private String Decl12MoLiq;
    private String Decl12MoWtr;
    private String Decl24MoGas;
    private String Decl24MoLiq;
    private String Decl24MoWtr;
    private String Decl3MoGas;
    private String Decl3MoLiq;
    private String Decl3MoWtr;
    private String Decl60MoGas;
    private String Decl60MoLiq;
    private String Decl60MoWtr;
    private String DeletedDate;
    private String EntityId;
    private String EurCalcDate;
    private String EurPrimaryProduct;
    private String First12Boe;
    private String First12Gas;
    private String First12Liq;
    private String First12Mmcfge;
    private String First12Wtr;
    private String First24Boe;
    private String First24Gas;
    private String First24Liq;
    private String First24Mmcfge;
    private String First24Wtr;
    private String First60Boe;
    private String First60Gas;
    private String First60Liq;
    private String First60Mmcfge;
    private String First60Wtr;
    private String First6Boe;
    private String First6Gas;
    private String First6Liq;
    private String First6Mmcfge;
    private String First6Wtr;
    private String FirstGas;
    private String FirstLiq;
    private String FirstProdDate;
    private String FirstWtr;
    private String FullBFactor;
    private String FullEndDate;
    private String FullFinalRate;
    private String FullGasEur;
    private String FullGasRrr;
    private String FullInitialDecline;
    private String FullOilEur;
    private String FullOilRrr;
    private String GasCum;
    private String GasDaily;
    private String GasMaxDaily;
    private String GasPracIpDaily;
    private String GasYear;
    private String Gor;
    private String Gor2ndMo;
    private String GorCum;
    private String GorLatestMo;
    private String IsDeleted;
    private String LastProdDate;
    private String LatestFlowRes;
    private String LatestGas;
    private String LatestLiq;
    private String LatestTestDate;
    private String LatestWcnt;
    private String LatestWhsip;
    private String LatestWtr;
    private String LiqCum;
    private String LiqDaily;
    private String LiqMaxDaily;
    private String LiqPracIpDaily;
    private String LiqYear;
    private String MaxActvWells;
    private String MaxWtrDaily;
    private String MmcfgeCum;
    private String MonthsProduced;
    private String PdenGranularity;
    private String PeakBoe;
    private String PeakBoeMonth;
    private String PeakBoeMonthNo;
    private String PeakDailyGasMonth;
    private String PeakDailyGasMonthNo;
    private String PeakDailyLiqMonth;
    private String PeakDailyLiqMonthNo;
    private String PeakGas;
    private String PeakGasDaily;
    private String PeakGasMonth;
    private String PeakGasMonthNo;
    private String PeakLiq;
    private String PeakLiqDaily;
    private String PeakLiqMonth;
    private String PeakLiqMonthNo;
    private String PeakMmcfge;
    private String PeakMmcfgeMonth;
    private String PeakMmcfgeMonthNo;
    private String Prior12Gas;
    private String Prior12Liq;
    private String Prior12Wtr;
    private String PriorGasCum;
    private String PriorLiqCum;
    private String PriorWtrCum;
    private String UpdatedDate;
    private String WtrCum;
    private String WtrDaily;
    private String WtrYear;
    private String Yield;
    private String Yield2ndMo;
    private String YieldLatestMo;

    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|" +
                        "%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|" +
                        "%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|" +
                        "%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|" +
                        "%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s",
                BcfgeCum,
                BeEndDate,
                BeFinalRate,
                BeGasDeltaEur,
                BeGasDeltaEurPct,
                BeGasEur,
                BeGasRrr,
                BeInitialDecline,
                BeInitialRate,
                BeOilDeltaEur,
                BeOilDeltaEurPct,
                BeOilEur,
                BeOilRrr,
                BebFactor,
                BoeCum,
                BoePracIp,
                CfgedCurRate,
                CfgedPracIp,
                CreatedDate,
                Decl12MoGas,
                Decl12MoLiq,
                Decl12MoWtr,
                Decl24MoGas,
                Decl24MoLiq,
                Decl24MoWtr,
                Decl3MoGas,
                Decl3MoLiq,
                Decl3MoWtr,
                Decl60MoGas,
                Decl60MoLiq,
                Decl60MoWtr,
                DeletedDate,
                EntityId,
                EurCalcDate,
                EurPrimaryProduct,
                First12Boe,
                First12Gas,
                First12Liq,
                First12Mmcfge,
                First12Wtr,
                First24Boe,
                First24Gas,
                First24Liq,
                First24Mmcfge,
                First24Wtr,
                First60Boe,
                First60Gas,
                First60Liq,
                First60Mmcfge,
                First60Wtr,
                First6Boe,
                First6Gas,
                First6Liq,
                First6Mmcfge,
                First6Wtr,
                FirstGas,
                FirstLiq,
                FirstProdDate,
                FirstWtr,
                FullBFactor,
                FullEndDate,
                FullFinalRate,
                FullGasEur,
                FullGasRrr,
                FullInitialDecline,
                FullOilEur,
                FullOilRrr,
                GasCum,
                GasDaily,
                GasMaxDaily,
                GasPracIpDaily,
                GasYear,
                Gor,
                Gor2ndMo,
                GorCum,
                GorLatestMo,
                IsDeleted,
                LastProdDate,
                LatestFlowRes,
                LatestGas,
                LatestLiq,
                LatestTestDate,
                LatestWcnt,
                LatestWhsip,
                LatestWtr,
                LiqCum,
                LiqDaily,
                LiqMaxDaily,
                LiqPracIpDaily,
                LiqYear,
                MaxActvWells,
                MaxWtrDaily,
                MmcfgeCum,
                MonthsProduced,
                PdenGranularity,
                PeakBoe,
                PeakBoeMonth,
                PeakBoeMonthNo,
                PeakDailyGasMonth,
                PeakDailyGasMonthNo,
                PeakDailyLiqMonth,
                PeakDailyLiqMonthNo,
                PeakGas,
                PeakGasDaily,
                PeakGasMonth,
                PeakGasMonthNo,
                PeakLiq,
                PeakLiqDaily,
                PeakLiqMonth,
                PeakLiqMonthNo,
                PeakMmcfge,
                PeakMmcfgeMonth,
                PeakMmcfgeMonthNo,
                Prior12Gas,
                Prior12Liq,
                Prior12Wtr,
                PriorGasCum,
                PriorLiqCum,
                PriorWtrCum,
                UpdatedDate,
                WtrCum,
                WtrDaily,
                WtrYear,
                Yield,
                Yield2ndMo,
                YieldLatestMo
        );
    }

    public String getBcfgeCum() {
        return BcfgeCum;
    }

    @JsonProperty("BcfgeCum")
    public void setBcfgeCum(String bcfgeCum) {
        BcfgeCum = bcfgeCum;
    }

    public String getBeEndDate() {
        return BeEndDate;
    }

    @JsonProperty("BeEndDate")
    public void setBeEndDate(String beEndDate) {
        BeEndDate = beEndDate;
    }

    public String getBeFinalRate() {
        return BeFinalRate;
    }

    @JsonProperty("BeFinalRate")
    public void setBeFinalRate(String beFinalRate) {
        BeFinalRate = beFinalRate;
    }

    public String getBeGasDeltaEur() {
        return BeGasDeltaEur;
    }

    @JsonProperty("BeGasBeltaEur")
    public void setBeGasDeltaEur(String beGasDeltaEur) {
        BeGasDeltaEur = beGasDeltaEur;
    }

    public String getBeGasDeltaEurPct() {
        return BeGasDeltaEurPct;
    }

    @JsonProperty("BeGasDeltaEurPct")
    public void setBeGasDeltaEurPct(String beGasDeltaEurPct) {
        BeGasDeltaEurPct = beGasDeltaEurPct;
    }

    public String getBeGasEur() {
        return BeGasEur;
    }

    @JsonProperty("BeGasEur")
    public void setBeGasEur(String beGasEur) {
        BeGasEur = beGasEur;
    }

    public String getBeGasRrr() {
        return BeGasRrr;
    }

    @JsonProperty("BeGasRrr")
    public void setBeGasRrr(String beGasRrr) {
        BeGasRrr = beGasRrr;
    }

    public String getBeInitialDecline() {
        return BeInitialDecline;
    }

    @JsonProperty("BeInitialDecline")
    public void setBeInitialDecline(String beInitialDecline) {
        BeInitialDecline = beInitialDecline;
    }

    public String getBeInitialRate() {
        return BeInitialRate;
    }

    @JsonProperty("BeInitialRate")
    public void setBeInitialRate(String beInitialRate) {
        BeInitialRate = beInitialRate;
    }

    public String getBeOilDeltaEur() {
        return BeOilDeltaEur;
    }

    @JsonProperty("BeOilDeltaEur")
    public void setBeOilDeltaEur(String beOilDeltaEur) {
        BeOilDeltaEur = beOilDeltaEur;
    }

    public String getBeOilDeltaEurPct() {
        return BeOilDeltaEurPct;
    }

    @JsonProperty("BeOilDeltaEurPct")
    public void setBeOilDeltaEurPct(String beOilDeltaEurPct) {
        BeOilDeltaEurPct = beOilDeltaEurPct;
    }

    public String getBeOilEur() {
        return BeOilEur;
    }

    @JsonProperty("BeOilEur")
    public void setBeOilEur(String beOilEur) {
        BeOilEur = beOilEur;
    }

    public String getBeOilRrr() {
        return BeOilRrr;
    }

    @JsonProperty("BeOilRrr")
    public void setBeOilRrr(String beOilRrr) {
        BeOilRrr = beOilRrr;
    }

    public String getBebFactor() {
        return BebFactor;
    }

    @JsonProperty("BebFactor")
    public void setBebFactor(String bebFactor) {
        BebFactor = bebFactor;
    }

    public String getBoeCum() {
        return BoeCum;
    }

    @JsonProperty("BoeCum")
    public void setBoeCum(String boeCum) {
        BoeCum = boeCum;
    }

    public String getBoePracIp() {
        return BoePracIp;
    }

    @JsonProperty("BoePracIp")
    public void setBoePracIp(String boePracIp) {
        BoePracIp = boePracIp;
    }

    public String getCfgedCurRate() {
        return CfgedCurRate;
    }

    @JsonProperty("CfgedCurRate")
    public void setCfgedCurRate(String cfgedCurRate) {
        CfgedCurRate = cfgedCurRate;
    }

    public String getCfgedPracIp() {
        return CfgedPracIp;
    }

    @JsonProperty("CfgedPracIp")
    public void setCfgedPracIp(String cfgedPracIp) {
        CfgedPracIp = cfgedPracIp;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    @JsonProperty("CreatedDate")
    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getDecl12MoGas() {
        return Decl12MoGas;
    }

    @JsonProperty("Decl12MoGas")
    public void setDecl12MoGas(String decl12MoGas) {
        Decl12MoGas = decl12MoGas;
    }

    public String getDecl12MoLiq() {
        return Decl12MoLiq;
    }

    @JsonProperty("Decl12MoLiq")
    public void setDecl12MoLiq(String decl12MoLiq) {
        Decl12MoLiq = decl12MoLiq;
    }

    public String getDecl12MoWtr() {
        return Decl12MoWtr;
    }

    @JsonProperty("Decl12MoWtr")
    public void setDecl12MoWtr(String decl12MoWtr) {
        Decl12MoWtr = decl12MoWtr;
    }

    public String getDecl24MoGas() {
        return Decl24MoGas;
    }

    @JsonProperty("Decl24MoGas")
    public void setDecl24MoGas(String decl24MoGas) {
        Decl24MoGas = decl24MoGas;
    }

    public String getDecl24MoLiq() {
        return Decl24MoLiq;
    }

    @JsonProperty("Decl24MoLiq")
    public void setDecl24MoLiq(String decl24MoLiq) {
        Decl24MoLiq = decl24MoLiq;
    }

    public String getDecl24MoWtr() {
        return Decl24MoWtr;
    }

    @JsonProperty("Decl24MoWtr")
    public void setDecl24MoWtr(String decl24MoWtr) {
        Decl24MoWtr = decl24MoWtr;
    }

    public String getDecl3MoGas() {
        return Decl3MoGas;
    }

    @JsonProperty("Decl3MoGas")
    public void setDecl3MoGas(String decl3MoGas) {
        Decl3MoGas = decl3MoGas;
    }

    public String getDecl3MoLiq() {
        return Decl3MoLiq;
    }

    @JsonProperty("Decl3MoLiq")
    public void setDecl3MoLiq(String decl3MoLiq) {
        Decl3MoLiq = decl3MoLiq;
    }

    public String getDecl3MoWtr() {
        return Decl3MoWtr;
    }

    @JsonProperty("Decl3MoWtr")
    public void setDecl3MoWtr(String decl3MoWtr) {
        Decl3MoWtr = decl3MoWtr;
    }

    public String getDecl60MoGas() {
        return Decl60MoGas;
    }

    @JsonProperty("Decl60MoGas")
    public void setDecl60MoGas(String decl60MoGas) {
        Decl60MoGas = decl60MoGas;
    }

    public String getDecl60MoLiq() {
        return Decl60MoLiq;
    }

    @JsonProperty("Decl60MoLiq")
    public void setDecl60MoLiq(String decl60MoLiq) {
        Decl60MoLiq = decl60MoLiq;
    }

    public String getDecl60MoWtr() {
        return Decl60MoWtr;
    }

    @JsonProperty("Decl60MoWtr")
    public void setDecl60MoWtr(String decl60MoWtr) {
        Decl60MoWtr = decl60MoWtr;
    }

    public String getDeletedDate() {
        return DeletedDate;
    }

    @JsonProperty("DeletedDate")
    public void setDeletedDate(String deletedDate) {
        DeletedDate = deletedDate;
    }

    public String getEntityId() {
        return EntityId;
    }

    @JsonProperty("EntityId")
    public void setEntityId(String entityId) {
        EntityId = entityId;
    }

    public String getEurCalcDate() {
        return EurCalcDate;
    }

    @JsonProperty("EurCalcDate")
    public void setEurCalcDate(String eurCalcDate) {
        EurCalcDate = eurCalcDate;
    }

    public String getEurPrimaryProduct() {
        return EurPrimaryProduct;
    }

    @JsonProperty("EurPrimaryProduct")
    public void setEurPrimaryProduct(String eurPrimaryProduct) {
        EurPrimaryProduct = eurPrimaryProduct;
    }

    public String getFirst12Boe() {
        return First12Boe;
    }

    @JsonProperty("First12Boe")
    public void setFirst12Boe(String first12Boe) {
        First12Boe = first12Boe;
    }

    public String getFirst12Gas() {
        return First12Gas;
    }

    @JsonProperty("First12Gas")
    public void setFirst12Gas(String first12Gas) {
        First12Gas = first12Gas;
    }

    public String getFirst12Liq() {
        return First12Liq;
    }

    @JsonProperty("First12Liq")
    public void setFirst12Liq(String first12Liq) {
        First12Liq = first12Liq;
    }

    public String getFirst12Mmcfge() {
        return First12Mmcfge;
    }

    @JsonProperty("First12Mmcfge")
    public void setFirst12Mmcfge(String first12Mmcfge) {
        First12Mmcfge = first12Mmcfge;
    }

    public String getFirst12Wtr() {
        return First12Wtr;
    }

    @JsonProperty("First12Wtr")
    public void setFirst12Wtr(String first12Wtr) {
        First12Wtr = first12Wtr;
    }

    public String getFirst24Boe() {
        return First24Boe;
    }

    @JsonProperty("First24Boe")
    public void setFirst24Boe(String first24Boe) {
        First24Boe = first24Boe;
    }

    public String getFirst24Gas() {
        return First24Gas;
    }

    @JsonProperty("First24Gas")
    public void setFirst24Gas(String first24Gas) {
        First24Gas = first24Gas;
    }

    public String getFirst24Liq() {
        return First24Liq;
    }

    @JsonProperty("First24Liq")
    public void setFirst24Liq(String first24Liq) {
        First24Liq = first24Liq;
    }

    public String getFirst24Mmcfge() {
        return First24Mmcfge;
    }

    @JsonProperty("First24Mmcfge")
    public void setFirst24Mmcfge(String first24Mmcfge) {
        First24Mmcfge = first24Mmcfge;
    }

    public String getFirst24Wtr() {
        return First24Wtr;
    }

    @JsonProperty("First24Wtr")
    public void setFirst24Wtr(String first24Wtr) {
        First24Wtr = first24Wtr;
    }

    public String getFirst60Boe() {
        return First60Boe;
    }

    @JsonProperty("First60Boe")
    public void setFirst60Boe(String first60Boe) {
        First60Boe = first60Boe;
    }

    public String getFirst60Gas() {
        return First60Gas;
    }

    @JsonProperty("First60Gas")
    public void setFirst60Gas(String first60Gas) {
        First60Gas = first60Gas;
    }

    public String getFirst60Liq() {
        return First60Liq;
    }

    @JsonProperty("First60Liq")
    public void setFirst60Liq(String first60Liq) {
        First60Liq = first60Liq;
    }

    public String getFirst60Mmcfge() {
        return First60Mmcfge;
    }

    @JsonProperty("First60Mmcfge")
    public void setFirst60Mmcfge(String first60Mmcfge) {
        First60Mmcfge = first60Mmcfge;
    }

    public String getFirst60Wtr() {
        return First60Wtr;
    }

    @JsonProperty("First60Wtr")
    public void setFirst60Wtr(String first60Wtr) {
        First60Wtr = first60Wtr;
    }

    public String getFirst6Boe() {
        return First6Boe;
    }

    @JsonProperty("First6Boe")
    public void setFirst6Boe(String first6Boe) {
        First6Boe = first6Boe;
    }

    public String getFirst6Gas() {
        return First6Gas;
    }

    @JsonProperty("First6Gas")
    public void setFirst6Gas(String first6Gas) {
        First6Gas = first6Gas;
    }

    public String getFirst6Liq() {
        return First6Liq;
    }

    @JsonProperty("First6Liq")
    public void setFirst6Liq(String first6Liq) {
        First6Liq = first6Liq;
    }

    public String getFirst6Mmcfge() {
        return First6Mmcfge;
    }

    @JsonProperty("First6Mmcfge")
    public void setFirst6Mmcfge(String first6Mmcfge) {
        First6Mmcfge = first6Mmcfge;
    }

    public String getFirst6Wtr() {
        return First6Wtr;
    }

    @JsonProperty("First6Wtr")
    public void setFirst6Wtr(String first6Wtr) {
        First6Wtr = first6Wtr;
    }

    public String getFirstGas() {
        return FirstGas;
    }

    @JsonProperty("FirstGas")
    public void setFirstGas(String firstGas) {
        FirstGas = firstGas;
    }

    public String getFirstLiq() {
        return FirstLiq;
    }

    @JsonProperty("FirstLiq")
    public void setFirstLiq(String firstLiq) {
        FirstLiq = firstLiq;
    }

    public String getFirstProdDate() {
        return FirstProdDate;
    }

    @JsonProperty("FirstProdDate")
    public void setFirstProdDate(String firstProdDate) {
        FirstProdDate = firstProdDate;
    }

    public String getFirstWtr() {
        return FirstWtr;
    }

    @JsonProperty("FirstWtr")
    public void setFirstWtr(String firstWtr) {
        FirstWtr = firstWtr;
    }

    public String getFullBFactor() {
        return FullBFactor;
    }

    @JsonProperty("FullBFactor")
    public void setFullBFactor(String fullBFactor) {
        FullBFactor = fullBFactor;
    }

    public String getFullEndDate() {
        return FullEndDate;
    }

    @JsonProperty("FullEndDate")
    public void setFullEndDate(String fullEndDate) {
        FullEndDate = fullEndDate;
    }

    public String getFullFinalRate() {
        return FullFinalRate;
    }

    @JsonProperty("FullFinalRate")
    public void setFullFinalRate(String fullFinalRate) {
        FullFinalRate = fullFinalRate;
    }

    public String getFullGasEur() {
        return FullGasEur;
    }

    @JsonProperty("FullGasEur")
    public void setFullGasEur(String fullGasEur) {
        FullGasEur = fullGasEur;
    }

    public String getFullGasRrr() {
        return FullGasRrr;
    }

    @JsonProperty("FullGasRrr")
    public void setFullGasRrr(String fullGasRrr) {
        FullGasRrr = fullGasRrr;
    }

    public String getFullInitialDecline() {
        return FullInitialDecline;
    }

    @JsonProperty("FullInitialDecline")
    public void setFullInitialDecline(String fullInitialDecline) {
        FullInitialDecline = fullInitialDecline;
    }

    public String getFullOilEur() {
        return FullOilEur;
    }

    @JsonProperty("FullOilEur")
    public void setFullOilEur(String fullOilEur) {
        FullOilEur = fullOilEur;
    }

    public String getFullOilRrr() {
        return FullOilRrr;
    }

    @JsonProperty("FullOilRrr")
    public void setFullOilRrr(String fullOilRrr) {
        FullOilRrr = fullOilRrr;
    }

    public String getGasCum() {
        return GasCum;
    }

    @JsonProperty("GasCum")
    public void setGasCum(String gasCum) {
        GasCum = gasCum;
    }

    public String getGasDaily() {
        return GasDaily;
    }

    @JsonProperty("GasDaily")
    public void setGasDaily(String gasDaily) {
        GasDaily = gasDaily;
    }

    public String getGasMaxDaily() {
        return GasMaxDaily;
    }

    @JsonProperty("GasMaxDaily")
    public void setGasMaxDaily(String gasMaxDaily) {
        GasMaxDaily = gasMaxDaily;
    }

    public String getGasPracIpDaily() {
        return GasPracIpDaily;
    }

    @JsonProperty("GasPracIpDaily")
    public void setGasPracIpDaily(String gasPracIpDaily) {
        GasPracIpDaily = gasPracIpDaily;
    }

    public String getGasYear() {
        return GasYear;
    }

    @JsonProperty("GasYear")
    public void setGasYear(String gasYear) {
        GasYear = gasYear;
    }

    public String getGor() {
        return Gor;
    }

    @JsonProperty("Gor")
    public void setGor(String gor) {
        Gor = gor;
    }

    public String getGor2ndMo() {
        return Gor2ndMo;
    }

    @JsonProperty("Gor2ndMo")
    public void setGor2ndMo(String gor2ndMo) {
        Gor2ndMo = gor2ndMo;
    }

    public String getGorCum() {
        return GorCum;
    }

    @JsonProperty("GorCum")
    public void setGorCum(String gorCum) {
        GorCum = gorCum;
    }

    public String getGorLatestMo() {
        return GorLatestMo;
    }

    @JsonProperty("GorLatestMo")
    public void setGorLatestMo(String gorLatestMo) {
        GorLatestMo = gorLatestMo;
    }

    public String getIsDeleted() {
        return IsDeleted;
    }

    @JsonProperty("IsDeleted")
    public void setIsDeleted(String isDeleted) {
        IsDeleted = isDeleted;
    }

    public String getLastProdDate() {
        return LastProdDate;
    }

    @JsonProperty("LastProdDate")
    public void setLastProdDate(String lastProdDate) {
        LastProdDate = lastProdDate;
    }

    public String getLatestFlowRes() {
        return LatestFlowRes;
    }

    @JsonProperty("LatestFlowRes")
    public void setLatestFlowRes(String latestFlowRes) {
        LatestFlowRes = latestFlowRes;
    }

    public String getLatestGas() {
        return LatestGas;
    }

    @JsonProperty("LatestGas")
    public void setLatestGas(String latestGas) {
        LatestGas = latestGas;
    }

    public String getLatestLiq() {
        return LatestLiq;
    }

    @JsonProperty("LatestLiq")
    public void setLatestLiq(String latestLiq) {
        LatestLiq = latestLiq;
    }

    public String getLatestTestDate() {
        return LatestTestDate;
    }

    @JsonProperty("LatestTestDate")
    public void setLatestTestDate(String latestTestDate) {
        LatestTestDate = latestTestDate;
    }

    public String getLatestWcnt() {
        return LatestWcnt;
    }

    @JsonProperty("LatestWcnt")
    public void setLatestWcnt(String latestWcnt) {
        LatestWcnt = latestWcnt;
    }

    public String getLatestWhsip() {
        return LatestWhsip;
    }

    @JsonProperty("LatestWhsip")
    public void setLatestWhsip(String latestWhsip) {
        LatestWhsip = latestWhsip;
    }

    public String getLatestWtr() {
        return LatestWtr;
    }

    @JsonProperty("LatestWtr")
    public void setLatestWtr(String latestWtr) {
        LatestWtr = latestWtr;
    }

    public String getLiqCum() {
        return LiqCum;
    }

    @JsonProperty("LiqCum")
    public void setLiqCum(String liqCum) {
        LiqCum = liqCum;
    }

    public String getLiqDaily() {
        return LiqDaily;
    }

    @JsonProperty("LiqDaily")
    public void setLiqDaily(String liqDaily) {
        LiqDaily = liqDaily;
    }

    public String getLiqMaxDaily() {
        return LiqMaxDaily;
    }

    @JsonProperty("LiqMaxDaily")
    public void setLiqMaxDaily(String liqMaxDaily) {
        LiqMaxDaily = liqMaxDaily;
    }

    public String getLiqPracIpDaily() {
        return LiqPracIpDaily;
    }

    @JsonProperty("LiqPracIpDaily")
    public void setLiqPracIpDaily(String liqPracIpDaily) {
        LiqPracIpDaily = liqPracIpDaily;
    }

    public String getLiqYear() {
        return LiqYear;
    }

    @JsonProperty("LiqYear")
    public void setLiqYear(String liqYear) {
        LiqYear = liqYear;
    }

    public String getMaxActvWells() {
        return MaxActvWells;
    }

    @JsonProperty("MaxActvWells")
    public void setMaxActvWells(String maxActvWells) {
        MaxActvWells = maxActvWells;
    }

    public String getMaxWtrDaily() {
        return MaxWtrDaily;
    }

    @JsonProperty("MaxWtrDaily")
    public void setMaxWtrDaily(String maxWtrDaily) {
        MaxWtrDaily = maxWtrDaily;
    }

    public String getMmcfgeCum() {
        return MmcfgeCum;
    }

    @JsonProperty("MmcfgeCum")
    public void setMmcfgeCum(String mmcfgeCum) {
        MmcfgeCum = mmcfgeCum;
    }

    public String getMonthsProduced() {
        return MonthsProduced;
    }

    @JsonProperty("MonthsProduced")
    public void setMonthsProduced(String monthsProduced) {
        MonthsProduced = monthsProduced;
    }

    public String getPdenGranularity() {
        return PdenGranularity;
    }

    @JsonProperty("PdenGranularity")
    public void setPdenGranularity(String pdenGranularity) {
        PdenGranularity = pdenGranularity;
    }

    public String getPeakBoe() {
        return PeakBoe;
    }

    @JsonProperty("PeakBoe")
    public void setPeakBoe(String peakBoe) {
        PeakBoe = peakBoe;
    }

    public String getPeakBoeMonth() {
        return PeakBoeMonth;
    }

    @JsonProperty("PeakBoeMonth")
    public void setPeakBoeMonth(String peakBoeMonth) {
        PeakBoeMonth = peakBoeMonth;
    }

    public String getPeakBoeMonthNo() {
        return PeakBoeMonthNo;
    }

    @JsonProperty("PeakBoeMonthNo")
    public void setPeakBoeMonthNo(String peakBoeMonthNo) {
        PeakBoeMonthNo = peakBoeMonthNo;
    }

    public String getPeakDailyGasMonth() {
        return PeakDailyGasMonth;
    }

    @JsonProperty("PeakDailyGasMonth")
    public void setPeakDailyGasMonth(String peakDailyGasMonth) {
        PeakDailyGasMonth = peakDailyGasMonth;
    }

    public String getPeakDailyGasMonthNo() {
        return PeakDailyGasMonthNo;
    }

    @JsonProperty("PeakDailyGasMonthNo")
    public void setPeakDailyGasMonthNo(String peakDailyGasMonthNo) {
        PeakDailyGasMonthNo = peakDailyGasMonthNo;
    }

    public String getPeakDailyLiqMonth() {
        return PeakDailyLiqMonth;
    }

    @JsonProperty("PeakDailyLiqMonth")
    public void setPeakDailyLiqMonth(String peakDailyLiqMonth) {
        PeakDailyLiqMonth = peakDailyLiqMonth;
    }

    public String getPeakDailyLiqMonthNo() {
        return PeakDailyLiqMonthNo;
    }

    @JsonProperty("PeakDailyLiqMonthNo")
    public void setPeakDailyLiqMonthNo(String peakDailyLiqMonthNo) {
        PeakDailyLiqMonthNo = peakDailyLiqMonthNo;
    }

    public String getPeakGas() {
        return PeakGas;
    }

    @JsonProperty("PeakGas")
    public void setPeakGas(String peakGas) {
        PeakGas = peakGas;
    }

    public String getPeakGasDaily() {
        return PeakGasDaily;
    }

    @JsonProperty("PeakGasDaily")
    public void setPeakGasDaily(String peakGasDaily) {
        PeakGasDaily = peakGasDaily;
    }

    public String getPeakGasMonth() {
        return PeakGasMonth;
    }

    @JsonProperty("PeakGasMonth")
    public void setPeakGasMonth(String peakGasMonth) {
        PeakGasMonth = peakGasMonth;
    }

    public String getPeakGasMonthNo() {
        return PeakGasMonthNo;
    }

    @JsonProperty("PeakGasMonthNo")
    public void setPeakGasMonthNo(String peakGasMonthNo) {
        PeakGasMonthNo = peakGasMonthNo;
    }

    public String getPeakLiq() {
        return PeakLiq;
    }

    @JsonProperty("PeakLiq")
    public void setPeakLiq(String peakLiq) {
        PeakLiq = peakLiq;
    }

    public String getPeakLiqDaily() {
        return PeakLiqDaily;
    }

    @JsonProperty("PeakLiqDaily")
    public void setPeakLiqDaily(String peakLiqDaily) {
        PeakLiqDaily = peakLiqDaily;
    }

    public String getPeakLiqMonth() {
        return PeakLiqMonth;
    }

    @JsonProperty("PeakLiqMonth")
    public void setPeakLiqMonth(String peakLiqMonth) {
        PeakLiqMonth = peakLiqMonth;
    }

    public String getPeakLiqMonthNo() {
        return PeakLiqMonthNo;
    }

    @JsonProperty("PeakLiqMonthNo")
    public void setPeakLiqMonthNo(String peakLiqMonthNo) {
        PeakLiqMonthNo = peakLiqMonthNo;
    }

    public String getPeakMmcfge() {
        return PeakMmcfge;
    }

    @JsonProperty("PeakMmcfge")
    public void setPeakMmcfge(String peakMmcfge) {
        PeakMmcfge = peakMmcfge;
    }

    public String getPeakMmcfgeMonth() {
        return PeakMmcfgeMonth;
    }

    @JsonProperty("PeakMmcfgeMonth")
    public void setPeakMmcfgeMonth(String peakMmcfgeMonth) {
        PeakMmcfgeMonth = peakMmcfgeMonth;
    }

    public String getPeakMmcfgeMonthNo() {
        return PeakMmcfgeMonthNo;
    }

    @JsonProperty("PeakMmcfgeMonthNo")
    public void setPeakMmcfgeMonthNo(String peakMmcfgeMonthNo) {
        PeakMmcfgeMonthNo = peakMmcfgeMonthNo;
    }

    public String getPrior12Gas() {
        return Prior12Gas;
    }

    @JsonProperty("Prior12Gas")
    public void setPrior12Gas(String prior12Gas) {
        Prior12Gas = prior12Gas;
    }

    public String getPrior12Liq() {
        return Prior12Liq;
    }

    @JsonProperty("Prior12Liq")
    public void setPrior12Liq(String prior12Liq) {
        Prior12Liq = prior12Liq;
    }

    public String getPrior12Wtr() {
        return Prior12Wtr;
    }

    @JsonProperty("Prior12Wtr")
    public void setPrior12Wtr(String prior12Wtr) {
        Prior12Wtr = prior12Wtr;
    }

    public String getPriorGasCum() {
        return PriorGasCum;
    }

    @JsonProperty("PriorGasCum")
    public void setPriorGasCum(String priorGasCum) {
        PriorGasCum = priorGasCum;
    }

    public String getPriorLiqCum() {
        return PriorLiqCum;
    }

    @JsonProperty("PriorLiqCum")
    public void setPriorLiqCum(String priorLiqCum) {
        PriorLiqCum = priorLiqCum;
    }

    public String getPriorWtrCum() {
        return PriorWtrCum;
    }

    @JsonProperty("PriorWtrCum")
    public void setPriorWtrCum(String priorWtrCum) {
        PriorWtrCum = priorWtrCum;
    }

    public String getUpdatedDate() {
        return UpdatedDate;
    }

    @JsonProperty("UpdatedDate")
    public void setUpdatedDate(String updatedDate) {
        UpdatedDate = updatedDate;
    }

    public String getWtrCum() {
        return WtrCum;
    }

    @JsonProperty("WtrCum")
    public void setWtrCum(String wtrCum) {
        WtrCum = wtrCum;
    }

    public String getWtrDaily() {
        return WtrDaily;
    }

    @JsonProperty("WtrDaily")
    public void setWtrDaily(String wtrDaily) {
        WtrDaily = wtrDaily;
    }

    public String getWtrYear() {
        return WtrYear;
    }

    @JsonProperty("WtrYear")
    public void setWtrYear(String wtrYear) {
        WtrYear = wtrYear;
    }

    public String getYield() {
        return Yield;
    }

    @JsonProperty("Yield")
    public void setYield(String yield) {
        Yield = yield;
    }

    public String getYield2ndMo() {
        return Yield2ndMo;
    }

    @JsonProperty("Yield2ndMo")
    public void setYield2ndMo(String yield2ndMo) {
        Yield2ndMo = yield2ndMo;
    }

    public String getYieldLatestMo() {
        return YieldLatestMo;
    }

    @JsonProperty("YieldLatestMo")
    public void setYieldLatestMo(String yieldLatestMo) {
        YieldLatestMo = yieldLatestMo;
    }
}
