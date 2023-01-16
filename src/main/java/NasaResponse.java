import com.fasterxml.jackson.annotation.JsonProperty;

public class NasaResponse {
    private final String copyright;
    private final String date;
    private final String explanation;
    private final String hdurl;
    private final String media_type;
    private final String service_version;
    private final String title;
    private final String url;

    public NasaResponse(
            @JsonProperty("copyright") String copyright,
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("hdurl") String hdurl,
            @JsonProperty("media_type") String media_type,
            @JsonProperty("service_version") String service_version,
            @JsonProperty("title") String title,
            @JsonProperty("url") String url
    ) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.hdurl = hdurl;
        this.media_type = media_type;
        this.service_version = service_version;
        this.title = title;
        this.url = url;
    }

    public String getHdurl() {
        return hdurl;
    }

    @Override
    public String toString() {
        return "NasaResponse:" +
                "\ncopyright=" + copyright +
                "\ndate=" + date +
                "\nexplanation=" + explanation +
                "\nhdurl=" + hdurl +
                "\nmedia_type=" + media_type +
                "\nservice_version=" + service_version +
                "\ntitle=" + title +
                "\nurl=" + url;
    }
}