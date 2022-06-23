package com.study.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("com.study.model.MemoServiceImpl")
public class MemoServiceImpl implements MemoService {
  
  @Autowired
  private MemoMapper memoMapper;
  
  //리스트
  public List<MemoDTO> list(Map map) {
    return memoMapper.list(map);
  }

  //토탈
  public int total(Map map) {
    return memoMapper.total(map);
  }
  //조회수 증가
  public void upviewcnt(int memono) {
    memoMapper.upviewcnt(memono);
  }
  //읽기
  public MemoDTO read(int memono) {
    return memoMapper.read(memono);
  }
  //수정
  public boolean update(MemoDTO dto) {
    return memoMapper.update(dto);
  }
  //비번체크
  public int passCheck(Map map) {
    
    return memoMapper.passCheck(map);
  }
  //삭제
  public int delete(int memono) {
    
    return memoMapper.delete(memono);
  }
  //생성
  public int create(MemoDTO dto) {
    return memoMapper.create(dto);
  }

  public MemoDTO readReply(int memono) {
    return memoMapper.readReply(memono);
  }

  
  public void upAnsnum(Map map) {
    memoMapper.upAnsnum(map);
  }

  public int createReply(MemoDTO dto) {
    // TODO Auto-generated method stub
    return memoMapper.createReply(dto);
  }

}
