package com.apress.spring.web;

import com.apress.spring.domain.Journal;
import com.apress.spring.domain.JournalEntry;
import com.apress.spring.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Hyunjin on 2017-08-21.
 */
//@Controller
//public class JournalController {
//    @Autowired
//    JournalRepository repo;
//
//    @RequestMapping(value="/journal", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
//    public @ResponseBody
//    List<Journal> getJournal() {
//        return repo.findAll();
//    }
//
//    @RequestMapping("/")
//    public String index(Model model) {
//        model.addAttribute("journal", repo.findAll());
//        return "index";
//    }
//}

@RestController
public class JournalController {

    private static List<JournalEntry> entries = new ArrayList<JournalEntry>();
    static {
        try {
            entries.add(new JournalEntry("스프링 부트 입문", "오늘부터 스프링 부트를 배웠다", "08/27/2017"));
            entries.add(new JournalEntry("간단한 스프링 부트 프로젝트", "스프링 부트 프로젝트를 처음 만들어 보았다",
                    "08/27/2017"));
            entries.add(new JournalEntry("스프링 부트 해부", "스프링 부트를 자세히 살펴보았다", "08/27/2017"));
            entries.add(new JournalEntry("스프링 부트 클라우드", "클라우드 파운드리를 응용한 스프링 부트를 공부했다",
                    "08/27/2017"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/journal/all")
    public List<JournalEntry> getAll() throws ParseException {
        return entries;
    }

    @RequestMapping("/journal/findBy/title/{title}")
    public List<JournalEntry> findByTitleContains(@PathVariable String title)
        throws ParseException {
        return entries
                .stream()
                .filter(entries -> entries.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/journal", method = RequestMethod.POST)
    public JournalEntry add(@RequestBody JournalEntry entry) {
        entries.add(entry);
        return entry;
    }
}