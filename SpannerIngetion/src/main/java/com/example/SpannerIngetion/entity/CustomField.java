package com.example.SpannerIngetion.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.cloud.spring.data.spanner.core.mapping.Table;
import lombok.Data;

@Data
@Table(name = "CustomField") // 👈 Spanner Table Name
public class CustomField {

    @PrimaryKey
    private String id;

    @JsonProperty("CF_UHG_UHC_memberType")
    private String memberType;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_device_model")
    private String deviceModel;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_raliyid")
    private String raliyid;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_device_type")
    private String deviceType;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_device_brand")
    private String deviceBrand;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_device_display2oom")
    private boolean deviceDisplayZoom;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_device_mobile")
    private boolean deviceMobile;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_device_systemVersion")
    private String deviceSystemVersion;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_device_application")
    private String deviceApplication;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_application_previousRoute")
    private String applicationPreviousRoute;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_device_OS")
    private String deviceOS;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_mobileSessionID")
    private String mobileSessionID;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_policyNumber")
    private String policyNumber;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_application_currentRoute")
    private String applicationCurrentRoute;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_genoa_city")
    private String genoaCity;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_genoa_count")
    private int genoaCount;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_genoa_event")
    private String genoaEvent;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_genoa_zipCode")
    private int genoaZipCode;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_genoa_timestamp")
    private long genoaTimestamp;

    @JsonProperty("CF_UHG_UHC_UnitedHealthcareMobile_genoa_date")
    private String genoaDate;
}