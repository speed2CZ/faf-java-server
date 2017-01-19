package com.faforever.server.client;

import com.faforever.server.request.ClientMessage;
import lombok.Data;

/**
 * Requests the disconnection of a player's client.
 */
@Data
public class DisconnectClientRequest implements ClientMessage {
  private final int playerId;
}
