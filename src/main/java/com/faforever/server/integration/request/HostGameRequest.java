package com.faforever.server.integration.request;

import com.faforever.server.game.GameAccess;
import com.faforever.server.game.GameVisibility;
import com.faforever.server.request.ClientMessage;
import lombok.Data;

@Data
public class HostGameRequest implements ClientMessage {

  private final String mapName;
  private final String title;
  private final String mod;
  private final GameAccess access;
  private final Integer version;
  private final String password;
  private final GameVisibility visibility;
}
