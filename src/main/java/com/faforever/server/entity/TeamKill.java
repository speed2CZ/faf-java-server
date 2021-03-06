package com.faforever.server.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "teamkills")
@Data
@AllArgsConstructor()
@NoArgsConstructor
public class TeamKill {

  @Id
  @GeneratedValue
  private int id;
  @Column(name = "teamkiller")
  private int teamKiller;
  @Column(name = "victim")
  private int victim;
  @Column(name = "game_id")
  private int gameId;
  @Column(name = "gametime")
  private int gameTime;
  @Column(name = "reported_at")
  private Timestamp reportedAt;

}
