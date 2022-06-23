package com.study.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.study.model.MemoDTO;
import com.study.model.MemoServiceImpl;
import com.study.utility.Utility;

@Controller
public class MemoController {
   //주석
  @Autowired
  private MemoServiceImpl dao;

  @GetMapping("/")
  public String home() {

    return "/home";
  }

  @GetMapping("/memo/create")
  public String create() {

    return "/create";
  }

  @PostMapping("/memo/create")
  public String create(MemoDTO dto) {
    
    int flag = dao.create(dto);
    if (flag == 1) {
      return "redirect:list";
    } else {
      return "/create";
    }
  }

  @GetMapping("/memo/list")
  public String list(HttpServletRequest request) {
    String col = Utility.checkNull(request.getParameter("col"));
    String word = Utility.checkNull(request.getParameter("word"));

    if (col.equals("total")) {
      word = "";
    }
    // 페이징 관련---
    int nowPage = 1;
    if (request.getParameter("nowPage") != null) {
      nowPage = Integer.parseInt(request.getParameter("nowPage"));
    }

    int recordPerPage = 5;

    int sno = ((nowPage - 1) * recordPerPage);
    int eno = recordPerPage;

    Map map = new HashMap();
    map.put("col", col);
    map.put("word", word);
    map.put("sno", sno);
    map.put("eno", eno);

    List<MemoDTO> list = dao.list(map);
    int total = dao.total(map);
    String paging = Utility.paging(total, nowPage, recordPerPage, col, word);

    // 2. request 저장

    request.setAttribute("list", list);
    request.setAttribute("paging", paging);
    request.setAttribute("col", col);
    request.setAttribute("word", word);
    request.setAttribute("nowPage", nowPage);
    return "/list";
  }

  @GetMapping("/memo/read/{memono}")
  public String read(@PathVariable int memono, Model model) {
    dao.upviewcnt(memono);
    model.addAttribute("dto", dao.read(memono));
    model.addAttribute("memono",memono);

    return "/read";
  }

  @GetMapping("/memo/update/{memono}")
  public String update(@PathVariable int memono, Model model) {

    model.addAttribute("dto", dao.read(memono));
    model.addAttribute("memono",memono);
    return "/update";
  }

  @PostMapping("/memo/update")
  public String update(MemoDTO dto) {

    boolean flag = dao.update(dto); //int 로 하면 인트로 들어오고 boolean 으로 해도 자동으로 true 를 반환
    if (flag ) {
      return "redirect:list";
    } else {
      return "error";
    }
  }

  @GetMapping("/memo/delete/{memono}")
  public String delete(@PathVariable int memono, Model model) {

    model.addAttribute("memono", memono);
    return "/delete";
  }

  @PostMapping("/memo/delete")
  public String delete(int memono,String passwd) {
    //@RequestParam("") 을 사용하면 형변환을 생략할수도있다.
    //int memono = Integer.parseInt(map.get("memono"));
    Map map = new HashMap();
    map.put("memono",memono);
    map.put("passwd",passwd);
    int pflag = dao.passCheck(map);
    int flag = 0;
    if (pflag == 1)
      flag = dao.delete(memono);

    if (pflag != 1)
      return "passwdError";
    else if (flag != 1)
      return "error";
    else
      return "redirect:list";

  }

  @GetMapping("/memo/reply/{memono}")
  public String reply(@PathVariable int memono, Model model) {
    model.addAttribute("dto", dao.readReply(memono));
    model.addAttribute("memono",memono);
    return "/reply";
  }

  @PostMapping("/memo/reply")
  public String reply(MemoDTO dto) {
    Map map = new HashMap();
    map.put("grpno", dto.getGrpno());
    map.put("ansnum", dto.getAnsnum());
    dao.upAnsnum(map);
    if (dao.createReply(dto) ==1) {
      return "redirect:list";
    } else {
      return "error";
    }

  }

}
