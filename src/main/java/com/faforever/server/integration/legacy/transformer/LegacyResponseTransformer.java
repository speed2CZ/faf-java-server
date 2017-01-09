package com.faforever.server.integration.legacy.transformer;

import com.faforever.server.game.HostGameResponse;
import com.faforever.server.integration.legacy.dto.LoginResponse;
import com.faforever.server.integration.legacy.dto.SessionResponse;
import com.faforever.server.error.ErrorResponse;
import com.faforever.server.integration.response.StartGameProcessResponse;
import com.faforever.server.response.ServerResponse;
import com.google.common.collect.ImmutableMap;
import org.springframework.integration.transformer.GenericTransformer;

import java.io.Serializable;
import java.util.Map;

/**
 * Transforms responses into legacy response formats.
 */
public class LegacyResponseTransformer implements GenericTransformer<ServerResponse, Map<String, Serializable>> {

  // Welcome to the generics hell
  private final Map<Class<? extends ServerResponse>, GenericTransformer<? extends ServerResponse, Map<String, Serializable>>> transformers;

  public LegacyResponseTransformer() {
    transformers = ImmutableMap.<Class<? extends ServerResponse>, GenericTransformer<? extends ServerResponse, Map<String, Serializable>>>builder()
      .put(StartGameProcessResponse.class, LaunchGameResponseTransformer.INSTANCE)
      .put(SessionResponse.class, SessionResponseTransformer.INSTANCE)
      .put(LoginResponse.class, LoginResponseTransformer.INSTANCE)
      .put(ErrorResponse.class, ErrorResponseTransformer.INSTANCE)
      .put(HostGameResponse.class, HostGameResponseTransformer.INSTANCE)
      .build();
  }

  @Override
  @SuppressWarnings("unchecked")
  public Map<String, Serializable> transform(ServerResponse source) {
    return getTransformerFor(source.getClass()).transform(source);
  }

  @SuppressWarnings("unchecked")
  private GenericTransformer<ServerResponse, Map<String, Serializable>> getTransformerFor(Class<? extends ServerResponse> source) {
    return (GenericTransformer<ServerResponse, Map<String, Serializable>>) transformers.get(source);
  }
}