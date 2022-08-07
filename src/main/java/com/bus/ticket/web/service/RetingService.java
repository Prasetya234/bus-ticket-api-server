package com.bus.ticket.web.service;

import com.bus.ticket.enggine.response.ModelDelete;
import com.bus.ticket.web.dto.RetingDto;
import com.bus.ticket.web.model.Reting;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface RetingService {
  Reting create(RetingDto retingDto);
  List<Reting> findAll();
  Reting update(int id, RetingDto retingDto);
  ModelDelete delete(int id);
}
