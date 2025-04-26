package com.example.SpannerIngetion.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.cloud.spring.data.spanner.core.mapping.Table;
import lombok.Data;

@Data
@Table(name = "TopLevelCustom")  // <-- Your Spanner Table Name
public class TopLevelCustomField {

    @PrimaryKey
    @JsonProperty("eventId")
    private String eventId;

    @JsonProperty("@timestamp")
    private String timestamp;

    @JsonProperty("pixelTransformerHostName")
    private String pixelTransformerHostName;

    @JsonProperty("eventProcessingTimeNanos")
    private long eventProcessingTimeNanos;

    @JsonProperty("browserEvent")
    private String browserEvent;

    @JsonProperty("eventType")
    private String eventType;

    @JsonProperty("originalDardr")
    private String originalDardr;

    @JsonProperty("userAgentsMobileDevice")
    private String userAgentsMobileDevice;

    @JsonProperty("timeStampUnix")
    private long timeStampUnix;

    @JsonProperty("timeStampMinuteUTC")
    private int timeStampMinuteUTC;

    @JsonProperty("userAgentDevice")
    private String userAgentDevice;

    @JsonProperty("DARID")
    private String darid;

    @JsonProperty("cseListenerSequenceId")
    private String cseListenerSequenceId;

    @JsonProperty("pageURLHostAndPath")
    private String pageURLHostAndPath;

    @JsonProperty("serverTimestamp")
    private String serverTimestamp;

    @JsonProperty("timeOnPageTotal")
    private long timeOnPageTotal;

    @JsonProperty("userAgentOS")
    private String userAgentOS;

    @JsonProperty("timeStampHourUTC")
    private int timeStampHourUTC;

    @JsonProperty("optedOut")
    private boolean optedOut;

    @JsonProperty("ubrid")
    private String ubrid;

    @JsonProperty("timeStampMonthNumUTC")
    private int timeStampMonthNumUTC;

    @JsonProperty("timeStampMonthUTC")
    private String timeStampMonthUTC;

    @JsonProperty("timeStampDayUTC")
    private int timeStampDayUTC;

    @JsonProperty("userAgent")
    private String userAgent;

    @JsonProperty("pageURL")
    private String pageURL;

    @JsonProperty("pageURLPath")
    private String pageURLPath;

    @JsonProperty("optumPixelId")
    private String optumPixelId;

    @JsonProperty("timeStampYearUTC")
    private int timeStampYearUTC;

    @JsonProperty("userAgentBrowser")
    private String userAgentBrowser;

    @JsonProperty("pageVisitEventCount")
    private int pageVisitEventCount;

    @JsonProperty("timeStampsSecondUTC")
    private int timeStampsSecondUTC;

    @JsonProperty("webServerHost")
    private String webServerHost;
    @JsonProperty("custom_fields")
    private  CustomField customField;

}
