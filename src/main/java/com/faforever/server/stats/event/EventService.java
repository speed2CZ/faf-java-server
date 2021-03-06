package com.faforever.server.stats.event;

import com.faforever.server.api.ApiAccessor;
import com.faforever.server.entity.Player;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EventService {

  private final ApiAccessor apiAccessor;

  public EventService(ApiAccessor apiAccessor) {
    this.apiAccessor = apiAccessor;
  }

  public void executeBatchUpdate(Player player, List<EventUpdate> eventUpdates) {
    log.debug("Updating '{}' events for player '{}'", eventUpdates.size(), player);
    apiAccessor.updateEvents(eventUpdates);
  }
}
