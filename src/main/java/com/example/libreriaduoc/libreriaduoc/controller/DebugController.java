package com.example.libreriaduoc.libreriaduoc.controller;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.OffsetDateTime;
import java.util.Map;

@RestController
@RequestMapping("/debug")
public class DebugController {

  private final JdbcTemplate jdbc;

  public DebugController(JdbcTemplate jdbc) {
    this.jdbc = jdbc;
  }

  @GetMapping("/db")
  public Map<String, Object> db() {
    String version = jdbc.queryForObject("select version()", String.class);
    OffsetDateTime now = jdbc.queryForObject("select now()", OffsetDateTime.class);
    return Map.of("ok", true, "version", version, "now", now);
  }
}