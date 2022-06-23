package com.study.model;

import java.util.List;
import java.util.Map;

public interface MemoMapper {
  List<MemoDTO> list(Map map) ;
  int total(Map map);
  MemoDTO read(int memono);
  void upviewcnt(int memono);
  boolean update(MemoDTO dto);
  int delete(int memono);
  int passCheck(Map map);
  int create(MemoDTO dto);
  MemoDTO readReply(int memono);
  void upAnsnum(Map map);
  int createReply(MemoDTO dto);
}
