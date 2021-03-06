package com.faforever.server.matchmaker;

import com.faforever.server.game.Faction;
import com.faforever.server.request.ClientMessage;
import lombok.Data;

@Data
public class MatchMakerSearchRequest implements ClientMessage {
  private final Faction faction;
  private final String queueName;
}
