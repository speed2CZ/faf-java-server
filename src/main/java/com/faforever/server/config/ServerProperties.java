package com.faforever.server.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;

@Data
@ConfigurationProperties(prefix = "faf-server", ignoreUnknownFields = false)
public class ServerProperties {

  private int port = 8001;
  private String version = "dev";
  private String apiBaseUrl = "http://localhost:8080";
  private TrueSkill trueSkill = new TrueSkill();
  private Uid uid = new Uid();
  private MatchMaker matchMaker = new MatchMaker();
  private Game game = new Game();
  private GeoIp geoIp = new GeoIp();

  @Data
  public static class GeoIp {
    private String databaseUrl = "http://geolite.maxmind.com/download/geoip/database/GeoLite2-Country.mmdb.gz";
    private Path databaseFile = Paths.get("cache/geoIp.mmdb");
  }

  @Data
  public static class Game {
    /**
     * How many seconds a game needs to run per participating player in order to be ranked.
     */
    private int rankedMinTimeMultiplicator = 60;
  }

  @Data
  public static class TrueSkill {
    private double initialMean = 1500;
    private double initialStandardDeviation = 500;
    private double beta = 240;
    private double dynamicFactor = 10;
    private double drawProbability = 0.1;
  }

  @Data
  public static class MatchMaker {
    /**
     * The minimum calculated game quality two or more players have to have in order to trigger an immediate match.
     */
    private double desiredGameQuality = 0.8d;

    /**
     * The minimum calculated game quality two or more players have to have in order to be matched. Any lower than
     * this will be considered unacceptable so that the players will not be matched. This should be no larger than
     * the quality two new players with initial ratings get to ensure such players get matches.
     */
    private double acceptableGameQuality = 0.4d;

    /**
     * The transition time between requiring {@link #desiredGameQuality} to {@link #acceptableGameQuality} in seconds.
     */
    private long acceptableQualityWaitTime = Duration.ofMinutes(2).getSeconds();
  }

  @Data
  public static class Uid {
    private boolean enabled = true;
    /**
     * PKCS#8 private key without decoration and newlines. Use the following command to convert from PKCS#1:
     * <p>
     * <code>openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in pkcs1.key -out pkcs8.key</code>
     * </p>
     * Note that the key currently has to be a 224 bit key.
     */
    private String privateKey;
    private String linkToSteamUrl;
  }
}
