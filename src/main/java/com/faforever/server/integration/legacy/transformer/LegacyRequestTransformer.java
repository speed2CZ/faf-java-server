package com.faforever.server.integration.legacy.transformer;

import com.faforever.server.avatar.AvatarRequest;
import com.faforever.server.error.ProgrammingError;
import com.faforever.server.game.*;
import com.faforever.server.game.PlayerOptionRequest;
import com.faforever.server.integration.legacy.dto.ClientMessageType;
import com.faforever.server.integration.request.HostGameRequest;
import com.faforever.server.integration.request.JoinGameRequest;
import com.faforever.server.integration.request.UpdateGameStateRequest;
import com.faforever.server.integration.session.AskSessionRequest;
import com.faforever.server.matchmaker.MatchmakerRequest;
import com.faforever.server.request.ClientRequest;
import com.faforever.server.security.LoginRequest;
import com.faforever.server.social.SocialAddRequest;
import com.faforever.server.social.SocialRemoveRequest;
import org.springframework.integration.transformer.GenericTransformer;

import java.util.ArrayList;
import java.util.Map;

/**
 * Transforms requests of the legacy protocol to internal request objects.
 */
public class LegacyRequestTransformer implements GenericTransformer<Map<String, Object>, ClientRequest> {

  @Override
  public ClientRequest transform(Map<String, Object> source) {
    ClientMessageType command = ClientMessageType.fromString((String) source.get("command"));
    switch (command) {
      case HOST_GAME:
        return new HostGameRequest(
          (String) source.get("mapname"),
          (String) source.get("title"),
          (String) source.get("mod"),
          GameAccess.fromString((String) source.get("access")),
          source.get("version") == null ? null : ((Double) source.get("version")).intValue(),
          (String) source.get("password"),
          GameVisibility.fromString((String) source.get("visibility")));
      case LIST_REPLAYS:
        throw new UnsupportedOperationException("Not supported");
      case JOIN_GAME:
        return new JoinGameRequest();
      case ASK_SESSION:
        return new AskSessionRequest();
      case SOCIAL_ADD:
        return new SocialAddRequest();
      case SOCIAL_REMOVE:
        return new SocialRemoveRequest();
      case LOGIN:
        return new LoginRequest(
          (String) source.get("login"),
          (String) source.get("password"),
          (String) source.get("unique_id"));
      case GAME_MATCH_MAKING:
        return new MatchmakerRequest();
      case AVATAR:
        return new AvatarRequest();
      case GAME_STATE:
        return new UpdateGameStateRequest(GameState.fromString((String) getArgs(source).get(0)));
      case GAME_OPTION:
        ArrayList<Object> args = getArgs(source);
        return new GameOptionRequest((String) args.get(0), args.get(1));
      case PLAYER_OPTION:
        args = getArgs(source);
        return new PlayerOptionRequest(Integer.parseInt((String) args.get(0)), (String) args.get(1), args.get(2));
      case CLEAR_SLOT:
        args = getArgs(source);
        return new ClearSlotRequest((int) args.get(0));
      case AI_OPTION:
        args = getArgs(source);
        return new AiOptionRequest((String) args.get(0), (String) args.get(1), args.get(2));
      default:
        throw new ProgrammingError("Uncovered command: " + command);
    }
  }

  @SuppressWarnings("unchecked")
  private ArrayList<Object> getArgs(Map<String, Object> source) {
    return (ArrayList<Object>) source.get("args");
  }
}